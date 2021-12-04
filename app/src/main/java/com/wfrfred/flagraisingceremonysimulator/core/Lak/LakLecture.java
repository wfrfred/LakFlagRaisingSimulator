package com.wfrfred.flagraisingceremonysimulator.core.Lak;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wfrfred.flagraisingceremonysimulator.R;

public class LakLecture extends View {
    Bitmap lak;

    public LakLecture(Context context) {
        super(context);
    }

    public LakLecture(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        lak = BitmapFactory.decodeResource(getResources(), R.drawable.lak);
    }

    public LakLecture(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(Bitmap.createScaledBitmap(lak, getWidth(), getHeight(), true), 0, 0, null);
    }

}
