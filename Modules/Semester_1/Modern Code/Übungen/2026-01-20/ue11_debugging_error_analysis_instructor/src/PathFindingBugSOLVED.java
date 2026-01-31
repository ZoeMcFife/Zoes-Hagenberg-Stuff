public class PathFindingBugSOLVED {
    public static void main(String[] args) {
        IO.println("=== Path Finding Bug Exercise (SOLVED - Bug Location Marked) ===");
        IO.println();
        
        // Simple 2D grid: 0 = empty, 1 = wall, 2 = start, 3 = end
        // Using 1D array to represent 2D grid: grid[row * cols + col]
        int rows = 5;
        int cols = 5;
        int[] grid = {
            0, 0, 0, 0, 0,  // Row 0
            0, 1, 1, 0, 0,  // Row 1
            2, 0, 0, 1, 0,  // Row 2 (start at position 2,0)
            0, 0, 1, 0, 0,  // Row 3
            0, 0, 0, 0, 3   // Row 4 (end at position 4,4)
        };
        
        IO.println("Grid layout:");
        printGrid(grid, rows, cols);
        IO.println();
        
        // Find start and end positions
        int start = -1, end = -1;
        for (int i = 0; i < grid.length; i++) {
            if (grid[i] == 2) start = i;
            if (grid[i] == 3) end = i;
        }
        
        IO.println("Start position: " + (start / cols) + "," + (start % cols));
        IO.println("End position: " + (end / cols) + "," + (end % cols));
        IO.println();
        
        boolean pathExists = findPath(grid, rows, cols, start, end);
        
        IO.println();
        if (pathExists) {
            IO.println("✅ Path found!");
        } else {
            IO.println("❌ No path found!");
        }
        
        IO.println();
        IO.println("Expected: Path should be found");
    }
    
    // ========================================
    // BUG LOCATION: Lines 79 and 84
    // ========================================
    // BUG DESCRIPTION: Missing bounds check and missing wall check
    //
    // BUG 1 (Line 79): Missing lower bound check for newRow
    // - CURRENT (WRONG): if (newRow < rows && newCol >= 0 && newCol < cols)
    // - PROBLEM: Only checks if newRow < rows, but doesn't check if newRow >= 0.
    //   When moving up (dx = -1), newRow can become negative, causing an
    //   ArrayIndexOutOfBoundsException when calculating neighbor index.
    // - FIX: Change to: if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols)
    //
    // BUG 2 (Line 84): Missing wall check before processing neighbor
    // - CURRENT (WRONG): Checks visited before checking if it's a wall
    // - PROBLEM: The algorithm allows moving through walls (grid[neighbor] == 1).
    //   Walls should be skipped entirely - we shouldn't mark them as visited or
    //   add them to the queue. The wall check should happen BEFORE the visited check.
    // - FIX: Add wall check: if (grid[neighbor] == 1) continue;
    //        This should be placed right after calculating neighbor, before checking visited.
    // ========================================
    public static boolean findPath(int[] grid, int rows, int cols, int start, int end) {
        if (start == end) {
            return true;
        }
        
        // Queue for BFS: using circular array
        int[] queue = new int[rows * cols];
        int front = 0, rear = 0;
        queue[rear++] = start;
        
        // Visited array
        boolean[] visited = new boolean[rows * cols];
        visited[start] = true;
        
        // Directions: up, down, left, right
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        while (front < rear) {
            int current = queue[front++];
            int row = current / cols;
            int col = current % cols;
            
            // Check all neighbors
            for (int i = 0; i < 4; i++) {
                int newRow = row + dx[i];
                int newCol = col + dy[i];
                
                // BUG 1: Missing check for newRow >= 0
                if (newRow < rows && newCol >= 0 && newCol < cols) {
                    int neighbor = newRow * cols + newCol;
                    
                    // BUG 2: Missing wall check - should check if grid[neighbor] == 1 before visited check
                    if (!visited[neighbor]) {
                        if (neighbor == end) {
                            return true;
                        }
                        
                        visited[neighbor] = true;
                        queue[rear++] = neighbor;
                    }
                }
            }
        }
        
        return false;
    }
    
    public static void printGrid(int[] grid, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int val = grid[i * cols + j];
                char symbol = ' ';
                if (val == 1) symbol = '#';
                else if (val == 2) symbol = 'S';
                else if (val == 3) symbol = 'E';
                else symbol = '.';
                IO.print(symbol + " ");
            }
            IO.println();
        }
    }
}
