package thread_03;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Test_CyclicBarrier {

	private static int total = 5;
	
	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(total, () -> System.out.println("拼单人数已足够, 完成拼单") ) ;
		
		for ( int i = 0; i < total * 2; i++ ) {
			new Thread(() -> {
				System.out.println( Thread.currentThread().getName() + "来拼单了!");
				try {
					barrier.await();
					barrier.getNumberWaiting();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
				System.out.println("耶, 拼单成功!");
			}).start(); 
		}
	}

}
