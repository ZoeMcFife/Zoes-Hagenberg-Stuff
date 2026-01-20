public class PrimeCheckBugSOLVED {
    public static void main(String[] args) {
        IO.println("=== Prime Check Bug Exercise (SOLVED - Bug Location Marked) ===");
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
    
    // ========================================
    // BUG LOCATION: Lines 25 and 32
    // ========================================
    // BUG DESCRIPTION: Missing edge case check and wrong loop bounds
    //
    // BUG 1 (Line 25): Missing check for numbers <= 1
    // - PROBLEM: Numbers <= 1 are not prime by definition:
    //   - 0 is not prime
    //   - 1 is not prime
    //   - Negative numbers are not prime
    // - Without this check, the function will incorrectly return true for 1
    //   (since the loop won't execute and it returns true).
    // - FIX: Add at the beginning: if (n <= 1) return false;
    //
    // BUG 2 (Line 32): Wrong loop start and bounds
    // - CURRENT (WRONG): for (int i = 1; i < n; i++)
    // - PROBLEM 1: Starts from i=1, but 1 divides every number, so this will
    //   always return false for any n > 1 (since n % 1 == 0).
    // - PROBLEM 2: Checks up to n-1, which is inefficient. We only need to check
    //   up to n/2 (or better, sqrt(n)) since if n has a divisor > n/2, it would
    //   have a corresponding divisor < 2.
    // - FIX: Change to: for (int i = 2; i <= n / 2; i++)
    // ========================================
    public static boolean isPrime(int n) {
        // BUG 1: Missing check for n <= 1
        
        // BUG 2: Should start from 2, not 1, and check up to n/2
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}
