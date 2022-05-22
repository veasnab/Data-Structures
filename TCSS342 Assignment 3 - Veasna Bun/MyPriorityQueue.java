
public class MyPriorityQueue<T extends Comparable<T>> {
	// Implement PriorityQueue;
	private MyArrayList<T> heap;

	// PriorityQueue Constructor;
	public MyPriorityQueue() {
		heap = new MyArrayList<T>();
	}

	// PriorityQueue Method
	/**
	 * insert(T item)
	 * 
	 * @param Inserts the <T>item into the heap corrects location
	 */
	public void insert(T item) {
		heap.insert(item, heap.size());
		bubbleUp();
	}

	/**
	 * removeMin()
	 * 
	 * remove the minimum value in the heap
	 *
	 * @return minimum value in the heap
	 */
	public T removeMin() {
		T minimum = heap.get(0);
		heap.set(0, heap.get(size() - 1));
		heap.remove(size() - 1);
		sinkDown();
		return minimum;
	}

	/**
	 * T min()
	 * 
	 * @return minimum value in the heap
	 */
	public T min() {
		return heap.get(0);
	}

	/**
	 * size()
	 * 
	 * @return size: total number of item the heap
	 */
	public int size() {
		return heap.size();
	}

	/**
	 * isEmpty()
	 * 
	 * @return boolean<T/F>: is heap empty
	 */

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	/**
	 * toString()
	 * 
	 * @return String: the contents of the heap in String format
	 */

	public String toString() {
		return heap.toString();
	}

	/**
	 * bubbleUp()
	 * 
	 * Shifts the last element up to a position where it belongs
	 */

	private void bubbleUp() {
		int index = size() - 1;
		T parent = heap.get(parent(index));
		T node = heap.get(index);
		while (node.compareTo(parent) < 0) {
			heap.set(index, parent);
			heap.set(parent(index), node);
			index = parent(index);
			node = heap.get(index);
			parent = heap.get(parent(index));
		}
	}

	/**
	 * sinkDown()
	 * 
	 * Use for boot-strap starting at index 0;
	 */

	private void sinkDown() {
		sinkDown(0);
	}

	/**
	 * sinkDown(int index);
	 * 
	 * Shifts the first element down to a position where it belongs
	 * 
	 * @param take in the index starting at 0 and recursively call itself
	 */

	private void sinkDown(int index) {
		T left = heap.get(left(index));
		T right = heap.get(right(index));
		T node = heap.get(index);
//		System.out.println(left.compareTo(node) < 0);
		if (left == null) {
			// base case do nothing if left is null
		} else if (right == null && left.compareTo(node) < 0) {
			// if right is null and left is less than switch then sink down.
			heap.set(index, left);
			heap.set(left(index), node);
			sinkDown(left(index));
		} else if ((left != null && right != null)) {
			// if left and right is not null compare the two
			// then move to the least value
			System.out.println(left.compareTo(right) < 0);
			System.out.println(left);
			System.out.println(right);
			System.out.println(node);
			if (left.compareTo(right) < 0 && node.compareTo(left) > 0) {
				// walk left
				heap.set(index, left);
				heap.set(left(index), node);
				sinkDown(left(index));
			} else if (right.compareTo(left) < 0 && node.compareTo(right) > 0) {
				// walk right
				heap.set(index, right);
				heap.set(right(index), node);
				sinkDown(right(index));
			}
		}

	}

	/**
	 * parent(int index)
	 * 
	 * @param index is the child
	 * @return the index of the child parent
	 */

	private int parent(int index) {
		if (index % 2 == 0 && index != 0) {
			return (index / 2) - 1; // right
		} else {
			return index / 2; // left
		}
	}

	/**
	 * left(int index)
	 * 
	 * @param index is the parent
	 * @return the index of the left child of the parent
	 */

	private int left(int index) {
		return (index * 2) + 1;
	}

	/**
	 * right(int index)
	 * 
	 * @param index is the parent
	 * @return the index of the right child of the parent
	 */

	private int right(int index) {
		return (index * 2) + 2;
	}
}
