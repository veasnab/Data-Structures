import java.util.Random;

public class Population {
	// implement Population
	public MyLinkedList<Genome> population;
	public Genome mostFit;
	public int generation;
	private int size;

	// Population Constructor
	public Population() {
		this.population = new MyLinkedList<>();
		this.generation = 0;
		this.size = 100;
		while (population.size() < size) {
			population.addBefore(new Genome());
		}
		population.sort();
		this.mostFit = population.first();
	}

	public void nextGeneration() {
		removeGenome();
		createGenome();
		this.generation++;
		population.sort();
		mostFit = population.first();
	}

	private void removeGenome() {
		population.first();
		int index = 0;
		while (index < 50) {
			population.next();
			index = index + 1;
		}
		while (population.size() > 50) {
			population.remove();
		}
	}

	private void createGenome() {
		while (population.size() < size) {
			Random rand = new Random();
			int random = rand.nextInt(2);
			if (random == 0) {
				Genome clone = cloneGenome();
				clone.mutate();
				population.addBefore(clone);
			} else if (random == 1) {
				Genome clone = cloneGenome();
				clone.crossover(findCrossover());
				clone.mutate();
				population.addBefore(clone);
			}
		}
	}

	private Genome findCrossover() {
		Random rand = new Random();
		int random = rand.nextInt(50);
		int index = 0;
		population.first();
		while (index < random) {
			population.next();
			index = index + 1;
		}
		return population.current();
	}

	private Genome cloneGenome() {
		Random rand = new Random();
		int random = rand.nextInt(50);
		int index = 0;
		population.first();
		while (index < random) {
			population.next();
			index = index + 1;
		}

		return new Genome(population.current());
	}

	public String toString() {
		return population.toString();
	}

	public static void main(String[] args) {
		Population pop = new Population();
		while (pop.mostFit.fitness() < 0) {
			System.out.print("Generation " + pop.generation + " ");
			System.out.println(pop.mostFit);
			pop.nextGeneration();
		}
		System.out.print("Generation " + pop.generation + " ");
		System.out.println(pop.mostFit);
	}
}
