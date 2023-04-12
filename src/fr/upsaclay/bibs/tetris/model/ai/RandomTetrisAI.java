package fr.upsaclay.bibs.tetris.model.ai;

import fr.upsaclay.bibs.tetris.TetrisAction;
import fr.upsaclay.bibs.tetris.TetrisMode;
import fr.upsaclay.bibs.tetris.control.player.GamePlayer;
import fr.upsaclay.bibs.tetris.control.player.PlayerType;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGridView;
import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public List<TetrisAction> nextActions(TetrisGridView view, Tetromino onhold, List<Tetromino> nextTetrominos) {
        List<TetrisAction> actions = new ArrayList<>();

        // Add left or right action randomly
        if (random.nextBoolean()) {
            actions.add(TetrisAction.MOVE_LEFT);
        } else {
            actions.add(TetrisAction.MOVE_RIGHT);
        }

        // Add rotate action randomly
        if (random.nextBoolean()) {
            actions.add(TetrisAction.ROTATE_LEFT);
        } else {
        	actions.add(TetrisAction.ROTATE_RIGHT);
        }

        // Add down action
        actions.add(TetrisAction.DOWN);

        return actions;
    }

	
}
