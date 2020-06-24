package edu;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Edu extends Canvas implements Runnable, KeyListener{
	

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private boolean isRunning = true;
	private Thread thread;
	private final int WIDTH = 160;
	private final int HEIGHT = 120;
	private final int SCALE = 4;
	private BufferedImage image;
	
	public int edux = 3;
	
	private Player player;
	private Parede parede;
	
	public Edu() {
		this.addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
		player = new Player(10, 10, 50, 50, "Polaro" );
		parede = new Parede(1000, 1000, Parede.getGordura(), player.getHeight()*2, player.getID());
		
	}
	
	public void initFrame() {
		frame = new JFrame();
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	public synchronized void stop() {
		
	}
	
	public static void main(String[] agrs) {
		Edu game = new Edu();
		game.start();
		
	}
	
	public void tick() {
		player.tick();
		parede.tick();
	}
	public void render() {
		BufferStrategy bs  = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		player.render(g);
		parede.render(g);
		bs.show();
;	}
		
	@Override
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = (1000000000 / amountOfTicks);
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			if(System.currentTimeMillis() - timer >=1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_E) {
			parede.construida = true;
			parede.paredeando();
			
		}
		
		
		 if(e.getKeyCode() == KeyEvent.VK_RIGHT || 
			e.getKeyCode() == KeyEvent.VK_D) {
			 player.right = true;
		 }
		 else if(e.getKeyCode() == KeyEvent.VK_LEFT || 
			e.getKeyCode() == KeyEvent.VK_A) {
			 player.left = true;
		 }
		 
		 if(e.getKeyCode() == KeyEvent.VK_UP || 
			e.getKeyCode() == KeyEvent.VK_W) {
			 player.up = true;
		 }
		 
		 else if(e.getKeyCode() == KeyEvent.VK_DOWN || 
			e.getKeyCode() == KeyEvent.VK_S) {
			 player.down = true;
		 }

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || 
				e.getKeyCode() == KeyEvent.VK_D) {
				 player.right = false;
			 }
			 else if(e.getKeyCode() == KeyEvent.VK_LEFT || 
				e.getKeyCode() == KeyEvent.VK_A) {
				 player.left = false;
			 }
			 
			 if(e.getKeyCode() == KeyEvent.VK_UP || 
				e.getKeyCode() == KeyEvent.VK_W) {
				 player.up = false;
			 }
			 
			 else if(e.getKeyCode() == KeyEvent.VK_DOWN || 
				e.getKeyCode() == KeyEvent.VK_S) {
				 player.down = false;
			 }
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
	