package fr.upsaclay.bibs.tetris.model.score;

import java.util.List;

import fr.upsaclay.bibs.tetris.TetrisAction;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGridView;

public class Scoring_marathon implements ScoreComputer{
	int score;
	int level;
	int lines;
	int combo_count=-1;
	
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
		
	}

}
