package com.wfrfred.flagraisingceremonysimulator.core.mission;


/**
 * @author wfrfred
 * @date 2021/11/20
 */
public abstract class Mission {
    protected final Data data;
    protected final MissionController missionController;

    /**
     * 构造函数
     *
     * @param missionController 容器
     * @param data              若为null则默认一个空DATA
     */
    public Mission(MissionController missionController, Data data) {
        this.missionController = missionController;
        if (data == null) this.data = new Data();
        else this.data = data;
    }


    /**
     * 填充mission的内容
     */
    public abstract void start();


    /**
     * 后期传值
     *
     * @param data 将会与原data融合
     */
    public void addData(Data data) {
        this.data.add(data);
    }
}
