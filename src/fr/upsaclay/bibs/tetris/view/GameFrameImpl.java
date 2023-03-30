package fr.upsaclay.bibs.tetris.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.*;
import fr.upsaclay.bibs.tetris.control.manager.ManagerAction;
import fr.upsaclay.bibs.tetris.control.manager.VisualGameManager;
import fr.upsaclay.bibs.tetris.model.grid.TetrisCell;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGrid;
import fr.upsaclay.bibs.tetris.model.tetromino.TetrominoShape;

public class GameFrameImpl extends JFrame implements GameFrame {

		JPanel pause;
		JButton boutton_pause_resume;
		JButton boutton_pause_quit;
		
		GamePanelImpl grille;

		JPanel endgame;
		JButton end_menu;
		
		JPanel menu;
		ButtonGroup player_mode;
		ButtonGroup game_mode;
		JRadioButton player_mode1,player_mode2;
		JRadioButton game_mode1;
		JButton boutton_menu_start;
		JButton boutton_menu_quit;

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.BLACK);
		this.createManagerComponents();
		pause=new JPanel();
		pause.setBackground(Color.LIGHT_GRAY);
		pause.setLayout(new BoxLayout(pause,BoxLayout.Y_AXIS));
		
		JLabel text_pause=new JLabel("Jeu en Pause");
		text_pause.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		pause.add(Box.createRigidArea(new Dimension(0, 35)));
		pause.add(text_pause);
		pause.add(Box.createRigidArea(new Dimension(0, 35)));
		JPanel pause_aux=new JPanel(new FlowLayout());
		pause_aux.add(boutton_pause_resume);
		pause_aux.add(boutton_pause_quit);
		pause_aux.setOpaque(false);
		pause.add(pause_aux);
		pause.setBounds(180,350,200,200);
		this.add(pause);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setSize(screenSize.width/2, screenSize.height);  
	    this.setVisible(true);  
	    
	    grille=new GamePanelImpl();
	    this.add(grille);
	    grille.setBounds(0,0,grille.getParent().getWidth(),grille.getParent().getHeight());
	    grille.initialize();
	    
	    menu=new JPanel();
	    menu.setLayout(new GridBagLayout());
	    GridBagConstraints c=new GridBagConstraints();
	    c.insets = new Insets(1, 1, 1, 1);
	    this.add(menu);
	    menu.setBounds(0,this.getHeight()*2/10,this.getWidth(),this.getHeight()/2);
	    menu.setBackground(Color.LIGHT_GRAY);
	    JLabel tittle =new JLabel("Tetris (lame edition)");
	    tittle.setHorizontalAlignment(JLabel.CENTER);
	    JLabel game_mode_label =new JLabel("Mode de jeu:");
	    game_mode_label.setHorizontalAlignment(JLabel.CENTER);
	    JLabel player_mode_label =new JLabel("Mode du joueur:");
	    player_mode_label.setHorizontalAlignment(JLabel.CENTER);
	   
	    c.gridx=1;
	    c.gridy=0;
	    c.ipadx=100;
	    c.ipady=100;

	    menu.add(tittle,c);
	    c.gridx=1;
	    c.gridy=1;
	    c.ipadx=20;
	    c.ipady=20;

	    menu.add(player_mode_label,c);
	    c.gridx=0;
	    c.gridy=2;
	    c.ipadx=10;
	    c.ipady=10;

	    menu.add(player_mode1,c);
	    c.gridx=2;
	    c.gridy=2;
	    c.ipadx=10;
	    c.ipady=10;
	    menu.add(player_mode2,c);
	    c.gridx=1;
	    c.gridy=3;
	    c.ipadx=20;
	    c.ipady=20;
	    menu.add(game_mode_label,c);
	    c.gridx=1;
	    c.gridy=4;
	    c.ipadx=10;
	    c.ipady=10;
	    menu.add(game_mode1,c);
	    c.gridx=0;
	    c.gridy=5;
	    c.ipadx=30;
	    c.ipady=30;
	    menu.add(boutton_menu_start,c);
	    c.gridx=2;
	    c.gridy=5;
	    c.ipadx=30;
	    c.ipady=30;
	    menu.add(boutton_menu_quit,c);
	    
	    endgame=new JPanel(new GridLayout(6,1,30,10));
	    this.add(endgame);
	    endgame.setBounds(0,endgame.getParent().getHeight()*2/10,endgame.getParent().getWidth()*6/10,endgame.getParent().getHeight()*5/10);
	    JLabel endgame_label1=new JLabel("Partie Terminé");
	    endgame_label1.setHorizontalAlignment(JLabel.CENTER);
	    JLabel endgame_label2=new JLabel("Score :");
	    endgame_label2.setHorizontalAlignment(JLabel.CENTER);
	    JLabel endgame_label3=new JLabel("Niveau atteint :");
	    endgame_label3.setHorizontalAlignment(JLabel.CENTER);
	    JLabel endgame_label4=new JLabel("Nombre de ligne cassé:");
	    endgame_label4.setHorizontalAlignment(JLabel.CENTER);
	    endgame.add(endgame_label1);
	    endgame.add(endgame_label2);
	    endgame.add(endgame_label3);
	    endgame.add(endgame_label4);
	    endgame.add(Box.createRigidArea(new Dimension(0, 5)));
	    endgame.add(end_menu);
	    
	    endgame.setBackground(Color.LIGHT_GRAY);
	    endgame.setOpaque(true);
	    
	    this.endgame.setVisible(false);
	    this.pause.setVisible(false);
	    this.menu.setVisible(false);
	    this.grille.setVisible(false);
	}
	public GamePanelImpl getgrid() {
		return grille;
	}
	@Override
	public void drawManagementView() {
		// TODO Auto-generated method stub
		pause.setVisible(false);
		grille.drawManagementView();
		menu.setVisible(true);
		endgame.setVisible(false);
		grille.setFocusable(false);
	}

	@Override
	public void drawGamePlayView() {
		// TODO Auto-generated method stub
		pause.setVisible(false);
		grille.drawGamePlayView();
		menu.setVisible(false);
		endgame.setVisible(false);
		grille.setFocusable(true);
	}

	@Override
	public void drawGamePauseView() {
		// TODO Auto-generated method stub
		System.out.println("ici ");
		pause.setVisible(true);
		grille.drawGamePauseView();
		menu.setVisible(false);
		endgame.setVisible(false);
		pause.repaint();
		grille.setFocusable(false);
	}

	@Override
	public void drawEndGameView() {
		// TODO Auto-generated method stub
		endgame.setVisible(true);
		pause.setVisible(false);
		grille.drawEndGameView();
		menu.setVisible(false);
		grille.setFocusable(false);
		endgame.repaint();
	}

	@Override
	public GamePanel getGamePanel() {
		// TODO Auto-generated method stub
		return grille;
	}

	@Override
	public void createManagerComponents() {
		// TODO Auto-generated method stub

		boutton_pause_resume=new JButton("Continuer");
		boutton_pause_quit=new JButton("quitter");
		player_mode=new ButtonGroup();
	    player_mode1= new JRadioButton("IA (not chatGPT good)");
	    player_mode1.setOpaque(false);
	    player_mode2 = new JRadioButton("Human");
	    player_mode2.setOpaque(false);
        player_mode.add(player_mode1);
        player_mode.add(player_mode2);
        player_mode.setSelected(player_mode1.getModel(), true);
        
        game_mode=new ButtonGroup();
	    game_mode1=new JRadioButton("Marathon");
	    game_mode1.setOpaque(false);
	    game_mode.setSelected(game_mode1.getModel(), true);
       
	    boutton_menu_start=new JButton("START");
	    boutton_menu_start.setOpaque(false);
	    boutton_menu_quit=new JButton("QUIT");
	    boutton_menu_quit.setOpaque(false);
	    end_menu=new JButton("Menu");
	    end_menu.setPreferredSize(new Dimension(40, 40));
	}

	@Override
	public void attachManagerActionListener(ActionListener listener) {
		// TODO Auto-generated method stub
		boutton_pause_resume.addActionListener(listener);
		boutton_pause_quit.addActionListener(listener);
		end_menu.addActionListener(listener);
		player_mode1.addActionListener(listener);
		player_mode2.addActionListener(listener);
		game_mode1.addActionListener(listener);
		boutton_menu_start.addActionListener(listener);
		boutton_menu_quit.addActionListener(listener);
		((VisualGameManager.ActionHandler) listener).setButton(boutton_pause_resume,boutton_pause_quit, end_menu,player_mode1,player_mode2,game_mode1,boutton_menu_start,boutton_menu_quit);
	}

	@Override
	public void startGameKeyListener(KeyListener listener) {
		// TODO Auto-generated method stub
		grille.addKeyListener(listener);
		grille.setFocusable(true);
		grille.requestFocus();
	}
	
	
	@Override
	public void stopGameKeyListener(KeyListener listener) {
		// TODO Auto-generated method stub
		grille.removeKeyListener(listener);
	}

	@Override
	public void showErrorMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDefaultSetting(ManagerAction action) {
		// TODO Auto-generated method stub
		
	}

}
