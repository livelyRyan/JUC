package thread_07;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/*
 * 
 * 使用ForkJoinPool计算MAX_NUM个数字的总和
 * 
*/
public class T11_ForkJoinPool {

	static int[] nums = new int[1000000];
	static final int MAX_NUM = 50000;
	static Random r = new Random();
	
	static {
		for(int i=0; i<nums.length; i++) {
			nums[i] = r.nextInt(100);
		}
		// stream api
		System.out.println("---" + Arrays.stream(nums).sum());
	}
	
	static class AddTask extends RecursiveAction{
		private static final long serialVersionUID = 1L;
		int start, end;
		AddTask(int s, int e) {
			start = s;
			end = e;
		}
		@Override
		protected void compute() {
			if(end-start <= MAX_NUM) {
				long sum = 0L;
				for(int i=start; i<end; i++) sum += nums[i];
				System.out.println("from:" + start + " to:" + end + " = " + sum);
			} else {
				int middle = start + (end-start)/2;
				AddTask t1 = new AddTask(start, middle);
				AddTask t2 = new AddTask(middle, end);
				// 将当前线程进行fork操作
				t1.fork();
				t2.fork();
			}
		}
	}
	
	static class AddTaskRet extends RecursiveTask<Long> {
		private static final long serialVersionUID = 1L;
		int start, end;
		AddTaskRet(int s, int e) {
			start = s;
			end = e;
		}
		@Override
		protected Long compute() {
			if ( end -start <= MAX_NUM ) {
				long sum = 0L;
				for(int i=start; i<end; i++) sum += nums[i];
				return sum;
			} else {
				int middle = start + (end-start)/2;
				AddTaskRet t1 = new AddTaskRet(start, middle);
				AddTaskRet t2 = new AddTaskRet(middle, end);
				
				// 先将任务fork,然后 再把任务join汇总
				t1.fork();
				t2.fork();
				return t1.join() + t2.join();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
//		ForkJoinPool fjp = new ForkJoinPool();
//		AddTask task = new AddTask(0, nums.length);
//		fjp.execute(task); // 异步方法
//		System.in.read();  // 阻塞main线程,使fjp中的线程执行任务
		
		ForkJoinPool fjp = new ForkJoinPool();
		AddTaskRet task = new AddTaskRet(0, nums.length);
		fjp.execute(task);
		Long result = task.join(); // 该方法阻塞
		System.out.println(result);
		
	}

}
