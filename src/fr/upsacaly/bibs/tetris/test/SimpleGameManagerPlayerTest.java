package fr.upsacaly.bibs.tetris.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import fr.upsacaly.bibs.tetris.TetrisAction;
import fr.upsacaly.bibs.tetris.control.manager.GameManager;
import fr.upsacaly.bibs.tetris.control.manager.GameType;
import fr.upsacaly.bibs.tetris.control.player.GamePlayer;
import fr.upsacaly.bibs.tetris.control.player.PlayerType;
import fr.upsacaly.bibs.tetris.model.grid.TetrisCell;
import fr.upsacaly.bibs.tetris.model.grid.TetrisCoordinates;
import fr.upsacaly.bibs.tetris.model.grid.TetrisGridView;
import fr.upsacaly.bibs.tetris.model.tetromino.Tetromino;
import fr.upsacaly.bibs.tetris.model.tetromino.TetrominoProvider;
import fr.upsacaly.bibs.tetris.model.tetromino.TetrominoShape;

class SimpleGameManagerPlayerTest {

	@Test
	void testSimpleManagerCreation() {
		GameManager.getGameManager(GameType.SIMPLE);
	}
	
	@Test
	void testInitilization() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		assertEquals(simpleManager.getNumberOfLines(), GameManager.DEFAULT_LINES);
		assertEquals(simpleManager.getNumberOfCols(), GameManager.DEFAULT_COLS);
		assertEquals(simpleManager.getGameMode(), GameManager.DEFAULT_MODE);
		assertEquals(simpleManager.getTetrominoProvider(), GameManager.DEFAULT_PROVIDER);
		assertEquals(simpleManager.getPlayerType(), GameManager.DEFAULT_PLAYER_TYPE);
	}
	
	@Test
	void testCreateHumanPlayer() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.createPlayer();
		assertTrue(simpleManager.getPlayer() != null);
	}
	
	@Test
	void testCreateAIPlayer() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.setPlayerType(PlayerType.AI);
		/** If you implement an AI simple player, you can change this test and add specific ai tests */
		assertThrows(UnsupportedOperationException.class, () -> simpleManager.createPlayer());
	}
	
	@Test
	void testLoadNewGame() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		assertEquals(player.getType(), GameManager.DEFAULT_PLAYER_TYPE);
		assertEquals(player.getLevel(), 1);
		assertEquals(player.getScore(), 0);
		assertEquals(player.getLineScore(), 0);
		assertFalse(player.isOver());
		assertEquals(player.getHeldTetromino(), null);
		assertFalse(player.isActive());
	}
	
	@Test
	void testStartPausePlayer() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		assertFalse(player.isActive());
		simpleManager.startPlayer();
		assertTrue(player.isActive());
		simpleManager.pausePlayer();
		assertFalse(player.isActive());
		simpleManager.startPlayer();
		assertTrue(player.isActive());
	}
	
	@Test
	void testStartPlayerTetromino() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(TetrominoShape.ISHAPE.getTetromino(0))));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		TetrisGridView gridView = player.getGridView();
		assertTrue(gridView.hasTetromino());
		assertEquals(gridView.getTetromino(), TetrominoShape.ISHAPE.getTetromino(0));
		assertEquals(gridView.getCoordinates().getLine(), 0);
	}
	
	@Test
	void testStartPlayerFallDown() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(TetrominoShape.ISHAPE.getTetromino(0))));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		assertTrue(player.performAction(TetrisAction.DOWN));
		TetrisGridView gridView = player.getGridView();
		assertTrue(gridView.hasTetromino());
		assertEquals(gridView.getTetromino(), TetrominoShape.ISHAPE.getTetromino(0));
		assertEquals(gridView.getCoordinates().getLine(), 1);
	}
	
	@Test
	void testExceptionFallDown1() {
		// exception when player is not active
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(TetrominoShape.ISHAPE.getTetromino(0))));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		assertThrows(IllegalStateException.class, () -> player.performAction(TetrisAction.DOWN));
		simpleManager.startPlayer();
		
	}
	
	@Test
	void testExceptionFallDown2() {
		// exception when player is not active
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(TetrominoShape.ISHAPE.getTetromino(0))));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		simpleManager.pausePlayer();
		assertThrows(IllegalStateException.class, () -> player.performAction(TetrisAction.DOWN));
		
	}
	
	@Test
	void testTetrisActionMoveLeft() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(TetrominoShape.ISHAPE.getTetromino(0))));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		TetrisGridView gridView = player.getGridView();
		int col = gridView.getCoordinates().getCol();
		assertTrue(player.performAction(TetrisAction.MOVE_LEFT));
		assertEquals(gridView.getCoordinates().getCol(), col - 1);
		for(int i = col - 1; i > 0; i --) {
			assertTrue(player.performAction(TetrisAction.MOVE_LEFT));
		}
		assertFalse(player.performAction(TetrisAction.MOVE_LEFT));
	}
	
	@Test
	void testExceptionTetrisAction1() {
		// exception when player is not active
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(TetrominoShape.ISHAPE.getTetromino(0))));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		assertThrows(IllegalStateException.class, () -> player.performAction(TetrisAction.MOVE_LEFT));
		simpleManager.startPlayer();
		
	}
	
	@Test
	void testExceptionTetrisAction2() {
		// exception when player is not active
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(TetrominoShape.ISHAPE.getTetromino(0))));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		simpleManager.pausePlayer();
		assertThrows(IllegalStateException.class, () -> player.performAction(TetrisAction.MOVE_LEFT));
	}
	
	@Test
	void testTetrisActionMoveRight() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(TetrominoShape.ISHAPE.getTetromino(0))));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		TetrisGridView gridView = player.getGridView();
		int col = gridView.getCoordinates().getCol();
		assertTrue(player.performAction(TetrisAction.MOVE_RIGHT));
		assertEquals(gridView.getCoordinates().getCol(), col + 1);
		for(int i = col + 1; i < simpleManager.getNumberOfCols() - 4; i++) {
			assertTrue(player.performAction(TetrisAction.MOVE_RIGHT));
		}
		assertFalse(player.performAction(TetrisAction.MOVE_RIGHT));
	}
	
	@Test
	void testTetrisActionRotateRight() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(TetrominoShape.ISHAPE.getTetromino(0))));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		TetrisGridView gridView = player.getGridView();
		assertTrue(player.performAction(TetrisAction.ROTATE_RIGHT));
		assertEquals(gridView.getTetromino(), TetrominoShape.ISHAPE.getTetromino(1));
	}
	
	@Test
	void testTetrisActionRotateLeft() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(TetrominoShape.ISHAPE.getTetromino(0))));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		TetrisGridView gridView = player.getGridView();
		assertTrue(player.performAction(TetrisAction.ROTATE_LEFT));
		assertEquals(gridView.getTetromino(), TetrominoShape.ISHAPE.getTetromino(3));
	}
	
	@Test
	void testTetrominoMerge() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(TetrominoShape.ISHAPE.getTetromino(0))));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		TetrisGridView gridView = player.getGridView();
		int col = gridView.getCoordinates().getCol();
		for(int i = 0; i < GameManager.DEFAULT_LINES - 2; i++) {
			assertTrue(player.performAction(TetrisAction.DOWN));
		}
		assertFalse(player.performAction(TetrisAction.DOWN));
		assertEquals(gridView.gridCell(GameManager.DEFAULT_LINES - 1, col), TetrisCell.I);
		assertEquals(gridView.gridCell(GameManager.DEFAULT_LINES - 1, col + 1), TetrisCell.I);
		assertEquals(gridView.gridCell(GameManager.DEFAULT_LINES - 1, col + 2), TetrisCell.I);
		assertEquals(gridView.gridCell(GameManager.DEFAULT_LINES - 1, col + 3), TetrisCell.I);
	}
	
	@Test
	void testTetrominoMergeNewTetromino() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(
				TetrominoShape.ISHAPE.getTetromino(0),
				TetrominoShape.JSHAPE.getTetromino(0)
		)));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		TetrisGridView gridView = player.getGridView();
		for(int i = 0; i < GameManager.DEFAULT_LINES - 2; i++) {
			assertTrue(player.performAction(TetrisAction.DOWN));
		}
		assertFalse(player.performAction(TetrisAction.DOWN));
		/* the tetromino should have been merged and the next tetromino placed */
		assertEquals(gridView.getTetromino(), TetrominoShape.JSHAPE.getTetromino(0));
		assertEquals(gridView.getCoordinates().getLine(), 0);
	}
	
	@Test
	void testTetrisActionHardDropMerge() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(TetrominoShape.ISHAPE.getTetromino(0))));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		TetrisGridView gridView = player.getGridView();
		int col = gridView.getCoordinates().getCol();
		assertTrue(player.performAction(TetrisAction.HARD_DROP));
		/* after hard drop, the tetromino should have been merged to the grid */
		assertEquals(gridView.gridCell(GameManager.DEFAULT_LINES - 1, col), TetrisCell.I);
		assertEquals(gridView.gridCell(GameManager.DEFAULT_LINES - 1, col + 1), TetrisCell.I);
		assertEquals(gridView.gridCell(GameManager.DEFAULT_LINES - 1, col + 2), TetrisCell.I);
		assertEquals(gridView.gridCell(GameManager.DEFAULT_LINES - 1, col + 3), TetrisCell.I);
	}
	
	@Test
	void testTetrisActionHardDropMergeNewTetromino() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(
				TetrominoShape.ISHAPE.getTetromino(0),
				TetrominoShape.JSHAPE.getTetromino(0)
		)));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		TetrisGridView gridView = player.getGridView();
		assertTrue(player.performAction(TetrisAction.HARD_DROP));
		/* the tetromino should have been merged and the next tetromino placed */
		assertEquals(gridView.getTetromino(), TetrominoShape.JSHAPE.getTetromino(0));
		assertEquals(gridView.getCoordinates().getLine(), 0);
	}
	
	@Test
	void testTetrisActionHardDropPoints1() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(TetrominoShape.ISHAPE.getTetromino(0))));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		assertTrue(player.performAction(TetrisAction.HARD_DROP));
		assertEquals(player.getScore(), (GameManager.DEFAULT_LINES - 2) * 2);
	}
	
	@Test
	void testTetrisActionHardDropPoints2() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(TetrominoShape.ISHAPE.getTetromino(0))));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		assertTrue(player.performAction(TetrisAction.DOWN));
		assertTrue(player.performAction(TetrisAction.HARD_DROP));
		assertEquals(player.getScore(), (GameManager.DEFAULT_LINES - 3) * 2);
	}
	
	@Test
	void testTetrisActionSoftDropPoints() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(TetrominoShape.ISHAPE.getTetromino(0))));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		assertTrue(player.performAction(TetrisAction.START_SOFT_DROP));
		assertTrue(player.performAction(TetrisAction.DOWN));
		assertTrue(player.performAction(TetrisAction.DOWN));
		assertTrue(player.performAction(TetrisAction.END_SOFT_DROP));
		assertTrue(player.performAction(TetrisAction.DOWN));
		assertEquals(player.getScore(), 2);
	}
	
	@Test
	void testNoHold() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(
				TetrominoShape.ISHAPE.getTetromino(0),
				TetrominoShape.JSHAPE.getTetromino(0)
		)));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		assertEquals(player.getHeldTetromino(), null);
	}
	
	@Test
	void testTetrisActionHold() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(
				TetrominoShape.ISHAPE.getTetromino(0),
				TetrominoShape.JSHAPE.getTetromino(0)
		)));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		assertTrue(player.performAction(TetrisAction.DOWN));
		assertTrue(player.performAction(TetrisAction.HOLD));
		// we test that the held tetromino is the previous one
		assertEquals(player.getHeldTetromino(), TetrominoShape.ISHAPE.getTetromino(0));
		// there should be now a new tetromino on top of the grid
		assertEquals(player.getGridView().getTetromino(), TetrominoShape.JSHAPE.getTetromino(0));
		assertEquals(player.getGridView().getCoordinates().getLine(), 0);
	}
	
	@Test
	void testTetrisActionHold2() {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(
				TetrominoShape.ISHAPE.getTetromino(0),
				TetrominoShape.JSHAPE.getTetromino(0),
				TetrominoShape.OSHAPE.getTetromino(0)
		)));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		assertTrue(player.performAction(TetrisAction.DOWN));
		assertTrue(player.performAction(TetrisAction.HOLD));
		// we hard drop the new tetromino
		assertTrue(player.performAction(TetrisAction.HARD_DROP));
		// Now OShape is on top, we hold it instead of IShape
		assertTrue(player.performAction(TetrisAction.HOLD));
		// the held tetromino should be OShape and the grid tetromino IShape
		assertEquals(player.getHeldTetromino(), TetrominoShape.OSHAPE.getTetromino(0));
		assertEquals(player.getGridView().getTetromino(), TetrominoShape.ISHAPE.getTetromino(0));
		assertEquals(player.getGridView().getCoordinates().getLine(), 0);
	}
	
	@Test
	void testTetrisActionHoldOptional() {
		/* it usually forbidden to re-hold a tetromno that was just unheld */
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(
				TetrominoShape.ISHAPE.getTetromino(0),
				TetrominoShape.JSHAPE.getTetromino(0),
				TetrominoShape.OSHAPE.getTetromino(0)
		)));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		assertTrue(player.performAction(TetrisAction.DOWN));
		assertTrue(player.performAction(TetrisAction.HOLD));
		// we cannot re-hold the tetromino
		assertFalse(player.performAction(TetrisAction.HOLD));
		assertEquals(player.getGridView().getTetromino(), TetrominoShape.JSHAPE.getTetromino(0));
		// we hard drop the new tetromino
		assertTrue(player.performAction(TetrisAction.HARD_DROP));
		// Now OShape is on top, we hold it instead of IShape
		assertTrue(player.performAction(TetrisAction.HOLD));
		// the held tetromino should be OShape and the grid tetromino IShape
		assertEquals(player.getHeldTetromino(), TetrominoShape.OSHAPE.getTetromino(0));
		assertEquals(player.getGridView().getTetromino(), TetrominoShape.ISHAPE.getTetromino(0));
		assertEquals(player.getGridView().getCoordinates().getLine(), 0);
	}
	
	@Test
	void testPacking() {
		/* it usually forbidden to re-hold a tetromno that was just unheld */
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(
				TetrominoShape.ISHAPE.getTetromino(0),
				TetrominoShape.ISHAPE.getTetromino(0),
				TetrominoShape.OSHAPE.getTetromino(0),
				TetrominoShape.JSHAPE.getTetromino(0)
		)));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		assertTrue(player.performAction(TetrisAction.MOVE_LEFT));
		assertTrue(player.performAction(TetrisAction.MOVE_LEFT));
		assertTrue(player.performAction(TetrisAction.MOVE_LEFT));
		assertTrue(player.performAction(TetrisAction.HARD_DROP));
		assertTrue(player.performAction(TetrisAction.MOVE_RIGHT));
		assertTrue(player.performAction(TetrisAction.HARD_DROP));
		assertTrue(player.performAction(TetrisAction.MOVE_RIGHT));
		assertTrue(player.performAction(TetrisAction.MOVE_RIGHT));
		assertTrue(player.performAction(TetrisAction.MOVE_RIGHT));
		assertTrue(player.performAction(TetrisAction.MOVE_RIGHT));
		int score = player.getScore();
		assertTrue(player.performAction(TetrisAction.HARD_DROP));
		// there is a new tetromino
		TetrisGridView gridView = player.getGridView();
		assertEquals(gridView.getTetromino(), TetrominoShape.JSHAPE.getTetromino(0));
		// the last line is not empty and not full
		assertFalse(gridView.isEmpty(19));
		assertFalse(gridView.isFull(19));
		// the before last is empty
		assertTrue(gridView.isEmpty(18));
		// we have gained one line
		assertEquals(player.getLineScore(), 1);
		// we have made 100 + 18*2 = 136 points
		assertEquals(player.getScore(), score + 136);
	}
	
	@Test
	void testEndGame1() {
		// ending the game because of conflicts
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(
				TetrominoShape.ISHAPE.getTetromino(1),
				TetrominoShape.ISHAPE.getTetromino(1),
				TetrominoShape.ISHAPE.getTetromino(1),
				TetrominoShape.ISHAPE.getTetromino(1),
				TetrominoShape.ISHAPE.getTetromino(1),
				TetrominoShape.ISHAPE.getTetromino(1)
		)));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		for(int i = 0; i < 5; i++) {
			assertEquals(player.getGridView().getTetromino(), TetrominoShape.ISHAPE.getTetromino(1));
			assertTrue(player.performAction(TetrisAction.HARD_DROP));
		}
		assertTrue(player.isOver());
		assertFalse(player.isActive());
		assertThrows(IllegalStateException.class, () -> player.performAction(TetrisAction.HARD_DROP));
		assertThrows(IllegalStateException.class, () -> player.performAction(TetrisAction.DOWN));
	}
	
	@Test
	void testEndGame2() {
		// ending the game because no more tetrominos
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.setTetrominoProvider(TetrominoProvider.listTetrominoProvider(Arrays.asList(
				TetrominoShape.ISHAPE.getTetromino(1)
		)));
		simpleManager.loadNewGame();
		GamePlayer player = simpleManager.getPlayer();
		simpleManager.startPlayer();
		assertTrue(player.performAction(TetrisAction.HARD_DROP));
		assertTrue(player.isOver());
		assertFalse(player.isActive());
		assertThrows(IllegalStateException.class, () -> player.performAction(TetrisAction.HARD_DROP));
		assertThrows(IllegalStateException.class, () -> player.performAction(TetrisAction.DOWN));
	}
	
	@Test
	void testLoadGame1() throws FileNotFoundException, IOException {
		String fileName = "resources/save_1.txt";
		File file = new File(fileName);
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.loadFromFile(file);
		GamePlayer player = simpleManager.getPlayer();
		assertFalse(player.isActive());
		assertEquals(player.getScore(), 90);
		assertEquals(player.getLevel(), 1);
		assertEquals(player.getLineScore(), 0);
		assertEquals(player.getGridView().getTetromino(), TetrominoShape.OSHAPE.getTetromino(0));
		assertEquals(player.getGridView().getCoordinates(), new TetrisCoordinates(3, 4));
		assertTrue(player.getGridView().isEmpty(15));
		assertEquals(player.getGridView().gridCell(19, 0), TetrisCell.S);
	}
	
	@Test
	void testLoadGame2() throws FileNotFoundException, IOException {
		String fileName = "resources/save_2.txt";
		File file = new File(fileName);
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.loadFromFile(file);
		GamePlayer player = simpleManager.getPlayer();
		assertFalse(player.isActive());
		assertEquals(player.getScore(), 448);
		assertEquals(player.getLevel(), 1);
		assertEquals(player.getLineScore(), 2);
		assertEquals(player.getGridView().getTetromino(), TetrominoShape.SSHAPE.getTetromino(0));
		assertEquals(player.getGridView().getCoordinates(), new TetrisCoordinates(6, 5));
		assertTrue(player.getGridView().isEmpty(15));
		assertEquals(player.getGridView().gridCell(19, 0), TetrisCell.T);
	}
	
	@Test
	void testLoadGame3() throws FileNotFoundException, IOException {
		String fileName = "resources/save_3.txt";
		File file = new File(fileName);
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.loadFromFile(file);
		GamePlayer player = simpleManager.getPlayer();
		assertFalse(player.isActive());
		assertEquals(player.getScore(), 2610);
		assertEquals(player.getLevel(), 2);
		assertEquals(player.getLineScore(), 12);
		assertEquals(player.getGridView().getTetromino(), TetrominoShape.JSHAPE.getTetromino(1));
		assertEquals(player.getGridView().getCoordinates(), new TetrisCoordinates(6, 3));
		assertTrue(player.getGridView().isEmpty(10));
		assertFalse(player.getGridView().isEmpty(11));
		assertEquals(player.getGridView().gridCell(19, 0), TetrisCell.I);
	}
	
	@Test
	void testSaveGameEmpty() throws FileNotFoundException {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.loadNewGame();
		simpleManager.startPlayer();
		simpleManager.pausePlayer();
		File saveFile = new File("resources/test_save_empty.txt");
		simpleManager.save(saveFile);
	}
	
	@Test
	void testSaveGameNonEmpty() throws FileNotFoundException {
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.loadNewGame();
		simpleManager.startPlayer();
		GamePlayer player = simpleManager.getPlayer();
		player.performAction(TetrisAction.HARD_DROP);
		simpleManager.pausePlayer();
		File saveFile = new File("resources/test_save_non_empty.txt");
		simpleManager.save(saveFile);
	}
	
	@Test
	void testSaveAndLoadGameNonEmpty() throws FileNotFoundException, IOException {
		String fileName = "resources/test_save_non_empty.txt";
		GameManager simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.loadNewGame();
		simpleManager.startPlayer();
		GamePlayer player = simpleManager.getPlayer();
		player.performAction(TetrisAction.HARD_DROP);
		player.performAction(TetrisAction.DOWN);
		player.performAction(TetrisAction.MOVE_LEFT);
		Tetromino tet = player.getGridView().getTetromino();
		TetrisCoordinates coordinates = player.getGridView().getCoordinates();
		int score = player.getScore();
		simpleManager.pausePlayer();
		File saveFile = new File(fileName);
		simpleManager.save(saveFile);
		
		simpleManager = GameManager.getGameManager(GameType.SIMPLE);
		simpleManager.initialize();
		simpleManager.loadFromFile(new File(fileName));
		player = simpleManager.getPlayer();
		assertEquals(player.getGridView().getTetromino(), tet);
		assertEquals(player.getGridView().getCoordinates(), coordinates);
		assertEquals(player.getScore(), score);
	}
	
}
