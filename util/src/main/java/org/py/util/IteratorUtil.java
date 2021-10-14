package org.py.util;

import java.util.*;
import java.util.Map.*;

public class IteratorUtil {
	public static <K, V> Iterator<Map.Entry<K, V>> getIterator(Dictionary<K, V> dict) {
		return new Iterator<Map.Entry<K, V>>() {
			
			private Enumeration<K> keys = dict.keys();
			
			@Override
			public boolean hasNext() {
				return keys.hasMoreElements();
			}

			@Override
			public Entry<K, V> next() {
				K key = keys.nextElement();
				// don't cache dict.get(key) to copy the default behavior where Entry and the backing map are connected.
//				V value = dict.get(key);
				return new Map.Entry<K, V>() {
					@Override
					public K getKey() {
						return key;
					}

					@Override
					public V getValue() {
						return dict.get(key);
					}

					@Override
					public V setValue(V value) {
						return dict.put(key, value);
					}
				};
			}
		};
	}
	
}