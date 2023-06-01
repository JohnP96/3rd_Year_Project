/************************************************************************************
 * TabGA

 * Author: John Pederson

 * Last edited: 03/04/2023

 * Description: Implementation of a genetic algorithm to transcribe guitar tablature
 * from a MIDI file input.

 * Bug fixes/improvements: Error handling needs to be improved.
 ************************************************************************************/
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TabGA {
    private String name;
    private int populationSize;
    private double mutationRate; // Percentage chance of mutation for each individual
    private int numGenerations;
    private ArrayList<Tab> population;

    // To be used for crossover and also passed down to GuitarNote objects
    private Random rand;

    public TabGA(){}

    /**
     * Initialise the genetic algorithm class.
     * @param name the name of the MIDI file to be intabulated, this will also be
     *             the name of the output.
     * @param populationSize the number of Tab objects in the initial population.
     */
    public TabGA(String name, int populationSize, int numGenerations, double mutationRate) throws Exception{
        this.name = name;
        this.populationSize = populationSize;
        this.numGenerations = numGenerations;
        this.mutationRate = mutationRate;
        population = new ArrayList<>();
        rand = new Random();
        MidiReader reader;
        try {
             reader = new MidiReader(name + ".mid");
        }
        catch (FileNotFoundException e){
             reader = new MidiReader(name + ".midi");
        }
        for (int p = 0; p < populationSize; p++) {
            Tab tab = new Tab(reader.getNoteSequence(rand), rand);
            population.add(tab);
        }
    }

    public String getName(){
        return name;
    }

    public int getPopulationSize(){
        return populationSize;
    }

    public ArrayList<Tab> getPopulation(){
        return new ArrayList<>(population);
    }

    public double getMutationRate(){
        return mutationRate;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPopulationSize(int populationSize){
        this.populationSize = populationSize;
    }

    public void setMutationRate(float mutationRate) {
        this.mutationRate = mutationRate;
    }

    /**
     * Selects the pool of individuals to use as parents in the
     * crossover method using stochastic universal sampling.
     * @return An ArrayList of the selected Tab objects
     */
    private ArrayList<Tab> selection(){
        Tab[] selected = new Tab[populationSize];
//        double totalFitness = 0;
//        for(Tab tab : population){
//            totalFitness += tab.calculateFitness();
//        }
//        double interval = totalFitness/populationSize; // Create equal intervals between each selected member
////        System.out.println("Interval = " + interval);
//        double startPoint = rand.nextDouble()*interval;
//        double fitnessLimit;
//        int fitnessSum;
//        int popIndex;
//        for(int i = 0; i<populationSize; i++){
//            fitnessLimit = startPoint + interval*i;
//            popIndex = 0;
//            fitnessSum = population.get(popIndex).getFitness();
//            while (fitnessSum < fitnessLimit){
//                popIndex++;
//                fitnessSum += population.get(popIndex).getFitness();
//            }
////            System.out.println("i = " + i + "and popIndex = " + popIndex);
//            selected[i] = population.get(popIndex);
//        }

//        Tournament Selection
        int tourneySize = 10;
        for(int i=0; i<populationSize;i++) {
            ArrayList<Tab> tourney = new ArrayList<>();
            for(int j=0; j<tourneySize; j++){
                tourney.add(population.get(rand.nextInt(populationSize)));
            }
            Collections.sort(tourney);
            selected[i] = tourney.get(0);
        }


        return new ArrayList<>(List.of(selected));
    }

    /**
     * Creates a new chord from the given chord, possibly with different fingering
     * @param chord The chord to be mutated
     */
    private void mutate(Chord chord) throws Exception{
        ArrayList<GuitarNote> notes = chord.getNotes();
        chord.clearNotes();
        ArrayList<Integer> unoccupiedStrings = new ArrayList<>();
        for(int i=0; i<6; i++){
            unoccupiedStrings.add(i);
        }
        if(!chord.addNotes(notes, 0, unoccupiedStrings)){
            throw new Exception("Chord error");
        }
    }

    /**
     * Combines parent tabs into children by randomly selecting a break point and
     * combining the chords from the first parent before the break and chords from
     * the second parent after the break, then vice versa for the second child.
     */
    private void crossover(ArrayList<Tab> parents) throws Exception{
        ArrayList<Tab> children = new ArrayList<>();
        int numChords = population.get(0).getChords().size();
        int parentsSize;
        int randomParent;
        double mutationRand;
        while ((parentsSize = parents.size()) > 1){
            randomParent = rand.nextInt(parentsSize);
            ArrayList<Chord> parentOne = population.get(randomParent).getChords();
            parents.remove(randomParent);
            randomParent = rand.nextInt(parentsSize-1);
            ArrayList<Chord> parentTwo = population.get(randomParent).getChords();
            parents.remove(randomParent);
            /* In the case of the breakpoint being 0, the parents simply move to the next
               generation as they are */
            int breakPointOne = rand.nextInt(numChords);
            int breakPointTwo = rand.nextInt(breakPointOne, numChords);
            ArrayList<Chord> childOne = new ArrayList<>(parentOne.subList(0, breakPointOne));
            ArrayList<Chord> childTwo = new ArrayList<>(parentTwo.subList(0, breakPointOne));
            childOne.addAll(parentTwo.subList(breakPointOne, breakPointTwo));
            childTwo.addAll(parentOne.subList(breakPointOne, breakPointTwo));
            childOne.addAll(parentTwo.subList(breakPointTwo, numChords));
            childTwo.addAll(parentOne.subList(breakPointTwo, numChords));
            for(Chord chord : childOne){
                mutationRand = rand.nextDouble(100);
                if(mutationRand < mutationRate){
                    mutate(chord);
                }
            }
            for(Chord chord : childTwo){
                mutationRand = rand.nextDouble(100);
                if(mutationRand < mutationRate){
                    mutate(chord);
                }
            }
            children.add(new Tab(childOne));
            children.add(new Tab(childTwo));
        }
        population = children;
    }

    /**
     * Converges on the most fit tab found by running the crossover
     * function for the assigned number of generations.
     */
    public Tab mostFitTab() throws Exception{
        double avgFitness;
        for (int gen=0; gen<numGenerations; gen++){
            avgFitness = 0;
            for(Tab tab : population){
                avgFitness += tab.calculateFitness();

            }
//            System.out.println("Gen " + gen + ": " + avgFitness/populationSize);
            ArrayList<Tab> parents = selection();
//
//            System.out.println(avgFitness/populationSize);
            crossover(parents);

        }
        avgFitness = 0;
        for(Tab tab : population){
            avgFitness += tab.calculateFitness();
        }
//        System.out.println("Next Gen: " + avgFitness/populationSize);
        Collections.sort(population);
        return population.get(0);
    }
}
