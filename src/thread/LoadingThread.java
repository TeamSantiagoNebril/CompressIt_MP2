package thread;

import GUI.ConsoleJPanel;
import GUI.Gui;

public class LoadingThread extends Thread
{
	
	private ConsoleJPanel console;
	private Gui gui;
	private String message;
	
	public LoadingThread(ConsoleJPanel c, Gui g, String m)
	{
		console = c;
		gui = g;
		message = m;
		
	}
	
	
	private boolean stop;
	public void run()
	{
		stop = false;
		int k = 1;
		String dot = "";
		String[] text = new String[3];
		for(int l = 1; l < 4; l++)
		{
			text[l-1] = console.consoleText[l].getText();
		}
		while(!stop)
		{
			for(int i = 0; i < 3; i++)
			{
				console.addText(text[i]);
			}
			for(int j = 0; j < k; j++)
			{
				dot += ".";
			}
			console.addText(message + dot);
			gui.repaint();
			if(k <=2)
			{
				k++;
			}
			else
			{
				k = 1;
			}
			dot = "";
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deActivate( Boolean bool)
	{
		stop = bool;
	}
}
