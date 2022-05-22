package com.example.vocabulary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

public class MemoryActivity extends AppCompatActivity {
    private WebView webViewMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        webViewMemory = (WebView) findViewById(R.id.wv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.memory_men,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                showMessage("开始背诵模式");
                break;
             case  R.id.item2:
                 showMessage("开始记忆模式");
                 break;
            case R.id.item4:
                webViewMemory.loadUrl("file:///android_asset/AboutUs.html");
                break;
            case R.id.item5:
                Intent intent = new Intent(MemoryActivity.this, LearnMoreActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    public void showMessage(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}