package fr.upsaclay.bibs.tetris.model.grid;

import java.io.PrintStream;
import java.util.List;

import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;


/**
 * This class provides a "view" of a tetris grid without allowing for modifications
 * 
 * This is useful to share the grid info (for example with the graphic interface)
 * without breaking encapsulation 
 * 
 * It is "synchronized" because it does not copy the info but keeps a refrence to the 
 * original grid. So if the grid is changes, so is the view
 * 
 * @author Viviane Pons
 *
 */
public class SynchronizedView implements TetrisGridView {
	
	private final TetrisGrid grid;

	public SynchronizedView(TetrisGrid grid) {
		this.grid = grid;
	}

	public int numberOfLines() {
		return grid.numberOfLines();
	}
	

	public int numberOfCols() {
		return grid.numberOfCols();
	}
	

	public TetrisCell gridCell(int i, int j) {
		return grid.gridCell(i, j);
	}
	

	public void printGrid(PrintStream out) {
		grid.printGrid(out);
	}
	

	public boolean hasTetromino() {
		return grid.hasTetromino();
	}

	public Tetromino getTetromino() {
		return grid.getTetromino();
	}
	

	public TetrisCoordinates getCoordinates() {
		return grid.getCoordinates();
	}
	

	public TetrisCell visibleCell(int i, int j) {
		return grid.visibleCell(i, j);
	}
	

	public boolean hasConflicts() {
		return grid.hasConflicts();
	}
	

	public boolean isFull(int lineNumber) {
		return grid.isFull(lineNumber);
	}

	@Override
	public boolean isEmpty(int lineNumber) {
		return grid.isEmpty(lineNumber);
	}

	@Override
	public boolean isEmpty() {
		return grid.isEmpty();
	}

	@Override
	public List<Integer> fullLines() {
		return grid.fullLines();
	}
}
