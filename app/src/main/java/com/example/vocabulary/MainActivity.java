package com.example.vocabulary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.example.vocabulary.widget.CustomDialog;

public class MainActivity extends AppCompatActivity {
    private Button bt1;
    private Button bt2;
    private Button bt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bt1=findViewById(R.id.bt_register);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });

        bt2=findViewById(R.id.bt_login);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog(MainActivity.this);
                customDialog.setTitle("提示").setMessage("确认登录吗？").setCancel("取消", new CustomDialog.IOnCancelListener() {
                    @Override
                    public void onCancel(CustomDialog dialog) {
                            dialog.dismiss();
                    }
                }).setConfirm("确认", new CustomDialog.IOnConfirmListener() {
                    @Override
                    public void onConfirm(CustomDialog dialog) {
                 Intent intent = new Intent(MainActivity.this,MemoryActivity.class);
                startActivity(intent);
                    }
                }).show();


            }
        });

        bt3=(Button)findViewById(R.id.bt_else_way);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("请选择你想要登录的方式:").setNegativeButton("WeChat账号", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, null);
                        startActivity(intent);
                    }
                }).setNeutralButton("Github账号", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, null);
                        startActivity(intent);
                    }
                }).setPositiveButton("Twitter账号", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, null);
                        startActivity(intent);
                    }
                }).show();
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager inputMethodManager = null;

        if(inputMethodManager == null){
            inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            //获取当前焦点控件
            View  view =getCurrentFocus();
            if(view !=null && inputMethodManager != null){
                //以非永久的方式隐藏当前窗口的软键盘
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                view.clearFocus();
            }
        }
        return super.onTouchEvent(event);
    }
}