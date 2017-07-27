package _1036225283.com.keyValue.server.socket;

import _1036225283.com.keyValue.server.socket.util.Factory;
import _1036225283.com.keyValue.server.socket.util.Info;
import _1036225283.com.keyValue.server.socket.util.SocketThread;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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

    public EngineSocket(int port) {
        this.port = port;
    }

    public EngineSocket() {
    }

    public void init() {

        Thread.currentThread().setName("线程:主轮询线程");


    }

    public void start() throws IOException {
        init();
        if (port == null) {
            port = 8080;
        }
        Thread.currentThread().setName("线程：服务主线程");
        log.info(LogType.thread, this, Thread.currentThread().toString());
        serverSocket = new ServerSocket(port);
        serverSocket.setReceiveBufferSize(32 * 1024);
        log.info(LogType.debug, this, "server is start");
        while (true) {
            Socket socket = serverSocket.accept();
            socket.setTcpNoDelay(true);
            socket.setSoLinger(true, 3);
            socket.setReceiveBufferSize(32 * 1024);
            socket.setSendBufferSize(32 * 1024);
            log.dateInfo(this, "第一步：接收socket开始,ip:" + socket.getInetAddress().getHostAddress() + ",port:" + socket.getPort());
            if (nMaxSocket > Factory.list.size()) {
                SocketThread socketThread = new SocketThread(socket);
                Thread thread = new Thread(socketThread);
                Info info = new Info(thread, socketThread);
                Factory.list.add(info);
                thread.start();
            } else {
                socket.close();
                log.dateInfo(LogType.time, this, "nMaxSocket is max,socket is close");
            }

            log.dateInfo(LogType.time, this, "第一步：接收socket结束");
        }
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
