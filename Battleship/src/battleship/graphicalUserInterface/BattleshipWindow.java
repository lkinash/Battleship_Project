package battleship.graphicalUserInterface;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import battleship.game.MainGame;


public class BattleshipWindow extends JFrame{

	JPanel mainPanel = new JPanel();
	JLabel mainLabel = new JLabel("Battleship");					//add a number of panels and frames for organization

	Color color = new Color(35,128,235);
	
	public void runGame(){
		
		mainPanel.setLayout( new FlowLayout());	
		mainPanel.setBackground(color);
		mainPanel.add(mainLabel);
		
		add(mainPanel);
	}
	
}
