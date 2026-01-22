/**
 * UE12 - Section 2: Traversal Patterns
 * 
 * This class demonstrates different traversal patterns for 2D arrays:
 * - Row-major traversal (standard, cache-friendly)
 * - Column-major traversal (vertical processing)
 * - Comparing both approaches
 */
public class TwoDArrayTraversal {
    
    public static void main(String[] args) {
        IO.println("=== 2D Array Traversal Patterns ===");
        IO.println();
        
        // Initialize a sample 2D array
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        
        IO.println("Original matrix:");
        printMatrix(matrix);
        IO.println();
        
        // ========================================
        // STEP 1: Row-major traversal (standard)
        // ========================================
        IO.println("--- Step 1: Row-Major Traversal ---");
        IO.println("Traversing row by row (outer loop = rows, inner loop = columns):");
        IO.println();
        
        IO.println("Row-major order:");
        traverseRowMajor(matrix);
        IO.println();
        
        IO.println("Row-major traversal path:");
        IO.println("  (0,0) -> (0,1) -> (0,2) -> (0,3)");
        IO.println("  -> (1,0) -> (1,1) -> (1,2) -> (1,3)");
        IO.println("  -> (2,0) -> (2,1) -> (2,2) -> (2,3)");
        IO.println();
        IO.println("Note: Row-major is cache-friendly because it accesses");
        IO.println("      memory in contiguous order (better performance).");
        IO.println();
        
        // ========================================
        // STEP 2: Column-major traversal
        // ========================================
        IO.println("--- Step 2: Column-Major Traversal ---");
        IO.println("Traversing column by column (outer loop = columns, inner loop = rows):");
        IO.println();
        
        IO.println("Column-major order:");
        traverseColumnMajor(matrix);
        IO.println();
        
        IO.println("Column-major traversal path:");
        IO.println("  (0,0) -> (1,0) -> (2,0)");
        IO.println("  -> (0,1) -> (1,1) -> (2,1)");
        IO.println("  -> (0,2) -> (1,2) -> (2,2)");
        IO.println("  -> (0,3) -> (1,3) -> (2,3)");
        IO.println();
        IO.println("Note: Column-major is useful for vertical operations");
        IO.println("      (e.g., checking columns in Connect 4).");
        IO.println();
        
        // ========================================
        // STEP 3: Print matrix in both orders
        // ========================================
        IO.println("--- Step 3: Printing in Different Orders ---");
        IO.println();
        
        IO.println("Printing row by row (row-major):");
        printRowMajor(matrix);
        IO.println();
        
        IO.println("Printing column by column (column-major):");
        printColumnMajor(matrix);
    }
    
    /**
     * Traverses a 2D array in row-major order
     * @param arr The 2D array to traverse
     */
    static void traverseRowMajor(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                IO.print("(" + r + "," + c + ")=" + arr[r][c] + " ");
            }
            IO.println();
        }
    }
    
    /**
     * Traverses a 2D array in column-major order
     * @param arr The 2D array to traverse
     */
    static void traverseColumnMajor(int[][] arr) {
        for (int c = 0; c < arr[0].length; c++) {
            for (int r = 0; r < arr.length; r++) {
                IO.print("(" + r + "," + c + ")=" + arr[r][c] + " ");
            }
            IO.println();
        }
    }
    
    /**
     * Prints matrix in row-major order (standard display)
     * @param arr The 2D array to print
     */
    static void printRowMajor(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                IO.print(String.format("%3d ", arr[r][c]));
            }
            IO.println();
        }
    }
    
    /**
     * Prints matrix in column-major order (transposed display)
     * @param arr The 2D array to print
     */
    static void printColumnMajor(int[][] arr) {
        for (int c = 0; c < arr[0].length; c++) {
            for (int r = 0; r < arr.length; r++) {
                IO.print(String.format("%3d ", arr[r][c]));
            }
            IO.println();
        }
    }
    
    /**
     * Helper method to print matrix in standard format
     * @param arr The 2D array to print
     */
    static void printMatrix(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                IO.print(String.format("%3d ", arr[r][c]));
            }
            IO.println();
        }
    }
}

