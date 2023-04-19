package fr.upsaclay.bibs.tetris.control.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import fr.upsaclay.bibs.tetris.TetrisMode;
import fr.upsaclay.bibs.tetris.control.player.GamePlayer;
import fr.upsaclay.bibs.tetris.control.player.PlayerType;
import fr.upsaclay.bibs.tetris.model.tetromino.TetrominoProvider;

/**
 * An interface for the main controller
 * 
 * This controller is in charge of game options and game creation
 * 
 * 
 * @author Viviane Pons
 *
 */

public interface GameManager {
	
	public static final int DEFAULT_LINES = 20;
	public static final int DEFAULT_COLS = 10;
	
	public static final TetrisMode DEFAULT_MODE = TetrisMode.MARATHON;
	public static final TetrominoProvider DEFAULT_PROVIDER = TetrominoProvider.randomTetrominoProvider();
	public static final PlayerType DEFAULT_PLAYER_TYPE = PlayerType.HUMAN;
	
	// Configuration
	
	/**
	 * Initialize the game Manager 
	 * 
	 * Should set the necessary fields with their default values
	 * 
	 * Note: the player is not created at initilization
	 * 
	 * In visual mode, this is where the game frame can be launched
	 */
	public void initialize();

	/**
	 * Sets the game mode
	 * @param mode a TetrisMode
	 */
	public void setGameMode(TetrisMode mode);
	
	/**
	 * Return the game mode
	 * @return a TetrisMode
	 */
	public TetrisMode getGameMode();
	
	
	/**
	 * Sets the number of tours before AI game ends if set at negative the value end when the game is over
	 * @param nb_tours the number of tours
	 */
	public void setNombre_tours(int nb_tours);
	
	/**
	 * Sets the tetromino provider 
	 * @param provider a TetrominoProvider
	 */
	public void setTetrominoProvider(TetrominoProvider provider);
	
	/**
	 * Return the current tetromino povider
	 * @return the TetrominoProvider
	 */
	public TetrominoProvider getTetrominoProvider();
	
	/**
	 * Sets the player type
	 * @param playerType a PlayerType
	 */
	public void setPlayerType(PlayerType playerType);
	
	/**
	 * Return the current player type
	 * @return a PlayerType
	 */
	public PlayerType getPlayerType();
	
	/**
	 * Return the number of lines
	 * @return the number of lines
	 */
	public int getNumberOfLines();
	
	/**
	 * Return the number of cols
	 * @return the number of cols
	 */
	public int getNumberOfCols();
	
	// Actions
	
	
	/**
	 * Creates a player with the correct player type
	 * 
	 * The specific class of the player will depend of the GameType: Simple or Visual
	 * 
	 * If there is no implementation for player type in this game type, throws an 
	 * UnsupportedOperationException
	 */
	public void createPlayer();
	
	/**
	 * Return the player 
	 * @return a GamePlayer
	 */
	public GamePlayer getPlayer();
	
	/**
	 * Load a new empty game
	 * 
	 * This creates a new player if needed and initialize the player with the new game
	 * 
	 * It does not start the player, so the player should be on "pause" i.e. not active
	 */
	public void loadNewGame();
	
	/**
	 * Loads a game read from a file
	 * 
	 * This creates a new player if needed and initialize the player with the new game
	 * 
	 * It does not start the player, so the player should be on "pause" i.e. not active
	 * 
	 * A game file contains information about the game state (grid, score, level, etc.)
	 * 
	 * It does not contain: the tetromino provider, the held tetromino saved in game
	 * 
	 * See project description for file format
	 * 
	 * @param file a file
	 * @throws FileNotFoundException if the file cannot be read
	 * @throws IOException if there is an error while scanning the file following the file format
	 */
	public void loadFromFile(File file) throws FileNotFoundException, IOException;
	
	/**
	 * starts the player (i.e. the actual game)
	 */
	public void startPlayer();
	
	/**
	 * pause the player
	 */
	public void pausePlayer();
	
	/**
	 * Save the game
	 * 
	 * A game file contains information about the game state (grid, score, level, etc.)
	 * 
	 * It does not contain: the tetromino provider, the held tetromino saved in game
	 * 
	 * See project description for file format
	 * 
	 * @param file a file
	 * @throws FileNotFoundException if one cannot write in the file
	 */
	public void save(File file) throws FileNotFoundException;
	
	
	
	/**
	 * Return an instance of GameManager depending on GameType
	 * @param type the gameType (SIMPLE or VISUAL)
	 * @return a GameManager
	 */
	public static GameManager getGameManager(GameType type) {
		if(type==GameType.SIMPLE) {
			return new SimpleGameManager();
		}else {
			return new VisualGameManager();
		}
	}	
}
