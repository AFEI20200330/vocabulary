package com.example.vocabulary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.example.vocabulary.util.Dao;
import com.example.vocabulary.util.ToastUtil;

import java.util.Random;

public class MemoryActivity extends AppCompatActivity {
    private WebView webViewMemory;
    private Button btBs;
    private Button btJy;
    private Button btFx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);


        webViewMemory = (WebView) findViewById(R.id.wv);
        btBs=findViewById(R.id.bt_bs);
        btJy=findViewById(R.id.bt_jiyi);
        btFx=findViewById(R.id.bt_fuxi);

        btBs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("开始背诵模式");
//                String word="abandon";
//                String a="A.放弃，抛弃，遗弃";
//                String b="B.那里，在那里";
//                String c="C.不放弃，不抛弃，坚持";
//                String tru="答对了";
//                String fal="答错了";
                Random random = new Random();
                int k = random.nextInt(3) + 1;
                String[] s = new String[]{"abandon", "放弃，抛弃，遗弃", "那里，在那里", "不放弃，不抛弃，坚持"};
                builderSet(s, k);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.memory_men,menu);
        return true;
    }

    //String[]数组用来存储多个信息，其中String[0]存储单词，String[1]存储正确选项，String[2]存储错误选项，String[3]存储错选项2
    public boolean builderSet(String[] s,int k) {
        if(s == null) {
            return false;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(MemoryActivity.this);
        switch (k) {
            case 1:

                builder.setTitle("请选择你认为正确的选项:").setMessage(s[0])
                        .setPositiveButton("A."+s[1], new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToastUtil.showMsg(MemoryActivity.this, "答对了");
                                dialog.dismiss();
                            }
                        }).setNeutralButton("B."+s[2], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtil.showMsg(MemoryActivity.this, "答错了");
                        ToastUtil.showMsg(MemoryActivity.this, "正确答案应该是:" + s[1]);
                        setTitle(s[0]+s[1]);
                    }
                }).setNegativeButton("C."+s[3], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtil.showMsg(MemoryActivity.this,"答错了");
                        ToastUtil.showMsg(MemoryActivity.this, "正确答案应该是:" + s[1]);
                        setTitle(s[0]+s[1]);
                    }
                }).setCancelable(false).show();
                return true;
            case 2:
                builder.setTitle("请选择你认为正确的选项:").setMessage(s[0])
                        .setPositiveButton("A."+s[2], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtil.showMsg(MemoryActivity.this, "答错了");
                        ToastUtil.showMsg(MemoryActivity.this, "正确答案应该是:" + s[1]);
                        setTitle(s[0]+s[1]);
                    }
                }).setNeutralButton("C."+s[1], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtil.showMsg(MemoryActivity.this, "答对了");
                        dialog.dismiss();
                    }
                }).setNegativeButton("B."+s[3], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtil.showMsg(MemoryActivity.this,"答错了");
                        ToastUtil.showMsg(MemoryActivity.this, "正确答案应该是:" + s[1]);
                        setTitle(s[0]+s[1]);
                    }
                }).setCancelable(false).show();
                return true;
            case 3:
                builder.setTitle("请选择你认为正确的选项:").setMessage(s[0])
                        .setNeutralButton("C."+s[2], new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToastUtil.showMsg(MemoryActivity.this, "答错了");
                                ToastUtil.showMsg(MemoryActivity.this, "正确答案应该是:" + s[1]);
                                setTitle(s[0]+s[1]);
                            }
                        }).setPositiveButton("A."+s[3], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtil.showMsg(MemoryActivity.this,"答错了");
                        ToastUtil.showMsg(MemoryActivity.this, "正确答案应该是:" + s[1]);
                        setTitle(s[0]+s[1]);
                    }
                }).setNegativeButton("B."+s[1], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtil.showMsg(MemoryActivity.this, "答对了");
                        dialog.dismiss();
                    }
                }).setCancelable(false).show();
                return true;

            default:
                break;

        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean flag=false;
        if(item.getItemId() !=0){
            flag =true;
        }
        while(flag) {
            switch (item.getItemId()) {
                case R.id.item1:
                    showMessage("开始背诵模式");
//                String word="abandon";
//                String a="A.放弃，抛弃，遗弃";
//                String b="B.那里，在那里";
//                String c="C.不放弃，不抛弃，坚持";
//                String tru="答对了";
//                String fal="答错了";
                    Random random = new Random();
                    int k = random.nextInt(3) + 1;
                    String[] s = new String[]{"abandon", "放弃，抛弃，遗弃", "那里，在那里", "不放弃，不抛弃，坚持"};
                    builderSet(s, k);
                    flag=false;
//                AlertDialog.Builder builder = new AlertDialog.Builder(MemoryActivity.this);
//                builder.setTitle("请选择你认为正确的选项:").setMessage(word)
//                        .setPositiveButton(a, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                ToastUtil.showMsg(MemoryActivity.this,tru);
//                                dialog.dismiss();
//                            }
//                        }).setNeutralButton(b, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        ToastUtil.showMsg(MemoryActivity.this,fal);
//                        ToastUtil.showMsg(MemoryActivity.this,"正确答案应该是:"+a);
//                        setTitle(a);
//                    }
//                }).setNegativeButton(c, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        ToastUtil.showMsg(MemoryActivity.this,fal);
//                        ToastUtil.showMsg(MemoryActivity.this,"正确答案应该是:"+a);
//                        setTitle(word+a);
//                    }
//                }).setCancelable(false).show();
                    break;
                case R.id.item2:
                    showMessage("开始记忆模式");
                    flag =false;
                    break;
                case R.id.item4:
                    //webView加载本地的html页面
                    webViewMemory.loadUrl("file:///android_asset/AboutUs.html");
                    flag=false;
                    break;
                case R.id.item5:
                    //加载网页
                    Intent intent = new Intent(MemoryActivity.this, LearnMoreActivity.class);
                    startActivity(intent);
                    flag=false;
                    break;
                default:
                    break;
            }
        }
            return true;

    }

    public void showMessage(String msg)
    {
//        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
//        Toast toast = Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER,0,0);
//        toast.show();
        ToastUtil.showMsg(getApplicationContext(),msg);

    }



}