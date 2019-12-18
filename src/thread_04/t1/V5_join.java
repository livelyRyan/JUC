package thread_04.t1;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 
*/
public class V5_join {

	List<Integer> lst = Collections.synchronizedList(new ArrayList<>());

	public Boolean add(Integer i) {
		return lst.add(i);
	}

	public int size() {
		return lst.size();
	}

	static Thread t1, t2 = null;
	
	public static void main(String[] args) {
		V5_join lst = new V5_join();
		
		t1 = new Thread(() -> {
			System.out.println("added 5, t2 ending");
		});

		t2 = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				lst.add(i);
				System.out.println("add num " + i);
			}
			try {
				// 一定要确保执行t1.join时, t1已经start了. 否则t1.join不会让t1去执行打印
				while ( t1.getState() == State.NEW ) {
					Thread.sleep(1);
				}
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 5; i < 10; i++) {
				lst.add(i);
				System.out.println("add num " + i);
			}
		});
		
		// thread.start后，线程从new的状态变为就绪态，因此start方法的调用顺序不能决定线程的执行顺序
		// 所以这里设置线程优先级， 让t2一定先执行
		t2.setPriority(10);
		t1.setPriority(1);
		t2.start();
		t1.start();
	}

}
