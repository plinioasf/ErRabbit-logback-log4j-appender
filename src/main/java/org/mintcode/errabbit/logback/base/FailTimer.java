package org.mintcode.errabbit.logback.base;

/**
 * Fail timer
 * To retry connect to active mq
 */
public class FailTimer implements Runnable{

    private int minutes = 0;
    private int maxMintues = 5;

    public void run() {

        try {
            while (minutes < maxMintues){
                Thread.sleep(1000);
                minutes++;
            }
            Settings.getInstance().setActivated(true);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
