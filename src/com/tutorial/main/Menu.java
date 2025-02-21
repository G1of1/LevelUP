package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.tutorial.main.Game.STATE;



public class Menu extends MouseAdapter 
{
	private Game game1;
	private HUD hud;
	private Handler handler;
	public String[] dead = new String[6];
	public String diff;
	public String choice;
	public static String file = "data/scores.txt";
	public static String file2 = "data/scores2.txt";
	public static String[] scores = new String[11];
	public static String[] scores2 = new String[11];
	ArrayList<Integer> lscores = new ArrayList<Integer>();
	
	
	private Random r = new Random();
	public Menu(Game game, Handler handler, HUD hud)
	{
		this.game1 = game;
		this.handler = handler;
		this.hud = hud;
		dead[0] = "YOU DIED...";
		dead[1] = "LMAO";
		dead[2] = "GIT GUD";
		dead[3] = "HOW'D YOU DIE??";
		dead[4] = "YOUR TRASH";
		dead[5] = "UNINSTALL";
		
		choice = dead[r.nextInt(dead.length)];
		
	}
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		//button in the menu
		if(game1.state == STATE.Menu)
		{
			//play game button
			if(mouseOver(mx, my, 490 , 167, 130, 40))
			{
				Game.button();
				game1.state = STATE.Select;
			}
			//help button
			if(mouseOver(mx, my, 490, 232, 130, 40))
			{
				Game.button();
				game1.state = STATE.Help;
			}
			//scores button
			if(mouseOver(mx, my, 490, 302, 130, 40))
			{
				Game.button();
				game1.state = STATE.Scores;
				loadScores();
				loadScores2();
			}
			//quit button
			if(mouseOver(mx, my, 490 , 367, 130, 40))
			{
				Game.button();
				System.exit(0);
			}
		}
		else if(game1.state == STATE.Game | game1.state == STATE.Game2)
		{
			if(Game.paused)
			{
				if(mouseOver(mx, my, 492, 197, 131, 41))
				{
					Game.button();
					Game.stopMusic();
					Game.playMusic(1);
					game1.state = STATE.Menu;
					
					
					Game.paused = false;
					handler.clearEnemys();
					handler.clearPlayer();
					hud.setScore(0);
					hud.setLevel(0);
					diff = "";
				}
			}
		}
		
		else if(game1.state ==STATE.Select)
		{
			//back button
			if(mouseOver(mx, my, 490 , 302, 130, 40))
				Game.button();
				game1.state = STATE.Menu;
			//normal button
			if(mouseOver(mx, my,490 , 167, 130, 40))
			{
				Game.button();
				game1.state = STATE.Game;
				diff = "Normal";
				Game.stopMusic();
				Game.playMusic(0);
				
				handler.clearEnemys();
				hud.setScore(0);
				hud.setLevel(1);
				handler.addObject(new Player(Game.WIDTH / 2 - 15, Game.HEIGHT / 2 - 15, ID.Player, handler));
				
				handler.addObject(new Enemy1(r.nextInt(Game.WIDTH - 80), r.nextInt(Game.HEIGHT- 80), ID.Enemy, handler));
				handler.addObject(new Enemy1(r.nextInt(Game.WIDTH- 80), r.nextInt(Game.HEIGHT -80), ID.Enemy, handler));
			}
			//hard button
			if(mouseOver(mx, my, 490 , 232, 130, 40))
			{
				Game.button();
				game1.state = STATE.Game2;
				diff = "Hard";
				Game.stopMusic();
				Game.playMusic(0);
			
				handler.clearEnemys();
				hud.setScore(0);
				hud.setLevel(1);
				handler.addObject(new Player(Game.WIDTH / 2 - 15, Game.HEIGHT / 2 - 15, ID.Player, handler));
				handler.addObject(new Enemy2(r.nextInt(Game.WIDTH - 80), r.nextInt(Game.HEIGHT- 80), ID.Enemy, handler));
				handler.addObject(new Enemy2(r.nextInt(Game.WIDTH- 80), r.nextInt(Game.HEIGHT -80), ID.Enemy, handler));
			}
			
		}
		 
