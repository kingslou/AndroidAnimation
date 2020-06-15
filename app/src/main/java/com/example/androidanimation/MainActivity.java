package com.example.androidanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.umeng.analytics.MobclickAgent;

public class MainActivity extends AppCompatActivity {

    private ImageView imageAirShip, imageTwo, imageThree, imageSelect;
    private int mShipWidth;
    private ViewGroup mViewGroup;
    private int imageWidth,imageHeight;
    private RelativeLayout imageOneRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        MobclickAgent.onEvent(this,"100_1");
        imageAirShip = findViewById(R.id.imageShip);
        imageTwo = findViewById(R.id.imageTwo);
        imageThree = findViewById(R.id.imageThree);
        imageSelect = findViewById(R.id.imageSelect);
        imageOneRoot = findViewById(R.id.imageOneRoot);
        mViewGroup = (ViewGroup) getWindow().getDecorView();


        imageWidth = DisplayUtils.dip2px(this,100);
        imageHeight = DisplayUtils.dip2px(this,100);

        imageSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DragActivity.class));
            }
        });

        imageOneRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bitmap = AnimationView.viewConversionBitmap(imageOneRoot);
//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.one);
                //获取商品坐标
                int[] goodsPoint = new int[2];
                imageOneRoot.getLocationInWindow(goodsPoint);
                //坐标
                int[] shoppingCartPoint = new int[2];
                imageAirShip.getLocationInWindow(shoppingCartPoint);
                AnimationView animationView = new AnimationView(MainActivity.this);
                animationView.setImageBitmap(bitmap,imageWidth,imageHeight);

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
