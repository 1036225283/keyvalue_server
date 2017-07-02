package _1036225283.com.keyValue.server.socket.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UtilPoolThread {

	private ExecutorService executorService;

	public UtilPoolThread(Integer size) {
		// TODO Auto-generated constructor stub
		if (size == null) {
			executorService = Executors.newFixedThreadPool(10);
		} else if (size == 1) {
			executorService = Executors.newSingleThreadExecutor();
		} else if (size < Integer.MAX_VALUE) {
			executorService = Executors.newFixedThreadPool(size);
		} else if (size == Integer.MAX_VALUE) {
			executorService = Executors.newScheduledThreadPool(10);
		}
	}

	public void execute(Runnable command) {
		executorService.execute(command);
	}

}
