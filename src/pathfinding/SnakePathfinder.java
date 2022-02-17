package pathfinding;

import java.util.ArrayList;

import main.SnakePanel;

import java.awt.event.KeyEvent;
import utils.Pair;
import snake.Snake;

public class SnakePathfinder {
	
	public SnakePanel snakePanel;
	public ArrayList<Snake> snakeList;
	public int direction;
	
	public SnakePathfinder() {
		direction = KeyEvent.VK_RIGHT;
	}
	
	public SnakePathfinder(SnakePanel s) {
		snakePanel = s;
		direction = KeyEvent.VK_RIGHT;
	}
	
	public void findBait() {
		Pair<Integer,ArrayList<Snake>> p = snakePanel.getSnake();
		direction = p.first;
		snakeList = p.second;
		
	}
}
