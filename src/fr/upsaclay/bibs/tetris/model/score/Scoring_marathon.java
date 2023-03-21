package fr.upsaclay.bibs.tetris.model.score;

import java.util.List;

import fr.upsaclay.bibs.tetris.TetrisAction;
import fr.upsaclay.bibs.tetris.model.grid.TetrisCoordinates;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGridView;

public class Scoring_marathon implements ScoreComputer{
	int score;
	int level;
	int lines;
	int combo_count=-1;
	TetrisCoordinates tr=null;
	
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
	}

	@Override
	public void registerAfterAction(TetrisGridView gridView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerMergePack(List<Integer> packResult, TetrisGridView gridView) {
		// TODO Auto-generated method stub
		packResult.sort(null);
		int score_add=0;
		int compteur=0;
		for(int i=0;i<packResult.size()-1;i++) {
			if(packResult.get(i)==packResult.get(i)-1) {
				compteur++;
			}
			else {
				switch(compteur) {
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
				compteur=0;
			}
		}
		this.score=this.getScore()+score_add;
	}

}
