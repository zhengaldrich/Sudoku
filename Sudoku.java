import java.util.*;
public class Sudoku {
    public static Scanner sc = new Scanner(System.in);
    public static int[][] board = new int[9][9];
    public static void main(String[] args) {
        System.out.println("Hallo!");
        fillBoard(0, 0);
        removeRandom();
        printBoard();
        while (!win()) {
            System.out.println("Enter 1 to continue, enter 2 to quit");
            int userChoice = sc.nextInt();
            if (userChoice == 1) {
                System.out.println("____________________");
                System.out.println("Enter a valid row index: ");
                int userRow = sc.nextInt();
                System.out.println("Enter a valid column index: ");
                int userCol = sc.nextInt();
                System.out.println("Enter your guess: ");
                int guess = sc.nextInt();
                if ((userRow < 9 && userRow >= 0) &&
                    (userCol < 9 && userCol >= 0) &&
                    (board[userRow][userCol] == 0 && isValid(userRow, userCol, guess))) {
                        board[userRow][userCol] = guess;
                        printBoard();
                    }
                else {
                    System.out.println("Try again!");
                    printBoard();
                } 
            }
            if (userChoice == 2) {
                System.out.println("You quit!");
                break;
            }
            else System.out.println("That's not a valid input!");
        }
        if (win()) System.out.println("You win!!"); 
        else System.out.println("Bai!");
    }
    
    public static boolean win() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = board[i][j];
                if (num == 0) return false;
                board[i][j] = 0;
                if (!isValid(i, j, num)) {
                    board[i][j] = num;
                    return false;
                }
                board[i][j] = num;
            }
        }
        return true;
    }
    
    public static void removeRandom() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (Math.random() > 0.75) board[i][j] = 0;
            }
        }
    }
    
    public static void printBoard() {
        System.out.println("____________________");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print(" ");
                }
                
                if (board[i][j] == 0) {
                    System.out.print("- ");
                    continue; 
                } 
                
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
            if ((i + 1) % 3 == 0 && i != 8) {
                System.out.println();
            }
        }
    }
        
    public static boolean fillBoard(int row, int col) {
        if (row == 9) return true;
        int nextRow;
        int nextCol;
        if (col == 8) {
            nextRow = row + 1;
            nextCol = 0;
        } 
        else {
            nextRow = row;
            nextCol = col + 1;
        }
        int[] nums = {1,2,3,4,5,6,7,8,9};
        for (int i = nums.length - 1; i > 0; i--) {
            int j = (int)(Math.random() * (i + 1));
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (isValid(row, col, num)) {
                board[row][col] = num;
                
                if (fillBoard(nextRow, nextCol)) return true;
                board[row][col] = 0;
            }
        }
        return false;
    }
    
    public static boolean isValid(int row, int col, int num) {
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == num) return false;
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == num) return false;
        }
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
    
        for (int i = boxRowStart; i < boxRowStart + 3; i++) {
            for (int j = boxColStart; j < boxColStart + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }
        return true;
    }
}
