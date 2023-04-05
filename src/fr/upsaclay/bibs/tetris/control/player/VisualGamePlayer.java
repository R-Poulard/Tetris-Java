package fr.upsaclay.bibs.tetris.control.player;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.upsaclay.bibs.tetris.TetrisAction;
import fr.upsaclay.bibs.tetris.TetrisMode;
import fr.upsaclay.bibs.tetris.control.manager.VisualGameManager;
import fr.upsaclay.bibs.tetris.model.grid.TetrisCell;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGrid;
import fr.upsaclay.bibs.tetris.model.score.ScoreComputer;
import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;
import fr.upsaclay.bibs.tetris.view.GamePanelEvent;


public class VisualGamePlayer extends SimpleGamePlayer implements GamePlayer{

	VisualGameManager mg;
	boolean cleaner_over=false;
	
	public VisualGamePlayer(VisualGameManager mg) {
		super(mg.getPlayerType());
		// TODO Auto-generated constructor stub
		this.mg=mg;
	}

	@Override
	public void start() {
		super.start();
		ArrayList<Tetromino> tmp=new ArrayList<>();
		tmp.add(this.pr.showNext(0));
		mg.getgame_frame().getgrid().updateNextTetrominos(tmp);
		mg.getgame_frame().getgrid().updateHeldTetromino(null);
		if(this.mg.getGameMode()==TetrisMode.SPACE_CLEANER) {

	
			Random rand = new Random(); 
			for(int i=18;i<mg.getNumberOfLines();i++) {

			      int upperbound = 4;

			      int int_random = rand.nextInt(upperbound)+4; 
			      for(int a=int_random;a>0;a--) {
			    	  boolean posed=false;
			    	  while(posed!=true) {
					      int position = rand.nextInt(10); 
					      
			    		  posed=this.grid.setBlock(i,position,TetrisCell.ROCK);
			    		  if(posed) {

			    		  }
			    	  }
			      }
			}
		}
		mg.getgame_frame().getgrid().update();
	}

	
	public void unpause() {
		active=true;
	}

	@Override
	public void packing(TetrisGrid grid,ScoreComputer sc) {
		if(this.mg.getGameMode()==TetrisMode.SPACE_CLEANER) {
			List<Integer> to_break;
			do {
			mg.getput().start();
			mg.getput().setMicrosecondPosition(0);
			to_break=grid.pack2();
			
			sc.registerMergePack(to_break, grid);
			if(to_break.size()!=0) {
				mg.getclear().start();
				mg.getclear().setMicrosecondPosition(0);
	
				mg.getgame_frame().getgrid().launchGamePanelEvent(GamePanelEvent.LINES,to_break);
				if(sc.getComboCount()>0) {
				mg.getgame_frame().getgrid().launchGamePanelEvent(GamePanelEvent.COMBO,Integer.valueOf(sc.getComboCount()));
				}
			}
			else {
				mg.getgame_frame().getgrid().launchGamePanelEvent(GamePanelEvent.END_COMBO,0);
				
			}
			boolean ended=true;
			for(int i=0;i<grid.numberOfCols();i++) {
				if(grid.visibleCell(19, i)==TetrisCell.ROCK) {
					ended=false;
				}
			}
			if(ended) {
				cleaner_over=true;
				return;
			}
			else {
				mg.getgame_frame().getgrid().updateScore(sc.getScore());
				mg.getgame_frame().getgrid().updateScoreLines(sc.getLines());
				mg.getgame_frame().getgrid().updateLevel(sc.getLevel());
				mg.getgame_frame().getgrid().update();
			}
			}while(to_break.size()!=0);
		}
		else {
			mg.getput().start();
			mg.getput().setMicrosecondPosition(0);
			List<Integer> to_break=grid.pack();
			sc.registerMergePack(to_break, grid);
			if(to_break.size()!=0) {
				mg.getclear().start();
				mg.getclear().setMicrosecondPosition(0);
	
				mg.getgame_frame().getgrid().launchGamePanelEvent(GamePanelEvent.LINES,to_break);
				if(sc.getComboCount()>0) {
				mg.getgame_frame().getgrid().launchGamePanelEvent(GamePanelEvent.COMBO,Integer.valueOf(sc.getComboCount()));
				}
			}
			else {
				mg.getgame_frame().getgrid().launchGamePanelEvent(GamePanelEvent.END_COMBO,0);
				
			}
		}
	}
	
	@Override
	public void next() {
		super.next();
		ArrayList<Tetromino> tmp=new ArrayList<>();
		tmp.add(this.pr.showNext(0));
		mg.getgame_frame().getgrid().updateNextTetrominos(tmp);
	}
	public boolean performAction(TetrisAction action) {
		boolean res;
		try {
		res=super.performAction(action);
		}
		catch(IllegalStateException e) {
			mg.over();
			return false;
		}
		if(res) {
			switch(action) {
			case DOWN:
				if(!active) {
					return false;
				}
			case END_SOFT_DROP:
				if(mg.getgame_frame().getgrid().getTimer()!=null) {
					mg.getgame_frame().getgrid().setLoopDelay(mg.getgame_frame().getgrid().getTimer().getInitialDelay());
				}
				break;
			case HOLD:
				if(!active) {
					return false;
				}
				mg.getgame_frame().getgrid().updateHeldTetromino(held);
				break;
			case HARD_DROP:
				if(!active) {
					return false;
				}
			case START_SOFT_DROP:
				if(mg.getgame_frame().getgrid().getTimer()!=null) {
					mg.getgame_frame().getgrid().setLoopDelay(100);
				}
				this.performAction(TetrisAction.DOWN);
				break;
			default:
				break;
			}
			mg.getgame_frame().getgrid().updateScore(sc.getScore());
			mg.getgame_frame().getgrid().updateScoreLines(sc.getLines());
			mg.getgame_frame().getgrid().updateLevel(sc.getLevel());
			mg.getgame_frame().getgrid().update();
		if(this.cleaner_over) {
			System.out.println("FALSE RETURNED");
			this.cleaner_over=false;
			return false;
		}
		}
		return true;
	}
}
