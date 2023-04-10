/************************************************************************************
 * Tab

 * Author: John Pederson

 * Last edited: 10/04/2023

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
        int octave;
        int n;
        boolean inRangeHigh = true;
        boolean inRangeLow = true;
        for(GuitarNote note : notes){
            octave = note.getOctave();
            n = note.getNote().ordinal();
            if(inRangeHigh && (octave > 5 || octave == 5 && n > Notes.G.ordinal())) {
                inRangeHigh = false;
                if(inRangeLow){
                    for(GuitarNote gn : notes){
                        gn.setOctave(gn.getOctave()-1);
                    }
                }
                else{
                    throw new Exception("Composition not in range");
                }
            }
            else if (inRangeLow && (octave < 2 || octave == 2 && n < Notes.E.ordinal())){
                inRangeLow = false;
                if(inRangeHigh){
                    for(GuitarNote gn : notes){
                        gn.setOctave(gn.getOctave()+1);
                    }
                }
                else{
                    throw new Exception("Composition not in range");
                }
            }
        }
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
                fitness += Math.sqrt(Math.pow(note.getFretNumber() - lastNote.getFretNumber(), 2));
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
