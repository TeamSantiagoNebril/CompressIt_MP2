package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConsoleJPanel extends JPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel[] consoleText;
	private int numRow;
	public ConsoleJPanel(int numRow)
	{
		setOpaque(false);
		this.numRow = numRow;
		consoleText = new JLabel[numRow];
		setLayout(new GridLayout(numRow, 1));
		for(int i = 0; i < numRow; i++)
		{
			consoleText[i] = new JLabel("");
			consoleText[i].setForeground(Color.ORANGE);
			consoleText[i].setFont(new Font("Arial", Font.PLAIN, 14));
			consoleText[i].setOpaque(false);
			add(consoleText[i]);
		}
		consoleText[3].setFont(new Font("Arial", Font.BOLD, 15));
		
	}
	
	public void addText(String input)
	{
		for(int i = 0 ; i < numRow-1; i++)
		{
			consoleText[i].setText(consoleText[i+1].getText());
		}
		consoleText[numRow-1].setText(input);
	}
}
