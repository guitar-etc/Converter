package org.py.util;

import java.util.Arrays;

public class LogUtil {
	public static void log(Object... objs) {
		System.out.println(Arrays.deepToString(objs));
	}
}
