package _1036225283.com.keyValue.server.socket.util;

import java.net.Socket;
import java.util.Date;

/**
 * Created by 1036225283 on 2016/11/9.
 */
public class WriteTest extends Thread {

    private Socket socket;

    public WriteTest(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        write(socket);
    }

    public static void write(Socket socket) {
        String result = "赵玉，我会不会慢慢喜欢上你";
        StringBuffer sb = new StringBuffer();
        sb.append("HTTP/1.1 200 OK").append("\r\n");
        sb.append("Server: Apache-Coyote/1.1").append("\r\n");
        sb.append(
                "Set-Cookie: JSESSIONID=641D11458BF06D2592E3BEFD612531D0; Path=/spring-mvc/; HttpOnly")
                .append("\r\n");
        sb.append("Access-Control-Allow-Origin: *").append("\r\n");
        sb.append("Accept-Charset: utf-8").append("\r\n");
        sb.append("Content-Type: text/html;charset=UTF-8").append("\r\n");
        if (result == null) {
            sb.append("Content-Length: ").append(0).append("\r\n");
            sb.append("Date: ").append(new Date().toString()).append("\r\n")
                    .append("\r\n");
            // ---------------------------------------------------------------------------------------
            sb.append("");
        } else {
            System.out.println(result.getBytes().length);
            sb.append("Content-Length: ").append(result.getBytes().length + 4).append("\r\n");
            sb.append("\r\n").append("\r\n");
            // ---------------------------------------------------------------------------------------
            sb.append(result);
        }
        try {
            socket.getOutputStream().write(sb.toString().getBytes());
            socket.close();
        } catch (Exception e) {
            System.out.println("write data is execption");
        }


    }

    public static String getString(String value) {
        String result = value;
        StringBuffer sb = new StringBuffer();
        sb.append("HTTP/1.1 200 OK").append("\r\n");
        sb.append("Server: Apache-Coyote/1.1").append("\r\n");
        sb.append(
                "Set-Cookie: JSESSIONID=641D11458BF06D2592E3BEFD612531D0; Path=/spring-mvc/; HttpOnly")
                .append("\r\n");
        sb.append("Access-Control-Allow-Origin: *").append("\r\n");
        sb.append("Accept-Charset: utf-8").append("\r\n");
        sb.append("Content-Type: text/html;charset=UTF-8").append("\r\n");
        System.out.println(result.getBytes().length);
        sb.append("Content-Length: ").append(result.getBytes().length + 4).append("\r\n");
        sb.append("\r\n").append("\r\n");
        // ---------------------------------------------------------------------------------------
        sb.append(result);
        return sb.toString();
    }
}
