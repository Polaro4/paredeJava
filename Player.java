package edu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
	
	private static int x, y , width, height;
	private String id;
	protected int speed = 2;
	
	public boolean up,down,left,right;
	private Parede parede;
	
	public Player(int x, int y, int width, int height, String id) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;	
	}
	
	public static int getX() {  
		return x;
	}
	
	public static int getY() {
		return y;
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static int getHeight() {
		return height;
	}
	public String getID() {
		return this.id;
	}
	
	
	
	public void tick() {
		
		if(up) {
			y-=speed;
			
		}
		else if (down) {
			y+=speed;
		}
		if(left) {
			x-=speed;
		}
		else if(right) {
			x+=speed;
		}
		
		if ((x+width == Parede.getX())&& (y > Parede.getY() - (height)) && 
			(y < Parede.getY() + (height + height))) {
			x-=speed;
			
		}else if ((x == Parede.getX() + width*2)&& (y > Parede.getY() - (height)) && 
			(y < Parede.getY() + (height + height))) {
			x+=speed;
			
		//colisão vertical
		}else if ((y+height > Parede.getY())&& (x > Parede.getX() - (width)) && 
			(x < Parede.getX() + (width + width))&& !(y > Parede.getY() + height)) {
			y-=speed;	
		
		}else if ((y < Parede.getY() + height*2)&& (x > Parede.getX() - (width)) && 
			(x < Parede.getX() + (width + width))&& !(y < Parede.getY() + height)) {
			y+=speed;
			
		}
			
	
	}
	
	public void render(Graphics g) {
			g.setColor(Color.green);
			g.fillRect(x, y, width, height);
		
	}
	
}
