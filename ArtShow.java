/**
 * ArtShow.java
 * 
 * Simulates fireworks through recursion.
 * 
 * @author Brandon Xu
 * @version 1.0
 * @since 2/10/2022
 */

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ArtShow
{
	private static ArrayList<Double> xList = new ArrayList<Double>();
	private static ArrayList<Double> yList = new ArrayList<Double>();
	private static ArrayList<Double> sizeList = new ArrayList<Double>();
	private static ArrayList<Integer> red = new ArrayList<Integer>();
	private static ArrayList<Integer> green = new ArrayList<Integer>();
	private static ArrayList<Integer> blue = new ArrayList<Integer>();
	private static ArrayList<Integer> burstTime = new ArrayList<Integer>();
	
	public static void main(String [] args)
	{
		setUpAndDrawBackground();
		fireworks();
	}
	
	/// Draws a black square, which is the background
	public static void setUpAndDrawBackground()
	{
		StdDraw.setCanvasSize(800,800);
		StdDraw.setPenColor(Color.BLACK);
		
		StdDraw.filledSquare(0.5, 0.5, 0.5);
		StdDraw.enableDoubleBuffering();
	}
	
	/// Creates the fireworks recursively and updates the firework's positions
	public static void fireworks()
	{
		double x = Math.random() * 0.9 + 0.05;
		double y = 0;
		double size = Math.random()*0.02 + 0.01;
		
		xList.add(x);
		yList.add(y);
		sizeList.add(size);
		red.add((int)(100 + Math.random() * 100));
		green.add((int)(100 + Math.random() * 100));
		blue.add((int)(100 + Math.random() * 100));
		burstTime.add(0);
		
		updateFireworks(50);
		
		fireworks();
	}
	
	/// draws the fireworks, which are colored circles
	public static void drawFireworks(double x, double y, double size, int red, int green, int blue)
	{
		StdDraw.setPenColor(red, green, blue);
		StdDraw.filledCircle(x, y, size);
	}
	
	/// updates the position of the fireworks, updates 10 times recursively
	public static void updateFireworks(int n)
	{
		if(n == 0)
			return;
			
		StdDraw.clear(Color.BLACK);
		for(int i=0; i<xList.size(); i++)
		{
			if(burstTime.get(i) == 0)
			{
				drawFireworks(xList.get(i), yList.get(i), sizeList.get(i), red.get(i), green.get(i), blue.get(i));
				yList.set(i, yList.get(i) + 0.01);
			}
			if(yList.get(i) >= Math.random()*1.4 + 0.6 || burstTime.get(i) != 0)
			{
				burst(i);
			}
		}
		
		StdDraw.show();
		
		StdDraw.pause(3);
		updateFireworks(n-1);
	}
	
	/// draws the smaller circles that appear when the firework bursts
	public static void burst(int i)
	{
		int t = burstTime.get(i);
		int y = 10;
		double x = 0;
		double v = 2;
		
		for(int j = 0; j<20; j++, y--, x+=v)
		{
			StdDraw.setPenColor(red.get(i), green.get(i), blue.get(i));
			StdDraw.filledCircle(xList.get(i) - 0.0005*x*t, -0.0001*t*t*0.2 + yList.get(i) + 0.0005*y*t, sizeList.get(i)/3);
			StdDraw.filledCircle(xList.get(i) - 0.0004*x*t, -0.0001*t*t*0.2 + yList.get(i) + 0.0004*y*t, sizeList.get(i)/3);
			StdDraw.filledCircle(xList.get(i) - 0.0003*x*t, -0.0001*t*t*0.2 + yList.get(i) + 0.0003*y*t, sizeList.get(i)/3);
			StdDraw.filledCircle(xList.get(i) - 0.0002*x*t, -0.0001*t*t*0.2 + yList.get(i) + 0.0002*y*t, sizeList.get(i)/3);
			StdDraw.filledCircle(xList.get(i) - 0.0001*x*t, -0.0001*t*t*0.2 + yList.get(i) + 0.0001*y*t, sizeList.get(i)/3);
			
			v-= 0.2;
		}
		
		y = 10;
		v = 2;
		x = 0;
		
		for(int j = 0; j<20; j++, y--, x+=v)
		{
			StdDraw.setPenColor(red.get(i), green.get(i), blue.get(i));
			StdDraw.filledCircle(xList.get(i) + 0.0005*x*t, -0.0001*t*t*0.2 + yList.get(i) + 0.0005*y*t, sizeList.get(i)/3);
			StdDraw.filledCircle(xList.get(i) + 0.0004*x*t, -0.0001*t*t*0.2 + yList.get(i) + 0.0004*y*t, sizeList.get(i)/3);
			StdDraw.filledCircle(xList.get(i) + 0.0003*x*t, -0.0001*t*t*0.2 + yList.get(i) + 0.0003*y*t, sizeList.get(i)/3);
			StdDraw.filledCircle(xList.get(i) + 0.0002*x*t, -0.0001*t*t*0.2 + yList.get(i) + 0.0002*y*t, sizeList.get(i)/3);
			StdDraw.filledCircle(xList.get(i) + 0.0001*x*t, -0.0001*t*t*0.2 + yList.get(i) + 0.0001*y*t, sizeList.get(i)/3);
			
			v-= 0.2;
		}
		
		burstTime.set(i, t + 1);
		
		if(sizeList.get(i) - 0.0002 <= 0)
			removeFirework(i);
		else
			sizeList.set(i, sizeList.get(i) - 0.0002);
	}
	
	/// removes the firework at specified index
	public static void removeFirework(int index)
	{
		xList.remove(index);
		yList.remove(index);
		sizeList.remove(index);
		red.remove(index);
		green.remove(index);
		blue.remove(index);
		burstTime.remove(index);
	}
}
