package org.mintcode.errabbit.model;

import ch.qos.logback.classic.Logger;
import org.apache.log4j.Category;

import java.io.Serializable;

/**
 * Model for ch.qos.logback.classic.Logger
 */
public class ErCategory implements Serializable{

    private static final long serialVersionUID = 1L;

    private String name;
    private ErLevel level;

    public ErCategory(){

    }



   /**
    * Generate from org.apache.log4j.Category
    * @param category
    * @return
    */
   public static ErCategory fromCategory(Category category){

        if (category == null){
            return null;
        }

        ErCategory erCategory = new ErCategory();
        erCategory.setName(category.getName());
        erCategory.setLevel(ErLevel.fromLevel(category.getLevel()));

        return erCategory;
    }

    /**
     * Generate from ch.qos.logback.classic.Logger
     * @param category
     * @return
     */
    public static ErCategory fromCategory(Logger category){

        if (category == null){
            return null;
        }

        ErCategory erCategory = new ErCategory();
        erCategory.setName(category.getName());
        erCategory.setLevel(ErLevel.fromLevel(category.getLevel()));

        return erCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ErLevel getLevel() {
        return level;
    }

    public void setLevel(ErLevel level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "ErCategory{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
