package thread_05;

public class StrongRef {

	public static void main(String[] args) {
		Object obj = new Object();
		System.gc();
		System.out.println(obj);
		obj = null;
		System.gc();
		System.out.println(obj);
	}

}
