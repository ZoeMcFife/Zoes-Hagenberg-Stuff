public class InsertionSortBugSOLVED {
    public static void main(String[] args) {
        IO.println("=== Insertion Sort Bug Exercise (SOLVED - Bug Location Marked) ===");
        IO.println();
        
        int[] arr = {12, 11, 13, 5, 6};
        IO.println("Original array:");
        printArray(arr);
        
        insertionSort(arr);
        
        IO.println();
        IO.println("Sorted array:");
        printArray(arr);
        
        IO.println();
        IO.println("Expected: [5, 6, 11, 12, 13]");
    }
    
    // ========================================
    // BUG LOCATION: Lines 27 and 33
    // ========================================
    // BUG DESCRIPTION: Two bugs in insertionSort()
    //
    // BUG 1 (Line 27): Wrong loop start index
    // - CURRENT (WRONG): for (int i = 0; i < n; i++)
    // - PROBLEM: Starts from index 0, but the first element (index 0) is already
    //   considered "sorted" in insertion sort. Starting from 0 causes unnecessary
    //   processing and can lead to incorrect behavior.
    // - FIX: Change to: for (int i = 1; i < n; i++)
    //
    // BUG 2 (Line 33): Wrong condition in while loop
    // - CURRENT (WRONG): while (j > 0 && arr[j] > key)
    // - PROBLEM: When j becomes 0, the condition j > 0 is false, so the loop stops.
    //   This means we never shift elements when j=0, even if arr[0] > key.
    //   This causes the first element to never be moved, leading to incorrect sorting.
    // - FIX: Change to: while (j >= 0 && arr[j] > key)
    // ========================================
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        
        // BUG 1: Should start from 1, not 0
        for (int i = 0; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            
            // BUG 2: Should be j >= 0, not j > 0
            while (j > 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            
            arr[j + 1] = key;
        }
    }
    
    public static void printArray(int[] arr) {
        IO.print("[");
        for (int i = 0; i < arr.length; i++) {
            IO.print(arr[i]);
            if (i < arr.length - 1) {
                IO.print(", ");
            }
        }
        IO.println("]");
    }
}
