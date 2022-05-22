package com.example.vocabulary;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button bt1;
    private Button bt2;
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
                Intent intent = new Intent(MainActivity.this,MemoryActivity.class);
                startActivity(intent);
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