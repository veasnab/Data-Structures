
public class MyBinarySearchTree<T extends Comparable<T>> {
	// Implement Node
	private static class Node<T> {
		public T item;
		public Node<T> left;
		public Node<T> right;
		public int height;
		public int balanceFactor;

		// Node Constructor
		public Node() {
		}

		public String toString() {
			if (!balancing)
				return item + ":H" + height;
			return item + ":H" + height + ":B" + balanceFactor;
		}
	}
	// Node Class End **

	// Implement MyBinarySearchTree
	private Node<T> root;
	private int size;
	public long comparisons;
	public Integer rotations;
	private static boolean balancing;

	// MyBinarySearchTree Constructors
	public MyBinarySearchTree() {
		this(false);
	}

	public MyBinarySearchTree(boolean balancing) {
		this.root = null;
		this.size = 0;
		this.rotations = 0;
		this.comparisons = 0;
		MyBinarySearchTree.balancing = balancing;
	}
	// MyBinarySearchTree Method

	/**
	 * assuming there will not be a duplicate add this item to the BinaryTree; size
	 * is always increment by 1;
	 * 
	 * @param item
	 */
	public void add(T item) {
		add(item, root);
		size++;
	}

	private Node<T> add(T item, Node<T> subTree) {
		Node<T> addItem = new Node<T>();
		addItem.item = item;
		if (subTree == null) {
			root = subTree = addItem;
		} else if (item.compareTo(subTree.item) < 0) {
			if (subTree.left == null) {
				subTree.left = addItem;
			} else {
				add(item, subTree.left);
			}

		} else if (item.compareTo(subTree.item) > 0) {
			if (subTree.right == null) {
				subTree.right = addItem;
			} else {
				add(item, subTree.right);
			}
		}
		updateHeight(subTree);
		if (balancing && Math.abs(subTree.balanceFactor) > 1) {
			subTree = rebalance(subTree);
		}
		return subTree;
	}

	/**
	 * @param remove item if found, if found size is decrease by one
	 */
	public void remove(T item) {
		remove(item, root);
	}

	private Node<T> remove(T item, Node<T> subTree) {
		if (subTree == null) {
			return subTree;
		}
		if (item.compareTo(subTree.item) < 0) {
			subTree.left = remove(item, subTree.left);
		} else if (item.compareTo(subTree.item) > 0) {
			subTree.right = remove(item, subTree.right);
		} else {
			if (size == 1) {
				this.root = null;
				size--;
			} else if (size == 2 && subTree.right != null) {
				rotateLeft(subTree);
				subTree.left = null;
				subTree.height = 0;
				subTree.balanceFactor = 0;
				size--;
				return subTree;
			} else if (size == 2 && subTree.left != null) {
				rotateRight(subTree);
				subTree.right = null;
				subTree.height = 0;
				subTree.balanceFactor = 0;
				size--;
				return subTree;
			} else if (subTree.left == null) {
				size--;
				return subTree.right;
			} else if (subTree.right == null) {
				size--;
				return subTree.left;
			}
			if (subTree != null && subTree.left != null && subTree.right != null) {
				subTree.item = minValue(subTree.right);
				subTree.right = remove(subTree.item, subTree.right);
			}
		}
		updateHeight(subTree);
		if (balancing && Math.abs(subTree.balanceFactor) > 1) {
			subTree = rebalance(subTree);
		}
		return subTree;
	}

	/**
	 * helper method to find the mind value to be remove
	 * 
	 * @param root
	 * @return
	 */

	private T minValue(Node<T> subTree) {
		T minimum = subTree.item;
		while (subTree.left != null) {
			minimum = subTree.left.item;
			subTree = subTree.left;
		}
		return minimum;
	}

	/**
	 * search for the item in the BinaryTree, return item if found else return null;
	 * 
	 * @param item
	 * @return
	 */
	public T find(T item) {
		return find(item, root);
	}

