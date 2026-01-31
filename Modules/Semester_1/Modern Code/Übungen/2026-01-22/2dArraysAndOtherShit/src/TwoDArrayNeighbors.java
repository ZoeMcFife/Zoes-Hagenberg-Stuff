/**
 * UE12 - Section 4: Boundary Checking & Neighbor Operations
 * 
 * This class demonstrates:
 * - Boundary validation with isValid() helper method
 * - Checking 8 neighbors of a cell (Minesweeper pattern)
 * - Counting neighbors with specific values
 * - Handling edge cases (corners, edges, interior cells)
 */
public class TwoDArrayNeighbors {
    
    public static void main(String[] args) {
        IO.println("=== Boundary Checking & Neighbor Operations ===");
        IO.println();
        
        // Sample grid: 1 = wall, 0 = empty
        int[][] grid = {
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1}
        };
        
        IO.println("Test grid (1 = wall, 0 = empty):");
        printGrid(grid);
        IO.println();
        
        // ========================================
        // STEP 1: Test isValid() helper method
        // ========================================
        IO.println("--- Step 1: Testing isValid() Method ---");
        IO.println("Checking valid positions:");
        IO.println("  isValid(grid, 0, 0) = " + isValid(grid, 0, 0) + " (corner)");
        IO.println("  isValid(grid, 2, 2) = " + isValid(grid, 2, 2) + " (interior)");
        IO.println("  isValid(grid, 1, 1) = " + isValid(grid, 1, 1) + " (interior)");
        IO.println();
        IO.println("Checking invalid positions:");
        IO.println("  isValid(grid, -1, 0) = " + isValid(grid, -1, 0) + " (negative row)");
        IO.println("  isValid(grid, 0, -1) = " + isValid(grid, 0, -1) + " (negative column)");
        IO.println("  isValid(grid, 5, 0) = " + isValid(grid, 5, 0) + " (row out of bounds)");
        IO.println("  isValid(grid, 0, 5) = " + isValid(grid, 0, 5) + " (column out of bounds)");
        IO.println();
        
        // ========================================
        // STEP 2: Count neighbors (Minesweeper style)
        // ========================================
        IO.println("--- Step 2: Counting Neighbors ---");
        IO.println("Counting walls (value 1) around each empty cell:");
        IO.println();
        
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == 0) { // Only count for empty cells
                    int wallCount = countNeighbors(grid, r, c, 1);
                    IO.println("  Cell [" + r + "][" + c + "] has " + wallCount + " wall neighbors");
                }
            }
        }
        IO.println();
        
        // ========================================
        // STEP 3: Demonstrate different cell types
        // ========================================
        IO.println("--- Step 3: Different Cell Types ---");
        IO.println("Corner cell [0][0]: " + countNeighbors(grid, 0, 0, 1) + " neighbors (3 possible)");
        IO.println("Edge cell [0][2]: " + countNeighbors(grid, 0, 2, 1) + " neighbors (5 possible)");
        IO.println("Interior cell [2][2]: " + countNeighbors(grid, 2, 2, 1) + " neighbors (8 possible)");
        IO.println();
        
        // ========================================
        // STEP 4: Check all 8 directions
        // ========================================
        IO.println("--- Step 4: Checking All 8 Directions ---");
        IO.println("Neighbors of cell [2][2]:");
        checkAllNeighbors(grid, 2, 2);
    }
    
    /**
     * Checks if a position (r, c) is valid in the grid
     * @param grid The 2D array
     * @param r Row index
     * @param c Column index
     * @return true if valid position, false otherwise
     */
    static boolean isValid(int[][] grid, int r, int c) {
        // Check row bounds
        if (r < 0 || r >= grid.length) {
            return false;
        }
        // Check column bounds (use first row's length)
        if (c < 0 || c >= grid[0].length) {
            return false;
        }
        return true;
    }
    
    /**
     * Counts neighbors of a cell that have a specific value
     * Uses 8-direction checking (up, down, left, right, and 4 diagonals)
     * @param grid The 2D array
     * @param r Row index of the cell
     * @param c Column index of the cell
     * @param value The value to count in neighbors
     * @return Number of neighbors with the specified value
     */
    static int countNeighbors(int[][] grid, int r, int c, int value) {
        int count = 0;
        
        // Delta arrays for 8 directions: row and column offsets
        int[] dr = {-1, -1, -1,  0, 0,  1, 1, 1}; // row deltas
        int[] dc = {-1,  0,  1, -1, 1, -1, 0, 1}; // column deltas
        
        // Check all 8 directions
        for (int i = 0; i < 8; i++) {
            int newR = r + dr[i];
            int newC = c + dc[i];
            
            // Only check if valid position
            if (isValid(grid, newR, newC) && grid[newR][newC] == value) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * Checks all 8 neighbors of a cell and displays their values
     * @param grid The 2D array
     * @param r Row index
     * @param c Column index
     */
    static void checkAllNeighbors(int[][] grid, int r, int c) {
        // Direction names for display
        String[] directions = {
            "top-left", "top", "top-right",
            "left", "right",
            "bottom-left", "bottom", "bottom-right"
        };
        
        // Delta arrays for 8 directions
        int[] dr = {-1, -1, -1,  0, 0,  1, 1, 1};
        int[] dc = {-1,  0,  1, -1, 1, -1, 0, 1};
        
        for (int i = 0; i < 8; i++) {
            int newR = r + dr[i];
            int newC = c + dc[i];
            
            if (isValid(grid, newR, newC)) {
                IO.println("  " + directions[i] + " [" + newR + "][" + newC + "] = " + grid[newR][newC]);
            } else {
                IO.println("  " + directions[i] + " [out of bounds]");
            }
        }
    }
    
    /**
     * Helper method to print grid
     * @param grid The 2D array to print
     */
    static void printGrid(int[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                IO.print(grid[r][c] + " ");
            }
            IO.println();
        }
    }
}