		 else if(game1.state == STATE.Help)
		{
			if(mouseOver(mx, my, Game.WIDTH / 2 - 88, 502, 130, 40))
			{
				Game.button();
				game1.state = STATE.Menu;
			}
		}
		
		 else if(game1.state == STATE.PlayerDead)
		{
			if(mouseOver(mx, my, Game.WIDTH / 2 - 88, 502, 130, 40))
			{
				Game.button();
				game1.state = STATE.Menu;
				diff = "";
				Game.stopMusic();
				Game.playMusic(1);
			}
			
			if(mouseOver(mx, my, Game.WIDTH / 2 - 85, 600, 125, 35))
			{
				Game.button();
				if(diff.equals("Normal"))
				{	
					game1.state = STATE.Game;
				
									
					Game.stopMusic();
				Game.playMusic(0);
				
				handler.clearEnemys();
				hud.setScore(0);
				hud.setLevel(1);
				handler.addObject(new Player(Game.WIDTH / 2 - 15, Game.HEIGHT / 2 - 15, ID.Player, handler));
				
				handler.addObject(new Enemy1(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.Enemy, handler));
				handler.addObject(new Enemy1(r.nextInt(Game.WIDTH- 80), r.nextInt(Game.HEIGHT -80), ID.Enemy, handler));
			}
				else if(diff.equals("Hard"))
				{
					Game.diff = 1;
					game1.state = STATE.Game2;
					
					Game.diff = 0;					
					Game.stopMusic();
					Game.playMusic(0);
				
				handler.clearEnemys();
				hud.setScore(0);
				hud.setLevel(1);
				handler.addObject(new Player(Game.WIDTH / 2 - 15, Game.HEIGHT / 2 - 15, ID.Player, handler));
				
				handler.addObject(new Enemy2(r.nextInt(Game.WIDTH-80), r.nextInt(Game.HEIGHT-80), ID.Enemy, handler));
				handler.addObject(new Enemy2(r.nextInt(Game.WIDTH- 80), r.nextInt(Game.HEIGHT -80), ID.Enemy, handler));
				}
			}
		}
		 else if(game1.state == STATE.Complete)
		 {
			 if(mouseOver(mx, my, 480, 250, 125, 40))
			 {
				 game1.state = STATE.Menu;
				 Game.stopMusic();
				 Game.playMusic(1);
			 }
		 }
		
