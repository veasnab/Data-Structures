import java.util.Random;

public class Genome implements Comparable<Genome> {
	// implement Genome
	protected MyLinkedList<Character> genes;
	private MyLinkedList<Character> target;
	private double mutationRate;

	// Genome Constructor
	public Genome() {
		this.genes = new MyLinkedList<>();
		genes.addBefore(randomGenes());
		target();
	}

	public Genome(Genome genome) {
		MyLinkedList<Character> temp = new MyLinkedList<>();
		genome.genes.first();
		while (genome.genes.current() != null) {
			temp.addBefore(genome.genes.current());
			genome.genes.next();
		}
		this.genes = temp;
		target();
	}

	private void target() {
		this.target = new MyLinkedList<>();
		String name = "CHRISTOPHER PAUL MARRIOTT";
		for (int i = 0; i < name.length(); i++) {
			this.target.addBefore(name.charAt(i));
		}
		this.mutationRate = 0.05;
	}

	private Character randomGenes() {
		Random rand = new Random();
		char[] character = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '-', '\'' };
		int random = rand.nextInt(29);
		return character[random];
	}

//========================================================================
	public void mutate() {
		Random rand = new Random();
		double random = 0;
		random = (((double) (rand.nextInt(101))) / 100);
		if (random < mutationRate) {
			addGenes();
		}
		random = (((double) (rand.nextInt(101))) / 100);
		if (random < mutationRate && genes.size() >= 1) {
			removeGenes();
		}
		eachGenes();
	}

	private void addGenes() {
		Random rand = new Random();
		int random = rand.nextInt(genes.size() + 1);
		int index = 0;
		genes.first();
		while (index <= random) {
			genes.next();
			index = index + 1;
		}
		genes.addBefore(randomGenes());
	}

	private void removeGenes() {
		Random rand = new Random();
		int random = rand.nextInt(genes.size() + 1);
		int index = 0;
		genes.first();
		while (index <= random) {
			genes.next();
			index = index + 1;
		}
		genes.remove();
	}

	private void eachGenes() {
		Random rand = new Random();
		double random = 0;
		genes.first();
		MyLinkedList<Character> temp = new MyLinkedList<>();
		while (genes.current() != null) {
			random = (((double) (rand.nextInt(101))) / 100);
			if (random < mutationRate) {
				temp.addBefore(randomGenes());
			} else {
				temp.addBefore(genes.current());
			}
			genes.next();
		}
		this.genes = temp;
	}

//=================================================================== Mutate End
	public void crossover(Genome parent) {
		Random rand = new Random();
		int random = rand.nextInt(2);
		parent.genes.first();
		genes.first();

		MyLinkedList<Character> temp = new MyLinkedList<>();
		while ((parent.genes.current() != null && genes.current() != null)) {
			if (random == 0) {
				temp.addBefore(genes.current());
			}
			if (random == 1) {
				temp.addBefore(parent.genes.current());
			}
			parent.genes.next();
			genes.next();
		}
		this.genes = temp;
	}

	public int fitness() {
		int length = target.size();
		int differ = 0;
		target.first();
		genes.first();
		while (target.current() != null || genes.current() != null) {
			
			if (genes.current() != null && target.current() != null 
					&& target.current().compareTo(genes.current()) != 0) {
				differ = differ + 1;
				length = length - 1;
				
			}
			else if (genes.current() != null && target.current() != null
					&& target.current().compareTo(genes.current()) == 0) {
				length = length - 1;
			}
			else if (target.current() != null && genes.current() == null) {
				differ = differ + 1;
			}
			else if(target.current() == null && genes.current() != null) {
				differ = differ + 1;
				length = length + 1;
			}
			target.next();
			genes.next();

		}
		return -(length + differ);
	}

	@Override
	public int compareTo(Genome other) {
		if (fitness() < other.fitness()) {
			return 1;
		} else if (fitness() == other.fitness()) {
			return 0;
		}
		return -1;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("(\"");
		genes.first();
		while (genes.current() != null) {
			str.append(genes.current());
			genes.next();
		}
		str.append("\", ");
		str.append(fitness());
		str.append(")");
		return str.toString();

	}

	// for testing
//	private void addMe() {
//		this.genes.first();
//		this.genes.remove();
//		String name = "CHRISTOPHER PAUL MARRIOTT";
//		for (int i = 0; i < name.length(); i++) {
//			this.genes.addBefore(name.charAt(i));
//		}
//	}
//
//	public static void main(String[] args) {
//		Genome g = new Genome();
//		g.addMe();
//		System.out.println(g);
//		g.eachGenes();
//		System.out.println(g);
//	}
}
