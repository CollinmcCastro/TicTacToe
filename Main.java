package tictactoe;
import javax.swing.*;


public class Main
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> 
        {
            TicTacToeGame game = new TicTacToeGame();
            game.setVisible(true);
        });
    } 
}