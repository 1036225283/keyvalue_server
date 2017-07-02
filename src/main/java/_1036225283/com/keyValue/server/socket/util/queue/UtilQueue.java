package _1036225283.com.keyValue.server.socket.util.queue;

import java.util.LinkedList;
import java.util.List;

import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

public abstract class UtilQueue<T> implements Runnable {

    private boolean flag = false;

    private List<T> list = new LinkedList<>();// 消息队列

    protected LogManager log = LogManager.getInstance();

    public synchronized void push(T t) {
        synchronized (list) {
            if (!list.contains(t)) {
                list.add(t);
            }
        }
        if (flag) {
        } else {
            notify();
        }
    }

    @Override
    public synchronized void run() {
        // TODO Auto-generated method stub
        while (true) {
            if (list.size() == 0) {
                try {
                    flag = false;
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    log.error(e, "");
                }
            } else {
                flag = true;
                T t;
                synchronized (list) {
                    t = list.remove(0);
                }
                try {
                    handle(t);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info(LogType.queue, this, "handle exception = " + e.getMessage());
                }

                log.info(LogType.queue, this, "queueSize+" + list.size());
            }
        }

    }

    public abstract void handle(T t);
}
