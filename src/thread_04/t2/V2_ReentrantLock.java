package thread_04.t2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *         面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法， 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 *	使用ReentrantLock进行跟细粒度的控制，生产现场只唤醒消费线程
 *
 */
public class V2_ReentrantLock {

	final int size = 5;
	String[] lst = new String[size];
	int nextIndex = 0;
	private volatile int count = 0;
	
	ReentrantLock lock = new ReentrantLock();
	Condition producer = lock.newCondition();
	Condition consumer = lock.newCondition();
	
	int getCount() {
		return count;
	}

	public void put() {
		try {
			lock.lock();
			// ! 这里一定要用while !
			while (count == size) {
				// 阻塞, 等待有人拿走
				producer.await();
			}
			lst[nextIndex] = String.valueOf(nextIndex);
			count++;
			System.out.println(Thread.currentThread().getName() + " put the value " + lst[nextIndex]);
			// 计算下一次存放的下标
			nextIndex = (nextIndex + 1) % 5;
			consumer.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public synchronized String get() {
		String result = null;
		try {
			lock.lock();
			while (count == 0) {
				// 阻塞,等待有产品时候再拿
				consumer.await();
			}
			int getIndex = (nextIndex + size - count) % size;
			result = lst[getIndex];
			count--;
			System.out.println(Thread.currentThread().getName() + " get the value " + result);
			producer.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return result;
	}

	public static void main(String[] args) {
		V2_ReentrantLock source = new V2_ReentrantLock();
		
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (true) {
					source.get();
				}
			}).start(); 
		}
		
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				while (true) {
					source.put();
				}
			}).start(); 
		}
		
	}

}
