import java.io.IOException;
import java.util.Arrays;

public class MyTrie {

	private static class Node implements Comparable<Node> {
		// Implement Node
		public boolean terminal;
		public char character;
		public MyOrderedList<Node> children;

		public Node(char c) {
			this.children = new MyOrderedList<Node>();
			this.character = c;
			this.terminal = false;
		}

		// Node method
		public int compareTo(Node other) {
			if (character < other.character) {
				return -1;
			} else if (character == other.character) {
				return 0;
			}
			return 1;
		}

		public void addChildren() {
			// create a node with all child
			String childMain = "0123456789'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			for (int i = 0; i < childMain.length(); i++) {
				Node node = new Node(childMain.charAt(i));
				children.add(node);
			}
		}
	}

	// Implement MyTrie
	private Node root;
	private int size;
	public long comparisons;

	// MyTrie Constructor
	public MyTrie() {
		this.root = new Node('\0');
		this.size = 0;
		this.comparisons = 0;
	}

	// MyTrie Method
	public void insert(String item) {
		// start at route node
		Node current = root;
		for (int i = 0; i < item.length(); i++) {
			// if current.children don't have all the children add them
			if (current.children.isEmpty()) {
				current.addChildren();
			}
			comparisons++;
			// search and trace over child
			Node found = current.children.binarySearch(new Node(item.charAt(i)));
			// if this child exist traverse to the child
			if (found != null) {
				current = found;
			}
			// repeat until end of string
		}
		if (!current.terminal) {
			size++;
			current.terminal = true;
		}
	}

	public void remove(String item) {
		// start at the root
		Node current = root;
		// iterate through the loop until null is found or reach the end of the string
		for (char c : item.toCharArray()) {
			current = current.children.binarySearch(new Node(c));
			comparisons++;
			if (current == null) {
				return;// this string doesn't exist
			}

		}
		// reach the end of the node
		// change the current terminal to false
		current.terminal = false;
	}

	public boolean find(String item) {
		// start at the root
		Node current = root;
		// iterate through the loop until null is found or reach the end of the string
		for (char c : item.toCharArray()) {
			current = current.children.binarySearch(new Node(c));
			comparisons++;
			if (current == null) {
				return false; // this string doesn't exist return false
			}
		}
		return current.terminal; // check to see if this is a already a string that exist
		// not sure why this is running so slow
	}

	public int size() {
		return size;
	}

	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("[");
		addWord(root, "", output);
		output.replace(output.length() - 2, output.length(), "]");
		String s = output.toString();
		String[] words = s.split(",");

		return null; //Arrays.toString(words); to print out words..
	}

	private void addWord(Node node, String str, StringBuilder output) {
		Node current = node;
		for (int i = 0; i < current.children.size(); i++) {
			Node find = (current.children.get(i));
			if (find.children != null) {
				addWord(find, str + find.character, output);
			}
		}
		if (current.terminal) {
			output.append(str + ", ");
		}
	}

	public static void main(String[] args) throws IOException {
		UniqueWords s = new UniqueWords();

	}

}
