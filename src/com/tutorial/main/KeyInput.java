package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.tutorial.main.Game.STATE;

public class KeyInput extends KeyAdapter 
{
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	Game game;
	public KeyInput(Handler handler, Game game)
	{
		this.handler = handler;
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
		this.game = game;
	}
	public void keyPressed(KeyEvent e)// when key is pressed velocity of the object increases
{
	int key = e.getKeyCode();//setting key to letter binding. When key is pressed it will display a number value corresponding with key pressed.
	//System.out.println(key);
	//if object is a player, if they press up/down/left/right it moves at 5 velocity
	for(int i = 0; i < handler.object.size(); i++)
	{
		GameObject tempObject = handler.object.get(i);
		if(tempObject.getID() == ID.Player)
		{
			if(key == KeyEvent.VK_UP)
			{
				tempObject.setVelY(- 5);
				keyDown[0] = true;
			}
			if(key == KeyEvent.VK_DOWN)
			{
				tempObject.setVelY(5);
				keyDown[1] = true;
			}
			if(key == KeyEvent.VK_LEFT)
			{
				tempObject.setVelX(- 5);
				keyDown[2] = true;
			}
			if(key == KeyEvent.VK_RIGHT)
				{tempObject.setVelX(5);
				keyDown[3] = true;
				}
		}
		
		
	}
	if(key == KeyEvent.VK_ESCAPE)
	{
		if(Game.state == STATE.Game|| Game.state == STATE.Game2)
		{
		if(Game.paused)
		{
			Game.paused = false;
		}
		else
		Game.paused = true;
	}
	}
}
public void keyReleased(KeyEvent e) //when key is released the velocity of the object is set back to 0
{
	int key = e.getKeyCode();
	for(int i = 0; i < handler.object.size(); i++)
	{
		GameObject tempObject = handler.object.get(i);
		if(tempObject.getID() == ID.Player)
		{
			if(key == KeyEvent.VK_UP)
			{
				tempObject.setVelY(- 5);
				keyDown[0] = false;
			}
			if(key == KeyEvent.VK_DOWN)
			{
				tempObject.setVelY(5);
				keyDown[1] = false;
			}
				;
			if(key == KeyEvent.VK_LEFT)
			{
				
				tempObject.setVelX(- 5);
				keyDown[2] = false;
			}
			if(key == KeyEvent.VK_RIGHT)
			{
				tempObject.setVelX(5);
				keyDown[3] = false;
				
			}
			
			//for vertical movement
			if(!keyDown[0] && !keyDown[1])
			{
				tempObject.setVelY(0);
			}
			
			//for horizontal movement
			if(!keyDown[3] && !keyDown[2]) 
			{
				tempObject.setVelX(0);
			}
			
		}
	}
}
}
