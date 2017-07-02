package _1036225283.com.keyValue.server.socket.util.protocol.write;

import _1036225283.com.keyValue.server.socket.core.CoreType;
import _1036225283.com.keyValue.server.socket.util.parse.UtilParam;
import com.nitian.util.log.LogManager;

import java.util.Map;

/**
 * HTTP-Write协议解析器
 * Created by 1036225283 on 2016/12/17.
 */
public class ProtocolXwsWriteHandler extends ProtocolWriteHandler {

    protected static LogManager log = LogManager.getInstance();

    @Override
    public byte[] handle(Map<String, String> map) {
        String result = map.get(CoreType.result.toString());
        StringBuffer sb = new StringBuffer();
        sb.append("XWS:XWS/1.1");
        sb.append("\r\n");
        sb.append("Url:" + map.get(CoreType.url.toString()));
        sb.append("\r\n");
        sb.append("Server:XWS-Coyote/1.1");
        sb.append("\r\n");
        sb.append("Accept-Charset: utf-8");
        sb.append("\r\n");
        sb.append("Length:").append(result.length());
        sb.append("\r\n");
        sb.append("Content:");
        sb.append(UtilParam.encode(result));
        map.put(CoreType.close.toString(), "false");
        return sb.toString().getBytes();
    }
}
