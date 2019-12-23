package thread_01;

/*
 * 同一个对象,线程a在调用该对象的一个同步方法时,线程b可以调用该对象的另一个同步方法吗?
 * 
 * 不可以
 * synchronized 方法其实是锁了对象,而不是锁方法
 * 
*/
public class T05 {

	public synchronized void work() {
		System.out.println("i'm working");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("work finished");
	} 
	
	
	public synchronized void eat() {
		System.out.println("i'm eating");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("eat finished");
	} 
	
	
	public static void main(String[] args) {
		T05 o = new T05();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				o.work();
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				o.eat();
			}
		}).start();
		
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main end");
	}

}
