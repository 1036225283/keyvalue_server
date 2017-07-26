package _1036225283.com.keyValue.server.socket.util;

/**
 * server tools
 * Created by xws on 7/26/17.
 */
public class UtilKeyValueServer {

    //验证密码是否正确
    public static boolean auth(byte[] src, int length) {
        byte[] dest = new byte[length];
        System.arraycopy(src, 0, dest, 0, length);
        byte[] result = Factory.RSA.privateKeyDecode(dest);
        String pass = new String(result);
        if (Factory.pass.equals(pass)) {
            return true;
        } else {
            return false;
        }
    }

}
