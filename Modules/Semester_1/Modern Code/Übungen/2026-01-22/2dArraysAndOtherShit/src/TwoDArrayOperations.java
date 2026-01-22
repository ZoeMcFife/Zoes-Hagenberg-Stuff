/**
 * UE12 - Section 3: Common Operations
 * 
 * This class demonstrates common operations on 2D arrays:
 * - Sum all elements
 * - Find maximum and minimum values
 * - Search for a value (linear search in 2D)
 * - Count occurrences of specific values
 */
public class TwoDArrayOperations {
    
    public static void main(String[] args) {
        IO.println("=== Common 2D Array Operations ===");
        IO.println();
        
        // Sample 2D array for testing
        int[][] matrix = {
            {3, 7, 2, 9},
            {1, 5, 8, 4},
            {6, 2, 1, 7}
        };
        
        IO.println("Test matrix:");
        printMatrix(matrix);
        IO.println();
        
        // ========================================
        // STEP 1: Sum all elements
        // ========================================
        IO.println("--- Step 1: Sum of All Elements ---");
        int totalSum = sumAllElements(matrix);
        IO.println("Sum of all elements: " + totalSum);
        IO.println();
        
        // ========================================
        // STEP 2: Find maximum value
        // ========================================
        IO.println("--- Step 2: Find Maximum Value ---");
        int maxValue = findMax(matrix);
        IO.println("Maximum value: " + maxValue);
        IO.println();
        
        // ========================================
        // STEP 3: Find minimum value
        // ========================================
        IO.println("--- Step 3: Find Minimum Value ---");
        int minValue = findMin(matrix);
        IO.println("Minimum value: " + minValue);
        IO.println();
        
        // ========================================
        // STEP 4: Search for a value
        // ========================================
        IO.println("--- Step 4: Search for Value ---");
        int target1 = 8;
        int[] position1 = searchValue(matrix, target1);
        if (position1 != null) {
            IO.println("Found " + target1 + " at position [" + position1[0] + "][" + position1[1] + "]");
        } else {
            IO.println("Value " + target1 + " not found");
        }
        
        int target2 = 10;
        int[] position2 = searchValue(matrix, target2);
        if (position2 != null) {
            IO.println("Found " + target2 + " at position [" + position2[0] + "][" + position2[1] + "]");
        } else {
            IO.println("Value " + target2 + " not found");
        }
        IO.println();
        
        // ========================================
        // STEP 5: Count occurrences
        // ========================================
        IO.println("--- Step 5: Count Occurrences ---");
        int valueToCount = 2;
        int count = countOccurrences(matrix, valueToCount);
        IO.println("Number of occurrences of " + valueToCount + ": " + count);
        
        int valueToCount2 = 7;
        int count2 = countOccurrences(matrix, valueToCount2);
        IO.println("Number of occurrences of " + valueToCount2 + ": " + count2);
    }
    
    /**
     * Calculates the sum of all elements in a 2D array
     * @param arr The 2D array
     * @return The sum of all elements
     */
    static int sumAllElements(int[][] arr) {
        int sum = 0;
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                sum += arr[r][c];
            }
        }
        return sum;
    }
    
    /**
     * Finds the maximum value in a 2D array
     * @param arr The 2D array
     * @return The maximum value
     */
    static int findMax(int[][] arr) {
        int max = arr[0][0]; // Start with first element
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                if (arr[r][c] > max) {
                    max = arr[r][c];
                }
            }
        }
        return max;
    }
    
    /**
     * Finds the minimum value in a 2D array
     * @param arr The 2D array
     * @return The minimum value
     */
    static int findMin(int[][] arr) {
        int min = arr[0][0]; // Start with first element
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                if (arr[r][c] < min) {
                    min = arr[r][c];
                }
            }
        }
        return min;
    }
    
    /**
     * Searches for a value in a 2D array (linear search)
     * @param arr The 2D array to search
     * @param target The value to find
     * @return Array with [row, col] if found, null otherwise
     */
    static int[] searchValue(int[][] arr, int target) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                if (arr[r][c] == target) {
                    return new int[]{r, c};
                }
            }
        }
        return null; // Not found
    }
    
    /**
     * Counts how many times a value appears in a 2D array
     * @param arr The 2D array
     * @param value The value to count
     * @return The number of occurrences
     */
    static int countOccurrences(int[][] arr, int value) {
        int count = 0;
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                if (arr[r][c] == value) {
                    count++;
                }
            }
        }
        return count;
    }
    
    /**
     * Helper method to print matrix
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

