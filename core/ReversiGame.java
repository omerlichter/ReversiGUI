package core;

/**
 * Created by Omer on 04/01/2018.
 */
public class ReversiGame {
    public static void main(String args[]) {
        Drawer drawer = new ConsoleDrawer();
        GameFlow gameFlow = new GameFlow(8, drawer);
        while (gameFlow.runOneTurn());
    }
}
