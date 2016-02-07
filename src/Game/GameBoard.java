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

	public static void main(String[] args) {
		startup(); // Makes(or re-makes) every component.
	}

	public static void makeButton(JButton button, int y, int x) { // Method for
																	// creating
																	// game
																	// squares.
		button = new JButton("-");
		JButton myButton = button; // Creates a local version of the button to
									// be used in the Actionlistener.
		myButton.setFont(font); // Makes the button use comic sans (!)
		// All of these constraint modifications set the physical properties of
		// the square.
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = x;
		c.gridy = y;
		c.ipadx = 200;
		c.ipady = 200;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(myButton, c); // Adds the button to the panel.
		buttonList[listElement] = myButton; // Adds the button to a list of the
											// buttons.
		myButton.addActionListener(new ActionListener() { // For when the button
															// is pressed.
			public void actionPerformed(ActionEvent e) {
				if (turn) { // Alternates turns.
					myButton.setText("X");
				} else {
					myButton.setText("O");
				}
				myButton.setEnabled(false);
				turn = !turn; // Now the other player's turn.
				if (turn) {
					label.setText("Player 2's turn!");
				} else {
					label.setText("Player 1's turn!");
				}
				tieCount++; // When this reaches 9, it is set to -1 and the game
							// is over.
				if (isWinner() || tieCount == -1) { // When the game is over.
					for (int i = 0; i < 9; i++) {
						buttonList[i].setEnabled(false); // Disables every
															// button.
					}
					restart.setEnabled(true); // Allows for a restart.
					if (tieCount == -1 && isWinner() == false) { // For a tie.
						label.setText("It's a tie!");
					} else if (!turn) { // 'X' wins.
						label.setText("Player 1 wins!");
					} else if (turn) { // 'O' wins.
						label.setText("Player 2 wins!");
					}
				}
			}
		});
		// System.out.println(listElement);
		listElement++; // Controls where the button is added to the list.
	}

	public static void makeRestartButton() { // Creates the "restart" button.
												// Cannot be used for other
												// buttons as is.
		restart = new JButton("Restart");
		// Lines below set physical properties.
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		c.ipadx = 50;
		c.ipady = 25;
		// c.insets = new Insets(10, 10, 10, 10);
		restart.addActionListener(new ActionListener() { // For when pressed.
			public void actionPerformed(ActionEvent e) {
				panel.removeAll(); // Clears everything in the panel (including
									// this button).
				frame.remove(panel); // Deletes the now empty panel.
				// Every variable is set to default.
				turn = true;
				listElement = 0;
				index = 0;
				tieCount = 0;

				startup(); // Recreates and adds buttons.
				panel.revalidate(); // Makes sure panel is properly
									// re-implemented.
				panel.repaint();
			}
		});
		panel.add(restart, c); // Adds the restart button to the panel.
		restart.setEnabled(false); // Disabled until the end of the game.

	}

	public static void makeDisplay() { // The turn/win display.
		// Physical properties.
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.ipadx = 2 - 0;
		c.ipady = 200;
		c.gridwidth = 1;
		c.insets = new Insets(10, 10, 10, 10);
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 24)); // Slightly
																	// smaller
																	// font than
																	// the
																	// buttons.
		panel.add(label, c); // Adds the panel
	}

	public static boolean isWinner() { // Tests for a winning line.

		index = 0;
		if (tieCount == 9) {
			tieCount = -1;
		}
		while (index < 3) { // Tests columns.
			if (buttonList[index].getText() == buttonList[index + 3].getText()
					&& buttonList[index + 3].getText() == buttonList[index + 6].getText()
					&& buttonList[index].getText() != "-") {
				buttonList[index].setBackground(Color.RED); // Makes the winning
															// squares red.
				buttonList[index + 3].setBackground(Color.RED);
				buttonList[index + 6].setBackground(Color.RED);
				return true;
			}
			index++; // Tests next column.
		}
		index = 0;
		while (index < 9) { // Tests rows.
			if (buttonList[index].getText() == buttonList[index + 1].getText()
					&& buttonList[index + 1].getText() == buttonList[index + 2].getText()
					&& buttonList[index].getText() != "-") {
				buttonList[index].setBackground(Color.RED); // Makes the winning
															// squares red.
				buttonList[index + 1].setBackground(Color.RED);
				buttonList[index + 2].setBackground(Color.RED);
				return true;
			}
			index += 3; // Tests next row.
		}
		index = 0;
		if (buttonList[0].getText() == buttonList[4].getText() && buttonList[4].getText() == buttonList[8].getText()
				&& buttonList[index].getText() != "-") {// 1st Diagonal
			buttonList[0].setBackground(Color.RED); // Makes the winning squares
													// red.
			buttonList[4].setBackground(Color.RED);
			buttonList[8].setBackground(Color.RED);
			return true;
		}

		if (buttonList[2].getText() == buttonList[4].getText() && buttonList[4].getText() == buttonList[6].getText()
				&& buttonList[2].getText() != "-") { // 2nd Diagonal
			buttonList[2].setBackground(Color.RED); // Makes the winning squares
													// red.
			buttonList[4].setBackground(Color.RED);
			buttonList[6].setBackground(Color.RED);
			return true;
		}
		return false;
	}

	public static void startup() { // Makes every component in the panel.
		// The squares are made.
		makeButton(topLeft, 0, 0);
		makeButton(topMid, 0, 1);
		makeButton(topRight, 0, 2);
		makeButton(midLeft, 1, 0);
		makeButton(center, 1, 1);
		makeButton(midRight, 1, 2);
		makeButton(botLeft, 2, 0);
		makeButton(botMid, 2, 1);
		makeButton(botRight, 2, 2);
		frame.add(panel); // The panel is added to the window.
		frame.setSize(1100, 1100);
		frame.setVisible(true); // Window is visible.
		makeDisplay(); // Makes the label at the bottom.
		makeRestartButton(); // Makes the restart button.
	}
}
