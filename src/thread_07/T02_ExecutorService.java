/**
 * ExecutorService������Executor����������,��������Executor
 * ��ʶsubmit��������չ��execute����������һ������ֵ
 * 
 * ��ʶCallable����Runnable��������չ
 * ��Callable�ĵ��ã������з���ֵ
 * 
 */
package thread_07;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class T02_ExecutorService  {
    public static void main(String[] args) {
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "I'm callable";
			}
		};
    	
    	ExecutorService service = Executors.newCachedThreadPool();
    	Future<String> future = service.submit(callable); // �첽,����Callable
    	try {
			System.out.println(future.get()); // ����
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} 
    	service.shutdown();
    	
    }
}
