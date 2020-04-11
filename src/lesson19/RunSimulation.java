package lesson19;

import java.util.Random;
/**
 *  
 * @author tim hickey
 *
 */

public class RunSimulation {
	// the maximum number of days the simulation will run
	private static int MAX_TICKS=1000;

	private Random random = new Random();

	public static void main(String[] args) {
		// first we get the simulation parameters
		// from the command line

		int width = Integer.parseInt(args[0]);
		int height = Integer.parseInt(args[1]);
		int numStayHome = Integer.parseInt(args[2]);
		int numEssential = Integer.parseInt(args[3]);
		int numSkeptic = Integer.parseInt(args[4]);
		int numFrequentFliers = Integer.parseInt(args[5]);

		// next we create the population and the country
		Population population;

		//population = new Population(numPeople);
		//population = new AllStayAtHome(numPeople);
		//int numEssential = numPeople/10;
		//int numOther = numPeople/20;
		//int numStayHome = numPeople - numEssential - numOther;
		System.out.printf("Width %d, height %d, home %d, ess %d, skp %d, fliers %d\n", width, height, numStayHome, numEssential, numSkeptic, numFrequentFliers);
		System.out.println("Creating population");
		population = new MixedPopulation(numStayHome, numEssential, numSkeptic, numFrequentFliers);

		System.out.println("Creating people");
		population.createPeople();

		Country country = new Country(width,height);
		// and add a link to the population in the country
		country.population = population;
		// next we place the people into the country randomly
		System.out.println("Placing people");
		population.placePeople(country);

		System.out.println("Initial State of the Country");
		country.printCountry();

		System.out.println("\nTracking the Infection");
		for(int k=0;k<MAX_TICKS; k++) {
			country.simulateOneStep();
			country.printState(k);

			if (country.numInfected==0) {
				break;
			}
		}
		System.out.println("\nFinal State of the Country");
		country.printCountry();

	}




}
