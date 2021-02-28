//import Lab3.Individual;
import Lab3.Population;
import Lab3.GeneticAlgorithm;

/**
 * @author Arvin
 * This is our main class used to run the genetic algorithm.
 * 
 * This case is one of the simplest problems we can solve: the objective is to
 * end up with an individual whose chromosome is all ones.
 */


public class App {
    	private static final int numberOfBits = 20;

        public static void main(String[] args) {
		// Create GA object
		GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.001, 0.95, 2);

		// Initialize population
		Population population = ga.initPopulation(numberOfBits);

		// Evaluate population
		ga.evalPopulation(population);

		// Keep track of current generation
		int generation = 1;

		// Start the evolution loop 
		while (ga.isTerminationConditionMet(population) == false) {
			// Print fittest individual from population
			System.out.println("Best solution: " + population.getFittest(0).toString());

			// Apply crossover
			population = ga.crossoverPopulation(population);

			// Apply mutation
			population = ga.mutatePopulation(population);

			// Evaluate population
			ga.evalPopulation(population);

			// Increment the current generation
			generation++;
		}

		/**
		 * We're out of the loop now, which means we have a perfect solution 
		 */
		System.out.println("Found solution in " + generation + " generations");
		System.out.println("Best solution: " + population.getFittest(0).toString());
	}
}
