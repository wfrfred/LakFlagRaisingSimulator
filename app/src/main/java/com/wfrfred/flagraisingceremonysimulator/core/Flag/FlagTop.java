package com.wfrfred.flagraisingceremonysimulator.core.Flag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wfrfred.flagraisingceremonysimulator.R;

public class FlagTop extends View {
    Bitmap pic;

    public FlagTop(Context context) {
        super(context);
    }

    public FlagTop(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        pic = BitmapFactory.decodeResource(getResources(), R.drawable.flag_top, null);
    }

    public FlagTop(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pic = circleView(pic);
        canvas.drawBitmap(Bitmap.createScaledBitmap(pic, getWidth(), getHeight(), true), 0, 0, null);
    }

    private Bitmap circleView(Bitmap bitmap) {
        //前面同上，绘制图像分别需要bitmap，canvas，paint对象
        Bitmap bm = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //这里需要先画出一个圆
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getHeight() / 2, paint);
        //圆画好之后将画笔重置一下
        paint.reset();
        //设置图像合成模式，该模式为只在源图像和目标图像相交的地方绘制源图像
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bm;
    }

}
