package fr.upsaclay.bibs.tetris.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import fr.upsaclay.bibs.tetris.control.manager.VisualGameManager.ActionHandler;
import fr.upsaclay.bibs.tetris.model.grid.SynchronizedView;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGridView;
import fr.upsaclay.bibs.tetris.model.score.ScoreComputer;
import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;

public class GamePanelImpl extends JPanel implements GamePanel{
	int nblines;
	int nbcols;
	TetrisGridView grille_model;
	int loopdelay;
	int score;
	int ligne;
	int level;

	
	Grille grille_de_jeu;
	TetroCell holded;
	TetroCell nexted;
	JPanel aux;
	
	JLabel jlscore;
	JLabel jlligne;
	JLabel jllevel;
	JLabel jlinfo1;
	JLabel jlinfo2;
	
	Timer timer;
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
		jlinfo1=new JLabel("Ici");
		jlinfo2=new JLabel("La");
		aux.add(Box.createRigidArea(new Dimension(0, 35)));
		aux.add(jllevel);
		aux.add(Box.createRigidArea(new Dimension(0, 35)));
		aux.add(jlligne);
		aux.add(Box.createRigidArea(new Dimension(0, 35)));
		aux.add(jlscore);
		aux.add(Box.createRigidArea(new Dimension(0, 200)));
		aux.add(jlinfo1);
		aux.add(Box.createRigidArea(new Dimension(0, 200)));
		aux.add(jlinfo2);
		timer=new Timer(0,null);
	}

	public class Grille extends JPanel{
		
		
		JPanel[][] grille;
		
		private Grille() {
			super();
			
		}
		public void set_up() {
			this.setLayout(new GridLayout(nblines,nbcols));
			if(grille!=null) {
				for(int i=0;i<nblines;i++) {
					for(int y=0;y<nbcols;y++) {
						this.remove(grille[i][y]);
					}
				}
				
			}
			grille=new JPanel[nblines][nbcols];
			for(int i=0;i<nblines;i++) {
				for(int y=0;y<nbcols;y++) {
					grille[i][y]= new JPanel();
					this.add(grille[i][y]);
				}
			}
			this.repaint();
		}
		
		public void update(){
			
			for(int i=0;i<nblines;i++) {
				for(int y=0;y<nbcols;y++) {
					Border blackline = BorderFactory.createLineBorder(Color.black);
					grille[i][y].setBorder(blackline);
					switch(grille_model.visibleCell(i, y)) {
					case EMPTY:
						grille[i][y].setBorder(null);
						grille[i][y].setBackground(new Color(39,33,79,250));
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
		JPanel[][] grille;
		Tetromino tr;
		private TetroCell() {
			this.setLayout(null);

			this.setBackground(Color.WHITE);
			
		}
		void set_tetro(Tetromino tr) {
			this.tr=tr;
		}
		void display_tetromino(){
			if(main !=null) {
			this.remove(main);
			}
			if(tr==null) {
				System.out.println("null");
				this.setBackground(Color.WHITE);
				this.repaint();
				return;
			}
			main=new JPanel(new GridLayout(tr.getBoxSize(),tr.getBoxSize()));
			grille = new JPanel[tr.getBoxSize()][tr.getBoxSize()];
			
			for(int i=0;i<tr.getBoxSize();i++) {
				for(int y=0;y<tr.getBoxSize();y++) {
					grille[i][y]=new JPanel();
					Border blackline = BorderFactory.createLineBorder(Color.black);
					grille[i][y].setBorder(blackline);
					switch(tr.cell(i, y)) {
						case EMPTY:
							grille[i][y].setBorder(null);
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
					}
					grille[i][y].setVisible(true);
					grille[i][y].setOpaque(true);
					
					main.add(grille[i][y]);
					grille[i][y].repaint();
				}
			}
			this.add(main);
			
			switch(tr.getShape()) {
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
			main.setVisible(true);
			main.repaint();
			this.repaint();
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
		System.out.println(timer.isRunning());

	}

	@Override
	public void drawGamePauseView() {
		// TODO Auto-generated method stub
		//this.setVisible(true);
		this.pauseActionLoop();

	}

	@Override
	public void drawEndGameView() {
		// TODO Auto-generated method stub
		this.timer=null;
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
		this.grille_model=view;
		this.nblines=view.numberOfLines();
		this.nbcols=view.numberOfCols();
		this.grille_de_jeu.set_up();
	}

	@Override
	public void setLoopAction(ActionListener listener) {
		// TODO Auto-generated method stu
		timer=new Timer(1,null);
		timer.addActionListener(listener);
		((ActionHandler) listener).setTimer(timer);
	}

	@Override
	public void startActionLoop() {
		// TODO Auto-generated method stub
		timer.start();
		
	}

	@Override
	public void pauseActionLoop() {
		// TODO Auto-generated method stub
		timer.stop();
	}

	@Override
	public void setLoopDelay(int ms) {
		// TODO Auto-generated method stub
		timer.setDelay(ms);
	}

	@Override
	public void launchGamePanelEvent(GamePanelEvent event, Object attach) {
		// TODO Auto-generated method stub
		switch(event) {
		case COMBO:
			jlinfo1.setText("Combo x"+((ScoreComputer) attach).getComboCount());
			break;
		case LINES:
			jlinfo2.setText(((Integer)attach).toString() + " LIGNES DETRUITES");
			break;
		case END_COMBO:
			jlinfo2.setText("");
			jlinfo1.setText("");
			break;		
		}
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
		this.holded.set_tetro(tetromino);
		holded.display_tetromino();
		holded.revalidate();
		holded.repaint();	
	}

	@Override
	public void updateNextTetrominos(List<Tetromino> tetrominos) {
		// TODO Auto-generated method stub
		this.nexted.set_tetro(tetrominos.get(0));
		nexted.display_tetromino();
		nexted.revalidate();
		nexted.repaint();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		grille_de_jeu.update();
		grille_de_jeu.repaint();
		jlscore.repaint();
		jlligne.repaint();
		jllevel.repaint();
	}

	public Timer getTimer() {
		// TODO Auto-generated method stub
		return timer;
	}

}
