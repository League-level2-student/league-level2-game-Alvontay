package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	Tile[][] gameState = new Tile[4][4];
	Random r = new Random();
	JFrame frame = new JFrame("2048");
	int[] empty = new int[2];
	boolean tileMoved;
	Timer timer = new Timer(1000/60, this);
	
//	tests constructor
	public GamePanel(String hi) {
		frame.add(this);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
		
		frame.pack();
		repaint();
	}
	
	public GamePanel() {

		frame.add(this);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
			printBoard();
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
		
		g.setFont(new Font("Arial",0 , 300));
		g.drawString(":)", 500, 500);
		

	}

	public void tryToMove(int direction) {
		
		tileMoved = false;
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				gameState[i][j] = new Tile(i, j, tiles[i][j].getValue());
			}
		}
		
		
		
		switch(direction) {
			case UP: combineAndMoveVertical("up");
				break;
			case DOWN: combineAndMoveVertical("down");
				break;
			case LEFT: 	combineAndMoveLeft();
				break;
			case RIGHT: combineAndMoveRight();
				break;
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (gameState[i][j].getValue() != tiles[i][j].getValue()) {
					tileMoved = true;
				}
			}
		}
		
	}
	
	public void combineAndMoveVertical(String direction) {
		
		int startRow = 0, vChange = 0;
		
		switch (direction) {
		case "up": vChange = -1; startRow = 1;
			break;
		case "down": vChange = 1; startRow = 2;
			break;
		}
		
		// Shift everything (direction)
		moveVertical(direction);
//		System.out.println("First Shift");
		
		// For each collumn
		for (int col = 0; col < 4; col++) {
			
			int row = startRow;
			
			// try to combine first 2 tiles from (direction), if cant, try the two vertically adjacent
			if (combine(row, col, vChange, 0)) {
				
				// Find next tile to 
				if (direction.equals("up")) {
					while (tiles[row][col].getValue() == 0 && row < 3) {
						row++;
					}
				} else {
					while (tiles[row][col].getValue() == 0 && row > 0) {
						row--;
					}
				}
				
			} else {
				row -= vChange;
				combine(row, col, vChange, 0);
			}
			
			// try to combine last pair of tiles
			row -= vChange;
			combine(row, col, vChange, 0);
			
		}
		
		// Shift everything (direction)
		moveVertical(direction);
//		System.out.println("Second Shift");
	}
	
	public void combineAndMoveRight() {
		moveRightBetter();
		int row = 0;
		while (row < 4) {
			int col = 2;
			if (combine(row, col, 0, 1)) {
				while (tiles[row][col].getValue() == 0 && col > 0) {
					col--;
				}
			} else {
				col--;
				combine(row, col, 0, 1);
			}
			
			col--;
			combine(row, col, 0, 1);
			row++;
		}
		moveRightBetter();
	}
	
	public void combineAndMoveLeft() { 
		moveLeftBetter();
		int row = 0;
		while (row < 4) {
			int col = 1;
			if (combine(row, col, 0, -1)) {
				while (tiles[row][col].getValue() == 0 && col < 3) {
					col++;
				}
			} else {
				col++;
				combine(row, col, 0, -1);
			}
			
			col++;
			combine(row, col, 0, -1);
			row++;
		}
//		System.out.println("after combine");
		moveLeftBetter();
	}
	
	public void moveVertical(String direction) {
		
		int endRow = 0, row = 0, change = 0;
		
		for (int col = 0; col < 4; col++) {
			
			switch (direction) {
			case "up": endRow = 0; row = 1; change = -1;
				break;
			case "down": endRow = 3; row = 2; change = 1;
				break;
			}
			
			while (direction.equals("up")? row < 3: row > 0) {
				
				while(!(tiles[row][col].getValue() != 0 && tiles[row + change][col].getValue() == 0) && (direction.equals("up")? row < 3: row > 0)) {
					row -= change;
				}
				
				while ((tiles[endRow][col].getValue() != 0) && (direction.equals("down")? endRow != 0: endRow != 3)) {
					endRow -= change;
				}

				if ((direction.equals("down")? endRow != 0: endRow != 3) && (direction.equals("down")? endRow > row: endRow < row)) {
					tiles[endRow][col].setValue(tiles[row][col].getValue());
					tiles[row][col].setValue(0);
				}
				
			}
			
		}
		
	}

	private void moveRightBetter() {
		
		for (int row = 0; row < 4; row++) {
			
			//place to move tile to
			int endCol = 3;
			// row of tile
			int col = 2;
			
			while (col > 0) {
				// find a tile to move
				while (!(tiles[row][col].getValue() != 0 && tiles[row][col + 1].getValue() == 0) && col > 0) {
					col--;
				}
				
				while (tiles[row][endCol].getValue() != 0 && endCol != 0) {
					endCol--;
				}	
				
				if (endCol != 0 && endCol > col) {
					tiles[row][endCol].setValue(tiles[row][col].getValue());
					tiles[row][col].setValue(0);
				}
			}
		}
		
	}
	
	private void moveLeftBetter() {
		
		for (int row = 0; row < 4; row++) {	
			
			//place to move tile to
			int endCol = 0;
			// row of tile
			int col = 1;
			
			while (col < 3) {
				// find a tile to move
				while (!(tiles[row][col].getValue() != 0 && tiles[row][col - 1].getValue() == 0) && col < 3) {
					col++;
				}
				
				while (tiles[row][endCol].getValue() != 0 && endCol != 3) {
					endCol++;
				}	
				
				if (endCol != 3 && endCol < col) {
					tiles[row][endCol].setValue(tiles[row][col].getValue());
					tiles[row][col].setValue(0);
				}
			}
		}
		
	}
	
	// down is dr = 1, up is dr = -1, right is dc = 1, left is dc = -1
	public boolean combine(int r, int c, int dr, int dc) {
		
		

		try {
//			System.out.println("Combined: " + (tiles[r][c].getValue() == tiles[r + dr][c + dc].getValue() && tiles[r][c].getValue() != 0));
			
			if (tiles[r][c].getValue() == tiles[r + dr][c + dc].getValue() && tiles[r][c].getValue() != 0) {
				
				tiles[r + dr][c + dc].setValue(tiles[r][c].getValue()*2);
				tiles[r][c].setValue(0);
				tileMoved = true;
				return true;
			}	
		} catch(Exception e) {
//			e.printStackTrace();
		}
		
		return false;

	}
	
	public void printBoard() {	
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if (j == 3) {
					System.out.print(tiles[i][j].value + " \n");
				} else {
					System.out.print(tiles[i][j].value + " ");
				}
			}
		}
		System.out.println("\n");
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
