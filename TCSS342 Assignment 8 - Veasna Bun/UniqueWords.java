import java.io.IOException;

public class UniqueWords {
	// implement UniqueWords
	private BookReader book;

	// constructor UniqueWords
	public UniqueWords() throws IOException {
		book = new BookReader("WarAndPeace.txt");
		addUniqueWordsToHashTable();
//		addUniqueWordsToAVL();
//		addUniqueWordsToBST();
//		addUniqueWordsToTrie();
//		addUniqueWordsToArrayList();
//		addUniqueWordsToLinkedList();
//		addUniqueWordsToOrderedList();
	}

	public void addUniqueWordsToHashTable() {
		// testing adding duration
		long duration = 0;
		long start = System.currentTimeMillis();
		// testing adding duration
		
		MyHashTable<String,String> MHT = new MyHashTable<>(32768);
		char c;
		boolean findWord = false;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < book.book.length(); i++) {
			c = book.book.charAt(i);
			if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || (c == '\'')) {
				str.append(c);
				findWord = true;
			} else {
				if (findWord) {
					String s = str.toString();
					findWord = false;
					if (MHT.get(s) == null) {
						MHT.put(s,s);
					}
					// if found skip the word
					str = new StringBuilder();
				}
			}
		}

		// testing adding duration
		long now = System.currentTimeMillis();
		duration = (now - start);
		// testing adding duration
		System.out.println("Adding unique words to a hash table... " + duration + " milliseconds");
		
		System.out.println(MHT.size() + " unique words");	
		System.out.println(MHT.comparisons + " comparisons");
		System.out.println(MHT.maxProbe + " max probe");
		// testing adding duration
		duration = 0;
		start = System.currentTimeMillis();
		// testing adding duration
		MHT.toString(); // testing speed of toString();
		// testing adding duration
		now = System.currentTimeMillis();
		duration = (now - start);
		// testing adding duration
		System.out.println("Extracting the key-value pairs... " + duration + " milliseconds.");

	}

	public void addUniqueWordsToAVL() {
		// testing adding duration
		long duration = 0;
		long start = System.currentTimeMillis();
		// testing adding duration

		MyBinarySearchTree<String> AVL = new MyBinarySearchTree<>(true);
		char c;
		boolean findWord = false;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < book.book.length(); i++) {
			c = book.book.charAt(i);
			if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || (c == '\'')) {
				str.append(c);
				findWord = true;
			} else {
				if (findWord) {
					String s = str.toString();
					findWord = false;
					if (AVL.find(s) == null) {
						// if not found add to MyTrie
						AVL.add(s);
					}
					// if found skip the word
					str = new StringBuilder();
				}
			}
		}

		// testing adding duration
		long now = System.currentTimeMillis();
		duration = (now - start);
		// testing adding duration
		System.out.println("Adding unique words to an AVL binary search tree... " + duration + " milliseconds");
		System.out.println(AVL.size() + " unique words");
		System.out.println(AVL.height() + " height");
		System.out.println(AVL.comparisons + " comparisons");
		System.out.println(AVL.rotations + " rotations");
		// testing adding duration
		duration = 0;
		start = System.currentTimeMillis();
		// testing adding duration
		AVL.toString(); // testing speed of toString();
		// testing adding duration
		now = System.currentTimeMillis();
		duration = (now - start);
		// testing adding duration
		System.out.println("Traversing the AVL... " + duration + " milliseconds.");
	}

	public void addUniqueWordsToBST() {
		// testing adding duration
		long duration = 0;
		long start = System.currentTimeMillis();
		// testing adding duration

		MyBinarySearchTree<String> BTS = new MyBinarySearchTree<>();
		char c;
		boolean findWord = false;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < book.book.length(); i++) {
			c = book.book.charAt(i);
			if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || (c == '\'')) {
				str.append(c);
				findWord = true;
			} else {
				if (findWord) {
					String s = str.toString();
					findWord = false;
					if (BTS.find(s) == null) {
						// if not found add to MyTrie
						BTS.add(s);
					}
					// if found skip the word
					str = new StringBuilder();
				}
			}
		}

		// testing adding duration
		long now = System.currentTimeMillis();
		duration = (now - start);
		// testing adding duration
		System.out.println("Adding unique words to a binary search tree... in " + duration + " milliseconds");
		System.out.println(BTS.size() + " unique words");
		System.out.println(
				"The binary search tree had a height of " + BTS.height() + " and made comparisons " + BTS.comparisons);
		// testing adding duration
		duration = 0;
		start = System.currentTimeMillis();
		// testing adding duration
		BTS.toString(); // testing speed of toString();
		// testing adding duration
		now = System.currentTimeMillis();
		duration = (now - start);
		// testing adding duration
		System.out.println("Traversing the binary search tree... " + duration + " milliseconds.");
	}

	public void addUniqueWordsToTrie() {
		// testing adding duration
		long duration = 0;
		long start = System.currentTimeMillis();
		// testing adding duration

		MyTrie MT = new MyTrie();
		char c;
		boolean findWord = false;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < book.book.length(); i++) {
			c = book.book.charAt(i);
			if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || (c == '\'')) {
				str.append(c);
				findWord = true;
			} else {
				if (findWord) {
					String s = str.toString();
					findWord = false;
					if (!MT.find(s)) {
						// if not found add to MyTrie
						MT.insert(s);
					}
					// if found skip the word
					str = new StringBuilder();
				}
			}
		}

		// testing adding duration
		long now = System.currentTimeMillis();
		duration = (now - start);
		// testing adding duration
		System.out.println("Adding unique words to a trie... in " + duration + " milliseconds");
		System.out.println(MT.size() + " unique words");
		System.out.println(MT.comparisons + " comparisons");
		// testing adding duration
		duration = 0;
		start = System.currentTimeMillis();
		// testing adding duration
		MT.toString(); // testing speed of toString();
		// testing adding duration
		now = System.currentTimeMillis();
		duration = (now - start);
		// testing adding duration
		System.out.println("Traversing the trie... in " + duration + " milliseconds.");
	}

	// UniqueWords Methods
	public void addUniqueWordsToLinkedList() {
		// testing adding duration
		long duration = 0;
		long start = System.currentTimeMillis();
		// testing adding duration
		MyLinkedList<String> MLL = new MyLinkedList<>();
		try {
			book.words.first();
			while (book.words.current() != null) {
				if (!(MLL.contains(book.words.current()))) {
					MLL.addBefore(book.words.current());
				}
				book.words.next();
			}
		} catch (NullPointerException e) {
			// testing adding duration
			long now = System.currentTimeMillis();
			duration = (now - start) / 1000;
			System.out.println("Adding unique words to a linked list... in " + duration + " seconds.");
			System.out.println(MLL.size() + " unique words");
			// testing adding duration
			System.out.println(MLL.comparisons + " comparisons");
			// testing sort duration
			duration = 0;
			start = System.currentTimeMillis();
			MLL.sort();
			now = System.currentTimeMillis();
			duration = (now - start) / 1000;
			System.out.println("Bubble sorting linked list... in " + duration + " seconds.");
			System.out.println();
			// testing sort duration
		}

	}

	public void addUniqueWordsToArrayList() {
		// testing adding duration
		long duration = 0;
		long start = System.currentTimeMillis();
		// testing adding duration
		MyArrayList<String> MAL = new MyArrayList<>();
		try {
			book.words.first();
			while (book.words.current() != null) {
				if (!(MAL.contains(book.words.current()))) {
					MAL.insert(book.words.current(), MAL.size());
				}
				book.words.next();
			}
		} catch (NullPointerException e) {
			long now = System.currentTimeMillis();
			duration = (now - start) / 1000;
			// testing adding duration
			System.out.println("Adding unique words to a array list... in " + duration + " seconds.");
			System.out.println(MAL.size() + " unique words");
			System.out.println(MAL.comparisons + " comparisons");
			// testing sort duration
			duration = 0;
			start = System.currentTimeMillis();
			MAL.sort();
			now = System.currentTimeMillis();
			duration = (now - start) / 1000;
			System.out.println("Bubble sorting array list... in " + duration + " seconds.");
			System.out.println();
			// testing sort duration

		}
	}

	public void addUniqueWordsToOrderedList() {
		// testing adding duration
		long duration = 0;
		long start = System.currentTimeMillis();
		// testing adding duration
		MyOrderedList<String> MOL = new MyOrderedList<>();
		try {
			book.words.first();
			while (book.words.current() != null) {
//				if ((MOL.binarySearch(book.words.current()))) {
//				} else {
//					MOL.add(book.words.current());
//				}
//				book.words.next();
			}
		} catch (NullPointerException e) {
			long now = System.currentTimeMillis();
			duration = (now - start) / 1000;
			System.out.println("Adding unique words to an ordered list... in " + duration + " seconds.");
			System.out.println(MOL.size() + " unique words");
			System.out.println(MOL.comparisons + " comparisons");
		}
	}

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("unused")
		UniqueWords s = new UniqueWords();
	}
}
