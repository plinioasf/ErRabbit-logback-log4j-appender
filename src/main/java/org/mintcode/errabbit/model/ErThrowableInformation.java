package org.mintcode.errabbit.model;

import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.classic.Logger;
import org.apache.log4j.Category;
import org.apache.log4j.spi.ThrowableInformation;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Model for ch.qos.logback.classic.spi.ThrowableProxy
 * Created by soleaf on 2/21/15.
 */
public class ErThrowableInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    private ErThrowable throwable;
    private ErCategory category;
    private String[] rep;

    public ErThrowableInformation() {
    }

    /**
     * Generate from org.apache.log4j.spi.TrowableInformation
     * @param tw
     * @return
     */
    public static ErThrowableInformation fromThrowableInformation(ThrowableInformation tw) {

        ErThrowableInformation ert = new ErThrowableInformation();
        ert.setThrowable(ErThrowable.formThrowable(tw.getThrowable()));
        ert.setRep(tw.getThrowableStrRep());

        try {
            Field field = tw.getClass().getDeclaredField("category");
            field.setAccessible(true);
            ert.setCategory(ErCategory.fromCategory((Category) field.get(tw)));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return ert;
    }

    /**
     * Generate from ch.qos.logback.classic.spi.ThrowableProxy
     * @param tw
     * @return
     */
    public static ErThrowableInformation fromThrowableInformation(IThrowableProxy proxy) {

        List<String> stacks = new ArrayList<String>();
        ThrowableProxy tw = (ThrowableProxy) proxy;
        ErThrowableInformation ert = new ErThrowableInformation();
        ert.setThrowable(ErThrowable.formThrowable(tw.getThrowable()));

        for (StackTraceElementProxy s : tw.getStackTraceElementProxyArray()) {
            stacks.add(s.getSTEAsString());
        }

        String[] simpleArray = new String[stacks.size()];
        ert.setRep(stacks.toArray(simpleArray));

        String className = tw.getClassName();
        Logger logger = (Logger) LoggerFactory.getLogger(className);
        ert.setCategory(ErCategory.fromCategory(logger));

        return ert;
    }

    public ErThrowable getThrowable() {
        return throwable;
    }

    public void setThrowable(ErThrowable throwable) {
        this.throwable = throwable;
    }

    public ErCategory getCategory() {
        return category;
    }

    public void setCategory(ErCategory category) {
        this.category = category;
    }

    public String[] getRep() {
        return rep;
    }

    public void setRep(String[] rep) {
        this.rep = rep;
    }

    @Override
    public String toString() {
        return "ErThrowableInformation{" +
                "throwable=" + throwable +
                ", category=" + category +
                ", rep=" + Arrays.toString(rep) +
                '}';
    }
}
