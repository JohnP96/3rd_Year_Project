/************************************************************************************
 * Chord

 * Author: John Pederson

 * Last edited: 08/02/2023

 * Description: Object containing fret and string information for all notes played
 * simultaneously

 * Bug fixes/improvements: Add exception for full chords
 ************************************************************************************/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Chord{

    private ArrayList<GuitarNote> notes;
    private Random rand;

    public Chord(Random rand){
        notes = new ArrayList<>();
        this.rand = rand;
    }

    public Chord(GuitarNote note, Random rand){
        this.rand = rand;
        notes = new ArrayList<>();
        note.randomPosition();
        notes.add(note);
    }

    public ArrayList<GuitarNote> getNotes() {
        // Here we ensure the notes are returned in order from highest to lowest
        Collections.sort(notes);
        Collections.reverse(notes);
        return new ArrayList<>(notes);
    }

    /**
     * Adds the given note to the chord and ensures that the higher notes
     * are played on higher strings.
     * @param note the GuitarNote to add to the chord
     * @return a boolean denoting whether the function was successful
     */
    public boolean addNote(GuitarNote note){
        // If there are 6 notes being played there is no room for another
        if(notes.size() >= 6){
            return false;
        }
        if(notes.size() == 0){
            note.randomPosition();
            notes.add(note);
            return true;
        }
        Collections.sort(notes);

        /* First we check if the note is the highest or lowest and if there is no higher or
        * lower string available we transpose the preceding notes to lower or higher strings */

        if(note.compareTo(notes.get(0)) <= 0){
            // If the note cannot be put on a lower string, transpose the others
            while (!note.randomPositionOnString(notes.get(0).getStringNumber() + 1)){
                if(!transposeNote(notes.get(0), -1)){
                    return false;
                }
            }
            notes.add(0, note);
            return true;
        }
        if(note.compareTo(notes.get(notes.size()-1)) >= 0){
            // If the note cannot be put on a higher string, transpose the others
            while (!note.randomPositionOnString(notes.get(notes.size()-1).getStringNumber() - 1)){
                if(transposeNote(notes.get(notes.size()-1), 1)){
                    return false;
                }
            }
            notes.add(note);
            return true;
        }

        /* If the note is not the highest or lowest we find its place in the list and use that
        * along with the size of the list to decide whether the strings below or above need
        * to be transposed */

        for(int i = 1; i < notes.size(); i++){
            // If the new note is lower it replaces the old one in the list
            if(note.compareTo(notes.get(i)) <= 0){
                // Choose the optimal string for the note to be played on
                note.randomPositionOnString(chooseString(i));
                notes.add(i, note);
                return true;
            }
        }
        return false;
    }

    /**
     * Transposes notes up or down the strings
     * by the distance given (Positive moves to lower strings and negative
     * to higher). Notes list must be sorted.
     * @param distance an integer denoting the number of strings to move by
     *                 - in normal practice will only be 1 or -1
     * @return a boolean denoting whether the function succeeded
     */
    private boolean transposeNote(GuitarNote note, int distance){
        // Move the note to the specified string
        if(!note.randomPositionOnString(note.getStringNumber()+distance)){
            return false;
        }
        // If the next note is now on the same string, move that note too
        if(0 < notes.indexOf(note)-distance && notes.indexOf(note)-distance < notes.size() ) {
            GuitarNote nextNote = notes.get(notes.indexOf(note) - distance);
            if (nextNote.getStringNumber() == note.getStringNumber()) {
                return transposeNote(nextNote, distance);
            }
        }
        return true;
    }

    /**
     * Selects a string to place the added note onto using
     * the index the note will be inserted at and the number
     * of notes in the chord.
     * @param index an integer denoting the index the note
     *              will be inserted into the ArrayList at
     * @return  an integer denoting the string for the note
     * to be placed on.
     */
    private int chooseString(int index){
        GuitarNote higherNote = notes.get(index);
        GuitarNote lowerNote = notes.get(index-1);
        int higherString = higherNote.getStringNumber();
        int lowerString = lowerNote.getStringNumber();
        /* If there are empty strings between the higher and lower notes then pick one
           at  random */
        if(lowerString - higherString > 1){
            return rand.nextInt(lowerString - higherString - 1) + higherString + 1;
        }
        /* If there are no empty strings then either the higher or the lower note are
        * transposed, dependent on how many notes are higher or lower */
        else{
            if(index < notes.size()/2){
                if(transposeNote(lowerNote, 1)){
                    return lowerString;
                }
                else{
                    transposeNote(higherNote, -1);
                    return higherString;
                }
            }
            else{
                if(transposeNote(higherNote, -1)){
                    return higherString;
                }
                else{
                    transposeNote(lowerNote, 1);
                    return lowerString;
                }
            }
        }
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for (GuitarNote n : notes){
            str.append(n.getNote()).append(n.getOctave()).append(" Fret: ").append(n.getFretNumber())
                    .append(" String: ").append(n.getStringNumber()).append('\n');
        }
        return str.toString();
    }
}
