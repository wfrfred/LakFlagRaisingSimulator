package com.wfrfred.flagraisingceremonysimulator.core.mission;

import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author wfrfred
 * @date 2021/11/20
 */
public class RaisingMission extends Mission {
    private final View belowFlag;
    private final View slideView;
    private final TextView prompt;
    private MediaPlayer bgm;
    private Timer timer = new Timer();

    private double posX, posY, curPosX, curPosY;
    private boolean isRaising = false;
    private double currentHeight;
    private double score = 0;
    private final double HEIGHT;
    private final double WIDTH;
    private int times = 0;

    private double totalDelta = 0;

    public RaisingMission(MissionController missionController, Data data, View belowFlag, View slideView, TextView prompt, MediaPlayer bgm) {
        super(missionController, data);
        this.belowFlag = belowFlag;
        this.slideView = slideView;
        this.prompt = prompt;
        HEIGHT = this.data.getDouble("height", 1295.0);
        WIDTH = this.data.getDouble("width", 180.0);
        this.bgm = bgm;
    }

    @Override
    public void start() {
        startBgm();
        isRaising = true;
        slideView.setOnTouchListener(raisingListener);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                finishRaising();
                score = 100 - totalDelta / 2400;
                Data data = new Data();
                data.addString("0", "分数：" + score);
                missionController.nextMission(data);
            }
        }, 120000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                double totalHeight = HEIGHT - 2f * WIDTH / 3f;
                ++times;
                double delta = times * totalHeight / 12000 - currentHeight;
                String str;
                if (delta > 0) {
                    str = "Too slow!";
                } else if (delta < 0) {
                    str = "Too fast";
                } else {
                    str = "Perfect!";
                }
                prompt.post(() -> {
                    prompt.setText(str);
                });
                totalDelta += Math.sqrt(Math.abs(delta));
            }
        }, new Date(), 10);
    }

    View.OnTouchListener raisingListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    posX = event.getX();
                    posY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!isRaising) break;
                    curPosX = event.getX();
                    curPosY = event.getY();
                    currentHeight -= (curPosY - posY) / 100;
                    if (currentHeight < 0) currentHeight = 0;
                    if (currentHeight > HEIGHT - 2f * WIDTH / 3f) {
                        currentHeight = HEIGHT - 2f * WIDTH / 3f;
                        isRaising = false;
                    }
                    setViewHeight(belowFlag, (int) currentHeight);
                    break;
            }
            return true;
        }
    };

    private void startBgm() {
        bgm.setLooping(false);
        bgm.seekTo(0);
        bgm.start();
    }

    private void finishRaising() {
        isRaising = false;
        bgm.release();
        bgm = null;
        timer.cancel();
        timer = null;
    }

    /**
     * 重设 view 的宽高
     */
    private void setViewHeight(View view, int nHeight) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp.height != nHeight) {
            lp.height = nHeight;
            view.setLayoutParams(lp);
        }
    }
}
