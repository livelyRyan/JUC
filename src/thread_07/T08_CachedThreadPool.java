package thread_07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T08_CachedThreadPool {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println(service);
		for (int i = 0; i < 2; i++) {
			service.execute(() -> {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		System.out.println(service);
		// CachedThreadPool中线程的最大限制时间是60s，因此等待灭活后，再次打印service信息
		TimeUnit.SECONDS.sleep(61);
		
		System.out.println(service);
	}
}
