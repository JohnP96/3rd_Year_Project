/************************************************************************************
 * TabGA

 * Author: John Pederson

 * Last edited: 02/01/2023

 * Description: Implementation of a genetic algorithm to transcribe guitar tablature
 * from a MIDI file input.

 * Bug fixes/improvements: Error handling needs to be improved.
 ************************************************************************************/
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class TabGA {

    private String name;
    private int populationSize;
    private int mutationRate;

    /**
     * Constructors
     */

    public TabGA(){}
    public TabGA(String name){
        this.name = name;
    }


    /**
     * Standard get and set methods (primarily for testing and optimising
     * the algorithm)
     */

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
     * Read in MIDI file and convert into sequence of Note objects -
     * currently only allows for one MIDI track
     */
    public void readMIDI(){
    }

    /**
     * Sends the generated .tab file to the TAB software to be converted into
     * readable tablature
     * @return A boolean for whether the function was successful or not
     */
    public boolean sendToTAB(){
        try {
            String output = "../output_tab/" + name;
            String input = "../tab_files/" + name + ".tab";

            // Run TAB executable in console
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec(new String[]{"TAB/tab.exe", "-o", output, input},
                    null, new File("TAB"));

            // Print console output from TAB software (in case of errors)
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = inputStream.readLine()) != null)
                System.out.println(line);

            p.waitFor();
            if(p.exitValue() == 0){ // TAB returns 0 if successful and -1 on failure
                return true;
            }
        }
        catch(Exception e){
            // IOException or InterruptedException
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Sends the generated .tab file to the TAB software to be converted into
     * readable tablature
     * @param name The name of the '.tab' file to send to TAB
     * @return A boolean for whether the function was successful or not

    public boolean sendToTAB(String name){
        try {
            String output = "../output_tab/" + name;
            String input = "../tab_files/" + name + ".tab";

            // Run TAB executable in console
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec(new String[]{"TAB/tab.exe", "-o", output, input},
                    null, new File("TAB"));

            // Print console output from TAB software (in case of errors)
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = inputStream.readLine()) != null)
                System.out.println(line);

            p.waitFor();
            if(p.exitValue() == 0){ // TAB returns 0 if successful and -1 on failure
                return true;
            }
        }
        catch(Exception e){
            // IOException or InterruptedException
            e.printStackTrace();
        }
        return false;
    }*/
}
