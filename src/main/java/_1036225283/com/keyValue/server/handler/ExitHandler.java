package _1036225283.com.keyValue.server.handler;

import java.util.Map;

import _1036225283.com.keyValue.server.socket.core.Handler;

public class ExitHandler extends Handler {

	@Override
	public void handle(Map<String, String> map) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

}
