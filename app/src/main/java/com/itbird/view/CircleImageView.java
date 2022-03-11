package com.itbird.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.itbird.R;

/**
 * 自定义圆形ImageView
 * Created by itbird on 2022/2/23
 */
public class CircleImageView extends androidx.appcompat.widget.AppCompatImageView {
    private final String TAG = CircleImageView.class.getSimpleName();


    /**
     * 边框相关属性和常量定义
     */
    private int mBoradWidth = DEFAULT_BORAD_WIDTH; //边框宽度
    private int mBoardColor = DEFAULT_BOARD_COLOR; //边框颜色
    private static final int DEFAULT_BOARD_COLOR = Color.WHITE; //默认边框颜色
    private static final int DEFAULT_BORAD_WIDTH = 2; //默认边框宽度
    /**
     * 资源图片和相关属性
     */
    private Bitmap mBitmap; //资源图片，转换为bitmap，获取其宽高，然后计算两个画笔的半径
    private int mBitmapWidth; //图片的宽度
    private int mBitmapHeight;//图片的高度
    /**
     * 圆形的半径
     */
    private float mContentRadius; //内容半径
    private float mBoardRadius; //边框半径

    /**
     * 绘制相关的系统资源和函数
     */
    private BitmapShader mBitmapShader;
    private Paint mContentPaint; //内容画笔
    private Paint mBoardPoint; //边框画笔

    public CircleImageView(Context context) {
        super(context);
        init();
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        mBoardColor = typedArray.getColor(R.styleable.CircleImageView_board_color, DEFAULT_BOARD_COLOR);
        mBoradWidth = typedArray.getDimensionPixelSize(R.styleable.CircleImageView_board_width, DEFAULT_BORAD_WIDTH);
        typedArray.recycle();
        init();
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        mBoardColor = typedArray.getColor(R.styleable.CircleImageView_board_color, DEFAULT_BOARD_COLOR);
        mBoradWidth = typedArray.getDimensionPixelSize(R.styleable.CircleImageView_board_width, DEFAULT_BORAD_WIDTH);
        typedArray.recycle();
        init();
    }

    private void init() {
        //设置边框画笔
        mBoardPoint = new Paint();
        mBoardPoint.setColor(mBoardColor); //设置边框画笔颜色
        mBoardPoint.setStyle(Paint.Style.STROKE); //边框画笔，设置空心
        mBoardPoint.setStrokeWidth(mBoradWidth); //设置边框画笔，宽度
        mBoardPoint.setAntiAlias(true); //消除锯齿

        mContentPaint = new Paint();
        mContentPaint.setAntiAlias(true);  //消除锯齿
    }

    private void drawBitmap() {
        //拿到位图
        mBitmap = getBitmapFromDrawable(getDrawable());
        if (mBitmap == null) {
            return;
        }

        mBitmapHeight = mBitmap.getHeight();
        mBitmapWidth = mBitmap.getWidth();
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP); //为当前bitmap建立渲染模式，x、y拉伸
        updateShaderMatrix();
        mContentPaint.setShader(mBitmapShader); //设置shape，用于进行bitmap渲染
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置半径
        mContentRadius = Math.min(getMeasuredWidth() / 2, getMeasuredHeight() / 2) - mBoradWidth;
        mBoardRadius = mContentRadius + mBoradWidth / 2;
    }

    /**
     * drawble转换为bitmap
     *
     * @param drawable
     * @return
     */
    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

    private void updateShaderMatrix() {
        float scale;
        float dx = 0;
        float dy = 0;
        Matrix matrix = new Matrix();

        if (mBitmapWidth * getMeasuredHeight() > getMeasuredWidth()
                * mBitmapHeight) {
            scale = getMeasuredHeight() / (float) mBitmapHeight;
            dx = (getMeasuredWidth() - mBitmapWidth * scale) * 0.5f;
        } else {
            scale = getMeasuredWidth() / (float) mBitmapWidth;
            dy = (getMeasuredHeight() - mBitmapHeight * scale) * 0.5f;
        }
        matrix.setScale(scale, scale);
        matrix.postTranslate((int) (dx + 0.5f) + mBoradWidth,
                (int) (dy + 0.5f) + mBoradWidth);
        mBitmapShader.setLocalMatrix(matrix);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //重写onDraw方法
        if (getDrawable() == null) {
            //如果资源为空，则直接走super的draw方法
            super.onDraw(canvas);
        } else {
            drawBitmap();
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mContentRadius, mContentPaint); //画bitmap
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mBoardRadius, mBoardPoint); //画边框
        }
    }
}
