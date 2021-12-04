package com.wfrfred.flagraisingceremonysimulator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wfrfred.flagraisingceremonysimulator.activity.CoreActivity;

public class MainActivity extends AppCompatActivity {
    TextView lak;
    TextView title;
    int mode = 0;//彩蛋 默认为0 触发为1
    private Intent intent;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(MainActivity.this, CoreActivity.class);
        setContentView(R.layout.activity_start);
        bind();
        setChangeListener();
        LinearLayout start = findViewById(R.id.start);
        start.setOnTouchListener((view, motionEvent) -> {
            intent.putExtra("mode", mode);//传递给core是否触发彩蛋
            startActivity(intent);
            return true;
        });//点击任意处继续
    }

    private void bind() {
        lak = findViewById(R.id.lak);
        title = findViewById(R.id.title);
    }

    /**
     * 彩蛋：拖拽LAK和旗交换
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setChangeListener() {
        lak.setOnTouchListener(new View.OnTouchListener() {
            float currentX, currentY;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int[] target = new int[2];
                title.getLocationOnScreen(target);//获取“旗”的位置
                int[] position = new int[2];
                lak.getLocationOnScreen(position);//获取“LAK”的位置
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    currentX = motionEvent.getX() + position[0];
                    currentY = motionEvent.getY() + position[1];
                    //若拖拽LAK到旗附近则交换内容
                    if (Math.abs(target[0] - currentX) <= 50 & Math.abs(target[1] - currentY) <= 50) {
                        lak.setText(R.string.title_01);
                        title.setText(R.string.acknowledgement_01);
                        mode = 1;//传递给core
                    }
                }
                return true;
            }
        });

    }
}