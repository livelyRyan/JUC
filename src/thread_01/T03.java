package thread_01;

/*
 * 1. 子线程发生异常后不会影响主线程
 * 2. 子线程发生异常后,锁会被释放
 * 
*/
public class T03 {

	public static Object o = new Object();
	
	public static class MyThread extends Thread {
		@Override
		public void run() {
			synchronized (o) {
				System.out.println(1/0);
			}
		}
	}
	
	public static void main(String[] args) {

//		System.out.println(1/0);
		
		MyThread thread = new MyThread();
		thread.start();
		
		try {
			Thread.sleep(100);
			synchronized (o) {
				System.out.println("main has got the lock");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("main end");
	}

}
