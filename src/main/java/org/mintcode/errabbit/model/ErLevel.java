package org.mintcode.errabbit.model;

import java.io.Serializable;

public class ErLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int level;
    private String levelStr;

    public ErLevel(){}

    /***
     * Generate from org.apache.log4j.Level
     * @param level
     * @return
     */
    public static ErLevel fromLevel(org.apache.log4j.Level level){

        if (level == null){
            return null;
        }

        ErLevel erLevel = new ErLevel();
        erLevel.setLevel(level.toInt());
        erLevel.setLevelStr(level.toString());
        return erLevel;
    }

    /***
     * Generate from ch.qos.logback.classic.Level
     * @param level
     * @return
     */
    public static ErLevel fromLevel(ch.qos.logback.classic.Level level){

        if (level == null){
            return null;
        }

        ErLevel erLevel = new ErLevel();
        erLevel.setLevel(level.toInt());
        erLevel.setLevelStr(level.toString());
        return erLevel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLevelStr() {
        return levelStr;
    }

    public void setLevelStr(String levelStr) {
        this.levelStr = levelStr;
    }

    @Override
    public String toString() {
        return levelStr;
    }
}
