package com.example.crucialfunctiontest.server;

/**
 * @Author xushupeng
 * @Date 2025-01-04 12:18
 */
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;

@Slf4j
public class DataWebSocketHandler extends TextWebSocketHandler {

    private Timer timer;
    private Random random = new Random();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        System.out.println("Connection established: " + session.getId());
        new Thread(() -> {
            while (session.isOpen()) {
                try {
                    int data = random.nextInt(100);
                    session.sendMessage(new TextMessage(String.valueOf(data)));
                    System.out.println("Sent data: " + data);
                    Thread.sleep(5000);
                } catch (InterruptedException | IOException e) {
                    System.err.println("Error sending message: " + e.getMessage());
                }
            }
        }).start();
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        if (timer != null) {
            timer.cancel();
        }
    }
}