package thread_06.containers;

import java.util.concurrent.ArrayBlockingQueue;

public class T1_Queue {

	public static void main(String[] args) {
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
		
		queue.add(1);
		queue.add(2);
		// ��СΪ2��queue,ͨ��add()�����ŵ���������ʱ,��ֱ�����쳣
		queue.add(3);
		queue.add(4);
		System.out.println(queue);
	}

}
