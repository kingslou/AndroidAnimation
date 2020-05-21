package com.example.androidanimation;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.androidanimation.adapter.CardAdapter;
import com.example.androidanimation.bean.CardInfo;
import com.example.androidanimation.databinding.ActivityCardBinding;
import com.example.androidanimation.widget.star.StarLayout;

import java.util.ArrayList;
import java.util.List;

public class CardActivity extends AppCompatActivity {

    private CardAdapter cardAdapter;
    private ActivityCardBinding binding;
    private List<CardInfo> cardInfoList = new ArrayList<>();
    int position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void mockData() {
        cardInfoList.clear();
        for (int i = 0; i < 4; i++) {
            CardInfo cardInfo = new CardInfo();
            cardInfo.setCardName("Name" + i);
            cardInfo.setCardNameBackGround("BackName" + i);
            if (i == 1 || i == 3) {
                cardInfo.setAnswerSign("A");
            } else {
                cardInfo.setAnswerSign(i + "Name");
            }
            cardInfoList.add(cardInfo);
        }
    }

    private void initView() {
        mockData();
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        cardAdapter = new CardAdapter(cardInfoList);

        binding.recycleCard.setLayoutManager(layoutManager);
        binding.recycleCard.setAdapter(cardAdapter);

        new Handler().postDelayed(() -> cardAdapter.coverCards(true), 2000);

        addStartView();

    }


    private void addStartView() {
        try {
            binding.llStart.removeAllViews();
            int startCount = 3;
            for (int i = 0; i < startCount; i++) {
                StarLayout starLayout = new StarLayout(this);
                starLayout.setVisibility(View.INVISIBLE);
                starLayout.setTag(i);

                LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                layoutParams.rightMargin = DisplayUtils.dip2px(this, 10);
                binding.llStart.addView(starLayout, layoutParams);
            }

            int index = binding.llStart.getChildCount();

            CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if (millisUntilFinished == 0) {
                        return;
                    }
                    if (position < index) {
                        StarLayout starLayout = (StarLayout) binding.llStart.getChildAt(position);
                        starLayout.setVisibility(View.VISIBLE);
                        starLayout.startRingAnim();
                        position++;
                    }
                }

                @Override
                public void onFinish() {

                }
            };
            countDownTimer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
