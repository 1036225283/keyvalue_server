package _1036225283.com.keyValue.server.socket.core;

import java.util.Map;

public class DefaultHandler extends Handler {

	@Override
	public void handle(Map<String, String> map) {
		// TODO Auto-generated method stub
		map.put(CoreType.result.toString(), "{\"state\":\"404\"}");
	}

}
