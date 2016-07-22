package org.mintcode.errabbit.logback.send;

import ch.qos.logback.classic.spi.ILoggingEvent;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.mintcode.errabbit.logback.base.Print;
import org.mintcode.errabbit.model.ErrLoggingEvent;

import javax.jms.*;


/**
 * Send to active mq
 */
public class ActiveMQSender {

    private static ActiveMQSender single = new ActiveMQSender();

    //created ConnectionFactory object for creating connection
    String userName;
    String password;
    String uri;
    String queueName;
    String rabbitID;

    Connection connection = null;
    Session session = null;
    MessageProducer producer = null;

    private ActiveMQSender(){}

    public static ActiveMQSender getInstance(){
        return single;
    }

    /**
     * Connect to activeMQ
     * @param uri
     * @param userName
     * @param password
     * @param rabbitID
     * @return
     */
    public boolean connect(String uri, String userName, String password, String rabbitID){

        this.uri = uri;
        this.userName = userName;
        this.password = password;
        this.rabbitID = rabbitID;
        this.queueName = "errabbit.report." + rabbitID;

        if (getSession()){
            Print.out("Connection Success QueueName:" + queueName);
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * Get Session
     * @return
     */
    public boolean getSession() {

        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(userName, password, uri);
            // Create a Connection
            Connection connection = factory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue(queueName);

            // Create a MessageProducer from the Session to the Topic or Queue
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            return true;

        } catch (JMSException e) {
            Print.out("Connection Fail >> " + e.getMessage());
            return false;
        }
    }

    /**
     * Convert and send loggingEvent
     * @param loggingEvent
     */
    public void send(ILoggingEvent loggingEvent){
        try {
            ErrLoggingEvent errLoggingEvent = ErrLoggingEvent.fromLoggingEvent(loggingEvent);
            ObjectMessage message = session.createObjectMessage(errLoggingEvent);
            send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    /**
     * Convert and send message
     * @param message
     */
    protected void send(ObjectMessage message) {
        try {
            producer.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
            Print.out("Couldn't send a report");
        }
    }


    @Override
    protected void finalize() throws Throwable {
        Print.out("Finalize");
        close();
        super.finalize();
    }

    public void close() throws JMSException {
        // Clean up
        producer.close();
        session.close();
        connection.close();
    }

}

