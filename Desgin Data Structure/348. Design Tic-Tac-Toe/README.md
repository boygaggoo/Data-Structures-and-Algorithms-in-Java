### DP
|        |       | 0 | Player 1 | Player 2 |
|--------|-------|---|----------|----------|
| Row    | 0-1n  |   |          |          |
| Column | 1n-2n |   |          |          |
| /      | 2n-4n |   |          |          |
| \      | 4n-5n |   |          |          |


```java
class TicTacToe {
    int[][] board;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        board = new int[6*n][3];
    }
    
    public int move(int row, int col, int player) {
        int n = board.length / 6;
        // for accumulated values of - , | , / and \
        int[] temp = new int[]{row,n+col,2*n+row+col,5*n+row-col};
        for( int i : temp ){
            if( ++board[i][player] == n ) return player;
        }
        return 0;
    }
}
```