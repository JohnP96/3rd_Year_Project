import java.util.Random;

/************************************************************************************
 * Note

 * Author: John Pederson

 * Last edited: 05/01/2023

 * Description: Contains the possible fretboard positions of the given note. These
 * are encoded as integers with 0 to 6 as the open strings from low to high and
 * continuing in this way up the fretboard to fret 24 (149).

 * Bug fixes/improvements:
 ************************************************************************************/
public class GuitarNote {

    private Notes note;
    private int[] positions;
    private long startTick; // Duration is measured in MIDI ticks

    /**
     * Static arrays containing all possible positions on a 24 fret fretboard
     * for each note
     */

    public static final int[] cPositions = {10, 19, 33, 48, 53, 62, 82, 91, 105, 120, 125, 134};
    public static final int[] cSharpPositions = {16, 25, 39, 54, 59, 68, 88, 97, 111, 126, 131, 140};
    public static final int[] dPositions = {2, 22, 31, 45, 60, 65, 74, 94, 103, 117, 132, 137, 146};
    public static final int[] dSharpPositions = {8, 28, 37, 51, 66, 71, 80, 100, 109, 123, 138, 143};
    public static final int[] ePositions = {0, 5, 14, 34, 43, 57, 72, 77, 86, 106, 115, 129, 144, 149};
    public static final int[] fPositions= {6, 11, 20, 40, 49, 63, 78, 83, 92, 112, 121, 135};
    public static final int[] fSharpPositions= {12, 17, 26, 46, 55, 69, 84, 89, 98, 118, 127, 141};
    public static final int[] gPositions = {3, 18, 23, 32, 52, 61, 75, 90, 95, 104, 124, 133, 147};
    public static final int[] gSharpPositions = {9, 24, 29, 38, 58, 67, 81, 96, 101, 110, 130, 139};
    public static final int[] aPositions = {1, 15, 30, 35, 44, 64, 73, 87, 102, 107, 116, 136, 145};
    public static final int[] aSharpPositions = {7, 21, 36, 41, 50, 70, 93, 108, 113, 122, 142};
    public static final int[] bPositions = {4, 13, 27, 42, 47, 56, 76, 99, 114, 119, 128, 148};


    public GuitarNote(Notes note, long startTick){
        this.note = note;
        this.startTick = startTick;
        if(note == Notes.C){
            positions = cPositions;
        }
        else if(note == Notes.C_SHARP){
            positions = cSharpPositions;
        }
        else if(note == Notes.D){
            positions = dPositions;
        }
        else if(note == Notes.D_SHARP){
            positions = dSharpPositions;
        }
        else if(note == Notes.E){
            positions = ePositions;
        }
        else if(note == Notes.F){
            positions = fPositions;
        }
        else if(note == Notes.F_SHARP){
            positions = fSharpPositions;
        }
        else if(note == Notes.G){
            positions = gPositions;
        }
        else if(note == Notes.G_SHARP){
            positions = gSharpPositions;
        }
        else if(note == Notes.A){
            positions = aPositions;
        }
        else if(note == Notes.A_SHARP){
            positions = aSharpPositions;
        }
        else if(note == Notes.B){
            positions = bPositions;
        }
    }

    public int[] getPositions(){
        return positions;
    }

    public String toString(){
        return "Note: " + note + ", Start: " + startTick;
    }

    /**
     * Returns a random position from the possible positions for that note
     * @return An integer denoting the fret position
     */
    public int randomPosition(){
        Random rand = new Random();
        return positions[rand.nextInt(positions.length)];
    }
}
