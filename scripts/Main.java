/** Main file for running and testing code */

import javax.sound.midi.*;
import java.io.File;

public class Main {
    public static void main(String[] args){

        try{
            Sequence seq = MidiSystem.getSequence(new File("rogero.midi"));
            System.out.println(seq.getTracks().length);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
