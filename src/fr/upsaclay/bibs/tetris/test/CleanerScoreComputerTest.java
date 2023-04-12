package fr.upsaclay.bibs.tetris.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.upsaclay.bibs.tetris.TetrisAction;
import fr.upsaclay.bibs.tetris.TetrisMode;
import fr.upsaclay.bibs.tetris.model.grid.TetrisCoordinates;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGrid;
import fr.upsaclay.bibs.tetris.model.score.ScoreComputer;
import fr.upsaclay.bibs.tetris.model.tetromino.TetrominoShape;

class CleanerScoreComputerTest {

	@Test
	void testTetrisLineScoreLevel() {
		/* create good grid */
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.SPACE_CLEANER, 1000, 1, 10);
		TetrisGrid grid = TetrisGrid.getEmptyGrid(20, 10);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,0));
		grid.merge();
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,4));
		grid.merge();
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(17,0));
		grid.merge();
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(17,4));
		grid.merge();
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(16,0));
		grid.merge();
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(16,4));
		grid.merge();
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(15,0));
		grid.merge();
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(15,4));
		grid.merge();
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(1));
		grid.setCoordinates(new TetrisCoordinates(16,6));
		grid.merge();
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(1));
		grid.setCoordinates(new TetrisCoordinates(16,7));
		/** merge pack **/	
		grid.merge();
		List<Integer> lines = grid.pack();
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getScore(), 1000);
		assertEquals(scoreComputer.getLines(), 10);
		assertEquals(scoreComputer.getLevel(), 1);
	}
	
	@Test
	void testIncreaseLevel() {
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.SPACE_CLEANER, 900, 1, 9);
		TetrisGrid grid = TetrisGrid.getEmptyGrid(20, 10);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,0));
		grid.merge();
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,4));
		grid.merge();
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,8));
		/** merge pack **/
		grid.merge();
		List<Integer> lines = grid.pack();
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getScore(), 900);
		assertEquals(scoreComputer.getLines(), 9);
		assertEquals(scoreComputer.getLevel(), 1);
	}
	
	@Test
	void testSoftDrop() {
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.MARATHON);
		TetrisGrid grid = TetrisGrid.getEmptyGrid(20, 10);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		scoreComputer.registerBeforeAction(TetrisAction.START_SOFT_DROP, grid.getView());
		scoreComputer.registerAfterAction(grid.getView());
		scoreComputer.registerBeforeAction(TetrisAction.DOWN, grid.getView());
		grid.tryMove(TetrisCoordinates.DOWN);
		scoreComputer.registerAfterAction(grid.getView());
		assertEquals(scoreComputer.getScore(),1);
		scoreComputer.registerBeforeAction(TetrisAction.DOWN, grid.getView());
		grid.tryMove(TetrisCoordinates.DOWN);
		scoreComputer.registerAfterAction(grid.getView());
		assertEquals(scoreComputer.getScore(),2);
		scoreComputer.registerBeforeAction(TetrisAction.END_SOFT_DROP, grid.getView());
		scoreComputer.registerAfterAction(grid.getView());
		scoreComputer.registerBeforeAction(TetrisAction.DOWN, grid.getView());
		grid.tryMove(TetrisCoordinates.DOWN);
		scoreComputer.registerAfterAction(grid.getView());
		assertEquals(scoreComputer.getScore(),2);
	}
	
	@Test
	void testHardDrop() {
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.SPACE_CLEANER);
		TetrisGrid grid = TetrisGrid.getEmptyGrid(20, 10);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		scoreComputer.registerBeforeAction(TetrisAction.HARD_DROP, grid.getView());
		grid.hardDrop();
		scoreComputer.registerAfterAction(grid.getView());
		assertEquals(scoreComputer.getScore(), 54);
	}
	
	@Test
	void testHardDrop2() {
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.SPACE_CLEANER);
		TetrisGrid grid = TetrisGrid.getEmptyGrid(20, 10);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,0));
		grid.merge();
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(1,0));
		scoreComputer.registerBeforeAction(TetrisAction.HARD_DROP, grid.getView());
		grid.hardDrop();
		scoreComputer.registerAfterAction(grid.getView());
		assertEquals(scoreComputer.getScore(), 48);
	}
	
	@Test
	void testCombo() {
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.SPACE_CLEANER);
		TetrisGrid grid = TetrisGrid.getEmptyGrid(20, 10);
		/* preparing the grid */
		for(int i = 18; i > 10; i--) {
			grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
			grid.setCoordinates(new TetrisCoordinates(i,0));
			grid.merge();
			grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
			grid.setCoordinates(new TetrisCoordinates(i,4));
			grid.merge();
		}
		/* combo */
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,8));
		grid.merge();
		List<Integer> lines = grid.pack();
		assertEquals(scoreComputer.getComboCount(), -1);
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getComboCount(), 0);
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,8));
		grid.merge();
		lines = grid.pack();
		assertEquals(scoreComputer.getComboCount(), 0);
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getComboCount(), 1);
		assertEquals(scoreComputer.getScore(), 100); 
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,8));
		grid.merge();
		lines = grid.pack();
		assertEquals(scoreComputer.getComboCount(), 1);
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getComboCount(), 2);
		assertEquals(scoreComputer.getScore(), 300); 
		// no combo
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(16,0));
		grid.merge();
		lines = grid.pack();
		assertEquals(scoreComputer.getComboCount(), 2);
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getComboCount(), -1);
		assertEquals(scoreComputer.getScore(), 300);
		// new lines without combo
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,8));
		grid.merge();
		lines = grid.pack();
		assertEquals(scoreComputer.getComboCount(), -1);
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getComboCount(), 0);
		assertEquals(scoreComputer.getScore(), 300); // 1050 + 300
	}
	
	@Test
	void testComboLevel() {
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.SPACE_CLEANER, 1000,1, 10);
		TetrisGrid grid = TetrisGrid.getEmptyGrid(20, 10);
		/* preparing the grid */
		for(int i = 18; i > 10; i--) {
			grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
			grid.setCoordinates(new TetrisCoordinates(i,0));
			grid.merge();
			grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
			grid.setCoordinates(new TetrisCoordinates(i,4));
			grid.merge();
		}
		assertEquals(scoreComputer.getLevel(), 1);
		/* combo */
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,8));
		grid.merge();
		assertEquals(scoreComputer.getComboCount(), -1);
		List<Integer> lines = grid.pack();
		scoreComputer.registerMergePack(lines, grid.getView());
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,8));
		grid.merge();
		lines = grid.pack();
		assertEquals(scoreComputer.getComboCount(), 0);
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getScore(), 1100); // 1000 + 
		assertEquals(scoreComputer.getLevel(), 1);
		
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,8));
		grid.merge();
		lines = grid.pack();
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getScore(), 1300); // 2300 + 300*2 + 100*2 from combo
		assertEquals(scoreComputer.getLevel(), 2);
		// no combo
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(16,0));
		grid.merge();
		lines = grid.pack();
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getScore(), 1300);
		assertEquals(scoreComputer.getLevel(), 2);
	}
	
}
