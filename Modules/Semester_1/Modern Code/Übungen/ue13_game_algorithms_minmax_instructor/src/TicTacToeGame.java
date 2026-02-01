/**
 * UE13 - Interactive Tic-Tac-Toe Game vs MinMax AI
 * 
 * This class provides an interactive game where you can play tic-tac-toe
 * against the MinMax algorithm. The computer will always play optimally!
 * 
 * Usage: Run this class and follow the prompts to make your moves.
 */
public class TicTacToeGame {
    
    // Constants for board state (matching GameBoard.java)
    static final int EMPTY = 0;
    static final int X = 1;  // Human player
    static final int O = 2;  // Computer (MinMax AI)
    
    public static void main(String[] args) {
        IO.println("=== Tic-Tac-Toe vs MinMax AI ===");
        IO.println();
        IO.println("You are X, the computer (MinMax AI) is O");
        IO.println("Enter moves as row and column (0-2), e.g., '0 1' for top-middle");
        IO.println();
        
        int[][] board = new int[3][3]; // Empty board
        boolean playerTurn = true; // true = human (X), false = computer (O)
        
        // Game loop
        while (true) {
            // Display current board
            printBoard(board);
            IO.println();
            
            // Check for game over
            if (GameBoard.hasWin(board, X)) {
                IO.println("🎉 Congratulations! You won!");
                break;
            } else if (GameBoard.hasWin(board, O)) {
                IO.println("🤖 The MinMax AI won! Better luck next time!");
                break;
            } else if (isBoardFull(board)) {
                IO.println("🤝 It's a draw! Good game!");
                break;
            }
            
            if (playerTurn) {
                // Human player's turn
                int[] move = getPlayerMove(board);
                if (move == null) {
                    IO.println("Invalid move. Please try again.");
                    continue;
                }
                board[move[0]][move[1]] = X;
                playerTurn = false;
            } else {
                // Computer's turn (MinMax)
                IO.println("🤖 Computer (MinMax AI) is thinking...");
                int[] bestMove = GameBoard.findMinMaxMove(board, O);
                if (bestMove != null) {
                    board[bestMove[0]][bestMove[1]] = O;
                    IO.println("Computer plays at [" + bestMove[0] + "][" + bestMove[1] + "]");
                } else {
                    IO.println("No moves available!");
                    break;
                }
                playerTurn = true;
            }
            IO.println();
        }
        
        // Final board state
        IO.println();
        IO.println("Final board:");
        printBoard(board);
    }
    
    /**
     * Gets a move from the human player via console input
     * @param board The current game board
     * @return Array with [row, col] of player's move, or null if invalid
     */
    static int[] getPlayerMove(int[][] board) {
        IO.print("Your move (row col, e.g., '0 1'): ");
        
        try {
            String input = IO.readln();
            if (input == null || input.trim().isEmpty()) {
                return null;
            }
            
            String[] parts = input.trim().split("\\s+");
            if (parts.length != 2) {
                return null;
            }
            
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            
            // Validate move
            if (row < 0 || row >= 3 || col < 0 || col >= 3) {
                IO.println("Invalid: Row and column must be between 0 and 2");
                return null;
            }
            
            if (board[row][col] != EMPTY) {
                IO.println("Invalid: That position is already taken!");
                return null;
            }
            
            return new int[]{row, col};
        } catch (NumberFormatException e) {
            IO.println("Invalid: Please enter two numbers separated by space");
            return null;
        }
    }
    
    /**
     * Checks if the board is full (no empty spaces)
     * @param board The game board
     * @return true if board is full, false otherwise
     */
    static boolean isBoardFull(int[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Prints the game board in readable format
     * @param board The game board to print
     */
    static void printBoard(int[][] board) {
        IO.println("  0 1 2");
        for (int r = 0; r < board.length; r++) {
            IO.print(r + " ");
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == X) {
                    IO.print("X ");
                } else if (board[r][c] == O) {
                    IO.print("O ");
                } else {
                    IO.print(". ");
                }
            }
            IO.println();
        }
    }
}

