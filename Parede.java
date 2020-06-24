package edu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Parede {
	
	
	private static int x, y;
	private String id;
	public boolean construida, antigaParede;
	private Edu edu;
	private Player player;
	private static int width, height;
	private static int gordura = 15;
	
	public Parede(int x, int y,int width, int height,String id) {
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
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public String getID() {
		return this.id;
	}
	public static int getGordura() {
		return gordura;
	}
	
	public void paredeando() {
		if(player.getX() != x + 10) {
			x = player.getX() + 50;
		}
		if(player.getY() != y + 10) {
			y = player.getY() -25;
		}
	}
	
	public void tick() {
	
		
	}
	
	
	public void render(Graphics g) {
		if(construida) {
			g.setColor(Color.white);
			g.fillRect(x, y, width, height);
		}
	}
}
