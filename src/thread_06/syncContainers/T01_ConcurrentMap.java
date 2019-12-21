/**
 * http://blog.csdn.net/sunxianghuang/article/details/52221913 
 * http://www.educity.cn/java/498061.html
 * �Ķ�concurrentskiplistmap
 */
package thread_06.syncContainers;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class T01_ConcurrentMap {
	public static void main(String[] args) {
//		Map<String, String> map = new ConcurrentHashMap<>();
		Map<String, String> map = new ConcurrentSkipListMap<>(); //�߲�����������
		
		//Map<String, String> map = new Hashtable<>();
		//Map<String, String> map = new HashMap<>(); //Collections.synchronizedXXX
		
		// һ�ٸ��߳�,ÿ���̷ֱ߳��100������, ���㻨��ʱ��
		Random r = new Random();
		Thread[] ths = new Thread[100];
		CountDownLatch latch = new CountDownLatch(ths.length);
		long start = System.currentTimeMillis();
		for(int i=0; i<ths.length; i++) {
			ths[i] = new Thread(()->{
				// keyͨ�����������,��˿��ܴ������map.size()����1w�����, ��Ϊkey�����ظ���
				for(int j=1; j<101; j++) map.put(String.valueOf(r.nextInt(1000000000)*j), "a");
				latch.countDown();
			});
		}
		
		Arrays.asList(ths).forEach(t->t.start());
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println(map.size());

	}
}
