package _1036225283.com.keyValue.server.socket.util.protocol;

import _1036225283.com.keyValue.server.socket.core.CoreProtocol;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.nio.ByteBuffer;

/**
 * 进行协议分发，不同的协议，调用不同的协议处理器
 * Created by 1036225283 on 2016/12/17.
 */
public class ProtocolDispatcher {


    protected static LogManager log = LogManager.getInstance();


    public static String dispatcher(ByteBuffer buffer, byte[] bs) {
        try {
            buffer.flip();
            int length = buffer.remaining();
            buffer.get(bs, 0, length);
//            int index = bs[0];

            String request = new String(bs, 0, length);
            log.info(LogType.debug, "----HTTP分发数据 = " + request);

            if (request.startsWith("XWS")) {
                return CoreProtocol.XWS.toString();
            } else if (request.contains("Upgrade: websocket")) {
                return CoreProtocol.WEBSOCKETUPGRADE.toString();
            } else if (request.startsWith("GET") || request.startsWith("POST") || request.startsWith("DELETE") || request.startsWith("UPDATE")) {
                return CoreProtocol.HTTP.toString();
            } else {
                return CoreProtocol.HTTPS.toString();
            }
        } catch (Exception e) {
            log.error(e, "鉴定协议出问题了!哈哈哈");
        }
        return null;
    }

}
