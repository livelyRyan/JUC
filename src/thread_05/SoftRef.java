package thread_05;

import java.lang.ref.SoftReference;
/**
 * 软引用
 * 软引用是用来描述一些还有用但并非必须的对象。
 * 对于软引用关联着的对象，在系统将要发生内存溢出异常之前，将会把这些对象列进回收范围进行第二次回收。
 * 如果这次回收还没有足够的内存，才会抛出内存溢出异常。
 * -Xmx20M
 */
public class SoftRef {

	public static void main(String[] args) {
		SoftReference<byte[]> ref = new SoftReference<>(new byte[1024*1024*10]);
		System.out.println(ref.get());
		System.gc();
		System.out.println(ref.get());
		// 再分配一个数组，heap将装不下，这时候系统会垃圾回收，先回收一次，如果不够，会把软引用干掉
		@SuppressWarnings("unused")
		byte[] b = new byte[1024*1024*11];
        System.out.println(ref.get());
	}

}
