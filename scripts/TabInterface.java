/************************************************************************************
 * TAB Interface

 * Author: John Pederson

 * Last edited: 27/03/2023

 * Description: Class for interacting with TAB software created by Wayne Cripps

 * Bug fixes/improvements: Error handling,
 ************************************************************************************/
import java.io.*;
import java.util.Scanner;

public class  TabInterface {

    /**
     * Creates a .tab file from the Tab object provided
     * @return A boolean for whether the function was successful or not
     */
    public static boolean createTabFile(String name, Tab tab) throws IOException{
        try(FileWriter writer = new FileWriter("generated_tab/" + name + ".tab")) {
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
        return false;
    }


    /**
     * Sends the generated .tab file to the TAB software to be converted into
     * readable tablature
     * @return A boolean for whether the function was successful or not
     */
    public static boolean sendToTAB(String name) throws IOException, InterruptedException{

        String output = "../output_tab/" + name;
        String input = "../generated_tab/" + name + ".tab";

        // Run TAB executable in console
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec(new String[]{"TAB/tab.exe", "-o", output, input},
                null, new File("TAB"));

        // Print console output from TAB software (in case of errors)
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        while ((line = inputStream.readLine()) != null) {
            System.out.println(line);
        }
        p.waitFor();
        // TAB returns 0 if successful and -1 on failure
        return p.exitValue() == 0;
    }


    /**
     * Compares a generated .tab file with the original .tab file. Returning the
     * proportion of chords that are the same for both tabs.
     * @param generatedTabName A string denoting the name of the generated .tab file
     * @param originalTabName A string denoting the name of the original .tab file
     * @return A double denoting the proportion of matching chords between the two tabs.
     * i.e, 1 minus the number of different chords divided by the total number of chords
     * Returns max integer value if tabs are different lengths.
     */
    public static double compareTabs(String generatedTabName, String originalTabName) throws FileNotFoundException {
        float differences = 0;
        float numChords = 0;
        File generatedTab = new File("generated_tab/" + generatedTabName + ".tab");
        File originalTab = new File("original_tab/" + originalTabName + ".tab");
        Scanner genScan = new Scanner(generatedTab );
        Scanner origScan = new Scanner(originalTab);
        genScan.useDelimiter("\n");
        origScan.useDelimiter("\n");
        String genLine;
        String origLine;

        // Ignore the first two lines of header information
        for(int i=0; i<2; i++){
            if(genScan.hasNext() && origScan.hasNext()){
                genScan.next();
                origScan.next();
            }
        }

        while(genScan.hasNext() && origScan.hasNext()){
            numChords++;
            genLine = genScan.next();
            origLine = origScan.next();
            while(genLine.equals("")){
                genLine = genScan.next();
            }
            while(origLine.equals("")){
                origLine = origScan.next();
            }
            if(!origLine.equals(genLine)){
                differences++;
            }
        }
        // If the tabs are different lengths
        if(genScan.hasNext() || origScan.hasNext()){
            return Integer.MAX_VALUE;
        }
        return 1-(differences/numChords);
    }

    /**
     * Creates a guitar tab from the given lute tab. This can
     * then be used to compare with tab generated by the genetic
     * algorithm.
     * @param tabName A string denoting the name of the lute tab file
     * @throws FileNotFoundException If the lute tab cannot be found
     * @throws IOException If there is an error writing the guitar tab
     */
    public static void luteToGuitar(String tabName, String fileName) throws FileNotFoundException, IOException{
        try(FileWriter writer = new FileWriter("original_tab/" + fileName + ".tab")) {
            writer.append("$line=o\n").append("{").append(tabName).append("}\n").append("b\n");
            File luteTab = new File("original_tab_lute/" + tabName + ".tab");
            Scanner luteFileScanner = new Scanner(luteTab);
            luteFileScanner.useDelimiter("\n");
            String line = "";
            char[] lineChars;
            int counter = 0;

            while (luteFileScanner.hasNext()) {
                line = luteFileScanner.next().trim();
                //System.out.println("Line: " + line + "\nLength: " + line.length());
                // Ignores unimportant lines like those for bar breaks, titles etc.
                if(line.length() > 1) {
                    lineChars = line.toCharArray();
                    char firstChar = lineChars[0];
                    if (firstChar != '-' && firstChar != '{' && firstChar != '%' && firstChar != 'b'
                            && firstChar != '$' && firstChar != 'F' && firstChar != 'S' && firstChar != 'e') {

                        counter++; // Counter for when to add bar breaks

                        int flag = 1;
                        if (lineChars[0] == '#') {
                            flag = 2;
                        }
                        writer.append('0');
                        int stringNum = -1;
                        for (int i = flag; i < lineChars.length; i++) {
                            stringNum++;
                            char character = lineChars[i];
//                            System.out.println("Char in: " + character);
                            if (character != '.') {
                                int unicode = lineChars[i];
//                                System.out.println("Unicode: " + unicode);

                                if (unicode != 32) {

                                    /* The lute is tuned 3 semitones higher than a guitar one every string
                                     * but the 3rd highest, which is tuned 2 semitones higher */
                                    if (stringNum != 2) {
                                        if (unicode < 104) {
                                            character = (char) (unicode - 46);
                                        } else if (unicode < 106) {
                                            writer.append("N1");
                                            character = (char) (unicode - 56);
                                            // We ignore 106 as j is not used in lute tablature
                                        } else if (unicode > 106) {
                                            writer.append("N1");
                                            character = (char) (unicode - 57);
                                        }
                                    } else {
                                        if (unicode < 104) {
                                            character = (char) (unicode - 47);
                                        } else if (unicode < 106) {
                                            writer.append("N1");
                                            character = (char) (unicode - 57);
                                        } else if (unicode > 106) {
                                            writer.append("N1");
                                            character = (char) (unicode - 58);
                                        }
                                    }
                                }
//                                System.out.println("Char out: " + character);
                                writer.append(character);
                            }
                        }
                        writer.append('\n');
                        // Bar breaks are added at the same intervals as in the generated tab
                        if (counter % 10 == 0) {
                            writer.append("b\n\nb\n");
                        }
                    }
                }
            }
            writer.append("e\n");
        }
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
