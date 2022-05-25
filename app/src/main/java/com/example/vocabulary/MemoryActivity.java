package com.example.vocabulary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.example.vocabulary.Entity.Word;
import com.example.vocabulary.util.DBOpenHelper;
import com.example.vocabulary.util.Dao;
import com.example.vocabulary.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MemoryActivity extends AppCompatActivity {
    private WebView webViewMemory;
    private Button btBs;
    private Button btJy;
    private Button btFx;
    private DBOpenHelper dbOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        //实例化dbOpenheler
        dbOpenHelper = new DBOpenHelper(MemoryActivity.this,"Person.db",null,1);
        webViewMemory = (WebView) findViewById(R.id.wv);
        btBs=findViewById(R.id.bt_bs);
        btJy=findViewById(R.id.bt_jiyi);
        btFx=findViewById(R.id.bt_fuxi);

        btJy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("开始记忆模式");
                Intent intent = new Intent(MemoryActivity.this,JYActivity.class);
                startActivity(intent);
            }
        });

        btFx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("开始复习模式");
                BS();
            }
        });
//
//        Random random = new Random();
//        int rid = random.nextInt(340)+1;
//        String[] rd={String.valueOf(rid)};
//        System.out.println("rid是"+rid);
//        Cursor cursor = dbOpenHelper.getReadableDatabase().query("EnWords",null,"id=?",rd,null,null,null);
//
////        DBOpenHelper dbOpenHelper = new DBOpenHelper(MemoryActivity.this,"Person.db",null,1);
//
//        ArrayList<Word> arrayList= new ArrayList<Word>();
//        Word word=new Word();
//        while (cursor.moveToNext()){
////                    Map<String,String> map = new HashMap<String,String>();
//            word.setId(cursor.getInt(0));
//            word.setWord(cursor.getString(1));
//            word.setTranslation(cursor.getString(2));
//            arrayList.add(word);
//            System.out.println(arrayList);
////                    map.put("word",cursor.getString(1);
////                    map.put("translation",cursor.getString(2));
////                    arrayList.add(map);
//        }

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

//                int rid = random.nextInt(340)+1;
//                String[] rd={String.valueOf(rid)};
//                System.out.println(rd);
//                Cursor cursor = MemoryActivity.this.dbOpenHelper.getReadableDatabase().query("Enwords",null,"id=?",rd,null,null,null);
//
//                ArrayList<Word> arrayList= new ArrayList<Word>();
//                Word word=new Word();
//                while (cursor.moveToNext()){
////                    Map<String,String> map = new HashMap<String,String>();
//                    word.setId(cursor.getInt(0));
//                    word.setWord(cursor.getString(1));
//                    word.setTranslation(cursor.getString(2));
//                    arrayList.add(word);
//                    System.out.println(arrayList);
////                    map.put("word",cursor.getString(1);
////                    map.put("translation",cursor.getString(2));
////                    arrayList.add(map);
//                }
//                if(arrayList == null || arrayList.size() ==0){
//                    Toast.makeText(MemoryActivity.this, "出错了", Toast.LENGTH_SHORT).show();
//                }else{
//                    int init=1;
//                    int id=random.nextInt(init+6);
//
////                    String[] s = new String[]{}
//                }
                BS();
            }
        });

    }

    public void BS(){
        Random random = new Random();
        String[] s1 = new String[]{"abandon", "放弃，抛弃，遗弃", "那里，在那里", "不放弃，不抛弃，坚持"};
        String[] s2 = new String[]{"book","书本，书籍,课本","义务，感受，责任","放弃，抛弃，遗弃"};
        String[] s3 = new String[]{"Money","金钱，财产,钱币","书本，课本，书籍","放弃，抛弃，遗弃"};
        String[] s4 = new String[]{"administration","行政管理","书本，课本，书籍","放弃，抛弃，遗弃"};
        int k = random.nextInt(3) + 1;
        int i=random.nextInt(5)+1;
        switch (i){
            case 1:
                builderSet(s1, k);
                break;
            case 2:
                builderSet(s2, k);
                break;
            case 3:
                builderSet(s3, k);
                break;
            case 4:
                builderSet(s4, k);
                break;
            default:
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.memory_men,menu);
        return true;
    }

    //String[]数组用来存储多个信息，其中String[0]存储单词，String[1]存储正确选项，String[2]存储错误选项，String[3]存储错选项2
    public void builderSet(String[] s,int k) {
        if(s == null) {
            return;
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
                break;
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
                break;
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
                break;

            default:
                break;

        }
        return;
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
                    Intent intent = new Intent(MemoryActivity.this,JYActivity.class);
                    startActivity(intent);
                    flag =false;
                    break;
                case R.id.item4:
                    //webView加载本地的html页面
                    webViewMemory.loadUrl("file:///android_asset/AboutUs.html");
                    flag=false;
                    break;
                case R.id.item5:
                    //加载网页
                    Intent intent2 = new Intent(MemoryActivity.this, LearnMoreActivity.class);
                    startActivity(intent2);
                    flag=false;
                    break;
                case R.id.it_exit:
                    Intent intent3 = new Intent(MemoryActivity.this,LoginActivity.class);
                    startActivity(intent3);
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