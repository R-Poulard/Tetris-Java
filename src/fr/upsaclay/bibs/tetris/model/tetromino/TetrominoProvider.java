package fr.upsaclay.bibs.tetris.model.tetromino;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 * An interface to "provide" tetrominos to the game
 * 
 * It is used by the game manager to get a new tetromino
 * each time it is needed
 * 
 * It offers the possibility to "see" tetrominos in advance
 * 
 * You should offer 2 implementations:
 * 
 * 	   * list tetromino provider (the tetrominos come from a given list).
 * 
 *     * random tetromino provider. The tetrominos are chosen randomly
 *     
 * The first one is easier to implement.
 * 
 * Indeed, for the random tetromino provider, you also have to manage a list
 * of all the dominos that have been "seen" already
 * 
 * @author Viviane Pons
 *
 */
public interface TetrominoProvider {

	/**
	 * Return if the provider still has tetrominos to provide
	 * 
	 * A random tetromino provider always says true
	 * 
	 * A list tetromino provider says true until all the tetrominos
	 * have been rendered
	 * 
	 * @return
	 */
	public boolean hasNext();
	
	/**
	 * Return the next tetromino on the provider
	 * 
	 * For example, if it is a list tetromino provider with t1, t2, t3
	 * 
	 * the first call to next returns t1
	 * the second call returns t2
	 * and the third call returns t3
	 * 
	 * after that, there are no more tetrominos to be provided by this provider
	 * 
	 * on a list tetromino provider, it can throw an exception
	 * if all the tetrominos have been returned.
	 * 
	 * @return a tetromino
	 */
	public Tetromino next();
	
	/**
	 * Show the tetromino to come in n step
	 * for n = 0 this is the next tetromino
	 * for n = 1 this is the next next tetromino
	 * 
	 * If n is bigger than what the provider has left to provide,
	 * it returns null.
	 * 
	 * @param n the number of next steps
	 * @return the tetromino to be returned by next in n steps
	 */
	public Tetromino showNext(int n);
	
	
	/**
	 * Return an instance of a list tetromino provier from the given list
	 * @param tetrominos a list of tetrominos
	 * @return a list tetromino provider
	 */
	public static TetrominoProvider listTetrominoProvider(List<Tetromino> tetrominos) {
		return new TetroProv(tetrominos);
	}
	
	/**
	 * Return an instance of a random tetromino provider
	 * @return a random tetromino provider
	 */
	public static TetrominoProvider randomTetrominoProvider() {
		return new TetroProvRandom();
	}
	
	/**
	 * Return a list tetromino provider using the given file for
	 * reading the tetrominos 
	 * @param file a file
	 * @return a list tetromino provider
	 * @throws FileNotFoundException if the file cannot be read
	 * @throws IOException if it encounters an error reading the file
	 */
	public static TetrominoProvider fromFile(File file) throws FileNotFoundException, IOException {
		Scanner scan = new Scanner(file);
		List<Tetromino> tetrominos = new ArrayList<Tetromino>();
		try {
			while(scan.hasNext()) {
				String shapestr = scan.next();
				int r = scan.nextInt();
				TetrominoShape shape = TetrominoShape.valueOf(shapestr);
				tetrominos.add(shape.getTetromino(r));
			} 
			scan.close();
			return listTetrominoProvider(tetrominos);
		} catch(Exception e) {
			scan.close();
			throw new IOException(e);
		}
		
	}
	
	
}
