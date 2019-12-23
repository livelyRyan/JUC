package thread_07;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/*
 * 
 * ʹ��ForkJoinPool����MAX_NUM�����ֵ��ܺ�
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
				// ����ǰ�߳̽���fork����
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
				
				// �Ƚ�����fork,Ȼ�� �ٰ�����join����
				t1.fork();
				t2.fork();
				return t1.join() + t2.join();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
//		ForkJoinPool fjp = new ForkJoinPool();
//		AddTask task = new AddTask(0, nums.length);
//		fjp.execute(task); // �첽����
//		System.in.read();  // ����main�߳�,ʹfjp�е��߳�ִ������
		
		ForkJoinPool fjp = new ForkJoinPool();
		AddTaskRet task = new AddTaskRet(0, nums.length);
		fjp.execute(task);
		Long result = task.join(); // �÷�������
		System.out.println(result);
		
	}

}
