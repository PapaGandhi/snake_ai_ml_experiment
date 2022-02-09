package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class SnakePanel extends JPanel implements KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int NUM_SQUARES = 25;
	private int baitXPos = 0;
	private int baitYPos = 0;
	ArrayList<Snake> snakeList = new ArrayList<Snake>();
	private Bait bait;
	
	public SnakePanel()
	{
		System.out.println("Height: " + getHeight());
		System.out.println("Width: " + getWidth());
		initializeSnakes();
		bait = new Bait(snakeList);
	}
	
	public SnakePanel(int width,int height)
	{
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
		for (int xPos = Position.xSpacing; xPos < getWidth(); xPos += Position.xSpacing)
		{
			g.fillRect(xPos, 0, 2, getHeight());
		}
	}
	
	public void paintHorzLines(Graphics g)
	{
		g.setColor(Color.BLACK);
		for (int yPos = Position.ySpacing; yPos < getWidth(); yPos += Position.ySpacing)
		{
			g.fillRect(0, yPos, getWidth(), 2);
		}
	}
	
	public void paintBait(Graphics g)
	{
		g.setColor(Color.RED);
		int baitXPos = bait.getxPos();
		int baitYPos = bait.getyPos();
		g.fillRect(baitXPos * Position.xSpacing, baitYPos * Position.ySpacing, Position.xSpacing, Position.ySpacing);
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
			g.fillRect(snakeXPos * Position.xSpacing, snakeYPos * Position.ySpacing, Position.xSpacing, Position.ySpacing);	
		}
		
	}
	
	public void gameOver()
	{
		System.out.println("Game Over");
    	resetGame();
	}
	
	public void resetGame()
	{
		snakeList.clear();
		initializeSnakes();
	}
	
	public void keyPressed(KeyEvent e)
	{
		 boolean outOfBounds = false;
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT:
	            System.out.println("Right key pressed");
	            outOfBounds = rightKeyPressed();
	            break;
			case KeyEvent.VK_LEFT:
	            System.out.println("Left key pressed");
				outOfBounds = leftKeyPressed();
				break;
			case KeyEvent.VK_DOWN:
	            System.out.println("Down Key pressed");
	            outOfBounds = downKeyPressed();
	            break;
			case KeyEvent.VK_UP:
	            System.out.println("Up key pressed");
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
		if(head.getxPos() * Position.xSpacing > Position.WIDTH ||
				head.getxPos() * Position.xSpacing < 0)
		{
			return true;
		}
		
		// Check collision against height bounds
		if((head.getyPos() + 2) * Position.ySpacing > Position.HEIGHT ||
				head.getyPos() * Position.ySpacing < 0)
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
			snakeList.add(new Snake(tailSnake.oldPosition));
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
