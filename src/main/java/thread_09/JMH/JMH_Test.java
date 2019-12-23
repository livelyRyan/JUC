package thread_09.JMH;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JMH_Test {

	static List<Integer> nums = new ArrayList<>();
	static {
		Random r = new Random();
		for (int i = 0; i < 1000; i++) nums.add(1000000 + r.nextInt(1000000));
	}

	static void foreach() {
		nums.forEach(v->isPrime(v));
	}

	static void parallel() {
		nums.parallelStream().forEach(JMH_Test::isPrime);
	}

	static boolean isPrime(int num) {
		for(int i=2; i<=num/2; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws RunnerException {
        // ʹ��һ����������ִ�в��ԣ�ִ��5��warmup��Ȼ��ִ��5�����
        Options opt = new OptionsBuilder().include(JMH_Test.class.getSimpleName()).forks(1).warmupIterations(5)
                .measurementIterations(5).build();
        new Runner(opt).run();
    }
}
