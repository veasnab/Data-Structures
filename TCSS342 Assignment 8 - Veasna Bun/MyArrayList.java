
//Veasna Bun Updated on 04/14/2022
//TCSS342 Assignment 2 Array-List 

public class MyArrayList<T extends Comparable<T>> {
	// Implement ArrayList
	private T[] list;
	private int capacity;
	private int size;

	// added fields for assignment 2
	public long comparisons;

	// ArrayList Constructor
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		this.capacity = 16;
		this.size = 0;
		this.list = (T[]) new Comparable[capacity];
		this.comparisons = 0;
	}

	// ArrayList Method
	/**
	 * take a type item and store it at index, resize when capacity is met
	 * 
	 * @param item  take a type item
	 * @param store at the index
	 */
	public void insert(T item, int index) {
		if (index > size || index < 0) {
			return;
		}
		if (size == capacity) {
			resize();
		}
		for (int i = size; i > index; i--) {
			list[i] = list[i - 1];
		}
		list[index] = item;
		size++;
	}

	/**
	 * if the item is between 0 and size remove that item
	 * 
	 * @param index must be between 0 and size
	 * @return the item being remove
	 */
	public T remove(int index) {
		if (index > size - 1 || index < 0) {
			return null;
		} else {
			T removeElement = list[index];
			for (int i = index; i < size - 1; i++) {
				list[i] = list[i + 1];
			}
			size--;
			list[size] = null;
			return removeElement;
		}
	}

	/**
	 * @param item search for the item in O(n)
	 * @return if found return true
	 */
	public boolean contains(T item) {
		comparisons++;
		for (int i = 0; i < size; i++) {
			comparisons++;
			if (list[i].compareTo(item) == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param item search for the item
	 * @return the index number if found else return -1
	 */
	public int indexOf(T item) {
		for (int i = 0; i < size; i++) {
			if (list[i].compareTo(item) == 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * @param index look at the between 0 and size
	 * @return item store at that index
	 */
	public T get(int index) {
		if (index > size - 1 || index < 0) {
			return null;
		} else
			return list[index];
	}

	/**
	 * @param index if the index is between 0 and size
	 * @param item  is updated to the item passed
	 */

	public void set(int index, T item) {
		if (index > size - 1 || index < 0) {
			return;
		}
		list[index] = item;
	}

	/**
	 * @return count of item in the array
	 */
	public int size() {
		return this.size;
	}

	/**
	 * @return true if the size is 0, else return false
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * print the item store between 0 and size in the form of [item, ...]
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (size >= 0) {
			str.append("[");
		}
		for (int i = 0; i < size(); i++) {
			str.append(list[i]);
			str.append(", ");
		}
		if (size == 0) {
			str.append("]");
		}
		if (size > 0) {
			str.replace(str.length() - 2, str.length(), "]");
		}
		return str.toString();
	}

	/**
	 * private method called by the add insert method. Resize when size is == to
	 * capacity by multiple of 2
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		capacity = capacity * 2;
		T[] placeHolder = (T[]) new Comparable[capacity];
		for (int i = 0; i < size; i++) {
			placeHolder[i] = list[i];
		}
		list = placeHolder;
	}

	/**
	 * sort the item in acceding order this is a placeholder may update to a faster
	 * algorithm later default to bubbleSort();
	 */

	public void sort() {
		bubbleSort();
	}

	private void bubbleSort() {
		for (int i = 0; i < size - 1; i++) {
			boolean inversion = false;
			for (int j = 0; j < size - i - 1; j++) {
				if (get(j).compareTo(get(j + 1)) > 0) {
					T temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
					inversion = true;
				}
			}
			if (!inversion)
				break;
		}
	}
}
