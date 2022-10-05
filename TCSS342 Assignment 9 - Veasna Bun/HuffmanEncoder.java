
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

public class HuffmanEncoder {

	/*
	 * FrequencyNode
	 * ==========================================================================
	 */
	private static class FrequencyNode implements Comparable<FrequencyNode> {
		// implement FrequencyNode
		public Character character;
		public Integer count;

		// FrequencyNode Constructor
		public FrequencyNode() {
			this.character = null;
			this.count = 0;
		}

		// FrequencyNode Method
		@Override
		public int compareTo(FrequencyNode other) {
			if (character.compareTo(other.character) < 0) {
				return -1;
			} else if (character.compareTo(other.character) == 0) {
				return 0;
			}
			return 1;
		}

		public String toString() {
			return character + ":" + count;
		}
	}
	/*
	 * FrequencyNode
	 * ==========================================================================
	 */

//HuffmanNode ============================================================================
	private static class HuffmanNode implements Comparable<HuffmanNode> {
		// implement HuffmanNode
		public Character character;
		public Integer weight;
		public HuffmanNode left;
		public HuffmanNode right;
		//
		public String word;

		// HuffmanNode Constructor
		public HuffmanNode(Character ch, Integer wt) {
			if (ch != null) {
				this.character = ch;
				this.weight = wt;
			}
		}

		public HuffmanNode(String word, Integer wt) {
			if (word != null) {
				this.word = word;
				this.weight = wt;
			}
		}

		public HuffmanNode(HuffmanNode left, HuffmanNode right) {
			if (HuffmanEncoder.wordCodes) {
				if (left.word != null && right.word != null) {
					this.left = left;
					this.right = right;
					this.weight = left.weight + right.weight;
				}
			} else {
				if (left.character != null && right.character != null) {
					this.left = left;
					this.right = right;
					this.weight = left.weight + right.weight;
				}
			}

		}

		// HuffmanNode Method
		@Override
		public int compareTo(HuffmanNode other) {
			if (weight.compareTo(other.weight) < 0) {
				return -1;
			} else if (weight.compareTo(other.weight) == 0) {
				return 0;
			}
			return 1;
		}

		public String toString() {
			if (HuffmanEncoder.wordCodes) {
				return word + ":" + weight;
			} else {
				return character + ":" + weight;
			}
		}
	}
//HuffmanNode ============================================================================

//CodeNode ============================================================================
	private static class CodeNode implements Comparable<CodeNode> {
		// Implement CodeNode
		public Character character;
		public String code;

		public CodeNode() {
		}

		// CodeNode Method
		@Override
		public int compareTo(CodeNode other) {
			if (character.compareTo(other.character) < 0) {
				return -1;
			} else if (character.compareTo(other.character) == 0) {
				return 0;
			}
			return 1;
		}

		public String toString() {
			return character + ":" + code;
		}
	}
//CodeNode ============================================================================

	/**
	 * 
	 */
	// implement HuffmanEncoder
	private String inputFileName;
	private String outputFileName;
	private String codeFileName;
	private BookReader book;
	private MyOrderedList<FrequencyNode> frequencies;
	private HuffmanNode huffmanTree;
	private MyOrderedList<CodeNode> codes;
	private byte[] encoderText;
	//
	private static boolean wordCodes;
	private MyHashTable<String, Integer> frequenciesHash;
	private MyHashTable<String, String> codesHash;

	// HuffmanEncoder Constructor
	public HuffmanEncoder() throws IOException {
		this(true);
	}

	private HuffmanEncoder(boolean wordCodes) throws IOException {
		HuffmanEncoder.wordCodes = wordCodes;
		this.inputFileName = "./WarAndPeace.txt";
		this.codeFileName = "./WarAndPeace-codes.txt";
		this.outputFileName = "./WarAndPeace-compressed.bin";
		this.book = new BookReader(inputFileName);
		this.codes = new MyOrderedList<>();

		//
		this.frequenciesHash = new MyHashTable<>(32768); // 2 to the power of 15
		this.codesHash = new MyHashTable<>(32768);

		countFrequency();
		buildTree();
		encode();
		writeFiles();

	}

	// HuffmanEncoder Method
	private void countFrequency() {
		System.out.println();
		if (wordCodes) {
			// testing duration
			long duration = 0;
			long start = System.currentTimeMillis();
			// testing duration
			book.wordsAndSeparators.first();
			while (book.wordsAndSeparators.current() != null) {
				String s = book.wordsAndSeparators.current();
				if (this.frequenciesHash.get(s) == null) {
					this.frequenciesHash.put(s, 1);
				} else {
					this.frequenciesHash.put(s, this.frequenciesHash.get(s) + 1);
				}
				book.wordsAndSeparators.next();
			}
			// testing duration
			long now = System.currentTimeMillis();
			duration = now - start;
			System.out.println("Counting frequencies of words and separators... " + this.frequenciesHash.size()
					+ " unique words and \nseparators found in " + duration + " milliseconds.");
			// testing duration

		} else {
			this.frequencies = new MyOrderedList<>();
			// testing duration
			long duration = 0;
			long start = System.currentTimeMillis();
			// testing duration

			String readbook = book.book;
			for (int i = 0; i < readbook.toString().length(); i++) {
				char c = readbook.charAt(i);
				FrequencyNode thisCharacter = new FrequencyNode();
				thisCharacter.character = c;
				if (frequencies.binarySearch(thisCharacter) == null) {
					frequencies.add(thisCharacter);
					thisCharacter.count = 1;
				} else {
					frequencies.binarySearch(thisCharacter).count++;
				}
			}

			// testing duration
			long now = System.currentTimeMillis();
			duration = now - start;
			System.out.println("Counting frequencies of characters... " + frequencies.size()
					+ " unique characters found in " + duration);
			System.out.println(" milliseconds.");
			// testing duration
		}

	}

