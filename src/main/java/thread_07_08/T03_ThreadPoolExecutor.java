package thread_07_08;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T03_ThreadPoolExecutor {

	static class Task implements Runnable{

		private int index;
		
		Task(int index) {
			this.index = index;
		}
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " get " + index);
			try {
				// 让线程被当前任务阻塞
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
		
		@Override
	    public String toString() {
	        return "Task{" +
	                "index=" + this.index +
	                '}';
	    }
	}
	    
	public static void main(String[] args) {
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, 
				new LinkedBlockingQueue<Runnable>(4), Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.CallerRunsPolicy());
		
		for (int i = 0; i < 8; i++) {
			// 4个用线程在执行，4个在任务队列中
			tpe.execute(new Task(i));
		}
		
		System.out.println(tpe.getQueue());
		// 第九个任务来了，会根据拒绝策略进行拒绝.
		tpe.execute(new Task(100));
		// CallerRunsPolicy会使task 100由主线程运行任务，由于任务中有System.in.read();因此主线程阻塞，以下打印语句不会执行，且程序不会自己结束
		System.out.println(tpe.getQueue());
		tpe.shutdown();
	}

}
