public class RemoveDuplicatesBugSOLVED {
    public static void main(String[] args) {
        IO.println("=== Remove Duplicates Bug Exercise (SOLVED - Bug Location Marked) ===");
        IO.println();
        
        int[] arr = {1, 2, 2, 3, 4, 4, 4, 5, 5};
        IO.println("Original array:");
        printArray(arr);
        
        int newLength = removeDuplicates(arr);
        
        IO.println();
        IO.println("Array after removing duplicates:");
        printArray(arr, newLength);
        IO.println("New length: " + newLength);
        
        IO.println();
        IO.println("Expected: [1, 2, 3, 4, 5] with length 5");
    }
    
    // ========================================
    // BUG LOCATION: Line 37
    // ========================================
    // BUG DESCRIPTION: Wrong comparison operator in removeDuplicates()
    //
    // The condition checks if the current element equals the previous element,
    // but it should check if they are NOT equal. When elements are equal (duplicates),
    // we should skip writing them. When they're different (unique), we should write them.
    //
    // CURRENT (WRONG): if (arr[i] == arr[i - 1])
    // - This writes elements when they are duplicates (wrong!)
    // - It should write elements when they are unique (different from previous)
    //
    // FIX: Change line 37 to: if (arr[i] != arr[i - 1])
    // ========================================
    public static int removeDuplicates(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        
        int writeIndex = 1;
        
        for (int i = 1; i < arr.length; i++) {
            // BUG: Should be != (not equal), not == (equal)
            if (arr[i] == arr[i - 1]) {
                arr[writeIndex] = arr[i];
                writeIndex++;
            }
        }
        
        return writeIndex;
    }
    
    public static void printArray(int[] arr) {
        printArray(arr, arr.length);
    }
    
    public static void printArray(int[] arr, int length) {
        IO.print("[");
        for (int i = 0; i < length; i++) {
            IO.print(arr[i]);
            if (i < length - 1) {
                IO.print(", ");
            }
        }
        IO.println("]");
    }
}
