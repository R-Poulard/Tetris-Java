package fr.upsaclay.bibs.tetris.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.upsaclay.bibs.tetris.model.grid.SynchronizedView;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGridView;
import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;

public class GamePanelImpl extends JPanel implements GamePanel{
	int nblines=20;
	int nbcols=10;
	TetrisGridView grille;
	int loopdelay;
	int score;
	int ligne;
	int level;
	Tetromino held;
	List<Tetromino> next;
	
	Grille grille_de_jeu;
	TetroCell holded;
	TetroCell nexted;
	JPanel aux;
	
	JLabel jlscore;
	JLabel jlligne;
	JLabel jllevel;
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		aux=new JPanel();
		this.add(aux);
		aux.setLayout(new BoxLayout(aux,BoxLayout.Y_AXIS));
		aux.setBounds(aux.getParent().getWidth()*6/10+5,5,aux.getParent().getWidth()*4/10-5,aux.getParent().getHeight()-15);
		
		grille_de_jeu=new Grille();
		this.add(grille_de_jeu);
		grille_de_jeu.setBounds(5,5,grille_de_jeu.getParent().getWidth()*6/10,grille_de_jeu.getParent().getHeight()-15);
		 
		holded=new TetroCell();
		aux.add(holded);
		holded.setMaximumSize(new Dimension(holded.getParent().getWidth()*4/7,holded.getParent().getWidth()*4/7));
		holded.setMinimumSize(new Dimension(holded.getParent().getWidth()*4/7,holded.getParent().getWidth()*4/7));
		
		aux.add(Box.createRigidArea(new Dimension(0, 10)));
		nexted=new TetroCell();
		aux.add(nexted);
		nexted.setMaximumSize(new Dimension(nexted.getParent().getWidth()*4/7,nexted.getParent().getHeight()*1/5));

