package _1036225283.com.keyValue.server.socket.util;

import com.nitian.util.random.UtilRandom;

import java.util.Timer;
import java.util.TimerTask;

//扫描需要示范的资源
public class UtilTimer {

    public static void main(String[] args) {
        timer();
    }

    public static void timer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
                System.out.println(UtilRandom.createUUID());
            }
        }, 1000, 2000);
    }
}
