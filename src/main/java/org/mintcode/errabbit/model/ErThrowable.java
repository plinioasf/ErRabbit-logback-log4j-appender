package org.mintcode.errabbit.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * No Use
 */
public class ErThrowable implements Serializable{

    private static final long serialVersionUID = 1L;

    private ErStackTraceElement[] stackTraceElements;
    private String detailMessage;

    public ErThrowable(){

    }

    public static ErThrowable formThrowable(Throwable tw){

        if (tw == null){
            return null;
        }

        ErThrowable ert = new ErThrowable();
        ert.setDetailMessage(tw.getMessage());
        StackTraceElement[] stackTraceElements = tw.getStackTrace();
        ErStackTraceElement[] erStackTraceElements = new ErStackTraceElement[tw.getStackTrace().length];
        for (int i = 0 ; i < stackTraceElements.length; i++){
            erStackTraceElements[i] = ErStackTraceElement.fromStackTraceElement(stackTraceElements[i]);
        }
        ert.setStackTraceElements(erStackTraceElements);

        return ert;
    }

    public ErStackTraceElement[] getStackTraceElements() {
        return stackTraceElements;
    }

    public void setStackTraceElements(ErStackTraceElement[] stackTraceElements) {
        this.stackTraceElements = stackTraceElements;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    @Override
    public String toString() {
        return "ErThrowable{" +
                "stackTraceElements=" + Arrays.toString(stackTraceElements) +
                ", detailMessage='" + detailMessage + '\'' +
                '}';
    }
}
