/************************************************************************************
 * Note

 * Author: John Pederson

 * Last edited: 23/01/2023

 * Description: Contains the possible fretboard positions of the given note. These
 * are encoded as integers with 0 to 6 as the open strings from high to low and
 * continuing in this way up the fretboard to fret 24 (149).

 * Bug fixes/improvements:
 ************************************************************************************/
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;

public class GuitarNote implements Comparable<GuitarNote>{

    private final Notes note;
    private ArrayList<Integer> possiblePositions;
    private final long startTick; // Duration is measured in MIDI ticks
    private int currentPosition;
    private int stringNumber;
    private int fretNumber;

    /**
     * Static arrays containing all possible positions on a 24 fret fretboard
     * for each note
     */

    public static final Integer[] cPositions = {7, 22, 32, 48, 53, 63, 79, 94, 104, 120, 125, 135};
    public static final Integer[] cSharpPositions = {13, 28, 54, 59, 69, 85, 100, 110, 126, 131, 141};
    public static final Integer[] dPositions = {3, 19, 34, 60, 65, 75, 91, 106, 116, 132, 137, 147};
    public static final Integer[] dSharpPositions = {9, 25, 40, 66, 71, 81, 97, 112, 122, 138, 143};
    public static final Integer[] ePositions = {0, 5, 15, 31, 46, 72, 77, 87, 103, 118, 128, 144, 149};
    public static final Integer[] fPositions= {6, 11, 21, 37, 52, 78, 83, 93, 109, 124, 134};
    public static final Integer[] fSharpPositions= {12, 17, 27, 43, 58, 84, 89, 99, 115, 130, 140};
    public static final Integer[] gPositions = {2, 18, 23, 33, 49, 64, 90, 95, 105, 121, 136, 146};
    public static final Integer[] gSharpPositions = {8, 24, 29, 39, 55, 70, 96, 101, 111, 127, 142};
    public static final Integer[] aPositions = {4, 14, 30, 35, 45, 61, 76, 102, 107, 117, 133, 148};
    public static final Integer[] aSharpPositions = {10, 20, 36, 41, 51, 67, 82, 108, 113, 123, 139};
    public static final Integer[] bPositions = {1, 16, 26, 42, 47, 57, 73, 88, 114, 119, 129, 145};


    /**
     * Construct a GuitarNote object from information taken from a MIDI file.
     * @param note A Notes enum, describing the note being played.
     * @param startTick A 'long' containing the tick the note begins on.
     */

    public GuitarNote(Notes note, long startTick){
        possiblePositions = new ArrayList<>();
        this.note = note;
        this.startTick = startTick;
        if(note == Notes.C){
            Collections.addAll(possiblePositions, cPositions);
        }
        else if(note == Notes.C_SHARP){
            Collections.addAll(possiblePositions, cSharpPositions);
        }
        else if(note == Notes.D){
            Collections.addAll(possiblePositions, dPositions);
        }
        else if(note == Notes.D_SHARP){
            Collections.addAll(possiblePositions, dSharpPositions);
        }
        else if(note == Notes.E){
            Collections.addAll(possiblePositions, ePositions);
        }
        else if(note == Notes.F){
            Collections.addAll(possiblePositions, fPositions);
        }
        else if(note == Notes.F_SHARP){
            Collections.addAll(possiblePositions, fSharpPositions);
        }
        else if(note == Notes.G){
            Collections.addAll(possiblePositions, gPositions);
        }
        else if(note == Notes.G_SHARP){
            Collections.addAll(possiblePositions, gSharpPositions);
        }
        else if(note == Notes.A){
            Collections.addAll(possiblePositions, aPositions);
        }
        else if(note == Notes.A_SHARP){
            Collections.addAll(possiblePositions, aSharpPositions);
        }
        else if(note == Notes.B){
            Collections.addAll(possiblePositions, bPositions);
        }
    }

    public ArrayList<Integer> getPossiblePositions(){
        return new ArrayList<>(possiblePositions);
    }

    public long getStartTick() {
        return startTick;
    }

    public int getStringNumber() {
        return stringNumber;
    }

    public Integer getFretNumber() {
        return fretNumber;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        fretNumber = currentPosition / 6;
        stringNumber = currentPosition % 6;
    }

    /**
     * Remove a fret position from the possible positions for this note
     * @param position An int denoting the fret position to be removed
     * @return A boolean denoting whether the function was successful
     * or not
     */
    public boolean removePosition(int position){
        if(possiblePositions.contains(position)){
            possiblePositions.remove(Integer.valueOf(position));
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Removes all possible positions on the string that the current position
     * is on. Then calls randomPosition to replace the current position.
     */
    public void removeCurrentString(){
        for(int pos = stringNumber; pos<150; pos=pos+6){
            this.removePosition(pos);
        }
        this.randomPosition();
    }

    public String toString(){
        return "Note: " + note + ", Start: " + startTick;
    }

    /**
     * Sets a random current position from the possible positions for this note
     */
    public void randomPosition(){
        Random rand = new Random();
        setCurrentPosition(possiblePositions.get(rand.nextInt(possiblePositions.size())));
    }

    public int compareTo(GuitarNote note) {
        return Integer.compare(this.stringNumber, note.stringNumber);
    }
}
