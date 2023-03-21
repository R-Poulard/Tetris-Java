package fr.upsaclay.bibs.tetris.control.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import fr.upsaclay.bibs.tetris.TetrisMode;
import fr.upsaclay.bibs.tetris.control.player.GamePlayer;
import fr.upsaclay.bibs.tetris.control.player.PlayerType;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGrid;
import fr.upsaclay.bibs.tetris.model.tetromino.TetrominoProvider;

public abstract class AbstractGameManager implements GameManager {

	
	public  TetrisMode mode;
	public  TetrominoProvider provider;
	public  PlayerType player_type;
	public int cols,lines;
	GamePlayer pl=null;
	TetrisGrid gr;
	@Override
	public void initialize() {
		this.mode=AbstractGameManager.DEFAULT_MODE;
		this.player_type=AbstractGameManager.DEFAULT_PLAYER_TYPE;
		this.cols=AbstractGameManager.DEFAULT_COLS;
		this.lines=AbstractGameManager.DEFAULT_LINES;
		provider=AbstractGameManager.DEFAULT_PROVIDER;
		this.player_type=AbstractGameManager.DEFAULT_PLAYER_TYPE;
	}

	@Override
	public void setGameMode(TetrisMode mode) {
		this.mode=mode;
	}

	@Override
	public TetrisMode getGameMode() {
		// TODO Auto-generated method stub
		return mode;
	}

	@Override
	public void setTetrominoProvider(TetrominoProvider provider) {
		this.provider=provider;
	}

	@Override
	public TetrominoProvider getTetrominoProvider() {
		// TODO Auto-generated method stub
		return provider;
	}

	@Override
	public void setPlayerType(PlayerType playerType) {
		// TODO Auto-generated method stub
		this.player_type=playerType;
	}

	@Override
	public PlayerType getPlayerType() {
		// TODO Auto-generated method stub
		return player_type;
	}

	@Override
	public int getNumberOfLines() {
		// TODO Auto-generated method stub
		return lines;
	}

	@Override
	public int getNumberOfCols() {
		// TODO Auto-generated method stub
		return cols;
	}

	@Override
	public abstract void createPlayer();

	@Override
	public GamePlayer getPlayer() {
		return this.pl;
	}
	
	@Override
	public void loadNewGame() {
		// TODO Auto-generated method stub
		gr=TetrisGrid.getEmptyGrid(lines, cols);
		this.createPlayer();
		this.pausePlayer();
	}

	@Override
	public void loadFromFile(File file) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startPlayer() {
		// TODO Auto-generated method stub
		pl.start();
	}

	@Override
	public void pausePlayer() {
		// TODO Auto-generated method stub
		pl.pause();
	}

	@Override
	public void save(File file) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
	}

}
