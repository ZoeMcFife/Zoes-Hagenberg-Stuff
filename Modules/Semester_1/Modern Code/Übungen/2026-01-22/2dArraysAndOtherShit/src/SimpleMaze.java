/**
 * UE12 - Section 6: Practical Application 2 - Simple Maze
 * 
 * This class demonstrates using 2D arrays for maze representation:
 * - Represent maze: 0 = path, 1 = wall, 2 = player, 9 = goal
 * - Check if move is valid (not wall, within bounds)
 * - Find player position in maze
 * - Find path from player to goal using pathfinding algorithm
 * - Display maze with path
 */
public class SimpleMaze {
    
    // Constants for maze cell types
    static final int PATH = 0;
    static final int WALL = 1;
    static final int PLAYER = 2;
    static final int GOAL = 9;
    static final int PATH_MARKER = 3; // Used to mark the found path
    
    public static void main(String[] args) {
        IO.println("=== Simple Maze Representation ===");
        IO.println();
        
        // ========================================
        // STEP 1: Create maze representation
        // ========================================
        IO.println("--- Step 1: Creating Maze ---");
        int[][] maze = {
            {1, 1, 1, 1, 1, 1, 1},
            {1, 2, 0, 0, 0, 0, 1},  // Player at [1][1]
            {1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 9, 1},  // Goal at [4][5]
            {1, 1, 1, 1, 1, 1, 1}
        };
        
        IO.println("Maze representation:");
        IO.println("  0 = path, 1 = wall, 2 = player, 9 = goal");
        printMaze(maze);
        IO.println();
        
        // ========================================
        // STEP 2: Find player position
        // ========================================
        IO.println("--- Step 2: Finding Player Position ---");
        int[] playerPos = findPlayer(maze);
        if (playerPos != null) {
            IO.println("Player found at position [" + playerPos[0] + "][" + playerPos[1] + "]");
        } else {
            IO.println("Player not found in maze");
        }
        IO.println();
        
        // ========================================
        // STEP 3: Check valid moves
        // ========================================
        IO.println("--- Step 3: Checking Valid Moves ---");
        if (playerPos != null) {
            int r = playerPos[0];
            int c = playerPos[1];
            
            IO.println("From player position [" + r + "][" + c + "]:");
            IO.println("  Move up? " + isValidMove(maze, r - 1, c));
            IO.println("  Move down? " + isValidMove(maze, r + 1, c));
            IO.println("  Move left? " + isValidMove(maze, r, c - 1));
            IO.println("  Move right? " + isValidMove(maze, r, c + 1));
        }
        IO.println();
        
        // ========================================
        // STEP 4: Test boundary cases
        // ========================================
        IO.println("--- Step 4: Testing Boundary Cases ---");
        IO.println("Testing moves from corner [0][0]:");
        IO.println("  Move up? " + isValidMove(maze, -1, 0) + " (out of bounds)");
        IO.println("  Move left? " + isValidMove(maze, 0, -1) + " (out of bounds)");
        IO.println("  Move down? " + isValidMove(maze, 1, 0));
        IO.println("  Move right? " + isValidMove(maze, 0, 1));
        IO.println();
        
        // ========================================
        // STEP 5: Check if goal is reachable
        // ========================================
        IO.println("--- Step 5: Finding Goal ---");
        int[] goalPos = findGoal(maze);
        if (goalPos != null) {
            IO.println("Goal found at position [" + goalPos[0] + "][" + goalPos[1] + "]");
        } else {
            IO.println("Goal not found in maze");
        }
        IO.println();
        
        // ========================================
        // STEP 6: Pathfinding - Find path from player to goal
        // ========================================
        IO.println("--- Step 6: Pathfinding - Finding Path to Goal ---");
        if (playerPos != null && goalPos != null) {
            // Create a copy of the maze for pathfinding (don't modify original)
            int[][] mazeCopy = copyMaze(maze);
            
            // Find path using DFS (Depth-First Search)
            boolean pathFound = findPathDFS(mazeCopy, playerPos[0], playerPos[1], goalPos[0], goalPos[1]);
            
            if (pathFound) {
                IO.println("Path found from player to goal (DFS - any path)!");
                IO.println("Maze with path marked (* = path):");
                printMazeWithPath(mazeCopy);
                IO.println();
                
                IO.println("Path coordinates:");
                printPathCoordinates(mazeCopy, playerPos, goalPos);
            } else {
                IO.println("No path found from player to goal");
            }
        }
        IO.println();
        
        // ========================================
        // STEP 7: Shortest Path - BFS Algorithm
        // ========================================
        IO.println("--- Step 7: Shortest Path - BFS Algorithm ---");
        if (playerPos != null && goalPos != null) {
            // Create a copy of the maze for shortest path finding
            int[][] mazeCopy2 = copyMaze(maze);
            
            // Find shortest path using BFS (Breadth-First Search)
            int[][] shortestPath = findShortestPathBFS(mazeCopy2, playerPos[0], playerPos[1], goalPos[0], goalPos[1]);
            
            if (shortestPath != null) {
                IO.println("Shortest path found!");
                IO.println("Path length: " + (shortestPath.length - 1) + " steps");
                IO.println();
                
                IO.println("Shortest path coordinates:");
                for (int i = 0; i < shortestPath.length; i++) {
                    IO.print("[" + shortestPath[i][0] + "][" + shortestPath[i][1] + "]");
                    if (i < shortestPath.length - 1) {
                        IO.print(" -> ");
                    }
                }
                IO.println();
                IO.println();
                
                // Mark shortest path on maze
                markPathOnMaze(mazeCopy2, shortestPath);
                IO.println("Maze with shortest path marked (* = path):");
                printMazeWithPath(mazeCopy2);
            } else {
                IO.println("No path found from player to goal");
            }
        }
        IO.println();
        
        // ========================================
        // STEP 8: A* Algorithm (Heuristic-Based Pathfinding)
        // ========================================
        IO.println("--- Step 8: A* Algorithm (Heuristic-Based Pathfinding) ---");
        IO.println("A* uses f(n) = g(n) + h(n) where:");
        IO.println("  g(n) = actual cost from start to node n");
        IO.println("  h(n) = estimated cost from node n to goal (heuristic)");
        IO.println("  f(n) = total estimated cost");
        IO.println();
        
        if (playerPos != null && goalPos != null) {
            // Create a copy of the maze for A* pathfinding
            int[][] mazeCopy3 = copyMaze(maze);
            
            // Find path using A* algorithm
            int[][] aStarPath = findPathAStar(mazeCopy3, playerPos[0], playerPos[1], goalPos[0], goalPos[1]);
            
            if (aStarPath != null) {
                IO.println("A* path found!");
                IO.println("Path length: " + (aStarPath.length - 1) + " steps");
                IO.println();
                
                IO.println("A* path coordinates:");
                for (int i = 0; i < aStarPath.length; i++) {
                    IO.print("[" + aStarPath[i][0] + "][" + aStarPath[i][1] + "]");
                    if (i < aStarPath.length - 1) {
                        IO.print(" -> ");
                    }
                }
                IO.println();
                IO.println();
                
                // Mark A* path on maze
                markPathOnMaze(mazeCopy3, aStarPath);
                IO.println("Maze with A* path marked (* = path):");
                printMazeWithPath(mazeCopy3);
                IO.println();
                
                IO.println("Note: A* explores fewer nodes than BFS by using heuristic guidance,");
                IO.println("      but both find the shortest path in this unweighted grid.");
            } else {
                IO.println("No path found from player to goal");
            }
        }
    }
    
