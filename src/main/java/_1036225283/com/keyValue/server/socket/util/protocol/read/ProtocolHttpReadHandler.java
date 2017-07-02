package _1036225283.com.keyValue.server.socket.util.protocol.read;

import _1036225283.com.keyValue.server.socket.core.CoreProtocol;
import _1036225283.com.keyValue.server.socket.core.CoreType;
import _1036225283.com.keyValue.server.socket.util.parse.UtilParseRead;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * HTTP协议解析器
 * Created by 1036225283 on 2016/12/17.
 */
public class ProtocolHttpReadHandler extends ProtocolReadHandler {

    protected static LogManager log = LogManager.getInstance();

    @Override
    public boolean handle(Map<String, String> map, ByteBuffer buffer, byte[] bs) {
        try {

            buffer.flip();
            int length = buffer.remaining();
            buffer.get(bs, 0, length);
            String request = new String(bs, 0, length);
            log.info(LogType.debug, "----HTTP读取数据 = " + request);
            String[] strings = request.split("\r\n");
            String ip = UtilParseRead.getIp(strings);
            String port = UtilParseRead.getPort(strings);
            String url = UtilParseRead.getUrl(strings);
            String param = UtilParseRead.getParam(strings);
            String method = UtilParseRead.getMethod(strings);
            map.put(CoreType.ip.toString(), ip);
            map.put(CoreType.port.toString(), port);
            map.put(CoreType.url.toString(), url);
            map.put(CoreType.param.toString(), param);
            map.put(CoreType.method.toString(), method);
            map.put(CoreType.protocol.toString(), CoreProtocol.HTTP.toString());
            map.put(CoreType.size.toString(), String.valueOf(request.length()));
            map.put(CoreType.close.toString(), CoreType.close.toString());
            return true;
        } catch (Exception e) {
            log.error(e, "解析HTTP协议出错了!!!");
            return false;
        }
    }
}
