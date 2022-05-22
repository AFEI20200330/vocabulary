package com.example.vocabulary.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.vocabulary.R;

public class CustomDialog extends Dialog {
    private TextView mTvTitle,mTvMessage,mTvCancel,mTvConfirm;
    private String title,message,cancel,confirm;
    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        mTvTitle=findViewById(R.id.tv_title);
        mTvMessage=findViewById(R.id.tv_message);
        mTvCancel=findViewById(R.id.tv_cancle);
        mTvConfirm=findViewById(R.id.tv_confirm);


    }
}
