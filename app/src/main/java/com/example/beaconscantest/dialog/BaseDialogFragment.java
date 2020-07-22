package com.example.beaconscantest.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.example.beaconscantest.R;
import com.google.android.material.snackbar.Snackbar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;


/**
 * 현재 이 부분 그린램프앱에서 그대로 복사해온 것
 */

public abstract class BaseDialogFragment extends DialogFragment {
    protected OnResultListener mListener;
    private Toast mToast;
    private Snackbar mSnackBar;
    private Unbinder unbionder;
    private ProgressBar mProgressBar;

    public abstract void initBundleData();

    public abstract Integer inflateLayout();

    public abstract void initLayout(View view);

    public void setOnResultListener(OnResultListener listener) {
        mListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBundleData();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() == null) {
            return;
        }
//        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(inflateLayout(), container, false);
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        unbionder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLayout(view);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        unbionder.unbind();
    }

    public void setResult(boolean dismiss, Object... objects) {
        hideKeyboard2(getActivity());
        if (mListener != null) {
            mListener.onDialogResult(objects);
        }
        if (dismiss) {
            dismiss();
        }
    }

    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(getDialog().getContext(), msg, Toast.LENGTH_SHORT);
            mToast.show();
        } else {
            mToast.setText(msg);
            mToast.show();
        }
    }

    public void showSnackBar(String msg) {
        if (mSnackBar == null) {
            mSnackBar = Snackbar.make(getActivity().getWindow().getDecorView().findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT);
            mSnackBar.show();
        } else {
            mSnackBar.setText(msg);
            mSnackBar.show();
        }
    }

    public void showSnackBar(String msg, String action, View.OnClickListener listener) {
        if (mSnackBar == null) {
            mSnackBar = Snackbar.make(getActivity().getWindow().getDecorView().findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT);
            mSnackBar.setAction(action, listener);
            mSnackBar.show();
        } else {
            mSnackBar.setText(msg);
            mSnackBar.setAction(action, listener);
            mSnackBar.show();
        }
    }

    private void hideKeyboard(final Activity activity) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (activity == null) {
                    return;
                }
                if (activity.getCurrentFocus() == null) {
                    return;
                }
                if (activity.getCurrentFocus().getWindowToken() == null) {
                    return;
                }

                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 200);
    }

    /**
     * Local Methods
     * 프로그레스바 보여주기
     */
    public void showProgressBar() {
        if (mProgressBar == null) {
            mProgressBar = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyle);
            mProgressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent),
                    android.graphics.PorterDuff.Mode.MULTIPLY);
            mProgressBar.setBackgroundResource(android.R.color.transparent);
            RelativeLayout rl = new RelativeLayout(getActivity());
            rl.setGravity(Gravity.CENTER);
            rl.setBackgroundResource(android.R.color.transparent);
            rl.addView(mProgressBar);

            RelativeLayout.LayoutParams params = new
                    RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            ViewGroup layout = (ViewGroup) ((Activity) getActivity()).findViewById(android.R.id.content).getRootView();
            layout.addView(rl, params);

            mProgressBar.setIndeterminate(true);
        }

        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        mProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 프로그레스바 숨기기
     */
    public void hideProgressBar() {
        Timber.w("" + mProgressBar);
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);

            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    public void hideKeyboard2(final Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface OnResultListener {
        void onDialogResult(Object... obj);
    }
}
