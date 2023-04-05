package fr.upsaclay.bibs.tetris.control.manager;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionEvent;

import fr.upsaclay.bibs.tetris.TetrisAction;
import fr.upsaclay.bibs.tetris.TetrisMode;
import fr.upsaclay.bibs.tetris.control.player.PlayerType;
import fr.upsaclay.bibs.tetris.control.player.VisualGamePlayer;
import fr.upsaclay.bibs.tetris.view.GameFrameImpl;


public class VisualGameManager extends AbstractGameManager {

	GameFrameImpl game_frame;
	KeyHandler key;
	ActionHandler action;
	VisualGamePlayer pl;
	boolean inpause;
	Timer timer;
	Clip clip_boutton;
	Clip command;
	Clip music;
	Clip clear;
	Clip over;
	boolean done=false;
	Clip put;
	
	@Override
	public void initialize() {
		this.mode=AbstractGameManager.DEFAULT_MODE;
		this.player_type=AbstractGameManager.DEFAULT_PLAYER_TYPE;
		this.cols=AbstractGameManager.DEFAULT_COLS;
		this.lines=AbstractGameManager.DEFAULT_LINES;
		provider=AbstractGameManager.DEFAULT_PROVIDER;
		key=new KeyHandler();
		action=new ActionHandler();
		game_frame=new GameFrameImpl();
		game_frame.initialize();
		game_frame.attachManagerActionListener(action);
		game_frame.bindGameKeyListener(key);
		menu();
		try {
			clip_boutton= AudioSystem.getClip();
			clip_boutton.open(AudioSystem.getAudioInputStream(new File("resources/sounds/9285.wav")));
			music= AudioSystem.getClip();
			music.open(AudioSystem.getAudioInputStream(new File("resources/sounds/mainSound.wav")));
			music.loop(Clip.LOOP_CONTINUOUSLY);
			music.start();
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
		

		//command=new Clip();
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
		loadNewGame();
		game_frame.getgrid().setGridView(gr.getView());
		pl.initialize(gr, scp, DEFAULT_PROVIDER);
		pl.start();
		game_frame.getgrid().setLoopAction(action);
		if(this.mode==TetrisMode.CAVERN) {
			game_frame.getgrid().set_black_mode(true);
		}
		else {
			game_frame.getgrid().set_black_mode(false);
		}
		game_frame.drawGamePlayView();
		game_frame.startGameKeyListener(key);
	}
	
	public void start_from_file() {
		inpause=false;	
		game_frame.getgrid().setGridView(gr.getView());
		pl.initialize(gr, scp, DEFAULT_PROVIDER);
		pl.start();
		game_frame.getgrid().setLoopAction(action);
		game_frame.drawGamePlayView();
		game_frame.startGameKeyListener(key);
	}
	public void menu() {
		done=false;
		game_frame.drawManagementView();
		pl=null;
	}
	
	public void comebacktogame() {
		inpause=false;
		game_frame.drawGamePlayView();
		pl.unpause();
	}
	
	public void pause() {
		game_frame.drawGamePauseView();
		inpause=true;
	}
	
	
	public void over() {
		if(!over.isRunning() && !done) {
			done=true;
			over.start();
			over.setMicrosecondPosition(0);
		}
		game_frame.drawEndGameView();
		game_frame.stopGameKeyListener(key);
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

		boolean toomuch=false;
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(!inpause) {
				
				boolean res=true;
				switch(e.getKeyCode()){
				case KeyEvent.VK_ESCAPE:
					if(!inpause) {
						pause();
						pl.pause();
						res=true;
					}
					break;
				case 65://gauche
					
					res=pl.performAction(TetrisAction.MOVE_LEFT);
					break;
				case 90://held

					res=pl.performAction(TetrisAction.HOLD);
					break;
				case 69://droite

					res=pl.performAction(TetrisAction.MOVE_RIGHT);
					break;
				case 88://hard drop
					if(!toomuch) {
						pl.performAction(TetrisAction.HARD_DROP);
						toomuch=true;
					}
					break;
				case 83://bas

					res=pl.performAction(TetrisAction.START_SOFT_DROP);
					break;	
				case 75://rotgauche

					res=pl.performAction(TetrisAction.ROTATE_LEFT);
					break;
				case 77://rotdroit

					res=pl.performAction(TetrisAction.ROTATE_RIGHT);
					break;
				}
				System.out.println("res "+res);
				if(!res) {
					
					over();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			switch(e.getKeyCode()){
			case 83://bas
				if(!pl.performAction(TetrisAction.END_SOFT_DROP)) {

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
	
	public static void main(String[] args) {    
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	SwingUtilities.invokeLater(()-> GameManager.getGameManager(GameType.VISUAL).initialize());

	        }
	    });
	}

	public class ActionHandler implements ActionListener{
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
		JFileChooser chooser = new JFileChooser();

		
		public void actionPerformed(ActionEvent e) {
			Object source=e.getSource();
			if(source==timer) {
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
				else if(source==player_mode1) {
					clip_boutton.setMicrosecondPosition(0);
					clip_boutton.start();

					player_type=PlayerType.HUMAN;
				}
				else if(source==player_mode2) {
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

					int returnVal = chooser.showOpenDialog(null);
			        if(returnVal == JFileChooser.APPROVE_OPTION) {
			        	try {
							save(chooser.getSelectedFile());
							menu();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							
						} 
			        }
				}
				else if(source==chose_file){
					clip_boutton.setMicrosecondPosition(0);
					clip_boutton.start();

			        int returnVal = chooser.showOpenDialog(null);
			        if(returnVal == JFileChooser.APPROVE_OPTION) {
			        	try {
							loadFromFile(chooser.getSelectedFile());
							start_from_file();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			            
			        }
				}
			}
		}
		
		
		public void setTimer(Timer t) {
			timer=t;
			timer.setInitialDelay(1000);
			timer.setDelay(1000);
		}
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

