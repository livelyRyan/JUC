/**
 * ExecutorService定义了Executor的生命周期,用来管理Executor
 * 认识submit方法，扩展了execute方法，具有一个返回值
 * 
 * 认识Callable，对Runnable进行了扩展
 * 对Callable的调用，可以有返回值
 * 
 */
package thread_07_08;

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
    	Future<String> future = service.submit(callable); // 异步,增加Callable
    	try {
			System.out.println(future.get()); // 阻塞
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} 
    	service.shutdown();
    	
    }
}
