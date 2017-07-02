package _1036225283.com.keyValue.server.socket.core;

import _1036225283.com.keyValue.server.socket.EngineHandle;

import java.util.Map;

public abstract class Handler {

    public Handler() {

    }

    public abstract void handle(Map<String, String> map);

    private EngineHandle engineHandle;

    private Map<String, String> map;

    public void afterHandle() {
        engineHandle.getEngineSocket().push(map);
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }


    public void setEngineHandle(EngineHandle engineHandle) {
        this.engineHandle = engineHandle;
    }

}
