package game;

public class ExtraMethods {

	

	
	
	
	
	
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