	private T find(T item, Node<T> subTree) {
		comparisons++;
		if (subTree == null || item == null) {
			return null;
		}
		if (item.compareTo(subTree.item) == 0) {
			return subTree.item;
		}
		if (item.compareTo(subTree.item) < 0) {
			return find(item, subTree.left);
		}
		return find(item, subTree.right);
	}

	/**
	 * @return the height/ depth of the binaryTree
	 */
	public int height() {
		if (isEmpty()) {
			return -1;
		}
		return root.height;
	}

	/**
	 * @return the number of Node in the Binary SearchTree
	 */
	public int size() {
		return size;
	}

	/**
	 * @return true if BinaryTree Empty/ else return false
	 */
	public boolean isEmpty() {
		return root == null;
	}

	private void updateHeight(Node<T> node) {
		if (node == null) {
			return;
		} else if (node.left == null && node.right == null) {
			node.height = 0;
			node.balanceFactor = 0;
		} else if (node.left != null && node.right == null) {
			node.height = node.left.height + 1;
			node.balanceFactor = node.left.height + 1;
		} else if (node.left == null && node.right != null) {
			node.height = node.right.height + 1;
			node.balanceFactor = -1 - node.right.height;
		} else if (node.left != null && node.right != null) {
			if (node.left.height > node.right.height) {
				node.height = node.left.height + 1;
			} else {
				node.height = node.right.height + 1;
			}
			node.balanceFactor = node.left.height - node.right.height;
		}
	}

	private Node<T> rotateLeft(Node<T> node) {
		T temp = node.item;
		node.item = node.right.item;

		Node<T> newTemp = new Node<T>();
		newTemp.item = temp;
		newTemp.left = node.left;
		newTemp.right = node.right.left;
		updateHeight(newTemp);

		node.left = newTemp;
		node.right = node.right.right;
		rotations++;

		return node;
	}

	private Node<T> rotateRight(Node<T> node) {
		T temp = node.item;
		node.item = node.left.item;

		Node<T> newTemp = new Node<T>();
		newTemp.item = temp;
		newTemp.right = node.right;
		newTemp.left = node.left.right;
		updateHeight(newTemp);
		node.right = newTemp;
		node.left = node.left.left;

		rotations++;
		return node;
	}

	private Node<T> rebalance(Node<T> node) {
		Node<T> temp = new Node<T>();
		// left
		if (node.balanceFactor >= 2) {
			// left - right
			if (node.left.balanceFactor < 0) {
				rotateLeft(node.left);
				updateHeight(node.left);
			}
			// left - left
			temp = rotateRight(node);
			updateHeight(temp);
		}
		// right
		if (node.balanceFactor <= -2) {
			// right - left
			if (node.right.balanceFactor > 0) {
				rotateRight(node.right);
				updateHeight(node.right);
			}
			// right - right
			temp = rotateLeft(node);
			updateHeight(temp);
			// left
		}
		return temp;
	}

	/**
	 * return the String of Node in the BinarySearchTree - iterating InOrder
	 */
	public String toString() {
		String s = "";
		if (isEmpty()) {
			s = "[]";
			return s;
		} else {
			s = s + "[";
			s = s + toString(root);
			s = s.substring(0, s.length() - 2);
			s = s + "]";
		}
		return s;
	}

	public String toString(Node<T> root) {
		String s = "";
		if (root == null) {
			return "";
		}
		s += toString(root.left);
		s += root.toString() + ", ";
		s += toString(root.right);
		return s;
	}

	public static void main(String[] args) {
		MyBinarySearchTree<Integer> s = new MyBinarySearchTree<Integer>(true);
		s.add(5);
		s.add(3);
		s.add(7);
		s.add(1);
		s.add(6);
		s.add(4);
		s.add(8);
		s.add(9);
		s.add(0);
		s.add(2);

		s.remove(10);

		s.remove(0);
		s.remove(5);
		s.remove(9);
//		
		s.remove(3);
		s.remove(7);
		s.remove(8);

		s.remove(4);
		s.remove(1);
		s.remove(2);
		s.remove(6);

		System.out.println(s);
	}
}
