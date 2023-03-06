import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/** Main file for running and testing code */

public class Main {
    public static void main(String[] args){

        try {
//            Random rand = new Random();
            long startTime = System.currentTimeMillis();
            ArrayList<Float> results = new ArrayList<>();

            for(int i=0; i<100; i++) {
                TabGA ga = new TabGA("rogero", 500, 100, 0.0);
                Tab tab = ga.mostFitTab();
                TabInterface.createTabFile("Rogero_fitness1_Trunc_0.05", tab);
                results.add(TabInterface.compareTabs("Rogero_fitness1_Trunc_0.05", "rogero_to_guitar"));
            }
            long endTime = System.currentTimeMillis();
            System.out.println(results);
            double avg = 0;
            for(Float i : results){
                avg += i;
            }
            avg = avg/results.size();
            System.out.println(avg);
            System.out.println(endTime-startTime);

//            TabInterface.sendToTAB("Rogero_Truncated");
//            System.out.println(TabInterface.compareTabs("Rogero_Test", "rogero_to_guitar"));
//            System.out.println(TabInterface.compareTabs("Rogero_Truncated", "rogero_to_guitar"));

//            TabInterface.luteToGuitar("rogero", "rogero_to_guitar");

//            Integer[][] notes = new Integer[12][];
//            notes[0] = GuitarNote.cPositions;
//            notes[1] = GuitarNote.cSharpPositions;
//            notes[2] = GuitarNote.dPositions;
//            notes[3] = GuitarNote.dSharpPositions;
//            notes[4] = GuitarNote.ePositions;
//            notes[5] = GuitarNote.fPositions;
//            notes[6] = GuitarNote.fSharpPositions;
//            notes[7] = GuitarNote.gPositions;
//            notes[8] = GuitarNote.gSharpPositions;
//            notes[9] = GuitarNote.aPositions;
//            notes[10] = GuitarNote.aSharpPositions;
//            notes[11] = GuitarNote.bPositions;
//
//            ArrayList<Integer> missing = new ArrayList<>();
//
//            for(int i = 0; i<78; i++){
//                boolean match = false;
//                for(Integer[] array : notes){
//                    for(Integer pos : array){
//                        if(pos == i){
//                            match = true;
//                        }
//                    }
//                }
//                if(!match){
//                    missing.add(i);
//                }
//            }
//            for(Integer m : missing){
//                System.out.println(m);
//                System.out.println("String " + m%6 + " Fret " + (int)(m/6));
//            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
