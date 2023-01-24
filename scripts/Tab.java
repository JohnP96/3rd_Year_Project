/************************************************************************************
 * Tab

 * Author: John Pederson

 * Last edited: 23/01/2023

 * Description: Object containing all fret positions within a guitar tab

 * Bug fixes/improvements:
 ************************************************************************************/
import java.util.ArrayList;
import java.util.Collections;

public class Tab {

    private ArrayList<ArrayList<GuitarNote>> chords;


    /**
     * Construct a Tab object from an array of GuitarNote objects, selecting random
     * positions for each note.
     * @param notes An array of GuitarNote objects.
     */
    public Tab(GuitarNote[] notes){
        /* Create chords from notes played on the same tick, then add these
        * to the array of positions */
        chords = new ArrayList<>();
        ArrayList<GuitarNote> chord = new ArrayList<>();
        long lastTick = 0;
        for (GuitarNote note : notes) {
            note.randomPosition();
            if (note.getStartTick() == lastTick) {
                // Check that the notes are being played on different strings
                for (GuitarNote n : chord){
                    if (note.compareTo(n) == 0){
                        note.removeCurrentString();
                    }
                }
                chord.add(note);
            }
            else {
                // Notes in chord are sorted by string number before being
                // added to ArrayList to make tabbing easier
                Collections.sort(chord);
                chords.add(chord);
                chord = new ArrayList<>();
                chord.add(note);
            }
            lastTick = note.getStartTick();
        }
        chords.add(chord);
    }

    public ArrayList<ArrayList<GuitarNote>> getChords(){
        return new ArrayList<>(chords);
    }
}
