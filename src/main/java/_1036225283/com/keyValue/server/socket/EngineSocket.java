package _1036225283.com.keyValue.server.socket;

import _1036225283.com.keyValue.server.socket.util.SocketThread;
import _1036225283.com.keyValue.server.socket.util.factory.Factory;
import _1036225283.com.keyValue.server.socket.util.pool.UtilPoolBuffer;
import _1036225283.com.keyValue.server.socket.util.pool.UtilPoolByte;
import _1036225283.com.keyValue.server.socket.util.pool.UtilPoolMap;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1036225283 on 2016/11/13.
 * 消息引擎
 */
public class EngineSocket<T> {

    public LogManager log = LogManager.getInstance();


    private Integer port;
    private ServerSocket serverSocket;
    private int poolMax = 800;
    private int poolTotal = 200;

    private int nMaxSocket = 20;

    private List<SocketThread> list = new ArrayList<>();


    private UtilPoolBuffer poolBuffer;
    private UtilPoolByte poolByte;
    private UtilPoolMap poolMap;

    public EngineSocket(int port) {
        this.port = port;
    }

    public EngineSocket() {
    }

    public void init() {

        Thread.currentThread().setName("线程:主轮询线程");

        poolBuffer = Factory.getPoolBuffer(this.getClass().getName(), this);
        poolByte = new UtilPoolByte(poolMax, poolTotal, null);// socket读取缓冲区(lend:replay)
        poolMap = new UtilPoolMap(poolMax, poolTotal);// 解析数据缓冲区(lend:)

    }

    public void start() throws IOException {
        init();
        if (port == null) {
            port = 8080;
        }
        Thread.currentThread().setName("线程：服务主线程");
        log.info(LogType.thread, this, Thread.currentThread().toString());
        serverSocket = new ServerSocket(port);
        log.info(LogType.debug, this, "server is start");
        while (true) {
            Socket socket = serverSocket.accept();
            log.dateInfo(LogType.time, this, "第一步：接收socket开始");
            System.out.println("socketCount =  " + list.size());
            if (nMaxSocket > list.size()) {
                SocketThread socketThread = new SocketThread(socket);
                list.add(socketThread);
                new Thread(socketThread).start();
            } else {
                socket.close();
                log.dateInfo(LogType.time, this, "nMaxSocket is max,socket is close");
            }

            log.dateInfo(LogType.time, this, "第一步：接收socket结束");
        }
    }


    public UtilPoolByte getPoolByte() {
        return poolByte;
    }

    public UtilPoolBuffer getPoolBuffer() {
        return poolBuffer;
    }


    public UtilPoolMap getPoolMap() {
        return poolMap;
    }


    public void setPort(int port) {
        this.port = port;
    }


    public Integer getPort() {
        return port;
    }

    public int getPoolMax() {
        return poolMax;
    }

    public int getPoolTotal() {
        return poolTotal;
    }

    /**
     * 系统回调处理
     *
     * @param object
     */
    public synchronized void callback(Object object) {
    }
}
