package com.wfrfred.flagraisingceremonysimulator.core.mission;

import java.util.HashMap;

/**
 * @author wfrfred
 * @date 2021/11/20
 */
public class Data {
    private final HashMap<String, Integer> ints = new HashMap<>();
    private final HashMap<String, Double> doubles = new HashMap<>();
    private final HashMap<String, Boolean> booleans = new HashMap<>();
    private final HashMap<String, String> strings = new HashMap<>();

    public int getInt(String name, int defaultValue) {
        return ints.getOrDefault(name, defaultValue);
    }

    public double getDouble(String name, double defaultValue) {
        return doubles.getOrDefault(name, defaultValue);
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        return booleans.getOrDefault(name, defaultValue);
    }

    public String getString(String name, String defaultValue) {
        return strings.getOrDefault(name, defaultValue);
    }

    public void addInt(String name, int value) {
        ints.putIfAbsent(name, value);
    }

    public void addDouble(String name, double value) {
        doubles.putIfAbsent(name, value);
    }

    public void addBoolean(String name, boolean value) {
        booleans.putIfAbsent(name, value);
    }

    public void addString(String name, String value) {
        strings.putIfAbsent(name, value);
    }

    public HashMap<String, Integer> getInts() {
        return ints;
    }

    public HashMap<String, Double> getDoubles() {
        return doubles;
    }

    public HashMap<String, Boolean> getBooleans() {
        return booleans;
    }

    public HashMap<String, String> getStrings() {
        return strings;
    }

    public void add(Data data) {
        ints.putAll(data.getInts());
        doubles.putAll(data.getDoubles());
        booleans.putAll(data.getBooleans());
        strings.putAll(data.getStrings());
    }
}
