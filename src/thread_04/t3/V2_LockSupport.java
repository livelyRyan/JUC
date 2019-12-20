package thread_04.t3;

import java.util.concurrent.locks.LockSupport;

/*
 * 题目: 两个线程,一个打印1-9, 一个打印a-z, 打印结果是 a1b2c3...
 * 
*/
public class V2_LockSupport {
	
	static String a = "abcdefgh";
	static String b = "12345678";
	static Thread t1, t2 = null;
	
	public static void main(String[] args) {
		
		t1 = new Thread(() ->{
			for (char c : a.toCharArray()) {
				System.out.println(c);
				LockSupport.unpark(t2);
				LockSupport.park();
			}
		});
		
		t2 = new Thread(() ->{
			for (char c : b.toCharArray()) {
				LockSupport.park();
				System.out.println(c);
				LockSupport.unpark(t1);
			}
		});
		
		t1.start();
		t2.start();
	}

}
