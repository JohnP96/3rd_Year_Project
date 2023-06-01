import java.io.*;
import java.util.*;

/** Main file for running and testing code */

public class Main {
    public static void main(String[] args){

        try {
//            File dir = new File("midi_files");
////            for(int pop=100; pop<1001; pop+=100) {
////                System.out.println("Pop: " + pop);
//                ArrayList<Double> avgResults = new ArrayList<>();
////                avgResults.add((double)pop);
//                long avgTime = 0;
//                for (File file : Objects.requireNonNull(dir.listFiles())) {
//                    String fileName = file.getName().replaceFirst("[.][^.]+$", "");
//                                    System.out.println(fileName);
//                    ArrayList<Double> results = new ArrayList<>();
//                    TabInterface.luteToGuitar(fileName, fileName);
//                    for (int i = 0; i < 10; i++) {
//                        long startTime = System.currentTimeMillis();
//                        TabGA ga = new TabGA(fileName, 300, 100, 1);
//                        Tab tab = ga.mostFitTab();
//                        //                    System.out.println(tab.getChords());
//                        TabInterface.createTabFile(fileName, tab);
//                        results.add(TabInterface.compareTabs(fileName, fileName));
//                        long endTime = System.currentTimeMillis();
//                        avgTime += (endTime - startTime);
////                                            System.out.println("Iteration: " + (i+1) + " Time taken: " + (endTime - startTime));
//                    }
//                    double avg = 0;
//                    for (Double i : results) {
//                        if (i == Integer.MAX_VALUE) {
//                            System.out.println("Comparison Error");
//                            System.out.println(fileName);
//                            throw new Exception();
//                        }
//                        avg += i;
//                    }
//                    avgResults.add(avg / results.size());
//                                    System.out.println(fileName + " results: " + avg/ results.size());
//                }
//                double avgAverage = 0;
//                for (Double i : avgResults) {
//                    avgAverage += i;
//                }
////            }
//            System.out.println(avgResults);
//            System.out.println(avgAverage/avgResults.size());
//            System.out.println("Average time taken: " + (avgTime/50) + "ms");
//            Random rand = new Random();
//            GuitarNote a = new GuitarNote(Notes.A, 4, 1, rand);
//            GuitarNote e = new GuitarNote(Notes.E, 1, 0, rand);
//            GuitarNote c = new GuitarNote(Notes.C, 3, 2, rand);
//            GuitarNote[] notes = new GuitarNote[]{e, a, c};
//            GuitarNote[] notes1 = new GuitarNote[]{new GuitarNote(Notes.E, 2, 0, rand),
//            new GuitarNote(Notes.A, 2, 0, rand), new GuitarNote(Notes.D, 3, 0, rand)};
//
//            Tab tab1 = new Tab(notes, rand);
//            Tab tab2 = new Tab(notes1,rand);
//            ArrayList<Tab> tabs = new ArrayList<>();
//            tabs.add(tab1);
//            tabs.add(tab2);
//            for (Tab t : tabs){
//                System.out.println(t.calculateFitness());
//                System.out.println(t);
//            }
//            Collections.sort(tabs);
//            for (Tab t : tabs){
//                System.out.println(t);
//            }
//            String name = "180a_Contrapunto_secondo_tenor";
//            TabGA tga = new TabGA(name, 1000, 100, 1);
//            Tab mft = tga.mostFitTab();
//            TabInterface.createTabFile(name, mft);
//            System.out.println(TabInterface.compareTabs(name, name));

            TabInterface.sendToTAB("180a_Contrapunto_secondo_tenor");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
