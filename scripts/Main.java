import com.sun.security.jgss.GSSUtil;

import javax.sound.midi.*;
import java.io.File;

/** Main file for running and testing code */

public class Main {
    public static void main(String[] args){
        try {
            Sequence seq = MidiSystem.getSequence(new File("midi_files/rogero"+".midi"));
            Track track = seq.getTracks()[0];
            for(int i=0; i<track.size(); i++){
                MidiMessage message = track.get(i).getMessage();
                if(message instanceof ShortMessage shortMessage) {
                    if (shortMessage.getCommand() == ShortMessage.NOTE_ON) {
                        int key = shortMessage.getData1();
                        int velocity = shortMessage.getData2();
                        int octave = key / 12 - 1;
                        Notes note = Notes.values()[key % 12];
                        System.out.println("Key: " + key + " Octave: " + octave + " note " + note);
                    }
                }
                else{
                    System.out.println(message.getClass());
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
