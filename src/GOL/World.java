package GOL;

import java.util.Random;


public class World
{
    public final static int worldSizeLines = 20; 
    public final static int worldSizeCols = 20; 
    public final static char lineSymbol = '*'; 
    public static Insect matrix[][] = new Insect[worldSizeLines][worldSizeCols];
    
    
    public static void drawMatrix()         //εμφάνιση πλέγματος
    {
        for (int i=0; i<worldSizeLines; i++)
        {  
            for (int k=0; k<81; k++)
                System.out.print(lineSymbol);
            System.out.println();
            for (int j=0; j<worldSizeCols; j++)
            {
                if (matrix[i][j] instanceof Beetle)
                    System.out.print(lineSymbol + " X ");
                else if (matrix[i][j] instanceof Ant)
                    System.out.print(lineSymbol + " O ");
                else
                    System.out.print(lineSymbol + "   ");
            }
            System.out.println(lineSymbol);
        }
        for (int k=0; k<81; k++)
                System.out.print(lineSymbol);
    }
    
    public static void generateInsects(int beetlesNum, int antsNum)     //Γέννεση εντόμων στο πλέγμα
    {
        Random rand = new Random();
        int line;
        int col;
        while (beetlesNum > 0)
        {
            line = rand.nextInt(worldSizeLines);
            col = rand.nextInt(worldSizeCols);
            if (matrix[line][col] == null)
            {
                matrix[line][col] = new Beetle(line,col);
                beetlesNum--;
            }
        }
        
        while (antsNum > 0)
        {
            line = rand.nextInt(worldSizeLines);
            col = rand.nextInt(worldSizeCols);
            if (matrix[line][col] == null)
            {
                matrix[line][col] = new Ant(line,col);
                antsNum--;
            }
        }
    }
    
    public static void moveBeetles()        //Κίνηση σκαθαριών
    {
        for(int i=0; i<World.worldSizeLines; i++)
        {
            for (int j=0; j<World.worldSizeCols; j++)
            {
                Insect ins = World.getInsect(new Position(i,j));
                if (ins instanceof Beetle && !ins.movedThisRound)
                {
                    ins.move();
                    ins.movedThisRound = true;
                }
            }
        }
    }
    
    public static void moveAnts()       //Κίνηση μυρμηγκιών
    {
        for(int i=0; i<World.worldSizeLines; i++)
        {
            for (int j=0; j<World.worldSizeCols; j++)
            {
                Insect ins = World.getInsect(new Position(i,j));
                if (ins instanceof Ant && !ins.movedThisRound)
                {
                    ins.move();
                    ins.movedThisRound = true;
                }
            }
        }
    }
    
    public static Insect getInsect(Position pos)        //Επιστρέφει το έντομο το οποίο βρίσκεται στη θέση της παραμέτρου.
    {
        try
        {
            return matrix[pos.getPosLine()][pos.getPosCol()];
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return null;
        }
    }
    
    public static void setInsectToPosition(Position pos, Insect ins)    //μετακινεί το έντομο σε μια θέση.
    {
        int posLine = pos.getPosLine();
        int posCol = pos.getPosCol();
        matrix[posLine][posCol] = ins;
    }
    
    public static void clearPosition(Position pos)          //καθαρίζει τη θέση.
    {
        int posLine = pos.getPosLine();
        int posCol = pos.getPosCol();
        matrix[posLine][posCol] = null;
    }
    
    public static void clearMovedThisRound()                //καθαρίζει τις ενδείξεις για την ύπαρξη κίνησης σε ένα γύρο.
    {
        for(int i=0; i<World.worldSizeLines; i++)
        {
            for (int j=0; j<World.worldSizeCols; j++)
            {
                try
                {
                    matrix[i][j].movedThisRound = false;
                }
                catch(NullPointerException e)
                {
                    
                }
            }
        }
    }
}
