package thread_04.t1;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

/*
 * Semaphore + join方法
 * 用Semaphore去限制add线程必须先于监控线程中的打印语句前执行
 * 用join方法确保add线程在放完第五个，执行semaphore.release()后让给监控线程去打印
 *  
 * 
*/
public class V4_Semaphore {

	List<Integer> lst = Collections.synchronizedList(new ArrayList<>());

	public Boolean add(Integer i) {
		return lst.add(i);
	}

	public int size() {
		return lst.size();
	}

	static Thread t1, t2 = null;
	
	public static void main(String[] args) {
		V4_Semaphore lst = new V4_Semaphore();
		
		Semaphore semaphore = new Semaphore(1);
		
		t1 = new Thread(() -> {
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("added 5, t2 ending");
			// 监控线程运行结束后， 不需要释放令牌， 因为此后t2的运行不再需要令牌了，即没有调用semaphore.acquire();
//			semaphore.release();
		});

		t2 = new Thread(() -> {
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 5; i++) {
				lst.add(i);
				System.out.println("add num " + i);
			}
			// add线程放完5个后， 释放许可
			semaphore.release();
			try {
				// 当释放许可后，新的许可又发到了当前线程， 
				// 则进行join操作，强制让监控线程打印完再进行其他步骤
				// 一定要确保执行t1.join时, t1已经start了. 否则t1.join不会让t1去执行打印
				while ( t1.getState() == State.NEW ) {
					Thread.sleep(1);
				}
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 5; i < 10; i++) {
				lst.add(i);
				System.out.println("add num " + i);
			}
		});
		
		// thread.start后，线程从new的状态变为就绪态，因此start方法的调用顺序不能决定线程的执行顺序
		// 所以这里设置线程优先级， 让t2一定先执行
		t2.setPriority(10);
		t1.setPriority(1);
		t2.start();
		t1.start();
	}

}
