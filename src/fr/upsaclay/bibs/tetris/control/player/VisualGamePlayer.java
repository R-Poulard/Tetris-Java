package fr.upsaclay.bibs.tetris.control.player;

import java.io.PrintStream;

import fr.upsaclay.bibs.tetris.TetrisAction;
import fr.upsaclay.bibs.tetris.control.manager.VisualGameManager;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGrid;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGridView;
import fr.upsaclay.bibs.tetris.model.score.ScoreComputer;
import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;
import fr.upsaclay.bibs.tetris.model.tetromino.TetrominoProvider;

public class VisualGamePlayer implements GamePlayer{

	
	public void initialize(TetrisGrid grid, ScoreComputer scoreComputer, TetrominoProvider provider, VisualGameManager visualGameManager) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public PlayerType getType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setLogPrintStream(PrintStream out) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getLineScore() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isOver() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public TetrisGridView getGridView() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean performAction(TetrisAction action) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Tetromino getHeldTetromino() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize(TetrisGrid grid, ScoreComputer scoreComputer, TetrominoProvider provider) {
		// TODO Auto-generated method stub
		
	}
}
