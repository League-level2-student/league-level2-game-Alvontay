package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

	static final int WIDTH = 400;
	static final int HEIGHT = 400;
	static final int UP = 0;
	static final int DOWN = 1;
	static final int LEFT = 2;
	static final int RIGHT = 3;

	Tile[][] tiles = new Tile[4][4];
	Random r = new Random();
	JFrame frame = new JFrame("2048");
	int[] empty = new int[2];
	boolean tileMoved;
	Timer timer = new Timer(1000/60, this);
	
	public GamePanel() {

		frame.add(this);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
//		frame.setBackground(new Color(50,50,70));	
		frame.setVisible(true);
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(3);
		this.setBackground(Color.black);
		timer.start();
		
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {

				Tile tile = new Tile(i, j, 0);
				tiles[i][j] = tile;

			}
		}
		
		newTile();
		newTile();
		
		frame.pack();
		repaint();

	}

	private void newTile() {
		if (anyEmpty()) {
			empty = findEmptyTile();
			tiles[empty[0]][empty[1]].setValue(generateStartingValue());			
		}
	}
	
	private boolean anyEmpty() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if (tiles[i][j].getValue() == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	private int generateStartingValue() {
		return r.nextInt(4) == 0? 4: 2;
	}
	
	private int[] findEmptyTile() {
		
		int row, col;
		
		do {
			row = r.nextInt(4);
			col = r.nextInt(4);
		} while (tiles[row][col].getValue() != 0);
		
		int[] values = {row, col};
			
		return values;
	}
	
	@Override
	public void paintComponent(Graphics g) { 

		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				try {
					tiles[j][i].draw(g);
				}catch(Exception e) {
				System.out.println(j + ", " + i);
				}
			}
		}
		
		if (tileMoved) {
			newTile();	
			tileMoved = false;
		}

	}

	private void tryToMove(int direction) {

		int r, c;
		
		tileMoved = false;
		
		switch(direction) {
			case UP: 
				
				for(r = 1; r < 4; r++) {
					for(c = 0; c < 4; c++) {
						if (tiles[r][c].getValue() != 0) {
//							moveUp(r,c);
							moveVertical(r,c,(-1));
						}
					}	
				};
				
			break;
			case DOWN: 
				
				for(r = 2; r >= 0; r--) {
					for(c = 0; c < 4; c++) {
						if (tiles[r][c].getValue() != 0) {
//							moveDown(r,c);
							moveVertical(r,c,1);
						}
					}	
				};
				
			break;
			case LEFT: 
				
				for(c = 1; c < 4; c++) {
					for(r = 0; r < 4; r++) {
						if (tiles[r][c].getValue() != 0) {
//							moveLeft(r,c);
							moveHorizontal(r,c,-1);
						}
					}
				};
				
			break;
			case RIGHT: 
				
				for(c = 2; c >= 0; c--) {
					for(r = 0; r < 4; r++) {
						if (tiles[r][c].getValue() != 0) {
//							moveRight(r,c);
							moveHorizontal(r,c,1);
						}
					}
				};
				
			break;
		}

	}
	
	private void moveVertical(int r, int c, int dir) {
		
		int moveRow = r;
		
		try {
			while (tiles[moveRow + dir][c].getValue() == 0) {
				moveRow += dir;
			}
			
			if (tiles[r][c].getValue() == tiles[moveRow + dir][c].getValue()) {
				moveRow += dir;
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		
		
		if (!combine(r, c, moveRow - r, 0)) {
			
			if (moveRow != r) {
				tiles[moveRow][c].setValue(tiles[r][c].getValue());	
				tiles[r][c].setValue(0);
				tileMoved = true;
			} 
		} 
	}
	
	private void moveHorizontal(int r, int c, int dir) {
		int moveCol = c;
		
		try {
			while (tiles[r][moveCol + dir].getValue() == 0) {
				moveCol += dir;
			}
			
			if (tiles[r][c].getValue() == tiles[r][moveCol + dir].getValue()) {
				moveCol += dir;
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		
		
		if (!combine(r, c, 0, moveCol - c)) {
			
			if (moveCol != c) {
				tiles[r][moveCol].setValue(tiles[r][c].getValue());	
				tiles[r][c].setValue(0);
				tileMoved = true;
			} 
		} 
	}
	
	private void moveUp(int r, int c) {
		
		int moveRow = r;
		
		// extra condition  (|| tiles[row + 1][c].getValue() == tiles[r][c].getValue())
		while (moveRow > 0 && tiles[moveRow - 1][c].getValue() == 0) {
			moveRow--;
		}

		if (moveRow > 0 && tiles[r][c].getValue() == tiles[moveRow - 1][c].getValue()) {
			moveRow--;
		}
		
		if (!combine(r, c, moveRow - r, 0)) {
			
			if (moveRow != r) {
				tiles[moveRow][c].setValue(tiles[r][c].getValue());	
				tiles[r][c].setValue(0);
				tileMoved = true;
			} 
		} 
	}

	private void moveDown(int r, int c) {
		
		int moveRow = r;
		
		// extra condition  (|| tiles[row + 1][c].getValue() == tiles[r][c].getValue())
		while (moveRow < 3 && tiles[moveRow + 1][c].getValue() == 0) {
			moveRow++;
		}

		if (moveRow < 3 && tiles[r][c].getValue() == tiles[moveRow + 1][c].getValue()) {
			moveRow++;
		}
		
		if (!combine(r, c, moveRow - r, 0)) {
			
			if (moveRow != r) {
				tiles[moveRow][c].setValue(tiles[r][c].getValue());	
				tiles[r][c].setValue(0);
				tileMoved = true;
			} 
		} 
		
	}
	
	private void moveLeft(int r, int c) {
		
		int moveCol = c;
		
		// extra condition  (|| tiles[row + 1][c].getValue() == tiles[r][c].getValue())
		while (moveCol > 0 && tiles[r][moveCol - 1].getValue() == 0) {
			moveCol--;
		}

		if (moveCol > 0 && tiles[r][c].getValue() == tiles[r][moveCol - 1].getValue()) {
			moveCol--;
		}
		
		if (!combine(r, c, 0, moveCol - c)) {
			
			if (moveCol != c) {
				tiles[r][moveCol].setValue(tiles[r][c].getValue());	
				tiles[r][c].setValue(0);
				tileMoved = true;
			} 
		}
		
	}
	
	private void moveRight(int r, int c) {
		
		int moveCol = c;
		
		// extra condition  (|| tiles[row + 1][c].getValue() == tiles[r][c].getValue())
		while (moveCol < 3 && tiles[r][moveCol + 1].getValue() == 0) {
			moveCol++;
		}

		if (moveCol < 3 && tiles[r][c].getValue() == tiles[r][moveCol + 1].getValue()) {
			moveCol++;
		}
		
		if (!combine(r, c, 0, moveCol - c)) {
			
			if (moveCol != c) {
				tiles[r][moveCol].setValue(tiles[r][c].getValue());	
				tiles[r][c].setValue(0);
				tileMoved = true;
			} 
		} 
		
	}
	
	// down is dr = 1, up is dr = -1, right is dc = 1, left is dc = -1
	public boolean combine(int r, int c, int dr, int dc) {
		
		if (dr == 0 && dc == 0) {
			return false;
		}
		
		try{
			if (tiles[r][c].getValue() == tiles[r + dr][c + dc].getValue()) {
				
				tiles[r + dr][c + dc].setValue(tiles[r][c].getValue()*2);
				tiles[r][c].setValue(0);
				tileMoved = true;
				return true;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return false;

	}
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {
			tryToMove(UP);
		}

		if (key == KeyEvent.VK_DOWN) {
			tryToMove(DOWN);
		}

		if (key == KeyEvent.VK_LEFT) {
			tryToMove(LEFT);
		}

		if (key == KeyEvent.VK_RIGHT) {
			tryToMove(RIGHT);
		}
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

}
