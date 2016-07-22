package org.mintcode.errabbit.logback.base;

/**
 * Settings Singleton
 */
public class Settings {

    private String host;
    private String sign;
    private Boolean activated;
    private String userName;
    private String password;
    private String rabbitID;

    private int failCount = 0;
    private int failThreshold = 5;

    private final String printHeader = "[ErRabbit] ";
    private static Settings single = new Settings();

    private Settings(){}
    public static Settings getInstance(){
        return single;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.failCount = 0;
        this.activated = activated;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrintHeader() {
        return printHeader;
    }

    public int getFailCount() {
        return failCount;
    }

    public int getFailThreshold() {
        return failThreshold;
    }

    public void setFailThreshold(int failThreshold) {
        this.failThreshold = failThreshold;
    }

    public String getRabbitID() {
        return rabbitID;
    }

    public void setRabbitID(String rabbitID) {
        this.rabbitID = rabbitID;
    }

    public void addFailCount(){
        this.failCount++;
        if (this.failCount >= this.failThreshold){

            Print.out("Stop sending some minutes. Because fail count " + failCount + ".");

            // Stop send messages
            this.activated = false;
            Thread t = new Thread(new FailTimer());
            t.start();
        }
    }
}
