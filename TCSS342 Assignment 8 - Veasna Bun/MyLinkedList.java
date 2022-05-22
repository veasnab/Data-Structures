
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
	/**
	 * updated on 4/20/2022 adding a Node to the list before the current
	 * 
	 * @param item
	 */
	public void addBefore(T item) {
		Node<T> addItem = new Node<T>(item, null);
		if (item == null) {
			return;
		}
		// no node in the list
		else if (isEmpty()) {
			first = last = addItem;
		}
		// add to the end of the list
		else if (!isEmpty() && previous == null && current == null) {
			last = last.next = addItem;
		}
		// at the end of the list so add new node to the end
		// update previous/last and current remain null
		else if (!isEmpty() && previous != null && current == null) {
			previous.next = addItem;
			previous = last = addItem;
		}
		// at the beginning of the list so add new Node to the beginning
		// update current/first and previous remain null
		else if (!isEmpty() && previous == null && current != null) {
			addItem.next = current;
			current = first = addItem;
		}
		// somewhere between the list so add the new Node before the current
		// update the previous to the new Node
		// the current does not change location and remain unmoved
		else if (!isEmpty() && previous != null && current != null) {
			addItem.next = current;
			previous.next = addItem;
			previous = addItem;
		}
		size++;
	}

	/**
	 * updated on 4/20/2022 adding a Node to the list after the current
	 * 
	 * @param item
	 */
	public void addAfter(T item) {
		Node<T> addItem = new Node<T>(item, null);
		// only add to the list if current is at a node and not empty
		if (current == null) {
			return;
		}
		// if current is not node && if its next is null
		// at the end of the list
		// add the new Node to the end and update last
		// current remain unmoved
		else if (current != null && current.next == null) {
			current.next = last = addItem;
		}
		// if current is not node && if the next item is not node
		// we either at beginning of the list or somewhere in the middle
		// using a temp node store the current.next item
		// update the current.next node to the new AddItem, where
		// addItem.next/current.next.next is null
		// update the current.next.next to the temp, where the information was store
		else if (current != null && current.next != null) {
			Node<T> temp = current.next;
			current.next = addItem;
			current.next.next = temp;
		}
		size++;
	}

	/**
	 * updated on 4/20/2022 removing the current node
	 * 
	 * @return item store in the current node
	 */

	public T remove() {
		// if the current is not at null nothing is removed
		if (current == null) {
			return null;
		}
		// store the removed node
		T removeNode = current.item;
		// previous is null we are at the beginning of the list
		// update the current and first: previous remain unchanged
		if (previous == null) {
			first = current = current.next;
		}
		// if previous is not null && current.next is null
		// update the current and last previous: remain unchanged
		else if (previous != null && current.next == null) {
			last = current = previous.next = current.next;
		}
		// if previous and current.next is not null, we are somewhere in middle
		// update the current to current.next and previous.next
		else if (previous != null && current.next != null) {
			current = previous.next = current.next;

		}
		size--;
		return removeNode;
	}

	/**
	 * @return the current node
	 */

	public T current() {
		if (current == null) {
			return null;
		}
		return current.item;
	}

	/**
	 * move the current to the first node and setting the previous to null
	 * 
	 * @return the first node
	 */

	public T first() {
		current = first;
		previous = null;
		if (current == null) {
			return null;
		}
		return (T) current.item;
	}

	/**
	 * move the current node to the next node
	 * 
	 * @return the next node
	 */
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
			previous = current;
			current = current.next;
		}
		return temp;
	}

	/**
	 * look through the list of Node starting from the first Node iterate unit item
	 * is found or until end of list
	 * 
	 * @param item
	 * @return True if item is found and false reach end of list/ item is not found
	 */
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

	/**
	 * @return the number of node in the list
	 */

	public int size() {
		return size;
	}

	/**
	 * @return true if size is not 0
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Return the string of the Node by iterate from the first node to the end
	 */
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

	/**
	 * @param add item to the front the list
	 */

	public void addToFirst(T item) {
		Node<T> addItem = new Node<T>(item, null);
		if (isEmpty()) {
			first = last = addItem;
		} else {
			addItem.next = first;
			first = addItem;
		}
		size++;
	}

	/**
	 * @param add item to the end of the list
	 */
	public void addToLast(T item) {
		Node<T> addItem = new Node<T>(item, null);
		if (isEmpty()) {
			last = first = addItem;
		} else {
			last.next = addItem;
			last = addItem;
		}
		size++;
	}

	/**
	 * swap the current node with previous node
	 */
	public void swapWithPrevious() {
		T temp = previous.item;
		previous.item = current.item;
		current.item = temp;
	}

	/**
	 * use for calling other sorting type might update this will faster sort
	 */
	public void sort() {
		bubbleSort();
	}

	/**
	 * bubble sort runtime O(n^2)
	 */
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
//	public static void main(String[] args) {
//		MyLinkedList<Integer> s = new MyLinkedList<>();
////		System.out.println(s);
////		s.addBefore(1);
////		System.out.println(s);
////		s.addBefore(2);
////		System.out.println(s);
////		s.addBefore(3);
////		System.out.println(s);
////		System.out.println(s.first());
////		s.addAfter(4);
////		System.out.println(s);
////		System.out.println(s.current());
////		System.out.println(s);
////		System.out.println(s.next());
////		System.out.println(s);
////		System.out.println(s.current());
////		System.out.println(s.next());
////		System.out.println(s);
////		System.out.println(s.current());
////
////		s.addBefore(5);
////		System.out.println(s);
////		s.addBefore(6);
////		System.out.println(s);
////		s.first();
////		s.remove();
////		System.out.println(s);
//
//	}
}
