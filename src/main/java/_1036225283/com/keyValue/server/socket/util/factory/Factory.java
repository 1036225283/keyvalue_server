package _1036225283.com.keyValue.server.socket.util.factory;

import _1036225283.com.keyValue.server.socket.EngineSocket;
import _1036225283.com.keyValue.server.socket.EngineSocketNIO;
import _1036225283.com.keyValue.server.socket.util.pool.UtilPoolBuffer;
import _1036225283.com.keyValue.server.socket.util.queue.UtilQueue;
import _1036225283.com.keyValue.server.socket.util.queue.UtilQueueSocketChannel;
import _1036225283.com.keyValue.server.socket.util.queue.UtilQueueWrite;
import _1036225283.com.keyValue.server.socket.util.store.CountStore;
import _1036225283.com.keyValue.server.socket.util.store.CountStoreSelectionKey;
import _1036225283.com.keyValue.server.socket.util.store.CountStoreSocket;
import _1036225283.com.keyValue.server.socket.util.write.UtilHttpWriteSocket;
import _1036225283.com.keyValue.server.socket.util.write.UtilWrite;

/**
 * Created by 1036225283 on 2016/11/20.
 * 工厂函数，根据类名来返回生成的实例
 */
public class Factory {

	/**
	 * 获取计数存储socket等对象
	 *
	 * @param className
	 * @return
	 */
	public static CountStore getCountStore(String className) {
		if (className.equals(EngineSocket.class.getName())) {
			return new CountStoreSocket();
		} else if (className.equals(EngineSocketNIO.class.getName())) {
			return new CountStoreSelectionKey();
		}
		return null;
	}

	/**
	 * 获取读线程队列
	 *
	 * @param className
	 * @param engineSocket
	 * @return
	 */
	public static UtilQueue getReadQueue(String className, EngineSocket engineSocket) {
		if (className.equals(EngineSocket.class.getName())) {
			return new UtilQueueSocketChannel(engineSocket);
		} else if (className.equals(EngineSocketNIO.class.getName())) {
			return new UtilQueueSocketChannel(engineSocket);
		}
		return null;

	}

	/**
	 * 获取写线程队列
	 *
	 * @param className
	 * @param engineSocket
	 * @return
	 */
	public static UtilQueue getWriteQueue(String className, EngineSocket engineSocket) {
		System.out.println("------------------------------------" + className);
		if (className.equals(EngineSocket.class.getName())) {
			return new UtilQueueWrite(engineSocket);
		} else if (className.equals(EngineSocketNIO.class.getName())) {
			return new UtilQueueWrite(engineSocket);
		}
		return null;

	}

	/**
	 * 获取buffer池
	 *
	 * @param className
	 * @return
	 */
	public static UtilPoolBuffer getPoolBuffer(String className, EngineSocket engineSocket) {
		if (className.equals(EngineSocket.class.getName())) {
			return null;
		} else if (className.equals(EngineSocketNIO.class.getName())) {
			return new UtilPoolBuffer(engineSocket.getPoolMax(), engineSocket.getPoolTotal(), null);
		}
		return null;

	}

	/**
	 * 获取 http write
	 *
	 * @param className
	 * @return
	 */
	public static UtilWrite getUtilHttpWrite(String className) {
		System.out.println("------------------------------------" + className);
		if (className.equals(EngineSocket.class.getName())) {
			return new UtilHttpWriteSocket();
		} else if (className.equals(EngineSocketNIO.class.getName())) {
			return new UtilHttpWriteSocket();
		}
		return null;
	}

}
