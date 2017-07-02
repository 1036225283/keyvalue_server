package _1036225283.com.keyValue.server.handler.keyValue;

import _1036225283.com.keyValue.client.UtilKeyValue;
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
    public byte[] handle(byte[] bs) {
        // TODO Auto-generated method stub
        String key = UtilKeyValue.get(bs);
        if (key == null) {
            return new byte[]{5};
        }

        String value = keyValue.get_(key);

        return UtilKeyValue.get(value);
    }

}
