package _1036225283.com.keyValue.server.socket.util;

import _1036225283.com.keyValue.server.socket.util.factory.Factory;
import com.nitian.util.log.LogManager;

import java.net.Socket;

/**
 * socket thread , read and write
 * Created by xws on 7/7/17.
 */
public class SocketThread implements Runnable {

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
                int index = bs[0];
                byte[] writeBytes = Factory.handlerFactory.get(index).handle(bs, length);
                socket.getOutputStream().write(writeBytes);
            }
        } catch (Exception e) {
            log.error(e, "handler线程异常" + e.getMessage());
        }

    }
}
