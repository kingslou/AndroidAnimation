package com.example.androidanimation.adapter;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidanimation.R;
import com.example.androidanimation.bean.CardInfo;

import java.util.List;

/**
 * @author 86153
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<CardInfo> cardInfoList;
    private Context mContext;
    private AnimatorSet mRightOutSet; // 右出动画
    private AnimatorSet mLeftInSet; // 左入动画

    public CardAdapter(List<CardInfo> cardInfos) {
        this.cardInfoList = cardInfos;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardInfo cardInfo = cardInfoList.get(position);
        holder.textCardFront.setText(cardInfo.getCardName());
        holder.textCardBackGround.setText(cardInfo.getCardNameBackGround());
        holder.itemView.setOnClickListener(v->{
            setCameraDistance(holder.cardFront,holder.cardBackGround);
            setAnimators();
            flipCard(cardInfo,holder.cardFront,holder.cardBackGround);
        });

        if(cardInfo.isNeedCover()){
            setCameraDistance(holder.cardFront,holder.cardBackGround);
            setAnimators();
            mRightOutSet.setTarget(holder.cardFront);
            mLeftInSet.setTarget(holder.cardBackGround);
            mRightOutSet.start();
            mLeftInSet.start();
            cardInfo.setFront(true);
        }
    }

    @Override
    public int getItemCount() {
        return cardInfoList.size();
    }

    // 翻转卡片
    public void flipCard(CardInfo cardInfo,View front,View backGround) {
        // 正面朝上
        if (!cardInfo.isFront()) {
            mRightOutSet.setTarget(front);
            mLeftInSet.setTarget(backGround);
            mRightOutSet.start();
            mLeftInSet.start();
            cardInfo.setFront(true);
        } else { // 背面朝上
            mRightOutSet.setTarget(backGround);
            mLeftInSet.setTarget(front);
            mRightOutSet.start();
            mLeftInSet.start();
            cardInfo.setFront(false);
        }
    }

    // 改变视角距离, 贴近屏幕
    private void setCameraDistance(View front,View backGround) {
        int distance = 16000;
        float scale = mContext.getResources().getDisplayMetrics().density * distance;
        front.setCameraDistance(scale);
        backGround.setCameraDistance(scale);
    }


    // 设置动画
    private void setAnimators() {
        mRightOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.anim_out);
        mLeftInSet = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.anim_in);

        // 设置点击事件
        mRightOutSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mLeftInSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
    }

    static class CardViewHolder extends RecyclerView.ViewHolder{
        private TextView textCardFront,textCardBackGround;
        private CardView cardFront,cardBackGround;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            textCardFront = itemView.findViewById(R.id.textWordsFront);
            textCardBackGround = itemView.findViewById(R.id.textWordsBackGround);

            cardFront = itemView.findViewById(R.id.cardFront);
            cardBackGround = itemView.findViewById(R.id.cardBackGround);
        }
    }
}
