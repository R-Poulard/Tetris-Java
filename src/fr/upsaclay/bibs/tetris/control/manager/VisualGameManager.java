package fr.upsaclay.bibs.tetris.control.manager;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JButton;

import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.event.ActionEvent;

import fr.upsaclay.bibs.tetris.TetrisAction;
import fr.upsaclay.bibs.tetris.TetrisMode;
import fr.upsaclay.bibs.tetris.control.player.PlayerType;
import fr.upsaclay.bibs.tetris.control.player.VisualGamePlayer;
import fr.upsaclay.bibs.tetris.model.tetromino.TetrominoShape;
import fr.upsaclay.bibs.tetris.view.GameFrameImpl;
import fr.upsaclay.bibs.tetris.view.GamePanelImpl;

public class VisualGameManager extends AbstractGameManager {

	GameFrameImpl game_frame;
	KeyHandler key;
	ActionHandler action;
	VisualGamePlayer pl;
	boolean inpause;
	Timer timer;
	
	@Override
	public void initialize() {
		this.mode=AbstractGameManager.DEFAULT_MODE;
		this.player_type=AbstractGameManager.DEFAULT_PLAYER_TYPE;
		this.cols=AbstractGameManager.DEFAULT_COLS;
		this.lines=AbstractGameManager.DEFAULT_LINES;
		provider=AbstractGameManager.DEFAULT_PROVIDER;
		this.player_type=AbstractGameManager.DEFAULT_PLAYER_TYPE;
		key=new KeyHandler();
		action=new ActionHandler();
		game_frame=new GameFrameImpl();
		game_frame.initialize();
		game_frame.attachManagerActionListener(action);
		menu();
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
		game_frame.drawGamePlayView();
		game_frame.startGameKeyListener(key);
	}
	
	public void menu() {
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
		System.out.println("IT S OVEEEERRR");
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
					pause();
					pl.pause();
					res=true;
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
					res=pl.performAction(TetrisAction.HARD_DROP);
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
		
		JButton end_menu;

		JRadioButton player_mode1,player_mode2;
		JRadioButton game_mode1;
		JButton boutton_menu_start;
		JButton boutton_menu_quit;
		
		
		public void actionPerformed(ActionEvent e) {
			Object source=e.getSource();
			if(source==boutton_pause_resume) {
				comebacktogame();
			}
			else if(source==boutton_pause_quit) {
				menu();
			}
			else if(source==end_menu) {
				menu();
			}
			else if(source==player_mode1) {
				player_type=PlayerType.HUMAN;
			}
			else if(source==player_mode2) {
				player_type=PlayerType.AI;
			}
			else if(source==game_mode1) {
				mode=TetrisMode.MARATHON;
			}
			else if(source==boutton_menu_start) {
				start_game();
			}
			else if(source==boutton_menu_quit){
				System.exit(0);
			}
			else if(source==timer) {
				if(pl!=null && !pl.performAction(TetrisAction.DOWN)) {
					over();
				}
			}
		}
		
		public void setTimer(Timer t) {
			timer=t;
			timer.setInitialDelay(1000);
			timer.setDelay(1000);
		}
		public void setButton(JButton bpr,JButton bpq, JButton em,JRadioButton pm1,JRadioButton pm2,JRadioButton gm1,JButton bms,JButton bmq) {
			boutton_pause_resume=bpr;
			boutton_pause_quit=bpq;
			
			end_menu=em;

			player_mode1=pm1;
			player_mode2=pm2;
			game_mode1=gm1;
			boutton_menu_start=bms;
			boutton_menu_quit=bmq;
		}
	}
}

