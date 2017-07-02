package _1036225283.com.keyValue.server.handler;

import _1036225283.com.keyValue.server.socket.core.CoreType;
import _1036225283.com.keyValue.server.socket.core.Handler;

import java.util.Map;

public class TestHandler extends Handler {

	@Override
	public void handle(Map<String, String> map) {
		// TODO Auto-generated method stub
		long start = System.nanoTime();
		int a[] = new int[100000];
		int i;
		for (i = 0; i < 100000; i++) {
			a[i] = i;
		}
		long end = System.nanoTime();
		map.put(CoreType.result.toString(), (end - start) / 1000 + "");
	}

}
