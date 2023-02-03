/************************************************************************************
 * MidiReader

 * Author: John Pederson

 * Last edited: 03/02/2023

 * Description: Adds functionality for reading notes from a midi sequence to be used
 * by the TabGA class. Does not currently include note timings.

 * Bug fixes/improvements: Update to deal with multiple tracks and different file
 * extensions. Update to better deal with timings.
 ************************************************************************************/
import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MidiReader {

    private Track track;

    /**
     * Creates an object for reading MIDI files into GuitarNote objects.
     * @param file The path to the MIDI file to be read.
     * @throws InvalidMidiDataException If there is an error with the MIDI data.
     * @throws IOException If file cannot be found.
     */

    public MidiReader(String file) throws InvalidMidiDataException, IOException {
        Sequence sequence = MidiSystem.getSequence(new File("midi_files/" + file));
        track = sequence.getTracks()[0];
    }

    /**
     * Takes MidiEvents and returns the actual notes
     * @param rand A Random object to pass to the GuitarNotes which is
     *             then used to select random fret positions
     * @return Array of GuitarNote objects in order
     */
    public GuitarNote[] getNoteSequence(Random rand){
        ArrayList<GuitarNote> notes = new ArrayList<>();
        for(int i=0; i< track.size(); i++){
            MidiEvent event = track.get(i);
            MidiMessage message = event.getMessage();
            if(message instanceof ShortMessage shortMessage){
                if (shortMessage.getCommand() == ShortMessage.NOTE_ON) {
                    int key = shortMessage.getData1();
                    int octave = (key / 12) - 1;
                    /* Here we take the modulo of the key data to give the note value*/
                    notes.add(new GuitarNote(Notes.values()[key % 12], octave, event.getTick(), rand));
                }
            }
        }
        return notes.toArray(new GuitarNote[0]);
    }

}
