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
				// ���̱߳���ǰ��������
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
			// 4�����߳���ִ�У�4�������������
			tpe.execute(new Task(i));
		}
		
		System.out.println(tpe.getQueue());
		// �ھŸ��������ˣ�����ݾܾ����Խ��оܾ�.
		tpe.execute(new Task(100));
		// CallerRunsPolicy��ʹtask 100�����߳���������������������System.in.read();������߳����������´�ӡ��䲻��ִ�У��ҳ��򲻻��Լ�����
		System.out.println(tpe.getQueue());
		tpe.shutdown();
	}

}
