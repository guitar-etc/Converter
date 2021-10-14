package org.py.util;

public class StringBuilderUtil {
	
	// I don't like the fact that I return a boxed class...
	// maybe accept Runnable ifFound?
	public static Character getLast(StringBuilder sb) {
		return sb.length() > 0 ? sb.charAt(sb.length() - 1) : null;
	}
	
	public static long getCount(StringBuilder sb, char ch) {
		return sb.toString().chars()
			.filter(i -> i == ch)
			.count();
	}
}
