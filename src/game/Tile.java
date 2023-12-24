package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Tile {
	
	protected int row, col, value;
	public int x, y;
	
	public static BufferedImage[] images = new BufferedImage[5];
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	static {
		loadImages();
	}
	
	public Tile(int row, int col, int value) {
		y = row * 100;
		x = col * 100;
		this.value = value;
	}
	
	
	
	public void draw(Graphics g) {
	
		switch(value) {
			case 0: g.setColor(new Color(20,30,40));
					g.fillRect(x, y, 100, 100);
			break;
			case 2: g.drawImage(images[0], x, y, 100, 100, null);
			break;
			case 4: g.drawImage(images[1], x, y, 100, 100, null);
			break;
			case 8: g.drawImage(images[2], x, y, 100, 100, null);
			break;
			case 16: g.drawImage(images[3], x, y, 100, 100, null);
			break;
			case 32: g.drawImage(images[4], x, y, 100, 100, null);
			break;
	
		}	
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	static void loadImages() {
		
	    if (needImage) {
	        try {
	        	for (int i = 0, num = 2; i < images.length; i++) {
	        		images[i] = ImageIO.read(Tile.class.getResourceAsStream("/2048-" + num + ".png"));
	            	num *= 2;
	        	}

	            gotImage = true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        needImage = false;
	    }
	}
	
}
