package io.theoperator.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by andreas on 2/24/15.
 */
public class MyHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        TextMessage t = new TextMessage("Hallo");
        try {
            session.sendMessage(t);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
