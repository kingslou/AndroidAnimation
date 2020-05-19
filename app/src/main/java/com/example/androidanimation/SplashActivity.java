package com.example.androidanimation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidanimation.databinding.SpalshActivityBinding;

/**
 * @author 86153
 */
public class SplashActivity extends AppCompatActivity {

    SpalshActivityBinding binding;
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
}
