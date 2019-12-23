package thread_01;

/*
 * ͬһ������,�߳�a�ڵ��øö����һ��ͬ������ʱ,�߳�b���Ե��øö������һ��ͬ��������?
 * 
 * ������
 * synchronized ������ʵ�����˶���,������������
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
