package main;

import java.awt.Color;

import javax.swing.JFrame;

import GUI.Gui;

public class Main {
	
	public static void main(String args[])
	{
		Gui gui = new Gui();
		gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
		gui.setUndecorated(true);
		gui.setLocationRelativeTo(null);
	    gui.setBackground(new Color(0, 0, 0, 0));
	    gui.setVisible(true);
	}
}
