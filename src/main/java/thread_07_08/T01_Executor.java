package thread_07_08;

import java.util.concurrent.Executor;

/*
 * Executorִ�����ӿ�,ֻ��һ��execute��������ִ�и�����Runnable
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
