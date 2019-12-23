package thread_07_08;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T12_WorkStealingPool {

	public static void main(String[] args) throws IOException {
		ExecutorService service = Executors.newWorkStealingPool();
		// 获取cpu核心数
		System.out.println(Runtime.getRuntime().availableProcessors());

		// 创建5个线程,由于当前机器cpu核心数为4,因此最多4个线程并行
		service.execute(new R(2000));
		// 拿到该任务的线程会第一个结束任务，然后一定会从某个线程的任务队列中拿第5个任务来执行
		service.execute(new R(1000));
		service.execute(new R(2000));
		service.execute(new R(2000)); //daemon
		service.execute(new R(2000));
		
		//由于产生的是精灵线程（守护线程、后台线程），主线程不阻塞的话，看不到输出
		System.in.read(); 
	}

	static class R implements Runnable {

		int time;
		R(int t) {
			this.time = t;
		}

		@Override
		public void run() {
			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 答应哪个线程来处理了
			System.out.println(time  + " " + Thread.currentThread().getName());
		}
	}
}
