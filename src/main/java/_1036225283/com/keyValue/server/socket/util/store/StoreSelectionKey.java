package _1036225283.com.keyValue.server.socket.util.store;

import java.nio.channels.SelectionKey;

/**
 * 协议-(SocketChannel)SelectionKey
 * Created by 1036225283 on 2016/12/11.
 */
public class StoreSelectionKey {

    private String protocol;
    private SelectionKey selectionKey;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public SelectionKey getSelectionKey() {
        return selectionKey;
    }

    public void setSelectionKey(SelectionKey selectionKey) {
        this.selectionKey = selectionKey;
    }
}
