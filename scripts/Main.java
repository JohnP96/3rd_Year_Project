import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/** Main file for running and testing code */

public class Main {
    public static void main(String[] args){

        try {
//            TabGA ga = new TabGA("rogero", 1000, 100);
//            Tab tab = ga.mostFitTab();
//            for(Chord chord : tab.getChords()){
//                for(GuitarNote note: chord.getNotes()){
//                    System.out.println(note.getFretNumber() + " " + note.getStringNumber());
//                }
//            }
//            TabInterface.createTabFile("Rogero_Test", tab);
//            TabInterface.sendToTAB("Rogero_Test");
            System.out.println(TabInterface.compareTabs("Rogero_Test", "rogero_original_guitar"));
//            Random rand = new Random();
//            GuitarNote n = new GuitarNote(Notes.G, 3, 0, rand);
//            GuitarNote a = new GuitarNote(Notes.A, 2, 0, rand);
//            GuitarNote b = new GuitarNote(Notes.D, 3, 0, rand);
//            Chord c = new Chord(n, rand);
//            System.out.println(c.addNote(a));
//            System.out.println(c.addNote(b));
//            System.out.println(c);
//            for (GuitarNote note:c.getNotes()) {
//                System.out.println(note);
////            }
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
