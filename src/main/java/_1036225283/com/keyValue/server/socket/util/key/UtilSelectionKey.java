package _1036225283.com.keyValue.server.socket.util.key;

import java.nio.channels.SelectionKey;

/**
 * nio 异常时关闭SelectionKey和SocketChannel
 * Created by 1036225283 on 2016/12/20.
 */
public class UtilSelectionKey {

    public static void cancel(SelectionKey selectionKey) {
        selectionKey.cancel();
        try {
            selectionKey.channel().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
