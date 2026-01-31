public class PrimeCheckBug {
    public static void main(String[] args) {
        IO.println("=== Prime Check Bug Exercise ===");
        IO.println("Find and fix the bug using the debugger!");
        IO.println();
        
        int[] testNumbers = {2, 3, 4, 5, 17, 25, 29, 97, 100, 101};
        
        IO.println("Testing prime numbers:");
        for (int num : testNumbers) {
            boolean isPrime = isPrime(num);
            IO.println(num + " is " + (isPrime ? "prime" : "not prime"));
        }
        
        IO.println();
        IO.println("Expected results:");
        IO.println("2: prime, 3: prime, 4: not prime, 5: prime");
        IO.println("17: prime, 25: not prime, 29: prime, 97: prime");
        IO.println("100: not prime, 101: prime");
    }
    
    public static boolean isPrime(int n) {
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}

