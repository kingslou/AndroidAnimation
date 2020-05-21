package com.example.androidanimation.adapter;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.androidanimation.R;
import com.example.androidanimation.bean.CardInfo;
import com.example.androidanimation.bean.CardViewModel;
import com.plattysoft.leonids.ParticleSystem;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author 86153
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<CardInfo> cardInfoList;
    private Context mContext;
    private AnimatorSet mRightOutSet; // 右出动画
    private AnimatorSet mLeftInSet; // 左入动画
    private boolean coverAllCards = false;
    private Map<Integer, CardViewModel> viewModelMap = new TreeMap<>();
    private boolean canClick = false;
    private CardListener cardListener;
    private CardViewModel cardViewModelOne = null;
    private CardViewModel cardViewModelTwo = null;
    private int hasSelectCount = 0;

    public CardAdapter(List<CardInfo> cardInfos) {
        this.cardInfoList = cardInfos;
    }

    public interface CardSelectListener {
        void cardSelectFinish();
    }

    public void coverCards(boolean cover) {
        this.coverAllCards = cover;
        notifyDataSetChanged();
        canClick = true;
    }

    public void setCardSelectListener(CardListener listener) {
        this.cardListener = listener;
    }

    public interface CardListener {

        void selectFail();

        void selectFinish();
    }


    public void checkClick() {
        //等待动画执行完毕
        new Handler().postDelayed(() -> {
            int resultCount = viewModelMap.entrySet().size();
            Toast.makeText(mContext, "当前数量" + resultCount, Toast.LENGTH_SHORT).show();
            if (resultCount == 2) {
                canClick = false;
                cardViewModelOne = null;
                cardViewModelTwo = null;
                int index = -1;
                for (Map.Entry<Integer, CardViewModel> entry : viewModelMap.entrySet()) {
                    index++;
                    if (index == 0) {
                        cardViewModelOne = entry.getValue();
                    }
                    if (index == 1) {
                        cardViewModelTwo = entry.getValue();
                    }
                }
                //检查结果
                if (cardViewModelOne.getCardInfo().getAnswerSign().equals(cardViewModelTwo.getCardInfo().getAnswerSign())) {
                    //todo 选择是对的，粒子爆炸效果
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ParticleSystem ps = new ParticleSystem((Activity) mContext, 100, R.mipmap.icon_star, 800);
                            ps.setScaleRange(0.7f, 1.3f);
                            ps.setSpeedRange(0.1f, 0.25f);
                            ps.setRotationSpeedRange(90, 180);
                            ps.setFadeOut(200, new AccelerateInterpolator());
                            ps.oneShot(cardViewModelOne.getView(), 70);

                            ParticleSystem ps2 = new ParticleSystem((Activity) mContext, 100, R.mipmap.icon_star, 800);
                            ps2.setScaleRange(0.7f, 1.3f);
                            ps2.setSpeedRange(0.1f, 0.25f);
                            ps.setRotationSpeedRange(90, 180);
                            ps2.setFadeOut(200, new AccelerateInterpolator());
                            ps2.oneShot(cardViewModelTwo.getView(), 70);
                        }
                    }, 500);
                    canClick = true;
                    viewModelMap.clear();
                    hasSelectCount = hasSelectCount + 2;
                    if(hasSelectCount==cardInfoList.size()){
                        cardListener.selectFinish();
                    }
                } else {
                    // 选择是错的 抖动效果，然后再翻转过来
                    View view1 = cardViewModelOne.getView();
                    View view2 = cardViewModelTwo.getView();
                    //延时抖动
                    YoYo.with(Techniques.Shake).duration(800).delay(1000).onEnd(animator -> {
                        //todo 还有翻转回来
                        View fr = view1.findViewById(R.id.cardFront);
                        View bg = view1.findViewById(R.id.cardBackGround);
                        setCameraDistance(fr, bg);
                        setAnimators();
                        turnCardBg(cardViewModelOne.getCardPosition(), fr, bg);
                    }).playOn(view1);
                    YoYo.with(Techniques.Shake).duration(800).delay(1000).onEnd(animator -> {
                        View fr = view2.findViewById(R.id.cardFront);
                        View bg = view2.findViewById(R.id.cardBackGround);
                        setCameraDistance(fr, bg);
                        setAnimators();
                        turnCardBg(cardViewModelTwo.getCardPosition(), fr, bg);
                    }).playOn(view2);
                    canClick = true;
                    viewModelMap.clear();
                }
            }
        }, 1000);

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
        holder.itemView.setOnClickListener(v -> {
            if (!canClick) {
                return;
            }
            int adapterPosition = holder.getAdapterPosition();
            setCameraDistance(holder.cardFront, holder.cardBackGround);
            setAnimators();
            flipCard(adapterPosition, holder.cardFront, holder.cardBackGround);
            CardViewModel cardViewModel = viewModelMap.get(adapterPosition);
            if (cardViewModel == null) {
                cardViewModel = new CardViewModel();
                cardViewModel.setCardInfo(cardInfo);
                cardViewModel.setView(holder.itemView);
                cardViewModel.setCardPosition(adapterPosition);
                viewModelMap.put(adapterPosition, cardViewModel);
            } else {
                viewModelMap.remove(adapterPosition);
            }
            checkClick();
        });

        if (cardInfo.isNeedCover() || coverAllCards) {
            setCameraDistance(holder.cardFront, holder.cardBackGround);
            setAnimators();
            mRightOutSet.setTarget(holder.cardFront);
            mLeftInSet.setTarget(holder.cardBackGround);
            mRightOutSet.start();
            mLeftInSet.start();
            cardInfo.setBg(true);
        }
    }

    @Override
    public int getItemCount() {
        return cardInfoList.size();
    }

    public void turnCardBg(int cardPosition, View front, View backGround) {
        mRightOutSet.setTarget(front);
        mLeftInSet.setTarget(backGround);
        mRightOutSet.start();
        mLeftInSet.start();
        cardInfoList.get(cardPosition).setBg(true);
    }

    public void turnCardFront(int cardPosition, View front, View backGround) {
        mRightOutSet.setTarget(backGround);
        mLeftInSet.setTarget(front);
        mRightOutSet.start();
        mLeftInSet.start();
        cardInfoList.get(cardPosition).setBg(false);
    }

    // 翻转卡片
    public void flipCard(int cardPosition, View front, View backGround) {
        // 正面朝上
        if (!cardInfoList.get(cardPosition).isBg()) {
            turnCardBg(cardPosition, front, backGround);
        } else { // 背面朝上
            //todo 不允许翻转回来
            turnCardFront(cardPosition, front, backGround);
        }
    }

    // 改变视角距离, 贴近屏幕
    private void setCameraDistance(View front, View backGround) {
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

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        mLeftInSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        private TextView textCardFront, textCardBackGround;
        private CardView cardFront, cardBackGround;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            textCardFront = itemView.findViewById(R.id.textWordsFront);
            textCardBackGround = itemView.findViewById(R.id.textWordsBackGround);

            cardFront = itemView.findViewById(R.id.cardFront);
            cardBackGround = itemView.findViewById(R.id.cardBackGround);
        }
    }
}
