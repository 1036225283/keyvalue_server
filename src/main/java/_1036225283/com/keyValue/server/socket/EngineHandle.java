package _1036225283.com.keyValue.server.socket;

import _1036225283.com.keyValue.server.socket.core.DefaultHandler;
import _1036225283.com.keyValue.server.socket.util.HandlerFactory;
import _1036225283.com.keyValue.server.socket.util.queue.UtilQueueHandle;

import java.util.Map;

/**
 * Created by 1036225283 on 2016/11/13.
 * 业务引擎
 */
public class EngineHandle {


    private HandlerFactory handlerFactory;
    // 业务消息队列
    private UtilQueueHandle queueHandle;


    private EngineSocket engineSocket;

    public EngineHandle() {
        init();
    }


    public void init() {

        handlerFactory = new HandlerFactory();
        handlerFactory.regist("default", new DefaultHandler());

        // 待处理消息队列
        queueHandle = new UtilQueueHandle(this);
        new Thread(queueHandle, "线程：业务队列线程").start();


    }

    public void push(Map<String, String> map) {
        queueHandle.push(map);
    }


    public HandlerFactory getHandlerFactory() {
        return handlerFactory;
    }


    public void setEngineSocket(EngineSocket engineSocket) {
        this.engineSocket = engineSocket;
    }

    public EngineSocket getEngineSocket() {
        return engineSocket;
    }
}
