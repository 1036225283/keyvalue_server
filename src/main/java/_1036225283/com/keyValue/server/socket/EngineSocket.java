package _1036225283.com.keyValue.server.socket;

import _1036225283.com.keyValue.server.socket.util.UtilPoolThread;
import _1036225283.com.keyValue.server.socket.util.factory.Factory;
import _1036225283.com.keyValue.server.socket.util.pool.UtilPoolBuffer;
import _1036225283.com.keyValue.server.socket.util.pool.UtilPoolByte;
import _1036225283.com.keyValue.server.socket.util.pool.UtilPoolMap;
import _1036225283.com.keyValue.server.socket.util.protocol.read.ProtocolReadFactory;
import _1036225283.com.keyValue.server.socket.util.protocol.write.ProtocolWriteFactory;
import _1036225283.com.keyValue.server.socket.util.queue.UtilQueue;
import _1036225283.com.keyValue.server.socket.util.store.CountStore;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1036225283 on 2016/11/13.
 * 消息引擎
 */
public class EngineSocket<T> {

	public LogManager log = LogManager.getInstance();

	private Map<T, String> socketMap;

	/**
	 * 业务引擎
	 */
	private EngineHandle engineHandle;
	private Integer port;
	private CountStore countStore;
	private ServerSocket serverSocket;
	private int poolMax = 800;
	private int poolTotal = 200;

	private ProtocolReadFactory protocolReadFactory;
	private ProtocolWriteFactory protocolWriteFactory;

	private UtilQueue queueRead;
	private UtilQueue queueWrite;

	private UtilPoolBuffer poolBuffer;
	private UtilPoolByte poolByte;
	private UtilPoolMap poolMap;

	public EngineSocket(int port) {
		this.port = port;
	}

	public EngineSocket() {
	}

	public void init() {

		Thread.currentThread().setName("线程:主轮询线程");

		socketMap = new HashMap<>();

		protocolReadFactory = new ProtocolReadFactory();
		protocolWriteFactory = new ProtocolWriteFactory();

		countStore = Factory.getCountStore(this.getClass().getName());

		poolBuffer = Factory.getPoolBuffer(this.getClass().getName(), this);
		poolByte = new UtilPoolByte(poolMax, poolTotal, null);// socket读取缓冲区(lend:replay)
		poolMap = new UtilPoolMap(poolMax, poolTotal);// 解析数据缓冲区(lend:)

		queueRead = Factory.getReadQueue(this.getClass().getName(), this);
		queueWrite = Factory.getWriteQueue(this.getClass().getName(), this);

		// 开启解析线程
		new Thread(queueRead, "线程：读队列线程").start();
		new Thread(queueWrite, "线程：写队列线程").start();
	}

	public void push(Map<String, String> map) {
		queueWrite.push(map);
	}

	public void start() throws IOException {
		init();
		if (port == null) {
			port = 8080;
		}
		Thread.currentThread().setName("线程：服务主线程");
		log.info(LogType.thread, this, Thread.currentThread().toString());
		serverSocket = new ServerSocket(port);
		log.info(LogType.debug, this, "server is start");
		while (true) {
			Socket socket = serverSocket.accept();
			log.dateInfo(LogType.time, this, "____________________________________________________");
			log.dateInfo(LogType.time, this, "第一步：接收socket开始");
			queueRead.push(socket);
			// applicationContext.getQueueRead().push(socket);
			// WriteTest writeTest = new WriteTest(socket);
			// writeTest.start();
			log.dateInfo(LogType.time, this, "第一步：接收socket结束");
		}
	}

	public void setEngineHandle(EngineHandle engineHandle) {
		this.engineHandle = engineHandle;
		engineHandle.setEngineSocket(this);
	}

	public UtilPoolByte getPoolByte() {
		return poolByte;
	}

	public UtilPoolBuffer getPoolBuffer() {
		return poolBuffer;
	}

	public CountStore getCountStore() {
		return countStore;
	}

	public UtilPoolMap getPoolMap() {
		return poolMap;
	}

	public UtilPoolThread getPoolWebSocketThread() {
		return null;
	}

	public EngineHandle getEngineHandle() {
		return engineHandle;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public UtilQueue getQueueRead() {
		return queueRead;
	}

	public Integer getPort() {
		return port;
	}

	public int getPoolMax() {
		return poolMax;
	}

	public int getPoolTotal() {
		return poolTotal;
	}

	/**
	 * 系统回调处理
	 *
	 * @param object
	 */
	public synchronized void callback(Object object) {
	}

	public synchronized Map<T, String> getSocketMap() {
		return socketMap;
	}

	/**
	 * 获取协议处理器
	 *
	 * @return 协议处理器
	 */
	public ProtocolReadFactory getProtocolReadFactory() {
		return protocolReadFactory;
	}

	public ProtocolWriteFactory getProtocolWriteFactory() {
		return protocolWriteFactory;
	}

	// public void setProtocolWriteFactory(ProtocolWriteFactory
	// protocolWriteFactory) {
	// this.protocolWriteFactory = protocolWriteFactory;
	// }
}
