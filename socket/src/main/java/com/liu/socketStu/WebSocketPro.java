package com.liu.socketStu;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Set;

/**
 * 注解式实现websocket
 */
@ServerEndpoint("/ws")
public class WebSocketPro {

    private  Session session;
    private static Set<WebSocketPro> websocketSet = new HashSet<>();

    @OnMessage
    public void onmessage(String message, Session session){
        System.out.println("收到消息："+message);


    }

    @OnOpen
    public void onopen(Session session){
        this.session = session;
        websocketSet.add(this);

        // 发送给其他socket 广播消息
        for (WebSocketPro webSocketPro : websocketSet) {
            if (webSocketPro!=this){
                webSocketPro.session.getAsyncRemote().sendText("***上线了");
            }
        }

    }

    @OnClose
    public void onclose(Session session){
        System.out.println("连接关闭。。。");

        // 发送给其他socket 广播消息
        for (WebSocketPro webSocketPro : websocketSet) {
            if (webSocketPro!=this){
                webSocketPro.session.getAsyncRemote().sendText("***下线了");
            }
        }
    }

    @OnError
    public void onerror(Session session,Throwable throwable){
        System.out.println("error。。。"+throwable.getMessage());
    }
}
