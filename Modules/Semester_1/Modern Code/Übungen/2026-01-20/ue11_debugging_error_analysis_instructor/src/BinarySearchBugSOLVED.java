public class BinarySearchBugSOLVED {
    public static void main(String[] args) {
        IO.println("=== Binary Search Bug Example ===");
        IO.println("=== BUG: Some searches may hang or give wrong results ===");
        IO.println("=== Use the debugger to find the bug! ===");
        IO.println();
        
        // Create a sorted array
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 45, 67, 78, 89, 95};
        
        IO.println("Sorted array: [2, 5, 8, 12, 16, 23, 38, 45, 67, 78, 89, 95]");
        IO.println();
        
        // ========================================
        // STEP 1: Search for value that exists
        // ========================================
        IO.println("=== Test 1: Searching for 23 (should be found) ===");
        int target1 = 23;
        int index1 = binarySearch(arr, target1);
        if (index1 != -1) {
            IO.println("Found " + target1 + " at index: " + index1);
        } else {
            IO.println(target1 + " not found");
        }
        IO.println();
        
        // ========================================
        // STEP 2: Search for value that doesn't exist
        // ========================================
        IO.println("=== Test 2: Searching for 50 (should NOT be found) ===");
        int target2 = 50;
        int index2 = binarySearch(arr, target2);
        if (index2 != -1) {
            IO.println("Found " + target2 + " at index: " + index2);
        } else {
            IO.println(target2 + " not found");
        }
        IO.println();
        
        // ========================================
        // STEP 3: Search for first element
        // ========================================
        IO.println("=== Test 3: Searching for 2 (first element) ===");
        int target3 = 2;
        int index3 = binarySearch(arr, target3);
        if (index3 != -1) {
            IO.println("Found " + target3 + " at index: " + index3);
        } else {
            IO.println(target3 + " not found");
        }
        IO.println();
        
        // ========================================
        // STEP 4: Search for last element
        // ========================================
        IO.println("=== Test 4: Searching for 95 (last element) ===");
        int target4 = 95;
        int index4 = binarySearch(arr, target4);
        if (index4 != -1) {
            IO.println("Found " + target4 + " at index: " + index4);
        } else {
            IO.println(target4 + " not found");
        }
        IO.println();
    }
    
    // Binary search implementation with a BUG
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                // BUG: Should be left = mid + 1, but we have left = mid
                // This causes infinite loop or wrong results
                left = mid;  // BUG HERE!
            } else {
                right = mid - 1;
            }
        }
        
        return -1;  // Not found
    }
}

