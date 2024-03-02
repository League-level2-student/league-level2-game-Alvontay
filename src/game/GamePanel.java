package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	Tile[][] boardState = new Tile[4][4];
	Random r = new Random();
	JFrame frame = new JFrame("2048");
	int[] empty = new int[2];
	boolean tileMoved;
	Timer timer = new Timer(1000/360, this);
	int score;
	JPanel header = new JPanel();
	JPanel listPanel = new JPanel();
	JLabel scoreLabel = new JLabel("<html><font color='#EEE' size = 40>Score: " + score  + "</font></html>");
	JPanel endScreen = new JPanel();
	JLabel textLabel = new JLabel();
	String gameState = "menu";
	
//	tests constructor
	public GamePanel(String test) {
		frame.add(this);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setVisible(true);
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(3);
		this.setBackground(Color.black);
		timer.start();
		
		int sadf = 0;
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {

				Tile tile = new Tile(i, j, ++sadf);
				tiles[i][j] = tile;

			}
		}
		
		isGameOver();

		
		frame.pack();
		repaint();
	}
	
	public GamePanel() {
		
        listPanel.add(this);
        header.add(scoreLabel);
        frame.add(header);
        
        this.setBackground(Color.black);
        listPanel.setBackground(Color.black);
        header.setBackground(Color.black);
        scoreLabel.setPreferredSize(new Dimension(WIDTH - 10, 40));
        header.setPreferredSize(new Dimension(WIDTH, 50));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        frame.add( BorderLayout.SOUTH, listPanel );
        this.add(textLabel);
        
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(3);
		
		frame.pack();
		
		frame.setVisible(true);
		
		scoreLabel.setText("");
		textLabel.setText("<html><font color='#EEE' size = 6><br><br><br>Press ENTER for a new game <br><br><br></font> "
							  + "<font color = '#CCC' size = 5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Try to combine tiles, If nothing <br>"
							  + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;can be combined, you lose!</font></html>");
	}

	private void newGame() {
		
		gameState = "game";
		
		score = 0;
		
		scoreLabel.setText("<html><font color='#EEE' size = 40>Score: " + score  + "</font></html>");
		textLabel.setText("");
		
		int num = 0;
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {

				Tile tile = new Tile(i, j, num);
				tiles[i][j] = tile;

			}
		}
		
		newTile();
		newTile();
		
		timer.start();
		
	}
	
	private void newTile() {
		if (anyEmpty()) {
			empty = findEmptyTile();
			tiles[empty[0]][empty[1]].setValue(generateStartingValue());
//			printBoard();
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
	
	private boolean anyNotEmpty() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if (tiles[i][j].getValue() > 0) {
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
					if (tiles[j][i] != null) {
						tiles[j][i].draw(g);
					}
					
				} catch(Exception e) {
					System.out.println(j + ", " + i);
					e.printStackTrace();
				}
			}
		}
		
		if (tileMoved) {
			newTile();
			if(isGameOver()) {
				showEndScreen();
			}
			tileMoved = false;
		}

	}

	private void showEndScreen() {
		
		gameState = "end";
		tileMoved = false;
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {

				}
				tiles[i][j].setValue(0);
				tiles[i][j].draw(getGraphics());
			}
		}
		
		scoreLabel.setText("<html><font color='#EEE' size = 40> &nbsp; &nbsp; &nbsp; &nbsp; GAME OVER </font></html>");
		
		textLabel.setText("<html><font color='#EEE' size = 40><br>Final score: " + score  + "</font>"
				+ "<font color='#EEE' size = 5><br>Press ENTER for a new game</font></html>");
		
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
				while (tiles[row][col].getValue() == 0 && (direction.equals("up")? row < 3 : row > 0)) {
					row -= vChange;
				}
