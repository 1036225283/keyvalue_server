package _1036225283.com.keyValue.server.socket.util.pool;


import java.nio.ByteBuffer;

public class UtilPoolBuffer extends UtilPool<ByteBuffer> {

    private int size = 64 * 1024;

    public UtilPoolBuffer(Integer max, Integer total, Integer size) {
        // TODO Auto-generated constructor stub
        initParam(max, total);
        if (size != null) {
            this.size = size;
        }
        initPool();
    }

    @Override
    protected ByteBuffer factory() {
        // TODO Auto-generated method stub
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        return byteBuffer;
    }

    @Override
    protected void initValue(ByteBuffer t) {
        // TODO Auto-generated method stub
        t.clear();
    }

}
