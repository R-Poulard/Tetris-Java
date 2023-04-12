package fr.upsaclay.bibs.tetris.control.manager;

import fr.upsaclay.bibs.tetris.control.player.PlayerType;
import fr.upsaclay.bibs.tetris.control.player.SimpleGamePlayer;

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
	// rien de plus a mettre car Simple Game Manager utilise le initialize deja fait auparavant
}
