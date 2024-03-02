package game;

public class ExtraMethods {
	
//	public void combineAndMoveRight() {
//		moveRightBetter();
//		int row = 0;
//		while (row < 4) {
//			int col = 2;
//			if (combine(row, col, 0, 1)) {
//				while (tiles[row][col].getValue() == 0 && col > 0) {
//					col--;
//				}
//			} else {
//				col--;
//				combine(row, col, 0, 1);
//			}
//			
//			col--;
//			combine(row, col, 0, 1);
//			row++;
//		}
//		moveRightBetter();
//	}
//	
//	public void combineAndMoveLeft() { 
//		moveLeftBetter();
//		int row = 0;
//		while (row < 4) {
//			int col = 1;
//			if (combine(row, col, 0, -1)) {
//				while (tiles[row][col].getValue() == 0 && col < 3) {
//					col++;
//				}
//			} else {
//				col++;
//				combine(row, col, 0, -1);
//			}
//			
//			col++;
//			combine(row, col, 0, -1);
//			row++;
//		}
////		System.out.println("after combine");
//		moveLeftBetter();
//	}
	
//	private void moveLeftBetter() {
//		
//		for (int row = 0; row < 4; row++) {	
//			
//			//place to move tile to
//			int endCol = 0;
//			// row of tile
//			int col = 1;
//			
//			while (col < 3) {
//				// find a tile to move
//				while (!(tiles[row][col].getValue() != 0 && tiles[row][col - 1].getValue() == 0) && col < 3) {
//					col++;
//				}
//				
//				while (tiles[row][endCol].getValue() != 0 && endCol != 3) {
//					endCol++;
//				}	
//				
//				if (endCol != 3 && endCol < col) {
//					tiles[row][endCol].setValue(tiles[row][col].getValue());
//					tiles[row][col].setValue(0);
//				}
//			}
//		}
//		
//	}
	
//	private void moveRightBetter() {
//		
//		for (int row = 0; row < 4; row++) {
//			
//			//place to move tile to
//			int endCol = 3;
//			// row of tile
//			int col = 2;
//			
//			while (col > 0) {
//				// find a tile to move
//				while (!(tiles[row][col].getValue() != 0 && tiles[row][col + 1].getValue() == 0) && col > 0) {
//					col--;
//				}
//				
//				while (tiles[row][endCol].getValue() != 0 && endCol != 0) {
//					endCol--;
//				}	
//				
//				if (endCol != 0 && endCol > col) {
//					tiles[row][endCol].setValue(tiles[row][col].getValue());
//					tiles[row][col].setValue(0);
//				}
//			}
//		}
//		
//	}
	
//	private void move(int r, int c, String direction) {
//
//		int vChange = 0, h = 0;
//
//		switch (direction) {
//		case "up": vChange = -1;
//			break;
//		case "down": vChange = 1;
//			break;
//		case "left": h = -1;
//			break;
//		case "right": h = 1;
//		}
//
//		int moveRow = r;
//		int moveCol = c;
//		
//		try {
//			while (tiles[moveRow + vChange][moveCol + h].getValue() == 0) {
//				moveRow += vChange;
//				moveCol += h;
//			}
//
//			if (tiles[r][c].getValue() == tiles[moveRow + vChange][moveCol + h].getValue()) {
//				moveRow += vChange;
//				moveCol += h;
//			}
//		} catch (Exception e) {
////			e.printStackTrace();
//		}
//
//		if (!combine(r, c, moveRow - r, moveCol - c)) {
//
//			if (moveCol != c || moveRow != r) {
//				tiles[moveRow][moveCol].setValue(tiles[r][c].getValue());
//				tiles[r][c].setValue(0);
//				tileMoved = true;
//			}
//		}
//	}

//	public void combineAndMoveUp() {
//		moveUpBetter();
//		int col = 0;
//		while (col < 4) {
//			int row = 1;
//			if (combine(row, col, -1, 0)) {
//				while (tiles[row][col].getValue() == 0 && row < 3) {
//					row++;
//				}
//			} else {
//				row++;
//				combine(row, col, -1, 0);
//			}
//			
//			row++;
//			combine(row, col, -1, 0);
//			col++;
//		}
////		System.out.println("after combine");
//		moveUpBetter();
//	}
//
//	public void combineAndMoveDown() {
//		// shift everything down
//		moveDownBetter();
//		int col = 0;
//		while (col < 4) {
//			int row = 2;
//			
//			if (combine(row, col, 1, 0)) {
//				while (tiles[row][col].getValue() == 0 && row > 0) {
//					row--;
//				}
//			} else {
//				row--;
//				combine(row, col, 1, 0);
//			}
//			
//			row--;
//			combine(row, col, 1, 0);
//			col++;
//		}
////		System.out.println("after combine");
//		moveDownBetter();
//	}
//
//	private void moveUpBetter() {
//		
//		for (int col = 0; col < 4; col++) {	
//			
//			//place to move tile to
//			int endRow = 0;
//			// row of tile
//			int row = 1;
//			
//			while (row < 3) {
//				// find a tile to move
//				while (!(tiles[row][col].getValue() != 0 && tiles[row - 1][col].getValue() == 0) && row < 3) {
//					row++;
//				}
//				
//				while (tiles[endRow][col].getValue() != 0 && endRow != 3) {
//					endRow++;
//				}	
//				
//				if (endRow != 3 && endRow < row) {
//					tiles[endRow][col].setValue(tiles[row][col].getValue());
//					tiles[row][col].setValue(0);
//				}
//			}
//		}
//		
//	}
//	
//	public void moveDownBetter() {
//		
//		for (int col = 0; col < 4; col++) {
//			
//			//place to move tile to
//			int endRow = 3;
//			// row of tile
//			int row = 2;
//			
//			while (row > 0) {
//				// find a tile to move
//				while (!(tiles[row][col].getValue() != 0 && tiles[row + 1][col].getValue() == 0) && row > 0) {
//					row--;
//				}
//				
//				while (tiles[endRow][col].getValue() != 0 && endRow != 0) {
//					endRow--;
//				}	
//				
//				if (endRow != 0 && endRow > row) {
//					tiles[endRow][col].setValue(tiles[row][col].getValue());
//					tiles[row][col].setValue(0);
//				}
//				
//			}
//		}
//	}
//	
//	
//	private void moveVertical(int r, int c, int dir) {
//		
//		int moveRow = r;
//		
//		try {
//			while (tiles[moveRow + dir][c].getValue() == 0) {
//				moveRow += dir;
//			}
//			
//			if (tiles[r][c].getValue() == tiles[moveRow + dir][c].getValue()) {
//				moveRow += dir;
//			}
//		} catch (Exception e) {
////			e.printStackTrace();
//		}
//		
//		
//		
//		if (!combine(r, c, moveRow - r, 0)) {
//			
//			if (moveRow != r) {
//				tiles[moveRow][c].setValue(tiles[r][c].getValue());	
//				tiles[r][c].setValue(0);
//				tileMoved = true;
//			} 
//		} 
//	}
//	
//	private void moveHorizontal(int r, int c, int dir) {
//		int moveCol = c;
//		
//		try {
//			while (tiles[r][moveCol + dir].getValue() == 0) {
//				moveCol += dir;
//			}
//			
//			if (tiles[r][c].getValue() == tiles[r][moveCol + dir].getValue()) {
//				moveCol += dir;
//			}
//		} catch (Exception e) {
////			e.printStackTrace();
//		}
//		
//		
//		
//		if (!combine(r, c, 0, moveCol - c)) {
//			
//			if (moveCol != c) {
//				tiles[r][moveCol].setValue(tiles[r][c].getValue());	
//				tiles[r][c].setValue(0);
//				tileMoved = true;
//			} 
//		} 
//	}
//	
//	private void moveUp(int r, int c) {
//		
//		int moveRow = r;
//		
//		// extra condition  (|| tiles[row + 1][c].getValue() == tiles[r][c].getValue())
//		while (moveRow > 0 && tiles[moveRow - 1][c].getValue() == 0) {
//			moveRow--;
//		}
//
//		if (moveRow > 0 && tiles[r][c].getValue() == tiles[moveRow - 1][c].getValue()) {
//			moveRow--;
//		}
//		
//		if (!combine(r, c, moveRow - r, 0)) {
//			
//			if (moveRow != r) {
//				tiles[moveRow][c].setValue(tiles[r][c].getValue());	
//				tiles[r][c].setValue(0);
//				tileMoved = true;
//			} 
//		} 
//	}
//
//	private void moveDown(int r, int c) {
//		
//		int moveRow = r;
//		
//		// extra condition  (|| tiles[row + 1][c].getValue() == tiles[r][c].getValue())
//		while (moveRow < 3 && tiles[moveRow + 1][c].getValue() == 0) {
//			moveRow++;
//		}
//
//		if (moveRow < 3 && tiles[r][c].getValue() == tiles[moveRow + 1][c].getValue()) {
//			moveRow++;
//		}
//		
//		if (!combine(r, c, moveRow - r, 0)) {
//			
//			if (moveRow != r) {
//				tiles[moveRow][c].setValue(tiles[r][c].getValue());	
//				tiles[r][c].setValue(0);
//				tileMoved = true;
//			} 
//		} 
//		
//	}
//	
//	private void moveLeft(int r, int c) {
//		
//		int moveCol = c;
//		
//		// extra condition  (|| tiles[row + 1][c].getValue() == tiles[r][c].getValue())
//		while (moveCol > 0 && tiles[r][moveCol - 1].getValue() == 0) {
//			moveCol--;
//		}
//
//		if (moveCol > 0 && tiles[r][c].getValue() == tiles[r][moveCol - 1].getValue()) {
//			moveCol--;
//		}
//		
//		if (!combine(r, c, 0, moveCol - c)) {
//			
//			if (moveCol != c) {
//				tiles[r][moveCol].setValue(tiles[r][c].getValue());	
//				tiles[r][c].setValue(0);
//				tileMoved = true;
//			} 
//		}
//		
//	}
//	
//	private void moveRight(int r, int c) {
//		
//		int moveCol = c;
//		
//		// extra condition  (|| tiles[row + 1][c].getValue() == tiles[r][c].getValue())
//		while (moveCol < 3 && tiles[r][moveCol + 1].getValue() == 0) {
//			moveCol++;
//		}
//
//		if (moveCol < 3 && tiles[r][c].getValue() == tiles[r][moveCol + 1].getValue()) {
//			moveCol++;
//		}
//		
//		if (!combine(r, c, 0, moveCol - c)) {
//			
//			if (moveCol != c) {
//				tiles[r][moveCol].setValue(tiles[r][c].getValue());	
//				tiles[r][c].setValue(0);
//				tileMoved = true;
//			} 
//		} 
//		
//	}

	
	
}
