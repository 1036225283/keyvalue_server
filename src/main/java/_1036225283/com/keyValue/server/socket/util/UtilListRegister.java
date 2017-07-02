package _1036225283.com.keyValue.server.socket.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 线性映射注册器
 *
 * @param <T>
 * @author 1036225283
 */
public class UtilListRegister<T> {

    private List<T> list = new ArrayList<>();

    private String username = "admin";

    private String password = "admin123";

    public UtilListRegister(){
        for (int i=0;i<100;i++){
            list.add(i,null);
        }
    }


    public UtilListRegister<T> register(int index, T t) {
        if (list.contains(t)) {
            throw new RuntimeException(t.toString() + " the t is registered for list register");
        }
        if (list.get(index) != null) {
            throw new RuntimeException(t.toString() + " the index is registered for list register");
        }
        list.add(index, t);
        return this;

    }

    public UtilListRegister<T> register(int index, T t, String username,
                                        String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            list.add(index, t);
        } else {
            throw new RuntimeException(t.toString()
                    + " username or password is error");
        }
        return this;
    }

    public T get(int index) {
        return list.get(index);
    }

}
