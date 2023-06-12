/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author ProRandom
 */
import java.util.*;

public class Player {
    private int color;

    public Player(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public int[] getNextMove(GameBoard gameBoard) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<int[]> validMoves = gameBoard.getValidMoves();
        if (validMoves.isEmpty()) {
            return null;
        }
        System.out.println("Valid moves:");
        for (int i = 0; i < validMoves.size(); i++) {
            System.out.println(i + ": " + Arrays.toString(validMoves.get(i)));
        }
        System.out.print("Enter move number: ");
        int moveNumber = scanner.nextInt();
        return validMoves.get(moveNumber);
    }
}
