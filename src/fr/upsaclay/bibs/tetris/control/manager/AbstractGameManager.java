package fr.upsaclay.bibs.tetris.control.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import fr.upsaclay.bibs.tetris.TetrisMode;
import fr.upsaclay.bibs.tetris.control.player.GamePlayer;
import fr.upsaclay.bibs.tetris.control.player.PlayerType;
import fr.upsaclay.bibs.tetris.model.ai.RandomTetrisAI;
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
	int nb_tours; //nombre de tour pour une partie AI avant de se terminer
	//Permet de garder un pointeur vers le joueurs pour le pauser ou le start
	GamePlayer pl=null;
	//a donner au player
	ScoreComputer scp=null;
	TetrisGrid gr;
	@Override
	public void initialize() {
		this.nb_tours=-1;
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


	public void setNombre_tours(int nb_tours) {
		this.nb_tours=nb_tours;
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
		gr=TetrisGrid.getEmptyGrid(lines, cols);//nouvelle grid, nouveau score computer
		scp=ScoreComputer.getScoreComputer(getGameMode());
		createPlayer();
		pausePlayer();//player mis en pause au début du jeu
		if(this instanceof SimpleGameManager && this.player_type==PlayerType.AI) {
			this.pl.start();
			party_computer(nb_tours);
		}
	}
	
	/*
	 * Fonction permettant de lancer une partie ordinateur et de l'enregistré lorsqu'elle est fini ou apreès X tours
	 * si X==-1 alors on ne s'arrête pas tant que la partie n'est pas fini.
	 */
	public void party_computer(int tour) {

		RandomTetrisAI ai=new RandomTetrisAI();
		int compteur=0;
		while(pl.isActive() && compteur!=tour) {
			pl.performAction(ai.nextActions(gr, null, null));
			compteur++;
		}
		File saveFile = new File("resources/save_ai.txt");
		PrintStream m;
		try {
			m = new PrintStream(saveFile);
			m.println(this.getGameMode());
			m.println(scp.getScore());
			m.println(scp.getLevel());
			m.println(scp.getLines());
			if(pl.isActive()) {
				m.println(gr.getTetromino().getShape());
				m.println(gr.getTetromino().getRotationNumber());
				m.println(gr.getCoordinates().getLine());
				m.println(gr.getCoordinates().getCol());
			}
			else {
				m.println("Partie Termine");
			}
			gr.printGrid(m);
			m.println();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur d'enregistrement");
		}
	}

	@Override
	public void loadFromFile(File file) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(file);//on essaye de lire
		try {
			//recupere les information pres grille
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
			ArrayList<String[]> tr=new ArrayList<String[]>();//on recupere les lignes une a une
			while(sc.hasNextLine()) {
				pointeur=sc.nextLine();
				if(pointeur.equals("")) {
					if(sc.hasNextLine()) {
						throw new IOException("ligne vide a millieu du file");
					}
					break;
				}
				else {
					nb_lines++;//on garde un int pour compter le nombre de ligne
					String[] lecture=pointeur.split(" ");
					if(lecture.length!=nb_cols && nb_cols!=0) {//test la malformation de la grille
						throw new IOException("nombre de colonnes variant");
					}
					else if(nb_cols==0) {
						nb_cols=lecture.length;
					}
					tr.add(lecture);
				}
			}//Instanciation de la grille avec les donner recuperer
			TetrisCell[][] pattern=new TetrisCell[nb_lines][nb_cols];
			//pour chaque lighe on va creer une ligne de TetrisCell a son image
			for(int i=0;i<nb_lines;i++) {
				String[] tmp=tr.get(i);
				for(int y=0;y<tmp.length;y++) {
					pattern[i][y]=TetrisCell.valueOf(tmp[y]);
				}
			}
			//on set la partie en instanciant la tetris grid ( avec la matrice etablie juste avant
			gr=TetrisGrid.getEmptyGrid(nb_lines, nb_cols);
			gr.initiateCells(pattern);
			mode=TetrisMode.valueOf(gameMode);
			player_type=PlayerType.HUMAN;//default car non preciser
			
			provider=AbstractGameManager.DEFAULT_PROVIDER;//default car non preciser
			player_type=AbstractGameManager.DEFAULT_PLAYER_TYPE;//default car non preciser
			//On ajoute le tetromino (on a recuperer sa classe en string qu on essaye d instancier)
			gr.setTetromino(TetrominoShape.valueOf(tetromino).getTetromino(rotation));
			gr.setCoordinates(new TetrisCoordinates(lines,cols));
			scp=ScoreComputer.getScoreComputer(getGameMode(), score, level, linescore);//score computer a partir des informations
			createPlayer();
			pausePlayer();
			sc.close();
		}
		catch(Exception e2) {
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
		PrintStream m=new PrintStream(file);//nous permet de print sans trop nous soucier du type d'entier ou du rajout de \n a la fin car println le fait pour nous
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
