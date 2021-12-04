package com.wfrfred.flagraisingceremonysimulator.core.mission;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Vector;

/**
 * @author wfrfred
 * @date 2021/11/20
 */
public class LectureMission extends Mission {

    private final TextView textView;
    private final LinearLayout lectureLayout;
    private final Vector<String> strings = new Vector<>();
    private int index = 0;

    /**
     * @param data     要求name为序数的string数组
     * @param textView 绑定的对话框
     */
    public LectureMission(MissionController missionController, Data data, TextView textView, LinearLayout lectureLayout) {
        super(missionController, data);
        this.textView = textView;
        this.lectureLayout = lectureLayout;
    }

    @Override
    public void start() {
        lectureLayout.post(() -> lectureLayout.setVisibility(View.VISIBLE));
        for (int i = 0; data.getStrings().containsKey(String.valueOf(i)); i++) {
            strings.add(data.getString(String.valueOf(i), ""));
        }
        textView.post(() -> {
            textView.setOnClickListener(listener);
            textView.setText(strings.get(index));
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (index < strings.size() - 1) {
                index++;
                textView.post(() -> textView.setText(strings.get(index)));
            } else {
                textView.post(() -> textView.setOnClickListener(null));
                lectureLayout.post(() -> lectureLayout.setVisibility(View.GONE));
                missionController.nextMission(null);
            }
        }
    };
}
