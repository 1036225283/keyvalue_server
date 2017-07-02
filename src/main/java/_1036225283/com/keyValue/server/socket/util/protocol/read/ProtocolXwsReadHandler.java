package _1036225283.com.keyValue.server.socket.util.protocol.read;

import _1036225283.com.keyValue.server.socket.core.CoreProtocol;
import _1036225283.com.keyValue.server.socket.core.CoreType;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * XWS协议解析器
 * Created by 1036225283 on 2016/12/17.
 */
public class ProtocolXwsReadHandler extends ProtocolReadHandler {

    protected static LogManager log = LogManager.getInstance();

    @Override
    public boolean handle(Map<String, String> map, ByteBuffer buffer, byte[] bs) {
        try {

            buffer.flip();
            int length = buffer.remaining();
            buffer.get(bs, 0, length);
            String request = new String(bs, 0, length);
            log.info(LogType.debug, "----XWS协议源数据 = " + request);
            String[] strings = request.split("\r\n");
            String ip = find(strings, "Host");
            String port = find(strings, "Port");
            String url = find(strings, "Url");
            String param = find(strings, "Content");
            map.put(CoreType.ip.toString(), ip);
            map.put(CoreType.port.toString(), port);
            map.put(CoreType.url.toString(), url);
            map.put(CoreType.param.toString(), param);
            map.put(CoreType.protocol.toString(), CoreProtocol.XWS.toString());
            map.put(CoreType.size.toString(), String.valueOf(request.length()));
            map.put(CoreType.close.toString(), "false");
            return true;
        } catch (Exception e) {
            log.error(e, "解析XWS协议出错了!!!");
            return false;
        }
    }

    public static String find(String[] strings, String key) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].indexOf(key) == 0) {
                return strings[i].split(":")[1];
            }
        }
        return null;
    }
}
