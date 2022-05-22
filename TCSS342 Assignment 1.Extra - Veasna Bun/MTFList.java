//Veasna Bun 04/14/2022
//TCSS342 Assignment 1 Extra MTFList

public class MTFList<T extends Comparable<T>> {
	private MyLinkedList<T> list;

	public MTFList() {
		list = new MyLinkedList<>();
	}

	public void add(T item) {
		list.addToFirst(item);
	}

	public T remove(T item) {
		T removed = null;
		try {
			list.first();
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
			return removed;
		}

	}

	public T find(T item) {
		T found = remove(item);
		if (found != null)
			add(found);
		return found;
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