		jlscore=new JLabel("Score: 0");
		jlligne=new JLabel("Lignes: 0");
		jllevel=new JLabel("Level: 0");
		aux.add(Box.createRigidArea(new Dimension(0, 35)));
		aux.add(jllevel);
		aux.add(Box.createRigidArea(new Dimension(0, 35)));
		aux.add(jlligne);
		aux.add(Box.createRigidArea(new Dimension(0, 35)));
		aux.add(jlscore);
	}
	
	public class Grille extends JPanel{
		
		SynchronizedView grid;
		JPanel[][] grille;
		
		private Grille() {
			super();
			this.setLayout(new GridLayout(nblines,nbcols));
			grille=new JPanel[nblines][nbcols];
			for(int i=0;i<nblines;i++) {
				for(int y=0;y<nbcols;y++) {
					grille[i][y]= new JPanel();
					grille[i][y].setBackground(new Color(0,0,i*5+y*5));
					this.add(grille[i][y]);
				}
			}
		}
		
		public void update(){
			for(int i=0;i<nblines;i++) {
				for(int y=0;y<nbcols;y++) {
					switch(grid.gridCell(i, y)) {
					case EMPTY:
						grille[i][y].setBackground(Color.WHITE);
						break;
					case GREY:
						break;
					case I:
						grille[i][y].setBackground(Color.CYAN);
						break;
					case J:
						grille[i][y].setBackground(Color.RED);
						break;
					case L:
						grille[i][y].setBackground(Color.BLUE);
						break;
					case O:
						grille[i][y].setBackground(Color.YELLOW);
						break;
					case S:
						grille[i][y].setBackground(Color.GREEN);
						break;
					case T:
						grille[i][y].setBackground(Color.MAGENTA);
						break;
					case Z:
						grille[i][y].setBackground(Color.orange);
						break;
					default:
						break;
					}
				}
			}
			this.repaint();
		}
		
	}
	
	public class TetroCell extends JPanel{
		

		
		JPanel main;
		private TetroCell() {
			this.setLayout(null);
			this.setBackground(Color.WHITE);
			
		}
		
		void display_tetromino(){
			main=new JPanel(new GridLayout(held.getBoxSize(),held.getBoxSize()));
			for(int i=0;i<held.getBoxSize();i++) {
				for(int y=0;y<held.getBoxSize();y++) {
					JPanel jp=new JPanel();
					main.add(jp);
					switch(held.cell(i, y)) {
						case EMPTY:
							jp.setBackground(Color.WHITE);
							break;
						case GREY:
							break;
						case I:
							System.out.println("lol");
							jp.setBackground(Color.CYAN);
							break;
						case J:
							jp.setBackground(Color.CYAN);
							break;
						case L:
							jp.setBackground(Color.CYAN);
							break;
						case O:
							jp.setBackground(Color.CYAN);
							break;
						case S:
							jp.setBackground(Color.CYAN);
							break;
						case T:
							jp.setBackground(Color.CYAN);
							break;
						case Z:
							jp.setBackground(Color.CYAN);
							break;
					}
				}
			}
			
			this.add(main);
			switch(held.getShape()) {
			case ISHAPE:
				main.setBounds(20,20,175,175);
				break;
			case JSHAPE:
				main.setBounds(10,35,150,150);
				break;
			case LSHAPE:
				main.setBounds(10,35,150,150);
				break;
			case OSHAPE:
				main.setBounds(60,60,100,100);
				break;
			case SSHAPE:
				main.setBounds(10,35,150,150);
				break;
			case TSHAPE:
				main.setBounds(35,35,150,150);
				break;
			case ZSHAPE:
				main.setBounds(10,35,150,150);
				break;
			default:
				break;
			
			}

			
		}
		
	}

	@Override
	public void drawManagementView() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}

	@Override
	public void drawGamePlayView() {
		// TODO Auto-generated method stub
		this.setVisible(true);
		this.startActionLoop();
	}

	@Override
	public void drawGamePauseView() {
		// TODO Auto-generated method stub
		this.setVisible(true);
		this.pauseActionLoop();
	}

	@Override
	public void drawEndGameView() {
		// TODO Auto-generated method stub
		this.setVisible(true);
		this.pauseActionLoop();
	}

	@Override
	public void setNumberOfLines(int nblines) {
		// TODO Auto-generated method stub
		this.nblines=nblines;
	}

	@Override
	public void setNumberOfCols(int nbcols) {
		// TODO Auto-generated method stub
		this.nbcols=nbcols;
	}

	@Override
	public void setGridView(TetrisGridView view) {
		// TODO Auto-generated method stub
		this.grille=view;
		this.nbcols=view.numberOfLines();
		this.nbcols=view.numberOfCols();
		this.grille_de_jeu.update();
	}

	@Override
	public void setLoopAction(ActionListener listener) {
		// TODO Auto-generated method stub
	}

	@Override
	public void startActionLoop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pauseActionLoop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoopDelay(int ms) {
		// TODO Auto-generated method stub
		this.loopdelay=ms;
	}

	@Override
	public void launchGamePanelEvent(GamePanelEvent event, Object attach) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateScore(int score) {
		// TODO Auto-generated method stub
		this.score=score;
		jlscore.setText("Score: "+score);
	}

	@Override
	public void updateScoreLines(int lines) {
		// TODO Auto-generated method stub
		this.ligne=lines;
		jlligne.setText("Ligne: "+ligne);
	}

	@Override
	public void updateLevel(int level) {
		// TODO Auto-generated method stub
		this.level=level;
		jllevel.setText("Level: "+this.level);
	}

	@Override
	public void updateHeldTetromino(Tetromino tetromino) {
		// TODO Auto-generated method stub
		this.held=tetromino;
	}

	@Override
	public void updateNextTetrominos(List<Tetromino> tetrominos) {
		// TODO Auto-generated method stub
		this.next=tetrominos;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		grille_de_jeu.update();
		holded.display_tetromino();
		nexted.display_tetromino();
		
		jlscore.repaint();
		jlligne.repaint();
		jllevel.repaint();
		
	}

}
