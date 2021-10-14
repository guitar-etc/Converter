package org.py.util;

import java.util.function.*;

public class ThrowableUtil {
	@SuppressWarnings("unchecked")
	public static <T extends RuntimeException, R> R sneakyThrow(Throwable th) throws T {
		throw (T)th;
	}
	
	public static interface ThrowingSupplier<T> extends Supplier<T> {
		@Override
		default T get() {
			try {
				return _get();
			}
			catch (Throwable th) {
				return sneakyThrow(th);
			}
		}
		
		public T _get() throws Throwable;
	}
	
	public static <T> T sneaky(ThrowingSupplier<T> supp) {
		return supp.get();
	}
}
