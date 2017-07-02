package _1036225283.com.keyValue.server.socket.util.protocol.read;

import _1036225283.com.keyValue.server.socket.EngineSocket;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * 抽象协议处理器
 */
public abstract class ProtocolReadHandler {

    public ProtocolReadHandler() {

    }

    public abstract boolean handle(Map<String, String> map, ByteBuffer buffer, byte[] bs);


    private Map<String, String> map;


    private EngineSocket engineSocket;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }


    public void setEngineSocket(EngineSocket engineSocket) {
        this.engineSocket = engineSocket;
    }

    public EngineSocket getEngineSocket() {
        return engineSocket;
    }

}
