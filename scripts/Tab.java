/************************************************************************************
 * Tab

 * Author: John Pederson

 * Last edited: 10/04/2023

 * Description: Object containing all fret positions within a guitar tab

 * Bug fixes/improvements: Fitness function needs improving
 ************************************************************************************/
import java.util.ArrayList;
import java.util.Arrays;
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
            if(inRangeHigh && (octave > 6 || octave == 6 && n > Notes.G.ordinal())) {
                inRangeHigh = false;
                if(inRangeLow){
                    System.out.println("Transposed up");
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
                    System.out.println("Transposed down");
                    for(GuitarNote gn : notes){
                        gn.setOctave(gn.getOctave()+1);
                    }
                }
                else{
                    throw new Exception("Composition not in range");
                }
            }
        }
        ArrayList<GuitarNote> notesInChord = new ArrayList<>();
        long lastTick = 0;
        for (GuitarNote note : notes) {
//            System.out.println(note);
            if (note.getStartTick() == lastTick) {
                notesInChord.add(note);
            }
            else {
                if(notesInChord.size() > 0) {
//                    System.out.println(notesInChord);
                    Chord chord = new Chord(rand);
                    ArrayList<Integer> unoccupiedStrings = new ArrayList<>();
                    for (int i = 0; i < 6; i++) {
                        unoccupiedStrings.add(i);
                    }
//                    System.out.println(notesInChord);
                    if(!chord.addNotes(notesInChord, 0, unoccupiedStrings)){
                        throw new Exception("Chord not complete");
                    }
                    if(chord.getNotes().get(chord.getNotes().size()-1) == null) {
                        System.out.println(chord);
                    }
                    chords.add(chord);
                    notesInChord = new ArrayList<>();
                    notesInChord.add(note);
                }
            }
            lastTick = note.getStartTick();
        }
        if(notesInChord.size() > 0) {
//            System.out.println(notesInChord);
            Chord chord = new Chord(rand);
            ArrayList<Integer> unoccupiedStrings = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                unoccupiedStrings.add(i);
            }
            if(!chord.addNotes(notesInChord, 0, unoccupiedStrings)){
                throw new Exception("Chord not complete");
            }
            if(chord.getNotes().get(chord.getNotes().size()-1) == null) {
                System.out.println(chord);
            }
            chords.add(chord);
        }
//        System.out.println(chords);
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
        GuitarNote lastNote;
        double avgFret;
        double lastAvgFret = 0;
        for (Chord chord : chords){
//            System.out.println(chord);
            avgFret = 0.0;
            ArrayList<GuitarNote> notes = chord.getNotes();
            lastNote = notes.get(0);
            for (GuitarNote note : notes){
                fitness += (Math.sqrt(Math.pow(note.getFretNumber() - lastNote.getFretNumber(), 2)));
                lastNote = note;
                avgFret += note.getFretNumber();
            }
            avgFret = avgFret/ notes.size();
            fitness += (Math.sqrt(Math.pow(avgFret - lastAvgFret, 2)));
            fitness += avgFret;
            lastAvgFret = avgFret;
        }
        return fitness;
    }

    @Override
    public int compareTo(Tab t) {
        return Integer.compare(this.fitness, t.fitness);
    }
}
