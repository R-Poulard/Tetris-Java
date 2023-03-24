package fr.upsaclay.bibs.tetris.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.*;
import fr.upsaclay.bibs.tetris.control.manager.ManagerAction;

public class GameFrameImpl extends JFrame implements GameFrame {

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.BLACK);
		JPanel pause=new JPanel();
		pause.setBackground(Color.LIGHT_GRAY);
		pause.setLayout(new BoxLayout(pause,BoxLayout.Y_AXIS));
		
		JLabel text_pause=new JLabel("Jeu en Pause");
		text_pause.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		JButton boutton_pause_resume=new JButton("Continuer");
		JButton boutton_pause_quit=new JButton("quitter");
		pause.add(Box.createRigidArea(new Dimension(0, 35)));
		pause.add(text_pause);
		pause.add(Box.createRigidArea(new Dimension(0, 35)));
		JPanel pause_aux=new JPanel(new FlowLayout());
		pause_aux.add(boutton_pause_resume);
		pause_aux.add(boutton_pause_quit);
		pause_aux.setOpaque(false);
		pause.add(pause_aux);
		
		pause.setSize(350, 350);
		pause.setBounds(100,50,200,200);
		this.add(pause,BorderLayout.CENTER);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setSize(screenSize.width/2, screenSize.height);  
	    this.setVisible(true);  
	}

	@Override
	public void drawManagementView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawGamePlayView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawGamePauseView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawEndGameView() {
		// TODO Auto-generated method stub

	}

	@Override
	public GamePanel getGamePanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createManagerComponents() {
		// TODO Auto-generated method stub

	}

	@Override
	public void attachManagerActionListener(ActionListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startGameKeyListener(KeyListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopGameKeyListener(KeyListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showErrorMessage(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDefaultSetting(ManagerAction action) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {    
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	GameFrameImpl mp=new GameFrameImpl();
	            mp.initialize();
	        }
	    });
	}

}
