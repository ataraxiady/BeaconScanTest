package com.example.beaconscantest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
    private Toast toast;
    private ProgressBar progressBar;

    public void showToast(String msg){
        if(toast == null){
            toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
            toast.show();
        }else {
            toast.setText(msg);
            toast.show();
        }
    }

    /**
     * 프로그레스바 보이기
     */
    public void showProgressBar(){
        if(progressBar == null){
            progressBar = new ProgressBar(this,null,android.R.attr.progressBarStyle);
            progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), android.graphics.PorterDuff.Mode.MULTIPLY);
            progressBar.setBackgroundResource(android.R.color.transparent);

            RelativeLayout rl = new RelativeLayout(this);
            rl.setGravity(Gravity.CENTER);
            rl.setBackgroundResource(android.R.color.transparent);
            rl.addView(progressBar);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            ViewGroup layout = (ViewGroup) ((Activity) this).findViewById(android.R.id.content).getRootView();
            layout.addView(rl, params);

            progressBar.setIndeterminate(true);
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        progressBar.setVisibility(View.VISIBLE);
    }


    /**
     * 프로그레스바 숨기기
     */
    public void hideProgressBar() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);

            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }


    /**
     * 브라우저 실행
     */
    public void runBrowser(String url) {
        if (url.length() > 0 && url.contains("http://")
                || url.length() > 0 && url.contains("https://")) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            Uri u = Uri.parse(url);
            i.setData(u);
            startActivity(i);
        }
    }


    /**
     * 에러만 보여주는 다이얼로그
     */
//    public void showErrorDialog() {
//        DefaultDialog defaultDialog = new DefaultDialog();
//        Bundle b = new Bundle();
//        b.putString("dialog_msg", getString(R.string.common_err_api_msg));
//        b.putInt("dialog_type", 1);
//        defaultDialog.setArguments(b);
////        defaultDialog.setOnResultListener(new BaseDialogFragment.OnResultListener() {
////            @Override
////            public void onDialogResult(Object... objects) {
////                boolean isOk = (boolean) objects[0];
////                if (isOk) {
////                    if (mDialogEventListener != null) {
////                        mDialogEventListener.onReceivedEvent();
////                        mDialogEventListener = null;
////                    }
////                }
////            }
////        });
//        defaultDialog.show(getSupportFragmentManager(), "DefaultDialog");
//    }



}