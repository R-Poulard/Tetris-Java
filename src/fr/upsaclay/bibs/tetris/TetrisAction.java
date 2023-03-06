package fr.upsaclay.bibs.tetris;

/**
 * List of possible actions during a game
 * 
 * @author Viviane Pons
 *
 */

public enum TetrisAction {
	/**
	 * Move the current tetromino left
	 */
	MOVE_LEFT,
	/**
	 * Move the current tetromino right
	 */
	MOVE_RIGHT,
	/**
	 * Start the "soft drop" (i.e. high speed going down)
	 */
	START_SOFT_DROP,
	/*
	 * End the soft drop
	 */
	END_SOFT_DROP,
	/**
	 * Hard drop the tetromino: falls directly and merge to the grid
	 */
	HARD_DROP,
	/*
	 * Rotate the current tetromino to the right
	 */
	ROTATE_RIGHT,
	/*
	 * Rotate the current tetromino to the left
	 */
	ROTATE_LEFT,
	/*
	 * Goes down one step
	 */
	DOWN,
	/*
	 * "hold" the current tetromino for later use
	 * If a tetromino was already held, it replaces the current tetromino
	 * The new tetromino is placed on top line
	 * It is not possible to rehold a tetromino that was just taken out of the hold box
	 */
	HOLD
}
