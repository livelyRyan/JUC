package thread_04.t1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/*
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * 
*/
public class V2_CountDownLatch {

	List<Integer> lst = Collections.synchronizedList(new ArrayList<>());

	public Boolean add(Integer i) {
		return lst.add(i);
	}

	public int size() {
		return lst.size();
	}

	public static void main(String[] args) {
		V2_CountDownLatch lst = new V2_CountDownLatch();

		CountDownLatch latch = new CountDownLatch(1);
		// 当add线程加到5后进行等待. 给监控线程时间打印
		CountDownLatch latch2 = new CountDownLatch(1);
		
		new Thread(() -> {
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("added 5, t2 ending");
			latch2.countDown();
		}).start();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				lst.add(i);
				System.out.println("add num " + i);
				if (lst.size() == 5) {
					latch.countDown();
					try {
						latch2.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
