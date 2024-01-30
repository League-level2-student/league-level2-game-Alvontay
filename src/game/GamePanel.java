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
			case UP: combineAndMoveUp();
				break;
			case DOWN: combineAndMoveDown();
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
	
	private void move(int r, int c, String direction) {

		int vChange = 0, h = 0;

		switch (direction) {
		case "up": vChange = -1;
			break;
		case "down": vChange = 1;
			break;
		case "left": h = -1;
			break;
		case "right": h = 1;
		}

		int moveRow = r;
		int moveCol = c;
		
		try {
			while (tiles[moveRow + vChange][moveCol + h].getValue() == 0) {
				moveRow += vChange;
				moveCol += h;
			}

			if (tiles[r][c].getValue() == tiles[moveRow + vChange][moveCol + h].getValue()) {
				moveRow += vChange;
				moveCol += h;
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}

		if (!combine(r, c, moveRow - r, moveCol - c)) {

			if (moveCol != c || moveRow != r) {
				tiles[moveRow][moveCol].setValue(tiles[r][c].getValue());
				tiles[r][c].setValue(0);
				tileMoved = true;
			}
		}
	}
	
	public void combineAndMoveVertical(String direction) {
		
		int startRow = 0, vChange = 0;
		
		switch (direction) {
		case "up": vChange = -1; startRow = 3;
			break;
		case "down": vChange = 1; startRow = 2;
			break;
		}
		
		moveVerticalBetter(direction);
		
		for (int col = 0; col < 4; col++) {
			
			int row = startRow;
			
			if (combine(row, col, vChange, 0)) {
				while (tiles[row][col].getValue() == 0 && row < 3) {
					row++;
				}
			} else {
				row++;
				combine(row, col, vChange, 0);
			}
			
			row++;
			combine(row, col, vChange, 0);
			
		}
		
		moveVerticalBetter(direction);
	}
	
	public void combineAndMoveUp() {
		moveUpBetter();
		int col = 0;
		while (col < 4) {
			int row = 1;
			if (combine(row, col, -1, 0)) {
				while (tiles[row][col].getValue() == 0 && row < 3) {
					row++;
				}
			} else {
				row++;
				combine(row, col, -1, 0);
			}
			
			row++;
			combine(row, col, -1, 0);
			col++;
		}
//		System.out.println("after combine");
		moveUpBetter();
	}

	public void combineAndMoveDown() {
		moveDownBetter();
		int col = 0;
		while (col < 4) {
			int row = 2;
			if (combine(row, col, 1, 0)) {
				while (tiles[row][col].getValue() == 0 && row > 0) {
					row--;
				}
			} else {
				row--;
				combine(row, col, 1, 0);
			}
			
			row--;
			combine(row, col, 1, 0);
			col++;
		}
//		System.out.println("after combine");
		moveDownBetter();
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
	
	public void moveVerticalBetter(String direction) {
		
		int endRow = 0, row = 0, change = 0;
		
		for (int col = 0; col < 4; col++) {
			
			switch (direction) {
			case "up": endRow = 0; row = 1; change = -1;
				break;
			case "down": endRow = 3; row = 2; change = 1;
				break;
			}
			
			while(!(tiles[row][col].getValue() != 0 && tiles[row + change][col].getValue() == 0) && row ) {
				
			}
			
		}
		
	}
	
	public void moveDownBetter() {
				
		for (int col = 0; col < 4; col++) {
			
			//place to move tile to
			int endRow = 3;
			// row of tile
			int row = 2;
			
			while (row > 0) {
				// find a tile to move
				while (!(tiles[row][col].getValue() != 0 && tiles[row + 1][col].getValue() == 0) && row > 0) {
					row--;
				}
				
				while (tiles[endRow][col].getValue() != 0 && endRow != 0) {
					endRow--;
				}	
				
				if (endRow != 0 && endRow > row) {
					tiles[endRow][col].setValue(tiles[row][col].getValue());
					tiles[row][col].setValue(0);
				}
			}
		}
	}
	
	private void moveUpBetter() {
		
		for (int col = 0; col < 4; col++) {	
			
			//place to move tile to
			int endRow = 0;
			// row of tile
			int row = 1;
			
			while (row < 3) {
				// find a tile to move
				while (!(tiles[row][col].getValue() != 0 && tiles[row - 1][col].getValue() == 0) && row < 3) {
					row++;
				}
				
				while (tiles[endRow][col].getValue() != 0 && endRow != 3) {
					endRow++;
				}	
				
				if (endRow != 3 && endRow < row) {
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
