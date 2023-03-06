package fr.upsaclay.bibs.tetris.model.grid;

/**
 * The list of possible cells in the grid
 * 
 * The letter represents from which tetromino the cell is originated.
 * This is what decides the color of the cell in the graphical interface
 * 
 * Grey represents an non empty cell that does not come from a tetromino
 * 
 * @author Viviane Pons
 *
 */
public enum TetrisCell {
	I,
	O,
	T,
	L,
	J,
	Z,
	S,
	EMPTY,
	GREY
}
