package msg;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

@Stateless
public class MDBSender {

    @Inject
    private JMSContext context;

    @Resource(mappedName = "java:/jboss/exported/jms/queue/SOA_test")
    private Queue queue;

    public void sendMessage(String txt){
        try {
            System.out.println("Sender: wiadomość o treści: "+txt);
            TextMessage msg = context.createTextMessage(txt);
            context.createProducer().send(queue, msg);
            System.out.println("Wysłano");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
