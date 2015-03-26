package messaging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private static final Log LOG=LogFactory.getLog(ChatController.class);
    private final SimpMessagingTemplate stompTemplate;
    
    @Autowired
    public ChatController(SimpMessagingTemplate template) {
        super();
        this.stompTemplate = template;
    }

    @MessageMapping("/message")
    public void sendMessage(String message) {
        LOG.info("message received:"+message);
        this.stompTemplate.convertAndSend("/topic/messages", message);
    }
    
}
