package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Game extends Canvas implements Runnable
{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1080;
	public static final int HEIGHT = WIDTH / 12 *9;
	private HUD hud;
	public static boolean paused = false;
	private Thread thread; //game run within thread
	private boolean running = false;
	public static int diff = 0;
	private Random r = new Random();
	private Handler handler;
	public static int frames1;
	private Spawn spawn;
	private spawn2 spawn2;
	public Menu menu;
	public static Sound sound = new Sound();
	public static Sound sound1 = new Sound();
	public static Sound sound2 = new Sound();
	public static String file = "data/scores.txt";
	public static String file2 = "data/scores2.txt";
	public static String scores[] = new String[10];
	//used to create the state of the game. The ID is used to determine the state of the game.
	public enum STATE {
		Menu(),
		PlayerDead(),
		Help(),
		Select(),
		Game(),
		Game2(),
		Scores(),
		Complete();

	}
	//create the instance of the state of the application
	public static STATE state = STATE.Menu;
	public Game()
	{
		handler = new Handler();
		hud = new HUD();
		new Window(WIDTH, HEIGHT, "Level Up", this);
		
	
		spawn = new Spawn(handler, hud);
		spawn2 = new spawn2(handler, hud);
		 menu = new Menu(this, handler, hud);
	
		this.addKeyListener(new KeyInput(handler,this));
		this.addMouseListener(menu);

		
		playMusic(1);
		sound.print();
	}
	public synchronized void start()
	{
		thread = new Thread(this);//initializing thread as new thread
		thread.start();//starting the thread
		running = true;
	}
	
	public synchronized void stop()
	{
		try {
			thread.join(); //stopping thread
			running = false;
		}catch(Exception e) {
			e.printStackTrace(); //run and error bug in console
		}
		
	}
	public void run()//updates FPS every second while running
	{
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		frames1 = frames;
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1)
			{
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames1 = frames;
				frames = 0;
				
			}
		}
		stop();
	}
	private void tick()
	{
		 //because of different objects handler will always be running
		//updates the hud and spawn for when the state of the application is in the game state
		
		if(state == STATE.Game)
		{
			
			if(!paused)
			{
				handler.tick();
				hud.tick();
				spawn.tick();
				if(hud.getHealth() <=0)
				{
					hud.HEALTH = 100;
					//saveScore(hud.getScore());
					handler.clearEnemys();
					handler.clearPlayer();
					state = STATE.PlayerDead;
					String strScore = String.valueOf(hud.getScore());
					saveScore(strScore);
					spawn.scoreKeep = 0;
					
				}
			}
		}
		else if(state == STATE.Game2)
		{
			
			if(!paused)
			{
				handler.tick();
				hud.tick();
				spawn2.tick();
				if(hud.getHealth() <= 0)
				{
					hud.HEALTH = 100;
					
					handler.clearEnemys();
					handler.clearPlayer();
					state = STATE.PlayerDead;
					String strScore = String.valueOf(hud.getScore());
					saveHardScore(strScore);
					spawn2.scoreKeep = 0;
				}
			}
		}
		else if(state == STATE.Menu | state == STATE.PlayerDead | state == STATE.Select | state == STATE.Help)
		{
			//menu.tick();
			handler.tick();
		}
	}
	private void render()
	{
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics(); //adds graphics that add the colored screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		
		
		if(state == STATE.Game || state == STATE.Game2)
		{
		
		hud.render(g);
		if(paused)
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			Font font = new Font("arial", 1, 30);
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("PAUSED", 495, 100);
			
			g.setColor(Color.ORANGE);
			g.fillRect(495, 200, 125, 35);
			g.drawRect(492, 197, 131, 41);
			g.setFont(new Font("arial", 1, 15));
			g.setColor(Color.WHITE);
			g.drawString("Back to Menu", 507, 225);
			
		}
		
		}
		else if(this.state == STATE.Menu | this.state == STATE.PlayerDead | this.state == STATE.Select | this.state == STATE.Help | this.state == STATE.Complete | this.state == STATE.Scores)
		{
			if(menu != null)
				menu.render(g);
			
		}		
		g.dispose();
		bs.show();
	}
	//makes sure game objects cannot escape
	public static int clamp(int value, int min, int max)
	{
		if(value >= max)
			return value = max;
		else if(value <= min)
				return value = min;
			else
				return value;
	}
	
	public static void playMusic(int i)
	{
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	
	public static void stopMusic()
	{
		sound.stop();
	}
	
	public static void lvlup()
	{
		sound2.setFile(2);
		sound2.play();
	}
	
	public static void button()
	{
		sound2.setFile(3);
		sound2.play();
	}
	
	public static void addScoresFile()
	{
        // Define the folder and file path
        String folderPath = "data";
        String filePath = folderPath + "/scores.txt";

        try {
            // Create the directory if it doesn't exist
            File folder = new File(folderPath);
            if (!folder.exists()) {
                if (folder.mkdir()) {
                    System.out.println("Directory created: " + folder.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory.");
                    return; // Exit if folder creation fails
                }
            }

            // Create a File object for the file
            File file = new File(filePath);

            // Check if the file already exists
            if (file.exists()) {
                System.out.println("File already exists: " + file.getAbsolutePath());
            } else {
                // Create a new file
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getAbsolutePath());
                } else {
                    System.out.println("Failed to create the file.");
                    return; // Exit if file creation fails
                }
            }

            // Write data to the file

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
	public static void addHardScoresFile() {
		String folderPath = "data";
        String filePath = folderPath + "/scores2.txt";

        try {
            // Create the directory if it doesn't exist
            File folder = new File(folderPath);
            if (!folder.exists()) {
                if (folder.mkdir()) {
                    System.out.println("Directory created: " + folder.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory.");
                    return; // Exit if folder creation fails
                }
            }

            // Create a File object for the file
            File file = new File(filePath);

            // Check if the file already exists
            if (file.exists()) {
                System.out.println("File already exists: " + file.getAbsolutePath());
            } else {
                // Create a new file
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getAbsolutePath());
                } else {
                    System.out.println("Failed to create the file.");
                    return; // Exit if file creation fails
                }
            }

            // Write data to the file

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
	}
	public static void saveScore(String score)
	{
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			int cnt = 0;
			// Read the file line by line
			while ((line = reader.readLine()) != null && cnt < scores.length) {
				scores[cnt] = line;
				cnt++;
			}
	
			// Add the new score to the array at the next available position
			if (cnt < scores.length) {
				scores[cnt] = score;
			} else {
				// If the array is full, replace the lowest score (assumed to be at index 0)
				scores[0] = score;
			}
	
			// Filter out nulls and sort the array
			String[] nonNullScores = Arrays.stream(scores)
										   .filter(s -> s != null)
										   .toArray(String[]::new);
	
			Arrays.sort(nonNullScores);
	
			// Write sorted scores back to the file, overwriting the old data
			try (FileWriter writer = new FileWriter(new File(file), false)) {
				for (String s : nonNullScores) {
					writer.write(s + "\n");
				}
			}
	
			System.out.println(Arrays.toString(nonNullScores));
	
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}
	public static void saveHardScore(String score) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file2))) {
			String line;
			int cnt = 0;
			// Read the file line by line
			while ((line = reader.readLine()) != null && cnt < scores.length) {
				scores[cnt] = line;
				cnt++;
			}
	
			// Add the new score to the array at the next available position
			if (cnt < scores.length) {
				scores[cnt] = score;
			} else {
				// If the array is full, replace the lowest score (assumed to be at index 0)
				scores[0] = score;
			}
	
			// Filter out nulls and sort the array
			String[] nonNullScores = Arrays.stream(scores)
										   .filter(s -> s != null)
										   .toArray(String[]::new);
	
			Arrays.sort(nonNullScores);
	
			// Write sorted scores back to the file, overwriting the old data
			try (FileWriter writer = new FileWriter(new File(file2), false)) {
				for (String s : nonNullScores) {
					writer.write(s + "\n");
				}
			}
	
			System.out.println(Arrays.toString(nonNullScores));
	
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}
	public static void main(String args[])
	{
		new Game();
		addScoresFile();
		addHardScoresFile();
		
	}
}
