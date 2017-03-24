package battleship.graphicalUserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import battleship.game.MainGame;
import battleship.service.Constants;


public class BattleshipWindow extends JFrame{

	JPanel mainPanel = new JPanel();
	JLabel mainLabel = new JLabel("Battleship");					//add a number of panels and frames for organization

	Color color = new Color(35,128,235);
	Color buttonColor = new Color(10,28,235);
	
	public void runGame(){
		
		mainPanel.setLayout( new BoxLayout(mainPanel, 1));	
		mainPanel.setBackground(color);
		
		JPanel topPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		
		topPanel.add(mainLabel);
		topPanel.setBackground(color);
		
		lowerPanel.add(gamePanel());
		lowerPanel.setBackground(color);
		
		mainPanel.add(topPanel);
		mainPanel.add(lowerPanel);
		
		add(mainPanel);
		
		MainGame game = new MainGame();
		game.build();
		game.run();
		
	}
	
	public JPanel gamePanel(){
	
		JPanel gamePanel = new JPanel();
		gamePanel.setBackground(color);
		
	    gamePanel.setLayout(new GridLayout(Constants.GRID_SIZE, Constants.GRID_SIZE));
	      
	    for(int i = 0; i < Constants.GRID_SIZE; i++){
		    for(int j = 0; j < Constants.GRID_SIZE; j++){
		    
		    	JButton button = new JButton();
		    	button.setPreferredSize(new Dimension(40, 40));
		    	button.setBackground(buttonColor);
		    	button.setOpaque(true);
		    	gamePanel.add(button);
		    	
		    }
	    }
	    
		return gamePanel;
	}
	
	
}
