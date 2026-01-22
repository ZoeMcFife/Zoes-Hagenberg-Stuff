/**
 * UE12 - Section 1: Basic 2D Array Operations
 * 
 * This class demonstrates basic 2D array operations:
 * - Creating and initializing 2D arrays (rectangular and jagged)
 * - Accessing elements using row/column indices
 * - Understanding array.length vs array[row].length
 * - Printing 2D arrays in readable format
 */
public class TwoDArrayBasics {
    
    public static void main(String[] args) {
        IO.println("=== Basic 2D Array Operations ===");
        IO.println();
        
        // ========================================
        // STEP 1: Create and initialize rectangular 2D array
        // ========================================
        IO.println("--- Step 1: Creating Rectangular 2D Array ---");
        int[][] matrix = new int[3][4]; // 3 rows, 4 columns
        IO.println("Created matrix[3][4] - 3 rows, 4 columns");
        IO.println("Number of rows: " + matrix.length);
        IO.println("Number of columns in row 0: " + matrix[0].length);
        IO.println();
        
        // ========================================
        // STEP 2: Initialize with values
        // ========================================
        IO.println("--- Step 2: Initializing with Values ---");
        int[][] grid = {
            {1, 2, 3},    // Row 0
            {4, 5, 6},    // Row 1
            {7, 8, 9}     // Row 2
        };
        IO.println("Initialized grid with values:");
        print2DArray(grid);
        IO.println();
        
        // ========================================
        // STEP 3: Access elements using row/column indices
        // ========================================
        IO.println("--- Step 3: Accessing Elements ---");
        IO.println("grid[0][0] = " + grid[0][0] + " (top-left)");
        IO.println("grid[1][2] = " + grid[1][2] + " (row 1, column 2)");
        IO.println("grid[2][1] = " + grid[2][1] + " (row 2, column 1)");
        IO.println();
        
        // ========================================
        // STEP 4: Modify elements
        // ========================================
        IO.println("--- Step 4: Modifying Elements ---");
        grid[1][1] = 99;
        IO.println("After setting grid[1][1] = 99:");
        print2DArray(grid);
        IO.println();
        
        // ========================================
        // STEP 5: Create jagged array (rows of different lengths)
        // ========================================
        IO.println("--- Step 5: Creating Jagged Array ---");
        int[][] jagged = new int[3][];
        jagged[0] = new int[1];  // Row 0: 1 column
        jagged[1] = new int[2];  // Row 1: 2 columns
        jagged[2] = new int[3];  // Row 2: 3 columns
        
        // Initialize values
        jagged[0][0] = 1;
        jagged[1][0] = 2;
        jagged[1][1] = 3;
        jagged[2][0] = 4;
        jagged[2][1] = 5;
        jagged[2][2] = 6;
        
        IO.println("Jagged array (Pascal's triangle style):");
        printJaggedArray(jagged);
        IO.println();
        
        // ========================================
        // STEP 6: Understanding length properties
        // ========================================
        IO.println("--- Step 6: Understanding Length Properties ---");
        IO.println("jagged.length = " + jagged.length + " (number of rows)");
        IO.println("jagged[0].length = " + jagged[0].length + " (columns in row 0)");
        IO.println("jagged[1].length = " + jagged[1].length + " (columns in row 1)");
        IO.println("jagged[2].length = " + jagged[2].length + " (columns in row 2)");
        IO.println();
        IO.println("Note: For rectangular arrays, all rows have the same length.");
        IO.println("      For jagged arrays, each row can have different lengths.");
    }
    
    /**
     * Prints a rectangular 2D array in readable format
     * @param arr The 2D array to print
     */
    static void print2DArray(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            IO.print("Row " + r + ": [");
            for (int c = 0; c < arr[r].length; c++) {
                IO.print(arr[r][c]);
                if (c < arr[r].length - 1) {
                    IO.print(", ");
                }
            }
            IO.println("]");
        }
    }
    
    /**
     * Prints a jagged 2D array in readable format
     * @param arr The jagged 2D array to print
     */
    static void printJaggedArray(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            IO.print("Row " + r + ": [");
            for (int c = 0; c < arr[r].length; c++) {
                IO.print(arr[r][c]);
                if (c < arr[r].length - 1) {
                    IO.print(", ");
                }
            }
            IO.println("]");
        }
    }
}

