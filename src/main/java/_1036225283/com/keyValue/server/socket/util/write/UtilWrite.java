package _1036225283.com.keyValue.server.socket.util.write;

import _1036225283.com.keyValue.server.socket.EngineSocket;
import com.nitian.util.log.LogManager;

import java.util.Map;

/**
 * 抽象写工具类
 */
public abstract class UtilWrite {

    protected static LogManager log = LogManager.getInstance();

    public abstract void write(Map<String, String> map, EngineSocket engineSocket);
}
