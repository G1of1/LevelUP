package com.tutorial.main;

import java.awt.Canvas;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas
{

	private static final long serialVersionUID = 1L;
	
	public Window(int width, int height, String title, Game game)
	{
		JFrame frame = new JFrame(title); //create JFrame
		frame.setPreferredSize(new Dimension(width,height)); //setSize of jFrame
		frame.setMaximumSize(new Dimension(width,height)); //set Max of jFrame
		frame.setMinimumSize(new Dimension(width,height)); //set Min of jFrame
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //allow x button to work
		frame.setResizable(false); //makes jframe not resizable
		frame.setLocationRelativeTo(null); //sets jframe to center of screen instead of left
		frame.add(game); //adds game class into to jframe
		frame.setVisible(true); //allows user to see frame
		game.start(); //starts the game
	}
	

}
