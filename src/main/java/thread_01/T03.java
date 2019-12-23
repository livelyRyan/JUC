package thread_01;

/*
 * 1. ���̷߳����쳣�󲻻�Ӱ�����߳�
 * 2. ���̷߳����쳣��,���ᱻ�ͷ�
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
