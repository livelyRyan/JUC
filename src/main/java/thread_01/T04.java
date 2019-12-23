package thread_01;

/*
 * 
 * synchronized(null)»á±¨´í
*/
public class T04 {

	@SuppressWarnings("null")
	public static void main(String[] args) {

		Object o = null;
		
		synchronized (o) {
			System.out.println("aaa");
		}
		System.out.println("main end");
	}

}
