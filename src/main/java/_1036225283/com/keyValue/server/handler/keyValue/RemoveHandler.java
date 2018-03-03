package _1036225283.com.keyValue.server.handler.keyValue;

import _1036225283.com.keyValue.client.UtilKeyValue;
import _1036225283.com.keyValue.server.socket.core.Handler;
import com._1036225283.util.self.keyvalue.KeyValue;

public class RemoveHandler extends Handler {

    private static KeyValue keyValue = KeyValue.getInstance();

    @Override
    public byte[] handle(byte[] bs, int length) {
        // TODO Auto-generated method stub
        String key = UtilKeyValue.remove(bs);
        keyValue.del_(key);
        return new byte[]{6};
    }

}
