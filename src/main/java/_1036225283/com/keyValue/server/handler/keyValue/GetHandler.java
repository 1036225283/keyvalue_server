package _1036225283.com.keyValue.server.handler.keyValue;

import _1036225283.com.keyValue.server.handler.UtilResult;
import _1036225283.com.keyValue.server.socket.core.CoreType;
import _1036225283.com.keyValue.server.socket.core.Handler;
import _1036225283.com.keyValue.server.socket.util.parse.UtilParam;
import com.nitian.util.keyvalue.KeyValue;
import com.nitian.util.keyvalue.Result;

import java.util.Map;

/**
 * get(key)
 */
public class GetHandler extends Handler {

    private static KeyValue keyValue = KeyValue.getInstance();


    @Override
    public void handle(Map<String, String> map) {
        // TODO Auto-generated method stub
        String param = map.get(CoreType.param.toString());
        Map<String, String> paramMap = UtilParam.getParam(param);
        String key = paramMap.get("key");
        if (key == null) {
            map.put(CoreType.result.toString(), UtilResult.keyIsNull("key is null"));
            return;
        }
        Result result = keyValue.get(key);

        long nanosecond = Long.valueOf(result.getTime());
        map.put(CoreType.result.toString(), UtilResult.success(key, result.getValue(), nanosecond));

    }

}
