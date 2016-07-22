package org.mintcode.errabbit.log4j.base;

/**
 * Just print message to console
 * Created by soleaf on 2014. 10. 26..
 */
public class Print {

    public static void out(String message){
        System.out.println(Settings.getInstance().getPrintHeader() + message);
    }

}
