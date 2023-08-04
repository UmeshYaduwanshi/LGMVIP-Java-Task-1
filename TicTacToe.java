import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons;
    private String[] board;
    private String turn;
    private JButton resetButton;

    public TicTacToe() {
        buttons = new JButton[9];
        board = new String[9];
        turn = "X";

        setLayout(new GridLayout(4, 3));
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            buttons[i].addActionListener(this);
            add(buttons[i]);
            board[i] = String.valueOf(i + 1);
        }

        resetButton = new JButton("Reset Game");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 20));
        resetButton.addActionListener(this);
        add(resetButton);
    }

    private String checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = null;

            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }

            if (line.equals("XXX")) {
                return "X";
            } else if (line.equals("OOO")) {
                return "O";
            }
        }

        for (int a = 0; a < 9; a++) {
            if (board[a].equals(String.valueOf(a + 1))) {
                break;
            } else if (a == 8) {
                return "draw";
            }
        }

        return null;
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == resetButton) {
            // Reset the board and buttons
            for (int i = 0; i < 9; i++) {
                board[i] = String.valueOf(i + 1);
                buttons[i].setText("");
                buttons[i].setEnabled(true);
            }

            // Reset the turn
            turn = "X";
            return;
        }

        JButton buttonClicked = (JButton) source;
        int index = -1;

        for (int i = 0; i < 9; i++) {
            if (buttonClicked == buttons[i]) {
                index = i;
                break;
            }
        }

        if (board[index].equals(String.valueOf(index + 1))) {
            board[index] = turn;
            buttonClicked.setText(turn);

            if (turn.equals("X")) {
                turn = "O";
            } else {
                turn = "X";
            }

            String result = checkWinner();
            if (result != null) {
                if (result.equalsIgnoreCase("draw")) {
                    JOptionPane.showMessageDialog(this, "It's a draw!");
                } else {
                    JOptionPane.showMessageDialog(this, "Congratulations! " + result + " wins!");
                }
                for (int i = 0; i < 9; i++) {
                    buttons[i].setEnabled(false);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe ttt = new TicTacToe();
            ttt.setVisible(true);
        });
    }
}
