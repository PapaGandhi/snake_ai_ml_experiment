package snake;

import java.util.ArrayList;

import utils.Position;

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
	
	public boolean equals(Snake snake)
	{
		return this.getPosition().equals(snake.getPosition());
	}
	
	public boolean equals(ArrayList<Snake> snakeList)
	{
		
		// Assuming the head is calling this method
		// then start at the second node. If any equal the
		// the head then return true;
		for(int i = 1; i < snakeList.size(); i++)
		{
			if(this.equals(snakeList.get(i)))
			{
				return true;
			}
		}
		return false;
	}
}
