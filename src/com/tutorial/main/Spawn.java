package com.tutorial.main;

import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Spawn 
{
	private Handler handler;
	private HUD hud;
	public static int scoreKeep = 0;
	private Random random = new Random();
	
	public Spawn(Handler handler, HUD hud)
	{
		this.handler = handler;
		this.hud = hud;
		
	}
	
	public void tick()//when score increase by set number, the level(in HUD class)increases
	{
		//scorekeep goes back to zero when the level increases in order to work correctly
	
			if(Game.paused == false)
			{
				scoreKeep++;
			if(scoreKeep >= 1000)
				{
				scoreKeep = 0;
				hud.setLevel(hud.getLevel() + 1);
				Game.lvlup();
				
				if(hud.getLevel() == 2)
				{handler.addObject(new Enemy2(random.nextInt(Game.WIDTH - 50),random.nextInt(Game.HEIGHT - 50), ID.Enemy2, handler));
				handler.addObject(new Enemy2(random.nextInt(Game.WIDTH - 50),random.nextInt(Game.HEIGHT - 50), ID.Enemy2, handler));
				handler.addObject(new Enemy1(random.nextInt(Game.WIDTH- 50),random.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
				}
		
				else if(hud.getLevel() == 3)
				{
			handler.addObject(new Enemy3(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.Enemy3, handler));
			handler.addObject(new Enemy4(random.nextInt(Game.WIDTH - 75), random.nextInt(Game.HEIGHT - 75), ID.Enemy4, handler));
				}
				else if(hud.getLevel() == 4)
				{
			handler.addObject(new Enemy4(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.Enemy4, handler));
			handler.addObject(new Enemy4(random.nextInt(Game.WIDTH - 75), random.nextInt(Game.HEIGHT - 75), ID.Enemy4, handler));
				}
		
				else if(hud.getLevel() == 5)
				{
					handler.clearEnemys();
					handler.addObject(new Enemy5(Game.WIDTH /2 - 50, -100, ID.Enemy5, handler));
				}
				
				else if(hud.getLevel() == 6)
				{
					handler.clearEnemys();
					handler.addObject(new Enemy3(random.nextInt(Game.WIDTH - 50),random.nextInt(Game.HEIGHT - 50), ID.Enemy3, handler));
					handler.addObject(new Enemy2(random.nextInt(Game.WIDTH - 50),random.nextInt(Game.HEIGHT - 50), ID.Enemy2, handler));
					handler.addObject(new Enemy1(random.nextInt(Game.WIDTH- 50),random.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
					handler.addObject(new Enemy3(random.nextInt(Game.WIDTH - 50),random.nextInt(Game.HEIGHT - 50), ID.Enemy3, handler));
					handler.addObject(new Enemy2(random.nextInt(Game.WIDTH - 50),random.nextInt(Game.HEIGHT - 50), ID.Enemy2, handler));
					handler.addObject(new Enemy1(random.nextInt(Game.WIDTH- 50),random.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
				}
				
				else if(hud.getLevel() == 7)
				{
			handler.addObject(new Enemy3(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.Enemy3, handler));
			handler.addObject(new Enemy3(random.nextInt(Game.WIDTH - 75), random.nextInt(Game.HEIGHT - 75), ID.Enemy3, handler));
			handler.addObject(new Enemy3(random.nextInt(Game.WIDTH - 75), random.nextInt(Game.HEIGHT - 75), ID.Enemy3, handler));
			
				}
				else if(hud.getLevel() == 8)
				{
			handler.addObject(new Enemy4(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.Enemy4, handler));
			handler.addObject(new Enemy4(random.nextInt(Game.WIDTH - 75), random.nextInt(Game.HEIGHT - 75), ID.Enemy4, handler));
			handler.addObject(new Enemy4(random.nextInt(Game.WIDTH - 75), random.nextInt(Game.HEIGHT - 75), ID.Enemy4, handler));
			
				}
				else if(hud.getLevel() == 9)
				{
			handler.addObject(new Enemy3(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.Enemy3, handler));
			handler.addObject(new Enemy2(random.nextInt(Game.WIDTH - 75), random.nextInt(Game.HEIGHT - 75), ID.Enemy2, handler));
			handler.addObject(new Enemy1(random.nextInt(Game.WIDTH - 75), random.nextInt(Game.HEIGHT - 75), ID.Enemy, handler));
			
				}
				
				else if(hud.getLevel() == 10)
				{
					handler.clearEnemys();
					handler.addObject(new Enemy5(Game.WIDTH /2 - 50, -100, ID.Enemy5, handler));
			handler.addObject(new Enemy4(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.Enemy4, handler));
			handler.addObject(new Enemy4(random.nextInt(Game.WIDTH - 75), random.nextInt(Game.HEIGHT - 75), ID.Enemy4, handler));
			handler.addObject(new Enemy4(random.nextInt(Game.WIDTH - 75), random.nextInt(Game.HEIGHT - 75), ID.Enemy4, handler));
			
				}
				}
			}
			
			if(hud.getLevel() > 10)
			{
				handler.clearEnemys();
				handler.clearPlayer();
				Game.state = STATE.Complete;
					
			}

		}
	
	
	
		
	
		
	
	
	
	}



