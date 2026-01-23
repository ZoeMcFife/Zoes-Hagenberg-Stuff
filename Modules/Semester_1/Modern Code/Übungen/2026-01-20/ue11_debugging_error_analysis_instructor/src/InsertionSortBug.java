public class InsertionSortBug {
    public static void main(String[] args) {
        IO.println("=== Insertion Sort Bug Exercise ===");
        IO.println("Find and fix the bug using the debugger!");
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
    
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            
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

