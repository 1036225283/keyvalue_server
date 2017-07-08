package _1036225283.com.keyValue.server.socket.util.queue;

import _1036225283.com.keyValue.server.socket.EngineSocket;
import _1036225283.com.keyValue.server.socket.util.factory.Factory;
import _1036225283.com.keyValue.server.socket.util.key.UtilSelectionKey;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * nio 直接解析buffer => bs[] ,call listHandler,return byte[],write byte[] to socket channel
 *
 * @author 1036225283
 */
public class UtilQueueSocketChannel extends UtilQueue<SelectionKey> {


    private EngineSocket engineSocket;
    protected LogManager log = LogManager.getInstance();

    public UtilQueueSocketChannel(EngineSocket engineSocket) {
        // TODO Auto-generated constructor stub
        this.engineSocket = engineSocket;
    }

    @Override
    public synchronized void handle(SelectionKey selectionKey) {
        // TODO Auto-generated method stub
        log.dateInfo(LogType.time, this, "handler data start");

        try {

            ByteBuffer byteBuffer = read(selectionKey);
            if (byteBuffer == null) {
                return;
            }
            byte[] bs = engineSocket.getPoolByte().lend();

            byteBuffer.flip();
            int length = byteBuffer.remaining();
            byteBuffer.get(bs, 0, length);
            int index = bs[0];


            byte[] writeBytes = Factory.handlerFactory.get(index).handle(bs, length);

            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            //nio 异常时关闭SelectionKey和SocketChannel
            if (!socketChannel.isConnected()) {
                UtilSelectionKey.cancel(selectionKey);
                return;
            }

            byteBuffer.clear();
            byteBuffer.put(writeBytes);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);

            this.engineSocket.callback(selectionKey);
            engineSocket.getPoolBuffer().repay(byteBuffer);
            engineSocket.getPoolByte().repay(bs);

            log.info(LogType.thread, this, Thread.currentThread().toString());
            log.dateInfo(LogType.time, this, "消息队列处理结束");

        } catch (Exception e) {
            log.error(e, "handler线程异常");
        }


    }


    public synchronized ByteBuffer read(SelectionKey key) throws IOException {
        //首先，借取资源

        ByteBuffer buffer = engineSocket.getPoolBuffer().lend();

        SocketChannel socketChannel = (SocketChannel) key.channel();


        int size;

        try {
            size = socketChannel.read(buffer);
        } catch (IOException e) {
            UtilSelectionKey.cancel(key);
            log.error(e, "远程客户端关闭了");
            engineSocket.getPoolBuffer().repay(buffer);
            return null;
        }


        if (size > 0) {
            return buffer;
//            buffer.flip();
//            buffer.get(bs, 0, size);
        } else if (size == 0) {
            engineSocket.getPoolBuffer().repay(buffer);
            log.dateInfo(LogType.time, this, "读取的数据长度为0，需要释放key和其他资源");
            return null;
        } else if (size == -1) {
            UtilSelectionKey.cancel(key);
            engineSocket.getPoolBuffer().repay(buffer);
            log.dateInfo(LogType.time, this, "读取的数据长度为-1，需要释放key和其他资源");
            return null;
        }
        return null;
    }

}
