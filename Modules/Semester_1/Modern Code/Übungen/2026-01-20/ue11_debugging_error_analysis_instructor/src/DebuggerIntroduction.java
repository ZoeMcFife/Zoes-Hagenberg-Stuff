public class DebuggerIntroduction {
    public static void main(String[] args) {
        IO.println("=== Debugger Introduction ===");
        IO.println("This is a working example to practice using the debugger.");
        IO.println();
        
        // ========================================
        // STEP 1: Simple calculation
        // ========================================
        IO.println("Step 1: Simple calculation");
        int a = 10;
        int b = 20;
        int sum = a + b;
        IO.println("a = " + a + ", b = " + b);
        IO.println("sum = " + sum);
        IO.println();
        
        // ========================================
        // STEP 2: Array processing
        // ========================================
        IO.println("Step 2: Array processing");
        int[] numbers = {5, 10, 15, 20, 25};
        int total = 0;
        
        for (int i = 0; i < numbers.length; i++) {
            total += numbers[i];
        }
        
        IO.println("Array: [5, 10, 15, 20, 25]");
        IO.println("Sum of array elements: " + total);
        IO.println();
        
        // ========================================
        // STEP 3: Method call
        // ========================================
        IO.println("Step 3: Method call");
        int result = multiply(7, 8);
        IO.println("multiply(7, 8) = " + result);
        IO.println();
        
        IO.println("=== Practice: Set breakpoints and step through this code ===");
        IO.println("1. Set a breakpoint on line with 'int a = 10;'");
        IO.println("2. Run in debug mode (Shift+F9 or Debug button)");
        IO.println("3. Use Step Over (F8) to execute line by line");
        IO.println("4. Inspect variables in the Variables panel");
        IO.println("5. Use Step Into (F7) when calling multiply() to see inside the method");
    }
    
    public static int multiply(int x, int y) {
        int product = x * y;
        return product;
    }
}

