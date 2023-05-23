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
                for (int i = 0; i < 5; i++) {
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
                System.out.println(fileName + " results: " + avg/ results.size());
            }
            double avgAverage = 0;
            for(Double i : avgResults){
                avgAverage += i;
            }
            System.out.println(avgResults);
            System.out.println(avgAverage/avgResults.size());
            System.out.println("Average time taken: " + (avgTime/50) + "ms");



        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
