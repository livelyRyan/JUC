package thread_07;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T10_SchedulerThreadPool {

	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
		// scheduleAtFixedRate(runnable, 初始延迟时间, 任务循环周期, 时间单位)
		service.scheduleAtFixedRate(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		}, 0, 500, TimeUnit.MILLISECONDS);
	}

}
