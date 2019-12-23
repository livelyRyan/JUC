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
				// һ��Ҫȷ��ִ��t1.joinʱ, t1�Ѿ�start��. ����t1.join������t1ȥִ�д�ӡ
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
		
		// thread.start���̴߳�new��״̬��Ϊ����̬�����start�����ĵ���˳���ܾ����̵߳�ִ��˳��
		// �������������߳����ȼ��� ��t2һ����ִ��
		t2.setPriority(10);
		t1.setPriority(1);
		t2.start();
		t1.start();
	}

}
