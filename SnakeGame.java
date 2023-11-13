package com.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.JPanel;

public class SnakeGame extends JPanel implements ActionListener, KeyListener{
	
	static final int SCREEN_WIDTHX = 600;
	static final int SCREEN_HEIGHTY = 600;
	static final int SIZE = 20;
	static final int DEALY = 200;
	final int[] x = new int[SCREEN_HEIGHTY* SCREEN_WIDTHX];
	final int[] y= new int[SCREEN_HEIGHTY* SCREEN_WIDTHX];
	int score;
	int bodyPart = 2;
	int FoodEatten;
	int FoodX;
	int FoodY;
	int currentDirectionX =1;
	int currentDirectionY =0;
	boolean running = false;
	Random random;
	Timer timer;
	public SnakeGame() {
		random= new Random();
		this.setPreferredSize (new Dimension (SCREEN_HEIGHTY, SCREEN_WIDTHX));
		this.setBackground (Color.black);
		this.setFocusable(true);
		this.addKeyListener(this);
		startGame();
		timer.start();
		
		
	}

	private void startGame() {
        createFood();
		running = true;
		timer = new Timer(DEALY,this);
		
	}
	public void  paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
      if(running) {
		
		g.setColor(Color.yellow);
		g.fillOval(FoodX, FoodY, SIZE, SIZE);
		for(int i=0;i<bodyPart;i++) {
			g.setColor(Color.MAGENTA);
			g.fillRect(x[i], y[i], SIZE, SIZE);
		}
      }
      else {
    	  gameOver(g);
      }
	}
	public void checkfood() {
		if(x[0]==FoodX && y[0] == FoodY) {
			createFood();
			score++;
			bodyPart++;
		}
	}
	public void move() {
		for (int i=bodyPart;i>0;i--) {
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		x[0] =x[0] + currentDirectionX*SIZE;
		y[0]=y[0]+currentDirectionY*SIZE;
	}
	public void createFood() {
		FoodX = random.nextInt(((int)SCREEN_WIDTHX/SIZE))*SIZE;
		FoodY = random.nextInt(((int)SCREEN_HEIGHTY/SIZE))*SIZE;
		
	}
	public void checkColloide() {
		for(int i=bodyPart;i>0;i--) {
		if(x[0]== x[i] && y[0] == y[i]) {
			running = false;
		}
		if(x[0]<0) {
			running=false;
		}
		if(x[0]>=SCREEN_WIDTHX) {
			running = false;
		}
		if(y[0]<0) {
			running=false;
		}
		if(y[0]>=SCREEN_HEIGHTY) {
			running=false;
		}
		if(!running) {
			timer.stop();
			
		}
		}
	}
	public void gameOver(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("serif",Font.BOLD,75));
		FontMetrics met = getFontMetrics(g.getFont());
		g.drawString("SCORE " + score, SCREEN_HEIGHTY/5, SCREEN_WIDTHX/3);
		g.drawString("GAME OVER " , SCREEN_HEIGHTY/9, SCREEN_WIDTHX/2);

	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_LEFT:
			if(currentDirectionX !=-1) {
			currentDirectionX=-1;
			currentDirectionY=0;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(currentDirectionX!=-1) {
			currentDirectionX=1;
			currentDirectionY=0;
			}
			break;
		case KeyEvent.VK_UP:
			if(currentDirectionY != 1) {
			currentDirectionX=0;
			currentDirectionY=-1;
			}
			break;
		case KeyEvent.VK_DOWN:
			if(currentDirectionY != -1) {
			currentDirectionX=0;
			currentDirectionY=1;
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(running) {
			move();
			checkfood();
			checkColloide();
		}
		repaint();
	}
	

}
