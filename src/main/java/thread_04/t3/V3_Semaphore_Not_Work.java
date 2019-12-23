package thread_04.t3;

import java.util.concurrent.Semaphore;

/*
 * 题目: 两个线程,一个打印1-9, 一个打印a-z, 打印结果是 a1b2c3...
 * 当前实现存在一些问题未解决： 
 * 		t1.acquire()可能在t2.acquire()后面执行；
 * 		t1.release()的时候，t2不一定执行了acquire();
 *       因此用semaphore并不算是一个可行的事情
*/
public class V3_Semaphore_Not_Work {
	static String a = "abcdefgh";
	static String b = "12345678";
	static Thread t1, t2 = null;
	// 一定要用公平锁，否则不能保证t1释放锁后t2拿到
	static Semaphore semaphore = new Semaphore(1, true);
	
	public static void main(String[] args) {

		t1 = new Thread(() ->{
			for (char c : a.toCharArray()) {
				try {
					semaphore.acquire();
					System.out.println(c);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaphore.release();
				}
			}
		});
		
		t2 = new Thread(() ->{
			for (char c : b.toCharArray()) {
				try {
					semaphore.acquire();
					System.out.println(c);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaphore.release();
				}
			}
		});
		
		t1.start();
		t2.start();
	}

}
