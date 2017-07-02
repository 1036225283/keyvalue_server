package _1036225283.com.keyValue.server.socket.util;

public class UtilStringTest {

	public static String getGET() {
		StringBuffer sb = new StringBuffer();
		sb.append("GET / HTTP/1.1")
				.append("\n")
				.append("Host: 127.0.0.1:8080")
				.append("\n")
				.append("User-Agent: Mozilla/5.0 (Windows NT 6.3; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0")
				.append("\n")
				.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
				.append("\n")
				.append("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
				.append("\n").append("Accept-Encoding: gzip, deflate")
				.append("Connection: keep-alive").append("\n");
		return sb.toString();
	}
}
