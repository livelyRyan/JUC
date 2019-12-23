package thread_03;

import java.util.concurrent.CountDownLatch;

public class Test_CountDownLatch {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(10);
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("start countDown");
				latch.countDown(); // 该方法本身就是原子性的, 线程安全的
				System.out.println("end countDown");
			}).start();
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("all threads has ended!");
	}

}
