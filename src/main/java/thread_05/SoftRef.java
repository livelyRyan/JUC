package thread_05;

import java.lang.ref.SoftReference;
/**
 * ������
 * ����������������һЩ�����õ����Ǳ���Ķ���
 * ���������ù����ŵĶ�����ϵͳ��Ҫ�����ڴ�����쳣֮ǰ���������Щ�����н����շ�Χ���еڶ��λ��ա�
 * �����λ��ջ�û���㹻���ڴ棬�Ż��׳��ڴ�����쳣��
 * -Xmx20M
 */
public class SoftRef {

	public static void main(String[] args) {
		SoftReference<byte[]> ref = new SoftReference<>(new byte[1024*1024*10]);
		System.out.println(ref.get());
		System.gc();
		System.out.println(ref.get());
		// �ٷ���һ�����飬heap��װ���£���ʱ��ϵͳ���������գ��Ȼ���һ�Σ������������������øɵ�
		@SuppressWarnings("unused")
		byte[] b = new byte[1024*1024*11];
        System.out.println(ref.get());
	}

}
