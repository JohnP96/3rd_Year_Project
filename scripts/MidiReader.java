/************************************************************************************
 * MidiReader

 * Author: John Pederson

 * Last edited: 05/01/2023

 * Description: Adds functionality for reading notes from a midi sequence to be used
 * by the TabGA class. Currently does not include note timings.

 * Bug fixes/improvements: Update to deal with multiple tracks and different file
 * extensions. Update to include timings.
 ************************************************************************************/
import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class MidiReader {

    private Track track;

    /**
     * Constructors
     */

    public MidiReader(String file) throws InvalidMidiDataException, IOException {
        Sequence sequence = MidiSystem.getSequence(new File("midi_files/" + file));
        track = sequence.getTracks()[0];
    }

    /**
     * Takes MidiEvents and returns the actual notes
     * @return Array of Note objects in order
     */
    public void getNoteSequence(){
        for(int i=0; i< track.size(); i++){
            MidiEvent event = track.get(i);
            MidiMessage message = event.getMessage();
            if(message instanceof ShortMessage){

            }
            else{

            }
        }
        return;
    }

}
