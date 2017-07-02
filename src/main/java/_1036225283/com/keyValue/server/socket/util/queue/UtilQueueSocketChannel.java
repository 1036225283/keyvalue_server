package _1036225283.com.keyValue.server.socket.util.queue;

import _1036225283.com.keyValue.server.socket.EngineSocket;
import _1036225283.com.keyValue.server.socket.core.CoreType;
import _1036225283.com.keyValue.server.socket.util.key.UtilSelectionKey;
import _1036225283.com.keyValue.server.socket.util.protocol.ProtocolDispatcher;
import _1036225283.com.keyValue.server.socket.util.protocol.read.ProtocolReadHandler;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Map;

/**
 * 解析HTTP/WEBSOCKET请求队列
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
        log.dateInfo(LogType.time, this, "第二步：开始解析SocketChannel中的http或者websocket数据");

        try {

            ByteBuffer buffer = read(selectionKey);
            if (buffer == null) {
                return;
            }
            byte[] bs = engineSocket.getPoolByte().lend();

            //进行socket获取socketChannel的判断，如果存在协议，就调用协议处理器
            //如果不存在，就另行处理
            String protocol;
//            System.out.println("协议存储 = " + engineSocket.getSocketMap());
            if (engineSocket.getSocketMap().containsKey(selectionKey)) {
                protocol = engineSocket.getSocketMap().get(selectionKey).toString();
            } else {
                protocol = ProtocolDispatcher.dispatcher(buffer, bs);
            }

            ProtocolReadHandler protocolReadHandler = engineSocket.getProtocolReadFactory().get(protocol);
            if (protocolReadHandler == null) {
                engineSocket.getPoolByte().repay(bs);
                engineSocket.getPoolBuffer().repay(buffer);
                UtilSelectionKey.cancel(selectionKey);
                return;
            }

            Map<String, String> map = engineSocket.getPoolMap().lend();
            if (!protocolReadHandler.handle(map, buffer, bs)) {
                engineSocket.getPoolByte().repay(bs);
                engineSocket.getPoolBuffer().repay(buffer);
                selectionKey.channel().close();
                return;
            }
            if (!map.containsKey(CoreType.close.toString())) {
                engineSocket.getSocketMap().put(selectionKey, protocol.replace("UPGRADE", ""));
                log.dateInfo(LogType.time, this, "<SocketChannel,String>的数量 = " + engineSocket.getSocketMap().size());
            }
            engineSocket.getPoolByte().repay(bs);
            engineSocket.getPoolBuffer().repay(buffer);
            log.dateInfo(LogType.time, this, "解析协议结束");
            if (map.containsKey(CoreType.stop.toString())) {
                log.dateInfo(LogType.time, this, "没有进入业务流程，直接返回了");

                return;
            }
            //存放异步标识
            long applicationId = engineSocket.getCountStore().put(selectionKey);
            map.put(CoreType.applicationId.toString(),
                    String.valueOf(applicationId));
            engineSocket.getEngineHandle().push(map);

        } catch (Exception e) {
            log.error(e, "read线程异常");
        }

        log.dateInfo(LogType.time, this, "push数据到handler线程了");

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

        //偿还资源

//        return new String(bs, 0, size, "UTF-8");

        return null;
    }

}
