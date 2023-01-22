/************************************************************************************
 * TabGA

 * Author: John Pederson

 * Last edited: 02/01/2023

 * Description: Implementation of a genetic algorithm to transcribe guitar tablature
 * from a MIDI file input.

 * Bug fixes/improvements: Error handling needs to be improved.
 ************************************************************************************/

public class TabGA {

    private String name;
    private int populationSize;
    private int mutationRate;


    public TabGA(){}
    public TabGA(String name){
        this.name = name;
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


}
