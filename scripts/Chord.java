/************************************************************************************
 * Chord

 * Author: John Pederson

 * Last edited: 02/02/2023

 * Description: Object containing fret and string information for all notes played
 * simultaneously

 * Bug fixes/improvements: Add exception for full chords
 ************************************************************************************/
import java.util.ArrayList;
import java.util.Collections;

public class Chord{

    private ArrayList<GuitarNote> notes;
    private ArrayList<Integer> occupiedStrings;

    public Chord(){
        notes = new ArrayList<>();
    }

    public Chord(GuitarNote note){
        notes = new ArrayList<>();
        //occupiedStrings = new ArrayList<>();
        note.randomPosition();
        notes.add(note);
        //occupiedStrings.add(note.getStringNumber());
    }

    public ArrayList<GuitarNote> getNotes() {
        return new ArrayList<>(notes);
    }

    /**
     * Adds the given note to the chord and ensures that the higher notes
     * are played on higher strings.
     * @param note the GuitarNote to add to the chord
     * @return a boolean denoting whether the function was successful
     */
    public boolean addNote(GuitarNote note){
        if(notes.size() >= 6){
            return false;
        }
        if(notes.size() == 0){
            note.randomPosition();
            notes.add(note);
            return true;
        }
        Collections.sort(notes);
        //Collections.sort(occupiedStrings);
        for(int i = 0; i < notes.size(); i++){
            // If the new note is lower it replaces the old one in the list
            if(note.compareTo(notes.get(i)) <= 0){
                notes.add(i, note);
                return true;
            }
        }
        while (!note.randomPositionOnString(notes.get(notes.size()-1).getStringNumber() - 1)){
            transposeNote(notes.get(notes.size()-1), 1);
        }
        notes.add(note);
        return true;
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
        if(!note.randomPositionOnString(note.getStringNumber()+distance)){
            return false;
        }
        // If the next note is now on the same string, move that note too
        GuitarNote nextNote = notes.get(notes.indexOf(note)-distance);
        if(nextNote.getStringNumber() == note.getStringNumber()){
            return transposeNote(nextNote, distance);
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
        if(notes.size() == 1){

        }
    }
}
