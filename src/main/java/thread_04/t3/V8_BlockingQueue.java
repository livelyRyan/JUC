package thread_04.t3;

import java.util.concurrent.ArrayBlockingQueue;

public class V8_BlockingQueue {

	static String a = "abcdefgh";
	static String b = "12345678";
	static Thread t1, t2 = null;
	
	public static void main(String[] args) {
		// 用两个阻塞队列作为信号传递
		ArrayBlockingQueue<Object> q1 = new ArrayBlockingQueue<>(1);
		ArrayBlockingQueue<Object> q2 = new ArrayBlockingQueue<>(1);
		
		t1 = new Thread(() ->{
			for (char c : a.toCharArray()) {
				System.out.println(c);
				try {
					// 之所以需要两个queue，是因为put操作不阻塞，t1放完了之后，t1可以take()走数据
					q2.put("ok");
					q1.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t2 = new Thread(() ->{
			for (char c : b.toCharArray()) {
				try {
					q2.take();
					System.out.println(c);
					q1.put("ok");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
	}

}