    /**
     * Checks if a move to position (r, c) is valid
     * A move is valid if:
     * - Position is within bounds
     * - Position is not a wall
     * @param maze The maze grid
     * @param r Row index
     * @param c Column index
     * @return true if move is valid, false otherwise
     */
    static boolean isValidMove(int[][] maze, int r, int c) {
        // Check boundaries first
        if (r < 0 || r >= maze.length || c < 0 || c >= maze[0].length) {
            return false;
        }
        // Check if it's not a wall
        return maze[r][c] != WALL;
    }
    
    /**
     * Finds the player position in the maze
     * @param maze The maze grid
     * @return Array with [row, col] if player found, null otherwise
     */
    static int[] findPlayer(int[][] maze) {
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                if (maze[r][c] == PLAYER) {
                    return new int[]{r, c};
                }
            }
        }
        return null;
    }
    
    /**
     * Finds the goal position in the maze
     * @param maze The maze grid
     * @return Array with [row, col] if goal found, null otherwise
     */
    static int[] findGoal(int[][] maze) {
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                if (maze[r][c] == GOAL) {
                    return new int[]{r, c};
                }
            }
        }
        return null;
    }
    
    /**
     * Prints the maze in readable format
     * @param maze The maze grid to print
     */
    static void printMaze(int[][] maze) {
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                if (maze[r][c] == WALL) {
                    IO.print("# ");  // Wall
                } else if (maze[r][c] == PLAYER) {
                    IO.print("P ");  // Player
                } else if (maze[r][c] == GOAL) {
                    IO.print("G ");  // Goal
                } else {
                    IO.print(". ");   // Path
                }
            }
            IO.println();
        }
    }
    
    /**
     * Prints the maze with path markers
     * @param maze The maze grid to print
     */
    static void printMazeWithPath(int[][] maze) {
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                if (maze[r][c] == WALL) {
                    IO.print("# ");  // Wall
                } else if (maze[r][c] == PLAYER) {
                    IO.print("P ");  // Player
                } else if (maze[r][c] == GOAL) {
                    IO.print("G ");  // Goal
                } else if (maze[r][c] == PATH_MARKER) {
                    IO.print("* ");  // Path marker
                } else {
                    IO.print(". ");   // Empty path
                }
            }
            IO.println();
        }
    }
    
    /**
     * Creates a copy of the maze
     * @param maze The original maze
     * @return A copy of the maze
     */
    static int[][] copyMaze(int[][] maze) {
        int[][] copy = new int[maze.length][maze[0].length];
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                copy[r][c] = maze[r][c];
            }
        }
        return copy;
    }
    
    /**
     * Finds a path from start to goal using Depth-First Search (DFS)
     * Marks the path with PATH_MARKER
     * @param maze The maze grid (will be modified to mark path)
     * @param startR Starting row
     * @param startC Starting column
     * @param goalR Goal row
     * @param goalC Goal column
     * @return true if path found, false otherwise
     */
    static boolean findPathDFS(int[][] maze, int startR, int startC, int goalR, int goalC) {
        // Base case: reached the goal
        if (startR == goalR && startC == goalC) {
            return true;
        }
        
        // Mark current cell as visited (unless it's player or goal)
        if (maze[startR][startC] != PLAYER && maze[startR][startC] != GOAL) {
            maze[startR][startC] = PATH_MARKER;
        }
        
        // Try all 4 directions: up, down, left, right
        int[] dr = {-1, 1, 0, 0}; // row deltas
        int[] dc = {0, 0, -1, 1}; // column deltas
        
        for (int i = 0; i < 4; i++) {
            int newR = startR + dr[i];
            int newC = startC + dc[i];
            
            // Check if move is valid and not visited
            if (isValidMove(maze, newR, newC) && 
                maze[newR][newC] != WALL && 
                maze[newR][newC] != PATH_MARKER &&
                maze[newR][newC] != PLAYER) {
                
                // Recursively try this path
                if (findPathDFS(maze, newR, newC, goalR, goalC)) {
                    return true; // Path found!
                }
            }
        }
        
        // Backtrack: unmark current cell if path not found
        if (maze[startR][startC] != PLAYER && maze[startR][startC] != GOAL) {
            maze[startR][startC] = PATH;
        }
        
        return false; // No path found from this position
    }
    
    /**
     * Prints the coordinates of the path from player to goal
     * @param maze The maze with path marked
     * @param playerPos Player position
     * @param goalPos Goal position
     */
    static void printPathCoordinates(int[][] maze, int[] playerPos, int[] goalPos) {
        IO.print("  Start: [" + playerPos[0] + "][" + playerPos[1] + "]");
        
        // Collect path coordinates
        int pathLength = 0;
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                if (maze[r][c] == PATH_MARKER) {
                    pathLength++;
                }
            }
        }
        
        IO.println(" -> Path length: " + (pathLength + 1) + " steps");
        IO.print("  Path: ");
        
        // Print path coordinates in order (simplified - shows all path markers)
        boolean first = true;
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                if (maze[r][c] == PATH_MARKER) {
                    if (!first) {
                        IO.print(" -> ");
                    }
                    IO.print("[" + r + "][" + c + "]");
                    first = false;
                }
            }
        }
        
        IO.println(" -> Goal: [" + goalPos[0] + "][" + goalPos[1] + "]");
    }
    
    /**
     * Finds the shortest path from start to goal using Breadth-First Search (BFS)
     * BFS guarantees finding the shortest path in an unweighted graph
     * @param maze The maze grid
     * @param startR Starting row
     * @param startC Starting column
     * @param goalR Goal row
     * @param goalC Goal column
     * @return 2D array where each row is [r, c] of path coordinates, or null if no path
     */
    static int[][] findShortestPathBFS(int[][] maze, int startR, int startC, int goalR, int goalC) {
        int rows = maze.length;
        int cols = maze[0].length;
        
        // Parent array to track path: parent[r][c] = [parentR, parentC]
        // We'll use a 2D array where each element stores parent coordinates
        // For simplicity, we'll use two separate arrays
        int[][] parentR = new int[rows][cols];
        int[][] parentC = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        
        // Initialize parent arrays to -1 (unvisited)
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                parentR[r][c] = -1;
                parentC[r][c] = -1;
            }
        }
        
        // BFS queue: store positions as [row, col]
        // We'll use a simple array-based queue
        int[][] queue = new int[rows * cols][2];
        int front = 0;
        int rear = 0;
        
        // Start BFS from start position
        queue[rear][0] = startR;
        queue[rear][1] = startC;
        rear++;
        visited[startR][startC] = true;
        parentR[startR][startC] = startR; // Mark as root
        parentC[startR][startC] = startC;
        
        // Direction arrays for 4 directions: up, down, left, right
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        // BFS main loop
        while (front < rear) {
            int currentR = queue[front][0];
            int currentC = queue[front][1];
            front++;
            
            // Check if we reached the goal
            if (currentR == goalR && currentC == goalC) {
                // Reconstruct path by following parent pointers
                return reconstructPath(parentR, parentC, startR, startC, goalR, goalC);
            }
            
            // Explore all 4 neighbors
            for (int i = 0; i < 4; i++) {
                int newR = currentR + dr[i];
                int newC = currentC + dc[i];
                
                // Check if valid move and not visited
                if (isValidMove(maze, newR, newC) && 
                    maze[newR][newC] != WALL && 
                    !visited[newR][newC]) {
                    
                    // Mark as visited and set parent
                    visited[newR][newC] = true;
                    parentR[newR][newC] = currentR;
                    parentC[newR][newC] = currentC;
                    
                    // Add to queue
                    queue[rear][0] = newR;
                    queue[rear][1] = newC;
                    rear++;
                }
            }
        }
        
        return null; // No path found
    }
    
    /**
     * Reconstructs the path from start to goal using parent arrays
     * @param parentR Parent row array
     * @param parentC Parent column array
     * @param startR Start row
     * @param startC Start column
     * @param goalR Goal row
     * @param goalC Goal column
     * @return 2D array with path coordinates
     */
    static int[][] reconstructPath(int[][] parentR, int[][] parentC, int startR, int startC, int goalR, int goalC) {
        // First, count path length by following parent pointers backwards
        int pathLength = 1;
        int r = goalR;
        int c = goalC;
        
        while (r != startR || c != startC) {
            int prevR = parentR[r][c];
            int prevC = parentC[r][c];
            r = prevR;
            c = prevC;
            pathLength++;
        }
        
        // Now build path array from start to goal
        int[][] path = new int[pathLength][2];
        r = goalR;
        c = goalC;
        
        // Fill path backwards (from goal to start)
        for (int i = pathLength - 1; i >= 0; i--) {
            path[i][0] = r;
            path[i][1] = c;
            if (i > 0) {
                int prevR = parentR[r][c];
                int prevC = parentC[r][c];
                r = prevR;
                c = prevC;
            }
        }
        
        return path;
    }
    
    /**
     * Marks the path on the maze with PATH_MARKER
     * @param maze The maze to mark
     * @param path The path coordinates
     */
    static void markPathOnMaze(int[][] maze, int[][] path) {
        // Mark all path positions (skip start and goal as they have special markers)
        for (int i = 1; i < path.length - 1; i++) {
            int r = path[i][0];
            int c = path[i][1];
            if (maze[r][c] != PLAYER && maze[r][c] != GOAL) {
                maze[r][c] = PATH_MARKER;
            }
        }
    }
    
    /**
     * Calculates Manhattan distance heuristic (admissible for grid)
     * Manhattan distance: |r1 - r2| + |c1 - c2|
     * @param r1 Row of first point
     * @param c1 Column of first point
     * @param r2 Row of second point
     * @param c2 Column of second point
     * @return Manhattan distance
     */
    static int manhattanDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
    
    /**
     * Finds the shortest path using A* algorithm
     * A* uses f(n) = g(n) + h(n) where:
     * - g(n) = actual cost from start to node n
     * - h(n) = heuristic estimate from node n to goal (Manhattan distance)
     * - f(n) = total estimated cost
     * @param maze The maze grid
     * @param startR Starting row
     * @param startC Starting column
     * @param goalR Goal row
     * @param goalC Goal column
     * @return 2D array where each row is [r, c] of path coordinates, or null if no path
     */
    static int[][] findPathAStar(int[][] maze, int startR, int startC, int goalR, int goalC) {
        int rows = maze.length;
        int cols = maze[0].length;
        
        // g(n) = actual cost from start to node n
        int[][] gCost = new int[rows][cols];
        // f(n) = g(n) + h(n) = total estimated cost
        int[][] fCost = new int[rows][cols];
        // Parent arrays to reconstruct path
        int[][] parentR = new int[rows][cols];
        int[][] parentC = new int[rows][cols];
        // Track which nodes are in open set (to be explored)
        boolean[][] inOpenSet = new boolean[rows][cols];
        // Track which nodes are in closed set (already explored)
        boolean[][] inClosedSet = new boolean[rows][cols];
        
        // Initialize all costs to infinity (large number)
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                gCost[r][c] = Integer.MAX_VALUE;
                fCost[r][c] = Integer.MAX_VALUE;
                parentR[r][c] = -1;
                parentC[r][c] = -1;
            }
        }
        
        // Initialize start node
        gCost[startR][startC] = 0;
        fCost[startR][startC] = manhattanDistance(startR, startC, goalR, goalC);
        inOpenSet[startR][startC] = true;
        parentR[startR][startC] = startR;
        parentC[startR][startC] = startC;
        
        // Direction arrays for 4 directions: up, down, left, right
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        // A* main loop
        while (true) {
            // Find node in open set with lowest f(n)
            int currentR = -1;
            int currentC = -1;
            int minF = Integer.MAX_VALUE;
            
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (inOpenSet[r][c] && fCost[r][c] < minF) {
                        minF = fCost[r][c];
                        currentR = r;
                        currentC = c;
                    }
                }
            }
            
            // If no node in open set, no path exists
            if (currentR == -1) {
                return null;
            }
            
            // Move current node from open set to closed set
            inOpenSet[currentR][currentC] = false;
            inClosedSet[currentR][currentC] = true;
            
            // Check if we reached the goal
            if (currentR == goalR && currentC == goalC) {
                return reconstructPath(parentR, parentC, startR, startC, goalR, goalC);
            }
            
            // Explore all 4 neighbors
            for (int i = 0; i < 4; i++) {
                int newR = currentR + dr[i];
                int newC = currentC + dc[i];
                
                // Skip if invalid move, wall, or already in closed set
                if (!isValidMove(maze, newR, newC) || 
                    maze[newR][newC] == WALL || 
                    inClosedSet[newR][newC]) {
                    continue;
                }
                
                // Calculate tentative g cost (current g + 1 step)
                int tentativeG = gCost[currentR][currentC] + 1;
                
                // If this path to neighbor is better, update it
                if (tentativeG < gCost[newR][newC]) {
                    // Update parent
                    parentR[newR][newC] = currentR;
                    parentC[newR][newC] = currentC;
                    
                    // Update g cost
                    gCost[newR][newC] = tentativeG;
                    
                    // Update f cost = g + h (heuristic)
                    fCost[newR][newC] = tentativeG + manhattanDistance(newR, newC, goalR, goalC);
                    
                    // Add to open set if not already there
                    if (!inOpenSet[newR][newC]) {
                        inOpenSet[newR][newC] = true;
                    }
                }
            }
        }
    }
}

