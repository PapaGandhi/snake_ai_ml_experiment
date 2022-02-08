package snake;

public class Position {

	public static int WIDTH;
	public static int HEIGHT;
	public static int NUM_SQUARES;
	public static int xSpacing;
	public static int ySpacing;
	protected int xPos;
	protected int yPos;
	protected Position oldPosition;
	
	
	public Position() 
	{
		oldPosition = null;
		this.xPos = 0;
		this.yPos = 0;
	}
	
	
	public Position(Position p)
	{
		oldPosition = null;
		this.xPos = p.xPos;
		this.yPos = p.yPos;
	}
	
	public Position(int xPos, int yPos)
	{
		oldPosition = null;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public Position(int width, int height, int numSquares)
	{
		oldPosition = null;
		initialize(width,height,numSquares);
	}
	
	public void initialize(int width, int height, int numSquares)
	{
		Position.WIDTH = width;
		Position.HEIGHT = height;
		Position.NUM_SQUARES = numSquares;
		xSpacing = WIDTH / NUM_SQUARES;
		ySpacing = HEIGHT / NUM_SQUARES;
	}
	
	public Position getPosition()
	{
		return this.copy();
	}
	
	public void setPosition(Position p)
	{
		oldPosition = new Position(this);
		this.xPos = p.xPos;
		this.yPos = p.yPos;
	}
	
	
	public Position getOldPosition()
	{
		return oldPosition.copy();
	}
	
	public int getxPos()
	{
		return xPos;
	}

	public int getyPos() 
	{
		return yPos;
	}

	public void setyPos(int yPos) 
	{
		oldPosition = new Position(this);
		this.yPos = yPos;
	}

	public void setxPos(int xPos) 
	{
		oldPosition = new Position(this);
		this.xPos = xPos;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Position other = (Position) obj;
        if(this.xPos != other.xPos)
        {
        	return false;
        }
        
        if(this.yPos != other.yPos)
        {
        	return false;
        }

        return true;
	}
	
	public Position copy()
	{
		Position p = new Position(xPos, yPos);
		return p;
	}

	public void print()
	{
		System.out.printf("WIDTH %d, HEIGHT %d, NUM_SQUARES %d, xPos %d, yPos %d\n", WIDTH, HEIGHT, NUM_SQUARES, xPos, yPos);
	}
	
}
