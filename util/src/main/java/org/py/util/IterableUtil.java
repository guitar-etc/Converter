package org.py.util;

public class IterableUtil {
	public static int getTotalCount(Iterable<?> coll) {
		int sum = 0;
		if (coll != null) {
			for (Object o : coll) {
				if (o instanceof Iterable) {
					Iterable<?> co = (Iterable<?>)o;
					int count = getTotalCount(co);
					sum += count;
				}
				else {
					sum++;
				}
			}
		}
		return sum;
	}
}
