import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	// ArrayLists to store positions of the player and CPU
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

	public static void main(String[] args) {
		 // Initialize the game board as a 2D array of characters
		char [] [] gameBoard = {
				{' ', '|' ,' ','|',' '},
				{'-', '+' ,'-','+','-'},
				{' ', '|' ,' ','|',' '},
				{'-', '+' ,'-','+','-'},
				{' ', '|' ,' ','|',' '}
				};
		
		// Print the initial game board
		printGameBoard(gameBoard);
		
		// Game loop continues until a break condition is met
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your placement (1-9):");
			int playerpos = scan.nextInt();
			
			// Validate player's position input to ensure it's not taken
			while(playerPositions.contains(playerpos) || cpuPositions.contains(playerpos)) {
				System.out.println("position taken! enter a correct position");
				playerpos = scan.nextInt();
			}
			
			// Place the player's piece ('X') on the game board
			placePiece(gameBoard,playerpos,"player");
			
			// Check if there is a winner after the player's move
			String result =checkWinner();
			if (result.length()>0) {
				System.out.println(result);
				break;// Exit the loop if there is a winner
			}
			
			// CPU's turn: randomly generate a position and place its piece ('O')
			Random rand = new Random();
			int cpupos =rand.nextInt(9) + 1;
			while(playerPositions.contains(cpupos) || cpuPositions.contains(cpupos)) {
				cpupos = rand.nextInt(9)+1;// Ensure the position is not taken
			}
			
			
			placePiece(gameBoard,cpupos,"cpu");
			printGameBoard(gameBoard);// Print the updated game board after CPU's move
			
			// Check if there is a winner after the CPU's move
			result =checkWinner();
			if (result.length() > 0) {
				System.out.println(result);
				break;// Exit the loop if there is a winner
			}
		}
	}
	
	
	// Method to print the current state of the game board
		public static void printGameBoard(char[] [] gameBoard) {
			for(char [] row : gameBoard) {
				for(char c: row) {
					System.out.print(c);// Print each character in the row
				}	
				System.out.println();// Move to the next line after printing each row
			}
		}
		
		
		// Method to place a player's or CPU's piece on the game board
		public static void placePiece(char [] [] gameBoard ,int pos,String user){
			char symbol = ' ';
			
			// Determine the symbol ('X' for player, 'O' for CPU)
			if(user.equals("player")) {
				symbol ='X';
				playerPositions.add(pos);// Add the position to player's list
				
			} else if (user.equals("cpu")) {
				symbol = 'O';
				cpuPositions.add(pos);// Add the position to CPU's list
			}
			
			
			// Place the symbol on the game board based on the position
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
				break;
			}	
		}
		
		
		// Method to check if there is a winner
		public static String checkWinner() {
			// Define all possible winning combinations (rows, columns, diagonals)
			List topRow = Arrays.asList(1,2,3);
			List midRow = Arrays.asList(4,5,6);
			List botRow = Arrays.asList(7,8,9);
			List leftCol= Arrays.asList(1,4,7);
			List midCol = Arrays.asList(2,5,8);
			List rightCol = Arrays.asList(3,6,9);
			List cross1 = Arrays.asList(1,5,9);
			List cross2 = Arrays.asList(7,5,3);
			List<List> winning = new ArrayList<List>();
			winning.add(topRow);
			winning.add(midRow);
			winning.add(botRow);
			winning.add(leftCol);
			winning.add(midCol);
			winning.add(rightCol);
			winning.add(cross1);
			winning.add(cross2);

			
			// Iterate through each winning combination
			for(List l : winning){
				if(playerPositions.containsAll(l)) {
					return "Congratulations you Won!";// Player wins
				}else if (cpuPositions.containsAll(l)) {
					return "CPU Wins! Sorry :(";// CPU wins
			}else if(playerPositions.size() + cpuPositions.size() == 9 ){
				return "CAT!";// It's a tie (all positions filled)
			}
		}
			
			return "";// Return an empty string if no winner yet
			}
		}
