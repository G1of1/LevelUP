package com.tutorial.main;
import java.util.*;

import java.awt.Color;
import java.awt.Graphics;

public class HUD 
{
	public static int HEALTH = 100;
	public static int score = 0;
	private int level = 1;
	private int greenValue = 255;
	private int h1;

	public void tick()
	{
		//HEALTH--; //decreases health
		HEALTH = Game.clamp(HEALTH, 0, 100); //makes sure health won't go pass 0 or 100
		
		greenValue = Game.clamp(greenValue, 0, 255); //make sure color doesn't pass 0 or 255
		
		greenValue = HEALTH * 2;
		
		score++;
		
		//System.out.println(score);
	
	
	}
	
	
	
	
	public void render(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(15, 15, 200, 25);
		
		
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, (int)HEALTH * 2, 25);
		
		g.setColor(Color.WHITE);
		g.drawRect(15, 15, 200, 25);
		
		g.setColor(Color.WHITE);
		g.drawString("Health: " + (int)HEALTH, 10, 10);

		g.setColor(Color.WHITE);
		g.drawString("Score: " + score, 10, 70);
		
		g.setColor(Color.WHITE);
		g.drawString("LEVEL: " + level, 10, 55);
		
		g.setColor(Color.WHITE);
		g.drawString("FPS: " + Game.frames1, 150, 10);
		
		
	
	}
	
	public void setScore(int score)
	{
		this.score = score;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	public int getHealth()
	{
		return HEALTH;
	}
}
