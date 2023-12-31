package fr.upsaclay.bibs.tetris.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;


import fr.upsaclay.bibs.tetris.model.grid.TetrisGridView;
import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;

public class GamePanelImpl extends JPanel implements GamePanel{
	
	boolean black_mode;//pour le mode de jeu en question
	
	int nblines;//dimension de la grille
	int nbcols;
	TetrisGridView grille_model;//modele

	
	int score;
	int ligne;
	int level;

	
	Grille grille_de_jeu;//la vue de la grille
	TetroCell holded;//le tetromino hold
	TetroCell nexted;//le tetromino suivant
	JPanel aux;//le score
	
	//jlabel du score
	JLabel jlscore;
	JLabel jlligne;
	JLabel jllevel;
	JLabel jlinfo1;
	JLabel jlinfo2;
	
	//timer pour down
	Timer timer;
	
	
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		aux=new JPanel() {//image de fond
		  @Override
		  protected void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    Image background;
			try {
				String fileName = "resources/images/background.jpg";
				background = ImageIO.read(new File(fileName));
			    g.drawImage(background, 0, 0, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} };
		aux.setBackground(Color.black);
		this.add(aux);
		aux.setLayout(new BoxLayout(aux,BoxLayout.Y_AXIS));
		aux.setBounds(aux.getParent().getWidth()*6/10+5,5,aux.getParent().getWidth()*4/10-5,aux.getParent().getHeight()-15);

		grille_de_jeu=new Grille();
		this.add(grille_de_jeu);
		grille_de_jeu.setBounds(5,5,grille_de_jeu.getParent().getWidth()*6/10,grille_de_jeu.getParent().getHeight()-15);
		 
		
		nexted=new TetroCell();
		aux.add(nexted);
		nexted.setMinimumSize(new Dimension(nexted.getParent().getWidth()*4/7,nexted.getParent().getWidth()*4/7));

		nexted.setMaximumSize(new Dimension(nexted.getParent().getWidth()*4/7,nexted.getParent().getHeight()*1/5));
		aux.add(Box.createRigidArea(new Dimension(0, 10)));
		
		holded=new TetroCell();
		aux.add(holded);

		holded.setMaximumSize(new Dimension(holded.getParent().getWidth()*4/7,holded.getParent().getWidth()*4/7));
		holded.setMinimumSize(new Dimension(holded.getParent().getWidth()*4/7,holded.getParent().getWidth()*4/7));

		JPanel labels=new JPanel();
		labels.setLayout(new BoxLayout(labels,BoxLayout.Y_AXIS));
		//tout les labels du score ligne ect
		jlscore=new JLabel("Score: 0");
		jlscore.setFont(new Font("Rockwell", Font.BOLD, 30)); //Creating an Times New Roman Font Style with size 30
		jlscore.setForeground(Color.red);
		jlscore.setAlignmentX(CENTER_ALIGNMENT);
		
		jlligne=new JLabel("Lignes: 0");
		jlligne.setFont(new Font("Rockwell", Font.BOLD, 30)); //Creating an Times New Roman Font Style with size 30
		jlligne.setForeground(Color.red);
		jlligne.setAlignmentX(CENTER_ALIGNMENT);
		
		jllevel=new JLabel("Level: 1");
		jllevel.setFont(new Font("Rockwell", Font.BOLD, 30)); //Creating an Times New Roman Font Style with size 30
		jllevel.setForeground(Color.red);
		jllevel.setAlignmentX(CENTER_ALIGNMENT);
		
		jlinfo1=new JLabel("");
		jlinfo1.setFont(new Font("Rockwell", Font.BOLD, 50)); //Creating an Times New Roman Font Style with size 30
		jlinfo1.setForeground(Color.red);
		jlinfo1.setAlignmentX(CENTER_ALIGNMENT);
		
		jlinfo2=new JLabel("");
		jlinfo2.setFont(new Font("Rockwell", Font.BOLD, 25)); //Creating an Times New Roman Font Style with size 30
		jlinfo2.setForeground(Color.red);
		jlinfo2.setAlignmentX(CENTER_ALIGNMENT);
		
		aux.add(Box.createRigidArea(new Dimension(0, 5)));
		labels.add(Box.createRigidArea(new Dimension(0,5)));
		labels.add(jllevel);
		labels.add(Box.createRigidArea(new Dimension(0, 5)));
		labels.add(jlligne);
		labels.add(Box.createRigidArea(new Dimension(0, 5)));
		labels.add(jlscore);
		aux.add(labels);
		labels.setMaximumSize(new Dimension(nexted.getParent().getWidth()*5/7,nexted.getParent().getHeight()*1/7));
		labels.setOpaque(true);
		labels.setBackground(new Color(39,33,79,255));
		labels.setBorder(BorderFactory.createLineBorder(Color.cyan,3));
		aux.add(Box.createRigidArea(new Dimension(0, 50)));
		aux.add(jlinfo1);
		aux.add(Box.createRigidArea(new Dimension(0, 100)));
		aux.add(jlinfo2);
		aux.repaint();
		timer=new Timer(0,null);
		this.setBackground(Color.black);
	}

