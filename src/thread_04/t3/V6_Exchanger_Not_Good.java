package thread_04.t3;

import java.util.concurrent.Exchanger;

/*
 * exchanger需要执行sleep，所以也不是好的选择
*/
public class V6_Exchanger_Not_Good {

	static String a = "abcdefgh";
	static String b = "12345678";
	static Thread t1, t2 = null;
	
	public static void main(String[] args) {
		Exchanger<Character> exchanger = new Exchanger<>();
		
		t1 = new Thread(() ->{
			for (char c : a.toCharArray()) {
				Character value = null;
				try {
					value = exchanger.exchange(c);
					// 拿到数字线程给的值后,睡1毫秒,使程序先打印字母
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(value);
			}
		});
		
		t2 = new Thread(() ->{
			for (char c : b.toCharArray()) {
				Character value = null;
				try {
					value = exchanger.exchange(c);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(value);
			}
		});
		
		t1.start();
		t2.start();
	}

}
