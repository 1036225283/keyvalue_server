package _1036225283.com.keyValue.server.socket.core;

/**
 * 协议的枚举
 *
 * @author 1036225283
 */
public enum CoreUrl {

	PING("/ping"), PONG("/pong"), CLOSE("/close");

	private String value;

	CoreUrl(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

	public static void main(String[] args) {
	}
}
