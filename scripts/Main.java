import java.io.File;
import java.util.Random;
import java.util.Scanner;

/** Main file for running and testing code */

public class Main {
    public static void main(String[] args){

        try {
            TabGA ga = new TabGA("rogero", 1000, 100);
            Tab tab = ga.mostFitTab();
            for(Chord chord : tab.getChords()){
                for(GuitarNote note: chord.getNotes()){
                    System.out.println(note.getFretNumber() + " " + note.getStringNumber());
                }
            }
            TabInterface.createTabFile("Rogero_Test", tab);
            TabInterface.sendToTAB("Rogero_Test");
            //System.out.println(TabInterface.compareTabs("Rogero_Test", "rogero_original_guitar"));
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
//            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
