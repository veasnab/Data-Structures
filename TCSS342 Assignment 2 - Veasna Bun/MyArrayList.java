
//Veasna Bun Updated on 04/07/2022
//TCSS342 Assignment 2 Array-List 

public class MyArrayList<T extends Comparable<T>> {
	// Implement ArrayList
	private T[] list;
	private int capacity;
	private int size;

	// added fields for assignment 2
	public long comparisons;

	// ArrayList Constructor
	public MyArrayList() {
		this.capacity = 16;
		this.size = 0;
		this.list = (T[]) new Comparable[capacity];
		this.comparisons = 0;
	}

	// ArrayList Method
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

	public int indexOf(T item) {
		for (int i = 0; i < size; i++) {
			if (list[i].compareTo(item) == 0) {
				return i;
			}
		}
		return -1;
	}

	public T get(int index) {
		if (index > size - 1 || index < 0) {
			return null;
		} else
			return list[index];
	}

	public void set(int index, T item) {
		if (index > size - 1 || index < 0) {
			return;
		}
		list[index] = item;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		if (size != 0) {
			str.append("[");
		}
		for (int i = 0; i < size(); i++) {
			str.append(list[i]);
			str.append(",");
		}
		if (size != 0) {
			str.replace(str.length() - 1, str.length(), "]");
		}
		return str.toString();
	}

	private void resize() {
		capacity = capacity * 2;
		T[] placeHolder = (T[]) new Comparable[capacity];
		for (int i = 0; i < size; i++) {
			placeHolder[i] = list[i];
		}
		list = placeHolder;
	}

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
