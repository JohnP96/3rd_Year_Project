/************************************************************************************
 * Chord

 * Author: John Pederson

 * Last edited: 01/02/2023

 * Description: Object containing fret and string information for all notes played
 * simultaneously

 * Bug fixes/improvements:
 ************************************************************************************/
import java.util.ArrayList;
import java.util.Collections;

public class Chord {

    private ArrayList<GuitarNote> notes;
    //private ArrayList<Integer> stringNumbers;
   // private ArrayList<Integer> fretNumbers;

    public Chord(GuitarNote note){
        notes = new ArrayList<>();
        notes.add(note);
    }

    public ArrayList<GuitarNote> getNotes() {
        return new ArrayList<>(notes);
    }

    public void addNote(GuitarNote note){
        Collections.sort(notes);
        
    }

}
