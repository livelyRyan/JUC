package thread_05;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/*
 *     һ�������Ƿ��������õĴ��ڣ���ȫ�����������ʱ�乹��Ӱ�죬
 *     Ҳ�޷�ͨ������������ȡһ�������ʵ����
 *     Ϊһ���������������ù�����ΨһĿ�ľ���������������ռ�������ʱ�յ�һ��ϵͳ֪ͨ��
 *     �����ú������öԹ�������Ļ��ն��������Ӱ�죬���ֻ�������û��������ù����Ŷ���
 *     ��ô�������ͻᱻ���ա����ǵĲ�֮ͬ�����������õ�get�����������õ�get����ʼ�շ���null,
 *     �����ÿ���ʹ��ReferenceQueue,�����ñ������ReferenceQueueʹ�á�
 *
 * jdk��ֱ���ڴ�Ļ��վ��õ������ã�����jvm�Զ��ڴ����ķ�Χ�Ƕ��ڴ棬
 *     ��ֱ���ڴ����ڶ��ڴ�֮�⣨��ʵ���ڴ�ӳ���ļ�������ȥ��������ڴ�ռ����ظ����
 *     ����ֱ���ڴ�ķ���ͻ��ն�����Unsafe��ȥ������java������һ��ֱ���ڴ�֮��
 *     ���ڶ��ڴ����һ�����󱣴���������ڴ�����ã�
 *     ������������ռ�������һ��������󱻻��գ�
 *     ��Ӧ���û��̻߳��յ�֪ͨ����ֱ���ڴ������������
 *
 *     ��ʵ�ϣ���������һ������Ҫ����;���������������ڴ���ͷţ�
 * DirectByteBuffer����ͨ����������ʵ�ֶ����ڴ���ͷŵġ�
 * 
*/
public class PhantomRef {

	private static final List<Object> LIST = new LinkedList<>();
	private static final ReferenceQueue<Object> QUEUE = new ReferenceQueue<>();

	public static void main(String[] args) {
		PhantomReference<Object> phantomReference = new PhantomReference<>(new Object(), QUEUE);

		// ���߳����Ͷ��ڴ棬ʹ�������󴥷�gc
		new Thread(() -> {
			while (true) {
				LIST.add(new byte[1024 * 1024]);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
				System.out.println(phantomReference.get());
			}
		}).start();

		// ����queue�仯����һʱ�䷢�������ñ�����
		new Thread(() -> {
			while (true) {
				Reference<? extends Object> poll = QUEUE.poll();
				if (poll != null) {
					System.out.println("--- �����ö���jvm������ ---- " + poll);
				}
			}
		}).start();
	}

}
