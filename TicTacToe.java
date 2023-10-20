package tictactoe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * TicTacToe of two players 'X' and 'O', taking turn to make moves.  At most there are 9 moves.
 * Students are expected to implement all the TTTGamePlayMethods in class TicTacToe.
 * @author pffung
 * 
 *** STUDENTS SHOULD FINISH THIS CLASS PROPERLY TO MAKE IT WORK!
 * 
 * CSCI1130 Java Assignment 6 TicTacToe
 *
 * Remark: Name your class, variables, methods, etc. properly. 
 * You should write comment for your work and follow good styles.
 *
 * I declare that the assignment here submitted is original except for source
 * material explicitly acknowledged, and that the same or closely related
 * material has not been previously submitted for another course. I also
 * acknowledge that I am aware of University policy and regulations on honesty
 * in academic work, and of the disciplinary guidelines and procedures
 * applicable to breaches of such policy and regulations, as contained in the
 * website.
 *
 * University Guideline on Academic Honesty:
 *     http://www.cuhk.edu.hk/policy/academichonesty
 * Faculty of Engineering Guidelines to Academic Honesty:
 *     https://www.erg.cuhk.edu.hk/erg/AcademicHonesty
 *
 * Section     : CSCI1130B
 * Student Name: Jerilyn Widjaja
 * Student ID  : 1155172259
 * Date        : November 29, 2022
 *
 */
public class TicTacToe extends JFrame implements ActionListener, TTTGamePlayMethods {
            
    /**
     * GUI Application main thread starts here.
     * Underlying there is another AWT-EventQueue thread running.
     * The GUI system internally will "listen" to user actions and invoke relevant Listener methods.
     * @param args is a String array of command line arguments 
     */
    public static void main(String[] args) {
        new TicTacToe();
        // After executing the constructor, the program is still running.
        // This is NOT the end of the whole application.
        // Java AWT GUI stuff will take over.
    }    


    
    /** given instance field declarations */
    
