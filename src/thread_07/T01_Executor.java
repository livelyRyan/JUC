package thread_07;

import java.util.concurrent.Executor;

/*
 * Executor执行器接口,只有一个execute方法用来执行给定的Runnable
*/
public class T01_Executor implements Executor {

	public static void main(String[] args) {
		new T01_Executor().execute(()->{System.out.println("executor running");});
	}

	@Override
	public void execute(Runnable command) {
		command.run();
	}

}
