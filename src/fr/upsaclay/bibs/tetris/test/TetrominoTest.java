package fr.upsaclay.bibs.tetris.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import fr.upsaclay.bibs.tetris.model.grid.TetrisCell;
import fr.upsaclay.bibs.tetris.model.grid.TetrisCoordinates;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGrid;
import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;
import fr.upsaclay.bibs.tetris.model.tetromino.TetrominoShape;

class TetrominoTest {
	
	TetrominoShape[] shapes = TetrominoShape.values();
	
	/** some rotations for test purpose **/

	/** left rotation of I **/
	TetrisCell[][] IRotated = new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.I,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.I,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.I,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.I,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	
	/** right rotation of T **/
	TetrisCell[][] TRotated = new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.T,	TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.T,	TetrisCell.T},
		{TetrisCell.EMPTY,	TetrisCell.T,	TetrisCell.EMPTY}
	};
	
	/**
	 * For testing purpose
	 * Test equality between the cells of a given tetrominos Grid and a double array of cells
	 * @param g a tetromino
	 * @param cells a double array of cells
	 * @return true if same dimension and all cells are equal
	 */
	public static boolean testTetrominoCells(Tetromino g, TetrisCell[][] cells) {
		for(int i = 0; i < g.getBoxSize(); i++) {
			for(int j = 0; j < g.getBoxSize(); j++) {
				if(g.cell(i, j) != cells[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Test
	void testGetType() {
		assertEquals(TetrominoShape.ISHAPE.getType(), TetrisCell.I);
		assertEquals(TetrominoShape.JSHAPE.getType(), TetrisCell.J);
		assertEquals(TetrominoShape.LSHAPE.getType(), TetrisCell.L);
		assertEquals(TetrominoShape.OSHAPE.getType(), TetrisCell.O);
		assertEquals(TetrominoShape.SSHAPE.getType(), TetrisCell.S);
		assertEquals(TetrominoShape.TSHAPE.getType(), TetrisCell.T);
		assertEquals(TetrominoShape.ZSHAPE.getType(), TetrisCell.Z);
		
	}

	@Test
	void testBoxSize() {
		assertEquals(TetrominoShape.ISHAPE.getBoxSize(), 4);
		assertEquals(TetrominoShape.JSHAPE.getBoxSize(), 3);
		assertEquals(TetrominoShape.LSHAPE.getBoxSize(), 3);
		assertEquals(TetrominoShape.OSHAPE.getBoxSize(), 2);
		assertEquals(TetrominoShape.SSHAPE.getBoxSize(), 3);
		assertEquals(TetrominoShape.TSHAPE.getBoxSize(), 3);
		assertEquals(TetrominoShape.ZSHAPE.getBoxSize(), 3);
	}
	
	@Test
	void testNomberOfRotations() {
		/** As the I shape is in a 4x4 box, it has 4 different rotations
		 */
		assertEquals(TetrominoShape.ISHAPE.getNumberOfRotations(), 4);
		assertEquals(TetrominoShape.JSHAPE.getNumberOfRotations(), 4);
		assertEquals(TetrominoShape.LSHAPE.getNumberOfRotations(), 4);
		assertEquals(TetrominoShape.OSHAPE.getNumberOfRotations(), 1);
		assertEquals(TetrominoShape.SSHAPE.getNumberOfRotations(), 4);
		assertEquals(TetrominoShape.TSHAPE.getNumberOfRotations(), 4);
		assertEquals(TetrominoShape.ZSHAPE.getNumberOfRotations(), 4);
	}
	
	@Test
	void testGetShape() {
		for(TetrominoShape shape : shapes) {
			for(int i = 0; i < shape.getNumberOfRotations(); i++) {
				assertEquals(shape.getTetromino(i).getShape(), shape);
			}
		}
	}
	
	@Test
	void testRotateRight() {
		assertTrue(testTetrominoCells(TetrominoShape.TSHAPE.getTetromino(0).rotateRight(), TRotated));
		for(TetrominoShape shape : shapes) {
			for(int i = 0; i < shape.getNumberOfRotations() - 1; i++) {
				assertEquals(shape.getTetromino(i).rotateRight(), shape.getTetromino(i+1));
			}
			assertEquals(shape.getTetromino(shape.getNumberOfRotations()-1).rotateRight(), shape.getTetromino(0));
		}
	}
	
	@Test
	void testRotateLeft() {
		assertTrue(testTetrominoCells(TetrominoShape.ISHAPE.getTetromino(0).rotateLeft(), IRotated));
		for(TetrominoShape shape : shapes) {
			for(int i = 1; i < shape.getNumberOfRotations(); i++) {
				assertEquals(shape.getTetromino(i).rotateLeft(), shape.getTetromino(i-1));
			}
			assertEquals(shape.getTetromino(0).rotateLeft(), shape.getTetromino(shape.getNumberOfRotations()-1));
		}
	}
	
	@Test
	void testRotateRightLeft() {
		for(TetrominoShape shape : shapes) {
			for(int i = 0; i < shape.getNumberOfRotations(); i++) {
				assertEquals(shape.getTetromino(i).rotateLeft().rotateRight(), shape.getTetromino(i));
				assertEquals(shape.getTetromino(i).rotateRight().rotateLeft(), shape.getTetromino(i));
			}
		}
	}
	
	@Test
	void testCell() {
		/** this test checks that all cell are of the right type and that
		 * there are exactly 4 on empty cells on all tetrominos
		 */
		for(TetrominoShape shape : shapes) {
			for(int k = 0; k < shape.getNumberOfRotations(); k++) {
				int c = 0;
				Tetromino tet = shape.getTetromino(k);
				for(int i = 0; i < shape.getBoxSize(); i++) {
					for(int j = 0; j < shape.getBoxSize(); j++) {
						assertTrue(tet.cell(i, j).equals(shape.getType() )|| tet.cell(i, j).equals(TetrisCell.EMPTY));
						if(tet.cell(i, j) != TetrisCell.EMPTY) {
							c+=1;
						}
					}
				}
				assertEquals(c,4);
			}
		}
	}
	
	@Test
	void testTetrominoBoxSize() {
		for(TetrominoShape shape : shapes) {
			for(int k = 0; k < shape.getNumberOfRotations(); k++) {
				assertEquals(shape.getTetromino(k).getBoxSize(), shape.getBoxSize());
			}
		}
	}
	
	@Test
	void testWallKicksFromRight() {
		assertEquals(TetrominoShape.OSHAPE.getTetromino(0).wallKicksFromRight().size(), 0);
		assertEquals(TetrominoShape.JSHAPE.getTetromino(0).wallKicksFromRight(), 
				Arrays.asList(TetrisCoordinates.LEFT, new TetrisCoordinates(1,-1), new TetrisCoordinates(-2,0), new TetrisCoordinates(-2,-1)));
		assertEquals(TetrominoShape.JSHAPE.getTetromino(1).wallKicksFromRight(), 
				Arrays.asList(TetrisCoordinates.LEFT, new TetrisCoordinates(-1,-1), new TetrisCoordinates(2,0), new TetrisCoordinates(2,-1)));
		assertEquals(TetrominoShape.JSHAPE.getTetromino(2).wallKicksFromRight(), 
				Arrays.asList(TetrisCoordinates.RIGHT, new TetrisCoordinates(1,1), new TetrisCoordinates(-2,0), new TetrisCoordinates(-2,1)));
		assertEquals(TetrominoShape.JSHAPE.getTetromino(3).wallKicksFromRight(), 
				Arrays.asList(TetrisCoordinates.RIGHT, new TetrisCoordinates(-1,1), new TetrisCoordinates(2,0), new TetrisCoordinates(2,1)));

		assertEquals(TetrominoShape.ISHAPE.getTetromino(0).wallKicksFromRight(), 
				Arrays.asList(new TetrisCoordinates(0,1), new TetrisCoordinates(0,-2), new TetrisCoordinates(2,1), new TetrisCoordinates(-1,-2)));
		assertEquals(TetrominoShape.ISHAPE.getTetromino(1).wallKicksFromRight(), 
				Arrays.asList(new TetrisCoordinates(0,-2), new TetrisCoordinates(0,1), new TetrisCoordinates(1,-2), new TetrisCoordinates(-2,1)));
		assertEquals(TetrominoShape.ISHAPE.getTetromino(2).wallKicksFromRight(), 
				Arrays.asList(new TetrisCoordinates(0,-1), new TetrisCoordinates(0,2), new TetrisCoordinates(-2,-1), new TetrisCoordinates(1,2)));
		assertEquals(TetrominoShape.ISHAPE.getTetromino(3).wallKicksFromRight(), 
				Arrays.asList(new TetrisCoordinates(0,2), new TetrisCoordinates(0,-1), new TetrisCoordinates(-1,2), new TetrisCoordinates(2,-1)));
	}
	
	
	@Test
	void testWallKicksFromLeft() {
		assertEquals(TetrominoShape.OSHAPE.getTetromino(0).wallKicksFromLeft().size(), 0);
		assertEquals(TetrominoShape.JSHAPE.getTetromino(3).wallKicksFromLeft(), 
				Arrays.asList(TetrisCoordinates.RIGHT, new TetrisCoordinates(-1,1), new TetrisCoordinates(2,0), new TetrisCoordinates(2,1)));
		assertEquals(TetrominoShape.JSHAPE.getTetromino(0).wallKicksFromLeft(), 
				Arrays.asList(TetrisCoordinates.RIGHT, new TetrisCoordinates(1,1), new TetrisCoordinates(-2,0), new TetrisCoordinates(-2,1)));
		assertEquals(TetrominoShape.JSHAPE.getTetromino(1).wallKicksFromLeft(), 
				Arrays.asList(TetrisCoordinates.LEFT, new TetrisCoordinates(-1,-1), new TetrisCoordinates(2,0), new TetrisCoordinates(2,-1)));
		assertEquals(TetrominoShape.JSHAPE.getTetromino(2).wallKicksFromLeft(), 
				Arrays.asList(TetrisCoordinates.LEFT, new TetrisCoordinates(1,-1), new TetrisCoordinates(-2,0), new TetrisCoordinates(-2,-1)));

		assertEquals(TetrominoShape.ISHAPE.getTetromino(3).wallKicksFromLeft(), 
				Arrays.asList(new TetrisCoordinates(0,-1), new TetrisCoordinates(0,2), new TetrisCoordinates(-2,-1), new TetrisCoordinates(1,2)));
		assertEquals(TetrominoShape.ISHAPE.getTetromino(0).wallKicksFromLeft(), 
				Arrays.asList(new TetrisCoordinates(0,2), new TetrisCoordinates(0,-1), new TetrisCoordinates(-1,2), new TetrisCoordinates(2,-1)));
		assertEquals(TetrominoShape.ISHAPE.getTetromino(1).wallKicksFromLeft(), 
				Arrays.asList(new TetrisCoordinates(0,1), new TetrisCoordinates(0,-2), new TetrisCoordinates(2,1), new TetrisCoordinates(-1,-2)));
		assertEquals(TetrominoShape.ISHAPE.getTetromino(2).wallKicksFromLeft(), 
				Arrays.asList(new TetrisCoordinates(0,-2), new TetrisCoordinates(0,1), new TetrisCoordinates(1,-2), new TetrisCoordinates(-2,1)));
	}
	
}
