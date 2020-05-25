package com.hwhhhh.fleamarket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/23 14:45
 */
@Component
@Slf4j
@ServerEndpoint("/websocket/{userId}")
public class WebSocketServer {
    // 静态变量，用来记录当前在线连接数
    private static int onlineCount = 0;
    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketServers = new CopyOnWriteArraySet<>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    // 接收userId
    private String userId;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        webSocketServers.add(this);
        addOnlineCount();
        log.info("有新窗口开始监听：" + userId + ",当前在线人数为" + getOnlineCount());
        this.userId = userId;
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    @OnClose
    public void onClose() {
        webSocketServers.remove(this);
        subOnlineCount();
        log.info("有人关闭！" + userId);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到用户" + userId + "的消息" + message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误！");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        log.info("服务器发送message" + message);
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendInfo(String message, @PathParam("userId") String userId) {
        log.info("send message to " + userId + " with what " + message);
        for (WebSocketServer item : webSocketServers) {
            try {
                if (userId == null) {
                    item.sendMessage(message);
                } else if (item.userId.equals(userId)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}