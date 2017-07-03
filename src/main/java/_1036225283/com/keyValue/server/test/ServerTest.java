package _1036225283.com.keyValue.server.test;

import _1036225283.com.keyValue.server.handler.keyValue.GetHandler;
import _1036225283.com.keyValue.server.socket.EngineHandle;
import _1036225283.com.keyValue.server.socket.EngineSocket;
import _1036225283.com.keyValue.server.socket.EngineSocketNIO;
import _1036225283.com.keyValue.server.socket.core.DefaultHandler;
import com.nitian.util.log.LogManager;

import java.util.HashMap;
import java.util.Map;

public class ServerTest {


    public static void main(String[] args) {

        LogManager log = LogManager.getInstance();

        final Map<String, Long> countMap = new HashMap<>();


        try {

            LogManager.setFileLog(true);
            LogManager.setIsConsole(true);

//            log.putType(LogType.debug.toString());
//            log.putType(LogType.error.toString());
//            log.putType(LogType.info.toString());
//            log.putType(LogType.warning.toString());
//
//            log.putType(LogType.time.toString());

            EngineHandle engineHandle = new EngineHandle();
            EngineSocket engineSocket = new EngineSocketNIO(8888);
            engineSocket.setEngineHandle(engineHandle);

            countMap.put("count", 0L);

            engineHandle.getHandlerFactory()
                    .register(1, new GetHandler())
            ;

            engineSocket.start();
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e, "");
        }

    }
}
