public class QuickSortBugSOLVED {
    public static void main(String[] args) {
        IO.println("=== Quicksort Bug Example ===");
        IO.println("=== Use the debugger to find the bug! ===");
        IO.println("=== Pay attention to the call stack and recursive calls ===");
        IO.println();
        
        // ========================================
        // STEP 1: Test with small array
        // ========================================
        IO.println("=== Test 1: Small array ===");
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90};
        IO.print("Original array: [");
        printArray(arr1);
        IO.println("]");
        
        quickSort(arr1, 0, arr1.length - 1);
        
        IO.print("Sorted array: [");
        printArray(arr1);
        IO.println("]");
        IO.println();
        
        // ========================================
        // STEP 2: Test with already sorted array
        // ========================================
        IO.println("=== Test 2: Already sorted array ===");
        int[] arr2 = {1, 2, 3, 4, 5};
        IO.print("Original array: [");
        printArray(arr2);
        IO.println("]");
        
        quickSort(arr2, 0, arr2.length - 1);
        
        IO.print("Sorted array: [");
        printArray(arr2);
        IO.println("]");
        IO.println();
        
        // ========================================
        // STEP 3: Test with reverse sorted array
        // ========================================
        IO.println("=== Test 3: Reverse sorted array ===");
        int[] arr3 = {5, 4, 3, 2, 1};
        IO.print("Original array: [");
        printArray(arr3);
        IO.println("]");
        
        quickSort(arr3, 0, arr3.length - 1);
        
        IO.print("Sorted array: [");
        printArray(arr3);
        IO.println("]");
        IO.println();
        
        // ========================================
        // STEP 4: Test with array containing duplicates
        // ========================================
        IO.println("=== Test 4: Array with duplicates ===");
        int[] arr4 = {3, 7, 3, 1, 7, 2, 1};
        IO.print("Original array: [");
        printArray(arr4);
        IO.println("]");
        
        quickSort(arr4, 0, arr4.length - 1);
        
        IO.print("Sorted array: [");
        printArray(arr4);
        IO.println("]");
        IO.println();
    }
    
    // Quicksort implementation with a BUG
    public static void quickSort(int[] arr, int low, int high) {
        // BUG: Missing base case check!
        // Should check if (low < high) before proceeding
        // Without this check, we get stack overflow or incorrect behavior

        int pivotIndex = partition(arr, low, high);
        
        // Recursive calls
        // BUG: Should check if (low < pivotIndex - 1) before recursive call
        quickSort(arr, low, pivotIndex - 1);  // Sort left partition
        quickSort(arr, pivotIndex + 1, high);  // Sort right partition
    }
    
    // Partition method
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  // Choose last element as pivot
        int i = low - 1;  // Index of smaller element
        
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    // Helper method to swap two elements
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // Helper method to print array
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            IO.print(arr[i]);
            if (i < arr.length - 1) {
                IO.print(", ");
            }
        }
    }
}

