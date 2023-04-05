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
		if(action == TetrisAction.HARD_DROP) {
	        //récupère la position courante du tétrmino et stocker dans saved
			currentTetromino = gridView.getTetromino();
	        savedTetrominoPosition = gridView.getCoordinates();
	    //cette valeur : pour calculer le score 
	    //en fonction de la distance parcourue par le tétrmino lors du "hard drop"
		}
	}

	@Override
	public void registerAfterAction(TetrisGridView gridView) {
	    if (savedTetrominoPosition == null) {
	        throw new IllegalStateException("No action has been registered");
	    }

	    TetrisCoordinates finalPosition = gridView.getCoordinates();
	    int distance = finalPosition.getLine() - savedTetrominoPosition.getLine();

	    // Calculate score based on number of cleared lines
	    int numClearedLines = this.getLines();
	    if (numClearedLines > 0) {
	        int baseScore = 0;
	        switch (numClearedLines) {
	            case 1:
	                baseScore = 40;
	                break;
	            case 2:
	                baseScore = 100;
	                break;
	            case 3:
	                baseScore = 500;
	                break;
	            case 4:
	                baseScore = 1200;
	                break;
	        }
	        
	        // Add combo bonus to base score
	        int comboBonus = 0;
	        if (combo_count >= 1) {
	            comboBonus = (combo_count + 1) * 50;
	        }
	        score += (baseScore + comboBonus) * level;

	        // Increase combo counter if a line is cleared
	        if (numClearedLines >= 1) {
	            combo_count++;
	        } else { // Reset combo counter if no lines are cleared
	        	combo_count = -1;
	        }
	    } else { // Reset combo counter if no lines are cleared
	    	combo_count = -1;
	    }

	    if (distance > 0) {
	        score += distance * level;
	    }

	    savedTetrominoPosition = null;
	    currentTetromino = null;
	}

	//public void registerMergePack(List<Integer> packResult, TetrisGridView gridView) {

	//	lines = packResult.size();

	//	level = getLevel();

	//	combo_count = getComboCount();

	//	score = getScore();

	//	int point = 0;


	//	if (lines > 0) {

	//	combo_count ++;

	//	} else {

	//	combo_count = -1;

	//	}

	//	switch (lines) {

	//	case 1:

	//	point = 40 * (level);

	//	break;

	//	case 2:

	//	point = 100 * (level);

	//	break;

	//	case 3:

	//	point = 500 * (level);

	//	break;

	//	case 4:

	//	point = 1200 * (level);

	//	break;

	//	}

	//	point = point + 50 * combo_count * level;

	//	score += point;

	//	lines += packResult.size();


	//	}

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
