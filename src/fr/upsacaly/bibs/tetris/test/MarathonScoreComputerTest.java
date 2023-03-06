package fr.upsacaly.bibs.tetris.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.upsacaly.bibs.tetris.TetrisAction;
import fr.upsacaly.bibs.tetris.TetrisMode;
import fr.upsacaly.bibs.tetris.model.grid.TetrisCoordinates;
import fr.upsacaly.bibs.tetris.model.grid.TetrisGrid;
import fr.upsacaly.bibs.tetris.model.grid.TetrisGridView;
import fr.upsacaly.bibs.tetris.model.score.ScoreComputer;
import fr.upsacaly.bibs.tetris.model.tetromino.TetrominoShape;

class MarathonScoreComputerTest {

	@Test
	void testCreate() {
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.MARATHON);
		assertEquals(scoreComputer.getScore(), 0);
		assertEquals(scoreComputer.getLevel(), 1);
		assertEquals(scoreComputer.getLines(), 0);
		scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.MARATHON, 1000, 2, 10);
		assertEquals(scoreComputer.getScore(), 1000);
		assertEquals(scoreComputer.getLevel(), 2);
		assertEquals(scoreComputer.getLines(), 10);
	}
	
	@Test
	void testRegisterActionException() {
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.MARATHON);
		TetrisGridView gridView = TetrisGrid.getEmptyGrid(10, 5).getView();
		assertThrows(IllegalStateException.class, () -> scoreComputer.registerAfterAction(gridView));
	}
	
	@Test
	void testSingleLineScore() {
		/* create good grid */
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.MARATHON);
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
		assertEquals(scoreComputer.getScore(), 100);
		assertEquals(scoreComputer.getLines(), 1);
		assertEquals(scoreComputer.getLevel(), 1);
	}
	
	@Test
	void testSingleLineScoreLevel() {
		/* create good grid */
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.MARATHON, 1000, 2, 10);
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
		assertEquals(scoreComputer.getScore(), 1200);
		assertEquals(scoreComputer.getLines(), 11);
		assertEquals(scoreComputer.getLevel(), 2);
	}
	
	@Test
	void testDoubleLineScore() {
		/* create good grid */
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.MARATHON);
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
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,8));
		/** merge pack **/
		grid.merge();
		List<Integer> lines = grid.pack();
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getScore(), 300);
		assertEquals(scoreComputer.getLines(), 2);
		assertEquals(scoreComputer.getLevel(), 1);
	}
	
	@Test
	void testDoubleLineScoreLevel() {
		/* create good grid */
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.MARATHON, 1000, 2, 10);
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
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,8));
		/** merge pack **/
		grid.merge();
		List<Integer> lines = grid.pack();
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getScore(), 1600);
		assertEquals(scoreComputer.getLines(), 12);
		assertEquals(scoreComputer.getLevel(), 2);
	}
	
	@Test
	void testTripleLineScore() {
		/* create good grid */
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.MARATHON);
		TetrisGrid grid = TetrisGrid.getEmptyGrid(20, 10);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,0));
		grid.merge();
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,4));
		grid.merge();
		grid.setTetromino(TetrominoShape.JSHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(17,6));
		grid.merge();
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(17,0));
		grid.merge();
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(17,2));
		grid.merge();
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(17,4));
		grid.merge();
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(16,6));
		grid.merge();
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(17,8));
		/** merge pack **/	
		grid.merge();
		List<Integer> lines = grid.pack();
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getScore(), 500);
		assertEquals(scoreComputer.getLines(), 3);
		assertEquals(scoreComputer.getLevel(), 1);
	}
	
	@Test
	void testTripleLineScoreLevel() {
		/* create good grid */
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.MARATHON, 1000, 2, 10);
		TetrisGrid grid = TetrisGrid.getEmptyGrid(20, 10);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,0));
		grid.merge();
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,4));
		grid.merge();
		grid.setTetromino(TetrominoShape.JSHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(17,6));
		grid.merge();
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(17,0));
		grid.merge();
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(17,2));
		grid.merge();
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(17,4));
		grid.merge();
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(16,6));
		grid.merge();
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(17,8));
		/** merge pack **/	
		grid.merge();
		List<Integer> lines = grid.pack();
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getScore(), 2000);
		assertEquals(scoreComputer.getLines(), 13);
		assertEquals(scoreComputer.getLevel(), 2);
	}
	
	@Test
	void testTetrisLineScore() {
		/* create good grid */
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.MARATHON);
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
		assertEquals(scoreComputer.getScore(), 800);
		assertEquals(scoreComputer.getLines(), 4);
		assertEquals(scoreComputer.getLevel(), 1);
	}
	
	
	
	@Test
	void testTetrisLineScoreLevel() {
		/* create good grid */
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.MARATHON, 1000, 2, 10);
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
		assertEquals(scoreComputer.getScore(), 2600);
		assertEquals(scoreComputer.getLines(), 14);
		assertEquals(scoreComputer.getLevel(), 2);
	}
	
	@Test
	void testIncreaseLevel() {
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.MARATHON, 900, 1, 9);
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
		assertEquals(scoreComputer.getScore(), 1000);
		assertEquals(scoreComputer.getLines(), 10);
		assertEquals(scoreComputer.getLevel(), 2);
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
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.MARATHON);
		TetrisGrid grid = TetrisGrid.getEmptyGrid(20, 10);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		scoreComputer.registerBeforeAction(TetrisAction.HARD_DROP, grid.getView());
		grid.hardDrop();
		scoreComputer.registerAfterAction(grid.getView());
		assertEquals(scoreComputer.getScore(), 36);
	}
	
	@Test
	void testHardDrop2() {
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.MARATHON);
		TetrisGrid grid = TetrisGrid.getEmptyGrid(20, 10);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,0));
		grid.merge();
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(1,0));
		scoreComputer.registerBeforeAction(TetrisAction.HARD_DROP, grid.getView());
		grid.hardDrop();
		scoreComputer.registerAfterAction(grid.getView());
		assertEquals(scoreComputer.getScore(), 32);
	}
	
	@Test
	void testCombo() {
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.MARATHON);
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
		assertEquals(scoreComputer.getScore(), 650); // 300 + 300 + 50 from combo
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,8));
		grid.merge();
		lines = grid.pack();
		assertEquals(scoreComputer.getComboCount(), 1);
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getComboCount(), 2);
		assertEquals(scoreComputer.getScore(), 1050); // 650 + 300 + 100 from combo
		// no combo
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(16,0));
		grid.merge();
		lines = grid.pack();
		assertEquals(scoreComputer.getComboCount(), 2);
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getComboCount(), -1);
		assertEquals(scoreComputer.getScore(), 1050);
		// new lines without combo
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,8));
		grid.merge();
		lines = grid.pack();
		assertEquals(scoreComputer.getComboCount(), -1);
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getComboCount(), 0);
		assertEquals(scoreComputer.getScore(), 1350); // 1050 + 300
	}
	
	@Test
	void testComboLevel() {
		ScoreComputer scoreComputer = ScoreComputer.getScoreComputer(TetrisMode.MARATHON, 1000, 2, 10);
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
		assertEquals(scoreComputer.getComboCount(), -1);
		List<Integer> lines = grid.pack();
		scoreComputer.registerMergePack(lines, grid.getView());
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,8));
		grid.merge();
		lines = grid.pack();
		assertEquals(scoreComputer.getComboCount(), 0);
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getScore(), 2300); // 1000 + 300*2 + 300*2 + 50*2 from combo
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,8));
		grid.merge();
		lines = grid.pack();
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getScore(), 3100); // 2300 + 300*2 + 100*2 from combo
		// no combo
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(16,0));
		grid.merge();
		lines = grid.pack();
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getScore(), 3100);
		// new lines without combo
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(18,8));
		grid.merge();
		lines = grid.pack();
		scoreComputer.registerMergePack(lines, grid.getView());
		assertEquals(scoreComputer.getScore(), 3700); // 3100 + 300*2
	}
	
	

}
