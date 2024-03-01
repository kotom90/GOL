package GOL;

import java.util.Random;

public abstract class Insect
{
    protected Position pos;
    protected Position intendedPos;
    protected Insect intendedPosInsect;
    protected int steps;
    protected int stepsBreed;
    protected boolean isDead = false;
    public boolean movedThisRound = false;
    
    
    enum Direction
    {
        LEFT, RIGHT, UP, DOWN
    }
    public Insect(int posLine, int posCol)
    {
        steps = 0;
        pos = new Position(posLine,posCol);
    }
    
    
    
    public void move()
    {
        Direction intendedDir = nextIntention();                    //Υπολογισμός της πρόθεσης του εντόμου.
        //System.out.println("pos: " + pos.getPosLine() + " " + pos.getPosCol());
        intendedPos = calcIntendedPos(intendedDir);                 //Υπολογισμός της θέσης στην οποία θέλει να πάει.
        //System.out.println("intended pos: " + intendedPos.getPosLine() + " " + intendedPos.getPosCol());
        intendedPosInsect = calcIntendedPosInsect(intendedPos);     //Το έντομο το οποίο βρίσκεται στη θέση στην οποία θέλει να πάει.
        steps++;                                                    //Αύξηση χρονικών βημάτων για το έντομο.
        act();                                                      //Το έντομο ενεργεί ανάλογα το είδος.
        if (!isDead && isMovementPossible(intendedPos))             //Αν είναι ζωντανό και μπορεί να κινηθεί σύμφωνα με τις προδιαγραφές του καθενός
        {
            if (steps > stepsBreed)                                //τότε αν ήρθε η ώρα της αναπαραγωγής
                breed(intendedPos);                                //τότε αναπαράγει στη θέση της πρόθεσής του,
            else
            {
                                                                    //αλλιώς μετακινείται.
                World.setInsectToPosition(intendedPos, this);
                World.clearPosition(this.pos);
                pos = intendedPos;
            }
        }
    }

    public boolean isIntendedPositionOutOfBounds(Position intPos)   //Έλεγχος για τα όρια του πλέγματος
    {
        if (intPos.getPosLine() < 0 ||                              //αν είναι εκτός ορίων
            intPos.getPosLine() > World.worldSizeLines-1 ||
            intPos.getPosCol() < 0 ||
            intPos.getPosCol() > World.worldSizeCols-1)
            return true;                                            //τότε επιστρέφει true
        return false;                                               //αλλιώς επιστρέφει false
    }
    
    public Position calcIntendedPos(Direction intendedDir)          //Υπολογισμός θέσης με βάση την κατεύθυνση
    {
        Position intendedPos = new Position();
        if (intendedDir == Direction.LEFT)
        {
            intendedPos.setPosLine(pos.getPosLine());
            intendedPos.setPosCol(pos.getPosCol()-1);
        }
        else if (intendedDir == Direction.RIGHT)
        {
            intendedPos.setPosLine(pos.getPosLine());
            intendedPos.setPosCol(pos.getPosCol()+1);
        }
        else if (intendedDir == Direction.UP)
        {
            intendedPos.setPosLine(pos.getPosLine()-1);
            intendedPos.setPosCol(pos.getPosCol());
        }
        else if (intendedDir == Direction.DOWN)
        {
            intendedPos.setPosLine(pos.getPosLine()+1);
            intendedPos.setPosCol(pos.getPosCol());
        }
        return intendedPos;
    }
    
    public Insect calcIntendedPosInsect(Position intendedPos)       //Το έντομο το οποίο υπάρχει στη θέση της πρόθεσης.
    {
        if (isIntendedPositionOutOfBounds(intendedPos))
            return null;
        else
            return World.getInsect(intendedPos);
    }
    
    public Direction nextIntention()                                //Γεννάει μια νέα πρόθεση προς μια από τις 4 κατευθύνσεις.
    {
        Random rand = new Random();
        Direction intendedDir = Direction.values()[rand.nextInt(Direction.values().length)];
        return intendedDir;
    }
    
    public abstract void act();
    public abstract boolean isMovementPossible(Position intendedPos);
    public abstract void breed(Position intendedPos);
}
