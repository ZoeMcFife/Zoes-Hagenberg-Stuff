/**
 * UE12 - Section 5: Practical Application 1 - Game Board
 * 
 * This class demonstrates using 2D arrays for game board representation:
 * - Create 3x3 game board (Tic-Tac-Toe style)
 * - Place pieces on board (X, O, or empty)
 * - Check for win conditions (rows, columns, diagonals)
 * - Find winning moves and paths to solution
 * - Display board in readable format
 */
public class GameBoard {
    
    // Constants for board state
    static final int EMPTY = 0;
    static final int X = 1;
    static final int O = 2;
    
    public static void main(String[] args) {
        IO.println("=== Game Board - Tic-Tac-Toe Example ===");
        IO.println();
        
        // ========================================
        // STEP 1: Create and initialize game board
        // ========================================
        IO.println("--- Step 1: Creating Game Board ---");
        int[][] board = new int[3][3]; // 3x3 board, all empty initially
        IO.println("Created 3x3 game board (all empty)");
        printBoard(board);
        IO.println();
        
        // ========================================
        // STEP 2: Place pieces on board
        // ========================================
        IO.println("--- Step 2: Placing Pieces ---");
        board[0][0] = X; // Player X places at top-left
        board[0][1] = X; // Player X places at top-middle
        board[1][1] = O; // Player O places at center
        board[2][2] = X; // Player X places at bottom-right
        
        IO.println("After placing pieces:");
        printBoard(board);
        IO.println();
        
        // ========================================
        // STEP 3: Check for win conditions
        // ========================================
        IO.println("--- Step 3: Checking Win Conditions ---");
        
        // Test case 1: Row win
        IO.println("Test case 1: Row win");
        int[][] board1 = {
            {X, X, X},
            {O, O, EMPTY},
            {EMPTY, EMPTY, EMPTY}
        };
        printBoard(board1);
        IO.println("Has row win? " + hasRowWin(board1, X));
        IO.println();
        
        // Test case 2: Column win
        IO.println("Test case 2: Column win");
        int[][] board2 = {
            {X, O, EMPTY},
            {X, O, EMPTY},
            {X, EMPTY, EMPTY}
        };
        printBoard(board2);
        IO.println("Has column win? " + hasColumnWin(board2, X));
        IO.println();
        
        // Test case 3: Diagonal win (top-left to bottom-right)
        IO.println("Test case 3: Diagonal win (main diagonal)");
        int[][] board3 = {
            {X, O, O},
            {O, X, EMPTY},
            {EMPTY, EMPTY, X}
        };
        printBoard(board3);
        IO.println("Has main diagonal win? " + hasMainDiagonalWin(board3, X));
        IO.println();
        
        // Test case 4: Diagonal win (top-right to bottom-left)
        IO.println("Test case 4: Diagonal win (anti-diagonal)");
        int[][] board4 = {
            {O, O, X},
            {O, X, EMPTY},
            {X, EMPTY, EMPTY}
        };
        printBoard(board4);
        IO.println("Has anti-diagonal win? " + hasAntiDiagonalWin(board4, X));
        IO.println();
        
        // Test case 5: Complete win check
        IO.println("Test case 5: Complete win check");
        IO.println("Player X wins? " + hasWin(board3, X));
        IO.println("Player O wins? " + hasWin(board3, O));
        IO.println();
        
        // ========================================
        // STEP 4: Find winning moves (pathfinding)
        // ========================================
        IO.println("--- Step 4: Finding Winning Moves (Pathfinding) ---");
        
        // Test case: Player X has two X's in a row, needs one more
        int[][] board5 = {
            {X, X, EMPTY},
            {O, O, EMPTY},
            {EMPTY, EMPTY, EMPTY}
        };
        IO.println("Test board:");
        printBoard(board5);
        
        int[] winningMove = findWinningMove(board5, X);
        if (winningMove != null) {
            IO.println("Player X can win by playing at [" + winningMove[0] + "][" + winningMove[1] + "]");
        } else {
            IO.println("No winning move found for Player X");
        }
        IO.println();
        
        // Test case: Player O needs to block
        IO.println("Finding blocking move for Player O:");
        int[] blockingMove = findBlockingMove(board5, O);
        if (blockingMove != null) {
            IO.println("Player O should block at [" + blockingMove[0] + "][" + blockingMove[1] + "]");
        } else {
            IO.println("No blocking move needed");
        }
        IO.println();
        
        // ========================================
        // STEP 5: Find all possible winning paths
        // ========================================
        IO.println("--- Step 5: Finding All Winning Paths ---");
        int[][] board6 = {
            {X, EMPTY, EMPTY},
            {EMPTY, X, EMPTY},
            {EMPTY, EMPTY, EMPTY}
        };
        IO.println("Test board (X has two on main diagonal):");
        printBoard(board6);
        
        IO.println("All possible winning moves for X:");
        int[][] allWinningMoves = findAllWinningMoves(board6, X);
        if (allWinningMoves.length > 0) {
            for (int i = 0; i < allWinningMoves.length; i++) {
                IO.println("  Move " + (i + 1) + ": [" + allWinningMoves[i][0] + "][" + allWinningMoves[i][1] + "]");
            }
        } else {
            IO.println("  No winning moves available");
        }
        IO.println();
        
        // ========================================
        // STEP 6: Find best strategic move
        // ========================================
        IO.println("--- Step 6: Finding Best Strategic Move ---");
        int[][] board7 = {
            {X, O, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
        };
        IO.println("Test board:");
        printBoard(board7);
        
        int[] bestMove = findBestMove(board7, X);
        if (bestMove != null) {
            IO.println("Best move for X: [" + bestMove[0] + "][" + bestMove[1] + "]");
        }
        IO.println();
        
        // ========================================
        // STEP 7: MinMax Algorithm (Optimal Play)
        // ========================================
        IO.println("--- Step 7: MinMax Algorithm (Optimal Play) ---");
        int[][] board8 = {
            {X, O, X},
            {O, X, EMPTY},
            {EMPTY, EMPTY, EMPTY}
        };
        IO.println("Test board (O's turn):");
        printBoard(board8);
        
        int[] minMaxMove = findMinMaxMove(board8, O);
        if (minMaxMove != null) {
            IO.println("MinMax optimal move for O: [" + minMaxMove[0] + "][" + minMaxMove[1] + "]");
            IO.println("MinMax score: " + minMax(board8, 0, true, O));
        }
        IO.println();
        
        // Demonstrate MinMax evaluation
        IO.println("MinMax evaluation examples:");
        int[][] board9 = {
            {X, X, EMPTY},
            {O, O, EMPTY},
            {EMPTY, EMPTY, EMPTY}
        };
        IO.println("Board (X's turn, can win):");
        printBoard(board9);
        IO.println("MinMax score for X: " + minMax(board9, 0, true, X) + " (positive = X wins)");
        IO.println();
        
        int[][] board10 = {
            {O, O, EMPTY},
            {X, X, EMPTY},
            {EMPTY, EMPTY, EMPTY}
        };
        IO.println("Board (O's turn, can win):");
        printBoard(board10);
        IO.println("MinMax score for O: " + minMax(board10, 0, true, O) + " (negative = O wins)");
    }
    
    /**
     * Checks if a player has won in any row
     * @param board The game board
     * @param player The player to check (X or O)
     * @return true if player has a row win, false otherwise
     */
    static boolean hasRowWin(int[][] board, int player) {
        for (int r = 0; r < board.length; r++) {
            boolean rowWin = true;
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] != player) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if a player has won in any column
     * @param board The game board
     * @param player The player to check (X or O)
     * @return true if player has a column win, false otherwise
     */
    static boolean hasColumnWin(int[][] board, int player) {
        for (int c = 0; c < board[0].length; c++) {
            boolean colWin = true;
            for (int r = 0; r < board.length; r++) {
                if (board[r][c] != player) {
                    colWin = false;
                    break;
                }
            }
            if (colWin) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if a player has won in the main diagonal (top-left to bottom-right)
     * @param board The game board
     * @param player The player to check (X or O)
     * @return true if player has a main diagonal win, false otherwise
     */
    static boolean hasMainDiagonalWin(int[][] board, int player) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][i] != player) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Checks if a player has won in the anti-diagonal (top-right to bottom-left)
     * @param board The game board
     * @param player The player to check (X or O)
     * @return true if player has an anti-diagonal win, false otherwise
     */
    static boolean hasAntiDiagonalWin(int[][] board, int player) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            if (board[i][n - 1 - i] != player) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Checks if a player has won in any way (row, column, or diagonal)
     * @param board The game board
     * @param player The player to check (X or O)
     * @return true if player has any win, false otherwise
     */
    static boolean hasWin(int[][] board, int player) {
        return hasRowWin(board, player) ||
               hasColumnWin(board, player) ||
               hasMainDiagonalWin(board, player) ||
               hasAntiDiagonalWin(board, player);
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
    
    /**
     * Finds a winning move for a player (pathfinding algorithm)
     * Tries placing a piece at each empty position and checks if it results in a win
     * @param board The current game board
     * @param player The player to find a winning move for (X or O)
     * @return Array with [row, col] of winning move, or null if no winning move exists
     */
    static int[] findWinningMove(int[][] board, int player) {
        // Try each empty position
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == EMPTY) {
                    // Try placing piece here
                    board[r][c] = player;
                    // Check if this results in a win
                    if (hasWin(board, player)) {
                        // Restore board and return winning move
                        board[r][c] = EMPTY;
                        return new int[]{r, c};
                    }
                    // Restore board
                    board[r][c] = EMPTY;
                }
            }
        }
        return null; // No winning move found
    }
    
    /**
     * Finds a blocking move for a player (prevents opponent from winning)
     * @param board The current game board
     * @param player The player who needs to block (X or O)
     * @return Array with [row, col] of blocking move, or null if no blocking needed
     */
    static int[] findBlockingMove(int[][] board, int player) {
        // Determine opponent
        int opponent = (player == X) ? O : X;
        // Find opponent's winning move and block it
        return findWinningMove(board, opponent);
    }
    
    /**
     * Finds all possible winning moves for a player
     * @param board The current game board
     * @param player The player to find winning moves for (X or O)
     * @return 2D array where each row is [r, c] of a winning move
     */
    static int[][] findAllWinningMoves(int[][] board, int player) {
        int count = 0;
        // First pass: count how many winning moves exist
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == EMPTY) {
                    board[r][c] = player;
                    if (hasWin(board, player)) {
                        count++;
                    }
                    board[r][c] = EMPTY;
                }
            }
        }
        
        // Second pass: collect all winning moves
        int[][] winningMoves = new int[count][2];
        int index = 0;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == EMPTY) {
                    board[r][c] = player;
                    if (hasWin(board, player)) {
                        winningMoves[index][0] = r;
                        winningMoves[index][1] = c;
                        index++;
                    }
                    board[r][c] = EMPTY;
                }
            }
        }
        
        return winningMoves;
    }
    
    /**
     * Finds the best strategic move for a player
     * Strategy: 1) Win if possible, 2) Block opponent if needed, 3) Take center, 4) Take corner, 5) Take any available
     * @param board The current game board
     * @param player The player to find best move for (X or O)
     * @return Array with [row, col] of best move, or null if board is full
     */
    static int[] findBestMove(int[][] board, int player) {
        // Strategy 1: Try to win
        int[] winningMove = findWinningMove(board, player);
        if (winningMove != null) {
            return winningMove;
        }
        
        // Strategy 2: Block opponent from winning
        int[] blockingMove = findBlockingMove(board, player);
        if (blockingMove != null) {
            return blockingMove;
        }
        
        // Strategy 3: Take center if available
        int center = board.length / 2;
        if (board[center][center] == EMPTY) {
            return new int[]{center, center};
        }
        
        // Strategy 4: Take a corner if available
        int[][] corners = {
            {0, 0}, 
            {0, board[0].length - 1}, 
            {board.length - 1, 0}, 
            {board.length - 1, board[0].length - 1}
        };
        for (int i = 0; i < corners.length; i++) {
            int r = corners[i][0];
            int c = corners[i][1];
            if (board[r][c] == EMPTY) {
                return new int[]{r, c};
            }
        }
        
        // Strategy 5: Take any available position
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == EMPTY) {
                    return new int[]{r, c};
                }
            }
        }
        
        return null; // Board is full
    }
    
    /**
     * Evaluates the board state from a player's perspective
     * @param board The game board
     * @param player The player to evaluate for (X or O)
     * @return +10 if player wins, -10 if opponent wins, 0 if draw or ongoing
     */
    static int evaluateBoard(int[][] board, int player) {
        int opponent = (player == X) ? O : X;
        
        if (hasWin(board, player)) {
            return 10; // Player wins
        } else if (hasWin(board, opponent)) {
            return -10; // Opponent wins
        } else {
            return 0; // Draw or game ongoing
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
     * MinMax algorithm - finds the optimal move by exploring all possible game states
     * @param board The current game board
     * @param depth Current depth in the game tree
     * @param isMaximizing True if it's the maximizing player's turn, false otherwise
     * @param player The player we're finding the best move for (X or O)
     * @return Score: +10 for win, -10 for loss, 0 for draw
     */
    static int minMax(int[][] board, int depth, boolean isMaximizing, int player) {
        int opponent = (player == X) ? O : X;
        
        // Base cases: check for win, loss, or draw
        int score = evaluateBoard(board, player);
        if (score == 10) {
            return score - depth; // Prefer faster wins
        } else if (score == -10) {
            return score + depth; // Prefer slower losses
        } else if (isBoardFull(board)) {
            return 0; // Draw
        }
        
        if (isMaximizing) {
            // Maximizing player's turn - try to maximize score
            int bestScore = Integer.MIN_VALUE;
            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board[r].length; c++) {
                    if (board[r][c] == EMPTY) {
                        // Try this move
                        board[r][c] = player;
                        int moveScore = minMax(board, depth + 1, false, player);
                        board[r][c] = EMPTY; // Undo move
                        
                        bestScore = Math.max(bestScore, moveScore);
                    }
                }
            }
            return bestScore;
        } else {
            // Minimizing player's turn - try to minimize score
            int bestScore = Integer.MAX_VALUE;
            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board[r].length; c++) {
                    if (board[r][c] == EMPTY) {
                        // Try this move
                        board[r][c] = opponent;
                        int moveScore = minMax(board, depth + 1, true, player);
                        board[r][c] = EMPTY; // Undo move
                        
                        bestScore = Math.min(bestScore, moveScore);
                    }
                }
            }
            return bestScore;
        }
    }
    
    /**
     * Finds the best move using MinMax algorithm
     * @param board The current game board
     * @param player The player to find best move for (X or O)
     * @return Array with [row, col] of best move, or null if board is full
     */
    static int[] findMinMaxMove(int[][] board, int player) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = null;
        
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == EMPTY) {
                    // Try this move
                    board[r][c] = player;
                    int moveScore = minMax(board, 0, false, player);
                    board[r][c] = EMPTY; // Undo move
                    
                    if (moveScore > bestScore) {
                        bestScore = moveScore;
                        bestMove = new int[]{r, c};
                    }
                }
            }
        }
        
        return bestMove;
    }
}

