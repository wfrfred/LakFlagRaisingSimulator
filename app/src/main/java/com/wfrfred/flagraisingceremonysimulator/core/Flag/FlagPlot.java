package com.wfrfred.flagraisingceremonysimulator.core.Flag;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class FlagPlot extends View {
    private final boolean isInit = false;
    private Paint paint;
    private RectF mRect;

    public FlagPlot(Context context) {
        super(context);
    }

    public FlagPlot(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FlagPlot(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInit) initPaint(canvas);
        canvas.drawRect(mRect, paint);
    }

    private void initPaint(Canvas canvas) {
        float width = canvas.getWidth();
        float length = canvas.getHeight();
        paint = new Paint();
        paint.setAntiAlias(true);
        LinearGradient linearGradient = new LinearGradient(
                0, 0,
                width, 0,
                Color.rgb(114, 114, 114), Color.WHITE,
                Shader.TileMode.MIRROR
        );
        paint.setShader(linearGradient);
        mRect = new RectF(0, 0, width, length);
    }
}
