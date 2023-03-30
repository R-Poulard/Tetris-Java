package fr.upsaclay.bibs.tetris.control.manager;

import fr.upsaclay.bibs.tetris.control.player.GamePlayer;
import fr.upsaclay.bibs.tetris.control.player.PlayerType;
import fr.upsaclay.bibs.tetris.control.player.SimpleGamePlayer;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGrid;
import fr.upsaclay.bibs.tetris.model.score.ScoreComputer;
import fr.upsaclay.bibs.tetris.model.tetromino.TetrominoProvider;

public class SimpleGameManager extends AbstractGameManager{

	@Override
	public void createPlayer() {
		// TODO Auto-generated method stub
		if(player_type==PlayerType.AI) {
			throw new UnsupportedOperationException();
		}
		pl=new SimpleGamePlayer(this.player_type);	
		pl.initialize( gr,scp, provider);
	}

}
