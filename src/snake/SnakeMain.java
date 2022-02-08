package snake;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class SnakeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("This is the way");
		System.out.println("This is the way 2");
		int width = 600;
		int height = 600;
		SwingUtilities.invokeLater(() ->  {
			var panel = new SnakePanel(width,height);
			panel.setBorder(new LineBorder(Color.BLACK));
			panel.setBackground(Color.GRAY.darker());
			panel.setLayout(null);
			var frame = new JFrame();
			frame.addKeyListener(panel);
			frame.setSize(width,height);
//			frame.add(panel, new GridBagConstraints());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("Snake Panel");
			frame.setLocationRelativeTo(null);
			frame.getContentPane().add(panel, BorderLayout.CENTER);
			frame.setVisible(true);
		});
	}
}
