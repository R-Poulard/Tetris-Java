package fr.upsaclay.bibs.tetris.view;
/**
 * A super interface for the frame and panels of the game
 * 
 * @author viviane
 *
 */
public interface GameViewPanel {
	
	/**
	 * Run all needed initializations
	 */
	public void initialize();

	/**
	 * Draw itself for the "management view" (before a game is started, 
	 * or in between games)
	 * 
	 * (add management buttons, menus and stuff)
	 */
	public void drawManagementView();
	
	/**
	 * Draw itself for the "game play view": the game is going on
	 * 
	 * (remove management buttons, add needed buttons like "pause", draw
	 * the actual game...)
	 */
	public void drawGamePlayView();
	
	/**
	 * Draw itself for when the game is on pause
	 */
	public void drawGamePauseView();
	
	/**
	 * Draw itself for when the game is over (show "Game over", adds a restart button, etc.)
	 */
	public void drawEndGameView();
}
