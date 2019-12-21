package thread_06.syncContainers;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T04_ConcurrentQueue {
	public static void main(String[] args) {
		Queue<String> strs = new ConcurrentLinkedQueue<>();
		
		for(int i=0; i<10; i++) {
			strs.offer("a" + i);  //add
		}
		System.out.println(strs);
		
		System.out.println(strs.size());
		
		// poll 取出并移除
		System.out.println(strs.poll());
		System.out.println(strs.size());
		
		// peek 只取出数据
		System.out.println(strs.peek());
		System.out.println(strs.size());
		
		//双端队列Deque
	}
}
