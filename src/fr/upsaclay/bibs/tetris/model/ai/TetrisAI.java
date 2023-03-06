package fr.upsaclay.bibs.tetris.model.ai;

import java.util.List;

import fr.upsaclay.bibs.tetris.TetrisAction;
import fr.upsaclay.bibs.tetris.TetrisMode;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGridView;
import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;

/**
 * This is a suggested AI decision maker interface
 * 
 * You can change it if needed
 * 
 * @author Viviane Pons
 *
 */
public interface TetrisAI {

	public void setTetrisMode(TetrisMode mode);
	
	public TetrisMode getTetrisMode();
	
	public List<TetrisAction> nextActions(TetrisGridView view, Tetromino onhold, List<Tetromino> nexTetrominos);
}
