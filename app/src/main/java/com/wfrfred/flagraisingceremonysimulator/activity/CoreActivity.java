package com.wfrfred.flagraisingceremonysimulator.activity;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wfrfred.flagraisingceremonysimulator.R;
import com.wfrfred.flagraisingceremonysimulator.core.Lak.Lak;
import com.wfrfred.flagraisingceremonysimulator.core.mission.Data;
import com.wfrfred.flagraisingceremonysimulator.core.mission.LectureMission;
import com.wfrfred.flagraisingceremonysimulator.core.mission.MissionController;
import com.wfrfred.flagraisingceremonysimulator.core.mission.RaisingMission;

public class CoreActivity extends AppCompatActivity {
    private static boolean mode = false;
    private static Bitmap lakBody = null;
    private final String[] strings = {
            "本日的升旗仪式由高二五班主持\n我是主持人LAK",
            "全体同学面向国际旗\n奏唱国际歌,行注目礼",
            "向上滑动以升旗，注意不要滑动过快哦"
    };
    private final Data preData = new Data();
    private final Data flag = new Data();
    private View flagCloth;
    private View flagPlot;
    private View belowFlag;
    private Lak lak;
    private LinearLayout lakContainer;
    private LinearLayout sky;
    private LinearLayout lectureLayout;
    private LinearLayout flagContainer;
    private MediaPlayer mediaPlayer;
    private TextView text;
    private TextView prompt;
    private MissionController controller;
    private LectureMission preRaising;
    private RaisingMission raisingMission;
    private LectureMission afterRaising;
    private boolean isInit = false;

    public static void setMode(boolean mode) {
        CoreActivity.mode = mode;
    }

    public static void setLakPicture(Bitmap lakBody) {
        CoreActivity.lakBody = lakBody;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer = MediaPlayer.create(this, R.raw.internationale);
        setContentView(R.layout.activity_core);
        bind();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!isInit) {
            if (lakBody != null) lak.changeBody(lakBody);
            lak.invalidate();
            initMission();
            controller.add(preRaising);
            controller.add(raisingMission);
            controller.add(afterRaising);
            isInit = true;
        }
    }

    private void bind() {
        flagCloth = findViewById(R.id.flag);
        belowFlag = findViewById(R.id.below_flag);
        text = findViewById(R.id.text);
        prompt = findViewById(R.id.prompt);
        lectureLayout = findViewById(R.id.lecture);
        flagContainer = findViewById(R.id.flag_container);
        lakContainer = findViewById(R.id.lak_head_container);
        lak = findViewById(R.id.lak_head);
        sky = findViewById(R.id.sky);
        flagPlot = findViewById(R.id.flagPlot);
    }

    private void egg() {
        if (mode) {
            ViewGroup.LayoutParams lp = flagCloth.getLayoutParams();
            flagContainer.removeView(flagCloth);
            Lak tmp = new Lak(this);
            tmp.changeBody(lakBody);
            flagCloth = tmp;
            flagContainer.addView(flagCloth, 0, lp);
            lakContainer.removeView(lak);
        }
    }

    private void initMission() {
        controller = new MissionController();
        for (int i = 0; i < strings.length; ++i) {
            preData.addString(String.valueOf(i), strings[i]);
        }
        flag.addDouble("height", flagPlot.getHeight());
        flag.addDouble("width", flagCloth.getWidth());
        egg();
        preRaising = new LectureMission(controller, preData, text, lectureLayout);
        raisingMission = new RaisingMission(controller, flag, belowFlag, sky, prompt, mediaPlayer);
        afterRaising = new LectureMission(controller, null, text, lectureLayout);
    }

}