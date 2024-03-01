package GOL;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game
{
    
    public void start()
    {
        World.generateInsects(10, 60);
        World.drawMatrix();
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            World.clearMovedThisRound();    //καθαρισμός της ένδειξης οτι υπήρξε ήδη κίνηση σε ένα γύρω.
            sc.nextLine();
            World.moveBeetles();        //κίνηση των σκαθαριών
            World.moveAnts();           //κίνηση των μυρμηγκιών
            System.out.println("\n\n\n");
            World.drawMatrix();
        }
    }
}
