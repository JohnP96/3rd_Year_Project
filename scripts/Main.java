import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/** Main file for running and testing code */

public class Main {
    public static void main(String[] args){

        try {
            MidiReader r = new MidiReader("rogero.midi");
            for(int i = 0; i<1000000; i++) {
                GuitarNote[] notes = r.getNoteSequence();
                Tab tab = new Tab(notes);
                for (ArrayList<Integer> x : tab.getChords()) {
                    if (x.size() > 1) {
                        Set<Integer> set = new HashSet<>(x);
                        if(set.size() < x.size()){
                            System.out.println("I want to die");
                        }
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
