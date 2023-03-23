import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
/** Main file for running and testing code */

public class Main {
    public static void main(String[] args){

        try {
            File dir = new File("midi_files");
//            ArrayList<Double> avgResults = new ArrayList<>();
//            for(File file : Objects.requireNonNull(dir.listFiles())) {
//                String fileName = file.getName().replaceFirst("[.][^.]+$", "");
//                System.out.println(fileName);
//                ArrayList<Double> results = new ArrayList<>();
//                TabInterface.luteToGuitar(fileName, fileName);
//                for (int i = 0; i < 20; i++) {
//                    long startTime = System.currentTimeMillis();
//                    TabGA ga = new TabGA(fileName, 500, 100, 0.01);
//                    Tab tab = ga.mostFitTab();
//                    TabInterface.createTabFile(fileName, tab);
//                    results.add(TabInterface.compareTabs(fileName, fileName));
//                    long endTime = System.currentTimeMillis();
//                    System.out.println("Iteration: " + (i+1) + " Time taken: " + (endTime - startTime));
//                }
//                double avg = 0;
//                for (Double i : results) {
//                    avg += i;
//                }
//                avgResults.add(avg/results.size());
//            }
//            double avgAverage = 0;
//            for(Double i : avgResults){
//                avgAverage += i;
//            }
//
//            System.out.println(avgAverage/avgResults.size());

//            for(File file : Objects.requireNonNull(dir.listFiles())) {
//                String fileName = file.getName().replaceFirst("[.][^.]+$", "");
//                MidiReader mr = new MidiReader(fileName + ".midi");
//                GuitarNote[] notes = mr.getNoteSequence(new Random());
//                int count = 0;
//                for (GuitarNote n : notes) {
//                    count++;
//                    if(n.getOctave() == 2 && n.getNote() == Notes.F){
//                        System.out.println(fileName);
//                        System.out.println("Note number " + count);
//                    }
//                }
//            }

//            File dir = new File("tablature/Dowland/tab");
//            String line;
//            String lastLine = "a";
//            for(File file : Objects.requireNonNull(dir.listFiles())){
//                String nameTab = file.getName();
//                String name = nameTab.substring(0, nameTab.indexOf('.'));
//                FileInputStream fis = new FileInputStream(file);
//                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
//                PrintWriter writer = new PrintWriter("tablature/Dowland/newtab/" + name + ".tab");
//                int counter = 0;
//                while((line = reader.readLine()) != null){
//                    counter++;
//                    if(!line.equals("P") && !line.equals("b")) {
//                        writer.write(line + "\n");
//                    }
//                    if(counter % 20 == 0){
//                        if(!lastLine.equals("")) {
//                            writer.write("\n");
//                        }
//                    }
//                    lastLine = line;
//                }
//                reader.close();
//                writer.close();
//                fis.close();
//            }
//            File file = Objects.requireNonNull(dir.listFiles())[1];
//            String nameTab = file.getName();
//            String name = nameTab.substring(0, nameTab.indexOf('.'));
//            FileInputStream fis = new FileInputStream(file);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
//            PrintWriter writer = new PrintWriter("tablature/Dowland/newtab/" + name + ".tab");
//            int counter = 0;
//            while((line = reader.readLine()) != null){
//                counter++;
//                writer.write(line + "\n");
//                if(counter % 20 == 0){
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
//            dir = new File("original_tab_lute");
//            for(File file : Objects.requireNonNull(dir.listFiles())) {
//                System.out.println(file.getName());
//                Scanner scan = new Scanner(file);
//                scan.useDelimiter("\n");
//                int lineCount = 0;
//                while (scan.hasNext()) {
//                    String line = scan.next().trim();
//                    lineCount++;
//                    int leng = line.length();
//                    if(leng > 7 && line.toCharArray()[0] != '#' && line.toCharArray()[1] != '.'){
//                        System.out.println("Line " + line + "\nat line num " + lineCount + " with length " + leng);
//                    }
//                }
//            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
