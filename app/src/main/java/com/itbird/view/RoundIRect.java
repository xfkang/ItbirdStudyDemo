package com.itbird.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.itbird.R;

/**
 * Created by itbird on 2022/2/21
 */
public class RoundIRect extends View {
    Paint mPaint;
    int mBackColor;

    public RoundIRect(Context context) {
        super(context);
        mPaint = new Paint();
    }

    public RoundIRect(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 加载自定义属性集合RoundRectView
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundIRect);
        //解析RoundRectView属性集合的round_rect_color属性，如果没设置默认值为Color.RED
        mBackColor = typedArray.getColor(R.styleable.RoundIRect_round_rect_backgroud, Color.RED);
        //获取资源后要及时释放
        typedArray.recycle();
        mPaint = new Paint();
    }

    public RoundIRect(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        // 设置wrap_content的默认宽高值
        // 默认宽高的设定并无固定依据,根据需要灵活设置
        // 当测量模式是AT_MOST（即wrap_content）时设置默认值
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(400, 400);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(400, getMeasuredHeight());
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(getMeasuredWidth(), 400);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int left = getPaddingLeft();
        int right = getPaddingRight();
        int top = getPaddingTop();
        int bottom = getPaddingBottom();


        mPaint.setColor(getResources().getColor(R.color.light_blue_900));
        //View的宽高需要减去padding
        RectF rectF = new RectF(0 + left, 0 + top, getWidth() - left, getHeight() - top);
        canvas.drawRoundRect(rectF, 10, 10, mPaint);
        setBackgroundColor(mBackColor);
    }
}
