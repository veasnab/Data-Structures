//Veasna Bun updated on 04/07/2022
//TCSS342 Assignment 2 MyOrderList 
public class MyOrderedList<T extends Comparable<T>> {
	// Implement MyOrderList
	private MyArrayList<T> list;
	public long comparisons;

	// MyOrderList Constructor
	public MyOrderedList() {
		list = new MyArrayList<>();
		this.comparisons = 0;
	}

	// MyOrderedList Method
	public void add(T item) {
		comparisons++;
		list.insert(item, size());
		for (int i = size() - 1; i > 0 && item.compareTo(list.get(i - 1)) < 0; i--) {
			comparisons++;
			list.set(i, list.get(i - 1));
			list.set(i - 1, item);
		}
	}

	public T remove(T item) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).compareTo(item) == 0) {
				return list.remove(i);
			}
		}
		return null;
	}

	public boolean binarySearch(T item) {
		return binarySearch(item, 0, size());
	}

	private boolean binarySearch(T item, int start, int finish) {
		if (finish - start > 0) {
			int mid = ((start + finish) / 2);
			if (list.get(mid).compareTo(item) == 0) {
				return true;
			} else if (list.get(mid).compareTo(item) > 0) {
				comparisons++;
				return binarySearch(item, start, mid);
			} else if (list.get(mid).compareTo(item) < 0) {
				comparisons++;
				return binarySearch(item, mid + 1, finish);
			}

		}
		comparisons++;
		return false;
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public String toString() {
		return list.toString();
	}
}
