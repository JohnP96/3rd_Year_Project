/************************************************************************************
 * Tab

 * Author: John Pederson

 * Last edited: 23/01/2023

 * Description: Object containing all fret positions within a guitar tab

 * Bug fixes/improvements:
 ************************************************************************************/
import java.util.ArrayList;

public class Tab {

    private ArrayList<ArrayList<Integer>> chords;


    /**
     * Construct a Tab object from an array of GuitarNote objects, selecting random
     * positions for each note.
     * @param notes An array of GuitarNote objects.
     */
    public Tab(GuitarNote[] notes){
        /* Create chords from notes played on the same tick, then add these
        * to the array of positions */
        chords = new ArrayList<>();
        ArrayList<Integer> chord = new ArrayList<>();
        long lastTick = 0;
        for (GuitarNote note : notes) {
            int pos = note.randomPosition();
            if (note.getStartTick() == lastTick) {
                // Check the chord does not already contain this fret position
                while (chord.contains(pos)) {
                    note.removePosition(pos);
                    pos = note.randomPosition();
                }
                chord.add(pos);
            } else {
                chords.add(chord);
                chord = new ArrayList<>();
                chord.add(pos);
            }
            lastTick = note.getStartTick();
        }
        chords.add(chord);
    }

    public ArrayList<ArrayList<Integer>> getChords(){
        return chords;
    }
}
