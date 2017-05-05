package main;

import java.io.IOException;

//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Toolkit;

//import javax.swing.JFrame;

import GUI.CombinationGui;
//import GUI.Gui;

public class Main {
	
	public static void main(String args[])
	{
		try {
			@SuppressWarnings("unused")
			CombinationGui g = new CombinationGui();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
