package _1036225283.com.keyValue.server.socket.util.parse;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 从encode的字符串中提取参数
 * Created by 1036225283 on 2017/1/3.
 */
public class UtilParam {

    /**
     * 对浏览器传递过来的数据进行解码，分解，提取
     *
     * @param param 浏览器传递过来的参数
     * @return Map<String-String>
     */
    public static Map<String, String> getParam(String param) {
        Map<String, String> map = new HashMap<>();
        String[] lines = param.split("&");
        for (String line : lines) {
            String[] keyValue = line.split("=");
            if (keyValue.length == 1) {
                String key = decode(keyValue[0]);
                map.put(key, null);
            } else if (keyValue.length == 2) {
                String key = decode(keyValue[0]);
                String value = decode(keyValue[1]);
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * 对参数进行解码
     *
     * @param param String
     * @return String
     */
    public static String decode(String param) {
        try {
            return URLDecoder.decode(param, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 对参数进行编码
     *
     * @param param 参数
     * @return string
     */
    public static String encode(String param) {
        try {
            return URLEncoder.encode(param, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
