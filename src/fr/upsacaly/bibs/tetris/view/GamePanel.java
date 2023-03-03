package fr.upsacaly.bibs.tetris.view;

import java.awt.event.ActionListener;
import java.util.List;

import fr.upsacaly.bibs.tetris.model.grid.TetrisGridView;
import fr.upsacaly.bibs.tetris.model.tetromino.Tetromino;

/**
 * A suggested interface for handling the visual interface of the game
 * 
 * This panel is shown when an actual game is going on 
 * 
 * A typical implementation would inherit from JPanel
 * 
 * Note: depending of your implementation choices and if you're doing
 * a really nice visual interface, you might need more methods or to change
 * those methods: add them with proper documentation
 * 
 * @author Viviane Pons
 *
 */
public interface GamePanel extends GameViewPanel {
	
	/**
	 * Sets the number of lines in the game
	 * @param nblines
	 */
	public void setNumberOfLines(int nblines);
	
	/**
	 * Sets the number of cols in the game
	 * @param nbcols
	 */
	public void setNumberOfCols(int nbcols);
	
	/**
	 * sets a TetrisGridView containing all synchronized information
	 * about the grid (current tetromino, cells, etc)
	 * 
	 * @param view
	 */
	public void setGridView(TetrisGridView view);
	
	/**
	 * Adds an action listener to be called at certain time 
	 * intervals
	 * @param listener
	 */
	public void setLoopAction(ActionListener listener);
	
	/**
	 * starts the action loop
	 */
	public void startActionLoop();
	
	/**
	 * pause the action loop
	 */
	public void pauseActionLoop();
	
	/**
	 * set / update the loop delay in milliseconds
	 * @param ms
	 */
	public void setLoopDelay(int ms);
	
	/**
	 * visual interface reaction to certain events in the game
	 * (like making new lines)
	 * @param event a GamePanelEvent
	 * @param attach an attachaed objects (needed for certain events: for example the lines that are destroyed)
	 */
	public void launchGamePanelEvent(GamePanelEvent event, Object attach);
	
	/**
	 * Update the score to be displayed
	 * @param score
	 */
	public void updateScore(int score);
	
	/**
	 * Update the score lines to be displayed
	 * @param lines
	 */
	public void updateScoreLines(int lines);
	
	/**
	 * Update the level to be displayed
	 * @param level
	 */
	public void updateLevel(int level);
	
	/**
	 * Update the held tetromino to be displayed
	 * @param tetromino
	 */
	public void updateHeldTetromino(Tetromino tetromino);
	
	/**
	 * update the list of upcomming tetrominos to be displayed
	 * @param tetrominos
	 */
	public void updateNextTetrominos(List<Tetromino> tetrominos);
	
	/**
	 * update the view
	 */
	public void update();
	
	

}
