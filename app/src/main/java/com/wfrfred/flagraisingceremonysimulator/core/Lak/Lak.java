package com.wfrfred.flagraisingceremonysimulator.core.Lak;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wfrfred.flagraisingceremonysimulator.R;

public class Lak extends View {
    private static final String TAG = "picture";
    private final int height;
    private final int weight;
    private final Bitmap head;
    private Bitmap body;

    public Lak(Context context) {
        super(context);
        head = BitmapFactory.decodeResource(getResources(), R.drawable.lak_head);
        body = BitmapFactory.decodeResource(getResources(), R.drawable.body00);
        height = body.getHeight();
        weight = body.getWidth();
    }

    public Lak(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        head = BitmapFactory.decodeResource(getResources(), R.drawable.lak_head);
        body = BitmapFactory.decodeResource(getResources(), R.drawable.body00);
        height = body.getHeight();
        weight = body.getWidth();
    }

    public Lak(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        head = BitmapFactory.decodeResource(getResources(), R.drawable.lak_head);
        body = BitmapFactory.decodeResource(getResources(), R.drawable.body00);
        height = body.getHeight();
        weight = body.getWidth();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = drawLak(head, body);
        if (getWidth() >= getHeight()) bitmap = adjustPhotoRotation(bitmap, 90);
        bitmap = Bitmap.createScaledBitmap(bitmap, getWidth(), getHeight(), true);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    private Bitmap drawLak(Bitmap head, Bitmap body) {
        int width = head.getWidth();
        int height = head.getHeight();
        Bitmap bm = Bitmap.createBitmap(2 * width, 3 * height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        Paint paint = new Paint();
        canvas.drawBitmap(head, width / 2, 0, paint);
        canvas.drawBitmap(body, 0, height, paint);
        return bm;
    }

    private Bitmap adjustPhotoRotation(Bitmap bm, final int orientationDegree) {
        Matrix m = new Matrix();
        m.setRotate(orientationDegree, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        try {
            return Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), m, true);
        } catch (OutOfMemoryError ignored) {

        }
        return null;
    }

    public void changeBody(Bitmap bitmap) {
        body = Bitmap.createScaledBitmap(bitmap, weight, height, true);
    }

}