	private void buildTree() {
		System.out.println();
		MyPriorityQueue<HuffmanNode> huffmanNode = new MyPriorityQueue<>();
		if (wordCodes) {
			// testing duration
			long duration = 0;
			long start = System.currentTimeMillis();
			// testing duration

			// add the Node with the weight

			for (int i = 0; i < frequenciesHash.size(); i++) {
				String s = this.frequenciesHash.keys.get(i);
				huffmanNode.insert(new HuffmanNode(s, this.frequenciesHash.get(s)));
			}

			// grab every two node create a new one until one one is left
			while (huffmanNode.size() > 1) {
				HuffmanNode left = huffmanNode.removeMin();
				HuffmanNode right = huffmanNode.removeMin();
				if (right != null && left != null) {
					HuffmanNode merge = new HuffmanNode(left, right);
					merge.word = " ";
					huffmanNode.insert(merge);
				} else if (left != null) {
					huffmanNode.insert(left);
				}
			}
			this.huffmanTree = huffmanNode.min();
			extractCodes(huffmanTree, "");

			// testing duration end
			long now = System.currentTimeMillis();
			duration = now - start;
			System.out.println("Building a Huffman tree and reading codes... " + duration + " milliseconds.");
			// testing duration end

		} else {
			// testing duration
			long duration = 0;
			long start = System.currentTimeMillis();
			// testing duration

			// add the Node with the weight
			for (int i = 0; i < frequencies.size(); i++) {
				FrequencyNode node = frequencies.get(i);
				huffmanNode.insert(new HuffmanNode(node.character, node.count));
			}
			// grab every two node create a new one until one one is left
			while (huffmanNode.size() > 1) {
				HuffmanNode left = huffmanNode.removeMin();
				HuffmanNode right = huffmanNode.removeMin();
				if (right != null && left != null) {
					HuffmanNode merge = new HuffmanNode(left, right);
					merge.character = ' ';
					huffmanNode.insert(merge);
				} else if (left != null) {
					huffmanNode.insert(left);
				}
			}
			this.huffmanTree = huffmanNode.min();
			extractCodes(huffmanTree, "");

			// testing duration end
			long now = System.currentTimeMillis();
			duration = now - start;
			System.out.println("Building a Huffman tree and reading codes... " + duration + " milliseconds.");
			// testing duration end
		}
	}

	private void extractCodes(HuffmanNode root, String code) {
		if (wordCodes) {
			if (root.left != null)
				extractCodes(root.left, code + "0"); // left
			if (root.right != null)
				extractCodes(root.right, code + "1"); // right
			if (root.left == null && root.right == null) {
				this.codesHash.put(root.word, code);
			}
		} else {
			if (root.left != null)
				extractCodes(root.left, code + "0"); // left
			if (root.right != null)
				extractCodes(root.right, code + "1"); // right
			if (root.left == null && root.right == null) {
				CodeNode c = new CodeNode();
				c.character = root.character;
				c.code = code;
				this.codes.add(c);
			}
		}
	}

	private void encode() {
		// testing duration
		long duration = 0;
		long start = System.currentTimeMillis();
		// testing duration

		StringBuilder sb = new StringBuilder();
		if (wordCodes) {
			book.wordsAndSeparators.first();
			while (book.wordsAndSeparators.current() != null) {
				sb.append(this.codesHash.get(book.wordsAndSeparators.current()));
				book.wordsAndSeparators.next();
			}
		} else {
			for (int i = 0; i < this.book.book.length(); i++) {
				CodeNode node = new CodeNode();
				node.character = this.book.book.charAt(i);
				HuffmanEncoder.CodeNode finder = this.codes.binarySearch(node);
				sb.append(finder.code);
			}
		}
		int size = (sb.length() / 8) + 1;

		this.encoderText = new byte[size];
		for (int i = 0, index = 0; i < sb.length(); i = i + 8, index++) {
			if (i + 8 < sb.length()) {
				String s = sb.substring(i, i + 8);
				byte b = (byte) Integer.parseInt(s, 2);
				this.encoderText[index] = b;

			} else { // at the end of the string;
				String s = sb.substring(i, i + (sb.length() % 8));
				byte b = (byte) Integer.parseInt(s, 2);
				this.encoderText[index] = b;
			}

		}
		// testing duration end
		long now = System.currentTimeMillis();
		duration = now - start;
		System.out.println("Encoding message... " + duration + " milliseconds.");
		// testing duration end
	}

	private void writeFiles() {
		System.out.println();
		// testing duration
		long duration = 0;
		long start = System.currentTimeMillis();
		// testing duration
		// store the codes to a txt file

		// store the encoder to a bin
		if (wordCodes) {	
			try (OutputStream opStream = new FileOutputStream(this.outputFileName)) {
				opStream.write(this.encoderText);
				opStream.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try (PrintWriter toFile = new PrintWriter(this.codeFileName)) {
				toFile.println(this.codesHash.toString());
				toFile.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else {

			try (OutputStream opStream = new FileOutputStream(this.outputFileName)) {
				opStream.write(this.encoderText);
				opStream.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try (PrintWriter toFile = new PrintWriter(this.codeFileName)) {
				toFile.println(this.codes.toString());
				toFile.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		// testing duration end
		long now = System.currentTimeMillis();
		duration = now - start;
		System.out.println("Writing compressed file... " + this.encoderText.length + " bytes writen in " + duration
				+ " milliseconds.");
		// testing duration end
	}

	public static void main(String[] args) throws IOException {
		HuffmanEncoder s = new HuffmanEncoder();
	}
}
