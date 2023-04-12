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
	boolean soft_dropping=false;//permet de ne pas faire deux start_drop 
	boolean already_hold=false;//neceassaire pour faire que 1 hold par tour
	
	public SimpleGamePlayer(PlayerType p) {
		this.p=p;
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
		return grid.hasConflicts() || !pr.hasNext();
	}

	@Override
	public TetrisGridView getGridView() {
		// TODO Auto-generated method stub
		return grid.getView();
	}

	public void packing(TetrisGrid grid,ScoreComputer sc) {//permet d'etre override par le visual game manager
		sc.registerMergePack(grid.pack(), grid);
	}
	
	public void next() {
		grid.setTetromino(pr.next());
		grid.setAtStartingCoordinates();
	}
	@Override
	public boolean performAction(TetrisAction action) {
		// TODO Auto-generated method stub
		if(!isActive()) {
			
			throw new IllegalStateException("Le joueur ne peut pas jouer");
		}
		try {
			boolean res=true;
			TetrisCoordinates new_tr;
			sc.registerBeforeAction(action, grid);//set du score computer avant tout action
			switch(action) {
			case DOWN:
				new_tr= new TetrisCoordinates(1,0);
				res=grid.tryMove(new_tr);
				sc.registerAfterAction(grid);
				if(res==false) {//si on a pas pue
					this.already_hold=false;
					grid.merge();
					packing(grid,sc);
					//on regarde s'il faut finir la game
					if(pr.hasNext()) {
						next();
						if(grid.hasConflicts()) {
							pause();
						}
					}
					else {
						grid.setCoordinates(null);
						pause();
					}
				}
				break;
			case END_SOFT_DROP:
				soft_dropping=false;
				break;
			case HARD_DROP:
				
				grid.hardDrop();//hardrop
				sc.registerAfterAction(grid);
				this.already_hold=false;//nouveau tetromino, on reautorise le hold
				grid.merge();
				packing(grid,sc);
				if(pr.hasNext()) {
					next();
					if(grid.hasConflicts()) {
						pause();
					}
				}
				else {
					grid.setCoordinates(null);
					pause();
				}
				break;
			case HOLD:
				if(held!=grid.getTetromino() && already_hold!=true) {//si on a deja tenu un tetromino pendant le tour on autorise pas le held
					Tetromino t=getHeldTetromino();
					if(t==grid.getTetromino()) {
						return false;
					}
					held=grid.getTetromino();
					if(held != null) {
						already_hold=true;
						if(t!=null) {//on remet le tetromino held au debut
							grid.setTetromino(t);
							grid.setAtStartingCoordinates();
						}else {//regarde si y a un conflit avec la grille
							if(pr.hasNext()) {
								next();
								if(grid.hasConflicts()) {
									pause();
								}
							}
							else{
								grid.setCoordinates(null);
								pause();
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
			//action 'basique' on essaye de le faire
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
