package _1036225283.com.keyValue.server.socket.util.list;

import java.util.LinkedList;
import java.util.List;

/**
 * 有限集合
 * 
 * @author 1036225283
 *
 */
public class UtilList<T> {

	private List<T> list = new LinkedList<T>();
	private int max = 1000;// 缓存容量极值

	public synchronized void remove(T t) {
		list.remove(t);
	}

	/**
	 * 存放值，如果空间足够，存放成功，返回true，空间不够，存放失败，返回false
	 * 
	 * @param t
	 * @return
	 */
	public synchronized boolean put(T t) {
		if (list.size() < max) {
			list.add(t);
			return true;
		} else {
			return false;
		}
	}

	public synchronized List<T> getList() {
		return list;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

}
