package _1036225283.com.keyValue.server.socket.util;


import com._1036225283.util.self.encrypt.UtilRSA;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1036225283 on 2016/11/20.
 * 工厂函数，根据类名来返回生成的实例
 */
public class Factory {


    public static HandlerFactory handlerFactory = new HandlerFactory();

    public static UtilRSA RSA = new UtilRSA("/software/test.jks", "xws@#$0905", "www.1036225283.com", "xws@#$private");

    public static String pass = "xwsKeyValue@#$";

    public static List<Info> list = new ArrayList<>();




}
