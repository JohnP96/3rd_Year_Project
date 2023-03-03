import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/** Main file for running and testing code */

public class Main {
    public static void main(String[] args){

        try {
            TabGA ga = new TabGA("rogero", 1000, 100, 0.05);
            Tab tab = ga.mostFitTab();
            System.out.println(tab.getFitness());
//            for(Chord chord : tab.getChords()){
//                for(GuitarNote note: chord.getNotes()){
//                    System.out.println(note.getFretNumber() + " " + note.getStringNumber());
//                }
//            }
            TabInterface.createTabFile("Rogero_Test", tab);
//            TabInterface.sendToTAB("Rogero_Truncated");
//            System.out.println(TabInterface.compareTabs("Rogero_Test", "rogero_original_guitar"));
//            System.out.println(TabInterface.compareTabs("Rogero_Truncated", "rogero_original_guitar"));
//            Random rand = new Random();
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
//
//            File generatedTab = new File("tab_files/" + "Rogero_Test" + ".tab");
//            File originalTab = new File("tab_files/" + "rogero_original_guitar" + ".tab");
//            Scanner genScan = new Scanner(generatedTab );
//            Scanner origScan = new Scanner(originalTab);
//            genScan.useDelimiter("\n");
//            origScan.useDelimiter("\n");
//            while(genScan.hasNext() && origScan.hasNext()){
//                String genLine = genScan.next();
//                String origLine = origScan.next();
//                System.out.println(genLine);
//                System.out.println(origLine);
//                System.out.println(genLine.equals(origLine));
//                System.out.println(genLine.equals(origLine.trim()));
//                System.out.println(origLine.equals(genLine.trim()));
//            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
