package pathfinding;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import main.SnakePanel;
import snake.Snake;
import utils.Pair;
import utils.Position;

public class SnakePathfinder {

	public SnakePanel snakePanel;
	public ArrayList<Snake> snakeList;
	public Map<Integer, ArrayList<Position>> weightedPositions;
	public int direction;

	public SnakePathfinder() {
		direction = KeyEvent.VK_RIGHT;
		weightedPositions = new HashMap<Integer, ArrayList<Position>>();
	}

	public SnakePathfinder(SnakePanel s) {
		snakePanel = s;
		direction = KeyEvent.VK_RIGHT;
		weightedPositions = new HashMap<Integer, ArrayList<Position>>();
	}

	public void findPath() {
		if (weightedPositions.isEmpty()) {
			// do work
			Pair<Integer, ArrayList<Snake>> p = snakePanel.getSnake();
			direction = p.first;
			snakeList = p.second;
			Snake snakeHead = snakeList.get(0);
			// Loop through the maximum amount of the entire grid. Break when we find the
			// head of the snake
			ArrayList<Position> adjacentPos = new ArrayList<Position>();
			for (int i = 0; i < Position.NUM_SQUARES; i++) {
				// do something.
				adjacentPos.clear();
				if (i == 0) {
					Position baitPos = findBait();
					adjacentPos.add(baitPos);
					weightedPositions.put(0, adjacentPos);
				} else { // i != 0
					ArrayList<Position> lastPos = weightedPositions.get(i-1);
					for (Position pos : lastPos)
					{
						adjacentPos.addAll(getAdjacentPositions(pos));
					}
					
					weightedPositions.put(i,adjacentPos);
					if(adjacentPos.contains(snakeHead.getPosition()))
					{
						// found the snakeHeadBreak.
						break;
					} 
				} // else i == 0
			} // for loop over positions
		} else { // if we've already found the path
			// move to the bait
		} // else weightedPos.isEmpty()
	} // findPath()

	public ArrayList<Position> getAdjacentPositions(Position pos) {
		return new ArrayList<Position>();
	}

	public Position findBait() {
		return snakePanel.getBait().getPosition();
	}
	
	public void printMap(Map<Integer, ArrayList<Position>> posMap)
	{
		posMap.forEach(null);
	}
}
