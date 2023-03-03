package fr.upsacaly.bibs.tetris.model.ai;

import java.util.List;

import fr.upsacaly.bibs.tetris.TetrisAction;
import fr.upsacaly.bibs.tetris.TetrisMode;
import fr.upsacaly.bibs.tetris.model.grid.TetrisGridView;
import fr.upsacaly.bibs.tetris.model.tetromino.Tetromino;

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
