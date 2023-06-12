/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author Chat-GPT
 */
import java.util.*;

public class AIPlayer {
    private int color;
    private int difficulty;

    public AIPlayer(int color, int difficulty) {
        this.color = color;
        this.difficulty = difficulty;
    }

    public int getColor() {
        return color;
    }

    public int[] getNextMove(GameBoard gameBoard) {
        List<int[]> validMoves = gameBoard.getValidMoves();
        if (validMoves.isEmpty()) {
            return null;
        }
        if (difficulty == 1) {
            // Random move
            Random random = new Random();
            return validMoves.get(random.nextInt(validMoves.size()));
        } else {
            // Minimax algorithm
            int[] bestMove = null;
            int bestScore = Integer.MIN_VALUE;
            for (int[] move : validMoves) {
                GameBoard copy = new GameBoard();
                int [][] board_copy = new int[8][8];
                copy.setBoard(gameBoard.getBoard(board_copy));
                copy.makeMove(move);
                int score = minmax(copy, 3, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                if (score > bestScore) {
                    bestMove = move;
                    bestScore = score;
                }
            }
            return bestMove;
        }
    }

    private int minmax(GameBoard gameBoard, int depth, int alpha, int beta, boolean maximizingPlayer) {
        if (depth == 0 || gameBoard.isGameOver()) {
            return gameBoard.getScore();
        }
        if (maximizingPlayer) {
            int maxScore = Integer.MIN_VALUE;
            for (int[] move : gameBoard.getValidMoves()) {
                GameBoard copy = new GameBoard();
                int [][] board_copy = new int[8][8];
                copy.setBoard(gameBoard.getBoard(board_copy));
                copy.makeMove(move);
                int score = minmax(copy, depth - 1, alpha, beta, false);
                maxScore = Math.max(maxScore, score);
                alpha = Math.max(alpha, score);
                if (beta <= alpha) {
                    break;
                }
            }
            return maxScore;
        } else {
            int minScore = Integer.MAX_VALUE;
            for (int[] move : gameBoard.getValidMoves()) {
                GameBoard copy = new GameBoard();
                int [][] board_copy = new int[8][8];
                copy.setBoard(gameBoard.getBoard(board_copy));
                copy.makeMove(move);
                int score = minmax(copy, depth - 1, alpha, beta, true);
                minScore = Math.min(minScore, score);
                beta = Math.min(beta, score);
                if (beta <= alpha) {
                    break;
                }
            }
            return minScore;
        }
    }

    public int getdifficulty(){
        return difficulty ;
    }
}
