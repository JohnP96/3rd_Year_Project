/************************************************************************************
 * TAB Interface

 * Author: John Pederson

 * Last edited: 06/02/2023

 * Description: Class for interacting with TAB software created by Wayne Cripps

 * Bug fixes/improvements: Error handling
 ************************************************************************************/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class  TabInterface {

    /**
     * Creates a .tab file from the Tab object provided
     * @return A boolean for whether the function was successful or not
     */
    public static boolean createTabFile(String name, Tab tab){
        try(FileWriter writer = new FileWriter("tab_files/" + name + ".tab");) {
            writer.append("$line=o\n").append("{").append(name).append("}\n").append("b\n");
            int counter = 0;
            for(Chord chord : tab.getChords()){
                counter++;
                int lastString = -1;
                writer.append('0'); // Sets flag
                for(GuitarNote note : chord.getNotes()){
                    /* Add spaces for un-played strings*/
                    for (int i = lastString; i<(note.getStringNumber()-1); i++){
                        writer.append(' ');
                    }
                    if(note.getFretNumber()<10) {
                        writer.append(note.getFretNumber().toString());
                    }
                    else{
                        writer.append('N').append(note.getFretNumber().toString());
                    }
                    lastString = note.getStringNumber();
                }
                writer.append('\n');
                if(counter % 10 == 0){
                    writer.append("b\n\nb\n");
                }

            }

            writer.append("e\n");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Sends the generated .tab file to the TAB software to be converted into
     * readable tablature
     * @return A boolean for whether the function was successful or not
     */
    public static boolean sendToTAB(String name){
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
//            while ((line = inputStream.readLine()) != null) {
//                System.out.println(line);
//            }
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
     * POSSIBLE IMPLEMENTATION FOR ASSIGNING NEW NAME TO TAB OUTPUT -  MAY FINISH LATER
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
