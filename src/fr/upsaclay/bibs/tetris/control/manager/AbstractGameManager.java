package fr.upsaclay.bibs.tetris.control.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import fr.upsaclay.bibs.tetris.TetrisMode;
import fr.upsaclay.bibs.tetris.control.player.GamePlayer;
import fr.upsaclay.bibs.tetris.control.player.PlayerType;
import fr.upsaclay.bibs.tetris.model.grid.TetrisCell;
import fr.upsaclay.bibs.tetris.model.grid.TetrisCoordinates;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGrid;
import fr.upsaclay.bibs.tetris.model.score.ScoreComputer;
import fr.upsaclay.bibs.tetris.model.tetromino.TetrominoProvider;
import fr.upsaclay.bibs.tetris.model.tetromino.TetrominoShape;

public abstract class AbstractGameManager implements GameManager {

	
	public  TetrisMode mode;
	public  TetrominoProvider provider;
	public  PlayerType player_type;
	public int cols,lines;
	GamePlayer pl=null;
	ScoreComputer scp=null;
	TetrisGrid gr;
	@Override
	public void initialize() {
		this.mode=AbstractGameManager.DEFAULT_MODE;
		this.player_type=AbstractGameManager.DEFAULT_PLAYER_TYPE;
		this.cols=AbstractGameManager.DEFAULT_COLS;
		this.lines=AbstractGameManager.DEFAULT_LINES;
		provider=AbstractGameManager.DEFAULT_PROVIDER;
		this.player_type=AbstractGameManager.DEFAULT_PLAYER_TYPE;
	}

	@Override
	public void setGameMode(TetrisMode mode) {
		this.mode=mode;
	}

	@Override
	public TetrisMode getGameMode() {
		// TODO Auto-generated method stub
		return mode;
	}

	@Override
	public void setTetrominoProvider(TetrominoProvider provider) {
		this.provider=provider;
	}

	@Override
	public TetrominoProvider getTetrominoProvider() {
		// TODO Auto-generated method stub
		return provider;
	}

	@Override
	public void setPlayerType(PlayerType playerType) {
		// TODO Auto-generated method stub
		this.player_type=playerType;
	}

	@Override
	public PlayerType getPlayerType() {
		// TODO Auto-generated method stub
		return player_type;
	}

	@Override
	public int getNumberOfLines() {
		// TODO Auto-generated method stub
		return lines;
	}

	@Override
	public int getNumberOfCols() {
		// TODO Auto-generated method stub
		return cols;
	}

	@Override
	public abstract void createPlayer();

	@Override
	public GamePlayer getPlayer() {
		return this.pl;
	}
	
	@Override
	public void loadNewGame() {
		// TODO Auto-generated method stub
		gr=TetrisGrid.getEmptyGrid(lines, cols);
		scp=ScoreComputer.getScoreComputer(getGameMode());
		this.createPlayer();
		this.pausePlayer();
	}

	@Override
	public void loadFromFile(File file) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(file);
		try {
			String gameMode=sc.nextLine();
			int score=sc.nextInt();
			int level=sc.nextInt();
			int linescore=sc.nextInt();
			sc.nextLine();
			String tetromino=sc.nextLine();
			int rotation=sc.nextInt();
			int lines=sc.nextInt();
			int cols=sc.nextInt();
			String pointeur="";
			int nb_lines=0;
			int nb_cols=0;
			sc.nextLine();
			ArrayList<String[]> tr=new ArrayList<String[]>();
			while(sc.hasNextLine()) {
				pointeur=sc.nextLine();
				if(pointeur.equals("")) {
					if(sc.hasNextLine()) {
						throw new IOException("ligne vide a millieu du file");
					}
					break;
				}
				else {
					nb_lines++;
					String[] lecture=pointeur.split(" ");
					if(lecture.length!=nb_cols && nb_cols!=0) {
						throw new IOException("nombre de colonnes variant");
					}
					else if(nb_cols==0) {
						nb_cols=lecture.length;
					}
					tr.add(lecture);
				}
			}
			TetrisCell[][] pattern=new TetrisCell[nb_lines][nb_cols];
			for(int i=0;i<nb_lines;i++) {
				String[] tmp=tr.get(i);
				for(int y=0;y<tmp.length;y++) {
					pattern[i][y]=TetrisCell.valueOf(tmp[y]);
				}
			}
			this.gr=TetrisGrid.getEmptyGrid(nb_lines, nb_cols);
			gr.initiateCells(pattern);
			this.mode=TetrisMode.valueOf(gameMode);
			this.player_type=PlayerType.HUMAN;
			this.cols=nb_cols;
			this.lines=nb_lines;
			this.provider=AbstractGameManager.DEFAULT_PROVIDER;
			this.player_type=AbstractGameManager.DEFAULT_PLAYER_TYPE;
			this.gr.setTetromino(TetrominoShape.valueOf(tetromino).getTetromino(rotation));
			this.gr.setCoordinates(new TetrisCoordinates(lines,cols));
			scp=ScoreComputer.getScoreComputer(getGameMode(), score, level, linescore);
			this.createPlayer();
			this.pausePlayer();
			sc.close();
		}
		catch(NoSuchElementException e2) {
			e2.printStackTrace();
			throw new IOException("mauvais formatage");
		}
	}

	@Override
	public void startPlayer() {
		// TODO Auto-generated method stub
		pl.start();
	}

	@Override
	public void pausePlayer() {
		// TODO Auto-generated method stub
		pl.pause();
	}

	@Override
	public void save(File file) throws FileNotFoundException {
		// TODO Auto-generated method stub
		PrintStream m=new PrintStream(file);
		m.println(this.getGameMode());
		m.println(scp.getScore());
		m.println(scp.getLevel());
		m.println(scp.getLines());
		m.println(gr.getTetromino().getShape());
		m.println(gr.getTetromino().getRotationNumber());
		m.println(gr.getCoordinates().getLine());
		m.println(gr.getCoordinates().getCol());
		gr.printGrid(m);
		m.println();
	}

}
