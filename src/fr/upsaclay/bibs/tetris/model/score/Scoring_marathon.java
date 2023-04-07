package fr.upsaclay.bibs.tetris.model.score;

import java.util.ArrayList;
import java.util.List;

import fr.upsaclay.bibs.tetris.TetrisAction;
import fr.upsaclay.bibs.tetris.model.grid.TetrisCoordinates;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGridView;
import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;

public class Scoring_marathon implements ScoreComputer{
	int score;
	int level;
	int lines;
	int combo_count=-1;
	boolean went_down;
	int dropped;
	boolean action_done;
	int depart;
	public Scoring_marathon(int score,int level,int lines) {
		this.score=score;
		this.level=level;
		this.lines=lines;
	}
	@Override
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
	    if(dropped==1 && went_down) {

	    	went_down=false;
			  this.score+=1;
		}
		else if(dropped==2){
		    dropped=0;
			this.score+=2*(gridView.getCoordinates().getLine()-depart);
			depart=0;
		 }
	}
	@Override
	public void registerMergePack(List<Integer> packResult, TetrisGridView gridView) {
		// TODO Auto-generated method stub
		packResult.sort(null);


		if(packResult.size()>0) {
			combo_count++;
		}
		else {
			combo_count=-1;
		}
		if(combo_count>0){
			score+=50*this.level*combo_count;

		}
		int score_add=0;
		int compteur=1;
		
		List<Integer> compte=new ArrayList<Integer>();
		for(int i=0;i<packResult.size();i++) {
			if(i+1<packResult.size() && packResult.get(i)==packResult.get(i+1)-1) {
				compteur++;
			}
			else {
				compte.add(Integer.valueOf(compteur));
				compteur=1;
			}
		}
		for(int i=0;i<compte.size();i++) {
			switch(compte.get(i)) {
			case 1: score_add+=100*this.getLevel();
			break;
			case 2:score_add+=300*this.getLevel();
			break;
				
			case 3:
				score_add+=500*this.getLevel();
				break;
			case 4:
				score_add+=800*this.getLevel();
				break;
			}
		}
		this.lines+=packResult.size();
		if(lines%10==0 && packResult.size()>0) {
			this.level++;
			System.out.println("ICCCIIIIII");
		}
		this.score=this.getScore()+score_add;
	}

}
