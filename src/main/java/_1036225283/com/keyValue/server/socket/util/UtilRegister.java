package _1036225283.com.keyValue.server.socket.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册器
 * 
 * @author 1036225283
 *
 * @param <T>
 */
public class UtilRegister<T> {

	private Map<String, Object> map = new HashMap<String, Object>();

	private String username = "admin";

	private String password = "admin123";

	public UtilRegister<T> regist(String key, T t) {
		if (map.containsKey(key)) {
			throw new RuntimeException(t.toString() + " the key is registered");
		}
		map.put(key, t);
		return this;
	}

	public UtilRegister<T> regist(String key, T t, String username,
			String password) {
		if (this.username.equals(username) && this.password.equals(password)) {
			map.put(key, t);
		} else {
			throw new RuntimeException(t.toString()
					+ " username or password is error");
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	public T get(String key) {
		return (T) map.get(key);
	}

}
