package _1036225283.com.keyValue.server.socket.util.parse;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 解析工具类
 *
 * @author 1036225283
 */
public class UtilParseRead {

    /**
     * 获取协议
     *
     * @param strings
     * @return
     */
    public static String getProtocol(String[] strings) {
        String protocol = find(strings, "Sec-WebSocket-Key");
        if (protocol == null) {
            return "HTTP";
        } else {
            return "WEBSOCKET";
        }
    }

    public static String getSecWebSocketKey(String[] strings) {
        String webSocketKey = find(strings, "Sec-WebSocket-Key").split(" ")[1];
        return webSocketKey;
    }

    /**
     * 获取ip
     *
     * @param strings
     * @return
     */
    public static String getIp(String[] strings) {
        String head = find(strings, "Host");
        String[] ips = head.split(":");
        return ips[1].trim();
    }

    /**
     * 获取端口
     *
     * @param strings
     * @return
     */
    public static String getPort(String[] strings) {
        String head = find(strings, "Host");
        String[] ips = head.split(":");
        return ips[2];
    }

    /**
     * 获取url
     *
     * @param strings
     * @return
     */
    public static String getUrl(String[] strings) {
        String url = strings[0].split(" ")[1].split("[?]")[0];
        return url;
    }

    /**
     * GET POST DELTE UPDATE
     *
     * @param strings
     * @return
     */
    public static String getMethod(String[] strings) {
        String method = strings[0].split(" ")[0];
        return method;
    }

    /**
     * 获取参数
     *
     * @param strings
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getParam(String[] strings)
            throws UnsupportedEncodingException {
        String param = "";
        String[] gets = strings[0].split(" ")[1].split("[?]");
        if (gets.length == 2) {
            param = gets[1];
        }
        String post = getPostParam(strings);
        if (!post.equals("")) {
            if (param.equals("")) {
                return post;
            } else {
                param = param + "&" + post;
            }
        }
        return param;
    }

    /**
     * 寻找值
     *
     * @param strings
     * @param key
     * @return
     */
    public static String find(String[] strings, String key) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].indexOf(key) == 0) {
                return strings[i];
            }
        }
        return null;
    }

    public static String findValue(String[] strings, String key) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].indexOf(key) == 0) {
                return strings[i].split(":")[1];
            }
        }
        return null;
    }

    /**
     * 获取post的参数
     *
     * @param strings
     * @return
     */
    private static String getPostParam(String[] strings) {
        String result = "";
        int length = strings.length;
        if (strings[length - 2].equals("")) {
            String value = strings[length - 1];
            result = value;
        }
        return result;
    }

    /**
     * 对参数进行解码
     *
     * @param param
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String decode(String param)
            throws UnsupportedEncodingException {
        String result = "";
        result = URLDecoder.decode(param, "UTF-8");
        return result;
    }
}
