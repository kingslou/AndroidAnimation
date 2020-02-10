package com.example.androidanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * 商品动画自定义View
 * Create by: chenwei.li
 * Date: 2017/4/23
 * Time: 下午9:21
 * Email: lichenwei.me@foxmail.com
 */

public class AnimationView extends View {

    //小红点开始坐标
    Point mCircleStartPoint = new Point();
    //小红点结束坐标
    Point mCircleEndPoint = new Point();
    //小红点控制点坐标
    Point mCircleConPoint = new Point();
    //小红点的移动坐标
    Point mCircleMovePoint = new Point();

    //小红点画笔
    private Paint mCirclePaint;

    //图片
    private Bitmap mBitmap;

    public AnimationView(Context context) {
        super(context);
        init(context);
    }


    public AnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private Bitmap imageScale(Bitmap bitmap, int dst_w, int dst_h) {
        int src_w = bitmap.getWidth();
        int src_h = bitmap.getHeight();
        float scale_w = ((float) dst_w) / src_w;
        float scale_h = ((float) dst_h) / src_h;
        Matrix matrix = new Matrix();
        matrix.postScale(scale_w, scale_h);
        Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix,
                true);
        return dstbmp;
    }

    /**
     * 进行一些初始化操作
     */
    private void init(Context context) {
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.RED);
    }

    /**
     * 商品加入购物车的小红点
     */
    private void drawCircle(Canvas canvas) {
        if (mBitmap == null) {
            mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }
        canvas.drawBitmap(mBitmap, mCircleMovePoint.x, mCircleMovePoint.y, mCirclePaint);
    }

    public void setImageBitmap(Bitmap bitmap, int width, int height) {
        this.mBitmap = imageScale(bitmap, width, height);
    }

    /**
     * 设置开始点和移动点
     *
     * @param x
     * @param y
     */
    public void setCircleStartPoint(int x, int y) {
        this.mCircleStartPoint.x = x;
        this.mCircleStartPoint.y = y;
        this.mCircleMovePoint.x = x;
        this.mCircleMovePoint.y = y;
    }

    /**
     * 设置结束点
     *
     * @param x
     * @param y
     */
    public void setCircleEndPoint(int x, int y) {
        this.mCircleEndPoint.x = x;
        this.mCircleEndPoint.y = y;
    }


    /**
     * 开始动画
     */
    public void startAnimation(final KissAnimationListener listener) {
        if (mCircleStartPoint == null || mCircleEndPoint == null) {
            return;
        }


        //设置控制点
        mCircleConPoint.x = ((mCircleStartPoint.x + mCircleEndPoint.x) / 2);
        mCircleConPoint.y = (20);

        //设置值动画
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new CirclePointEvaluator(), mCircleStartPoint, mCircleEndPoint);
        valueAnimator.setDuration(600);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point goodsViewPoint = (Point) animation.getAnimatedValue();
                mCircleMovePoint.x = goodsViewPoint.x;
                mCircleMovePoint.y = goodsViewPoint.y;
                invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ViewGroup viewGroup = (ViewGroup) getParent();
                viewGroup.removeView(AnimationView.this);
                listener.animationEnd();
            }
        });
        valueAnimator.start();

    }

    public interface KissAnimationListener {

        void animationEnd();
    }


    /**
     * 自定义Evaluator
     */
    public class CirclePointEvaluator implements TypeEvaluator {

        /**
         * @param t          当前动画进度
         * @param startValue 开始值
         * @param endValue   结束值
         * @return
         */
        @Override
        public Object evaluate(float t, Object startValue, Object endValue) {

            Point startPoint = (Point) startValue;
            Point endPoint = (Point) endValue;

            int x = (int) (Math.pow((1 - t), 2) * startPoint.x + 2 * (1 - t) * t * mCircleConPoint.x + Math.pow(t, 2) * endPoint.x);
            int y = (int) (Math.pow((1 - t), 2) * startPoint.y + 2 * (1 - t) * t * mCircleConPoint.y + Math.pow(t, 2) * endPoint.y);

            return new Point(x, y);
        }

    }

    public static Bitmap viewConversionBitmap(View v) {
        int w = v.getWidth();
        int h = v.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        v.draw(c);
        return bmp;
    }

}
