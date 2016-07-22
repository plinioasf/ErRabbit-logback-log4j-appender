package org.mintcode.errabbit.logback.base;

/**
 * Just print message to console
 */
public class Print {

    public static void out(String message){
        System.out.println(Settings.getInstance().getPrintHeader() + message);
    }

}
