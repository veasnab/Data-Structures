//THE ORGINAL
//Veasna Bun 03/30/2022
//TCSS342 Assignment 1 LinkedList 
public class MyLinkedList<T> {

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
	private Node<T> first, current, previous;
	private int size;

	// LinkedList Constructor
	public MyLinkedList() {
		this.first = this.current = this.previous = null;
		this.size = 0;
	}

	// LinedList Method
	public void addBefore(T item) {
		Node<T> addItem = new Node(item, null);
		if (first == null) {
			first = addItem;
			previous = first;

		} else if (current == null) {
			current = previous.next = addItem;

		} else if (current.next == null) {
			current.next = addItem;
			previous = current;
			current = current.next;
		} else {
			addItem.next = current;
			previous.next = addItem;
			previous = addItem;
		}
		size++;
	}

	public void addAfter(T item) {
		Node<T> addItem = new Node(item, null);
		if (current == null || first == null) {
			return;
		}
		previous = current;
		Node<T> temp = current.next;
		current.next = addItem;
		current.next.next = temp;
		size++;
	}

	public T remove() {
		if (current == null) {
			return null;
		}
		T removeNode = current.item;
		previous.next = current.next;
		current = previous.next;
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
		previous = first;
		if (current == null) {
			return null;
		}
		return (T) current.item;
	}

	public T next() {
		if (current == null) {
			return null;
		}
		previous = current;
		current = current.next;
		return current.item;
	}

	public boolean contains(T item) {
		current = first;
		while (current != null) {
			if (current.item.equals(item)) {
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
		return size == 0;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		current = first;
		if (size != 0) {
			str.append("[");
		}
		while (current != null) {
			str.append(current.item);
			current = current.next;
			str.append(",");
		}
		if (size != 0) {
			str.replace(str.length() - 1, str.length(), "]");
		}
		return str.toString();
	}
}
