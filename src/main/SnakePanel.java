package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import utils.Pair;
import utils.Position;

import javax.swing.JPanel;

import snake.Bait;
import snake.Snake;

public class SnakePanel extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;
	public static int NUM_SQUARES = 25;
	public static int LINE_WIDTH = 2;
	private int direction;
	ArrayList<Snake> snakeList = new ArrayList<Snake>();
	private Bait bait;
	
	public SnakePanel()
	{
		direction = KeyEvent.VK_RIGHT;
		System.out.println("Height: " + getHeight());
		System.out.println("Width: " + getWidth());
		initializeSnakes();
		bait = new Bait(snakeList);
	}
	
	public SnakePanel(int width,int height)
	{
		direction = KeyEvent.VK_RIGHT;
		new Position(width,height,NUM_SQUARES);
		initializeSnakes();
		bait = new Bait(snakeList);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		paintVertLines(g);
		paintHorzLines(g);
		paintBait(g);
		paintSnake(g);
	}
	
	public void paintVertLines(Graphics g)
	{
		g.setColor(Color.BLACK);
		for (int xPos = 0; xPos < Position.HEIGHT; xPos += Position.SQUARE_SPACING)
		{
			g.fillRect(xPos, 0, LINE_WIDTH, Position.HEIGHT);
		}
	}
	
	public void paintHorzLines(Graphics g)
	{
		g.setColor(Color.BLACK);
		for (int yPos = 0; yPos < Position.WIDTH; yPos += Position.SQUARE_SPACING)
		{
			g.fillRect(0, yPos, Position.WIDTH, LINE_WIDTH);
		}
	}
	
	public void paintBait(Graphics g)
	{
		g.setColor(Color.RED);
		int baitXPos = bait.getxPos();
		int baitYPos = bait.getyPos();
		g.fillRect(baitXPos * Position.SQUARE_SPACING + 2, baitYPos * Position.SQUARE_SPACING + 2 , Position.SQUARE_LENGTH, Position.SQUARE_LENGTH);
	}
	
	public void initializeSnakes()
	{
		snakeList.add(new Snake(13,3));
		snakeList.add(new Snake(12,3));
		snakeList.add(new Snake(11,3));
	}
	
	public void updateSnakeList()
	{
		for(int i = 1; i < snakeList.size(); i++)
		{
			Position prevOldPosition = snakeList.get(i-1).getOldPosition();
			snakeList.get(i).setPosition(prevOldPosition);
		}
	}
	
	public void paintSnake(Graphics g)
	{
		g.setColor(Color.BLUE);
		for (Snake snakePiece : snakeList) {
			int snakeXPos = snakePiece.getxPos();
			int snakeYPos = snakePiece.getyPos();
			// Add two to position to account for first lines on the boundry
			g.fillRect(snakeXPos * Position.SQUARE_SPACING + 2, snakeYPos * Position.SQUARE_SPACING + 2, Position.SQUARE_LENGTH, Position.SQUARE_LENGTH);	
		}
		
	}

	public void moveSnake()
	{
		boolean outOfBounds = false;
		switch(direction)
		{
			case KeyEvent.VK_RIGHT:
	            //System.out.println("Right key pressed");
	            outOfBounds = rightKeyPressed();
	            break;
			case KeyEvent.VK_LEFT:
	            //System.out.println("Left key pressed");
				outOfBounds = leftKeyPressed();
				break;
			case KeyEvent.VK_DOWN:
	            //System.out.println("Down Key pressed");
	            outOfBounds = downKeyPressed();
	            break;
			case KeyEvent.VK_UP:
	            //System.out.println("Up key pressed");
	            outOfBounds =  upKeyPressed();
	            break;
	        default:
	        	System.out.println("Invalid Key");
	        	break;
		}
		
		
       if(outOfBounds)
       {
       	gameOver();
       } 
       repaint();
	}
	
	public Pair<Integer,ArrayList<Snake>> getSnake()
	{
		return new Pair<Integer, ArrayList<Snake>>(direction,snakeList);
	}
	
	public void gameOver()
	{
		System.out.println("Game Over");
		direction = KeyEvent.VK_RIGHT;
    	resetGame();
	}
	
	public void resetGame()
	{
		snakeList.clear();
		initializeSnakes();
	}
	
	public void keyPressed(KeyEvent e)
	{
		direction = e.getKeyCode();
		//moveSnake(e.getKeyCode());
	}
	
	public boolean rightKeyPressed()
	{
		Snake head = snakeList.get(0);
		head.setxPos(head.getxPos() + 1);
		updateSnakeList();
		return checkCollision();
	}
	
	public boolean leftKeyPressed()
	{
		Snake head = snakeList.get(0);
		head.setxPos(head.getxPos() - 1);
		updateSnakeList();
		
		return checkCollision();
	}
	
	public boolean upKeyPressed()
	{
		Snake head = snakeList.get(0);
		head.setyPos(head.getyPos() - 1);
		updateSnakeList();
		return checkCollision();
	}
	
	public boolean downKeyPressed()
	{
		Snake head = snakeList.get(0);
		head.setyPos(head.getyPos() + 1);
		updateSnakeList();
		return checkCollision();
	}
	
	public boolean checkCollision()
	{
		
		Snake head = snakeList.get(0);
		
		// Check Collision against width bounds
		if(head.getxPos() * Position.SQUARE_SPACING + (2 * Position.LINE_WIDTH) >= Position.WIDTH ||
				head.getxPos() * Position.SQUARE_SPACING < 0)
		{
			return true;
		}
		
		// Check collision against height bounds
		if(head.getyPos() * Position.SQUARE_SPACING + (2 * Position.LINE_WIDTH) >= Position.HEIGHT ||
				head.getyPos() * Position.SQUARE_SPACING < 0)
		{
			return true;
		}
		
		// Check collision against snake list
		if(head.equals(snakeList))
		{
			return true;
		}
		
		// Check collision against bait
		if(head.getPosition().equals(bait.getPosition()))
		{
			System.out.println("Snake Bait Collision");
			Snake tailSnake = snakeList.get(snakeList.size() - 1);
			snakeList.add(new Snake(tailSnake.getOldPosition()));
			bait = new Bait(snakeList);
		}

		return false;
		
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// Do Nothing
		//System.out.println("Key Typed");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Do Nothing
		//System.out.println("Key Released");
	}
	
}
