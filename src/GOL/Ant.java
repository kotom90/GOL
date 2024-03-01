package GOL;


public class Ant extends Insect
{
    public Ant(int posLine, int posCol)
    {
        super(posLine, posCol);
        stepsBreed = 3;
    }
    @Override
    public void act()
    {

    }

    @Override
    public boolean isMovementPossible(Position intendedPos)                                     //Υλοποίηση μεθόδου δυνατότητας κίνησης του μυρμηγκιού.
    {
        if (World.getInsect(intendedPos) instanceof Insect                              //Αν στη θέση που θέλει να πάει υπάρχει άλλο έντομο
                || isIntendedPositionOutOfBounds(intendedPos))                                  //ή η θέση είναι εκτός ορίων
            return false;                                                                       //τότε η κίνηση δεν είναι δυνατή,
        return true;                                                                            //αλλιώς είναι.
    }

    @Override
    public void breed(Position intendedPos)
    {
        steps = 0;
        World.setInsectToPosition(intendedPos, new Ant(intendedPos.getPosLine(), intendedPos.getPosCol())); //δημιουργείται ένα νέο μυρμήγκι
    }
    
}
