package fr.upsaclay.bibs.tetris.model.score;

import java.util.List;

import fr.upsaclay.bibs.tetris.TetrisAction;
import fr.upsaclay.bibs.tetris.TetrisMode;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGridView;

/**
 * This interface describes the needed method for computing tetris score
 * 
 * It is used by the game controller to compute the score
 * 
 * At the beginning of the game, a ScoreComputer is created. As the game goes,
 * the game manager "registers" some actions on the ScoreComputer so that the
 * score can be updated.
 * 
 * Basically each TetrisMode would have its own class implementing ScoreComputer
 * 
 * You need to implement the class for MARATHON and return it inside the static method
 * getScoreComputer.
 * 
 * @author Viviane Pons
 *
 */
public interface ScoreComputer {
	
	public static final int STARTING_SCORE = 0;
	public static final int STARTING_LEVEL = 1;
	public static final int STARTING_LINES = 0;
	
	/**
	 * The level of the game
	 * 
	 * In Marathon mode, it starts at 1 and is increased every 10 lines
	 * 
	 * @return the level
	 */
	public int getLevel();
	
	/**
	 * The number of full lines that were realized by the player from
	 * the beginng
	 * 
	 * @return the number of full lines
	 */
	public int getLines();
	
	/**
	 * The score 
	 * @return the score
	 */
	public int getScore();
	
	/**
	 * The combo count (bonus for multiple wins in a row)
	 * 
	 * by default, the combo count is -1.
	 * If the player makes a full line, it is increased to 0, then 1, etc...
	 * Each time the player merge a tetromino to the grid without making a
	 * line, it goes back to -1.
	 * 
	 * @return
	 */
	public int getComboCount();
	
	/**
	 * Update the score and internal state using the information
	 * 
	 * This is called once an action has been decided but before performing the action
	 * 
	 * Example: before a "hard drop", you need to save the tetromino position because
	 * it will be used to computer the score
	 * 
	 * @param action a Tetris action
	 * @param gridView a view of a tetris grid
	 */
	public void registerBeforeAction(TetrisAction action, TetrisGridView gridView);

	/**
	 * Update the score and internal state using the information
	 * 
	 * This is called once an action has been performed before potential merging / packing
	 * 
	 * It must be called after an action has been registered by registerBeforeAction,
	 * otherwise it throws an IllegalStateException
	 * 
	 * Example: after a hard drop, you can compute the number of lines the tetromino
	 * went down and update the score accordingly
	 * 
	 * @param gridView a view of a tetris grid
	 */
	public void registerAfterAction(TetrisGridView gridView);
	
	
	/**
	 * Update the score and internal state using the information
	 * 
	 * This is called after a merge / pack has been performed
	 * 
	 * @param packResult the result of the pack
	 * @param gridView a view of a tetris grid 
	 */
	public void registerMergePack(List<Integer> packResult, TetrisGridView gridView);
	
	public static ScoreComputer getScoreComputer(TetrisMode mode, int initialScore, int initialLevel, int initialLines) {
		switch(mode) {
		case MARATHON:
		case CAVERN:
		case SPACE_CLEANER:
			return new Scoring_marathon(initialScore, initialLevel, initialLines);

		default:
			throw new UnsupportedOperationException("Not implemented");
		
		}
	}
	
	public static ScoreComputer getScoreComputer(TetrisMode mode) {
		return getScoreComputer(mode, STARTING_SCORE, STARTING_LEVEL, STARTING_LINES);
	}
	
}
