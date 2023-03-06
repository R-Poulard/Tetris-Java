package fr.upsaclay.bibs.tetris.view;

/**
 * List of game panel evens:
 * i.e. things that happen in the game that require a visual reaction
 * 
 * (such as a message or an animation or whatever)
 * 
 * @author Viviane Pons
 *
 */
public enum GamePanelEvent {
	/**
	 * The player has been making full lines
	 */
	LINES,
	/**
	 * The player has made a combo
	 */
	COMBO;
	
}
