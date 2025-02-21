package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy5 extends GameObject {

	private Handler handler;
	private int timer = 25;
	private int timer2 = 50;
	Random r = new Random();
	public Enemy5(int x, int y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;	
		velX = 0;
		velY = 4;
		
	}
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 96, 96);
	}

	public void tick() 
	{
		x+= velX;
		y+= velY;
		//is the timer for the enemy boss. When timer reaches zero the velocity stops
		if(timer <= 0)
			velY = 0;
		else 
			timer--;
		
		if(timer <=0)
		{
			timer2--;
		}
		if(timer2 <= 0)
		{
			if(velX == 0)
				velX = 10;
			
			int spawn = r.nextInt(10);
			if(spawn == 0)
			{
				for(int i = 0; i < 5; i++)
					handler.addObject(new Bullets(x, y, ID.Bullets, handler));
				
			}
			
		}
			//if(y <= 0 || y>= Game.HEIGHT - 65)
			//velY *= -1;
		if(x <= 0 || x>= Game.WIDTH - 120)
			velX *= -1;
		handler.addObject(new Trail(x, y, ID.Trail, Color.ORANGE, 96, 96, 0.05f, handler));
	}

	
	public void render(Graphics g) 
	{
		if(id == ID.Enemy5)
		{
		g.setColor(Color.ORANGE);
		g.fillRect((int)x, (int)y, 96, 96);
		}
		else
			g.setColor(Color.WHITE);
			g.fillRect((int)x, (int)y, 30, 30);
			
		
	}
}
