package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
//import javax.swing.SwingConstants;

public class Gui extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar customMenu;
	private JButton closeButton;
	private JButton minimizeButton;
	//private int pX;
	//private int pY;
	private JPanel mainPanel;
	private JFrame frame;
	//private JPanel buttonPanel;
	public Gui(){
		frame = this;
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(new Color((float)0,(float) 0, (float)0,(float) 0.70));
		
		customMenu = new JMenuBar();
		//customMenu.setLayout(new BorderLayout());
		customMenu.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		closeButton = new JButton("X");
		closeButton.setFont(new Font("Arial", Font.BOLD, 17));
		closeButton.setForeground(Color.WHITE);
		closeButton.setFocusPainted(false);
		closeButton.setBackground(new Color(0, 0, 0, 0));
		closeButton.setBorderPainted(false);
		closeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		closeButton.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				closeButton.setOpaque(true);
				closeButton.setBackground(new Color(232, 17, 35));
			}
			public void mouseExited(MouseEvent e){
				closeButton.setOpaque(false);
				closeButton.setBackground(new Color(0, 0, 0, 0));
				repaint();
			}
		});
		
		minimizeButton = new JButton("_");
		minimizeButton.setFont(new Font("Arial", Font.BOLD, 17));
		minimizeButton.setForeground(Color.WHITE);
		minimizeButton.setFocusPainted(false);
		minimizeButton.setBackground(new Color(0, 0, 0, 0));
		minimizeButton.setBorderPainted(false);
		minimizeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.setState(Frame.ICONIFIED);
			}
		});
		minimizeButton.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				minimizeButton.setOpaque(true);
				minimizeButton.setBackground(new Color(232, 17, 35));
			}
			public void mouseExited(MouseEvent e){
				minimizeButton.setOpaque(false);
				minimizeButton.setBackground(new Color(0, 0, 0, 0));
				repaint();
			}
		});
		
		JLabel title = new JLabel("Multi-Channel Image Compression using Huffman Coding                                                                                                                                               ");
		title.setFont(new Font("Arial", Font.BOLD, 12));
		title.setForeground(Color.WHITE);
		
		customMenu.add(title);//try
		
		
		customMenu.add(minimizeButton);
		customMenu.add(closeButton);
		customMenu.setBorderPainted(false);
		customMenu.setOpaque(false);
		//customMenu.setBackground(new Color(0f, 0f, 0f, 0.95f));
		
		mainPanel.add(customMenu, BorderLayout.NORTH);
		add(mainPanel);
	}
	
	
	//public static void main(String args[]){

	//}
}
