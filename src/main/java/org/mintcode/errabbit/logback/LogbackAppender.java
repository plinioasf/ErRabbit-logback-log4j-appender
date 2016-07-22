package org.mintcode.errabbit.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.mintcode.errabbit.logback.base.Print;
import org.mintcode.errabbit.logback.base.Settings;
import org.mintcode.errabbit.logback.base.Version;
import org.mintcode.errabbit.logback.send.ActiveMQSender;

import javax.jms.JMSException;

/**
 * LogbackAppender for Error Rabbit
 */
public class LogbackAppender extends AppenderBase<ILoggingEvent> {

    private Settings settings = Settings.getInstance();
    private ActiveMQSender sender = ActiveMQSender.getInstance();

    public LogbackAppender() {

    }

    /**
     * Checking Settings
     * - Print Information
     * - Set Activation (by verifying settings)
     */
    private void checkSettings(){

        if (settings.getHost() == null|| settings.getRabbitID() == null){
            settings.setActivated(false);
            return;
        }
        Print.out("Initiation!");
        Print.out("version " + Version.string);
        Print.out("host=" + settings.getHost());
        connect();
    }

    /**
     * Connect to active mq
     * @return
     */
    private boolean connect(){
        if (sender.connect(settings.getHost(), settings.getUserName(), settings.getPassword(), settings.getRabbitID())){
            settings.setActivated(true);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Append event
     * @param event
     */
    @Override
    protected void append(ILoggingEvent event) {

        if (settings.getActivated() == null || !settings.getActivated()) {
            return;
        }
        sender.send(event);

    }

    /**
     * Close
     */
    @Override
    public void stop() {
        try {
            sender.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public boolean requiresLayout() {
        return false;
    }

    /**
     * Settings
     */
    public void setHost(String host) {
        settings.setHost(host);
        checkSettings();
    }

    public void setUserName(String userName){
        settings.setUserName(userName);
        checkSettings();
    }

    public void setPassword(String password){
        settings.setPassword(password);
        checkSettings();
    }

    public void setRabbitID(String rabbitID){
        settings.setRabbitID(rabbitID);
        checkSettings();
    }

}
