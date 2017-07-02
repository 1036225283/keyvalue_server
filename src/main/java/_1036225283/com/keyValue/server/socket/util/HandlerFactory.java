package _1036225283.com.keyValue.server.socket.util;

import _1036225283.com.keyValue.server.socket.core.Handler;

/**
 * url-handler处理器
 *
 * @author 1036225283
 */
public class HandlerFactory extends UtilListRegister<Handler> {

    public Handler get(int index) {
        // TODO Auto-generated method stub
        Handler handler = super.get(index);
        if (handler == null) {
            return super.get(0);
        } else {
            return handler;
        }
    }
}
