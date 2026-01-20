public class RemoveDuplicatesBug {
    public static void main(String[] args) {
        IO.println("=== Remove Duplicates Bug Exercise ===");
        IO.println("Find and fix the bug using the debugger!");
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
    
    public static int removeDuplicates(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        
        int writeIndex = 1;
        
        for (int i = 1; i < arr.length; i++) {
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

