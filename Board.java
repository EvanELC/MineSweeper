package minesweeper;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Board implements ActionListener {

	JFrame frame = new JFrame("Minesweeper");
	JButton reset = new JButton("Reset");
	JButton[][] buttons = new JButton[12][12];
	int[][] counts = new int[12][12];
	Container grid = new Container();
	final int MINE = 10;

	public Board() {
		frame.setSize(1000, 700);
		frame.setLayout(new BorderLayout());
		frame.add(reset, BorderLayout.NORTH);
		reset.addActionListener(this);

		grid.setLayout(new GridLayout(12, 12));
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].addActionListener(this);
				grid.add(buttons[i][j]);
			}
		}
		frame.add(grid, BorderLayout.CENTER);
		createMines();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void createMines() {
		counts = new int[12][12];
		int minecount=0;
		while(minecount<=30) {

			counts[((int)Math.random()*10)+1][((int)Math.random()*10)+1] = MINE;
		}

		
		for (int i = 0; i < counts.length; i++) {
			for (int j = 0; j < counts[0].length; j++) {
				if (counts[i][j] != MINE) {
					int neighborCount = 0;
					if (i > 0 && j > 0 && counts[i - 1][j - 1] == MINE) {// up left
						neighborCount++;
					}
					if (j > 0 && counts[i][j - 1] == MINE) {// up
						neighborCount++;
					}
					if (i < counts.length - 1 && j < counts.length - 1 && counts[i + 1][j + 1] == MINE) {// down right
						neighborCount++;
					}
					// if (i < 0 && j < 0 && counts[i-1][j-1] == MINE) {//up right
					// neighborCount++;
					// }
					// if (i > 0 && counts[i][j-1] == MINE) {//down
					// neighborCount++;
					// }
					// if (i < counts.length -1 && j < counts.length-1 && counts[i+1][j+1] == MINE)
					// {//down left
					//
					// }
					counts[i][j] = neighborCount;
				}
			}
		}
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				buttons[i][j].setText(counts[i][j] + "");
			}
		}
	}

	public static void main(String[] args) {
		new Board();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(reset)) {
			// reset the board
		} else {
			for (int i = 0; i < buttons.length; i++) {
				for (int j = 0; j < buttons[0].length; j++) {
					if (event.getSource().equals(buttons[i][j])) {
						buttons[i][j].setText(counts[i][j] + "");
					}
				}
			}
		}
	}
}
