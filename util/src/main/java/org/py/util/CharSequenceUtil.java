package org.py.util;

// how can I get StringBuilderUtil to auto import these utility functions?
// All I can think of is inheritance...
public class CharSequenceUtil {
	public static Character getLast(CharSequence cs) {
		return cs.length() > 0 ? cs.charAt(cs.length() - 1) : null;
	}
	
	public static long getCount(CharSequence cs, char ch) {
		return cs.toString().chars()
			.filter(i -> i == ch)
			.count();
	}
}
