package thread_05;

import java.lang.ref.WeakReference;

/*
 * ����gc�ͱ�����
*/
public class WeakRef {

	public static void main(String[] args) {
		WeakReference<Object> ref = new WeakReference<>(new Object());

		System.out.println(ref.get());
		System.gc();
		System.out.println(ref.get());
		
        ThreadLocal<Object> tl = new ThreadLocal<>();
        tl.set(new Object());
        tl.remove();
		
	}

}
