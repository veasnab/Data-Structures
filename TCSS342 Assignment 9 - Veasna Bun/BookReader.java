import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//Veasna Bun updated on 04/07/2022
//TCSS342 Assignment 2 BookReader 

public class BookReader {
	// Implement BookReader
	public String book;
	public MyLinkedList<String> words;
	public MyLinkedList<String> wordsAndSeparators; 

	// BookReader Constructor
	public BookReader(String filename) throws IOException {
		this.book = "";
		this.words = new MyLinkedList<>();
		this.wordsAndSeparators = new MyLinkedList<>();
		readBook(filename);
		parseWords();
		
	}

	// BookReader Method
	@SuppressWarnings("resource")
	public void readBook(String filename) throws IOException {
		// testing duration
		long duration = 0;
		long start = System.currentTimeMillis();
		// testing duration

		BufferedReader br = new BufferedReader(new FileReader(filename));
		StringBuilder str = new StringBuilder();
		int c = 0;
		while ((c = br.read()) != -1) {
			char characters = (char) c;
			str.append(characters);
		}
		book = str.toString();
		// testing duration
		long now = System.currentTimeMillis();
		duration = now - start;
		System.out.println("Reading input file \"./WarAndPeace.txt\"... " + str.length() + " characters in " + duration
				+ " milliseconds.");
		System.out.println("");
		// testing duration
	}

	public void parseWords() throws IOException {
		// testing duration
		long duration = 0;
		long start = System.currentTimeMillis();
		// testing duration

		char c;
		boolean findWord = false;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < book.length(); i++) {
			c = book.charAt(i);
			if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || (c == '\'')) {
				str.append(c);
				findWord = true;
			} else {
				if (findWord) {
					String s = str.toString();
					findWord = false;
					words.addBefore(s);
					wordsAndSeparators.addBefore(s);
					str = new StringBuilder();
				}
			}
			if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || (c == '\''))) {
				String separator = c + "";
				wordsAndSeparators.addBefore(separator);
			}
		}
		// testing duration
		long now = System.currentTimeMillis();
		duration = now - start;
		System.out.println("Finding words and adding them to a linked list... in " + duration + " milliseconds.");
		System.out.println("The linked list has a length of " + words.size());
		// testing duration
	}
}
