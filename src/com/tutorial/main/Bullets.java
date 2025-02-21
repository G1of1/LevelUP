package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Bullets extends GameObject {

	private Handler handler;
	Random r = new Random();
	
	public Bullets(int x, int y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;	
		velX = r.nextInt(5 - -5) + -5;
		velY = 7;
		
	}
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 20, 20);
	}

	public void tick() 
	{
		x += velX;
		y += velY;
		//if(y <= 0 || y>= Game.HEIGHT - 65)
			//velY *= -1;
		//if(x <= 0 || x>= Game.WIDTH - 42)
			//velX *= -1;
		if(y >= Game.HEIGHT)
		{
			handler.removeObject(this);
		}
		handler.addObject(new Trail(x, y, ID.Trail, Color.ORANGE, 20, 20, 0.1f, handler));
	}

	
	public void render(Graphics g) 
	{
		if(id == ID.Bullets)
		{
		g.setColor(Color.ORANGE);
		g.fillRect((int)x, (int)y, 20, 20);
		}
		else
			g.setColor(Color.WHITE);
			g.fillRect((int)x, (int)y, 30, 30);
			
		
	}
}
