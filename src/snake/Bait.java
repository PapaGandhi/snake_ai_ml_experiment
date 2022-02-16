package snake;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Bait extends Position {
	
	ArrayList<Position> wholeGrid;
	ArrayList<Position> validPos;
	public Bait()
	{
		wholeGrid = new ArrayList<>();
		validPos = new ArrayList<>();
		initializeGrid();
	}
	
	public Bait(ArrayList<Snake> snakeList)
	{
		wholeGrid = new ArrayList<>();
		validPos = new ArrayList<>();
		initializeGrid();
		newPosition(snakeList);
	}
	
	
	@SuppressWarnings("unchecked")
	public void newPosition(ArrayList<Snake> snakeList)
	{
		validPos.clear();
		validPos= (ArrayList<Position>) wholeGrid.clone();
		validPos.removeAll(snakeList);

		int listIndex = ThreadLocalRandom.current().nextInt(0, validPos.size() - 1);
		Position newPos = validPos.get(listIndex);
		this.setPosition(newPos);
	}
	
	public void initializeGrid()
	{
		for(int i = 0; i < Position.NUM_SQUARES; i++)
		{
			for(int j = 0; j < Position.NUM_SQUARES; j++)
			{
				wholeGrid.add(new Position(i,j));
			}
		}
	}
	
	
}
