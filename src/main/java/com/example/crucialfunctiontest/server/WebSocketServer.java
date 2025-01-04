package com.example.crucialfunctiontest.server;

import com.example.crucialfunctiontest.model.entity.SocketDomain;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 在Spring中，@ServerEndpoint注解用于定义WebSocket端点，
 * 类似于Servlet的URL映射。对于问题“每次建立一个WebSocket链接是否会创建一个新的WebSocketServer对象”，
 * 答案是：是的，每次新的WebSocket连接会创建一个新的WebSocketServer对象。
 *
 *
 */
@Component
@Slf4j
@ServerEndpoint("/websocket/{sid}")
public class WebSocketServer {//每次建立一个websocket链接 就会创建一个WebSocketServer对象

    //在线客户端数量
    private static int  onlineCount = 0; //类共享变量
    //Map用来存储已连接的客户端信息
    private static ConcurrentHashMap<String, SocketDomain> websocketMap = new ConcurrentHashMap<>();

    //当前连接客户端的Session信息
    private Session session; //实例变量 互不干扰
    //当前客户端名称
    private String clientName="";

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid){

        if(!websocketMap.containsKey(sid)){
            WebSocketServer.onlineCount++;
        }
        this.session = session; //实例变量初始化
        this.clientName = sid;

        SocketDomain socketDomain = new SocketDomain();
        socketDomain.setSession(session);
        socketDomain.setUri(session.getRequestURI().toString());
        websocketMap.put(clientName, socketDomain); //clientName 唯一
        log.info("用户连接："+ clientName + "，人数:"+onlineCount);
        try {
            sendMessage("服务端发送的消息,恭喜你连接成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @OnClose
    public void onClose(){
        if(websocketMap.containsKey(clientName)){
            websocketMap.remove(clientName);
            onlineCount--;
            log.info("用户关闭："+ clientName + "，人数:"+onlineCount);
        }
    }

    @OnMessage
    public void onMessage(String message,Session session){
        if(!StringUtil.isNullOrEmpty(message)){
            log.info("收到用户消息:"+clientName+",报文:"+message);
        }
    }

    //给当前客户端发消息
    private void sendMessage(String obj) {
        synchronized (session) {
            //嵌套代码块 锁住session 锁住 session 对象。
            //使用 session.getAsyncRemote().sendText(obj) 方法异步发送消息。
            this.session.getAsyncRemote().sendText(obj);
        }
    }

    //给指定客户端发送消息，通过clientName找到Session发送
    public void sendMessageTo(String clientName,String obj){
        SocketDomain socketDomain = websocketMap.get(clientName);
        try {
            if(socketDomain !=null){
                socketDomain.getSession().getAsyncRemote().sendText(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    //给除了当前客户端的其他客户端发消息
    private void sendMessageToAllExpectSelf(String message, Session currentSession) {
        for(Map.Entry<String, SocketDomain> client : websocketMap.entrySet()){
            Session tempSeesion = client.getValue().getSession();
            if( !tempSeesion.getId().equals(currentSession.getId())&&tempSeesion.isOpen()){
                tempSeesion.getAsyncRemote().sendText(message);
                log.info("服务端发送消息给"+client.getKey()+":"+message);
            }
        }
    }
    //给包括当前客户端的全部客户端发送消息
    public void sendMessageToAll(String message){
        for(Map.Entry<String, SocketDomain> client : websocketMap.entrySet()){
            Session seesion = client.getValue().getSession();
            if(seesion.isOpen()){
                seesion.getAsyncRemote().sendText(message);
                log.info("服务端发送消息给"+client.getKey()+":"+message);
            }
        }
    }
    //给外部调用的方法接口
    public void sendAll(String Message){
        sendMessageToAll(Message);
    }

}