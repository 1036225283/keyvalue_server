package _1036225283.com.keyValue.server.socket.util.protocol.write;

import _1036225283.com.keyValue.server.socket.EngineSocket;

import java.util.Map;

/**
 * 抽象协议写处理器
 */
public abstract class ProtocolWriteHandler {

    public ProtocolWriteHandler() {

    }

    public abstract byte[] handle(Map<String, String> map);


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
