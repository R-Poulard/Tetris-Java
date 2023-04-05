package fr.upsaclay.bibs.tetris.model.grid;

import java.util.List;

import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;

/**
 * This interface represents a Tetris Grid made of Tetris cells
 * with an optional tetromino placed at a certain position on the grid
 * 
 * It lists all the needed computations needed for a Tetris game.
 * This does not compute score or do any "game check" (valid move 
 * for examples). This only the logic of the grid.
 * 
 * It will be used by the game manager to conduct an actual game.
 * 
 * The lines are indexed staring at 0 from the top
 * 
 * We provide extensive testing for this interface:
 * you need to write a class that implemets the interface as well 
 * as the static method getEmptyGrid which creates an instance of the 
 * interface.
 * 
 * Note that some of the methods are inherited from TetrisGridView
 * 
 * @author Viviane Pons
 *
 */

public interface TetrisGrid extends TetrisGridView {
	
	/**
	 * Return a view of self that cannot be used to modify the grid
	 * @return
	 */
	public SynchronizedView getView();

	/**
	 * Copies the values of cells into the grid
	 * 
	 * Throws an IllegalArgumentException if the dimensions do not fit
	 * 
	 * @param cells a double array of cells
	 */
	public void initiateCells(TetrisCell[][] cells);
	
	
	/**
	 * Sets a tetromino to the grid
	 * @param tetromino a Tetromino
	 */
	public void setTetromino(Tetromino tetromino);
	
	/**
	 * Sets the tetromino coordinates
	 * @param coordinates some TetrisCoordinates for the tetromino
	 */
	public void setCoordinates(TetrisCoordinates coordinates);
	
	
	/**
	 * Place the current tetromino in the top middle of the grid:
	 * line -- 0
	 * col -- (numberOfCols() - tetromino box size)/2
	 */
	public void setAtStartingCoordinates();
	
	/**
	 * Permet de poser un bloc à la position i et j supposé vide
	 * return true si l'action c'est faite correctement
	 */
	public boolean setBlock(int i,int j,TetrisCell type);
	/**
	 * Try to move the current tetromino by the given direction
	 * 
	 *  The direction is given as a tetris coordinates that should be added to the current coordinate.
	 *  For example, if the current tetromino is in position (0,3) (line 0 and col 3), and the given dir 
	 *  is (1,0) (TetrisCoordinates.DOWN), then the new position is (1,3).
	 *  
	 *  The tetromino is moved only if the new position does not generate conflicts with the underlying
	 *  grid. Otherwise, the tetromino stays at its initial position.
	 *  
	 *  If this function is called when the grid has no tetromino / coordinates, 
	 *  it thows an IllegalStateException
	 *  
	 * @param dir a TetrisCoordinates to be aded to the current tetromino coordinates
	 * @return true if the tetromino has been moved, false otherwise
	 */
	public boolean tryMove(TetrisCoordinates dir);
	
	/**
	 * Try to rotate the current tetromino to the right
	 * 
	 * If this function is called when the grid has no tetromino / coordinates, 
	 * it thows an IllegalStateException
	 * 
	 * This uses the SPF "Super rotation System" from the Tetris official rules
	 * 
	 * It first rotates the tetromino within its box and tests for conflicts. Then
	 * it moves the tetromino using the specific kicks defined at the tetromino level:
	 * for each kick, it tries to move the tetromino and comes back to initial position if 
	 * it fails. 
	 * 
	 * If all kicks fail, then the rotation fails and the grid is left unchanged.
	 * 
	 * @return true if the tetromino has been rotated, false otherwise
	 */
	public boolean tryRotateRight();
	
	/**
	 * Try to rotate the current tetromino to the left
	 * 
	 * If this function is called when the grid has no tetromino / coordinates, 
	 * it thows an IllegalStateException
	 * 
	 * This uses the SPF "Super rotation System" from the Tetris official rules
	 * 
	 * It first rotates the tetromino within its box and tests for conflicts. Then
	 * it moves the tetromino using the specific kicks defined at the tetromino level:
	 * for each kick, it tries to move the tetromino and comes back to initial position if 
	 * it fails. 
	 * 
	 * If all kicks fail, then the rotation fails and the grid is left unchanged.
	 * 
	 * @return true if the tetromino has been rotated, false otherwise
	 */
	public boolean tryRotateLeft(); 
	
	/**
	 * Merge the current tetromino into the grid 
	 * 
	 * If there is no tetromino, the grid is left unchanged.
	 * 
	 * An illegalStateException is thrown if there is a tetromino but no coordinates.
	 * 
	 * If the tetromino is in conflict with the grid, the merge still happens: the tetromino
	 * visible cell replace the grid cell.
	 * 
	 * After the operation, the current tetromino and coordinates are set to null
	 */
	public void merge();
	
	/**
	 * Move the current tetromino as much down as possible without getting a conflict
	 * 
	 * Note: this does NOT merge the tetromino to the grid
	 * 
	 * If there is no tetromino or no coordinates, it throws an IllegalStateException
	 */
	public void hardDrop();
	
	
	/**
	 * "Pack" the grid
	 * i.e. remove all full lines and replace them with empty lines on top
	 * 
	 * No "gravity" is applied on remaining cells (a cell can be above an empty cell)
	 * 
	 * @return the list of line indexes that have been "packed". These are the indices of lines
	 *         that were full before packing
	 */
	public List<Integer> pack();
	
	
	
	/**
	 * Return an empty Tetris Grid
	 * if nblines or nbcols are not strictly positive integers, it throws an IllegalAgumentException
	 * @param nblines a strictly positive integer
	 * @param nbcols a strictly positive integer
	 * @return an empty tetris grid with no tetromino
	 */
	public static TetrisGrid getEmptyGrid(int nblines, int nbcols) {
		if(nblines<=0 || nbcols<=0) {
			throw new IllegalArgumentException("Diemnsion Invalide");
		}
		return new TetriseGrille(nblines,nbcols);	
	}
}
