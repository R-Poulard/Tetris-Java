package fr.upsaclay.bibs.tetris.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;
import fr.upsaclay.bibs.tetris.model.tetromino.TetrominoProvider;
import fr.upsaclay.bibs.tetris.model.tetromino.TetrominoShape;

class TetrominoProviderTest {
	
	private Tetromino t0 = TetrominoShape.ISHAPE.getTetromino(0);
	private Tetromino t1 = TetrominoShape.LSHAPE.getTetromino(1);
	private Tetromino t2 = TetrominoShape.OSHAPE.getTetromino(0);
	
	private List<Tetromino> tetrominos = Arrays.asList(t0,t1,t2);
	private List<Tetromino> empty = new ArrayList<Tetromino>();

	@Test
	void testListNext() {
		TetrominoProvider provider = TetrominoProvider.listTetrominoProvider(tetrominos);
		assertEquals(provider.next(), t0);
		assertEquals(provider.next(), t1);
		assertEquals(provider.next(), t2);
	}
	
	@Test
	void tesListtHasNext() {
		assertFalse(TetrominoProvider.listTetrominoProvider(empty).hasNext());
		TetrominoProvider provider = TetrominoProvider.listTetrominoProvider(tetrominos);
		assertTrue(provider.hasNext());
		provider.next();
		assertTrue(provider.hasNext());
		provider.next();
		assertTrue(provider.hasNext());
		provider.next();
		assertFalse(provider.hasNext());
	}
	
	@Test
	void testListShowNext() {
		TetrominoProvider provider = TetrominoProvider.listTetrominoProvider(tetrominos);
		Tetromino p0 = provider.showNext(0);
		Tetromino p1 = provider.showNext(1);
		Tetromino p2 = provider.showNext(2);
		assertEquals(p0,t0);
		assertEquals(p1,t1);
		assertEquals(p2,t2);
		assertEquals(provider.showNext(4), null);
		assertEquals(provider.showNext(100),null);
		assertEquals(provider.next(), p0);
		assertEquals(provider.showNext(0),p1);
		assertEquals(provider.showNext(1),p2);
		assertEquals(provider.showNext(2),null);
		assertEquals(provider.next(), p1);
		assertEquals(provider.showNext(0),p2);
		assertEquals(provider.showNext(1),null);
		assertEquals(provider.showNext(2),null);
		assertEquals(provider.next(), p2);
		assertEquals(provider.showNext(0),null);
	}
	
	@Test
	void testRandomNext() {
		TetrominoProvider provider = TetrominoProvider.randomTetrominoProvider();
		provider.next();
		provider.next();
		provider.next();
	}
	
	@Test
	void tetRandomtHasNext() {
		TetrominoProvider provider = TetrominoProvider.randomTetrominoProvider();
		assertTrue(provider.hasNext());
		provider.showNext(3);
		provider.next();
		assertTrue(provider.hasNext());
		provider.next();
		assertTrue(provider.hasNext());
		provider.next();
		assertTrue(provider.hasNext());
	}
	
	@Test
	void testRandomShowNext() {
		TetrominoProvider provider = TetrominoProvider.randomTetrominoProvider();
		Tetromino p0 = provider.showNext(0);
		Tetromino p1 = provider.showNext(1);
		Tetromino p2 = provider.showNext(2);
		assertEquals(provider.next(), p0);
		assertEquals(provider.showNext(0),p1);
		assertEquals(provider.showNext(1),p2);
		assertEquals(provider.next(), p1);
		assertEquals(provider.showNext(0),p2);
		assertEquals(provider.next(), p2);
		Tetromino t50 = provider.showNext(50);
		assertEquals(provider.showNext(100), provider.showNext(100));
		assertEquals(t50, provider.showNext(50));
	}
	
	@Test
	void testFromFileProvider() throws FileNotFoundException, IOException {
		File file = new File("resources/tetrominos_test.txt");
		TetrominoProvider provider = TetrominoProvider.fromFile(file);
		assertTrue(provider.hasNext());
		assertEquals(provider.showNext(0), TetrominoShape.TSHAPE.getTetromino(2));
		assertEquals(provider.showNext(100), TetrominoShape.ZSHAPE.getTetromino(1));
		assertEquals(provider.showNext(199), TetrominoShape.ISHAPE.getTetromino(1));
		assertEquals(provider.showNext(200), null);
		assertEquals(provider.next(), TetrominoShape.TSHAPE.getTetromino(2));
		assertEquals(provider.next(), TetrominoShape.JSHAPE.getTetromino(2));
	}
	
	
	

}
