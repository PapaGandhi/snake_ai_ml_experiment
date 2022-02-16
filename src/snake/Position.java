package snake;


public class Position {

	public static int LINE_WIDTH = 2;
	
	public static int WIDTH;
	public static int HEIGHT;
	public static int NUM_SQUARES;
	public static int SQUARE_SPACING;
	public static int SQUARE_LENGTH;
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
		if(width == height) {
			Position.WIDTH = width;
			Position.HEIGHT = height;
			Position.NUM_SQUARES = numSquares;
			int lineWidths = Position.LINE_WIDTH * (Position.NUM_SQUARES + 1);
			int squareLengthTotals = width - lineWidths;
			Position.SQUARE_LENGTH = squareLengthTotals / numSquares;
			Position.SQUARE_SPACING = HEIGHT / NUM_SQUARES;
		} else {
			// Current limitations
			System.out.println("Can't have mismatched height and width");
			System.exit(1);
		}
		
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
