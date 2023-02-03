/************************************************************************************
 * Note

 * Author: John Pederson

 * Last edited: 03/02/2023

 * Description: Contains the possible fretboard positions of the given note. These
 * are encoded as integers with 0 to 6 as the open strings from high to low and
 * continuing in this way up the fretboard to fret 24 (149).

 * Bug fixes/improvements: Fix the way octaves work, add exception for no possible
 * positions
 ************************************************************************************/
import java.lang.reflect.Array;
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
    private int octave;
    private Random rand;


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

    public GuitarNote(Notes note, int octave, long startTick, Random rand){
        possiblePositions = new ArrayList<>();
        this.note = note;
        this.startTick = startTick;
        this.octave = octave;
        this.rand = rand;
        ArrayList<Integer> octavePositions = new ArrayList<>();

        // Select fret positions within the octave
        if(octave == 2){
            for(int i=5; i<53; i=i+6){
                octavePositions.add(i);
            }
            for(int i=4; i<22; i=i+6){
                octavePositions.add(i);
            }
        }
        else if(octave == 3){
            for(int i=53; i<125; i=i+6){
                octavePositions.add(i);
            }
            for(int i=22; i<94; i=i+6){
                octavePositions.add(i);
            }
            for(int i=3; i<63; i=i+6){
                octavePositions.add(i);
            }
            for(int i=2; i<32; i=i+6){
                octavePositions.add(i);
            }
            octavePositions.add(1);
        }
        else if(octave == 4){
            for(int i=125; i<150; i=i+6){
                octavePositions.add(i);
            }
            for(int i=94; i<150; i=i+6){
                octavePositions.add(i);
            }
            for(int i=32; i<104; i=i+6){
                octavePositions.add(i);
            }
            for(int i=63; i<135; i=i+6){
                octavePositions.add(i);
            }
            for(int i=7; i<79; i=i+6){
                octavePositions.add(i);
            }
            for(int i=0; i<48; i=i+6){
                octavePositions.add(i);
            }
        }
        else if(octave == 5){
            for(int i=104; i<150; i=i+6){
                octavePositions.add(i);
            }
            for(int i=135; i<150; i=i+6){
                octavePositions.add(i);
            }
            for(int i=79; i<150; i=i+6){
                octavePositions.add(i);
            }
            for(int i=48; i<120; i=i+6){
                octavePositions.add(i);
            }
        }
        else if(octave == 6){
            for(int i=144; i>=120; i=i-6){
                octavePositions.add(i);
            }
        }
        else{
            for(int i=0; i<150; i++){
                octavePositions.add(i);
            }
        }

        if(note == Notes.C){
            for (Integer i : cPositions){
                if(octavePositions.contains(i)){
                    possiblePositions.add(i);
                }
            }
        }
        else if(note == Notes.C_SHARP){
            for (Integer i : cSharpPositions){
                if(octavePositions.contains(i)){
                    possiblePositions.add(i);
                }
            }
        }
        else if(note == Notes.D){
            for (Integer i : dPositions){
                if(octavePositions.contains(i)){
                    possiblePositions.add(i);
                }
            }
        }
        else if(note == Notes.D_SHARP){
            for (Integer i : dSharpPositions){
                if(octavePositions.contains(i)){
                    possiblePositions.add(i);
                }
            }
        }
        else if(note == Notes.E){
            for (Integer i : ePositions){
                if(octavePositions.contains(i)){
                    possiblePositions.add(i);
                }
            }
        }
        else if(note == Notes.F){
            for (Integer i : fPositions){
                if(octavePositions.contains(i)){
                    possiblePositions.add(i);
                }
            }
        }
        else if(note == Notes.F_SHARP){
            for (Integer i : fSharpPositions){
                if(octavePositions.contains(i)){
                    possiblePositions.add(i);
                }
            }
        }
        else if(note == Notes.G){
            for (Integer i : gPositions){
                if(octavePositions.contains(i)){
                    possiblePositions.add(i);
                }
            }
        }
        else if(note == Notes.G_SHARP){
            for (Integer i : gSharpPositions){
                if(octavePositions.contains(i)){
                    possiblePositions.add(i);
                }
            }
        }
        else if(note == Notes.A){
            for (Integer i : aPositions){
                if(octavePositions.contains(i)){
                    possiblePositions.add(i);
                }
            }
        }
        else if(note == Notes.A_SHARP){
            for (Integer i : aSharpPositions){
                if(octavePositions.contains(i)){
                    possiblePositions.add(i);
                }
            }
        }
        else if(note == Notes.B){
            for (Integer i : bPositions){
                if(octavePositions.contains(i)){
                    possiblePositions.add(i);
                }
            }
        }
    }

    public ArrayList<Integer> getPossiblePositions(){
        return new ArrayList<>(possiblePositions);
    }

    public long getStartTick() {
        return startTick;
    }

    public Notes getNote(){
        return note;
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

    public int getOctave(){
        return octave;
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
        return "Note: " + note + octave + ", Start: " + startTick;
    }

    /**
     * Sets a random current position from the possible positions for this note
     * @return a boolean denoting whether a new position was
     *         successfully selected
     */
    public boolean randomPosition(){
        if(possiblePositions.size() > 0) {
            setCurrentPosition(possiblePositions.get(rand.nextInt(possiblePositions.size())));
            return true;
        }
        return false;
    }

    /**
     * Assigns a random fret position on the specified string
     * @param string an integer representing the string
     * @return a boolean denoting whether a new position was
     *         successfully selected
     */
    public boolean randomPositionOnString(int string){
        if (string < 0 || string > 5){
            return false;
        }
        ArrayList<Integer> positionsOnString = new ArrayList<>();
        for (Integer pos : possiblePositions){
            if (pos % 6 == string){
                positionsOnString.add(pos);
            }
        }
        System.out.println(positionsOnString);
        if(positionsOnString.size() > 0) {
            setCurrentPosition(positionsOnString.get(rand.nextInt(positionsOnString.size())));
            return true;
        }
        return false;
    }

    /**
     * Moves the note position to a different string
     * @param distance the number of strings to move the note by (negative
     *                 moves higher and positive lower)
     * @return an integer representing the string that the note has moved to,
     * a return value of -1 means that the move has failed.
     */
    public int moveString(int distance){
        int newString = this.stringNumber + distance;
        if(this.randomPositionOnString(newString)){
            return newString;
        }
        return -1;
    }

    public int compareTo(GuitarNote note) {
        if(this.octave < note.getOctave()){
            return -1;
        }
        if(this.octave > note.getOctave()){
            return 1;
        }
        // If notes are in same octave check the actual note values
        return Integer.compare(this.note.ordinal(), note.getNote().ordinal());
    }
}
