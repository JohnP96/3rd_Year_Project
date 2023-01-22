import java.io.FileWriter;

/** Main file for running and testing code */

public class Main {
    public static void main(String[] args){

        TabInterface.createTabFile("test");
        TabInterface.sendToTAB("test");
    }
}
