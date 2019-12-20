package thread_04.t3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 题目: 两个线程,一个打印1-9, 一个打印a-z, 打印结果是 a1b2c3...
 *  用ReentrantLock.condition方式， 与wait notify类似
 * 
*/
public class V4_ReentrantLock {
	
	static String a = "abcdefgh";
	static String b = "12345678";
	static Thread t1, t2 = null;
	
	static ReentrantLock reentrantLock = new ReentrantLock();
	static Condition c1 = reentrantLock.newCondition();
	static Condition c2 = reentrantLock.newCondition();
	
	public static void main(String[] args) {

		t1 = new Thread(() ->{
			for (char c : a.toCharArray()) {
				reentrantLock.lock();
				try {
					System.out.println(c);
					c2.signal();
					// 最后一个字符打印完不要再await了，否则程序不会停止
					if ( a.indexOf(c) != a.toCharArray().length-1 ) {
						c1.await();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					reentrantLock.unlock();
				}
			}
		});
		
		t2 = new Thread(() ->{
			for (char c : b.toCharArray()) {
				reentrantLock.lock();
				try {
					System.out.println(c);
					c1.signal();
					if ( b.indexOf(c) != b.toCharArray().length-1 ) {
						c2.await();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					reentrantLock.unlock();
				}
			}
		});
		
		t1.start();
		t2.start();
	}

}
