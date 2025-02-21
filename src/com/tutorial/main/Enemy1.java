package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy1 extends GameObject {

	private Handler handler;
	public Enemy1(int x, int y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;	
		velX = 7;
		velY = 7;
		
	}
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 15, 15);
	}

	public void tick() 
	{
		x+= velX;
		y+= velY;
		
		if(y <= 0 || y>= Game.HEIGHT - 65)
			velY *= -1;
		if(x <= 0 || x>= Game.WIDTH - 42)
			velX *= -1;
		handler.addObject(new Trail(x, y, ID.Trail, Color.GREEN, 30, 30, 0.1f, handler));
	}

	
	public void render(Graphics g) 
	{
		if(id == ID.Enemy)
		{
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, 15, 15);
		}
		else
			g.setColor(Color.WHITE);
			g.fillRect((int)x, (int)y, 30, 30);
			
		
	}
}
