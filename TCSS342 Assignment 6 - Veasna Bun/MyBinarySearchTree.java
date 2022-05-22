
public class MyBinarySearchTree<T extends Comparable<T>> {
	// Implement Node
	private static class Node<T> {
		public T item;

		public Node<T> left;
		public Node<T> right;
		public int height;

		// Node Constructor
		public Node() {
		}

		public String toString() {
			return item + ":H" + height;
		}
	}
	// Node Class End **

	// Implement MyBinarySearchTree
	private Node<T> root;
	private int size;
	public long comparisons;

	// MyBinarySearchTree Constructors
	public MyBinarySearchTree() {
		this.root = null;
		this.size = 0;
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
		if (subTree == null && root == null) {
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
		return addItem;
	}

	/**
	 * @param remove item if found, if found size is decrease by one
	 */
	public void remove(T item) {
		remove(item, root);
	}

	private Node<T> remove(T item, Node<T> subTree) {
		// search for the item
		if (subTree == null) {
			return null;
		}
		// go left
		else if (item.compareTo(subTree.item) < 0) {
			subTree.left = remove(item, subTree.left);
		}
		// go right
		else if (item.compareTo(subTree.item) > 0) {
			subTree.right = remove(item, subTree.right);
		}
		// item was found
		else {
			// case 1: no child
			if ((size == 1 || isEmpty()) && subTree.left == null && subTree.right == null) {
				root = null;
				size--;

			} else if (subTree.left == null && subTree.right == null) {
				subTree = null;
				size--;
			}
			// case 2: one child
			// check left
			else if (subTree.left != null && subTree.right == null) {
				subTree.item = subTree.left.item;
				subTree = subTree.left;
				size--;
			}
			// check right
			else if (subTree.left == null && subTree.right != null) {
				subTree.item = subTree.right.item;
				subTree.right = subTree.right.right;
				size--;
			}
			// case 3: two child
			else {
				// find the item Maximum value item in the left Node
				Node<T> walker = subTree.left;
				while (walker.right != null) {
					walker = walker.right;
				}
				subTree.item = walker.item;
				subTree.left = remove(subTree.item, subTree.left);
			}
		}

		updateHeight(subTree);
		return subTree;

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
		} else if (node.left != null && node.right == null) {
			node.height = node.left.height + 1;
		} else if (node.left == null && node.right != null) {
			node.height = node.right.height + 1;
		} else if (node.left != null && node.right != null) {
			if (node.left.height > node.right.height) {
				node.height = node.left.height + 1;
			} else {
				node.height = node.right.height + 1;
			}
		}
	}

	/**
	 * return the String of Node in the Binary SearchTree - iterating InOrder
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if (root != null)
			toString(root, sb);
		if (root == null) {
			sb.append("]");
		} else {
			sb.replace(sb.length() - 2, sb.length(), "]");
		}
		return sb.toString();
	}

	private String toString(Node<?> node, StringBuilder sb) {
		if (node != null) {
			toString(node.left, sb);
			sb.append(node + ", ");
			toString(node.right, sb);
		}
		return sb.toString();
	}
}
