
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class HuffmanEncoder {
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

	private static class HuffmanNode implements Comparable<HuffmanNode> {
		// implement HuffmanNode
		public Character character;
		public Integer weight;
		public HuffmanNode left;
		public HuffmanNode right;

		// HuffmanNode Constructor
		public HuffmanNode(Character ch, Integer wt) {
			if (ch != null) {
				this.character = ch;
				this.weight = wt;
			}
		}

		public HuffmanNode(HuffmanNode left, HuffmanNode right) {
			if (left.character != null && right.character != null) {
				this.left = left;
				this.right = right;
				this.weight = left.weight + right.weight;
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
			return character + ":" + weight;
		}
	}

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

	// implement HuffmanEncoder
	private String inputFileName;
	private String outputFileName;
	private String codeFileName;
	private BookReader book;
	private MyOrderedList<FrequencyNode> frequencies;
	private HuffmanNode huffmanTree;
	private MyOrderedList<CodeNode> codes;
	private byte[] encoderText;

	// HuffmanEncoder Constructor
	public HuffmanEncoder() throws IOException {
		this.inputFileName = "./WarAndPeace.txt";
		this.codeFileName = "./WarAndPeace-codes.txt";
		this.outputFileName = "./WarAndPeace-compressed.bin";

		this.book = new BookReader(inputFileName);
		this.codes = new MyOrderedList<>();
		countFrequency();
		buildTree();
		encode();
		writeFiles();
	}

	// HuffmanEncoder Method
	private void countFrequency() {
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
		System.out.println();
		// testing duration

	}

	private void buildTree() {
		MyPriorityQueue<HuffmanNode> huffmanNode = new MyPriorityQueue<>();
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
		System.out.println();
	}

	private void extractCodes(HuffmanNode root, String code) {
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

	private void encode() {
		// testing duration
		long duration = 0;
		long start = System.currentTimeMillis();
		// testing duration
		StringBuilder sb = new StringBuilder();
//		this.encoderText = new byte[];// may need to update
		for (int i = 0; i < this.book.book.length(); i++) {
			CodeNode node = new CodeNode();
			node.character = this.book.book.charAt(i);
			HuffmanEncoder.CodeNode finder = this.codes.binarySearch(node);
			sb.append(finder.code);
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
		System.out.println();
		// testing duration end
	}

	private void writeFiles() {
		// testing duration
		long duration = 0;
		long start = System.currentTimeMillis();
		// testing duration
		// store the codes to a txt file

		// store the encoder to a bin
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

		// testing duration end
		long now = System.currentTimeMillis();
		duration = now - start;
		System.out.println("Writing compressed file... " + this.encoderText.length + " bytes wrriten in " + duration
				+ " milliseconds.");
		// testing duration end
	}

	public static void main(String[] args) throws IOException {
		HuffmanEncoder s = new HuffmanEncoder();
	}
}
