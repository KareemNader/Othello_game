package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *
 * @author ProRandom
 */
public class Grid extends JFrame {
   
    private JPanel p0 , p1 , p2; 
    private static gameButtons [][] buttons ;
    private static GameBoard gameBoard ;
    public static AIPlayer Ai_agent ;
    public static JLabel score_lb ;
    public static JLabel score_word_lb ;
    public static JButton restart_btn;
    public static JButton home_btn;
    public static int mode ; 
    public static ImageIcon white_icon ;
    public static ImageIcon black_icon ;
    public static Grid grid; 
    private String type ;
    
    public Grid (int difficulty , int mode ){
        super();
        if (difficulty == 1) type = "Easy" ;
        else type = "Hard" ;
        white_icon = new ImageIcon(getClass().getResource("/icons/white70.jpg"));
        black_icon = new ImageIcon(getClass().getResource("/icons/blackk.jpg"));
        this.mode = mode ;
        p0 = new JPanel();
        p1 = new JPanel(); 
        p2 = new JPanel();
        buttons = new gameButtons[8][8] ;
        gameBoard = new GameBoard();
        Ai_agent = new AIPlayer(1, difficulty);
        score_lb = new JLabel();
        score_word_lb = new JLabel();
        restart_btn = new JButton();
        home_btn = new JButton();
        
        for(int r=0 ; r<8 ; r++){
            for(int c=0 ; c<8 ; c++){
                buttons[r][c] = new gameButtons(r,c,gameBoard);
                p1.add(buttons[r][c]);
            }
        }
        setup() ;
        
        home_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // perform function here
                home_btn_fn();
            }
        });
        
        restart_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // perform function here
                restart_btn_fn();
            }
        });
    }
    
    public void setup(){
        
        reset_buttons();
        printBoard();
        print_valid_moves();
        
        score_lb.setText("WHITE");
        score_word_lb.setText("PLAYER");
        
        Font font = new Font("Arial" , Font.BOLD , 30 ) ;
        score_word_lb.setFont(font);
        score_word_lb.setHorizontalAlignment(SwingConstants.CENTER);
        score_lb.setFont(font);
        score_lb.setHorizontalAlignment(SwingConstants.CENTER);
         
//        restart_btn.setSize(300, 50);
//        home_btn.setSize(300, 50);
        restart_btn.setText("Restart");
        home_btn.setText("Home");
        
        p0.setLayout(new BoxLayout(p0, BoxLayout.Y_AXIS));
        p2.setLayout(new GridLayout(2,2));
        p2.add(score_word_lb);
        p2.add(score_lb);
        p2.add(restart_btn);
        p2.add(home_btn);
        p1.setLayout(new GridLayout(8,8));
        p0.add(p1);
        p0.add(p2);
        this.add(p0);
        p1.setPreferredSize(new Dimension(600,600));
        p2.setPreferredSize(new Dimension(600,100));
        p0.setPreferredSize(new Dimension(600,700));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("game (" + type + ")");
        this.pack();
        p1.setBackground(new Color(240, 240, 242));
        p2.setBackground(new Color(240, 240, 242));
        setVisible(true); 
    }
   
     public static void printBoard(){
        for(int i=0 ; i<8 ; i++){
            for(int j=0 ; j<8 ; j++){
                if(gameBoard.board[i][j]==1)
                  buttons[i][j].setIcon(white_icon);
                else if(gameBoard.board[i][j]== -1)
                  buttons[i][j].setIcon(black_icon);
            }
        }
    }
    
    public static void  Ai_play(){
        int [] move = Ai_agent.getNextMove(gameBoard);
        gameBoard.makeMove(move);
    }
    
    public static void print_valid_moves(){
        ArrayList<int[]> validMoves = gameBoard.getValidMoves();
        for(int[] move : validMoves){
            buttons[move[0]][move[1]].setBackground(Color.GRAY);
        }
    }
    
    public static void reset_buttons(){
        for(int i=0 ; i<8 ; i++)
            for(int j=0 ; j<8 ; j++)
                buttons[i][j].setBackground(Color.WHITE);
    }
    
    public static boolean isFull(){
        for(int i=0 ; i<8 ; i++)
            for(int j=0 ; j<8 ; j++)
                if(gameBoard.board[i][j] == 0 )
                    return false ;
        return true ; 
    }
    
   public void home_btn_fn(){
       new HomePage().setVisible(true);
       this.dispose();
   }
   
   public void restart_btn_fn(){
        Grid g = new Grid(this.Ai_agent.getdifficulty(),this.mode ) ;
        g.grid = grid ;
        this.dispose();
   }
    
   public static void gameOver(){
       if(gameBoard.getScore()>=0)
                JOptionPane.showMessageDialog(grid, "WHITE PLAYER WON", "RESULT", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(grid, "BLACK PLAYER WON", "RESULT", JOptionPane.INFORMATION_MESSAGE);
   }
   
}
 public static void printBoard(){
        
        for(int i=0 ; i<8 ; i++){
            for(int j=0 ; j<8 ; j++){
                if(gameBoard.board[i][j]==1)
                  buttons[i][j].setIcon(white_icon);
                else if(gameBoard.board[i][j]== -1)
                  buttons[i][j].setIcon(black_icon);
            }
        }
    }
    
    public static void  Ai_play(){
        int [] move = Ai_agent.getNextMove(gameBoard);
        gameBoard.makeMove(move);
    }
    
    public static void print_valid_moves(){
        ArrayList<int[]> validMoves = gameBoard.getValidMoves();
        for(int[] move : validMoves){
            buttons[move[0]][move[1]].setBackground(Color.GRAY);
        }
    }
    
    public static void reset_buttons(){
        for(int i=0 ; i<8 ; i++)
            for(int j=0 ; j<8 ; j++)
                buttons[i][j].setBackground(Color.WHITE);
    }
    
    public static boolean isFull(){
        for(int i=0 ; i<8 ; i++)
            for(int j=0 ; j<8 ; j++)
                if(gameBoard.board[i][j] == 0 )
                    return false ;
        return true ; 
    }
    
   public void home_btn_fn(){
       new HomePage().setVisible(true);
       this.dispose();
   }
   
   public void restart_btn_fn(){
        Grid g = new Grid(this.Ai_agent.getdifficulty(),this.mode ) ;
        g.grid = grid ;
        this.dispose();
   }
    
   public static void gameOver(){
       if(gameBoard.getScore()>=0)
                JOptionPane.showMessageDialog(grid, "WHITE PLAYER WON", "RESULT", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(grid, "BLACK PLAYER WON", "RESULT", JOptionPane.INFORMATION_MESSAGE);
   }
   
}
   