		 else if(game1.state == STATE.Scores)
		 {
			 if(mouseOver(mx, my, 475, 495, 125, 35))
			 {
				 Game.button();
				 game1.state = STATE.Menu;
			 }
		 }
		
	}
	public void mouseReleased(MouseEvent e){}
	
	// if the mouse's x and y positions are within the parameters of the buttons return true else false
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
	{   
		
		if(mx > x && (mx < (x + width)))
		{
			if(my > y && (my < (y + height))) 
				return true;
    }
   return false;
	}
	
	public void render(Graphics g)
	{
		if(game1.state == STATE.Menu)
		{
		Font fnt = new Font("arial", 1, 20);
		Font fnt2 = new Font("arial", 1 ,30);
				
		g.setFont(fnt2);
		g.setColor(Color.WHITE);
		g.drawString("Level Up", 490, 100);
		
		g.setFont(fnt);
		g.setColor(Color.ORANGE);
		g.fillRect(493, 170, 125, 35);
		
		g.setColor(Color.ORANGE);
		g.drawRect(490 , 167, 130, 40);
		
		g.setColor(Color.WHITE);
		g.drawString("PLAY GAME", Game.WIDTH / 2 -45, 195);
		
		
		
		g.setColor(Color.ORANGE);
		g.fillRect(493, 235, 125, 35);
		
		g.setColor(Color.ORANGE);
		g.drawRect(490 , 232, 130, 40);
		
		
		
		g.setColor(Color.WHITE);
		g.drawString("HELP", Game.WIDTH / 2 - 8, 260);
		
		g.setColor(Color.ORANGE);
		g.fillRect(493, 305, 125, 35);
		
		g.setColor(Color.ORANGE);
		g.drawRect(490, 302, 130, 40);
		
		g.setColor(Color.WHITE);
		g.drawString("SCORES", Game.WIDTH / 2 - 25, 330);
		
		g.setColor(Color.ORANGE);
		g.fillRect(493, 370, 125, 35);
		
		g.setColor(Color.ORANGE);
		g.drawRect(490 , 367, 130, 40);
		
		g.setColor(Color.WHITE);
		g.drawString("QUIT", Game.WIDTH / 2 - 8, 395);
		}
		else if(game1.state == STATE.Select)
		{
		Font fnt = new Font("arial", 1, 20);
		Font fnt2 = new Font("arial", 1 ,30);
				
		g.setFont(fnt2);
		g.setColor(Color.WHITE);
		g.drawString("SELECT DIFFUCLTY", Game.WIDTH /2 - 130, 100);
		
		g.setFont(fnt);
		g.setColor(Color.ORANGE);
		g.fillRect(493, 170, 125, 35);
		
		g.setColor(Color.ORANGE);
		g.drawRect(490 , 167, 130, 40);
		
		g.setColor(Color.WHITE);
		g.drawString("NORMAL", Game.WIDTH / 2 - 29, 195);
		
		
		
		g.setColor(Color.ORANGE);
		g.fillRect(493, 235, 125, 35);
		
		g.setColor(Color.ORANGE);
		g.drawRect(490 , 232, 130, 40);
		
		
		
		g.setColor(Color.WHITE);
		g.drawString("HARD", Game.WIDTH / 2 - 12, 260);
		
		
		
		g.setColor(Color.ORANGE);
		g.fillRect(493, 305, 125, 35);
		
		g.setColor(Color.ORANGE);
		g.drawRect(490 , 302, 130, 40);
		
		g.setColor(Color.WHITE);
		g.drawString("BACK", Game.WIDTH / 2 - 12, 330);
		}
		else if(game1.state == STATE.PlayerDead)
		{
			
			
			Font fnt = new Font("arial", 1, 20);
			Font fnt2 = new Font("arial", 1 ,30);
			{
			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawString(choice, Game.WIDTH / 2 - 75 , 50);
			
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Final Score: " + hud.getScore(), 50, 80);
			
			g.setColor(Color.ORANGE);
			g.fillRect(Game.WIDTH / 2 - 85, 505 , 125, 35);
			
			g.setColor(Color.ORANGE);
			g.drawRect(Game.WIDTH / 2 - 88, 502, 130, 40);
			
			g.setColor(Color.WHITE);
			g.drawString("BACK", 490, 529);
			
			g.setColor(Color.ORANGE);
			g.fillRect(Game.WIDTH / 2 - 85, 600, 125, 35);
			
			g.setColor(Color.ORANGE);
			g.drawRect(Game.WIDTH / 2 - 88, 597, 130, 40);
			
			g.setColor(Color.WHITE);
			g.drawString("TRY AGAIN", 460, 625);
			}
		}
		else if(game1.state == STATE.Help)
		{
			{
				Font fnt = new Font("arial", 1, 20);
				Font fnt2 = new Font("arial", 1 ,30);
						
				g.setFont(fnt2);
				g.setColor(Color.WHITE);
				g.drawString("DIRECTIONS", Game.WIDTH / 2 - 90 , 50);
				
				g.setFont(fnt);
				g.setColor(Color.WHITE);
				g.drawString("Use the arrow keys to move the player", 50, 80);
				g.drawString("Avoid the enemies that are zooming around the screen. ", 50, 105);
				g.drawString("Try to get as many points as possible.", 50, 140);
				g.drawString("The game progressively gets harder, so LOCK IN.", 50, 175);
				g.drawString("If your health reaches zero, YOUR COOKED.", 50, 210);
				g.drawString("There are bosses every 5 levels, so like I said, LOCK IN.", 50, 245);
				
				g.setFont(fnt2);
				g.drawString("THIS IS A DEMO.... THERE ARE ONLY 10 LEVELS", 50, 290);
				
				
				g.setColor(Color.ORANGE);
				g.fillRect(Game.WIDTH / 2 - 85, 505 , 125, 35);
				
				g.setColor(Color.ORANGE);
				g.drawRect(Game.WIDTH / 2 - 88, 502, 130, 40);
				
				g.setFont(fnt);
				g.setColor(Color.WHITE);
				g.drawString("BACK", 490, 529);
				
			}
		}
		
		else if(game1.state == STATE.Scores)
		{
			Font titleFont = new Font("arial", 1, 30);
			Font textFont = new Font("arial", 1, 15);
			
			g.setFont(titleFont);
			g.setColor(Color.WHITE);
			g.drawString("Top 10 Scores", 435, 50);
			
			//Normal Mode Scores
			g.setFont(textFont);
			g.drawString("Normal", 420, 100);
			g.drawString("1.  " + scores[9], 425, 125);
			g.drawString("2.  " + scores[8], 425, 150);
			g.drawString("3.  " + scores[7], 425, 175);
			g.drawString("4.  " + scores[6], 425, 200);
			g.drawString("5.  " + scores[5], 425, 225);
			g.drawString("6.  " + scores[4], 425, 250);
			g.drawString("7.  " + scores[3], 425, 275);
			g.drawString("8.  " + scores[2], 425, 300);
			g.drawString("9.  " + scores[1], 425, 325);
			g.drawString("10.  "+ scores[0], 425, 350);

			//Hard Mode Scores
			g.setFont(textFont);
			g.drawString("Hard", 600, 100);
			g.drawString("1.  " + scores2[9], 605, 125);
			g.drawString("2.  " + scores2[8], 605, 150);
			g.drawString("3.  " + scores2[7], 605, 175);
			g.drawString("4.  " + scores2[6], 605, 200);
			g.drawString("5.  " + scores2[5], 605, 225);
			g.drawString("6.  " + scores2[4], 605, 250);
			g.drawString("7.  " + scores2[3], 605, 275);
			g.drawString("8.  " + scores2[2], 605, 300);
			g.drawString("9.  " + scores2[1], 605, 325);
			g.drawString("10.  "+ scores2[0], 605, 350);


			
			
			g.setColor(Color.ORANGE);
			g.fillRect(475, 495, 125, 35);
			g.drawRect(472, 492, 130, 40);
			
			g.setFont(textFont);
			g.setColor(Color.WHITE);
			g.drawString("Back to Menu", 487, 518);
			
		}
		
		else if(game1.state == STATE.Complete)
		{
			Font fnt = new Font("arial", 1, 20);
			Font fnt2 = new Font("arial", 1 ,30);
			Font fnt3 = new Font("arial", 1, 15);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			
			g.setFont(fnt2);
			g.setColor(Color.GREEN);
			g.drawString("GAME COMPLETE!!!", 400, 100);
			
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Final Score: " + hud.getScore(), 455, 200);
			
			g.setColor(Color.GREEN);
			g.fillRect(480, 250, 125, 40);
			
			g.setColor(Color.GREEN);
			g.drawRect(477, 247, 130, 44);
			
			g.setFont(fnt3);
			g.setColor(Color.BLACK);
			g.drawString("Back to Menu", 490, 275);
			
			
			
			
			
		}
		
	}
	public static void loadScores()
	{
		try (BufferedReader reader = new BufferedReader(new FileReader(file)))
		{
			String line;
            System.out.println("File Contents:");
            int cnt = 0;
            // Read the file line by line
            while ((line = reader.readLine()) != null) 
            {
                scores[cnt] = line;
            	System.out.println(line);
            	cnt++;
            }
			}
		catch(IOException e)
		{
			System.out.println("An error occurred: " + e.getMessage());
		}
	}
	public static void loadScores2() {
		try (BufferedReader reader = new BufferedReader(new FileReader(file2)))
		{
			String line;
            System.out.println("File Contents:");
            int cnt = 0;
            // Read the file line by line
            while ((line = reader.readLine()) != null) 
            {
                scores2[cnt] = line;
            	System.out.println(line);
            	cnt++;
            }
			}
		catch(IOException e)
		{
			System.out.println("An error occurred: " + e.getMessage());
		}
	}
	public void tick(){}
	
	
}
