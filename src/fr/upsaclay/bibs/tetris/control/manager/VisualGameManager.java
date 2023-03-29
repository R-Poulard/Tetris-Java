package fr.upsaclay.bibs.tetris.control.manager;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JButton;

import javax.swing.JRadioButton;
import javax.swing.Timer;

import java.awt.event.ActionEvent;

import fr.upsaclay.bibs.tetris.TetrisAction;
import fr.upsaclay.bibs.tetris.TetrisMode;
import fr.upsaclay.bibs.tetris.control.player.PlayerType;
import fr.upsaclay.bibs.tetris.control.player.VisualGamePlayer;
import fr.upsaclay.bibs.tetris.view.GameFrameImpl;
import fr.upsaclay.bibs.tetris.view.GamePanelImpl;

public class VisualGameManager extends AbstractGameManager {

	GameFrameImpl pl;
	KeyHandler key;
	ActionHandler action;
	VisualGamePlayer gp;
	boolean inpause;
	
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
		pl=new GameFrameImpl();
		pl.initialize();
		pl.attachManagerActionListener(action);
		menu();
	}
	
	public void start_game() {
		inpause=false;
		pl.drawGamePlayView();
		pl.startGameKeyListener(key);

		this.createPlayer();
	}
	
	public void menu() {
		pl.drawManagementView();
		pl.stopGameKeyListener(key);
		gp=null;
	}
	
	public void comebacktogame() {
		inpause=false;
		pl.drawGamePlayView();
		
	}
	
	public void pause() {
		pl.drawGamePauseView();
		inpause=true;
	}
	
	@Override
	public void createPlayer() {
		// TODO Auto-generated method stub
		gp=new VisualGamePlayer();
		gp.initialize(gr, scp, DEFAULT_PROVIDER);
		pl.drawEndGameView();
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
				
				switch(e.getKeyCode()){
				case KeyEvent.VK_ESCAPE:
					pause();
				case 65://gauche
					gp.performAction(TetrisAction.MOVE_LEFT);
					break;
				case 90://held
					gp.performAction(TetrisAction.HOLD);
					break;
				case 69://droite
					gp.performAction(TetrisAction.MOVE_RIGHT);
					break;
				case 88://droite
					gp.performAction(TetrisAction.HARD_DROP);
					break;
				case 83://bas
					gp.performAction(TetrisAction.START_SOFT_DROP);
					break;	
				case 75://rotgauche
					gp.performAction(TetrisAction.ROTATE_LEFT);
					break;
				case 77://rotdroit
					gp.performAction(TetrisAction.ROTATE_RIGHT);
					break;
				
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			switch(e.getKeyCode()){
			case 83://bas
				gp.performAction(TetrisAction.END_SOFT_DROP);
			}
		}
		
	}
	
	public class ActionHandler implements ActionListener{
		JButton boutton_pause_resume;
		JButton boutton_pause_quit;
		
		JButton end_menu;

		JRadioButton player_mode1,player_mode2;
		JRadioButton game_mode1;
		JButton boutton_menu_start;
		JButton boutton_menu_quit;
		Timer timer;
		
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
				gp.performAction(TetrisAction.DOWN);
			}
		}
		
		public void setTimer(Timer t) {
			timer=t;
			t.setInitialDelay(20);
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

