package thread_04.t1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/*
 * ʵ��һ���������ṩ����������add��size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�������������5��ʱ���߳�2������ʾ������
 * 
*/
public class V3_LockSupport {

	List<Integer> lst = Collections.synchronizedList(new ArrayList<>());

	public Boolean add(Integer i) {
		return lst.add(i);
	}

	public int size() {
		return lst.size();
	}

	static Thread t1, t2 = null;
	
	public static void main(String[] args) {
		V3_LockSupport lst = new V3_LockSupport();
		
		t1 = new Thread(() -> {
			LockSupport.park();
			System.out.println("added 5, t2 ending");
			LockSupport.unpark(t2);
		});

		t2 = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				lst.add(i);
				System.out.println("add num " + i);
				if (lst.size() == 5) {
					LockSupport.unpark(t1);
					LockSupport.park();
				}
			}
		});
		
		t1.start();
		t2.start();
	}

}
