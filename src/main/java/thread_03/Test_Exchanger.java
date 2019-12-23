package thread_03;

import java.util.concurrent.Exchanger;

public class Test_Exchanger {

	public static void main(String[] args) {
		Exchanger<String> exchanger = new Exchanger<String>();
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				try {
					String newName = exchanger.exchange(Thread.currentThread().getName());
					System.out.println(Thread.currentThread().getName() + " get new name " + newName);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}

}
