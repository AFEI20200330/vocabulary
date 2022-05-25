package com.example.vocabulary;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vocabulary.util.MD5Utils;
import com.example.vocabulary.util.MyDatabaseHelper;
import com.example.vocabulary.widget.CustomDialog;

public class LoginActivity extends AppCompatActivity {
    final int NOTIFYID=0x123;//通知ID
    private Button btn_register;
    private Button btn_login;
    private Button bt3;
    private Button btn_gorget;
    private int id=0;

    private String userName,psw,spPsw;
    private EditText et_user_name,et_psw;
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel("@me","@我的消息",NotificationManager.IMPORTANCE_HIGH);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btn_gorget=(Button)findViewById(R.id.forget_bt);
        dbHelper = new MyDatabaseHelper(this,"Person.db",null,1);
        btn_gorget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });


//        btn_register=findViewById(R.id.btn_register);
//        btn_register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//                startActivity(intent);
//            }
//        });

//        btn_login=findViewById(R.id.btn_login);
//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CustomDialog customDialog = new CustomDialog(LoginActivity.this);
//                customDialog.setTitle("提示").setMessage("确认登录吗？").setCancel("取消", new CustomDialog.IOnCancelListener() {
//                    @Override
//                    public void onCancel(CustomDialog dialog) {
//                            dialog.dismiss();
//                    }
//                }).setConfirm("确认", new CustomDialog.IOnConfirmListener() {
//                    @Override
//                    public void onConfirm(CustomDialog dialog) {
////                 Intent intent = new Intent(MainActivity.this,MemoryActivity.class);
////                startActivity(intent);
//                        //隐式跳转
//                        Intent intent1 = new Intent();
//                        intent1.setAction("android.intent.action.memory");
//                        startActivity(intent1);
//                    }
//                }).show();
//
//
//            }
//        });

        bt3=(Button)findViewById(R.id.bt_else_way);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("请选择你想要登录的方式:").setNegativeButton("WeChat账号", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(LoginActivity.this, null);
                        startActivity(intent);
                    }
                }).setNeutralButton("Github账号", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(LoginActivity.this, null);
                        startActivity(intent);
                    }
                }).setPositiveButton("Twitter账号", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(LoginActivity.this, null);
                        startActivity(intent);
                    }
                }).show();
            }
        });
        btn_register=findViewById(R.id.btn_register);
        btn_login=findViewById(R.id.btn_login);
        et_user_name=findViewById(R.id.et_user_name);
        et_psw=findViewById(R.id.et_psw);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        CustomDialog customDialog = new CustomDialog(LoginActivity.this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName=et_user_name.getText().toString().trim();
                psw=et_psw.getText().toString().trim();
                String md5Psw= MD5Utils.md5(psw);
                spPsw=readPsw(userName);
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(md5Psw.equals(spPsw)){
                    customDialog.setTitle("提示").setMessage("确认登录吗？").setCancel("取消", new CustomDialog.IOnCancelListener() {
                        @Override
                        public void onCancel(CustomDialog dialog) {
                            dialog.dismiss();
                        }
                    }).setConfirm("确认", new CustomDialog.IOnConfirmListener() {
                        @Override
                        public void onConfirm(CustomDialog dialog) { Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            saveLoginStatus(true, userName);
                            Intent data=new Intent();
                            data.putExtra("isLogin",true);
                            setResult(RESULT_OK,data);
                            LoginActivity.this.finish();
                            data.setAction("android.intent.action.memory");
                            startActivity(data);
                            dialog.dismiss();

                            NotificationManager manager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            Intent intent = new Intent(LoginActivity.this,MemoryActivity.class);
                            PendingIntent pendingIntent = PendingIntent.getActivity(
                                    LoginActivity.this,
                                    0,
                                    intent,
                                    PendingIntent.FLAG_MUTABLE
                            );
                            Intent bcIntent=new Intent("com.example.notification.CLOSE_NOTIFICATION");
                            bcIntent.setPackage(getPackageName());//重要，不设置可能会无效
                            bcIntent.putExtra("nid",id);

                            PendingIntent onClickPendingIntent = PendingIntent.getBroadcast(
                                    LoginActivity.this,
                                    id,
                                    bcIntent,
                                    PendingIntent.FLAG_MUTABLE|PendingIntent.FLAG_UPDATE_CURRENT
                            );
                            Notification notification = new NotificationCompat.Builder(LoginActivity.this,"@me")
                                    .setContentInfo("登录通知")
                                    .setContentText("您已登录阿飞单词书，请知悉")
                                    .setContentIntent(pendingIntent)
                                    .setWhen(System.currentTimeMillis())
                                    .setSmallIcon(R.drawable.bb)
                                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.bb))
                                    .setAutoCancel(true)
                                    .setDefaults(Notification.DEFAULT_ALL)
                                    .addAction(0,"关闭通知",onClickPendingIntent)
                                    .build();
                            manager.notify(id++,notification);


//                        //创建并发送通知
//                        //获取通知管理对象
//                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                        //创建通知对象
//                        NotificationCompat.Builder notification = new NotificationCompat.Builder(
//                        LoginActivity.this,"@me")
//                        //设置通知图标
//                            .setSmallIcon(R.drawable.bb)
//                        //通知标题
//                            .setContentTitle("登录通知")
//                            .setContentText("您已经登陆了阿飞单词书，点击查看详情")
//                        //设置发送时间
//                            .setWhen(System.currentTimeMillis())
//                        //设置声音和振动
//                            .setDefaults(Notification.DEFAULT_SOUND| Notification.DEFAULT_VIBRATE);
//                        //创建一个启动一个详细页面的intent
////                        notificationManager.notify(1,
////                                notification.build());
//                        System.out.println("通知已发送");
//
//                        Intent intent = new Intent(LoginActivity.this,MemoryActivity.class);
//                        PendingIntent pendingIntent = PendingIntent.getActivity(LoginActivity.this,0,intent,PendingIntent.FLAG_IMMUTABLE);
//                        notification.setContentIntent(pendingIntent);//设置通知栏点击跳转
//                        notificationManager.notify(NOTIFYID,notification.build());//发送通知

                            return;
                            //隐式跳转
                        }
                    }).show();
                }else if((spPsw!=null&&!TextUtils.isEmpty(spPsw)&&!md5Psw.equals(spPsw))){
                    Toast.makeText(LoginActivity.this, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(LoginActivity.this, "此用户名不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void init() {

    }

    private String createNotificationChannel(String channelId,String channelNAME,int level){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(channelId,channelNAME,level);
            channel.setDescription("通知级别"+level);
            manager.createNotificationChannel(channel);
            return channelId;
        }else {
            return null;
        }
    }


    //从SharedPreferences中根据用户名读取密码
    private String readPsw(String userName){
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sp.getString(userName , "");
    }
    //保存登录状态和登录用户名到SharedPreferences中
    private void saveLoginStatus(boolean status,String userName){
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isLogin", status);
        editor.putString("loginUserName", userName);
        editor.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            String userName=data.getStringExtra("userName");
            if(!TextUtils.isEmpty(userName)){
                et_user_name.setText(userName);
                et_user_name.setSelection(userName.length());
            }
        }
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