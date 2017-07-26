package _1036225283.com.keyValue.server.socket.util;

import _1036225283.com.keyValue.client.Operation;

/**
 * server tools
 * Created by xws on 7/26/17.
 */
public class UtilKeyValueServer {

    //验证密码是否正确
    public static boolean auth(byte[] src, int length) {
        byte[] dest = new byte[length];
        System.arraycopy(src, 0, dest, 0, length);
        String pass = "";

        try {
            byte[] result = Factory.RSA.privateKeyDecode(dest);
            pass = new String(result);
        } catch (Exception e) {
            pass = "";
        }
        if (Factory.pass.equals(pass)) {
            return true;
        } else {
            return false;
        }
    }

    //error
    public static byte[] ERROR(String error) {
        String total = 1 + error;
        byte[] bs = total.getBytes();
        bs[0] = Operation.ERROR;
        return bs;
    }

    //success
    public static byte[] SUCCESS(String success) {
        String total = 1 + success;
        byte[] bs = total.getBytes();
        bs[0] = Operation.SUCCESS;
        return bs;
    }


}
