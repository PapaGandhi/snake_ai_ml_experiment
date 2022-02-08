package snake;

public class Snake extends Position {
	
	Position pos[];
	
	public Snake()
	{
		resetPosition();
	}
	
	public Snake(Position p)
	{
		super(p);
	}
	
	public Snake(int xPos, int yPos)
	{
		super(xPos,yPos);
	}
	
	public void resetPosition() 
	{
		xPos = 13;
		yPos = 3;
	}
}
