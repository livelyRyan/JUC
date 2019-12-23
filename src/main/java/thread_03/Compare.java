package thread_03;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class Compare {

	static long count1 = 0L;
	static AtomicLong count2 = new AtomicLong(0L);
	static LongAdder count3 = new LongAdder();
	
	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[1000];
		
		Object lock = new Object();
		for(int i=0; i<threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for(int k=0; k<100000; k++) {
						synchronized (lock) {
							 count1++;
						}
					}
				}
			});
		}
		long start = System.currentTimeMillis();
		for (Thread thread : threads)  thread.start();
		for (Thread thread : threads) thread.join();
		long end = System.currentTimeMillis();
		System.out.println("count :" + count1 + " sync : " + (end - start));
		
		for(int i=0; i<threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for(int k=0; k<100000; k++) count2.getAndIncrement();
				}
			});
		}
		start = System.currentTimeMillis();
		for (Thread thread : threads)  thread.start();
		for (Thread thread : threads)  thread.join();
		
		end = System.currentTimeMillis();
		System.out.println("count :" + count2 + " atomic : " + (end - start));
		
		
		for(int i=0; i<threads.length; i++) {
            threads[i] = new Thread(() -> {
					for(int k=0; k<100000; k++) count3.increment();
				});
		}
		start = System.currentTimeMillis();
		for (Thread thread : threads)  thread.start();
		for (Thread thread : threads)  thread.join();
		
		end = System.currentTimeMillis();
		System.out.println("count :" + count3 + " LongAddr : " + (end - start));
		
	}

}
