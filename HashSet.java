/**
 * 
 */
package com.myImpl.hashset;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author contactbarun70
 *
 */
public class HashSet<E> {

	private Object[] array;

	private int size;

	private final int max_size;

	public HashSet(final Collection<? extends E> c) {
		if (c == null) {
			throw new NullPointerException("Can not create HashSet from null.");
		}
		array = c.toArray();
		size = c.size();
		max_size = c.size();
	}

	public HashSet() {
		array = new Object[10];
		this.size = 0;
		this.max_size = 10;
	}

	public boolean add(final E e) {
		if (array != null) {
			for (int i = 0; i < size; i++) {
				if ((e == null && e == array[i]) || e.equals(array[i])) {
					return false;
				}
			}
			if (size == max_size) {
				increaseSize();
			}
			array[size] = e;
			size++;
			return true;
		}
		return false;
	}

	public boolean addAll(final Collection<? extends E> c) {
		if (c == null) {
			throw new NullPointerException("Specified collection is null.");
		}
		boolean isUpdated = false;
		for (final Object o : c) {
			E e = (E) o;
			if (this.add(e)) {
				isUpdated = true;
			}
		}
		return isUpdated;
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			array[i] = null;
		}
		size = 0;
	}

	public boolean contains(final Object o) {
		for (int i = 0; i < size; i++) {
			if ((o == null && o == array[i]) || o.equals(array[i])) {
				return true;
			}
		}
		return false;
	}

	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		}
		return false;
	}

	public Iterator<E> iterator() {
		return new Iterator<E>() {

			int counter = 0;

			@Override
			public boolean hasNext() {
				if (counter < size)
					return true;
				return false;
			}

			@Override
			public E next() {
				return (E) array[counter++];
			}
		};
	}

	public boolean remove(final Object o) {
		if (size == 0) {
			return false;
		}
		boolean found = false;
		for (int i = 0; i < size; i++) {
			if ((o == null && o == array[i]) || o.equals(array[i])) {
				found = true;
				size--;
			}
			if (found) {
				array[i] = array[i + 1];
			}
		}
		return found;
	}

	public boolean removeAll(final Collection<?> c) {
		if (c == null) {
			throw new NullPointerException("Specified collection is null.");
		}
		boolean removed = false;
		for (final Object o : c) {
			if (this.remove(o)) {
				removed = true;
			}
		}
		return removed;
	}

	public boolean retainAll(final Collection<?> c) {
		if (c == null) {
			throw new NullPointerException("Specified collection is null.");
		}
		boolean retained = false;
		for (int i = 0; i < size; i++) {
			if(!c.contains(this.array[i])){
				retained = this.remove(this.array[i]);
			}
		}
		return retained;
	}

	public int size() {
		return this.size;
	}

	public Object[] toArray() {
		return Arrays.copyOf(this.array, this.size);
	}

	public <T> T[] toArray(final T[] a) {
		return (T[]) Arrays.copyOf(this.array, this.size);
	}

	private void increaseSize() {
		this.array = Arrays.copyOf(this.array, this.size * 2);
	}

	public boolean containsAll(final Collection<?> c) {
		if (c == null) {
			throw new NullPointerException("Specified collection is null.");
		}
		for (final Object o : c) {
			final E e = (E) o;
			boolean isPresent = false;
			for (int i = 0; i < size; i++) {
				if (e.equals(array[i]))
					isPresent = true;
			}
			if (!isPresent) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		for (int i = 0; i < size; i++) {
			buffer.append(array[i]);
			buffer.append(",");
		}
		buffer.deleteCharAt(buffer.lastIndexOf(","));
		buffer.append("]");
		return buffer.toString();
	}

}
