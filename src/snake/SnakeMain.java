package snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.management.timer.Timer;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

public class SnakeMain {

	public static int WINDOWS_X_PADDING = 13;
	public static int WINDOWS_Y_PADDING = 36; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("This is the way");
		System.out.println("This is the way 2");
		int width = 654;
		int height = 654;
		SwingUtilities.invokeLater(() ->  {
			var panel = new SnakePanel(width,height);
			panel.setBorder(new LineBorder(Color.BLACK));
			panel.setBackground(Color.GRAY.darker());
			panel.setLayout(null);
			startSnakeMoving(panel);
			var frame = new JFrame();
			frame.addKeyListener(panel);
			frame.setSize(width + WINDOWS_X_PADDING,height + WINDOWS_Y_PADDING);
//			frame.add(panel, new GridBagConstraints());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("Snake Panel");
			frame.setLocationRelativeTo(null);
			frame.getContentPane().add(panel, BorderLayout.CENTER);
			frame.setVisible(true);
		});
	}
	
	
	public static void startSnakeMoving(SnakePanel s)
	{
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run()
			{
				ActionListener actionListener = new ActionListener() {
					public void actionPerformed(ActionEvent actionEvent) {
						s.moveSnake();
					}
				};
				Timer timer = new Timer(300, actionListener);
				timer.start();
						
			}
		});
	}
}
