package com.example.androidanimation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.androidanimation.adapter.CardAdapter;
import com.example.androidanimation.bean.CardInfo;
import com.example.androidanimation.databinding.ActivityCardBinding;

import java.util.ArrayList;
import java.util.List;

public class CardActivity extends AppCompatActivity {

    private CardAdapter cardAdapter;
    private ActivityCardBinding binding;
    private List<CardInfo> cardInfoList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void mockData() {
        cardInfoList.clear();
        for (int i = 0; i < 8; i++) {
            CardInfo cardInfo = new CardInfo();
            cardInfo.setCardName("Name" + i);
            cardInfo.setCardNameBackGround("BackName" + i);

            if(i==3|i==0){
                cardInfo.setNeedCover(true);
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
    }
}
