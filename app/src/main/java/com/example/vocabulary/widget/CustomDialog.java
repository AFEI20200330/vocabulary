package com.example.vocabulary.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.vocabulary.R;

public class CustomDialog extends Dialog implements View.OnClickListener{

    //也可以用建造者模式builder写
    private TextView mTvTitle,mTvMessage,mTvCancel,mTvConfirm;

    private IOnCancelListener cancelListener;
    private IOnConfirmListener confirmListener;
    private String title,message,cancel,confirm;
    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomDialog( @NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        //设置宽度，通用代码
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        Point size = new Point();
        d.getSize(size);
        p.width= (int) (size.x*0.8);//设置dialog的宽度为当前手机屏幕的宽度的百分之八十
        getWindow().setAttributes(p);


        mTvTitle=findViewById(R.id.tv_title);
        mTvMessage=findViewById(R.id.tv_message);
        mTvCancel=findViewById(R.id.tv_cancle);
        mTvConfirm=findViewById(R.id.tv_confirm);
        if(!TextUtils.isEmpty(title)){
            mTvTitle.setText(title);
        }
       if(!TextUtils.isEmpty(message)){
            mTvMessage.setText(message);
       }
        if(!TextUtils.isEmpty(cancel)){
            mTvCancel.setText(cancel);
        }
        if(!TextUtils.isEmpty(confirm)){
            mTvConfirm.setText(confirm);
        }
        mTvCancel.setOnClickListener(this);
        mTvConfirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancle:
                if(cancelListener!=null){
                    cancelListener.onCancel(this);
                }
                break;
            case R.id.tv_confirm:
                if(confirmListener!=null)
                {
                    confirmListener.onConfirm(this);
                }
                break;
        }
    }

    public interface IOnCancelListener{
        void onCancel(CustomDialog dialog);
    }

    public interface  IOnConfirmListener{
        void onConfirm(CustomDialog dialog);
    }



    public CustomDialog setTitle(String title) {
        this.title = title;
        return this;
    }


    public CustomDialog setMessage(String message) {
        this.message = message;
        return this;
    }


    public CustomDialog setCancel(String cancel,IOnCancelListener listener) {
        this.cancel = cancel;
        this.cancelListener=listener;
        return this;
    }

    public CustomDialog setConfirm(String confirm,IOnConfirmListener listener) {
        this.confirm = confirm;
        this.confirmListener=listener;
        return this;
    }
}
