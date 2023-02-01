/************************************************************************************
 * Tab

 * Author: John Pederson

 * Last edited: 01/02/2023

 * Description: Object containing all fret positions within a guitar tab

 * Bug fixes/improvements: Fitness function needs improving, need to add function
 * for transcribing out of range octaves
 ************************************************************************************/
import java.util.ArrayList;
import java.util.Collections;

public class Tab implements Comparable<Tab>{

    private ArrayList<ArrayList<GuitarNote>> chords;
    private int fitness;


    /**
     * Construct a Tab object from an array of GuitarNote objects, selecting random
     * positions for each note.
     * @param notes An array of GuitarNote objects.
     */
    public Tab(GuitarNote[] notes){
        /* Create chords from notes played on the same tick, then add these
        * to the array of positions */
        fitness = 0; // Fitness is initialised to 0 to be calculated later
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

    /**
     * Creates a Tab object from the given chords.
     * @param chords an ArrayList of GuitarNote ArrayLists to be
     *               the chords for the created tab.
     */
    public Tab(ArrayList<ArrayList<GuitarNote>> chords){
        fitness = 0;
        this.chords = chords;
    }

    public ArrayList<ArrayList<GuitarNote>> getChords(){
        return new ArrayList<>(chords);
    }

    public int getFitness() {
        return fitness;
    }

    /**
     * Sets the fitness value for the Tab object based on the distance
     * between notes in chords and the notes previously. The distance
     * up the fretboard is also taken into account.
     * @return the fitness value as an integer
     */
    public int calculateFitness(){
        ArrayList<GuitarNote> lastChord = chords.get(0);
        GuitarNote lastNote = lastChord.get(lastChord.size()-1);
        for (ArrayList<GuitarNote> chord : chords){
            for (GuitarNote note : chord){
                fitness += Math.ceil(note.getFretNumber() / 2.0);
                fitness += (note.getCurrentPosition() - lastNote.getCurrentPosition()) * 2;
                lastNote = note;
            }
        }
        return fitness;
    }

    @Override
    public int compareTo(Tab t) {
        return Integer.compare(this.fitness, t.fitness);
    }
}
