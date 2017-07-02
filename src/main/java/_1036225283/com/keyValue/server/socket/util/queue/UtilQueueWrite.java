package _1036225283.com.keyValue.server.socket.util.queue;

import _1036225283.com.keyValue.server.socket.EngineSocket;
import _1036225283.com.keyValue.server.socket.core.CoreType;
import _1036225283.com.keyValue.server.socket.util.key.UtilSelectionKey;
import _1036225283.com.keyValue.server.socket.util.protocol.write.ProtocolWriteHandler;
import com.nitian.util.log.LogType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Map;

/**
 * 读消息队列：处理map数据
 *
 * @author 1036225283
 */
public class UtilQueueWrite extends UtilQueue<Map<String, String>> {


    private EngineSocket engineSocket;


    public UtilQueueWrite(EngineSocket engineSocket) {
        // TODO Auto-generated constructor stub
        this.engineSocket = engineSocket;
    }

    @Override
    public synchronized void handle(Map<String, String> map) {
        // TODO Auto-generated method stub
        log.dateInfo(LogType.time, this, "写消息开始处理");

        long applicationId = Long.valueOf(map.get(CoreType.applicationId
                .toString()));
        SelectionKey key = (SelectionKey) engineSocket.getCountStore().remove(
                applicationId);
        SocketChannel socketChannel = (SocketChannel) key.channel();

        if (!socketChannel.isConnected()) {
            UtilSelectionKey.cancel(key);
            return;
        }

        ByteBuffer byteBuffer = null;
        byte[] bs;

        String protocol = map.get(CoreType.protocol.toString());
        ProtocolWriteHandler protocolWriteHandler = engineSocket.getProtocolWriteFactory().get(protocol);
        if (protocolWriteHandler == null) {
            UtilSelectionKey.cancel(key);
            return;
        } else {
            bs = protocolWriteHandler.handle(map);
        }

        try {
            byteBuffer = engineSocket.getPoolBuffer().lend();
            byteBuffer.put(bs);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e, "");
        } finally {
            if (map.get(CoreType.close.toString()).equals("true")) {
                UtilSelectionKey.cancel(key);
            } else {
                this.engineSocket.callback(key);
            }
            engineSocket.getPoolMap().repay(map);
            engineSocket.getPoolBuffer().repay(byteBuffer);
        }
        log.info(LogType.thread, this, Thread.currentThread().toString());
        log.dateInfo(LogType.time, this, "写消息队列处理结束");

    }

}
