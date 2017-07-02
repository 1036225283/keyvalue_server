package _1036225283.com.keyValue.server.test;

import _1036225283.com.keyValue.server.socket.EngineHandle;
import _1036225283.com.keyValue.server.socket.EngineSocket;
import _1036225283.com.keyValue.server.socket.EngineSocketNIO;
import _1036225283.com.keyValue.server.socket.core.CoreProtocol;
import _1036225283.com.keyValue.server.socket.core.DefaultHandler;
import _1036225283.com.keyValue.server.socket.util.protocol.read.ProtocolHttpReadHandler;
import _1036225283.com.keyValue.server.socket.util.protocol.read.ProtocolXwsReadHandler;
import _1036225283.com.keyValue.server.socket.util.protocol.write.ProtocolHttpWriteHandler;
import _1036225283.com.keyValue.server.socket.util.protocol.write.ProtocolXwsWriteHandler;
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

            engineSocket.getProtocolReadFactory()
                    .regist(CoreProtocol.HTTP.toString(), new ProtocolHttpReadHandler())
                    .regist(CoreProtocol.XWS.toString(), new ProtocolXwsReadHandler())
            ;

            engineSocket.getProtocolWriteFactory()
                    .regist(CoreProtocol.HTTP.toString(), new ProtocolHttpWriteHandler())
                    .regist(CoreProtocol.XWS.toString(), new ProtocolXwsWriteHandler())
            ;

            countMap.put("count", 0L);

            engineHandle.getHandlerFactory()
                    .register(0, new DefaultHandler())
            ;

            engineSocket.start();
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e, "");
        }

    }
}
