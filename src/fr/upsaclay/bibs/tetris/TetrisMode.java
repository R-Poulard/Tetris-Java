package fr.upsaclay.bibs.tetris;

/**
 * List of implemented games
 * 
 * the default is to implement a "marathon" tetris
 * 
 * But you can look on the tetris wiki at different game modes and implement
 * extra ones
 * 
 * @author Viviane Pons
 *
 */

public enum TetrisMode {
	/**
	 * Classic tetris marathon with level
	 * 
	 * It starts at level 1 and move levels every 10 lines
	 * 
	 * Basic implementation: 
	 * 
	 * Computes score based on level and single / double / triple following official 
	 * tetris rules
	 * 
	 * Single: 100*level
	 * Double: 300*level
	 * Triple: 500*level
	 * Tetris (4 lines): 800*level
	 * 
	 * Possible extra implementation:
	 * 
	 * Extra points for soft drop : 1 point per down move
	 * Extra points for hard drop : 2 points per down move
	 * 
	 * Extra points for combo : 50 * combo count * level 
	 * (combo count is -1 by default, it increases by one if a merge created at 
	 * least one new line and goes back to -1 for each non winning merge)
	 * 
	 * Extra points for T-spin (see tetris wiki)
	 * 
	 * Extra points for difficult actions (see tetris wiki)
	 * 
	 */
	MARATHON
}
