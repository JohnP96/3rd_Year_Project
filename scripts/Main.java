import java.io.File;
import java.util.Random;

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
            System.out.println(TabInterface.compareTabs("Rogero_Test", "rogero"));

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
