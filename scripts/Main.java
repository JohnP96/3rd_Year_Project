import java.io.File;

/** Main file for running and testing code */

public class Main {
    public static void main(String[] args){

        try {
//            TabGA ga = new TabGA("rogero", 1000, 1000);
//            Tab tab = ga.mostFitTab();
//            TabInterface.createTabFile("Rogero_Test", tab);
//            TabInterface.sendToTAB("Rogero_Test");

            MidiReader r = new MidiReader("rogero.midi");
            GuitarNote[] g = r.getNoteSequence();
            for(GuitarNote n : g){
                System.out.println(n);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
