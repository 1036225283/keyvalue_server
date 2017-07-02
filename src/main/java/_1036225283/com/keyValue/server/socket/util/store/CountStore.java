package _1036225283.com.keyValue.server.socket.util.store;

import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.util.HashMap;
import java.util.Map;

/**
 * 计数存储
 */
public class CountStore<T> {

    /**
     * <applicationId,T>存储
     */
    private Map<Long, T> applicationMap = new HashMap<Long, T>();
    private long currendId = 0;
    protected LogManager log = LogManager.getInstance();

    /**
     * 创建aplicationId
     *
     * @return
     */
    public synchronized long createApplicationId() {
        currendId = currendId + 1;
        return currendId;
    }

    public synchronized long put(T t) {
        long id = createApplicationId();
        applicationMap.put(id, t);
        return id;
    }

    public synchronized T remove(long id) {
        log.info(LogType.socket, this, "map 中socket：" + applicationMap);
        return applicationMap.remove(id);
    }
}
