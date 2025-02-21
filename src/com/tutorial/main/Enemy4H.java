package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy4H extends GameObject {

	private Handler handler;
	private GameObject player;
	public Enemy4H(int x, int y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;	
		for(int i = 0; i < handler.object.size(); i++)
		{
			{if(handler.object.get(i).getID() == ID.Player)
				player = handler.object.get(i);
			}
		}
		velX = 30;
		velY = 30;
		
		
	}
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 20, 20);
	}

	public void tick() 
	{
		x+= velX;
		y+= velY;
		
		double diffX = x - player.getX() - 8;
		double diffY = y - player.getY() - 8;
		double distance = (float) Math.sqrt(((diffX) * (diffX)) + ((diffY) * diffY));
		
		velX = (int) ((-5.5 * diffX) / distance);
		velY = (int) ((-5.5 * diffY) /distance);
		
		if(y <= 0 || y>= Game.HEIGHT - 65)
			velY *= -1;
		if(x <= 0 || x>= Game.WIDTH - 42)
			velX *= -1;
		handler.addObject(new Trail(x, y, ID.Trail, Color.YELLOW, 20, 20, 0.1f, handler));
	}

	
	public void render(Graphics g) 
	{
		if(id == ID.Enemy4H)
		{
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 20, 20);
		}
		else
			g.setColor(Color.WHITE);
			g.fillRect(x, y, 30, 30);
			
		
	}
}
