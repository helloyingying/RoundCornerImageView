package com.android.liuzhuang.rcimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 圆角图片
 * Created by liuzhuang on 16/4/12.
 */
public class RoundCornerImageView extends ImageView {
    private float radius = 5;

    public RoundCornerImageView(Context context) {
        super(context);
        init(context, null);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init(context, attrs);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @SuppressWarnings("unused")
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerImageView);
        radius = typedArray.getDimensionPixelSize(R.styleable.RoundCornerImageView_rciv_radius, 5);
        typedArray.recycle();
    }

    @Override
    public void draw(Canvas canvas) {
        if (isInEditMode()) {
            super.draw(canvas);
            return;
        }

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        Bitmap bitmap = null;
        Drawable drawable = getDrawable();
        if (drawable != null) {
            bitmap = drawableToBitmap(drawable);
        }
        if (bitmap != null && bitmap.getWidth() > 0 && bitmap.getHeight() > 0) {
            BitmapShader shader;
            shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

            float scale = Math.max(getWidth() * 1.0f / bitmap.getWidth(), getHeight()
                    * 1.0f / bitmap.getHeight());
            Matrix matrix = new Matrix();
            matrix.setScale(scale, scale);
            shader.setLocalMatrix(matrix);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(shader);

            RectF rect = new RectF(0.0f, 0.0f, width, height);

            canvas.drawRoundRect(rect, radius, radius, paint);
        } else {
            super.draw(canvas);
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap;
        int width = Math.max(drawable.getIntrinsicWidth(), 2);
        int height = Math.max(drawable.getIntrinsicHeight(), 2);
        try {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
            bitmap = null;
        }

        return bitmap;
    }
}
