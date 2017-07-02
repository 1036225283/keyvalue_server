package _1036225283.com.keyValue.server.socket.util;

import _1036225283.com.keyValue.server.socket.core.Handler;

/**
 * url-handler处理器
 *
 * @author 1036225283
 */
public class HandlerFactory extends UtilRegister<Handler> {

    @SuppressWarnings("unchecked")
    @Override
    public Handler get(String key) {
        // TODO Auto-generated method stub
        Handler handler = super.get(key);
        if (handler == null) {
            return super.get("default");
        } else {
            return handler;
        }
    }
}
