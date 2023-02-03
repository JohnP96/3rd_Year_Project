/************************************************************************************
 * TabGA

 * Author: John Pederson

 * Last edited: 03/02/2023

 * Description: Implementation of a genetic algorithm to transcribe guitar tablature
 * from a MIDI file input.

 * Bug fixes/improvements: Error handling needs to be improved.
 ************************************************************************************/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TabGA {

    private String name;
    private int populationSize;
    private int mutationRate;
    private int numGenerations;
    private ArrayList<Tab> population;

    private Random rand;

    public TabGA(){}

    /**
     * Initialise the genetic algorithm class.
     * @param name the name of the MIDI file to be intabulated, this will also be
     *             the name of the output.
     * @param populationSize the number of Tab objects in the initial population.
     */
    public TabGA(String name, int populationSize, int numGenerations) throws Exception{
        this.name = name;
        this.populationSize = populationSize;
        this.numGenerations = numGenerations;
        population = new ArrayList<>();
        rand = new Random();
        MidiReader reader = new MidiReader(name + ".midi");
        for (int p=0; p<populationSize; p++){
            Tab tab = new Tab(reader.getNoteSequence(rand), rand);
            tab.calculateFitness();
            population.add(tab);
        }
    }

    public String getName(){
        return name;
    }

    public int getPopulationSize(){
        return populationSize;
    }

    public int getMutationRate(){
        return mutationRate;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPopulationSize(int populationSize){
        this.populationSize = populationSize;
    }

    public void setMutationRate(int mutationRate) {
        this.mutationRate = mutationRate;
    }

    /**
     * Pairs the most fit Tab objects (the first half of the population)
     * into children by combining the first half of the first parent's
     * chords with the second half of the second parents chords for the
     * first child and vice versa for the second child.
     */
    public void crossover(){
        Collections.sort(population);
        ArrayList<Tab> children = new ArrayList<>();
        for (int i=0; i<populationSize/2; i = i+2){
            ArrayList<Chord> parentOne = population.get(i).getChords();
            ArrayList<Chord> parentTwo = population.get(i+1).getChords();
            int numChords = parentOne.size();
            ArrayList<Chord> childOne = new ArrayList<>(parentOne.subList(0, numChords/2));
            ArrayList<Chord> childTwo = new ArrayList<>(parentTwo.subList(0, numChords/2));
            childOne.addAll(parentTwo.subList(numChords/2, numChords));
            childTwo.addAll(parentOne.subList(numChords/2, numChords));
            children.add(new Tab(childOne));
            children.add(new Tab(childTwo));
        }
        population = children;
    }

    /**
     * Converges on the most fit tab found by running the crossover
     * function for the assigned number of generations.
     */
    public Tab mostFitTab(){
        for (int gen=0; gen<numGenerations; gen++){
            crossover();
        }
        Collections.sort(population);
        return population.get(0);
    }
}