	public class Grille extends JPanel{
		//la grille est une double paneau de JPanel
		
		JPanel[][] grille;
		boolean black_mode=false;
		
		private Grille( ) {
			super();
			this.setOpaque(false);
			
		}
		public void set_up() {//initialisation
			Border backline=BorderFactory.createLineBorder(Color.cyan,5);
			this.setBorder(backline);
			this.setLayout(new GridLayout(nblines,nbcols));
			if(grille!=null) {//reinitialise la grille
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
		
		public void update(){//update en fonction de la tetris girlle vieww
			if(!black_mode) {
				for(int i=0;i<nblines;i++) {
					for(int y=0;y<nbcols;y++) {
						Border blackline = BorderFactory.createLineBorder(Color.black);//bordure noir de chaque cellule
						grille[i][y].setBorder(blackline);
						switch(grille_model.visibleCell(i, y)) {//valeur de background en fonction de la vue
						case EMPTY:
							grille[i][y].setBackground(new Color(39,33,79,160));
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
						case ROCK:
							blackline = BorderFactory.createLineBorder(new Color(51,51,51));
							grille[i][y].setBorder(blackline);
							grille[i][y].setBackground(Color.GRAY);
							break;
						default:
							break;
						}
					}
				}
				this.repaint();
			}
			else {//dans le lblack mode on regarde si  on est dans le cercle de visibilite sinon on met en noir
				for(int i=0;i<nblines;i++) {
					for(int y=0;y<nbcols;y++) {
						Border blackline = BorderFactory.createLineBorder(Color.black);
						grille[i][y].setBorder(blackline);

						if(inCircle(i,y)) {
							switch(grille_model.visibleCell(i, y)) {
							case EMPTY:
								
								grille[i][y].setBackground(new Color(39,33,79,160));
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
						else {
							grille[i][y].setBorder(null);
							grille[i][y].setBackground(Color.DARK_GRAY);
						}
					}
				}
				this.repaint();
			}
		}
		
		public boolean inCircle(int x,int y) {//formuile trouver sur internet pour faire un rond en pixel
			int m1=grille_model.getCoordinates().getLine()+grille_model.getTetromino().getBoxSize()/2;
			int m2=grille_model.getCoordinates().getCol()+grille_model.getTetromino().getBoxSize()/2;
			int dx=x-m1;
			int dy=y-m2;
			int distanceSquared = dx * dx + dy * dy;

		    return (distanceSquared <= 4*4);

		}
	}
	
	public class TetroCell extends JPanel{
		

		
		JPanel main;//panel contenant la matrice, permet d'orrienter en fonction du type de piece pour rendre plus esthetique
		JPanel[][] grille;
		Tetromino tr;
		boolean black_mode=false;
		private TetroCell( ) {
			this.setLayout(null);
			this.setBackground(new Color(39,33,79,255));
			Border backline=BorderFactory.createLineBorder(Color.cyan,5);
			this.setBorder(backline);
			
		}
		void set_tetro(Tetromino tr) {
			this.tr=tr;
		}
		
		void set_black_mode(boolean black_mode) {//si on a un black mode alors on garde le tout en noir
			this.black_mode=black_mode;
			if(black_mode) {
				this.setBackground(Color.BLACK);
				
				display_tetromino();
			}
			else {
				this.setBackground(new Color(39,33,79,255));
				display_tetromino();
			}
		}
		void display_tetromino(){

			if(main !=null) {
				this.removeAll();//remove le tetromino deja present
				for(int i=0;i<grille.length;i++) {
					for(int y=0;y<grille.length;y++) {
						main.remove(grille[i][y]);
						grille[i][y].setVisible(false);
					}
				}
				this.revalidate();
				this.repaint();
			}
			if(tr==null) {
				this.repaint();
				return;
			}
			main=new JPanel(new GridLayout(tr.getBoxSize(),tr.getBoxSize()));//creation de la nouvelle matrice
			
			if(black_mode) {
				main.setBackground(Color.BLACK);
			}
			else {
				main.setBackground(new Color(39,33,79,255));
			}
			grille = new JPanel[tr.getBoxSize()][tr.getBoxSize()];
			//remplissage du panneau
			if(!black_mode) {
			for(int i=0;i<tr.getBoxSize();i++) {
				for(int y=0;y<tr.getBoxSize();y++) {
					grille[i][y]=new JPanel();
					Border blackline = BorderFactory.createLineBorder(Color.black);
					grille[i][y].setBorder(blackline);
					switch(tr.cell(i, y)) {
						case EMPTY:
							grille[i][y].setBorder(null);
							grille[i][y].setOpaque(true);
							grille[i][y].setBackground(new Color(39,33,79,255));
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
					main.add(grille[i][y]);
					grille[i][y].repaint();
				}
			}
			}
			else {
				for(int i=0;i<tr.getBoxSize();i++) {
					for(int y=0;y<tr.getBoxSize();y++) {
						grille[i][y]=new JPanel();
						grille[i][y].setOpaque(true);
						grille[i][y].setBackground(Color.BLACK);
						main.add(grille[i][y]);
						grille[i][y].repaint();
					}
				}
			}
			this.add(main);
			
			//on position main dans la tetrocell en fonction de la piece
			
			switch(tr.getShape()) {
			case ISHAPE:
				main.setBounds(this.getParent().getWidth()/15
						,this.getParent().getWidth()/15,175,175);
				break;
			case JSHAPE:
				main.setBounds(this.getParent().getWidth()/8
				,this.getParent().getWidth()/12,150,150);
				break;
			case LSHAPE:
				main.setBounds(this.getParent().getWidth()/8
				,this.getParent().getWidth()/12,150,150);
				break;
			case OSHAPE:
				main.setBounds(this.getParent().getWidth()/7
				,this.getParent().getWidth()/10,100,100);
				break;
			case SSHAPE:
				main.setBounds(this.getParent().getWidth()/8
				,this.getParent().getWidth()/8,150,150);
				break;
			case TSHAPE:
				main.setBounds(this.getParent().getWidth()/8
				,this.getParent().getWidth()/8,150,150);
				break;
			case ZSHAPE:
				main.setBounds(this.getParent().getWidth()/8
				,this.getParent().getWidth()/8,150,150);
				break;
			default:
				break;
			
			}
			main.setVisible(true);
			main.setOpaque(true);
			main.repaint();
			this.revalidate();
			this.repaint();
		}
		
	}

	public void set_black_mode(boolean bl) {//si on est dans le mode de jeu on change la grille et le tetrocell de maniere adequate
		this.grille_de_jeu.black_mode=bl;
		this.nexted.set_black_mode(bl);;
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
		//this.setVisible(true);
		this.pauseActionLoop();

	}

	@Override
	public void drawEndGameView() {
		// TODO Auto-generated method stub
		this.timer=null;
		if(grille_de_jeu.black_mode) {
			this.grille_de_jeu.black_mode=false;
			this.grille_de_jeu.update();
			this.set_black_mode(false);
		}
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
		case COMBO://affichage du combo (modification jlabel simple)
			jlinfo1.setText("Combo x"+ ((Integer)attach).toString());
			break;
		case LINES://
			List<Integer> to_break=(List<Integer>)attach;
			jlinfo2.setText(to_break.size() + " LIGNES DETRUITES");
			jlinfo2.repaint();
			ActionListener taskPerformer = new ActionListener() {//ceci permet de faire une animation de glitch puis remettre comme avant a la prochaine grille update
	            public void actionPerformed(ActionEvent evt) {
	            	for(Integer i: to_break) {
	            	for(int y=0;y<nbcols;y++) {
	            		grille_de_jeu.grille[i][y].setBorder(null);
	            		grille_de_jeu.grille[i][y].setBackground(new Color(39,33,79,100));
						grille_de_jeu.grille[i][y].repaint();
	            	}	       
	            	try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	jlinfo2.setText(" ");//on fait disparaitre le texte
	            	jlinfo2.repaint();
				}
	            }
	        };
			Timer t=new Timer(150,taskPerformer);
			t.setRepeats(false);
			t.start();
			break;
		case END_COMBO://si le combo est terminer on enleve le message
			jlinfo1.setText("");
			break;		
		}
	}

	//update les affichage de scoring ligne et level
	
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
		jlligne.setText("Ligne: "+lines);
	}

	@Override
	public void updateLevel(int level) {
		// TODO Auto-generated method stub
		this.level=level;
		jllevel.setText("Level: "+level);
	}

	//update les tetrocells
	
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

	//change les labels de endgame avec les valeurs connus de score level ligne 
	
	public void update_endgame(JLabel endgame_label2, JLabel endgame_label3, JLabel endgame_label4) {
		// TODO Auto-generated method stub
		endgame_label2.setText("Score: "+score);
		endgame_label3.setText("Niveau atteint: "+level);
		endgame_label4.setText("Nombre de ligne cass�es: "+ligne);
	}

}
