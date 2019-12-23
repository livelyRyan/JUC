package thread_05;

public class Test_ThreadLocal {

	@SuppressWarnings("unused")
	private String name;
	Test_ThreadLocal(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		ThreadLocal<Test_ThreadLocal> tl = new ThreadLocal<>();
		// ���߳�������tl��ֵ
		tl.set(new Test_ThreadLocal("zhangsan"));
		
		new Thread(()->{
			// ��ǰ�̶߳��������߳����õ�ֵ
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
