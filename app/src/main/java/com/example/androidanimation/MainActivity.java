package com.example.androidanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageAirShip, imageOne, imageTwo, imageThree, imageSelect;
    private int mShipWidth;
    private ViewGroup mViewGroup;
    private int imageWidth,imageHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageAirShip = findViewById(R.id.imageShip);
        imageOne = findViewById(R.id.imageOne);
        imageTwo = findViewById(R.id.imageTwo);
        imageThree = findViewById(R.id.imageThree);
        imageSelect = findViewById(R.id.imageSelect);
        mViewGroup = (ViewGroup) getWindow().getDecorView();

        imageWidth = DisplayUtils.dip2px(this,100);
        imageHeight = DisplayUtils.dip2px(this,100);

        imageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取商品坐标
                int[] goodsPoint = new int[2];
                imageOne.getLocationInWindow(goodsPoint);

                //坐标
                int[] shoppingCartPoint = new int[2];
                imageAirShip.getLocationInWindow(shoppingCartPoint);
                AnimationView animationView = new AnimationView(MainActivity.this);
                animationView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.one),imageWidth,imageHeight);
                animationView.setCircleStartPoint(goodsPoint[0], goodsPoint[1]);
                animationView.setCircleEndPoint(shoppingCartPoint[0] + mShipWidth / 2, shoppingCartPoint[1]);
                //添加View并执行动画
                mViewGroup.addView(animationView);
                animationView.startAnimation(new AnimationView.KissAnimationListener() {
                    @Override
                    public void animationEnd() {
                        imageSelect.setVisibility(View.VISIBLE);
                        imageSelect.setImageResource(R.mipmap.one);
                    }
                });
            }
        });

        imageTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] goodsPoint = new int[2];
                imageTwo.getLocationInWindow(goodsPoint);

                //坐标
                int[] shoppingCartPoint = new int[2];
                imageAirShip.getLocationInWindow(shoppingCartPoint);

                AnimationView animationView = new AnimationView(MainActivity.this);
                animationView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.two),imageWidth,imageHeight);
                animationView.setCircleStartPoint(goodsPoint[0], goodsPoint[1]);
                animationView.setCircleEndPoint(shoppingCartPoint[0] + mShipWidth / 2, shoppingCartPoint[1]);
                //添加View并执行动画
                mViewGroup.addView(animationView);
                animationView.startAnimation(new AnimationView.KissAnimationListener() {
                    @Override
                    public void animationEnd() {
                        imageSelect.setVisibility(View.VISIBLE);
                        imageSelect.setImageResource(R.mipmap.two);
                    }
                });
            }
        });

        imageThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] goodsPoint = new int[2];
                imageThree.getLocationInWindow(goodsPoint);

                //获取坐标
                int[] shoppingCartPoint = new int[2];
                imageAirShip.getLocationInWindow(shoppingCartPoint);

                AnimationView animationView = new AnimationView(MainActivity.this);
                animationView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.three),imageWidth,imageHeight);
                animationView.setCircleStartPoint(goodsPoint[0], goodsPoint[1]);
                animationView.setCircleEndPoint(shoppingCartPoint[0] + mShipWidth / 2, shoppingCartPoint[1]);
                //添加View并执行动画
                mViewGroup.addView(animationView);
                animationView.startAnimation(new AnimationView.KissAnimationListener() {
                    @Override
                    public void animationEnd() {
                        imageSelect.setVisibility(View.VISIBLE);
                        imageSelect.setImageResource(R.mipmap.three);
                    }
                });
            }
        });

        ViewTreeObserver viewTreeObserver = imageAirShip.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageAirShip.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mShipWidth = imageAirShip.getMeasuredWidth();
            }
        });
    }

}
