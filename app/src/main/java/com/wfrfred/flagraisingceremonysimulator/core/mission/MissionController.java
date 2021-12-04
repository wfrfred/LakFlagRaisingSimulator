package com.wfrfred.flagraisingceremonysimulator.core.mission;

import java.util.LinkedList;
import java.util.Queue;

public class MissionController {
    private final Queue<Mission> missions;
    private boolean isWait = true;

    public MissionController() {
        missions = new LinkedList<>();
    }

    public void add(Mission mission) {
        missions.add(mission);
        if (isWait) {
            nextMission(null);
            isWait = false;
        }
    }

    public void nextMission(Data data) {
        Mission mission = missions.poll();
        if (mission != null) {
            if (data != null) mission.addData(data);
            mission.start();
        } else isWait = true;
    }
}
