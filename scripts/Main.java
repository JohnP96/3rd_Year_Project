
/** Main file for running and testing code */

public class Main {
    public static void main(String[] args){

        try {
            MidiReader r = new MidiReader("rogero.midi");
            Tab tab = new Tab(r.getNoteSequence());
            TabInterface.createTabFile("test", tab);
            TabInterface.sendToTAB("test");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
