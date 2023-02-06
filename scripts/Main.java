import java.io.File;
import java.util.Random;

/** Main file for running and testing code */

public class Main {
    public static void main(String[] args){

        try {
//            MidiReader r = new MidiReader("rogero.midi");
//            Random rand = new Random();
//            Tab tab = new Tab(r.getNoteSequence(rand), rand);
//            GuitarNote g = new GuitarNote(Notes.E, 4, 1, rand);
//            for(int i=0; i<6; i++){
//                g.randomPositionOnString(i);
//                System.out.println(g.getStringNumber());
//                System.out.println(g.getFretNumber());
//            }
            TabGA ga = new TabGA("rogero", 1000, 100);
            Tab tab = ga.mostFitTab();
            for(Chord chord : tab.getChords()){
                for(GuitarNote note: chord.getNotes()){
                    System.out.println(note.getFretNumber() + " " + note.getStringNumber());
                }
            }
            TabInterface.createTabFile("Rogero_Test", tab);
            TabInterface.sendToTAB("Rogero_Test");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
