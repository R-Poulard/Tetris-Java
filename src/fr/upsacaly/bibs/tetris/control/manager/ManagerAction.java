package fr.upsacaly.bibs.tetris.control.manager;

/**
 * List of actions to be run at management time
 * 
 * This is used for communication between the graphic interface
 * and the GameManager
 * 
 * You can implement some of these actions / all of them
 * 
 * You can add more if needed or change the list
 * 
 * @author Viviane Pons
 *
 */

public enum ManagerAction {
	/**
	 * Start the current game (or a new game at begining)
	 */
	START,
	/**
	 * Pause the current game
	 */
	PAUSE,
	/**
	 * Quit the current game
	 */
	QUIT,
	/**
	 * Save the current game
	 */
	SAVE,
	/**
	 * Load a game from a file
	 */
	LOAD,
	/**
	 * Select random tetromino provider
	 */
	RANDOM_TETROMINO,
	/**
	 * Select a tetromino files
	 */
	LOAD_TETROMINO
}