//				
//				if (direction.equals("up")) {
//					while (tiles[row][col].getValue() == 0 && row < 3) {
//						row++;
//					}
//				} else {
//					while (tiles[row][col].getValue() == 0 && row > 0) {
//						row--;
//					}
//				}
				
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
	
	public void combineAndMoveHorizontal(String direction) {
		
		int startCol = 0, hChange = 0;
		
		switch (direction) {
		case "left": hChange = -1; startCol = 1;
			break;
		case "right": hChange = 1; startCol = 2;
			break;
		}
		
		// Shift everything (direction)
		moveHorizontal(direction);
//		System.out.println("First Shift");
		
		// For each collumn
		for (int row = 0; row < 4; row++) {
			
			int col = startCol;
			
			// try to combine first 2 tiles from (direction), if cant, try the two vertically adjacent
			if (combine(row, col, 0, hChange)) {
				
				// Find next tile to 
				while (tiles[row][col].getValue() == 0 && (direction.equals("left")? col < 3 : col > 0)) {
					col -= hChange;
				}
				
//				if (direction.equals("left")) {
//					while (tiles[row][col].getValue() == 0 && col < 3) {
//						col++;
//					}
//				} else {
//					while (tiles[row][col].getValue() == 0 && col > 0) {
//						col--;
//					}
//				}
				
			} else {
				col -= hChange;
				combine(row, col, 0, hChange);
			}
			
			// try to combine last pair of tiles
			col -= hChange;
			combine(row, col, 0, hChange);
			
		}
		moveHorizontal(direction);
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
					if (endRow != row) {
						tileMoved = true;
					}
					tiles[endRow][col].setValue(tiles[row][col].getValue());
					tiles[row][col].setValue(0);
				}
				
			}
			
		}
		
	}
	
	public void moveHorizontal(String direction) {
		
		int endCol = 0, col = 0, change = 0;
		
		for (int row = 0; row < 4; row++) {
			
			switch (direction) {
			case "left": endCol = 0; col = 1; change = -1;
				break;
			case "right": endCol = 3; col = 2; change = 1;
				break;
			}
			
			while (direction.equals("left")? col < 3: col > 0) {
				
				while(!(tiles[row][col].getValue() != 0 && tiles[row][col + change].getValue() == 0) && (direction.equals("left")? col < 3: col > 0)) {
					col -= change;
				}
				
				while ((tiles[row][endCol].getValue() != 0) && (direction.equals("right")? endCol != 0: endCol != 3)) {
					endCol -= change;
				}

				if ((direction.equals("right")? endCol != 0: endCol != 3) && (direction.equals("right")? endCol > col: endCol < col)) {
					if (endCol != col) {
						tileMoved = true;
					}
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
				score += (tiles[r][c].getValue()*2);
				tiles[r + dr][c + dc].setValue(tiles[r][c].getValue()*2);
				tiles[r][c].setValue(0);
				tileMoved = true;
				System.out.println("Score: " + score);
				scoreLabel.setText("<html><font color='#EEE' size = 40>Score: " + score  + "</font></html>");
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

		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP: if (gameState.equals("game")) {combineAndMoveVertical("up");}
				break;
			case KeyEvent.VK_DOWN: if (gameState.equals("game")) {combineAndMoveVertical("down");}
				break;
			case KeyEvent.VK_RIGHT: if (gameState.equals("game")) {combineAndMoveHorizontal("right");}
				break;
			case KeyEvent.VK_LEFT: if (gameState.equals("game")) {combineAndMoveHorizontal("left");}
				break;
			case KeyEvent.VK_ENTER: if(gameState.equals("menu") || gameState.equals("end")) { newGame(); }
				break;
			case KeyEvent.VK_R: if (gameState.equals("game")) {newGame();}
				break;		
			case KeyEvent.VK_E: showEndScreen();
				break;
		}

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

	public boolean isGameOver() {
		//if every tile is full and cant combine anything
		
		//go through each tile and check if they can combine with a tile to the right or down
		if (!anyEmpty()) {
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					int value = tiles[i][j].getValue();
					
					if (tiles[i+1][j].getValue() == value || tiles[i][j+1].getValue() == value) {
						return false;
					}
				}
			}
			System.out.println("GAME OVER");
			return true;
		}
		return false;
	}
	
}
