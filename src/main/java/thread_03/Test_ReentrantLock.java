package thread_03;

import java.util.concurrent.locks.ReentrantLock;

public class Test_ReentrantLock {
	
	public static void main(String[] args) {
	
		// 入参: 是否是公平锁,默认即false
		ReentrantLock lock = new ReentrantLock(false);
		
		new Thread(() -> {
			try {
				// 加锁
				lock.lock();
				System.out.println("t1 start sleep");
				Thread.sleep(1000);
				System.out.println("t1 end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				// 解锁
				lock.unlock();
			}
		}).start();
		
		new Thread(() -> {
			try {
				// 尝试获取锁
				boolean gotLock = lock.tryLock();
				// 带超时的方法
				// lock.tryLock(1000, TimeUnit.MICROSECONDS);
				System.out.println("does t2 have the lock? " + gotLock);
				// 加锁
				lock.lock();
				System.out.println("t2 has got the lock");
				Thread.sleep(1000);
				System.out.println("t2 end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				// 解锁
				lock.unlock();
			}
		}).start();
	}
}
