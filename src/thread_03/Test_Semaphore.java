package thread_03;

import java.util.concurrent.Semaphore;

public class Test_Semaphore {

	static Semaphore semaphore = new Semaphore(2, false);
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 4; i++) {
			new Thread(() -> {
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + " start run");
	                Thread.sleep(1000);
	                System.out.println(Thread.currentThread().getName() + " end run");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					// 释放许可证, 供其他线程使用
					semaphore.release();
				}
			}).start();
		}
	}

}
