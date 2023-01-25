/************************************************************************************
 * TabGA

 * Author: John Pederson

 * Last edited: 02/01/2023

 * Description: Implementation of a genetic algorithm to transcribe guitar tablature
 * from a MIDI file input.

 * Bug fixes/improvements: Error handling needs to be improved.
 ************************************************************************************/
import java.util.ArrayList;

public class TabGA {

    private String name;
    private int populationSize;
    private int mutationRate;
    private int numGenerations;
    private ArrayList<Tab> population;


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
        MidiReader reader = new MidiReader(name + ".midi");
        for (int p=0; p<populationSize; p++){
            Tab tab = new Tab(reader.getNoteSequence());
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

    public void crossover(){

    }


}
