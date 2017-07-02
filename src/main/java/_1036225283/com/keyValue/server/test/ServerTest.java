package _1036225283.com.keyValue.server.test;

import _1036225283.com.keyValue.server.handler.ExitHandler;
import _1036225283.com.keyValue.server.handler.LoginHandler;
import _1036225283.com.keyValue.server.handler.TestHandler;
import _1036225283.com.keyValue.server.handler.keyValue.GetHandler;
import _1036225283.com.keyValue.server.handler.keyValue.InitHandler;
import _1036225283.com.keyValue.server.handler.keyValue.SetHandler;
import _1036225283.com.keyValue.server.socket.EngineHandle;
import _1036225283.com.keyValue.server.socket.EngineSocket;
import _1036225283.com.keyValue.server.socket.EngineSocketNIO;
import _1036225283.com.keyValue.server.socket.core.CoreProtocol;
import _1036225283.com.keyValue.server.socket.core.CoreType;
import _1036225283.com.keyValue.server.socket.core.Handler;
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
                    .regist("/user/login", new LoginHandler())
                    .regist("/exit", new ExitHandler())
                    .regist("/test", new TestHandler())
                    .regist("/love", new Handler() {
                        @Override
                        public void handle(Map<String, String> map) {
                            long count = countMap.get("count");
                            map.put(CoreType.result.toString(), "l love you " + count + " days !!!");
                            count = count + 1;
                            countMap.put("count", count);
                        }
                    })
                    .regist("/fuck", new Handler() {
                        @Override
                        public void handle(Map<String, String> map) {
                            map.put(CoreType.result.toString(), "l fuck you !!!");
                        }
                    })
                    .regist("/key-value/get", new GetHandler())
                    .regist("/key-value/set", new SetHandler())
                    .regist("/redis/init", new InitHandler())
            ;

            engineSocket.start();
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e, "");
        }

    }
}
