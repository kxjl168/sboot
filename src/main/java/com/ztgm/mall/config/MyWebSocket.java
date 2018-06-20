package com.ztgm.mall.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/*@ServerEndpoint("/websocket/{userId}")
@Component*/
public class MyWebSocket {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 线程安全的Map 存储用户信息
     */
    private static ConcurrentHashMap<String, Session> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) throws IOException {
        //加入set中;
        webSocketMap.put(userId, session);
        //在线数加1
        incrOnlineCount();
        logger.info("有新连接加入!当前在线人数为：" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        Map<String, String> map = session.getPathParameters();
        //从set中删除
        webSocketMap.remove(map.get("userId"));
        decOnlineCount();
        logger.info("一个连接已关闭,当前在线计数：" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        logger.info("来自客户端的消息：", message);
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        Map<String, String> map = session.getPathParameters();
        //从set中删除
        webSocketMap.remove(map.get("userId"));
        logger.info("发生错误");
        error.printStackTrace();
    }

    /**
     * 发自定义消息
     */
    public static void sendMessage(String message, String userId) throws IOException {
        Session session = webSocketMap.get(userId);
        if (session != null && session.isOpen()) {
            session.getBasicRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return MyWebSocket.onlineCount;
    }

    private static synchronized void incrOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    private static synchronized void decOnlineCount() {
        MyWebSocket.onlineCount--;
    }


}
