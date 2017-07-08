package _1036225283.com.keyValue.server.socket;

import _1036225283.com.keyValue.server.socket.util.queue.UtilQueue;
import _1036225283.com.keyValue.server.socket.util.queue.UtilQueueSocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * nio
 * Created by 1036225283 on 2016/11/17.
 */
public class EngineSocketNIO extends EngineSocket<SelectionKey> {

    // 通道管理器
    private Selector selector;
    // 处理队列
    private UtilQueue queueHandler;

    int count = 0;

    public void start() throws IOException {
        init2();

        this.selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(getPort()));
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        // System.out.println("服务端启动成功！");
        // 轮询访问selector
        boolean isFlag = true;
        while (isFlag) {
            // System.out.println("服务一直在运行");
            selector.select();
            Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();
            SelectionKey key;
            while (ite.hasNext()) {
                key = ite.next();
                ite.remove();
                try {

                    if (!key.isValid()) {
                        System.out.printf("无效的key\t" + key);
                        continue;
                    }

                    if (key.isAcceptable()) {
                        // System.out.println("accept...");
                        // System.out.println(UtilByte.toBin((byte)
                        // key.interestOps()));

                        this.accept(key);
                    } else if (key.isConnectable()) {
                        // System.out.println("connect...");
                        // System.out.println(UtilByte.toBin((byte)
                        // key.interestOps()));
                        this.connect(key);
                    } else if (key.isReadable()) {
                        // System.out.println("read...");
                        // System.out.println(UtilByte.toBin((byte)
                        // key.interestOps()));
                        key.interestOps(key.interestOps() ^ SelectionKey.OP_READ);
//						getQueueRead().push(key);
                        queueHandler.push(key);
                    } else if (key.isWritable()) {
                        // this.write(key);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error(e, "");
                }

            }

        }

    }


    /**
     * 获取socketChannel的状态
     *
     * @param socketChannel
     */
    public void state(SocketChannel socketChannel) {
    }

    public void accept(SelectionKey key) throws IOException {
        count = count + 1;

        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

        SocketChannel socketChannel = serverSocketChannel.accept();

        if (socketChannel.isRegistered()) {
        } else {
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        }
    }

    public void connect(SelectionKey key) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
    }

    public EngineSocketNIO() {
        init();
    }

    public EngineSocketNIO(int port) {
        setPort(port);
        init();
    }

    @Override
    public synchronized void callback(Object object) {
        SelectionKey key = (SelectionKey) object;
        key.interestOps(SelectionKey.OP_READ);
        selector.wakeup();

    }

    public void init2() {
        this.queueHandler = new UtilQueueSocketChannel(this);
        new Thread(this.queueHandler, "线程：处理线程").start();

    }
}
