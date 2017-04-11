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

// TODO: Auto-generated Javadoc
/**
 * The Class BattleshipWindow.
 *
 * @author Lindsey
 */
public class BattleshipWindow extends JFrame{

	/** The main panel. */
	JPanel mainPanel = new JPanel();
	
	/** The main label. */
	JLabel mainLabel = new JLabel("                                      Battleship                                    ");					//add a number of panels and frames for organization

	/** The top panel. */
	JPanel topPanel;
	
	/** The lower panel. */
	JPanel lowerPanel; 
	
	/** The timer. */
	Timer timer;
	
	/** The color. */
	Color color = new Color(35,128,235);
	
	/** The button color. */
	Color buttonColor = new Color(10,28,235);
	
	/** The empty. */
	Color empty = Color.WHITE;
	
	/** The hit. */
	Color hit = Color.RED;
	
	/** The boat. */
	Color boat = Color.GRAY;
	
	/** The miss. */
	Color miss = Color.CYAN;
	
	/** The game. */
	MainGame game;
	
	/** The delay. */
	int delay;
	
	/** The win. */
	boolean win;
	
	/**
	 * Run game.
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
	
	/**
	 * Game panel.
	 *
	 * @return the j panel
	 */
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
	
	
	     
	

	/**
	 * The listener interface for receiving random events.
	 * The class that is interested in processing a random
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addrandomListener<code> method. When
	 * the random event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @author Lindsey
	 */
	class randomListener implements ActionListener{					
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
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
	
	/**
	 * The listener interface for receiving randomBoatFinder events.
	 * The class that is interested in processing a randomBoatFinder
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addrandomBoatFinderListener<code> method. When
	 * the randomBoatFinder event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @author Lindsey
	 */
	class randomBoatFinderListener implements ActionListener{					
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
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
	
	/**
	 * The listener interface for receiving randomBoatFinderParity events.
	 * The class that is interested in processing a randomBoatFinderParity
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addrandomBoatFinderParityListener<code> method. When
	 * the randomBoatFinderParity event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @author Lindsey
	 */
	class randomBoatFinderParityListener implements ActionListener{					
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
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
	
	/**
	 * The listener interface for receiving randomSmartFinder events.
	 * The class that is interested in processing a randomSmartFinder
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addrandomSmartFinderListener<code> method. When
	 * the randomSmartFinder event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @author Lindsey
	 */
	class randomSmartFinderListener implements ActionListener{					
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
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
	
	/**
	 * The listener interface for receiving randomSmartFinderParity events.
	 * The class that is interested in processing a randomSmartFinderParity
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addrandomSmartFinderParityListener<code> method. When
	 * the randomSmartFinderParity event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @author Lindsey
	 */
	class randomSmartFinderParityListener implements ActionListener{					
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
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
	
	/**
	 * The listener interface for receiving prob events.
	 * The class that is interested in processing a prob
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addprobListener<code> method. When
	 * the prob event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @author Lindsey
	 */
	class probListener implements ActionListener{					
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
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
	
	/**
	 * The listener interface for receiving probBoatFinder events.
	 * The class that is interested in processing a probBoatFinder
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addprobBoatFinderListener<code> method. When
	 * the probBoatFinder event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @author Lindsey
	 */
	class probBoatFinderListener implements ActionListener{					
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
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
	
	/**
	 * The listener interface for receiving probSmartFinderParity events.
	 * The class that is interested in processing a probSmartFinderParity
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addprobSmartFinderParityListener<code> method. When
	 * the probSmartFinderParity event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @author Lindsey
	 */
	class probSmartFinderParityListener implements ActionListener{					
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
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
	
			
	/**
	 * Runs the game for the type passed in.
	 *
	 * @param type the type
	 */
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
