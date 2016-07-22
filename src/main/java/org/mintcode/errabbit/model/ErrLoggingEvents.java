package org.mintcode.errabbit.model;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import java.io.Serializable;
import java.util.Date;

public class ErrLoggingEvents implements Serializable{

    private static final long serialVersionUID = 1L;

    public ErrLoggingEvents(){

    }

    /**
     * @param loggingEvent
     * @return
     */
    public static ErrLoggingEvents fromLoggingEvent(ILoggingEvent le){

        ErrLoggingEvents erLoggingEvent = new ErrLoggingEvents();
        LoggingEvent loggingEvent = (LoggingEvent) le;

        erLoggingEvent.setCategoryName(loggingEvent.getLoggerName());
        erLoggingEvent.setLevel(loggingEvent.getLevel().toString());
        erLoggingEvent.setMessage(loggingEvent.getMessage());
        erLoggingEvent.setRenderedMessage(loggingEvent.getMessage());
        erLoggingEvent.setThreadName(loggingEvent.getThreadName());
        erLoggingEvent.setLocationInfo(ErLocationInfo.fromLocationInfo(loggingEvent.getCallerData()[0]));
        erLoggingEvent.setTimeStamp(loggingEvent.getTimeStamp());
        erLoggingEvent.setTimeStampDate(new Date(loggingEvent.getTimeStamp()));

        if (loggingEvent.getThrowableProxy() != null){
            erLoggingEvent.setThrowableInfo(ErThrowableInformation.fromThrowableInformation(loggingEvent.getThrowableProxy()));
        }
        return erLoggingEvent;

    }

    public String categoryName;

    public String level;

    private Object message;

    private String renderedMessage;

    private String threadName;

    private ErLocationInfo locationInfo;

    private ErThrowableInformation throwableInfo;

    public long timeStamp;

    public Date timeStampDate;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getRenderedMessage() {
        return renderedMessage;
    }

    public void setRenderedMessage(String renderedMessage) {
        this.renderedMessage = renderedMessage;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public ErLocationInfo getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(ErLocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }

    public ErThrowableInformation getThrowableInfo() {
        return throwableInfo;
    }

    public void setThrowableInfo(ErThrowableInformation throwableInfo) {
        this.throwableInfo = throwableInfo;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Date getTimeStampDate() {
        return timeStampDate;
    }

    public void setTimeStampDate(Date timeStampDate) {
        this.timeStampDate = timeStampDate;
    }

    @Override
    public String toString() {
        return "ErrLoggingEvent{" +
                "categoryName='" + categoryName + '\'' +
                ", level='" + level + '\'' +
                ", message=" + message +
                ", renderedMessage='" + renderedMessage + '\'' +
                ", threadName='" + threadName + '\'' +
                ", locationInfo=" + locationInfo +
                ", throwableInfo=" + throwableInfo +
                ", timeStamp=" + timeStamp +
                ", timeStampDate=" + timeStampDate +
                '}';
    }
}
