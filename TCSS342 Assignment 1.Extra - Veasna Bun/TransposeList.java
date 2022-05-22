//Veasna Bun 04/14/2022
//TCSS342 Assignment 1 Extra TransposeList
public class TransposeList<T extends Comparable<T>> {
	private MyLinkedList<T> list;

	public TransposeList() {
		list = new MyLinkedList<>();
	}

	public void add(T item) {
		list.addBefore(item);
	}

	public T remove(T item) {
		try {
			list.first();
			T removed = null;
			boolean removable = false;
			while (list.current() != null && removable == false) {
				if (list.current().compareTo(item) == 0) {
					removed = list.remove();
					removable = true;
				}
				if (removable)
					break;
				list.next();
			}
			return removed;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public T find(T item) {
		T foundItem = null;
		try {
			list.first();
			boolean found = false;
			while (list.current() != null && found == false) {
				if (list.current().compareTo(item) == 0) {
					foundItem = list.current();
					found = true;
					list.swapWithPrevious();
				}
				if (found)
					break;
				list.next();
			}
			return foundItem;
		} catch (NullPointerException e) {
			return foundItem;
		}
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
