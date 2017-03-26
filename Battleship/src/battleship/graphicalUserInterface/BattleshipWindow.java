package battleship.graphicalUserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import battleship.game.MainGame;
import battleship.service.Constants;
import battleship.service.Status;

public class BattleshipWindow extends JFrame{

	JPanel mainPanel = new JPanel();
	JLabel mainLabel = new JLabel("Battleship");					//add a number of panels and frames for organization

	JPanel topPanel;
	JPanel lowerPanel;
	
	Color color = new Color(35,128,235);
	Color buttonColor = new Color(10,28,235);
	
	Color empty = Color.WHITE;
	Color hit = Color.RED;
	Color boat = Color.GRAY;
	Color miss = Color.CYAN;
	
	MainGame game;
	
	public void runGame(){
		
		game = new MainGame();
		game.build();
		
		mainPanel.setLayout( new BoxLayout(mainPanel, 1));	
		mainPanel.setBackground(color);
		
		topPanel = new JPanel();
		lowerPanel = new JPanel();
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new squareListener());	
    	topPanel.add(startButton);
		
		topPanel.add(mainLabel);
		
		topPanel.setBackground(color);
		
		lowerPanel.add(gamePanel());
		lowerPanel.setBackground(color);
		
		mainPanel.add(topPanel);
		mainPanel.add(lowerPanel);
		
		add(mainPanel);
		
		/*
		while(true){
			
			try {
			    Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
			if(game.run())
				break;
			
			mainPanel.removeAll();
			
			lowerPanel = new JPanel();
			
			lowerPanel.add(gamePanel());
			lowerPanel.setBackground(color);
			
			mainPanel.add(topPanel);
			mainPanel.add(lowerPanel);
			add(mainPanel);
		}
*/
	}
	
	public JPanel gamePanel(){
	
		JPanel gamePanel = new JPanel();
		gamePanel.setBackground(color);
		
	    gamePanel.setLayout(new GridLayout(Constants.GRID_SIZE, Constants.GRID_SIZE));
	      
	    for(int i = 0; i < Constants.GRID_SIZE; i++){
		    for(int j = 0; j < Constants.GRID_SIZE; j++){
		    
		    	JButton button = new JButton();
		    	button.setPreferredSize(new Dimension(40, 40));
		    	
		    	Status status = game.getPlayer1SquareStatus(i, j);
		    	
		    	if(status.equals(Status.BOAT)){
			    	button.setBackground(boat);
		    	}
		    	else if(status.equals(Status.EMPTY)){
			    	button.setBackground(empty);
		    	}
		    	else if(status.equals(Status.MISS)){
			    	button.setBackground(miss);
		    	}
		    	else if(status.equals(Status.HIT)){
			    	button.setBackground(hit);	
		    	}
		    	
		    	button.setOpaque(true);
		    	button.setBorderPainted(false);
		    	gamePanel.add(button);

		    }
	    }
	    
		return gamePanel;
	}
	
	
	class squareListener implements ActionListener{					
		public void actionPerformed(ActionEvent event) 				
		{
			
			
			game.run();
			
			mainPanel.removeAll();
			
			lowerPanel = new JPanel();
			
			lowerPanel.add(gamePanel());
			lowerPanel.setBackground(color);
			
			mainPanel.add(topPanel);
			mainPanel.add(lowerPanel);
			add(mainPanel);
			revalidate();
			
			
	        new Timer(1000, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                
					
					game.run();
					
					mainPanel.removeAll();
					
					lowerPanel = new JPanel();
					
					lowerPanel.add(gamePanel());
					lowerPanel.setBackground(color);
					
					mainPanel.add(topPanel);
					mainPanel.add(lowerPanel);
					add(mainPanel);
					revalidate();
	            	
	            }
	        }).start();
							 
		}
		
	}
	
	public void setWeatherPanel(){
		
		

		return;
	}
	
	
}
