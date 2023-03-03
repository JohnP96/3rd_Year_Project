/************************************************************************************
 * TabGA

 * Author: John Pederson

 * Last edited: 03/03/2023

 * Description: Implementation of a genetic algorithm to transcribe guitar tablature
 * from a MIDI file input.

 * Bug fixes/improvements: Error handling needs to be improved. Add random break
 * point and parent selection for crossover. Add mutation.
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

    // To be used for crossover and also passed down to GuitarNote objects
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

    private ArrayList<Tab> selection(){
        for(Tab tab : population){
            tab.calculateFitness();
        }
        Collections.sort(population);
        return new ArrayList<>(population.subList(0, populationSize/2));
    }

    /**
     * Randomly selects parent tabs from the population (weighted by fitness) and combines
     * them into children by randomly selecting a break point and combining the chords
     * from the first parent before the break and chords from the second parent after the
     * break, then vice versa for the second child.
     */
    private void crossover(){
        ArrayList<Tab> children = new ArrayList<>();
        ArrayList<Tab> parents = selection();
        int numChords = population.get(0).getChords().size();
        int parentsSize;
        int randomParent;
        while ((parentsSize = parents.size()) > 1){
            randomParent = rand.nextInt(parentsSize);
            ArrayList<Chord> parentOne = population.get(randomParent).getChords();
            parents.remove(randomParent);
            randomParent = rand.nextInt(parentsSize-1);
            ArrayList<Chord> parentTwo = population.get(randomParent).getChords();
            parents.remove(randomParent);
            /* In the case of the breakpoint being 0, the parents simply move to the next
               generation as they are */
            int breakPoint = rand.nextInt(numChords);
            ArrayList<Chord> childOne = new ArrayList<>(parentOne.subList(0, breakPoint));
            ArrayList<Chord> childTwo = new ArrayList<>(parentTwo.subList(0, breakPoint));
            childOne.addAll(parentTwo.subList(breakPoint, numChords));
            childTwo.addAll(parentOne.subList(breakPoint, numChords));
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
