import java.io.File;

/** Main file for running and testing code */

public class Main {
    public static void main(String[] args){

        try {
            MidiReader r = new MidiReader("rogero.midi");
            for (GuitarNote g : r.getNoteSequence()){
                System.out.println("" + g.getNote() + g.getOctave());
                System.out.println(g.getPossiblePositions());
            }
//            TabGA ga = new TabGA("rogero", 1000, 1000);
//            Tab tab = ga.mostFitTab();
//            TabInterface.createTabFile("Rogero_Test", tab);
//            TabInterface.sendToTAB("Rogero_Test");

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
