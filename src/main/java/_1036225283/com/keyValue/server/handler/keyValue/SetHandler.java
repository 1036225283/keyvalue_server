package _1036225283.com.keyValue.server.handler.keyValue;

import _1036225283.com.keyValue.client.KvNode;
import _1036225283.com.keyValue.client.UtilKeyValue;
import _1036225283.com.keyValue.server.socket.core.Handler;
import com.nitian.util.keyvalue.KeyValue;

//set key value
public class SetHandler extends Handler {

    private static KeyValue keyValue = KeyValue.getInstance();

    @Override
    public byte[] handle(byte[] bs) {
        // TODO Auto-generated method stub
        KvNode node = UtilKeyValue.set(bs);

        keyValue.set_(node.getKey(), node.getValue());
        return new byte[]{6};
    }

}
