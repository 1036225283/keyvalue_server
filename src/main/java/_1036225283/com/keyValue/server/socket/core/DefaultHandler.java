package _1036225283.com.keyValue.server.socket.core;

//默认的处理方式，返回错误
public class DefaultHandler extends Handler {

	@Override
	public byte[] handle(byte[]bs) {
		// TODO Auto-generated method stub
		return new byte[]{5};
	}

}
