package _1036225283.com.keyValue.server.test;

import _1036225283.com.keyValue.server.handler.keyValue.GetHandler;
import _1036225283.com.keyValue.server.handler.keyValue.RemoveHandler;
import _1036225283.com.keyValue.server.handler.keyValue.SetHandler;
import _1036225283.com.keyValue.server.socket.EngineSocket;
import _1036225283.com.keyValue.server.socket.core.DefaultHandler;
import _1036225283.com.keyValue.server.socket.util.Factory;
import _1036225283.com.keyValue.server.socket.util.UtilTimer;
import com._1036225283.util.self.log.LogManager;
import com._1036225283.util.self.log.LogType;

public class ServerTest {


    public static void main(String[] args) {

        LogManager log = LogManager.getInstance();


        try {

            new UtilTimer();

            LogManager.setFileLog(true);
            LogManager.setIsConsole(true);

//            log.putType(LogType.debug.toString());
//            log.putType(LogType.error.toString());
            log.putType(LogType.info.toString());
//            log.putType(LogType.warning.toString());
//
//            log.putType(LogType.time.toString());

//            EngineSocket engineSocket = new EngineSocketNIO(8888);
            EngineSocket engineSocket = new EngineSocket(9999);

            // bs[0] = 0 // default protocol error
            // bs[0] = 1 // set key value
            // bs[0] = 2 // get key
            // bs[0] = 3 // result
            // bs[0] = 4 // remove key
            // bs[0] = 5 // error

            // bs[0] = 6 // ok
            Factory.handlerFactory
                    .register(0, new DefaultHandler())
                    .register(1, new SetHandler())
                    .register(2, new GetHandler())
                    .register(4, new RemoveHandler())
            ;

            engineSocket.start();
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e, "");
        }

    }
}
