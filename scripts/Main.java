
/** Main file for running and testing code */

public class Main {
    public static void main(String[] args){

        try {
            TabGA ga = new TabGA("rogero", 1000, 1000);
            Tab tab = ga.mostFitTab();
            TabInterface.createTabFile("Rogero_Test", tab);
            TabInterface.sendToTAB("Rogero_Test");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
