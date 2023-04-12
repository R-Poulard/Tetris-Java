package fr.upsaclay.bibs.tetris.control.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.upsaclay.bibs.tetris.TetrisAction;
import fr.upsaclay.bibs.tetris.TetrisMode;
import fr.upsaclay.bibs.tetris.control.player.PlayerType;
import fr.upsaclay.bibs.tetris.control.player.VisualGamePlayer;
import fr.upsaclay.bibs.tetris.model.ai.RandomTetrisAI;
import fr.upsaclay.bibs.tetris.view.GameFrameImpl;


public class VisualGameManager extends AbstractGameManager {

	RandomTetrisAI ai;
	
	GameFrameImpl game_frame;//vue
	KeyHandler key;//gere les commandes
	ActionHandler action;//gere les boutons du visual game manager
	VisualGamePlayer pl;//player 
	boolean inpause;//Permet de bloquer le key listener quand la pose est faite
	Timer timer;//timer de l action descente (gardé en memeoire car la vitesse varie au cours de la partie)
	Timer ai_timer;//timer permettant de lancer l action de lIA a des intervalles regulier (sans ça on reste bloquer dans une boucle)
	//Music (charger via le controleur pour plus de controle)
	
	Clip clip_boutton;
	Clip command;
	Clip music;
	Clip clear;
	Clip over;
	Clip put;
	boolean done=false;//permet que la fonciton "over" agissent pas en boucle
	
	
	@Override
	public void initialize() {
		//standard
		this.mode=AbstractGameManager.DEFAULT_MODE;
		this.player_type=AbstractGameManager.DEFAULT_PLAYER_TYPE;
		this.cols=AbstractGameManager.DEFAULT_COLS;
		this.lines=AbstractGameManager.DEFAULT_LINES;
		provider=AbstractGameManager.DEFAULT_PROVIDER;
		//creation des handler de la vue et bind l''un aux autres
		key=new KeyHandler();
		action=new ActionHandler();
		game_frame=new GameFrameImpl();
		game_frame.initialize();
		game_frame.attachManagerActionListener(action);
		game_frame.bindGameKeyListener(key);
		ai=new RandomTetrisAI();
		//affichage du menu
		menu();

		//chargement des music
		try {
			clip_boutton= AudioSystem.getClip();
			clip_boutton.open(AudioSystem.getAudioInputStream(new File("resources/sounds/9285.wav")));
			music= AudioSystem.getClip();
			music.open(AudioSystem.getAudioInputStream(new File("resources/sounds/mainSound.wav")));
			music.loop(Clip.LOOP_CONTINUOUSLY);//loop en boucle car c es tla musique de fond
			music.start();
			//baisse legermeent le volume
			FloatControl gainControl= (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
			float range = gainControl.getMaximum() - gainControl.getMinimum();
			float gain = (range * 0.7f) + gainControl.getMinimum();
			gainControl.setValue(gain);
			clear= AudioSystem.getClip();
			clear.open(AudioSystem.getAudioInputStream(new File("resources/sounds/clear.wav")));
			over= AudioSystem.getClip();
			over.open(AudioSystem.getAudioInputStream(new File("resources/sounds/next-level.wav")));	
			put= AudioSystem.getClip();
			put.open(AudioSystem.getAudioInputStream(new File("resources/sounds/put.wav")));	
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Clip getclear() {
		return clear;
	}
	public Clip getput() {
		return put;
	}
	
	public GameFrameImpl getgame_frame() {
		return game_frame;
	}
	
	public void start_game() {
		inpause=false;	
		loadNewGame();//creation de la nouvelle game
		game_frame.getgrid().setGridView(gr.getView());//set la gridView
		pl.initialize(gr, scp, DEFAULT_PROVIDER);//initialisation du player
		pl.start();//demarrage
		game_frame.getgrid().setLoopAction(action);//debut du timer
		if(this.mode==TetrisMode.CAVERN) {//ceci permet de changer la vue si le mode de jeu est caverne
			game_frame.getgrid().set_black_mode(true);
		}
		else {
			game_frame.getgrid().set_black_mode(false);
		}
		game_frame.drawGamePlayView();//affiche la partie de jeu
		if(this.player_type==PlayerType.AI) {
			ActionListener taskPerformer = new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	if(!inpause) {
	            		if(!pl.performAction(ai.nextActions(null, null, null))) {
	            			over();
	            		}
	            	}
				}
	        };
			ai_timer=new Timer(1100, taskPerformer);
			ai_timer.start();
		}
		game_frame.startGameKeyListener(key);//request focus de la vue
	}
	
	public void start_from_file() {//meme chose que precedement mais a partir d un fichier
		inpause=false;	
		game_frame.getgrid().setGridView(gr.getView());
		pl.initialize(gr, scp, DEFAULT_PROVIDER);
		pl.start();
		game_frame.getgrid().setLoopAction(action);
		if(this.mode==TetrisMode.CAVERN) {//ceci permet de changer la vue si le mode de jeu est caverne
			game_frame.getgrid().set_black_mode(true);
		}
		else {
			game_frame.getgrid().set_black_mode(false);
		}
		game_frame.drawGamePlayView();
		game_frame.startGameKeyListener(key);
	}
	
	public void menu() {
		done=false;
		game_frame.drawManagementView();
		pl=null;//player null car on quitte la partie
		game_frame.showErrorMessage("");
	}
	
	public void comebacktogame() {//permet de revenir apres une pause (si on reste a jouer)
		inpause=false;
		game_frame.drawGamePlayView();
		pl.unpause();
		if(this.player_type==PlayerType.AI) {
			ai_timer.start();
			
		}
	}
	
	public void pause() {
		game_frame.drawGamePauseView();
		inpause=true;
		if(this.player_type==PlayerType.AI) {
			ai_timer.stop();
		}
	}
	
	
	public void over() {
		if(!over.isRunning() && !done) {//permet de faire jouer le son une seul fois
			done=true;
			over.start();
			over.setMicrosecondPosition(0);//le remet au debut
		}
		game_frame.drawEndGameView();
		game_frame.stopGameKeyListener(key);//on ne request plus le focus
		if(this.player_type==PlayerType.AI) {
			ai_timer.stop();
			ai_timer=null;
		}
	}
	@Override
	public void createPlayer() {
		 this.pl=new VisualGamePlayer(this);
	}
	
	@Override
	public void pausePlayer() {
		// TODO Auto-generated method stub
		pl.pause();
	}
	
	public class KeyHandler implements KeyListener{

		boolean toomuch=false;//permet d eviter le spam du hardrop
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(!inpause ) {//si on pose alors il n'y a pas d'action qui sont faite
				boolean res=true;
				switch(e.getKeyCode()){
				case KeyEvent.VK_ESCAPE:
					if(!inpause) {//permet de check que l'on est pas deja en pause
						pause();
						pl.pause();
						res=true;
					}
					break;
				//call le joueur pour faire une action en fonction de la commande
				case 65://gauche
					if(player_type!=PlayerType.AI) {
					res=pl.performAction(TetrisAction.MOVE_LEFT);
					}
					break;
				case 90://held
					if(player_type!=PlayerType.AI) {
					res=pl.performAction(TetrisAction.HOLD);
					}
					break;
				case 69://droite
					if(player_type!=PlayerType.AI) {
					res=pl.performAction(TetrisAction.MOVE_RIGHT);
					}
					break;
				case 88://hard drop
					if(player_type!=PlayerType.AI) {
					if(!toomuch) {
						res=pl.performAction(TetrisAction.HARD_DROP);
						toomuch=true;
					}
					}
					break;
				case 83://bas
					if(player_type!=PlayerType.AI) {
					res=pl.performAction(TetrisAction.START_SOFT_DROP);
					}
					break;	
				case 75://rotgauche
					if(player_type!=PlayerType.AI) {
					res=pl.performAction(TetrisAction.ROTATE_LEFT);
					}
					break;
				case 77://rotdroit
					if(player_type!=PlayerType.AI) {
					res=pl.performAction(TetrisAction.ROTATE_RIGHT);
					}
					break;
				}
				if(!res) {//res renvoie false si jamais le joueur detecte une fin de partie
					
					over();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {//permet de faire en s ort que le softdrop soit en press/realease et pas touch/stop
			// TODO Auto-generated method stub
			switch(e.getKeyCode()){
			case 83://bas
				if(!pl.performAction(TetrisAction.END_SOFT_DROP)) {//check si la partie est fini sur cette action

					over();
				}
		   case 88://hard drop
				if(toomuch) {
					toomuch=false;
				}
				break;
			}
		}
		
	}
	
	
	public class ActionHandler implements ActionListener{
		
		//voir description des bouttons dans la vues
		JButton boutton_pause_resume;
		JButton boutton_pause_quit;
		JButton boutton_save_file;
		
		JButton end_menu;
		

		JRadioButton player_mode1,player_mode2;
		JRadioButton game_mode1;
		JRadioButton game_mode2;
		JRadioButton game_mode3;
		JButton boutton_menu_start;
		JButton boutton_menu_quit;
		JButton chose_file;
		JFileChooser chooser = new JFileChooser();//permet de load une game

		
		public void actionPerformed(ActionEvent e) {
			Object source=e.getSource();
			if(source==timer) {//l aciton down du jeu
				if(pl!=null && !pl.performAction(TetrisAction.DOWN)) {
					over();
				}
			}
			else {
				
				if(source==boutton_pause_resume) {
					clip_boutton.setMicrosecondPosition(0);
					clip_boutton.start();

					comebacktogame();
				}
				else if(source==boutton_pause_quit) {
					clip_boutton.setMicrosecondPosition(0);
					clip_boutton.start();

					menu();
				}
				else if(source==end_menu) {
					clip_boutton.setMicrosecondPosition(0);
					clip_boutton.start();

					menu();
				}
				else if(source==player_mode2) {
					clip_boutton.setMicrosecondPosition(0);
					clip_boutton.start();

					player_type=PlayerType.HUMAN;
				}
				else if(source==player_mode1) {
					clip_boutton.setMicrosecondPosition(0);
					clip_boutton.start();

					player_type=PlayerType.AI;
				}
				else if(source==game_mode1) {
					clip_boutton.setMicrosecondPosition(0);
					clip_boutton.start();

					mode=TetrisMode.MARATHON;
				}
				else if(source==game_mode2) {
					clip_boutton.setMicrosecondPosition(0);
					clip_boutton.start();

					mode=TetrisMode.CAVERN;
				}
				else if(source==game_mode3) {
					clip_boutton.setMicrosecondPosition(0);
					clip_boutton.start();

					mode=TetrisMode.SPACE_CLEANER;
				}
				else if(source==boutton_menu_start) {
					clip_boutton.setMicrosecondPosition(0);
					clip_boutton.start();

					start_game();
				}
				else if(source==boutton_menu_quit){
					clip_boutton.setMicrosecondPosition(0);
					clip_boutton.start();

					System.exit(0);
				}
				else if(source==boutton_save_file){
					clip_boutton.setMicrosecondPosition(0);
					clip_boutton.start();

					//permet de choisir le fichier, de save et de reoturne rua menu
					int returnVal = chooser.showOpenDialog(null);
			        if(returnVal == JFileChooser.APPROVE_OPTION) {
			        	try {
							save(chooser.getSelectedFile());
							menu();
							game_frame.showErrorMessage("");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							menu();
							game_frame.showErrorMessage("Failed Saving");
							
						} 
			        }
				}
				else if(source==chose_file){
					clip_boutton.setMicrosecondPosition(0);
					clip_boutton.start();
					//lance la game depuis le file
			        int returnVal = chooser.showOpenDialog(null);
			        if(returnVal == JFileChooser.APPROVE_OPTION) {
			        	try {
							loadFromFile(chooser.getSelectedFile());
							start_from_file();
							game_frame.showErrorMessage("");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							game_frame.showErrorMessage("Failed Loading");
						}
			        }
				}
			}
		}
		
		//set le delay initial d'une game
		public void setTimer(Timer t) {
			timer=t;
			timer.setInitialDelay(1500);
			timer.setDelay(1500);
		}
		//permet de link les bouttons aux handler (pour savoir qu'elle boutton a envoyer l'action)
		
		public void setButton(JButton sf,JButton cf,JButton bpr,JButton bpq, JButton em,JRadioButton pm1,JRadioButton pm2,JRadioButton gm1,JRadioButton gm2,JRadioButton gm3,JButton bms,JButton bmq) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter(null,"txt");
	        chooser.setFileFilter(filter);
	        chose_file=cf;
			boutton_save_file=sf;
			boutton_pause_resume=bpr;
			boutton_pause_quit=bpq;
			
			end_menu=em;

			player_mode1=pm1;
			player_mode2=pm2;
			game_mode1=gm1;
			game_mode2=gm2;
			game_mode3=gm3;
			boutton_menu_start=bms;
			boutton_menu_quit=bmq;
		}
	}
}

