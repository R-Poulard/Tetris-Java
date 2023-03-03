package fr.upsacaly.bibs.tetris.control.player;

import java.io.PrintStream;

import fr.upsacaly.bibs.tetris.TetrisAction;
import fr.upsacaly.bibs.tetris.model.grid.TetrisGrid;
import fr.upsacaly.bibs.tetris.model.grid.TetrisGridView;
import fr.upsacaly.bibs.tetris.model.score.ScoreComputer;
import fr.upsacaly.bibs.tetris.model.tetromino.Tetromino;
import fr.upsacaly.bibs.tetris.model.tetromino.TetrominoProvider;

/**
 * The controller for an ongoing game
 * 
 * This is typically created by the GameManager
 * 
 * @author Viviane Pons
 *
 */

public interface GamePlayer {
	
	/**
	 * Initialiaze the player 
	 * @param grid a TetrisGris
	 * @param scoreComputer a ScoreComputer
	 * @param provider a TetrominoProvider
	 */
	public void initialize(TetrisGrid grid, ScoreComputer scoreComputer, TetrominoProvider provider);
	
	/**
	 * Return the player type (HUMAN / AI)
	 * @return a PlayerType
	 */
	public PlayerType getType();
	
	/**
	 * Sets a print strem for logging player actions
	 * @param out
	 */
	public void setLogPrintStream(PrintStream out);
	
	/**
	 * Return the current level
	 * @return the levle
	 */
	public int getLevel();
	
	/**
	 * Return the current score
	 * @return the score
	 */
	public int getScore();
	
	/**
	 * Return the line score (the number of completed lines)
	 * @return the lines score
	 */
	public int getLineScore();
	
	/**
	 * Return if the player is active (not on pause, and not over)
	 * @return true if the player is active
	 */
	public boolean isActive();
	
	/**
	 * Starts the player
	 * 
	 * If it is the beginning of the game, it should put a new Tetromino on the grid
	 */
	public void start();
	
	/**
	 * Pause the player
	 */
	public void pause();
	
	/**
	 * Return if the game is over
	 * 
	 * A game is over if adding a new tetromino created a conflict or if the tetromino provider is empty
	 * 
	 * @return true if the game is over
	 */
	public boolean isOver();
	
	/**
	 * Return a grid view of the TetrisGrid
	 * @return a TetrisGridView
	 */
	public TetrisGridView getGridView();
	
	/**
	 * Performs the received TetrisAction
	 * 
	 * If the player is not active (pause or over), it throws an IllegalStateException
	 * 
	 * @param action a TetrisAction
	 * @return true if the action has been successfully performed
	 */
	public boolean performAction(TetrisAction action);
	
	/**
	 * The current tetromino falls down one step. 
	 * 
	 * If the player is not active (pause or over), it throws an IllegalStateException
	 * 
	 * If it cannot fall down: it is merged to the grid. The grid is packed and a new tetromino is put in the game
	 * @return
	 */
	public boolean oneStepFallDown();
	
	/**
	 * Return the held tetromino (saved for later)
	 * @return the held tetromino if it exists or null
	 */
	public Tetromino getHeldTetromino();
	
}
