/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
/**
 *
 * @author ProRandom
 */
public class gameButtons extends JButton implements ActionListener {
    private int row ;
    private int col ;
    private GameBoard gameBoard ;
    
    public gameButtons(int row , int col , GameBoard gameBoard){
        this.row = row ;
        this.col = col ;    
        this.gameBoard = gameBoard ; 
        this.addActionListener(this);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
      
        if(this.getBackground() != Color.GRAY)
            return ;
        
        int [] move = {row,col} ;
        gameBoard.makeMove(move);
        
        ArrayList<int[]> moves = new ArrayList<int[]>();
        moves = gameBoard.getValidMoves();
        if(!Grid.isFull() && moves.isEmpty())
            gameBoard.switchPlayer();
        
        else if(Grid.mode == 0 && !Grid.isFull()){
            Grid.Ai_play();
            
            moves = gameBoard.getValidMoves();
            while(!Grid.isFull() && moves.isEmpty()){
                gameBoard.switchPlayer();
                Grid.Ai_play();
                moves = gameBoard.getValidMoves();
            }
        }
        else{
                    if(gameBoard.getCurrentPlayer()== 1)
                        Grid.score_lb.setText("WHITE");
                    else
                        Grid.score_lb.setText("BLACK");
        }
        
        Grid.reset_buttons();
        Grid.printBoard();
        Grid.print_valid_moves();
        
        if(Grid.isFull()){
            Grid.gameOver();
        }
                
         
        
    }
    
    
     
}
