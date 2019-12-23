package thread_04.t3;

/*
 * 题目: 两个线程,一个打印1-9, 一个打印a-z, 打印结果是 a1b2c3...
 * 
 * 通过volatile index状态和synchronized实现
 * 
*/
public class V5_CAS {
	
	static String a = "abcdefgh";
	static String b = "12345678";
	static Thread t1, t2 = null;
	
	static volatile int index = 0;
	
	public static void main(String[] args) {
		
		t1 = new Thread(() ->{
			for (char c : a.toCharArray()) {
				while( true ) {
					// 因为这里只有两个线程， 因此index == 0 和 index == 1外面不需要加锁
					if ( index == 0 ) {
						System.out.println(c);
						index = 1;
						break;
					}
				}
			}
		});
		
		t2 = new Thread(() ->{
			for (char c : b.toCharArray()) {
				while( true ) {
					if ( index == 1 ) {
						System.out.println(c);
						index = 0;
						break;
					}
				}
			}
		});
		
		t1.start();
		t2.start();
	}

}
