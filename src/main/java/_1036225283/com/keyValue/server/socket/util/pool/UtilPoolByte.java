package _1036225283.com.keyValue.server.socket.util.pool;


public class UtilPoolByte extends UtilPool<byte[]> {

	private int size = 64 * 1024;

	public UtilPoolByte(Integer max, Integer total, Integer size) {
		// TODO Auto-generated constructor stub
		initParam(max, total);
		if (size != null) {
			this.size = size;
		}
		initPool();
	}

	@Override
	protected byte[] factory() {
		// TODO Auto-generated method stub
		byte[] bs = new byte[size];
		initValue(bs);
		return bs;
	}

	@Override
	protected void initValue(byte[] t) {
		// TODO Auto-generated method stub
		// for (int i = 0; i < t.length; i++) {
		// t[i] = 0;
		// }
	}

}
