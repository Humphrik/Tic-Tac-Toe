package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameBoard {
	static JFrame frame = new JFrame("Tic-Tac-Toe");
	static JPanel panel = new JPanel(new GridBagLayout());
	static Label label = new Label("Player 1's turn");
	static GridBagConstraints c = new GridBagConstraints();
	static JButton topLeft, topMid, topRight, midLeft, center, midRight, botLeft, botMid, botRight, restart;
	static JButton[] buttonList = new JButton[9];
	static int listElement, index, tieCount = 0;
	static Font font = new Font("Comic Sans MS", Font.BOLD, 36);
	static boolean turn = true;
	// static boolean isPressed = false;

	public static void main(String[] args) {
		startup();
	}

	public static void makeButton(JButton button, int y, int x) {
		button = new JButton("-");
		JButton myButton = button;
		myButton.setFont(font);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = x;
		c.gridy = y;
		c.ipadx = 200;
		c.ipady = 200;
		c.insets = new Insets(10, 10, 10, 10);
		myButton.setBackground(null);
		panel.add(myButton, c);
		button.setEnabled(true);
		buttonList[listElement] = myButton;
		myButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (turn) {
					myButton.setText("X");
				} else {
					myButton.setText("O");
				}
				myButton.setEnabled(false);
				turn = !turn;
				if (turn) {
					label.setText("Player 2's turn!");
				} else {
					label.setText("Player 1's turn!");
				}
				tieCount++;
				if (isWinner() || tieCount == -1) {
					for (int i = 0; i < 9; i++) {
						buttonList[i].setEnabled(false);
					}
					restart.setEnabled(true);
					if (tieCount == -1 && isWinner() == false) {
						label.setText("It's a tie!");
					} else if (!turn) {
						label.setText("Player 1 wins!");
					} else if (turn)
						label.setText("Player 2 wins!");
				}
			}
		});
		// System.out.println(listElement);
		listElement++;
	}

	public static void makeRestartButton() {
		restart = new JButton("Restart");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		c.ipadx = 100;
		c.ipady = 100;
		c.insets = new Insets(10, 10, 10, 10);
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				frame.remove(panel);
				turn = true;
				listElement = 0;
				index = 0;
				tieCount = 0;
				startup();
				panel.revalidate();
				panel.repaint();
			}
		});
		panel.add(restart);
		restart.setEnabled(false);

	}

	public static void makeDisplay() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.ipadx = 2 - 0;
		c.ipady = 200;
		c.gridwidth = 1;
		c.insets = new Insets(10, 10, 10, 10);
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		panel.add(label, c);
	}

	public static boolean isWinner() {

		index = 0;
		if (tieCount == 9) {
			tieCount = -1;
		}
		while (index < 3) { // Tests columns.
			if (buttonList[index].getText() == buttonList[index + 3].getText()
					&& buttonList[index + 3].getText() == buttonList[index + 6].getText()
					&& buttonList[index].getText() != "-") {
				buttonList[index].setBackground(Color.RED);
				buttonList[index + 3].setBackground(Color.RED);
				buttonList[index + 6].setBackground(Color.RED);
				return true;
			}
			index++;
		}
		index = 0;
		while (index < 9) { // Tests rows.
			if (buttonList[index].getText() == buttonList[index + 1].getText()
					&& buttonList[index + 1].getText() == buttonList[index + 2].getText()
					&& buttonList[index].getText() != "-") {
				buttonList[index].setBackground(Color.RED);
				buttonList[index + 1].setBackground(Color.RED);
				buttonList[index + 2].setBackground(Color.RED);
				return true;
			}
			index += 3;
		}
		index = 0;
		if (buttonList[0].getText() == buttonList[4].getText() && buttonList[4].getText() == buttonList[8].getText()
				&& buttonList[index].getText() != "-") {// 1st Diagonal
			buttonList[0].setBackground(Color.RED);
			buttonList[4].setBackground(Color.RED);
			buttonList[8].setBackground(Color.RED);
			return true;
		}

		if (buttonList[2].getText() == buttonList[4].getText() && buttonList[4].getText() == buttonList[6].getText()
				&& buttonList[2].getText() != "-") { // 2nd Diagonal
			buttonList[2].setBackground(Color.RED);
			buttonList[4].setBackground(Color.RED);
			buttonList[6].setBackground(Color.RED);
			return true;
		}
		return false;
	}

	public static void startup() {
		makeButton(topLeft, 0, 0);
		makeButton(topMid, 0, 1);
		makeButton(topRight, 0, 2);
		makeButton(midLeft, 1, 0);
		makeButton(center, 1, 1);
		makeButton(midRight, 1, 2);
		makeButton(botLeft, 2, 0);
		makeButton(botMid, 2, 1);
		makeButton(botRight, 2, 2);
		frame.add(panel);
		frame.setSize(1100, 1100);
		frame.setVisible(true);
		makeDisplay();
		makeRestartButton();
	}
}
