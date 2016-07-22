package org.mintcode.errabbit.model;

import org.apache.log4j.spi.LocationInfo;

import java.io.Serializable;

public class ErLocationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    public ErLocationInfo() {

    }

    /** 
     * Generate from org.apache.log4j.spi.LocationInfo
     * @param locationInfo
     * @return
     */
    public static ErLocationInfo fromLocationInfo(org.apache.log4j.spi.LocationInfo locationInfo) {

        ErLocationInfo erLocationInfo = new ErLocationInfo();

        erLocationInfo.setClassName(locationInfo.getClassName());
        erLocationInfo.setFileName(locationInfo.getFileName());
        erLocationInfo.setLineNumber(locationInfo.getLineNumber());
        erLocationInfo.setMethodName(locationInfo.getMethodName());

        return erLocationInfo;
    }

    /**
     * @param locationInfo
     * @return
     */
    public static ErLocationInfo fromLocationInfo(StackTraceElement locationInfo) {

        ErLocationInfo erLocationInfo = new ErLocationInfo();

        erLocationInfo.setClassName(locationInfo.getClassName());
        erLocationInfo.setFileName(locationInfo.getFileName());
        erLocationInfo.setLineNumber(String.valueOf(locationInfo.getLineNumber()));
        erLocationInfo.setMethodName(locationInfo.getMethodName());

        return erLocationInfo;
    }

    /**
     * Caller's line number.
     */
    String lineNumber;
    /**
     * Caller's file name.
     */
    String fileName;
    /**
     * Caller's fully qualified class name.
     */
    String className;
    /**
     * Caller's method name.
     */
    String methodName;

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return "ErLocationInfo{" +
                "lineNumber='" + lineNumber + '\'' +
                ", fileName='" + fileName + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
