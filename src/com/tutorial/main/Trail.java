package com.tutorial.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject {

	private float alpha = 1;
	private Handler handler;
	private Color color;
	private int width, height; 
	private float life; //used for 
	public Trail(int x, int y, ID id, Color color, int width, int height, float life, Handler handler) 
	{
		super(x, y, id);
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
		this.handler = handler;
	}

	
	public void tick() 
	{
		if(alpha > life)
			alpha -= (life - 0.01);
		else
			handler.removeObject(this);
		
	}

	
	public void render(Graphics g) //renders the trails
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillRect((int)x, (int)y, (int)width, (int)height);//customizable for different objects
		
		g2d.setComposite(makeTransparent(1));
		
	}
	private AlphaComposite makeTransparent(float a) //create the transparent trails
	{
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, a));
	}
	
	public Rectangle getBounds() //is not needed for trails
	{
		
		return null;
	}

}