    /** tttBoard is a 2D array of TTTButton (that is a subclass of JButton) */
    protected TTTButton[][] tttBoard;
    /** moveCount starts from 0, increment by one on each valid move, max is 9 */
    protected int moveCount;
    /** turn indicates current player, either 'X' or 'O'; first player is always 'X' */
    protected char turn;


    
    /**
     * Constructor prepares an object that is from subclass of JFrame.
     * Given, students need NOT modify.
     */
    public TicTacToe()
    {
        // basic JFrame properties
        setTitle(getClass().getSimpleName());
        setSize(300, 350);
        setLocation(300, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // TicTacToe board user interface
        setLayout(new GridLayout(3, 3));
        tttBoard = new TTTButton[3][3];
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
            {
                String coord = "" + row + "," + col;
                tttBoard[row][col] = new TTTButton();
                tttBoard[row][col].setText(coord);
                tttBoard[row][col].setActionCommand(coord);
                tttBoard[row][col].addActionListener(this);
                add(tttBoard[row][col]);
            }
        
        setVisible(true);
        
        // fields initialization
        moveCount = 0;
        turn = 'X';

        // initial screen output
        System.out.println(getClass().getSimpleName());
        System.out.println("Initial empty board on screen:");
        System.out.println(this);
        System.out.println("Turn: " + turn + '\n');
    }

    /**
     * Implementation of abstract method in ActionListener interface.
     * This method is invoked by the Java GUI system internally (in an AWT thread.)
     * Given code, students need NOT modify.
     * @param event is encapsulating details of the button click
     */
    @Override
    public void actionPerformed(ActionEvent event)
    {
        String coord = event.getActionCommand();
        int row = coord.charAt(0) - '0';
        int col = coord.charAt(2) - '0';
        
        // send message to student's makeMove() method, providing row and col of the move
        makeMove(row, col);
    }
    
    /**
     * Overridden toString() method to represent the game as a String.
     * @return String representation of the board
     * Given, need not modify
     */
    @Override
    public String toString()
    {
        String result = "";
        for (int row = 0; row < 3; row++, result += '\n')
            for (int col = 0; col < 3; col++, result += " ")
                if (tttBoard[row][col].getPiece() == ' ')
                    result += "_";
                else
                    result += tttBoard[row][col].getPiece();
        return result;
    }
    
    /**
     * User of current "turn" clicked on a TTTButton at (row, col), trying to make a move.
     * 0) echo user action on console screen
     * 1) setPiece 'X' or 'O' at the target button on the 2D array tttBoard;
     *    if failed (target occupied,) return without action
     * 2) update moveCount and PRINT(this) TicTacToe game
     * 3) if checkWin() got a winner, print "X Win" or "O Win"
     * 4) if no winner but draw game, print "Draw Game"
     * 5) on end game, disableTTTBoard(); otherwise changeTurn()
     * 6) return;
     * @param row of the clicked button
     * @param col of the clicked button
     */
    @Override
    public void makeMove(int row, int col)
    {
        // Given, echo user action on console screen
        System.out.println((moveCount+1) + ") Player " + turn + " clicked button at (" + row + ", " + col + ")");

        // STUDENT'S WORK HERE
        if (tttBoard[row][col].getPiece() == ' ') {
            tttBoard[row][col].setPiece(turn);
        }
        else {
            return;
        }
        moveCount++;
        System.out.println(this);
        boolean hasWinner = this.checkWin(row,col);
        boolean drawOccurs = this.checkDrawGame();
        if (hasWinner == true) {
            System.out.print(turn + " Won");
            this.disableTTTBoard();
        }
        else if (drawOccurs == true) {
            System.out.print("Draw Game");
            this.disableTTTBoard();
        }
        else {
            this.changeTurn();
        }
        return;
    }
    
    // STUDENT'S WORK HERE
    /**
     * Change turn AND print turn information.
     */
    @Override
    public void changeTurn() {
        if (turn == 'X')
            turn = 'O';
        else {   
            turn = 'X';
        }
        System.out.println("Turn: " + turn + "\n");  
    }
    
    /**
     * Disable all buttons one-by-one in the tttBoard array using the inherited JButton method setEnabled().
     */
    @Override
    public void disableTTTBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                tttBoard[row][col].setEnabled(false);
            }
        }
    }
    
    /**
     * Check draw game condition based on moveCount.
     * @return true on draw game
     */
    @Override
    public boolean checkDrawGame() {
        if (moveCount == 9) {
            boolean hasWin = true;
            for (int row = 0; row < 2; row++) {
                for (int col = 0; col < 2; col++) {
                    hasWin = this.checkWin(row,col); 
                }
            }
            if (hasWin == false) { 
                return true;
            }
        }
        return false;
    }
    
    /**
     * Check winner based on connect-3 condition.
     * @param row of the latest move
     * @param col of the latest move
     * @return true if current turn is a winner
     */
    @Override
    public boolean checkWin(int row, int col){ 
        var a = tttBoard[row][col].getPiece();
        // Check if there's a horizontal straight line
        if (col == 0) {
            var b = tttBoard[row][col + 1].getPiece();
            var c = tttBoard[row][col + 2].getPiece();
            if (a == b && a == c) {
                return true;
            }
        }
        if (col == 1) {
            var b = tttBoard[row][col - 1].getPiece();
            var c = tttBoard[row][col + 1].getPiece();
            if (a == b && a == c) {
                return true;
            }
        }
        if (col == 2) {
            var b = tttBoard[row][col - 1].getPiece();
            var c = tttBoard[row][col - 2].getPiece();
            if (a == b && a == c) {
                return true;
            }
        }
        // Check if there's a vertical straight line
        if (row == 0) {
            var d = tttBoard[row + 1][col].getPiece();
            var e = tttBoard[row + 2][col].getPiece();
            if (a == d && a == e) {
                return true;
            }
        }
        if (row == 1) {
            var d = tttBoard[row - 1][col].getPiece();
            var e = tttBoard[row + 1][col].getPiece();
            if (a == d && a == e) {
                return true;
            }
        }
        if (row == 2) {
            var d = tttBoard[row - 1][col].getPiece();
            var e = tttBoard[row - 2][col].getPiece();
            if (a == d && a == e) {
                return true;
            }
        }
        // Check if there's a diagonal straight line
        if (row == col) {
            var f = tttBoard[0][0].getPiece();
            var g = tttBoard[1][1].getPiece();
            var h = tttBoard[2][2].getPiece();
            if (f == g && f == h) {
                return true;
            }
        }
        // Check if there's an anti-diagonal straight line
        var i = tttBoard[0][2].getPiece();
        var j = tttBoard[1][1].getPiece();
        var k = tttBoard[2][0].getPiece();
        if (i == j && i == k && (i == 'X' || i == 'O')) {
            return true;
        }
        return false;
    }
    
} // end of class TicTacToe
