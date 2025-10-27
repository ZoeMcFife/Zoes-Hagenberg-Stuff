# Exercise 2: Variables, Types, Expressions and Control Structures - Unit Tests

This directory contains comprehensive unit tests for all six exercises in Exercise 2.

## Test Coverage

### ✅ RectangleCalculator (13 tests)
- **Area calculation**: Tests with various dimensions including unit, large, and edge cases
- **Perimeter calculation**: Tests with different rectangle sizes
- **Diagonal calculation**: Tests with Pythagorean triples (3-4-5, 5-12-13), squares, and the example from requirements
- **Formula verification**: Tests ensure correct mathematical formulas are used

### ✅ FizzBuzz (11 tests)
- **Divisibility by 3**: Tests "Fizz" output for multiples of 3
- **Divisibility by 5**: Tests "Buzz" output for multiples of 5
- **Divisibility by 15**: Tests "FizzBuzz" output for multiples of both 3 and 5
- **Regular numbers**: Tests that non-multiples return the number as a string
- **Sequence verification**: Tests the complete 1-15 sequence from requirements
- **Edge cases**: Tests precedence (FizzBuzz over Fizz/Buzz) and pattern consistency

### ✅ DayOfWeek (19 tests)
- **Day names**: Tests all 7 days (Monday through Sunday)
- **Day types**: Tests weekday vs weekend classification
- **Invalid inputs**: Tests handling of 0, negative numbers, and numbers > 7
- **Boundary values**: Tests edge cases at the limits of valid input
- **Consistency**: Tests that day names and types match correctly

### ✅ StarPattern (17 tests)
- **Row generation**: Tests individual row generation with correct star counts
- **Pattern generation**: Tests complete patterns of various sizes (1, 2, 3, 5, 7, 10)
- **Format verification**: Tests star separation by spaces, no trailing/leading spaces
- **Right triangle shape**: Tests that pattern forms correct triangle shape
- **Large patterns**: Tests with pattern size of 50
- **Consistency**: Tests pattern generation is deterministic

### ✅ CollatzConjecture (22 tests)
- **Next value calculation**: Tests both even (n/2) and odd (3n+1) formulas
- **Step counting**: Tests various starting numbers including powers of 2
- **Sequence generation**: Tests complete sequences with correct steps
- **Known cases**: Tests the example from requirements (5) and difficult number (27)
- **Formula verification**: Tests that Pythagorean theorem and parity are correct
- **Termination**: Tests all sequences end at 1

### ✅ DiceRollingSimulator (16 tests)
- **Range verification**: Tests dice rolls are between 1 and 6
- **Variety test**: Tests that random rolls produce varied results
- **Deterministic testing**: Uses custom roller for predictable test scenarios
- **Sequence verification**: Tests roll counting and numbering
- **Target detection**: Tests that rolling stops when target is reached
- **Statistical tests**: Tests distribution is reasonable over many rolls

## Total: 98 comprehensive unit tests

## Running the Tests

### Using the test runner script (recommended):
```bash
./run_tests.sh
```

### Manual execution:
```bash
# Compile source files
javac -d . src/*.java

# Compile test files
javac -cp "lib/junit-platform-console-standalone-1.10.1.jar:." -d . test/*.java

# Run tests
java -jar lib/junit-platform-console-standalone-1.10.1.jar --class-path . --scan-class-path
```

## Test Framework

- **JUnit 5** (Jupiter) - Modern testing framework
- **JUnit Platform Console Standalone** 1.10.1 - All-in-one test runner

## Test Quality Features

1. **Descriptive names**: Each test has a clear `@DisplayName` annotation
2. **Edge case coverage**: Tests include boundary values, invalid inputs, and special cases
3. **Requirement verification**: Tests directly verify examples from the exercise requirements
4. **Mathematical correctness**: Tests verify formulas and algorithms are implemented correctly
5. **Deterministic testing**: Uses dependency injection for testable random behavior (DiceRollingSimulator)
6. **Comprehensive assertions**: Tests include helpful error messages for debugging

## File Structure

```
.
├── src/                          # Source files
│   ├── RectangleCalculator.java
│   ├── FizzBuzz.java
│   ├── DayOfWeek.java
│   ├── StarPattern.java
│   ├── CollatzConjecture.java
│   └── DiceRollingSimulator.java
├── test/                         # Test files
│   ├── RectangleCalculatorTest.java
│   ├── FizzBuzzTest.java
│   ├── DayOfWeekTest.java
│   ├── StarPatternTest.java
│   ├── CollatzConjectureTest.java
│   └── DiceRollingSimulatorTest.java
├── lib/                          # Testing libraries
│   └── junit-platform-console-standalone-1.10.1.jar
├── run_tests.sh                  # Test runner script
└── TEST_README.md               # This file
```

## Design Decisions

### Testability
Each exercise class has been refactored to separate business logic into static methods that can be tested independently from the `main` method. This follows the Single Responsibility Principle and makes the code more maintainable.

### Test Data Structures
- **CollatzConjecture**: Uses a `CollatzStep` class to represent each step in the sequence
- **DiceRollingSimulator**: Uses a `RollResult` class to store roll information and supports custom rollers for deterministic testing

### Test Categories
Tests are organized into logical groups:
1. **Basic functionality**: Core feature tests
2. **Edge cases**: Boundary values and special inputs
3. **Invalid inputs**: Error handling verification
4. **Formula verification**: Mathematical correctness
5. **Requirements compliance**: Direct verification of exercise specifications

## Interpreting Test Results

When all tests pass, you'll see output like:
```
98 tests found
98 tests started
98 tests successful
0 tests failed
```

If a test fails, JUnit will show:
- Which test failed
- The expected vs actual values
- The assertion message explaining what went wrong

## Notes

- All tests pass with the current implementation
- Tests are independent and can run in any order
- No external dependencies required beyond JUnit
- Tests run quickly (< 1 second total)
