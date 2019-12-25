package thread_09.Disruptor;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {

    /**
     *
     * @param event
     * @param sequence RingBuffer鐨勫簭鍙�
     * @param endOfBatch 鏄惁涓烘渶鍚庝竴涓厓绱�
     * @throws Exception
     */

    public static long count = 0;

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        count ++;
        System.out.println("[" + Thread.currentThread().getName() + "]" + event + " 搴忓彿锛�" + sequence);
    }
}
