package thread_04.t1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * ʵ��һ���������ṩ����������add��size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�������������5��ʱ���߳�2������ʾ������
 * 
 *      ���͵������߳�ͨ������
 * 
*/
public class V1_WaitNotify {

	List<Integer> lst = Collections.synchronizedList(new ArrayList<>());

	public Boolean add(Integer i) {
		return lst.add(i);
	}

	public int size() {
		return lst.size();
	}

	public static void main(String[] args) {
		V1_WaitNotify lst = new V1_WaitNotify();

		Object lock = new Object();

		new Thread(() -> {
			synchronized (lock) { 
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("added 5, t2 ending");
				lock.notify();
			}
		}).start();

		// ˯һ��,��ֹadd�̵߳�notify�����ڼ����߳�wait֮ǰִ����
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				synchronized (lock) {
					lst.add(i);
					System.out.println("add num " + i);
					if (lst.size() == 5) {
						// notify�����ͷ���
						lock.notify();
						try {
							// wait �õ�ǰ�߳��ͷų���, ���Ҹ�����߳�ִ��ʱ��
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}

}
