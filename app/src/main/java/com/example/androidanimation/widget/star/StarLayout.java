package com.example.androidanimation.widget.star;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidanimation.R;


public class StarLayout extends FrameLayout {

    private ImageView idIvBigStar;            // 大星星

    private StarView idSmallStarListView;    // 所有的小星星

    public StarLayout(@NonNull Context context) {
        this(context, null);
    }

    public StarLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_star, this);

        idIvBigStar = findViewById(R.id.id_iv_big_star);
        idSmallStarListView = findViewById(R.id.id_starview);
    }

    public void startRingAnim() {
        idIvBigStar.post(new Runnable() {
            @Override
            public void run() {
                idIvBigStar.setAlpha(0f);
                startBigStarScaleAnim(1200, 0).start();
                idSmallStarListView.startAnimation(10, 1600, 300);
            }
        });
    }

    private Animator startBigStarScaleAnim(int duration, int startDelay) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f, 5f, 5f);

        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                idIvBigStar.setAlpha(1f);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                idIvBigStar.setScaleX(value);
                idIvBigStar.setScaleY(value);
            }
        });
        valueAnimator.setDuration(duration);
        valueAnimator.setStartDelay(startDelay);
        return valueAnimator;
    }

    private OnAnimationEnd onAnimationEnd;

    public void setOnAnimationEnd(OnAnimationEnd onAnimationEnd) {
        this.onAnimationEnd = onAnimationEnd;
    }

    public interface OnAnimationEnd {

        void onAnimationEnd();
    }

}