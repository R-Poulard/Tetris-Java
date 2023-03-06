package fr.upsaclay.bibs.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import fr.upsaclay.bibs.tetris.model.grid.TetrisCell;
import fr.upsaclay.bibs.tetris.model.grid.TetrisCoordinates;

/**
 * The list of different tetromino shapes
 * 
 * Each shape receives its initial tetromino as double array of tetris cells
 * 
 * It should then be able to return any rotation of the initial cells
 * 
 * Suggestion: compute all rotations at the creation of the shape and keeps the tetromino
 * stored inside the shape object
 * 
 * You need to implement the private constructor (as it is an enum) as well as needed
 * methods. You can add methods and fields and all you need.
 * 
 * We provide you with the random method
 * 
 * @author Viviane Pons
 *
 */
public enum TetrominoShape {
	ISHAPE(new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
		{TetrisCell.I,		TetrisCell.I,		TetrisCell.I,		TetrisCell.I},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	}),
	OSHAPE(new TetrisCell[][] {
		{TetrisCell.O, TetrisCell.O},
		{TetrisCell.O, TetrisCell.O}
	}),
	TSHAPE(new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.T,		TetrisCell.EMPTY},
		{TetrisCell.T,		TetrisCell.T,		TetrisCell.T},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	}),
	LSHAPE(new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.L},
		{TetrisCell.L,		TetrisCell.L,		TetrisCell.L},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	}),
	JSHAPE(new TetrisCell[][] {
		{TetrisCell.J,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
		{TetrisCell.J,		TetrisCell.J,		TetrisCell.J},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	}),
	ZSHAPE(new TetrisCell[][] {
		{TetrisCell.Z,		TetrisCell.Z,		TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.Z,		TetrisCell.Z},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	}),
	SSHAPE(new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.S,		TetrisCell.S},
		{TetrisCell.S,		TetrisCell.S,		TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	});
	
	
	private static final Random RANDOM = new Random();
	
	
	
	public static Tetromino randomTetromino() {
		TetrominoShape randomShape = values()[RANDOM.nextInt(values().length)];
		return randomShape.getTetromino(RANDOM.nextInt(randomShape.getNumberOfRotations()));
	}
	
	
	private TetrominoShape(TetrisCell[][] initialShape) {
		/// Write your code here
	}
	
	
	public TetrisCell getType() {
		throw new UnsupportedOperationException("Not implemented");
	}
	
	public int getNumberOfRotations() {
		throw new UnsupportedOperationException("Not implemented");
	}
	
	public int getBoxSize() {
		throw new UnsupportedOperationException("Not implemented");
	}
	
	public Tetromino getTetromino(int rotationNumber) {
		throw new UnsupportedOperationException("Not implemented");
	}
	
	

}
