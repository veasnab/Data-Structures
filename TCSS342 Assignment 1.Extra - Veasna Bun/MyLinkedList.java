
//Veasna Bun updated on 04/07/2022
//TCSS342 Assignment 2 LinkedList 
public class MyLinkedList<T extends Comparable<T>> {

	// Implement Node
	private static class Node<T> {
		public T item;
		public Node<T> next;

		// Node Constructor
		public Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}

		public String toString() {
			return item.toString();
		}
	}

	// Implement LinkedList
	private Node<T> first, current, previous, last;
	private int size;
	// added method for assignment 2
	public long comparisons;

	// LinkedList Constructor
	public MyLinkedList() {
		this.first = this.current = this.previous = this.last = null;
		this.size = 0;
		this.comparisons = 0;
	}

	// LinkedList Method
	public void addBefore(T item) {
		Node<T> addItem = new Node(item, null);
		if (first == null) {
			first = last = addItem;
			previous = new Node(null, first);
			current = new Node(first, null);
			current = current.next;
		} else if (current == null) {
			previous = previous.next;
			current = previous.next = addItem;
			current = current.next;
		} else if (current != null && previous != null) {
			previous.next = addItem;
			addItem.next = current;
			previous = previous.next;
		} else if (previous == null) {
			addItem.next = current;
			first = current = addItem;
		}
		size++;
	}

	public void addAfter(T item) {
		Node<T> addItem = new Node(item, null);
		if (current == null) {
			return;
		}
		if (current.next == null) {
			current.next = last = addItem;
		} else {
			Node<T> temp = current.next;
			current.next = addItem;
			current.next.next = temp;
		}
		size++;
	}

	public T remove() {
		if (current == null) {
			return null;
		}
		T removeNode = current.item;
		if (previous != null) {
			current = previous;
			previous.next = current.next.next;
			current = current.next;
		}
		if (previous == null) {
			current = first;
			first = current.next;
			current = first;
		}
		size--;
		return removeNode;
	}

	public T current() {
		if (current == null) {
			return null;
		}
		return current.item;
	}

	public T first() {
		current = first;
		previous = null;
		if (current == null) {
			return null;
		}
		return (T) current.item;
	}

	public T next() {
		if (current == null) {
			return null;
		}
		T temp = null;
		if (current.next != null) {
			previous = current;
			current = current.next;
			temp = current.item;
		} else {
			current = current.next;
		}
		return temp;
	}

	public boolean contains(T item) {
		comparisons++;
		current = first;
		while (current != null) {
			comparisons++;
			if (current.item.compareTo(item) == 0) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		Node<T> walker = first;
		if (size >= 0) {
			str.append("[");
		}
		while (walker != null) {
			str.append(walker.item);
			walker = walker.next;
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

// ===========added helper method for assignment 1 extra
	public void addToFirst(T item) {
		Node<T> addItem = new Node(item, null);
		if (isEmpty()) {
			first = last = current = addItem;
		} else {
			addItem.next = first;
			first = addItem;
		}
		size++;
	}

	public void addToLast(T item) {
		Node<T> addItem = new Node(item, null);
		if (isEmpty()) {
			last = first = addItem;
		} else {
			last.next = addItem;
			last = addItem;
		}
		size++;
	}

	public void swapWithPrevious() {
		T temp = previous.item;
		previous.item = current.item;
		current.item = temp;
	}

// ===========added helper method for assignment 1 extra (END) ==========//
// =========== added method for assignment 2
	public void sort() {
		bubbleSort();
	}

	private void bubbleSort() {
		if (first != null) {
			boolean status = false;
			do {
				current = first;
				// Reset working status
				status = false;
				while (current != null && current.next != null) {
					if ((current.item.compareTo(current.next.item) > 0)) {
						T temp = current.item;
						current.item = current.next.item;
						current.next.item = temp;
						// When node value change
						status = true;
					}
					previous = current;
					current = current.next;
				}
			} while (status);
		}
	}

// =========== added method for assignment 2 (END) ===========//
}
