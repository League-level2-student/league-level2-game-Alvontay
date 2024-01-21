package game;
import static org.junit.Assert.assertEquals;

import org.junit.*;

public class moveTest {

	static GamePanel testObj = new GamePanel("hi");

@Test
	public void test() {
	
		testObj.tiles[0][0].value = 0;
		testObj.tiles[1][0].value = 2;
		testObj.tiles[2][0].value = 2;
		testObj.tiles[3][0].value = 4;
	
		printBoard();
		System.out.println();
		testObj.combineDown();
		printBoard();
//		testObj.combineDown();
//		printBoard();

	}
// 0
// 2
// 2
// 0

//Going down - start at the bottom 
//	go up 1, try to combine (down) once
//		if combines
//			go up until it finds another piece staring at the first check
//			go up 1, try to combine (down) once
//		if doesnt combine 
//			go up 1
//			try to combine

	
	public boolean combine(String dir, int r) {
		return true;	
	}

	public static void printBoard() {	
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if (j == 3) {
					System.out.print(testObj.tiles[i][j].value + " \n");
				} else {
					System.out.print(testObj.tiles[i][j].value + " ");
				}
			}
		}
	}
}
