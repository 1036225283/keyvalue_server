package _1036225283.com.keyValue.server.handler;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * 浏览器返回结果封装
 * Created by 1036225283 on 2017/1/8.
 */
public class UtilResult {

    /**
     * 当key = null时，返回错误消息
     *
     * @return string
     */
    public static String keyIsNull(String msg) {
        Map<String, String> map = new HashMap<>();
        map.put("state", "failure");
        map.put("msg", msg);
        map.put("key", "");
        map.put("value", "");
        map.put("second", "");
        map.put("millisecond", "");
        map.put("microsecond", "");
        map.put("nanosecond", "");
        String json = JSON.toJSONString(map);
        return json;
    }

    /**
     * 给浏览器返回成功
     *
     * @param key        key
     * @param value      value
     * @param nanosecond 纳秒
     * @return
     */
    public static String success(String key, String value, long nanosecond) {
        Map<String, String> map = new HashMap<>();
        map.put("state", "success");
        map.put("msg", "success");
        map.put("key", key);
        map.put("value", value);
        map.put("second", (nanosecond / 1000 / 1000 / 1000) + "");
        map.put("millisecond", (nanosecond / 1000 / 1000) + "");
        map.put("microsecond", (nanosecond / 1000) + "");
        map.put("nanosecond", nanosecond + "");
        String json = JSON.toJSONString(map);
        return json;
    }

}
