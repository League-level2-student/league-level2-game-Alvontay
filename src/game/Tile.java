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
	
	
	
//	public void draw(Graphics g) {
//	
//		switch(value) {
//			case 2: g.drawImage(images[0], x, y, 100, 100, null);
//				break;
//			case 4: g.drawImage(images[1], x, y, 100, 100, null);
//				break;
//			case 8: g.drawImage(images[2], x, y, 100, 100, null);
//				break;
//			case 16: g.drawImage(images[3], x, y, 100, 100, null);
//				break;
//			case 32: g.drawImage(images[4], x, y, 100, 100, null);
//				break;
//			default: g.setColor(new Color(20,30,40));
//					 g.fillRect(x, y, 100, 100);
//				break;
//	
//		}	
//	}
	
	public void draw(Graphics g) {
		
		switch(value) {
			case 2: g.setColor(new Color(0xE5CCFF)); g.fillRect(x, y, 100, 100);
				break;
			case 4: g.setColor(new Color(0xCC99FF)); g.fillRect(x, y, 100, 100);
				break;
			case 8: g.setColor(new Color(0xB266FF)); g.fillRect(x, y, 100, 100);
				break;
			case 16: g.setColor(new Color(0x9933FF)); g.fillRect(x, y, 100, 100);
				break;
			case 32: g.setColor(new Color(0x7F00FF)); g.fillRect(x, y, 100, 100);
				break;
			case 64: g.setColor(new Color(0x6600CC)); g.fillRect(x, y, 100, 100);
				break;
			case 128: g.setColor(new Color(0x4C0099)); g.fillRect(x, y, 100, 100);
				break;
			case 256: g.setColor(new Color(0x330066)); g.fillRect(x, y, 100, 100);
				break;
			case 512: g.setColor(new Color(0x660066)); g.fillRect(x, y, 100, 100);
				break;
			case 1024: g.setColor(new Color(0x660033)); g.fillRect(x, y, 100, 100);
				break;
			case 2048: g.setColor(new Color(0x99004C)); g.fillRect(x, y, 100, 100);
				break;
			case 4096: g.setColor(new Color(0xCC0066)); g.fillRect(x, y, 100, 100);
				break;
			default: g.setColor(new Color(20,30,40));
					 g.fillRect(x, y, 100, 100);
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
