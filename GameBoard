
package game;
import java.util.*;

public class GameBoard {
    public int[][] board;
    private int currentPlayer;

    public GameBoard() {
        board = new int[8][8];
        board[3][3] = board[4][4] = 1;
        board[3][4] = board[4][3] = -1;
        currentPlayer = 1;
    }

    public void switchPlayer() {
        currentPlayer = -currentPlayer;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getOpponent() {
        return -currentPlayer;
    }

    public ArrayList<int[]> getValidMoves() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 0) {
                    int[] move = {i, j};
                    if (isValidMove(move)) {
                        moves.add(move);
                    }
                }
            }
        }
        return moves;
    }

    public boolean isValidMove(int[] move) {
        int row = move[0], col = move[1];
        if (board[row][col] != 0) {
            return false;
        }
        for (int drow = -1; drow <= 1; drow++) {
            for (int dcol = -1; dcol <= 1; dcol++) {
                if (drow == 0 && dcol == 0) {
                    continue;
                }
                int r = row + drow, c = col + dcol;
                while (r >= 0 && r < 8 && c >= 0 && c < 8 && board[r][c] == getOpponent()) {
                    r += drow;
                    c += dcol;
                }
                if (r >= 0 && r < 8 && c >= 0 && c < 8 && board[r][c] == currentPlayer && (r != row + drow || c != col + dcol)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void makeMove(int[] move) {
        int row = move[0], col = move[1];
        board[row][col] = currentPlayer;
        for (int drow = -1; drow <= 1; drow++) {
            for (int dcol = -1; dcol <= 1; dcol++) {
                if (drow == 0 && dcol == 0) {
                    continue;
                }
                int r = row + drow, c = col + dcol;
                while (r >= 0 && r < 8 && c >= 0 && c < 8 && board[r][c] == getOpponent()) {
                    r += drow;
                    c += dcol;
                }
                if (r >= 0 && r < 8 && c >= 0 && c < 8 && board[r][c] == currentPlayer) {
                    r -= drow;
                    c -= dcol;
                    while (r != row || c != col) {
                        board[r][c] = currentPlayer;
                        r -= drow;
                        c -= dcol;
                    }
                }
            }
        }
        switchPlayer();
    }

    public boolean isGameOver() {
        return getValidMoves().isEmpty();
    }

    public int getScore() {
        int score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                score += board[i][j];
            }
        }
        return score;
    }

    public int[][] getBoard(int[][] board) {
        for(int i=0 ; i<8 ; i++)
            for(int j=0 ; j<8 ; j++)
                board[i][j] = this.board[i][j];
        return board;
    }
    
    public void setBoard(int[][] board) {
        this.board = board;
    }
}
