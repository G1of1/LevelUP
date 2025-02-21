package com.tutorial.main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound 
{
	Clip clip;
	URL soundURL[] = new URL[4];
	
	public Sound()
	{
		soundURL[0] = getClass().getResource("/sound/igmusic.wav");
		soundURL[1] = getClass().getResource("/sound/bgmusic.wav");
		soundURL[2] = getClass().getResource("/sound/lvlup.wav");
		soundURL[3] = getClass().getResource("/sound/buttonsfx.wav");
	}
	//sets the audio file
	public void setFile(int i)
	{
		try {
			//gets the sound from the array of sounds
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			//clip is initialized
			clip = AudioSystem.getClip();
			//open the sound from the array
			clip.open(ais);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	public void play()
	{
		if(clip != null)
		{
			clip.start();
		}
		
	}
	
	public void loop()
	{
		if(clip != null)
		{
		clip.loop(clip.LOOP_CONTINUOUSLY);
		}
	}
	
	public void stop()
	{
		if(clip != null)
		{
			clip.stop();
		}
		
	}
	
	public void print()
	{
		System.out.println(soundURL[0]);
	}
}
