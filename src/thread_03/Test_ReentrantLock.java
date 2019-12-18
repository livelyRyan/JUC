package thread_03;

import java.util.concurrent.locks.ReentrantLock;

public class Test_ReentrantLock {
	
	public static void main(String[] args) {
	
		// ���: �Ƿ��ǹ�ƽ��,Ĭ�ϼ�false
		ReentrantLock lock = new ReentrantLock(false);
		
		new Thread(() -> {
			try {
				// ����
				lock.lock();
				System.out.println("t1 start sleep");
				Thread.sleep(1000);
				System.out.println("t1 end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				// ����
				lock.unlock();
			}
		}).start();
		
		new Thread(() -> {
			try {
				// ���Ի�ȡ��
				boolean gotLock = lock.tryLock();
				// ����ʱ�ķ���
				// lock.tryLock(1000, TimeUnit.MICROSECONDS);
				System.out.println("does t2 have the lock? " + gotLock);
				// ����
				lock.lock();
				System.out.println("t2 has got the lock");
				Thread.sleep(1000);
				System.out.println("t2 end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				// ����
				lock.unlock();
			}
		}).start();
	}
}
