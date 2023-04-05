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
			System.out.println("Dedans");
			Random rand = new Random(); 
			for(int i=8;i<mg.getNumberOfLines();i++) {
				System.out.println("Ici");
			      int upperbound = 4;
			      // Generating random values from 0 - 24 
			      // using nextInt()
			      int int_random = rand.nextInt(upperbound)+4; 
			      for(int a=int_random;a>0;a--) {
			    	  System.out.println("la");
			    	  boolean posed=false;
			    	  while(posed!=true) {
					      int position = rand.nextInt(10); 
					      
			    		  posed=this.grid.setBlock(i,position,TetrisCell.ROCK);
			    		  if(posed) {
			    		  System.out.println(posed);
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
		mg.getput().start();
		mg.getput().setMicrosecondPosition(0);
		List<Integer> to_break=grid.pack();
		sc.registerMergePack(to_break, grid);
		if(to_break.size()!=0) {
			mg.getclear().start();
			mg.getclear().setMicrosecondPosition(0);
			mg.getgame_frame().getgrid().launchGamePanelEvent(GamePanelEvent.LINES,to_break);
		}
		if(this.mg.getGameMode()==TetrisMode.SPACE_CLEANER) {
			for(int i=0;i<grid.numberOfCols();i++) {
				if(grid.visibleCell(19, i)==TetrisCell.ROCK) {
					return;
				}
			}
			mg.over();
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
			mg.getgame_frame().getgrid().update();
		}
	
		return true;
	}
}
