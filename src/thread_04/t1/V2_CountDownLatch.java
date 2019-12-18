package thread_04.t1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/*
 * ʵ��һ���������ṩ����������add��size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�������������5��ʱ���߳�2������ʾ������
 * 
*/
public class V2_CountDownLatch {

	List<Integer> lst = Collections.synchronizedList(new ArrayList<>());

	public Boolean add(Integer i) {
		return lst.add(i);
	}

	public int size() {
		return lst.size();
	}

	public static void main(String[] args) {
		V2_CountDownLatch lst = new V2_CountDownLatch();

		CountDownLatch latch = new CountDownLatch(1);
		// ��add�̼߳ӵ�5����еȴ�. ������߳�ʱ���ӡ
		CountDownLatch latch2 = new CountDownLatch(1);
		
		new Thread(() -> {
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("added 5, t2 ending");
			latch2.countDown();
		}).start();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				lst.add(i);
				System.out.println("add num " + i);
				if (lst.size() == 5) {
					latch.countDown();
					try {
						latch2.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
