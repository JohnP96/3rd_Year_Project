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
            ArrayList<Double> avgResults = new ArrayList<>();
            long avgTime = 0;
            for(File file : Objects.requireNonNull(dir.listFiles())) {
                String fileName = file.getName().replaceFirst("[.][^.]+$", "");
                System.out.println(fileName);
                ArrayList<Double> results = new ArrayList<>();
                TabInterface.luteToGuitar(fileName, fileName);
                for (int i = 0; i < 50; i++) {
                    long startTime = System.currentTimeMillis();
                    TabGA ga = new TabGA(fileName, 500, 100, 0.01);
                    Tab tab = ga.mostFitTab();
                    TabInterface.createTabFile(fileName, tab);
                    results.add(TabInterface.compareTabs(fileName, fileName));
                    long endTime = System.currentTimeMillis();
                    avgTime += (endTime-startTime);
                    System.out.println("Iteration: " + (i+1) + " Time taken: " + (endTime - startTime));
                }
                double avg = 0;
                for (Double i : results) {
                    if(i == Integer.MAX_VALUE){
                        System.out.println("Comparison Error");
                        System.out.println(fileName);
                        throw new Exception();
                    }
                    avg += i;
                }
                avgResults.add(avg/results.size());
            }
            double avgAverage = 0;
            for(Double i : avgResults){
                avgAverage += i;
            }
            System.out.println(avgResults);
            System.out.println(avgAverage/avgResults.size());
            System.out.println("Average time taken: " + (avgTime/50) + "ms");


//                if(fileName.equals("si_par_souffrir")) {
//                    Random rand = new Random();
//                    MidiReader mr = new MidiReader(fileName + ".mid");
//                    GuitarNote[] notes = mr.getNoteSequence(rand);
//                    Tab tab = new Tab(notes, rand);
//                    int chordCount = 0;
//                    for(Chord chord : tab.getChords()){
//                        chordCount++;
//                        if(chordCount > 124 && chordCount < 128){
//                            System.out.println(chord);
//                            for(GuitarNote note : chord.getNotes()){
//                                System.out.println(note + "\n");
//                            }
//                        }
//                    }
//                    System.out.println(chordCount);
//                    for(Chord chord : tab.getChords()){
//                        System.out.println(chord);
////                    }
//                }
//            }

//            for(File file : Objects.requireNonNull(dir.listFiles())) {
//                String fileName = file.getName().replaceFirst("[.][^.]+$", "");
//                MidiReader mr = new MidiReader(fileName + ".mid");
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
