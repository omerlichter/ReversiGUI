/**
 * Created by Omer on 04/01/2018.
 */
public class ReversiGame {
    public static void main(String args[]) {


        Drawer drawer = new ConsoleDrawer();
        Logic logic = new ReversiLogic();
        Player firstPlayer = new LocalPlayer(drawer, Cell.BLACK);
        Player secondPlayer = new LocalPlayer(drawer, Cell.WHITE);

        GameFlow gameFlow = new GameFlow(8, firstPlayer, secondPlayer, logic, drawer);
        gameFlow.run();
    }
}
