package tw.lab.Spring08.handler;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyWebSocketHandler extends TextWebSocketHandler {

    private static final Set<WebSocketSession> sessions = new HashSet<>();
    // private static final Set<WebSocketSession> sessions2 = new
    // CopyOnWriteArraySet<>()

    private static boolean isExistTeacher = false;
    private static WebSocketSession teacherSession;

    public MyWebSocketHandler() {
        System.out.println("MyWebSocketHandler");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String mesg = message.getPayload();
        // 判斷是否 Teacher => mesg
        if (!isExistTeacher && mesg.contains("isTeacher")) {
            isExistTeacher = true;
            teacherSession = session;
            System.out.println("Teacher Exist");
        } else if (session == teacherSession) {
            System.out.println("Teacher Drawing");
            for (WebSocketSession s : sessions) {
                if (s.isOpen()) {
                    s.sendMessage(message);
                }
            }
        }

    }

}
