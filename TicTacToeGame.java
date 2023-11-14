package tictactoe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TicTacToeGame extends JFrame
{
    private JButton[][] buttons;
    private char currentPlayer;
    private boolean gameWon;

    public TicTacToeGame()
    {
        buttons = new JButton[3][3];
        currentPlayer = 'X';
        gameWon = false;

        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        initializeButtons();
    }

    private void initializeButtons()
    {
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                buttons[row][col] = createButton();
                add(buttons[row][col]);
            }
        }
    }

    private JButton createButton()
    {
        JButton button = new JButton();
        button.setFont(new Font("Arial", Font.PLAIN, 60));
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!gameWon)
                {
                    JButton clickedButton = (JButton) e.getSource();
                    if (clickedButton.getText().equals(""))
                    {
                        clickedButton.setText(String.valueOf(currentPlayer));
                        clickedButton.setEnabled(false);
                        if (checkWin())
                        {
                            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + "wins!");
                            gameWon = true;
                        }
                        else if (isBoardFull())
                        {
                            JOptionPane.showMessageDialog(null, "It's a tie!");
                            gameWon = true;
                        }
                        else
                        {
                            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                        }
                    }
                }
            }
        });
        return button;
    }

    private boolean checkWin()
    {
        for (int i = 0; i < 3; i++)
        {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[i][2].getText().equals(String.valueOf(currentPlayer)) ||
            buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][i].getText().equals(String.valueOf(currentPlayer)) ||
            buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][2].getText().equals(String.valueOf(currentPlayer)) ||
            buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }





        }
        return false;
    }
    private boolean isBoardFull()
    {
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                if (buttons[row][col].getText().equals(""))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
