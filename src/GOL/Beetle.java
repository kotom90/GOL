package GOL;

import java.util.Random;
import GOL.Insect.Direction;


public class Beetle extends Insect
{
    public static final int STEPS_ALIVE = 3;
    private int stepsHungry;
    
    public Beetle(int posLine, int posCol)
    {
        super(posLine, posCol);
        stepsHungry = 0;
        stepsBreed = 7;
    }
    
    @Override
    public void act()                                              //υλοποίηση μεθόδου δράσης για το σκαθάρι.
    {
        stepsHungry++;
        if (stepsHungry > STEPS_ALIVE)                              //αν τα βήματα που έχει κάνει όσο ήταν πεινασμένο είναι περισσότερα απ αυτά που μένει ζωντανό
        {
            die();                                                  //τότε πεθαίνει.
            return;
        }
        checkForFood();
        if (isMovementPossible(intendedPos))
        {
            if(canEat(intendedPosInsect))                           //τότε αν μπορεί να φάει το έντομο στη θέση που θέλει να πάει
                eat(intendedPosInsect);                             //τότε το τρώει
        }
    }

    @Override
    public boolean isMovementPossible(Position intendedPos)                                     //Υλοποίηση μεθόδου δυνατότητας κίνησης.
    {

            Insect ins = World.getInsect(intendedPos);
            if (ins instanceof Beetle || isIntendedPositionOutOfBounds(intendedPos))            //Αν στη θέση που θέλει να πάει υπάρχει σκαθάρι
            {
                return false;                                                                   //τότε η κίνηση δεν είναι δυνατή,
            }
            return true;                                                                        //αλλιώς είναι.

    }
    
    public boolean canEat(Insect ins)
    {
        if (ins instanceof Ant)                 //Αν το έντομο είναι μυρμήγκι 
            return true;                        //τότε μπορεί να το φάει,
        return false;                           //αλλιώς δεν μπορεί.
    }
    
    public void eat(Insect ins)
    {
        stepsHungry = 0;                        
        ins.isDead = true;
    }
    
    public void die()
    {
        isDead = true;
        System.out.println("pos: " + pos.getPosLine() + " " + pos.getPosCol());
        World.clearPosition(this.pos);
    }
    
    public void checkForFood()                              //ελέγχει αν υπάρχει τροφή γύρω.
    {
        Insect ins;
        
        for (Direction dir: Direction.values())             //για κάθε κατεύθυνση
        {
            ins = World.getInsect(calcIntendedPos(dir));    
            if (ins instanceof Ant)                         //αν το έντομο είναι μυρμήγκι
            {
                intendedPos = ins.pos;                      //τότε αλλάζει την πρόθεση του σκαθαριού προς τη θέση του μυρμηγκιού.
                intendedPosInsect = ins;                    
            }
        }
        
        
    }
    
    @Override
    public void breed(Position intendedPos)
    {
        steps = 0;
        World.setInsectToPosition(intendedPos, new Beetle(intendedPos.getPosLine(), intendedPos.getPosCol())); //δημιουργείται ένα νέο σκαθάρι.
    }
}
