package com.example.beaconscantest.dialog;

import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.beaconscantest.R;

import butterknife.BindView;
import butterknife.OnClick;

public class DefaultDialog extends BaseDialogFragment{

    @BindView(R.id.dialog_msg)
    AppCompatTextView dialogMsg;

    @BindView(R.id.dialog_cancel_button)
    AppCompatButton dialogCancelButton;

    @BindView(R.id.dialog_confirm_button)
    AppCompatButton dialogConfirmButton;

    private String contents;
    private String confirmText;
    private String cancelText;
    private int buttonType = 0;


    /**
     * 그린램프 앱에는 onStart 부분이 있음
     */

    @Override
    public void initBundleData() {
        setCancelable(false);

        if(getArguments() != null){
            contents = getArguments().getString("dialog_msg");
            confirmText = getArguments().getString("dialog_btn_txt_ok");
            cancelText = getArguments().getString("dialog_btn_txt_cancel");
            buttonType = getArguments().getInt("dialog_type",0);
        }

    }

    @Override
    public Integer inflateLayout() {
        return R.layout.dialog_default;
    }

    @Override
    public void initLayout(View view) {
        if(contents != null){
            dialogMsg.setText(contents);
        }
        if (buttonType == 1){
            dialogCancelButton.setVisibility(View.GONE);
        }
        if (confirmText != null){
            dialogConfirmButton.setText(confirmText);
        }
        if (cancelText != null) {
            dialogCancelButton.setText(cancelText);
        }

    }

    @OnClick(R.id.dialog_cancel_button)
    public void cancelButtonClick() {
        if(mListener != null){
            mListener.onDialogResult(false);
        }
        dismiss();
    }

    @OnClick(R.id.dialog_confirm_button)
    public void confirmButtonClick() {
        if(mListener != null){
            mListener.onDialogResult(true);
        }
        dismiss();
    }

}
