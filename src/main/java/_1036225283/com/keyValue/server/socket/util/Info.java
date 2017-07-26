package _1036225283.com.keyValue.server.socket.util;

/**
 * kai
 * Created by xws on 7/26/17.
 */
public class Info {

    private Thread thread;
    private SocketThread socketThread;
    private boolean state = true;

    public Info(Thread thread, SocketThread socketThread) {
        this.thread = thread;
        this.socketThread = socketThread;
    }

}
