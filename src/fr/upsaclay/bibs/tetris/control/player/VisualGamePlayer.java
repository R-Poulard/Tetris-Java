package fr.upsaclay.bibs.tetris.control.player;


import java.util.ArrayList;
import java.util.List;

import fr.upsaclay.bibs.tetris.TetrisAction;
import fr.upsaclay.bibs.tetris.control.manager.VisualGameManager;
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
		mg.getgame_frame().getgrid().update();
	}

	
	public void unpause() {
		active=true;
	}

	@Override
	public void packing(TetrisGrid grid,ScoreComputer sc) {
		List<Integer> to_break=grid.pack();
		sc.registerMergePack(to_break, grid);
		System.out.println("Ici");
		if(to_break.size()!=0) {
			mg.getgame_frame().getgrid().launchGamePanelEvent(GamePanelEvent.LINES,to_break);
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
