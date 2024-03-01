package GOL;

class Position
{
    private int posLine;
    private int posCol;
    
    public Position()
    {
        
    }
    public Position(int posLine, int posCol)
    {
        this.posLine = posLine;
        this.posCol = posCol;
    }
    
    public int getPosLine()
    {
        return posLine;
    }
    
    public int getPosCol()
    {
        return posCol;
    }
    
    public void setPosLine(int posLine)
    {
        this.posLine = posLine;
    }
    
    public void setPosCol(int posCol)
    {
        this.posCol = posCol;
    }
    
    
}
