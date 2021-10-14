package org.py.util;

import java.lang.reflect.Array;

public class ArrayUtil {
	public static int getTotalCount(Object arr) {
		int sum = 0;
		if (arr != null) {
			if (!arr.getClass().isArray()) {
				throw new IllegalArgumentException("Please use arrays only: " + arr.getClass());
			}
			Class<?> elemType = arr.getClass().getComponentType();
			int length = Array.getLength(arr);
			if (!elemType.isArray()) {
				sum = length;
			}
			else {
				for (int i = 0; i < length; i++) {
					Object elem = Array.get(arr, i);
					sum += getTotalCount(elem);
				}
			}
		}
		return sum;
	}
}
