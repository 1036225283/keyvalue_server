package _1036225283.com.keyValue.server.handler.keyValue;

import _1036225283.com.keyValue.client.Operation;
import _1036225283.com.keyValue.client.UtilKeyValue;
import _1036225283.com.keyValue.server.socket.core.Handler;
import com._1036225283.util.self.keyvalue.KeyValue;

/**
 * get(key)
 */
public class GetHandler extends Handler {

    private static KeyValue keyValue = KeyValue.getInstance();


    @Override
    public byte[] handle(byte[] bs, int length) {
        // TODO Auto-generated method stub
        String key = UtilKeyValue.get(bs, length);
        if (key == null) {
            return new byte[]{5};
        }

        String value = keyValue.get_(key);

        return UtilKeyValue.get(Operation.SUCCESS, value);
    }

}
