package thread_04.t2;

/**
 *
 *         面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法， 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 */
public class V1_WaitNotify {

	static final int size = 5;
	String[] lst = new String[size];
	int nextIndex = 0;
	private volatile int count = 0;

	int getCount() {
		return count;
	}

	public synchronized void put() {
		// ! 这里一定要用while !
		while (count == size) {
			try {
				// 阻塞, 等待有人拿走
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lst[nextIndex] = String.valueOf(nextIndex);
		count++;
		System.out.println(Thread.currentThread().getName() + " put the value " + lst[nextIndex]);
		// 计算下一次存放的下标
		nextIndex = (nextIndex + 1) % 5;
		this.notifyAll();
	}

	public synchronized String get() {
		while (count == 0) {
			// 阻塞,等待有产品时候再拿
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int getIndex = (nextIndex + size - count) % size;
		count--;
		System.out.println(Thread.currentThread().getName() + " get the value " + lst[getIndex]);
		this.notifyAll();
		return lst[getIndex];
	}

	public static void main(String[] args) {
		V1_WaitNotify source = new V1_WaitNotify();
		
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
