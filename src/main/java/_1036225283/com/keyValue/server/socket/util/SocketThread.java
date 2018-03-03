package _1036225283.com.keyValue.server.socket.util;


import com._1036225283.util.self.log.LogManager;

import java.net.Socket;

/**
 * socket thread , read and write
 * Created by xws on 7/7/17.
 */
public class SocketThread implements Runnable {

    private boolean auth = false;//是否验证密码
    private Socket socket;
    byte[] bs = new byte[1024 * 64];

    protected LogManager log = LogManager.getInstance();


    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    //应答模式 读取信息后立马写信息
    public void run() {
        try {
            while (true) {
                int length = socket.getInputStream().read(bs);
                if (length == -1) {
                    socket.close();
                    return;
                }

                if (!auth) {
                    boolean result = UtilKeyValueServer.auth(bs, length);
                    if (result) {
                        auth = true;
                        byte[] writeBytes = UtilKeyValueServer.SUCCESS("auto success");
                        socket.getOutputStream().write(writeBytes);
                        continue;
                    } else {
                        byte[] writeBytes = UtilKeyValueServer.ERROR("connection server,first ,auth by password. place call client.auth(xxx)");
                        socket.getOutputStream().write(writeBytes);
                        socket.close();
                        return;
                    }
                }

                int index = bs[0];
                byte[] writeBytes = Factory.handlerFactory.get(index).handle(bs, length);
                socket.getOutputStream().write(writeBytes);
            }
        } catch (Exception e) {
            log.error(e, "handler线程异常" + e.getMessage());
        }

    }

    public Socket getSocket() {
        return socket;
    }
}
