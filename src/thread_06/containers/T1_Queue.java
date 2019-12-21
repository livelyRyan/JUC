package thread_06.containers;

import java.util.concurrent.ArrayBlockingQueue;

public class T1_Queue {

	public static void main(String[] args) {
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
		
		queue.add(1);
		queue.add(2);
		// 大小为2的queue,通过add()方法放第三个数据时,会直接抛异常
		queue.add(3);
		queue.add(4);
		System.out.println(queue);
	}

}
