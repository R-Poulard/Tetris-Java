package fr.upsaclay.bibs.tetris.model.grid;

/**
 * This class represents coordinates on the tetris grid
 * 
 * Coordinates are given through lines and column:
 * top line is line 0
 * left column is column 0
 * 
 * It can be either the coordinates of a cell or of a "vector", 
 * i.e. something to add on an existing coordinates
 * 
 * We provide you a basic implementation. You can add extra methods
 * if needed
 * 
 * @author Viviane Pons
 *
 */
public class TetrisCoordinates {
	
	public static final TetrisCoordinates UP = new TetrisCoordinates(-1,0);
	public static final TetrisCoordinates DOWN = new TetrisCoordinates(1,0);
	public static final TetrisCoordinates LEFT = new TetrisCoordinates(0,-1);
	public static final TetrisCoordinates RIGHT = new TetrisCoordinates(0,1);
	
	private final int line;
	private final int col;
	
	public TetrisCoordinates(int ligne, int colonne) {
		this.line = ligne;
		this.col = colonne;
	}

	public int getLine() {
		return line;
	}

	public int getCol() {
		return col;
	}
	
	@Override
	public String toString() {
		return "(" + line + ", " + col + ")";
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(! TetrisCoordinates.class.isAssignableFrom(o.getClass())) {
			return false;
		}
		TetrisCoordinates coord = (TetrisCoordinates)o;
		return coord.line == line && coord.col == col;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	

}
