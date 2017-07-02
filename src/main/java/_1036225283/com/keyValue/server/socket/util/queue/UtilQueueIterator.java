package _1036225283.com.keyValue.server.socket.util.queue;

import _1036225283.com.keyValue.server.socket.EngineSocket;
import com.nitian.util.log.LogManager;

import java.util.Iterator;

/**
 * 解析HTTP/WEBSOCKET请求队列
 *
 * @author 1036225283
 */
public class UtilQueueIterator extends UtilQueue<Iterator> {


    private EngineSocket engineSocket;
    protected LogManager log = LogManager.getInstance();

    public UtilQueueIterator(EngineSocket engineSocket) {
        // TODO Auto-generated constructor stub
        this.engineSocket = engineSocket;
    }

    @Override
    public synchronized void handle(Iterator map) {
        // TODO Auto-generated method stub

    }

}
