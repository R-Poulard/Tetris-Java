package fr.upsaclay.bibs.tetris.model.score;

import java.util.List;

import fr.upsaclay.bibs.tetris.TetrisAction;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGridView;

public class Scoring_cleaner implements ScoreComputer {
	int score;
	int level;
	int lines;
	int combo_count=-1;
	boolean went_down;
	int dropped;//permet de savoir quelle type de drop a ete fait
	boolean action_done;//regarde si l'action a bien ete faite
	int depart;//ligne de depart avant le debut du hard drop
	
	public Scoring_cleaner(int score,int level,int lines) {
		this.score=score;
		this.level=level;
		this.lines=lines;
	}@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return level;
	}

	@Override
	public int getLines() {
		// TODO Auto-generated method stub
		return lines;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return score;
	}

	@Override
	public int getComboCount() {
		// TODO Auto-generated method stub
		return combo_count;
	}

	@Override
	public void registerBeforeAction(TetrisAction action, TetrisGridView gridView) {
		// TODO Auto-generated method stub
		action_done=true;
		if(action == TetrisAction.HARD_DROP) {
			dropped=2;
			depart=gridView.getCoordinates().getLine();
		}
		else if(action==TetrisAction.START_SOFT_DROP) {
			dropped=1;

		}
		else if(action==TetrisAction.DOWN) {
			went_down=true;
		}
		if(action==TetrisAction.END_SOFT_DROP) {
			dropped=0;
		}
		
	}

	@Override
	public void registerAfterAction(TetrisGridView gridView) {
	    if (!action_done) {
	        throw new IllegalStateException("No action has been registered");
	    }
	    if(dropped==1 && went_down) {//si on est bien descendu alors on ajoute au score

	    	went_down=false;
			  this.score+=2;
		}
		else if(dropped==2){//si c etait un hard drop alors on calcul 3*nb ligne
		    dropped=0;
			this.score+=3*(gridView.getCoordinates().getLine()-depart);
			depart=0;
		 }
	}
	@Override
	public void registerMergePack(List<Integer> packResult, TetrisGridView gridView) {
		// TODO Auto-generated method stub
		packResult.sort(null);


		if(packResult.size()>0) {//si on a casser des linges alors set un combo
			combo_count++;
		}
		else {//sinon on combo break
			combo_count=-1;
		}
		if(combo_count>0){//ajoute au score le level * combo
			score+=100*this.level*combo_count;

		}
		if(combo_count>this.level) {//level up
			level++;
		}
		
	}

}

