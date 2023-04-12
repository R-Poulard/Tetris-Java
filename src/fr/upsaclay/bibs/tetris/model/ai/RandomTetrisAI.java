package fr.upsaclay.bibs.tetris.model.ai;

import java.util.List;
import java.util.Random;

import fr.upsaclay.bibs.tetris.TetrisAction;
import fr.upsaclay.bibs.tetris.TetrisMode;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGridView;
import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;

public class RandomTetrisAI implements TetrisAI {

    private TetrisMode mode;
    private Random random = new Random();

    @Override
    public void setTetrisMode(TetrisMode mode) {
        this.mode = mode;
    }

    @Override
    public TetrisMode getTetrisMode() {
        return mode;
    }

    @Override
    public TetrisAction nextActions(TetrisGridView view, Tetromino onhold, List<Tetromino> nextTetrominos) {
    	//On se content de faire une action aleatoire sans prendre compte de l'envirronement de jeu (manque de temps)
    	//On change les proba de chaque action pour que les action les plus decisif au une change plus faible de ce réaliser
    	int nb=random.nextInt(20);
    	switch(nb) {
    		case 0: 
    			return TetrisAction.HARD_DROP;
	    	case 1:case 2:
	    		return TetrisAction.HOLD;
	    	case 3:case 4:
	    		return TetrisAction.START_SOFT_DROP;
	    	case 5:
	    		return TetrisAction.END_SOFT_DROP;
	    	case 6:case 7:case 8:case 9:
	    		return TetrisAction.MOVE_LEFT;
	    	case 10:case 11:case 12:case 13:
	    		return TetrisAction.MOVE_RIGHT;
	    	case 14:case 15:case 16:
	    		return TetrisAction.ROTATE_LEFT;
	    	case 17:case 18:case 19:
	    		return TetrisAction.ROTATE_RIGHT;

    	}
    	return null;

    }

	
}
