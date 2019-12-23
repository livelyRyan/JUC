package thread_04.t1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * 
 *      典型的两个线程通信问题
 * 
*/
public class V1_WaitNotify {

	List<Integer> lst = Collections.synchronizedList(new ArrayList<>());

	public Boolean add(Integer i) {
		return lst.add(i);
	}

	public int size() {
		return lst.size();
	}

	public static void main(String[] args) {
		V1_WaitNotify lst = new V1_WaitNotify();

		Object lock = new Object();

		new Thread(() -> {
			synchronized (lock) { 
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("added 5, t2 ending");
				lock.notify();
			}
		}).start();

		// 睡一秒,防止add线程的notify方法在监听线程wait之前执行了
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				synchronized (lock) {
					lst.add(i);
					System.out.println("add num " + i);
					if (lst.size() == 5) {
						// notify不会释放锁
						lock.notify();
						try {
							// wait 让当前线程释放出锁, 并且给监控线程执行时间
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}

}
