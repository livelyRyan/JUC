package thread_04.t3;

import java.util.concurrent.LinkedTransferQueue;

public class V7_TransferQueue {

	static String a = "abcdefgh";
	static String b = "12345678";
	static Thread t1, t2 = null;
	
	public static void main(String[] args) {

		LinkedTransferQueue<Character> queue = new LinkedTransferQueue<>();
		
		t1 = new Thread(() ->{
			for (char c : a.toCharArray()) {
				try {
					queue.transfer(c);
					System.out.println(queue.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t2 = new Thread(() ->{
			for (char c : b.toCharArray()) {
				try {
					System.out.println(queue.take());
					queue.transfer(c);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
	}
}
