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
		super.start();//start du simplegame player
		ArrayList<Tetromino> tmp=new ArrayList<>();
		tmp.add(this.pr.showNext(0));
		mg.getgame_frame().getgrid().updateNextTetrominos(tmp);//permet d'update l'affichage d1er trtromino
		mg.getgame_frame().getgrid().updateHeldTetromino(null);
		if(this.mg.getGameMode()==TetrisMode.SPACE_CLEANER) {//permet de set les debris sur la cartes 

	
			Random rand = new Random(); 
			for(int i=7;i<mg.getNumberOfLines();i++) {//pour chque ligne

			      int upperbound = 4;

			      int int_random = rand.nextInt(upperbound)+4; //on initialise un nombre aleatoire de rock (entre 4 et 7
			      for(int a=int_random;a>0;a--) {//on essaye de les poser
			    	  boolean posed=false;
			    	  while(posed!=true) {//tant qu'on a pas réussi on retest une position aleatoire sur la ligne
					      int position = rand.nextInt(10); 
					      
			    		  posed=this.grid.setBlock(i,position,TetrisCell.ROCK);
			    		  
			    	  }
			      }
			}
		}
		mg.getgame_frame().getgrid().update();//update la grille pour afficher les debris
	}

	
	public void unpause() {
		active=true;
	}

	@Override
	public void packing(TetrisGrid grid,ScoreComputer sc) {//permet de gerer le packing en fonction du mode de jeu
		if(this.mg.getGameMode()==TetrisMode.SPACE_CLEANER) {//pack avec graviter du space cleaner
			List<Integer> to_break;
			do {//tant que tout les lignes ne sont pas casser on continue a pack (faire tout les pack 1 a 1 nous permet de creer des combo)
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
			for(int i=0;i<grid.numberOfCols();i++) {//check si on a terminer la partie
				if(grid.visibleCell(19, i)==TetrisCell.ROCK) {
					ended=false;
				}
			}
			if(ended) { // si c'est le cas on stop la game dans la vue
				pause();
				mg.over();
				return;
			}
			else {
				mg.getgame_frame().getgrid().updateScore(sc.getScore());//sinon on actualise le score
				mg.getgame_frame().getgrid().updateScoreLines(sc.getLines());
				mg.getgame_frame().getgrid().updateLevel(sc.getLevel());
				mg.getgame_frame().getgrid().update();
			}
			}while(to_break.size()!=0);
		}
		else {//dans les modes de jeu normales
			int post_level=sc.getLevel();//recuper le level avant le packing pour voir si jamais on a changé
			mg.getput().start();//sound box
			mg.getput().setMicrosecondPosition(0);
			
			List<Integer> to_break=grid.pack();
			sc.registerMergePack(to_break, grid);
			if(to_break.size()!=0) {//si on a casser des choses alors l event d'affichage se fait
				mg.getclear().start();
				mg.getclear().setMicrosecondPosition(0);
	
				mg.getgame_frame().getgrid().launchGamePanelEvent(GamePanelEvent.LINES,to_break);
				if(sc.getComboCount()>0) {//si on a un combo alors l event afficher qqchose dans la vue
				mg.getgame_frame().getgrid().launchGamePanelEvent(GamePanelEvent.COMBO,Integer.valueOf(sc.getComboCount()));
				}
				if(post_level!=sc.getLevel()) {//si on a un lvl up

					int pre_level=sc.getLevel();
					//ajuste la vitesse du timer en fonction du niveau
					if(pre_level==9) {
						mg.getgame_frame().getgrid().getTimer().setInitialDelay(150);
					}
					else if(pre_level<9) {
					mg.getgame_frame().getgrid().getTimer().setInitialDelay(mg.getgame_frame().getgrid().getTimer().getInitialDelay()-150);
					}
					else if(pre_level>=10 && pre_level<=12) {
						mg.getgame_frame().getgrid().getTimer().setInitialDelay(60);
						
					}
					else if(pre_level>=13 && pre_level<=15) {
						mg.getgame_frame().getgrid().getTimer().setInitialDelay(30);
						
					}
					mg.getgame_frame().getgrid().setLoopDelay(mg.getgame_frame().getgrid().getTimer().getInitialDelay());
					}
			}
			else {
				mg.getgame_frame().getgrid().launchGamePanelEvent(GamePanelEvent.END_COMBO,0);
				
			}
		}
	}
	
	@Override
	public void next() {//meme chose que la classe mere mais update le gui en meme temps
		super.next();
		ArrayList<Tetromino> tmp=new ArrayList<>();
		tmp.add(this.pr.showNext(0));
		mg.getgame_frame().getgrid().updateNextTetrominos(tmp);
	}
	public boolean performAction(TetrisAction action) {
		boolean res;
		try {
		res=super.performAction(action);//on fait laction via le simplegame player 
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
			case END_SOFT_DROP://change le delay du timer de facon lineaire en fonction du softdrop
				if(mg.getgame_frame().getgrid().getTimer()!=null) {
					mg.getgame_frame().getgrid().setLoopDelay(mg.getgame_frame().getgrid().getTimer().getInitialDelay()-100);
				}
				break;
			case HOLD:
				if(!active) {
					return false;
				}
				mg.getgame_frame().getgrid().updateHeldTetromino(held);//on update le nouveau tetromino eu
				break;
			case HARD_DROP:
				if(!active) {
					return false;
				}
			case START_SOFT_DROP:
				if(mg.getgame_frame().getgrid().getTimer()!=null) {
					mg.getgame_frame().getgrid().setLoopDelay(mg.getgame_frame().getgrid().getTimer().getInitialDelay()+100);
				}
				this.performAction(TetrisAction.DOWN);
				break;
			default:
				break;
			}
			//update la vue
			mg.getgame_frame().getgrid().updateScore(sc.getScore());
			mg.getgame_frame().getgrid().updateScoreLines(sc.getLines());
			mg.getgame_frame().getgrid().updateLevel(sc.getLevel());
			mg.getgame_frame().getgrid().update();
		
		}
		return true;
	}
}
