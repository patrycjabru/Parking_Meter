package xmsg;

import service.MessageStorage;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven( activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jboss/exported/jms/queue/SOA_test"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessageConsumer implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(MessageConsumer.class.toString());

    @EJB(lookup = "java:global/ManagementApp-1.0/MessageStorageBean!service.local.MessageStorageLocal")
    MessageStorage messageStorage;

    public MessageConsumer() {
    }

    @Override
    public void onMessage(Message rcvMessage) {
        TextMessage msg = null;
        System.out.println("OnMessage");
        try {
            if (rcvMessage instanceof TextMessage) {
                msg = (TextMessage) rcvMessage;
                LOGGER.info("Received Message from queue: " + msg.getText());
                System.out.println("New message: "+ msg.getText());
                messageStorage.addMessage(msg.getText());
            } else {
                LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}