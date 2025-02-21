package com.tutorial.main;

import java.awt.Color;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Player extends GameObject
{
//Random r = new Random();
	Handler handler;
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	public Rectangle getBounds() //gets the hitbox for the player object
{
	return  new Rectangle((int)x, (int)y, 15, 15);
}

	public void tick() //updates the player object as the games is running
	{
		x+= velX;
		y+= velY;
		handler.addObject(new Trail(x, y, ID.Trail, Color.WHITE, 15, 15, 0.1f, handler));
		x = Game.clamp(x, 0, Game.WIDTH - 31);
		y = Game.clamp(y, 0, Game.HEIGHT - 54);
		collision();
	}

	public void collision() //reduces health in scenario of collision
	{
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Enemy| tempObject.getID() == ID.Enemy2 | tempObject.getID() == ID.Enemy3 | tempObject.getID() == ID.Enemy4 |  tempObject.getID() == ID.Enemy5| tempObject.getID() == ID.Bullets | tempObject.getID() == ID.Enemy4H) 
				if(getBounds().intersects(tempObject.getBounds())) {
						HUD.HEALTH -=100;
				}
		}
	}	
	public void render(Graphics g) //renders the graphics for the player
	{
		
		Graphics2D g2d = (Graphics2D) g;
		
		if(id == ID.Player)
		{
		g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, 15, 15);
		}
		else
			g.setColor(Color.RED);
			g.fillRect((int)x, (int)y, 15, 15);
			
		
	}
	
	

}
