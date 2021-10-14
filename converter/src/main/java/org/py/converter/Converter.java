package org.py.converter;

import static org.py.util.ThrowableUtil.*;

import java.io.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.stream.*;

import org.py.util.*;

public class Converter {
	/** Probably want to allow additional configuration like delimeter
	 * @param sc
	 * @return
	 */
	public static Stream<String> toStream(Scanner sc) {
		return toStream(toIterator(sc));
	}
	
	public static <T> Stream<T> toStream(Optional<T> opt) {
		return opt.map(Stream::of).orElse(Stream.empty());
	}
	
	public static <T> Stream<T> toStream(Enumeration<T> enu) {
		return toStream(toIterator(enu));
	}
	
	public static <K, V> Stream<Map.Entry<K, V>> toStream(Dictionary<K, V> dict) {
		return toStream(IteratorUtil.getIterator(dict));
	}
	
	public static <K, V> Stream<Map.Entry<K, V>> toStream(Map<K, V> map) {
		return toStream(map.entrySet());
//		return EntryStream.of(map);
	}
	
	public static <T> Stream<T> toStream(Collection<T> coll) {
		return coll.stream();
	}
	
	public static DoubleStream toStream(double[] array) {
        return Arrays.stream(array);
    }
	
	public static LongStream toStream(long[] array) {
        return Arrays.stream(array);
    }
	
	public static IntStream toStream(int[] array) {
        return Arrays.stream(array);
    }
	
	public static <T> Stream<T> toStream(T[] array) {
		return Arrays.stream(array);
	}
	
	@SafeVarargs
	public static <T> Stream<T> toStreamFromVarargs(T... values) {
		return toStream(values);
	}
	
	public static IntStream toStream(CharSequence str) {
		return str.chars();
	}
		
	public static <T> Stream<T> toStream(Spliterator<T> itr) {
		return StreamSupport.stream(
				itr
                , false);
	}
	
	public static <T> Stream<T> toStream(Iterable<T> itr) {
		return toStream(itr.spliterator());
	}
	
	public static <T> Stream<T> toStream(Iterator<T> itr) {
		return toStream(Spliterators.spliteratorUnknownSize(
                		itr,
                        Spliterator.ORDERED));
	}
	
    public static <T> List<T> toList(Iterable<T> itr) {
        return toList(toStream(itr));
    }

    public static <T> List<T> toList(Stream<T> str) {
        return str.collect(Collectors.toList());
    }
    
    public static <T> Iterator<T> toIterator(Enumeration<T> enu) {
		return new Iterator<T>() {
			@Override
            public T next() {
                return enu.nextElement();
            }
			@Override
            public boolean hasNext() {
                return enu.hasMoreElements();
            }
        };
	}
	
	public static Iterator<String> toIterator(Scanner sc) {
		return new Iterator<String>() {

			@Override
			public boolean hasNext() {
				return sc.hasNext();
			}

			@Override
			public String next() {
				return sc.next();
			}
			
		};
	}
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	// Leave this to compare with simpler syntax.
	public static Date toDate(String dateString) {
		Date date = null;
		if (null != dateString) {
			try {
				date = DATE_FORMAT.parse(dateString);
			}
			catch (ParseException pe) {
				ThrowableUtil.sneakyThrow(pe);
			}
		}
		
		return date;
	}
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static LocalDate toLocalDate(String dateString) {
		return sneaky(() -> LocalDate.parse(dateString, formatter));
	}
	
	public static String toString(InputStream is) {
		return sneaky(() -> new String(is.readAllBytes()));
	}
	
	public static String toString(InputStream is, int maxBytes) {
		return sneaky(() -> new String(is.readNBytes(maxBytes)));
	}
}
