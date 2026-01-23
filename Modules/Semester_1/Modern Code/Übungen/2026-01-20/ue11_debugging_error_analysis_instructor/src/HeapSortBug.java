public class HeapSortBug {
    public static void main(String[] args) {
        IO.println("=== Heap Sort Bug Exercise ===");
        IO.println("Find and fix the bug using the debugger!");
        IO.println();
        
        int[] arr = {64, 34, 25, 12, 22, 11, 90, 5};
        IO.println("Original array:");
        printArray(arr);
        
        heapSort(arr);
        
        IO.println();
        IO.println("Sorted array:");
        printArray(arr);
        
        IO.println();
        IO.println("Expected: [5, 11, 12, 22, 25, 34, 64, 90]");
    }
    
    public static void heapSort(int[] arr) {
        int n = arr.length;
        
        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
        }
    }
    
    public static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
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
