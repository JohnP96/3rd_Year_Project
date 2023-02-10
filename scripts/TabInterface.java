/************************************************************************************
 * TAB Interface

 * Author: John Pederson

 * Last edited: 10/02/2023

 * Description: Class for interacting with TAB software created by Wayne Cripps

 * Bug fixes/improvements: Error handling
 ************************************************************************************/
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
     * Compares a generated guitar .tab file with the original lute .tab file.
     * @param guitarTabName A string denoting the name of the guitar .tab file
     * @param luteTabName A string denoting the name of the lute .tab file
     * @return An integer denoting the number of differences between the tabs.
     * Returns max integer value if they are different lengths.
     */
    public static int compareTabs(String guitarTabName, String luteTabName) throws FileNotFoundException {
        int score = 0;
        File guitarTab = new File("tab_files/" + guitarTabName + ".tab");
        File luteTab = new File("tab_files/" + luteTabName + ".tab");
        Scanner guitarFileScanner = new Scanner(guitarTab );
        Scanner luteFileScanner = new Scanner(luteTab);
        guitarFileScanner.useDelimiter("\n");
        luteFileScanner.useDelimiter("\n");
        String luteLine = "";
        String guitarLine = "";
        Pattern chordLine = Pattern.compile("^0");
        while(guitarFileScanner.hasNextLine()){
            // Ignores unimportant lines like those for bar breaks etc.
            while(!guitarFileScanner.hasNext(chordLine)){
                guitarFileScanner.next();
            }
            guitarLine = guitarFileScanner.next(chordLine);

            while(luteFileScanner.hasNext()){
                luteLine = luteFileScanner.next();
                // Same as before but harder due to non-constant flag values
                while(luteLine.length() < 2 || luteLine.toCharArray()[0] == '-'
                        || luteLine.toCharArray()[0] == '{'){
                    luteLine = luteFileScanner.next();
                }
            }

            char[] guitarChars = guitarLine.toCharArray();
            char[] luteChars = luteLine.toCharArray();
            int flagAllowance = 0; // Number of characters to skip (for the flag values)
            if(luteChars[0] == '#'){
                flagAllowance = 1;
            }
            for(int i=1; i<guitarChars.length; i++){
                int guitarUnicode = guitarChars[i];
                int luteUnicode = luteChars[i+flagAllowance];
                if(guitarUnicode == 78){
                    i = i+2;
                    guitarUnicode = guitarChars[i];
                    for(int j=0; j<3; j++){
                        if(guitarUnicode != luteUnicode - 108){
                            score ++;
                        }
                    }
                }
                else {
                    if (guitarUnicode != luteUnicode - 30) {
                        score++;
                    }
                }
            }
        }

        return score;
    }

    /*
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
