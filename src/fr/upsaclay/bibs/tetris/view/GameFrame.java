package fr.upsaclay.bibs.tetris.view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import fr.upsaclay.bibs.tetris.control.manager.ManagerAction;
import fr.upsaclay.bibs.tetris.model.grid.TetrisCell;

/**
 * A suggested interface to describe the main frame of the visual interface
 * 
 * A typical implementation would inherit from JFrame
 * 
 * this are all the methods needed to communicate with the game manager
 * 
 * Note: depending of your implementation choices and if you're doing
 * a really nice visual interface, you might need more methods or to change
 * those methods: add them with proper documentation
 * 
 * @author Viviane Pons
 *
 */
public interface GameFrame extends GameViewPanel {
	
	/**
	 * Default number of pixel to drow a single tetromino cell
	 */
	public static final int PIXELS_PER_CELL = 30;
	
	/**
	 * Return the panel handling the game action
	 * 
	 * @return a GamePanel
	 */
	public GamePanel getGamePanel();
	
	/**
	 * Creates all the manager components needed for game management
	 */
	public void createManagerComponents();
	
	/**
	 * All manager components are listened to by a single action listenner,
	 * typically the game manager
	 * 
	 * the action depends on which component was trigered
	 * @param listener
	 */
	public void attachManagerActionListener(ActionListener listener);
	
	/**
	 * Add a key listener (for the keyboard actions) to the 
	 * frame
	 * 
	 * (typically called when the player starts a game)
	 * 
	 * @param listener
	 */
	public void startGameKeyListener(KeyListener listener);
	
	/**
	 * Remove the sent listener from the frame
	 * 
	 * (for example, at the end of a game)
	 * 
	 * @param listener
	 */
	public void stopGameKeyListener(KeyListener listener);
	
	/**
	 * Shows an alert error message on screen
	 * @param message
	 */
	public void showErrorMessage(String message);
	
	/**
	 * Puts back the default settings for a given manager action
	 * 
	 * (for example for menus and radio buttons)
	 * 
	 * @param action
	 */
	public void setDefaultSetting(ManagerAction action);
	

	
}
