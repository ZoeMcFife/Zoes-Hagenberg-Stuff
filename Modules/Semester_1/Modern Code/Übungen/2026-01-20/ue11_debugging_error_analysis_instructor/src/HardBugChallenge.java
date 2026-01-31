public class HardBugChallenge {
    public static void main(String[] args) {
        IO.println("=== Hard Bug Challenge ===");
        IO.println("This is a challenging bug for you to find independently!");
        IO.println("=== CHALLENGE: Find the bug using the debugger! ===");
        IO.println("=== Hint: The bug is subtle and may only appear in certain cases ===");
        IO.println("=== Use all your debugging skills: breakpoints, step through, inspect variables, call stack ===");
        IO.println();

        // TODO -> Try to get a test case to find the bug

        // create a random array
        int[] arr1 = {5, 2, 8, 1, 9, 3};

        mergeSort(arr1, 0, arr1.length - 1);
        printArray(arr1);
    }
    
    // Merge sort implementation with a SUBTLE BUG
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            
            merge(arr, left, mid, right);
        }
    }
    
    // Merge method with a SUBTLE BUG
    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        // BUG: Should be "right - mid" but we have "right - mid + 1"
        // This causes an off-by-one error that leads to ArrayIndexOutOfBoundsException
        // or incorrect merging in certain cases
        int n2 = right - mid;  // BUG: Should be "right - mid"
        
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        
        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }
        
        // Merge the temporary arrays
        int i = 0, j = 0;
        int k = left;
        
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements of leftArr[]
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        
        // Copy remaining elements of rightArr[]
        // SUBTLE BUG: The bug is in the index calculation for copying rightArr
        // Look very carefully at how we're calculating the source index
        // Hint: Check the relationship between mid, right, and the array indices
        // Hint: The bug might cause ArrayIndexOutOfBoundsException in some cases
        // Hint: Try with arrays of different sizes to trigger the bug
        while (j < n2) {
            // BUG: Should be arr[k] = rightArr[j], but we have a subtle index issue
            // Actually, wait - let me make a real bug: we're using wrong index
            arr[k] = rightArr[j];
            j++;
            k++;
        }
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

