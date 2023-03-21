package fr.upsaclay.bibs.tetris.control.player;

import java.io.PrintStream;

import fr.upsaclay.bibs.tetris.TetrisAction;
import fr.upsaclay.bibs.tetris.model.grid.TetrisCoordinates;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGrid;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGridView;
import fr.upsaclay.bibs.tetris.model.score.ScoreComputer;
import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;
import fr.upsaclay.bibs.tetris.model.tetromino.TetrominoProvider;

public class SimpleGamePlayer implements GamePlayer {
	
	TetrisGrid grid;
	ScoreComputer sc;
	TetrominoProvider pr;
	PlayerType p;
	PrintStream ps=System.out;
	boolean active=false;
	Tetromino held;
	boolean soft_dropping=false;
	
	public SimpleGamePlayer(PlayerType p,TetrisGrid grid, ScoreComputer scoreComputer, TetrominoProvider provider) {
		this.p=p;
		initialize(grid, scoreComputer, provider);
	}
	
	@Override
	public void initialize(TetrisGrid grid, ScoreComputer scoreComputer, TetrominoProvider provider) {
		this.grid=grid;
		this.sc=scoreComputer;
		this.pr=provider;
	}

	@Override
	public PlayerType getType() {
		// TODO Auto-generated method stub
		return p;
	}

	@Override
	public void setLogPrintStream(PrintStream out) {
		ps=out;
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return sc.getLevel();
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return sc.getScore();
	}

	@Override
	public int getLineScore() {
		// TODO Auto-generated method stub
		return sc.getLines();
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return active;
	}

	@Override
	public void start() {
		active=true;
		grid.setTetromino(pr.next());
		grid.setAtStartingCoordinates();
	}

	@Override
	public void pause() {
		active=false;
	}

	@Override
	public boolean isOver() {
		// TODO Auto-generated method stub
		return grid.hasConflicts();
	}

	@Override
	public TetrisGridView getGridView() {
		// TODO Auto-generated method stub
		return grid.getView();
	}

	@Override
	public boolean performAction(TetrisAction action) {
		// TODO Auto-generated method stub
		if(isOver() || !isActive()) {
			throw new IllegalStateException("Le joueur ne peux pas jouer");
		}
		try {
			boolean res=true;
			TetrisCoordinates new_tr;
			sc.registerBeforeAction(action, grid);
			switch(action) {
			case DOWN:
				new_tr= new TetrisCoordinates(1,0);
				res=grid.tryMove(new_tr);
				sc.registerAfterAction(grid);
				if(res==false) {
					grid.merge();
					sc.registerMergePack(grid.pack(), grid);
					try {
						grid.setTetromino(pr.next());
						grid.setAtStartingCoordinates();
					}
					catch(Exception e) {
						grid.setCoordinates(null);
					}
				}
				break;
			case END_SOFT_DROP:
				soft_dropping=false;
				break;
			case HARD_DROP:
				grid.hardDrop();
				sc.registerAfterAction(grid);
				grid.merge();
				sc.registerMergePack(grid.pack(), grid);
				try {
					grid.setTetromino(pr.next());
					grid.setAtStartingCoordinates();
				}
				catch(Exception e) {
					grid.setCoordinates(null);
				}
				break;
			case HOLD:
				if(held!=grid.getTetromino()) {
					Tetromino t=getHeldTetromino();
					if(t==grid.getTetromino()) {
						return false;
					}
					held=grid.getTetromino();
					if(held != null) {
						if(t!=null) {
							grid.setTetromino(t);
							grid.setAtStartingCoordinates();
						}else {
							try {
								grid.setTetromino(pr.next());
								grid.setAtStartingCoordinates();
							}
							catch(Exception e) {
								grid.setCoordinates(null);
							}
						}
						res=true;
					}
					else {
						res=false;
					}
				}
				else {
					res=false;
				}
				break;
			case MOVE_LEFT:
				new_tr= new TetrisCoordinates(0,-1);
				res=grid.tryMove(new_tr);
				sc.registerAfterAction(grid);
				break;
			case MOVE_RIGHT:
				new_tr= new TetrisCoordinates(0,1);
				res=grid.tryMove(new_tr);
				sc.registerAfterAction(grid);
				break;
			case ROTATE_LEFT:
				res=grid.tryRotateLeft();
				sc.registerAfterAction(grid);
				break;
			case ROTATE_RIGHT:
				res=grid.tryRotateRight();
				sc.registerAfterAction(grid);
				break;
			case START_SOFT_DROP:
				soft_dropping=true;
				break;
			default:
				break;	
		}
		return res;
		}
		catch(IllegalStateException e) {
			return false;
		}
	}

	@Override
	public Tetromino getHeldTetromino() {
		// TODO Auto-generated method stub
		return held;
	}

}