import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/** Main file for running and testing code */

public class Main {
    public static void main(String[] args){

        try {
//            long startTime = System.currentTimeMillis();
//            ArrayList<Float> results = new ArrayList<>();
//            TabInterface.luteToGuitar("rogero", "rogero_to_guitar");
//            for(int i=0; i<100; i++) {
//                TabGA ga = new TabGA("rogero", 500, 100, 0.0);
//                Tab tab = ga.mostFitTab();
//                TabInterface.createTabFile("Rogero_fitness1_Trunc_0.05", tab);
//                results.add(TabInterface.compareTabs("Rogero_fitness1_Trunc_0.05", "rogero_to_guitar"));
//            }
//            long endTime = System.currentTimeMillis();
//            System.out.println(results);
//            double avg = 0;
//            for(Float i : results){
//                avg += i;
//            }
//            avg = avg/results.size();
//            System.out.println(avg);
//            System.out.println(endTime-startTime);


            File dir = new File("tablature/Dowland/newtab");
            File tabDir = new File("TAB");
            String line;
            for(File file : Objects.requireNonNull(dir.listFiles())){
                String nameTab = file.getName();
                String midiPath = "F:\\Documents\\GitHub\\3rd_Year_Project\\midi_files\\"
                        + nameTab.substring(0, nameTab.indexOf('.')) + ".midi";
                String tabPath = "F:\\Documents\\GitHub\\3rd_Year_Project\\tablature\\Dowland\\newtab\\" + nameTab;
                // Run TAB executable in console
//                Runtime rt = Runtime.getRuntime();
                ProcessBuilder p = new ProcessBuilder("F:\\Documents\\GitHub\\3rd_Year_Project\\TAB\\tab.exe", "-midi", tabPath);
                p.directory(tabDir);
                // Print console output from TAB software (in case of errors)

                p.inheritIO();
                Process proc = p.start();
                PrintWriter out = null;
                String lineFromInput;
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
                while((lineFromInput =inputStream.readLine()) != null){
                //create a print writer for writing to a file
                out = new PrintWriter(new FileWriter(midiPath));
                out.println(lineFromInput);
                }
                out.close();
            }

//            File dir = new File("tablature/Dowland/tab");
//            String line;
//            for(File file : Objects.requireNonNull(dir.listFiles())){
//                String nameTab = file.getName();
//                String name = nameTab.substring(0, nameTab.indexOf('.'));
//                FileInputStream fis = new FileInputStream(file);
//                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
//                PrintWriter writer = new PrintWriter("tablature/Dowland/newtab/" + name + ".tab");
//                int counter = 0;
//                while((line = reader.readLine()) != null){
//                    counter++;
//                    writer.write(line + "\n");
//                    if(counter % 10 == 0){
//                        writer.write("\n\n");
//                    }
//                }
//                reader.close();
//                writer.close();
//                fis.close();
//            }
//            File file = Objects.requireNonNull(dir.listFiles())[0];
//            String nameTab = file.getName();
//            String name = nameTab.substring(0, nameTab.indexOf('.'));
//            FileInputStream fis = new FileInputStream(file);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
//            PrintWriter writer = new PrintWriter("tablature/Dowland/newtab/" + name + ".tab");
//            int counter = 0;
//            while((line = reader.readLine()) != null){
//                counter++;
//                writer.write(line + "\n");
//                if(counter % 10 == 0){
//                    writer.write("\n\n");
//                }
//            }
//            reader.close();
//            writer.close();
//            fis.close();


            /*Integer[][] notes = new Integer[12][];
            notes[0] = GuitarNote.cPositions;
            notes[1] = GuitarNote.cSharpPositions;
            notes[2] = GuitarNote.dPositions;
            notes[3] = GuitarNote.dSharpPositions;
            notes[4] = GuitarNote.ePositions;
            notes[5] = GuitarNote.fPositions;
            notes[6] = GuitarNote.fSharpPositions;
            notes[7] = GuitarNote.gPositions;
            notes[8] = GuitarNote.gSharpPositions;
            notes[9] = GuitarNote.aPositions;
            notes[10] = GuitarNote.aSharpPositions;
            notes[11] = GuitarNote.bPositions;

            ArrayList<Integer> missing = new ArrayList<>();

            for(int i = 0; i<78; i++){
                boolean match = false;
                for(Integer[] array : notes){
                    for(Integer pos : array){
                        if(pos == i){
                            match = true;
                        }
                    }
                }
                if(!match){
                    missing.add(i);
                }
            }
            for(Integer m : missing){
                System.out.println(m);
                System.out.println("String " + m%6 + " Fret " + (int)(m/6));
            }*/

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
