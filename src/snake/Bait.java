package snake;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Bait extends Position {
	
	ArrayList<Position> wholeGrid;
	ArrayList<Position> validPos;
	public Bait()
	{
		for(int i = 0; i < Position.NUM_SQUARES; i++)
		{
			for(int j = 0; j < Position.NUM_SQUARES; j++)
			{
				wholeGrid.add(new Position(i,j));
			}
		}
	}
	
	public Bait(ArrayList<Snake> snakeList)
	{
		newPosition(snakeList);
	}
	
	
	public void newPosition(ArrayList<Snake> snakeList)
	{
		validPos.clear();
		for(Snake snake : snakeList)
		{
			for(Position p : wholeGrid)
			{
				if( p != snake.getPosition())
				{
					validPos.add(p);
				}
			}
		}
		
//		this.xPos = ThreadLocalRandom.current().nextInt(0, Position.NUM_SQUARES - 1);
//		this.yPos = ThreadLocalRandom.current().nextInt(0, Position.NUM_SQUARES - 1);
		int listIndex = ThreadLocalRandom.current().nextInt(0, validPos.size());
		Position newPos = validPos.get(listIndex);
		this.setPosition(newPos);
	}
	
	
}
