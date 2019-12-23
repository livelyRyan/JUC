package thread_04.t1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 
*/
public class V6_ {

	List<Integer> lst = Collections.synchronizedList(new ArrayList<>());

	public Boolean add(Integer i) {
		return lst.add(i);
	}

	public int size() {
		return lst.size();
	}

	static Thread t1, t2 = null;
	
	public static void main(String[] args) {
		V6_ lst = new V6_();
		
		t1 = new Thread(() -> {
			System.out.println("added 5, t2 ending");
		});

		t2 = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				lst.add(i);
				System.out.println("add num " + i);
			}
			// 在t2中控制t1的开启, 然后立刻调用t1.join()让t1执行
			t1.start();
			try {
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 5; i < 10; i++) {
				lst.add(i);
				System.out.println("add num " + i);
			}
		});
		
		t2.start();
	}

}
