package com.tutorial.main;

import java.awt.Graphics;

import java.util.LinkedList;

//Handles all objects, updates them and renders them to screen
public class Handler 
{
LinkedList<GameObject> object = new LinkedList<GameObject>();

public void tick() //updates all game objects
{
	for(int i = 0; i < object.size(); i++)
	{
		GameObject tempObject = object.get(i);
		tempObject.tick();
	}
	
}
public void render(Graphics g) //renders game objects in list
{
	for(int i = 0; i < object.size(); i++)
	{
		GameObject tempObject = object.get(i);
		tempObject.render(g);
	}
}
public void addObject(GameObject object)
{
	this.object.add(object);
	
}
public void removeObject(GameObject object)
{
	this.object.remove(object);
}

public void clearEnemys()
{
	for(int i = 0; i < object.size(); i++)
	{
		GameObject temp = object.get(i);
		if((temp.getID() == ID.Player))
			object.clear();
			if(Game.state != Game.STATE.PlayerDead)
			addObject(new Player(Game.WIDTH / 2 - 15, Game.HEIGHT / 2 - 15, ID.Player, this));
	}
}	
public void clearPlayer()
{
	for(int i = 0; i < object.size(); i++)
	{
		GameObject temp = object.get(i);
		if((temp.getID() == ID.Player))
			object.clear();
	}
		
	
}

}
