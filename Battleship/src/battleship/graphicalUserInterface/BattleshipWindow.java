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
import battleship.service.SimType;
import battleship.service.Status;

/**
 * @author Lindsey
 *
 */
public class BattleshipWindow extends JFrame{

	JPanel mainPanel = new JPanel();
	JLabel mainLabel = new JLabel("                                      Battleship                                    ");					//add a number of panels and frames for organization

	JPanel topPanel;
	JPanel lowerPanel; 
	
	Timer timer;
	
	Color color = new Color(35,128,235);
	Color buttonColor = new Color(10,28,235);
	
	Color empty = Color.WHITE;
	Color hit = Color.RED;
	Color boat = Color.GRAY;
	Color miss = Color.CYAN;
	
	MainGame game;
	
	int delay;
	
	boolean win;
	
	/**
	 * 
	 */
	public void runGame(){
		
		delay = 750;
		
		game = new MainGame();
		game.build(false);
		
		win = false;
		
		mainPanel.setLayout( new BoxLayout(mainPanel, 1));	
		mainPanel.setBackground(color);
		
		topPanel = new JPanel();
		lowerPanel = new JPanel();


		JButton randomBasicButton = new JButton("Random Basic Play");
		randomBasicButton.addActionListener(new randomListener());	
    	topPanel.add(randomBasicButton);
    	
		JButton randomBoatFinderButton = new JButton("Random Boat Finder Play");
		randomBoatFinderButton.addActionListener(new randomBoatFinderListener());	
    	topPanel.add(randomBoatFinderButton);
    	
		JButton randomBoatFinderParityButton = new JButton("Random Boat Finder Parity Play");
		randomBoatFinderParityButton.addActionListener(new randomBoatFinderParityListener());	
    	topPanel.add(randomBoatFinderParityButton);
    	
		JButton randomSmartFinderButton = new JButton("Random Smart Finder Play");
		randomSmartFinderButton.addActionListener(new randomSmartFinderListener());	
    	topPanel.add(randomSmartFinderButton);
    	
		JButton randomSmartFinderParityButton = new JButton("Random Smart Finder Parity Play");
		randomSmartFinderParityButton.addActionListener(new randomSmartFinderParityListener());	
    	topPanel.add(randomSmartFinderParityButton);
    	

		JButton probBasicButton = new JButton("Probability Basic Play");
		probBasicButton.addActionListener(new probListener());	
    	topPanel.add(probBasicButton);
    	
		JButton probBoatFinderButton = new JButton("Probability Boat Finder Play");
		probBoatFinderButton.addActionListener(new probBoatFinderListener());	
    	topPanel.add(probBoatFinderButton);
    	
		JButton probBoatFinderParityButton = new JButton("Probability Boat Smart Finder Parity Play");
		probBoatFinderParityButton.addActionListener(new probSmartFinderParityListener());	
    	topPanel.add(probBoatFinderParityButton);
    	
    	
		topPanel.add(mainLabel);
		
		topPanel.setBackground(color);
		
		lowerPanel.add(gamePanel());
		lowerPanel.setBackground(color);
		
		mainPanel.add(topPanel);
		mainPanel.add(lowerPanel);
		
		add(mainPanel);
		
		
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
	
	
	     
	

	class randomListener implements ActionListener{					
		public void actionPerformed(ActionEvent event) 				
		{
			game.build(true);
			win = false;
			
			mainPanel.removeAll();
			
			lowerPanel = new JPanel();
			
			lowerPanel.add(gamePanel());
			lowerPanel.setBackground(color);
			
			mainPanel.add(topPanel);
			mainPanel.add(lowerPanel);
			add(mainPanel);
			revalidate();
			
	        new Timer(delay, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                
	            	if(!win)
	            		runGamePlay(SimType.RandomBasicPlay);
			
	            }
	        }).start();
		
	        
		}
		
	}
	
	class randomBoatFinderListener implements ActionListener{					
		public void actionPerformed(ActionEvent event) 				
		{
			game.build(true);
			win = false;
			
			mainPanel.removeAll();
			
			lowerPanel = new JPanel();
			
			lowerPanel.add(gamePanel());
			lowerPanel.setBackground(color);
			
			mainPanel.add(topPanel);
			mainPanel.add(lowerPanel);
			add(mainPanel);
			revalidate();
			
	        new Timer(delay, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                
	            	if(!win)
	            		runGamePlay(SimType.RandomBasicWithBoatFinder);
			
	            }
	        }).start();
		
	        
		}
		
	}
	
	class randomBoatFinderParityListener implements ActionListener{					
		public void actionPerformed(ActionEvent event) 				
		{
			game.build(true);
			win = false;
			
			mainPanel.removeAll();
			
			lowerPanel = new JPanel();
			
			lowerPanel.add(gamePanel());
			lowerPanel.setBackground(color);
			
			mainPanel.add(topPanel);
			mainPanel.add(lowerPanel);
			add(mainPanel);
			revalidate();
			
	        new Timer(delay, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                
	            	if(!win)
	            		runGamePlay(SimType.RandomBasicWithBoatFinderParity);
			
	            }
	        }).start();
		
	        
		}
		
	}
	
	class randomSmartFinderListener implements ActionListener{					
		public void actionPerformed(ActionEvent event) 				
		{
			game.build(true);
			win = false;
			
			mainPanel.removeAll();
			
			lowerPanel = new JPanel();
			
			lowerPanel.add(gamePanel());
			lowerPanel.setBackground(color);
			
			mainPanel.add(topPanel);
			mainPanel.add(lowerPanel);
			add(mainPanel);
			revalidate();
			
	        new Timer(delay, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                
	            	if(!win)
	            		runGamePlay(SimType.RandomBasicWithSmartBoatFinder);
			
	            }
	        }).start();
		
	        
		}
		
	}
	
	class randomSmartFinderParityListener implements ActionListener{					
		public void actionPerformed(ActionEvent event) 				
		{
			
			game.build(true);
			win = false;
			
			mainPanel.removeAll();
			
			lowerPanel = new JPanel();
			
			lowerPanel.add(gamePanel());
			lowerPanel.setBackground(color);
			
			mainPanel.add(topPanel);
			mainPanel.add(lowerPanel);
			add(mainPanel);
			revalidate();
			
	        new Timer(delay, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                
	            	if(!win)
	            		runGamePlay(SimType.RandomBasicWithSmartBoatFinderParity);
			
	            }
	        }).start();
		
	        
		}
		
	}
	
	class probListener implements ActionListener{					
		public void actionPerformed(ActionEvent event) 				
		{
			
			game.build(false);
			win = false;
			
			mainPanel.removeAll();
			
			lowerPanel = new JPanel();
			
			lowerPanel.add(gamePanel());
			lowerPanel.setBackground(color);
			
			mainPanel.add(topPanel);
			mainPanel.add(lowerPanel);
			add(mainPanel);
			revalidate();
			
	        new Timer(delay, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                
	            	if(!win)
	            		runGamePlay(SimType.ProbBasicPlay);
			
	            }
	        }).start();
		
	        
		}
		
	}
	
	class probBoatFinderListener implements ActionListener{					
		public void actionPerformed(ActionEvent event) 				
		{
			
			game.build(false);
			win = false;
			
			mainPanel.removeAll();
			
			lowerPanel = new JPanel();
			
			lowerPanel.add(gamePanel());
			lowerPanel.setBackground(color);
			
			mainPanel.add(topPanel);
			mainPanel.add(lowerPanel);
			add(mainPanel);
			revalidate();
			
	        new Timer(delay, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                
	            	if(!win)
	            		runGamePlay(SimType.ProbBasicWithBoatFinder);
			
	            }
	        }).start();
		
	        
		}
		
	}
	
	class probSmartFinderParityListener implements ActionListener{					
		public void actionPerformed(ActionEvent event) 				
		{
			
			game.build(false);
			win = false;
			
			mainPanel.removeAll();
			
			lowerPanel = new JPanel();
			
			lowerPanel.add(gamePanel());
			lowerPanel.setBackground(color);
			
			mainPanel.add(topPanel);
			mainPanel.add(lowerPanel);
			add(mainPanel);
			revalidate();
			
	        new Timer(delay, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                
	            	if(!win)
	            		runGamePlay(SimType.ProbBasicWithSmartBoatFinder);
			
	            }
	        }).start();
		
	        
		}
		
	}
	
			
	public void runGamePlay(SimType type){
		
	
			if(game.run(type))
				win = true;
			
			mainPanel.removeAll();
			
			lowerPanel = new JPanel();
			
			lowerPanel.add(gamePanel());
			lowerPanel.setBackground(color);
			
			mainPanel.add(topPanel);
			mainPanel.add(lowerPanel);
			add(mainPanel);
			revalidate();
        	
	}
	       
	
	
}
