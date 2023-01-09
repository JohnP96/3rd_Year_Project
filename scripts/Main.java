import com.sun.security.jgss.GSSUtil;

import javax.sound.midi.*;
import java.io.File;

/** Main file for running and testing code */

public class Main {
    public static void main(String[] args){
        try {
            MidiReader mr = new MidiReader("rogero.midi");
            GuitarNote[] guitarNotes = mr.getNoteSequence();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
