package tictactoe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame {
    private JButton[][] buttons;
    private char currentPlayer;
    private boolean gameWon;
    private JButton resetButton;
    private JButton closeButton; // New button for closing the game

    public TicTacToeGame() {
        buttons = new JButton[3][3];
        currentPlayer = 'X';
        gameWon = false;

        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeButtons();
        addResetButton(); // New method to add a reset button
        addCloseButton(); // New method to add a close button

        // Initially, make the reset and close buttons invisible
        resetButton.setVisible(false);
        closeButton.setVisible(false);
    }

    private void initializeButtons() {
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = createButton();
                boardPanel.add(buttons[row][col]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);
    }

    private void addResetButton() {
        resetButton = new JButton("Play Again");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        // Use a separate panel with FlowLayout for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addCloseButton() {
        closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeGame();
            }
        });

        // Use the same panel for both buttons
        JPanel buttonPanel = (JPanel) getContentPane().getComponent(1);
        buttonPanel.add(closeButton);
    }

    private JButton createButton() {
        JButton button = new JButton();
        button.setFont(new Font("Arial", Font.PLAIN, 60));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameWon) {
                    JButton clickedButton = (JButton) e.getSource();
                    if (clickedButton.getText().equals("")) {
                        clickedButton.setText(String.valueOf(currentPlayer));
                        clickedButton.setEnabled(false);
                        if (checkWin()) {
                            showGameOverDialog("Player " + currentPlayer + " wins!");
                        } else if (isBoardFull()) {
                            showGameOverDialog("It's a tie!");
                        } else {
                            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                        }
                    }
                }
            }
        });
        return button;
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setEnabled(true);
            }
        }
        currentPlayer = 'X';
        gameWon = false;

        // Make the reset and close buttons invisible again after resetting the game
        resetButton.setVisible(false);
        closeButton.setVisible(false);
    }

    private void showGameOverDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
        gameWon = true;

        // Make the reset and close buttons visible when the game is over
        resetButton.setVisible(true);
        closeButton.setVisible(true);
    }

    private void closeGame() {
        // Close the game
        dispose();
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().equals("") &&
                    buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][2].getText().equals(String.valueOf(currentPlayer)) ||
                    !buttons[0][i].getText().equals("") &&
                            buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[2][i].getText().equals(String.valueOf(currentPlayer)) ||
                    !buttons[0][0].getText().equals("") &&
                            buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[2][2].getText().equals(String.valueOf(currentPlayer)) ||
                    !buttons[0][2].getText().equals("") &&
                            buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> 
        {
            TicTacToeGame game = new TicTacToeGame();
            game.setVisible(true);
        });
    } 

}
