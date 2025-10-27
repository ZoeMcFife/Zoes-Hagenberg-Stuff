public class StarPattern {
    /**
     * Generate a row of stars for the pattern
     * @param rowNumber the row number (1-based)
     * @return a string with the appropriate number of stars
     */
    public static String generateRow(int rowNumber) {
        StringBuilder row = new StringBuilder();
        for (int i = 0; i < rowNumber; i++) {
            if (i > 0) {
                row.append(" ");
            }
            row.append("*");
        }
        return row.toString();
    }

    /**
     * Generate the complete star pattern
     * @param size the number of rows in the pattern
     * @return an array of strings, each representing one row
     */
    public static String[] generatePattern(int size) {
        String[] pattern = new String[size];
        for (int i = 0; i < size; i++) {
            pattern[i] = generateRow(i + 1);
        }
        return pattern;
    }

    public static void main(String[] args) {
        // Variable to control pattern size
        int patternSize = 5;

        // Display header
        System.out.println("=== Star Pattern Generator ===");
        System.out.println("Pattern size: " + patternSize);

        // Generate and display the pattern
        String[] pattern = generatePattern(patternSize);
        for (String row : pattern) {
            System.out.println(row);
        }
    }
}
