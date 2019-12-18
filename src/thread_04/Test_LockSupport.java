package thread_04;

import java.util.concurrent.locks.LockSupport;

/*
 * LockSupport, 可以让自己阻塞, 让其他线程唤醒
 * 
 * 以下代码流程:  t2.start -> t2.start -> t2中unpart t1 -> t1中park自己,会发现自己不会阻塞
 * 因为 unpark可以在park之前执行, 这是wait/notify不具备的能力
 * 
*/
public class Test_LockSupport {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(() -> {
			System.out.println("t1 start");
			try {
				// 睡二秒  等待t2 执行完LockSupport.unpark(t1)后运行park
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			LockSupport.park();
			// 如果park两下, 那么就会阻塞, 因为当前只存在一次unpark
//			LockSupport.park();
			System.out.println("t1 end");
		}) ;
		
		Thread t2 = new Thread(() -> {
			System.out.println("t2 start");
			try {
				// 睡一秒  让t1启动起来
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("t2 start unpark t1");
			LockSupport.unpark(t1);
			System.out.println("t2 made t1 unpark");
		}) ;
		
		t2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t1.start();
	}

}
