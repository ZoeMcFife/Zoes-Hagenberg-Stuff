#!/bin/bash

# Test Runner Script for Exercise 2: Variables, Types, Expressions and Control Structures
# This script compiles and runs all unit tests

set -e  # Exit on error

# Colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo "=========================================="
echo "Exercise 2 - Unit Test Runner"
echo "=========================================="
echo ""

# Get the directory where this script is located
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

# Clean previous compilation
echo -e "${YELLOW}Cleaning previous build...${NC}"
find . -name "*.class" -type f -delete

# Compile source files
echo -e "${YELLOW}Compiling source files...${NC}"
if javac -d . src/*.java; then
    echo -e "${GREEN}✓ Source files compiled successfully${NC}"
else
    echo -e "${RED}✗ Failed to compile source files${NC}"
    exit 1
fi

# Compile test files
echo -e "${YELLOW}Compiling test files...${NC}"
if javac -cp "lib/junit-platform-console-standalone-1.10.1.jar:." -d . test/*.java; then
    echo -e "${GREEN}✓ Test files compiled successfully${NC}"
else
    echo -e "${RED}✗ Failed to compile test files${NC}"
    exit 1
fi

echo ""
echo -e "${YELLOW}Running all tests...${NC}"
echo ""

# Run tests
java -jar lib/junit-platform-console-standalone-1.10.1.jar \
    --class-path . \
    --scan-class-path

# Check exit code
if [ $? -eq 0 ]; then
    echo ""
    echo -e "${GREEN}=========================================="
    echo "All tests passed successfully!"
    echo -e "==========================================${NC}"
    exit 0
else
    echo ""
    echo -e "${RED}=========================================="
    echo "Some tests failed!"
    echo -e "==========================================${NC}"
    exit 1
fi
