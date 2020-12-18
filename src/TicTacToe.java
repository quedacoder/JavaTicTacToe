import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<>();
	static ArrayList<Integer> cpuPositions = new ArrayList<>();

	public static void main(String[] args) {

		// Desgin the game board
		char gameBoard[][] = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };
		
		printGameBoard(gameBoard);
		
		boolean cont = true;
		
		while(cont) {
			Scanner scanner = new Scanner(System.in);
			System.out.println();
			System.out.print("Enter your placement (1-9): ");
			int playerPos = scanner.nextInt();
			
			placePiece(playerPos, gameBoard, "user");
			System.out.println();
			
			// Computer logic
			Random ran = new Random();
			int cpuPos = 0;
			
			boolean isInvalid = true;
			
			while(isInvalid) {
				cpuPos = ran.nextInt(9) + 1;
				isInvalid = checkValidInput(cpuPos);
			}
			
			placePiece(cpuPos, gameBoard, "computer");
			System.out.println();
			
		   String result = checkWinner();
		   
		   if (!result.isEmpty()) {
			   System.out.println(result);
			   cont = false;
		   }

		}
		
	}

	private static boolean checkValidInput(int cpuPos) {
		
		if (playerPositions.contains(cpuPos)) {
			return true;
		} 
		
		if (cpuPositions.contains(cpuPos)) {
			return true;
		}
		return false;
	}

	public static void printGameBoard(char[][] board) {
		// printout the game board
		for (char[] row : board) {

			for (char symbol : row) {
				System.out.print(symbol);
			}
			System.out.println();
		}
	}
	
	public static void placePiece(int pos, char [][] gameBoard, String user) {
		
		char symbol = 'X';
		
		if (user.equals("computer")) {
			cpuPositions.add(pos);
			symbol = 'O';
		} else {
			playerPositions.add(pos);
		}
		
		switch(pos) {
			case 1: 
			    gameBoard[0][0] = symbol;
				break;
			case 2: 
			    gameBoard[0][2] = symbol;
				break;
			case 3: 
			    gameBoard[0][4] = symbol;
				break;
			case 4: 
			    gameBoard[2][0] = symbol;
				break;
			case 5: 
			    gameBoard[2][2] = symbol;
				break;
			case 6: 
			    gameBoard[2][4] = symbol;
				break;
			case 7: 
			    gameBoard[4][0] = symbol;
				break;
			case 8: 
			    gameBoard[4][2] = symbol;
				break;
			case 9: 
			    gameBoard[4][4] = symbol;
				break;
			default:
				System.out.println("Invalid positions. Try again");
	
		}
	
		printGameBoard(gameBoard);
	}
	
	public static String checkWinner() {
		
		String winner = "";
		
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(3, 5, 7);

		List<List> winningConditions = new ArrayList<List>();
		winningConditions.add(topRow);
		winningConditions.add(midRow);
		winningConditions.add(botRow);
		winningConditions.add(leftCol);
		winningConditions.add(midCol);
		winningConditions.add(rightCol);
		winningConditions.add(cross1);
		winningConditions.add(cross2);
		
		for(List condition : winningConditions) {
			
			if (playerPositions.containsAll(condition)) {
				return "Congratulations!!! You Are the Winner!!!!";
			} else if (cpuPositions.containsAll(condition)) {
				return "Sorry CPU Wins :(";
			} else if (playerPositions.size() + cpuPositions.size() == 9) {
				return "CAT";
			}
			
		}
		return "";
	}

}
