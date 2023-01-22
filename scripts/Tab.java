/************************************************************************************
 * Tab

 * Author: John Pederson

 * Last edited: 19/01/2023

 * Description: Object containing all fret positions within a guitar tab

 * Bug fixes/improvements:
 ************************************************************************************/
public class Tab {

    private int[] positions;

    public Tab(GuitarNote[] notes){
        positions = new int[notes.length];
        for(int i=0; i< notes.length; i++){
            positions[i] = notes[i].randomPosition();
        }
    }
}
