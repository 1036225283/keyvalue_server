package _1036225283.com.keyValue.server.socket.util;


import com._1036225283.util.self.log.LogManager;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

//扫描需要释放的资源
public class UtilTimer {

    static LogManager log = LogManager.getInstance();

    static {
        timer();
    }

    public static void timer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                List<Info> list = Factory.list;

                log.dateInfo(this, "connection.size:" + list.size());

                for (int i = 0; i < list.size(); i++) {
                    Info info = list.get(i);

                    try {
                        if (!info.state()) {
                            info.getThread().interrupt();
                            info.getSocketThread().getSocket().close();
                            log.dateInfo(this, info.state() + "");
                            list.remove(info);
                            break;
                        }

                        if (info.getSocketThread().getSocket().isClosed()) {
                            info.getThread().interrupt();
                            info.getSocketThread().getSocket().close();
                            log.dateInfo(this, info.state() + "");
                            list.remove(info);
                            break;
                        }

                        if (!info.getThread().isAlive()) {
                            info.getThread().interrupt();
                            info.getSocketThread().getSocket().close();
                            log.dateInfo(this, info.state() + "");
                            list.remove(info);
                            break;
                        }

                    } catch (Exception e) {
                        log.dateInfo(this, e.getMessage());
                    }

                }
            }
        }, 1000, 10 * 1000);
    }
}
