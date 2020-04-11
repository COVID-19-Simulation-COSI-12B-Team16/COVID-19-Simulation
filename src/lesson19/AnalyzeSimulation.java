package lesson19;

import java.util.Random;

public class AnalyzeSimulation {
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
        int numSusceptible = Integer.parseInt(args[6]);
        int repeat = Integer.parseInt(args[7]);

        Population population;

        int averageDays = 0;
        int averageInfectedPpl = 0;
        int times = 0;

        while(times++ < repeat) {
            population = new MixedPopulation(numStayHome, numEssential, numSkeptic, numFrequentFliers, numSusceptible);
            population.createPeople();
            Country country = new Country(width, height);
            country.population = population;
            population.placePeople(country);

            int days = 0;
            int maxNumOfInfected = 0;

            for (; days < MAX_TICKS; days++) {
                country.simulateOneStep();
                if (country.numInfected == 0) {
                    break;
                }
                maxNumOfInfected = Math.max(maxNumOfInfected, country.getNumInfected());
            }
            System.out.printf("Simulation %d, total days %d, max infected # of ppl %d \n", times, days, maxNumOfInfected);
            averageDays += days;
            averageInfectedPpl += country.getNumRecovered();
        }

        averageDays /= repeat;
        averageInfectedPpl /= repeat;
        System.out.printf("Width %d, height %d, home %d, ess %d, skp %d, fliers %d, susceptible %d \n", width, height, numStayHome, numEssential, numSkeptic, numFrequentFliers, numSusceptible);
        System.out.printf("Simulation average days to recover %d, average infected people %d, with %d times of repeats\n", averageDays, averageInfectedPpl, repeat);
    }
}
