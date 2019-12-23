package thread_06.containers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class T2_Collections {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Collection<Integer> c1 = new ArrayList<>();
		c1.add(1);
		c1.add(2);
		c1.add(3);
		c1.stream().forEach(System.out::println);

		List<Integer> c2 = new ArrayList<>();
		Set<Integer> c3 = new HashSet<>();
		Queue<Integer> c4 = new LinkedList<>();
	}
}
