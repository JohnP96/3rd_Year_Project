/************************************************************************************
 * Tab

 * Author: John Pederson

 * Last edited: 03/04/2023

 * Description: Object containing all fret positions within a guitar tab

 * Bug fixes/improvements: Fitness function needs improving, need to add function
 * for transcribing out of range octaves
 ************************************************************************************/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Tab implements Comparable<Tab>{

    private ArrayList<Chord> chords;
    private int fitness;


    /**
     * Construct a Tab object from an array of GuitarNote objects, selecting random
     * positions for each note.
     * @param notes An array of GuitarNote objects.
     * @param rand A Random object to be passed down to the Chords
     */
    public Tab(GuitarNote[] notes, Random rand) throws Exception{
        /* Create chords from notes played on the same tick, then add these
        * to the array of positions */
        fitness = 0; // Fitness is initialised to 0 to be calculated later
        chords = new ArrayList<>();

        Chord chord = new Chord(rand);
        long lastTick = 0;
        for (GuitarNote note : notes) {
            if (note.getStartTick() == lastTick) {
                if(!chord.addNote(note)){
                    throw new Exception("Error adding note: " + note);
                }
            }
            else {
                chords.add(chord);
                chord = new Chord(note, rand);
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
    public Tab(ArrayList<Chord> chords){
        fitness = 0;
        this.chords = chords;
    }

    public ArrayList<Chord> getChords(){
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
        fitness = 0;
        Chord lastChord = chords.get(0);
        GuitarNote lastNote = lastChord.getNotes().get(lastChord.getNotes().size()-1);
        for (Chord chord : chords){
            for (GuitarNote note : chord.getNotes()){
                fitness += 24-note.getFretNumber();
                fitness += (24-(note.getCurrentPosition() - lastNote.getCurrentPosition())) * 2;
                lastNote = note;
            }
        }
        return fitness;
    }

    @Override
    public int compareTo(Tab t) {
        // Allows Tabs to be sorted in descending order of fitness
        return Integer.compare(t.fitness, this.fitness);
    }
}
