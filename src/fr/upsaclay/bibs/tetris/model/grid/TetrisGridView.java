package fr.upsaclay.bibs.tetris.model.grid;

import java.io.PrintStream;
import java.util.List;

import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;

/**
 * This interface contains all the methods to "look at" a TetrisGrid
 * but not the methods to change the Tetris Grid
 * 
 * 
 * @author Viviane Pons
 *
 */
public interface TetrisGridView {
	
	/**
	 * Return the number of lines
	 * @return the number of lines
	 */
	public int numberOfLines();
	
	/**
	 * Return the number of cols
	 * @return the number of cols
	 */
	public int numberOfCols();
	
	/**
	 * Return the tetris cell on the grid at the given position
	 * if the position is not on the grid (even negative), it should return TetrisCell.GREY
	 * (as if the grid was infinite with grey cells all around)
	 * @param i the line number, positive or negative
	 * @param j the col number, positive or negative
	 * @return the cell on the grid
	 */
	public TetrisCell gridCell(int i, int j);
	
	/**
	 * Prints the grid cells line by line on the out stream
	 * (for example on the console or in a file)
	 * @param out the out stream to print the grid
	 */
	public void printGrid(PrintStream out);
	
	/**
	 * Return if the grid has a tetromino
	 * @return true if a tetromino has been attached
	 */
	public boolean hasTetromino();
	
	/**
	 * Return the current tetromino attached to the grid
	 * @return a Tetromino
	 */
	public Tetromino getTetromino();
	
	/**
	 * Return the coordinated of the current tetromino
	 * @return some TetrisCoordinates
	 */
	public TetrisCoordinates getCoordinates();
	
	/**
	 * Return the cell that is visible on the grid
	 * this is the grid cell unless there is an non-empty tetromino cell
	 * in front of it
	 * 
	 * If there is a current tetromino but no coordinates, an IllegalStateException is thrown
	 * @param i the line number
	 * @param j the col nomber
	 * @return the visible cell
	 */
	public TetrisCell visibleCell(int i, int j);
	
	
	/**
	 * Return if the current tetromino has a "conflict" with the grid
	 * i.e. : if it is overlapping on some non empty cells
	 * 
	 * If there is no current tetromino, there is no conflict
	 * If there is a tetromino but bo coordinates, it raises an IllegalStateException
	 * 
	 * @return true if some non empty cells are overlapping
	 */
	public boolean hasConflicts();
	
	/**
	 * Return if a given line is full (no empty cell)
	 * @param lineNumber the line number (top line is 0)
	 * @return true if the line is full, false otherwise
	 */
	public boolean isFull(int lineNumber);
	
	/**
	 * Return if a given line is empty 
	 * @param lineNumber the line number (top line is 0)
	 * @return true if the line is empty, false otherwise
	 */
	public boolean isEmpty(int lineNumber);
	
	/**
	 * Return the list of lines which are full
	 * @return a list of line numbers (top line is 0)
	 */
	public List<Integer> fullLines();
	
	/**
	 * Return if the full grid is empty
	 * @return true if the full grid is empty
	 */
	public boolean isEmpty(); 

}
