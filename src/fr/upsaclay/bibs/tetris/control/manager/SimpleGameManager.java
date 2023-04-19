package fr.upsaclay.bibs.tetris.control.manager;


import fr.upsaclay.bibs.tetris.control.player.SimpleGamePlayer;

public class SimpleGameManager extends AbstractGameManager{

	@Override
	public void createPlayer() {
		// TODO Auto-generated method stub
		
		pl=new SimpleGamePlayer(this.player_type);	
		pl.initialize( gr,scp, provider);

	}

}
