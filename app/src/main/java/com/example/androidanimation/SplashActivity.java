package com.example.androidanimation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidanimation.databinding.SpalshActivityBinding;

/**
 * @author 86153
 */
public class SplashActivity extends AppCompatActivity {

    SpalshActivityBinding binding;
    private int progress=0;
    private Message message;

    @SuppressLint("HandlerLeak")
    private Handler handler= new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int p=msg.what;
            binding.spv.setTotalAndCurrentCount(100,p);
        }

    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SpalshActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBezier.setOnClickListener(v -> {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
        });

        binding.btnDrag.setOnClickListener(v -> startActivity(new Intent(SplashActivity.this,DragActivity.class)));

        binding.btnCard.setOnClickListener(v->{
            startActivity(new Intent(SplashActivity.this,CardActivity.class));
        });

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            message=handler.obtainMessage();
            try {
                for (int i = 0; i <= 100; i++) {
                    int x=++progress;
                    message.what=x;
                    handler.sendEmptyMessage(message.what);
                    Thread.sleep(100);
                }

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        AsyncTask.execute(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler!=null){
            handler.removeCallbacksAndMessages(null);
        };
    }
}