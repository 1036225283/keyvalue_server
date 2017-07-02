package _1036225283.com.keyValue.server.socket.core;

/**
 * 协议的枚举
 *
 * @author 1036225283
 */
public enum CoreProtocol {

    HTTP("HTTP"), WEBSOCKET("WEBSOCKET"), WEBSOCKETUPGRADE("WEBSOCKETUPGRADE"), XWS("XWS"), HTTPS("HTTPS");

    private String value;

    CoreProtocol(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
