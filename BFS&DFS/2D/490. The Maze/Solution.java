public class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || start == null || destination == null) {
            return false;
        }
        boolean[][] hasVisited = new boolean[maze.length][maze[0].length];

        return dfs(maze, hasVisited, start, destination);

    }

    public boolean dfs(int[][] maze, boolean[][] hasVisited, int[] cur, int[] des) {
        int y = cur[0];
        int x = cur[1];
        if (y == des[0] && x == des[1]) return true;
        if (hasVisited[y][x]) return false;
        hasVisited[y][x] = true;

        if (x > 0 && maze[y][x - 1] != 1) {
            int i = x - 1;
            while (i > 0 && maze[y][i - 1] != 1) i--;
            if (dfs(maze, hasVisited, new int[]{y, i}, des)) return true;
        }

        if (x < maze[0].length - 1 && maze[y][x + 1] != 1) {
            int i = x + 1;
            while (i < maze[0].length - 1 && maze[y][i + 1] != 1) i++;
            if (dfs(maze, hasVisited, new int[]{y, i}, des)) return true;
        }

        if (y > 0 && maze[y - 1][x] != 1) {
            int i = y - 1;
            while (i > 0 && maze[i - 1][x] != 1) i--;
            if (dfs(maze, hasVisited, new int[]{i, x}, des)) return true;
        }

        if (y < maze.length - 1 && maze[y + 1][x] != 1) {
            int i = y + 1;
            while (i < maze.length - 1 && maze[i + 1][x] != 1) i++;
            if (dfs(maze, hasVisited, new int[]{i, x}, des)) return true;
        }

        return false;
    }
}