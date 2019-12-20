package thread_04.t3;

/*
 * 题目: 两个线程,一个打印1-9, 一个打印a-z, 打印结果是 a1b2c3...
 * 线程通信问题
 * 
*/
public class V1_WaitNotify {
	
	static String a = "abcdefgh";
	static String b = "12345678";
	
	public static void main(String[] args) {
		Object lock = new Object();
		new Thread(() ->{
			synchronized (lock) {
				for (char c : a.toCharArray()) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(c);
					lock.notify();
				}
			}
		}).start();
		
		new Thread(() ->{
			synchronized (lock) {
				for (char c : b.toCharArray()) {
					lock.notify();
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(c);
				}
			}
		}).start();
	}

}
