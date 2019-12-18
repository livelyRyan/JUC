package thread_01;

import java.util.concurrent.Executors;

public class T01_HowToCreateThread {

	static class MyThread extends Thread{
		@Override
		public void run() {System.out.println("hello my thread!");}
	}
	
	static class MyRun implements Runnable{
		@Override
		public void run() {
			System.out.println("hello my run!");
		}
		
	}
	
	public static void main(String[] args) {
		
		new MyThread().start();
		
		new Thread(new MyRun()).start();
		
		new Thread(() ->{
			System.out.println("hello Lambda!");
		}).start();
		
		// 线程池启动线程
		Executors.newCachedThreadPool();
	}

}
