package thread_05;

public class Test_ThreadLocal {

	private String name;
	Test_ThreadLocal(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		ThreadLocal<Test_ThreadLocal> tl = new ThreadLocal<>();
		// 主线程设置了tl的值
		tl.set(new Test_ThreadLocal("zhangsan"));
		
		new Thread(()->{
			// 当前线程读不到主线程设置的值
			System.out.println(tl.get());
		}).start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tl.remove();
	}
}
