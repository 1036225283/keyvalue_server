package _1036225283.com.keyValue.server.handler.keyValue;

import _1036225283.com.keyValue.server.socket.core.CoreType;
import _1036225283.com.keyValue.server.socket.core.Handler;
import com.nitian.util.keyvalue.KeyValue;

import java.util.Map;

public class InitHandler extends Handler {

	private static KeyValue keyValue = KeyValue.getInstance();

	@Override
	public byte[] handle(byte[]bs) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 800000; i++) {
			keyValue.set(i + "", i + "");
		}
		return new byte[]{6};
	}

}