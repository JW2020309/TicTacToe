package tictactoe;

import java.io.File;
import java.io.PrintStream;

/**
 * TicTacToeWithSaveGame is a subclass of TicTacToe that supports saving the game history.
 * All Exception shall be handled in try-catch, without any remedial actions and prints no message,
 * such that the normal game play is not affected.
 */
public class TicTacToeWithSaveGame extends TicTacToe {
    
    private PrintStream ps;
    
    /**
     * Constructor prepares an object that is from subclass of TicTacToe.
     * This constructor simply redirects System.out to file "ttt.txt"
     * It shall use simple try-catch to silent all PrintStream related Exceptions.
     */
    public TicTacToeWithSaveGame() 
    {
        try {
            System.setOut(new PrintStream(new File("ttt.txt")));
        } catch (Exception e) {
        }
    }
    
    /**
     * GUI Application main thread starts here.
     * Underlying there is another AWT-EventQueue thread running.
     * The GUI system internally will "listen" to user actions and invoke relevant Listener methods.
     * @param args is a String array of command line arguments 
     */
    public static void main(String[] args) {
        new TicTacToeWithSaveGame();
        // After executing the constructor, the program is still running.
        // This is NOT the end of the whole application.
        // Java AWT GUI stuff will take over.
    }        
}
