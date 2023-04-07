package fr.upsaclay.bibs.tetris.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import fr.upsaclay.bibs.tetris.control.manager.ManagerAction;
import fr.upsaclay.bibs.tetris.control.manager.VisualGameManager;
import lol.Client;

public class GameFrameImpl extends JFrame implements GameFrame {
		
		boolean black_mode;
		
	 	JPanel filling;
	 	JPanel filling2;
		JPanel pause;
		JButton boutton_pause_resume;
		JButton boutton_pause_quit;
		JButton boutton_save_file;
		
		GamePanelImpl grille;

		JPanel endgame;
		JLabel endgame_label2;
		JLabel endgame_label3;
		JLabel endgame_label4;
		JButton end_menu;
		
		JPanel menu;
		ButtonGroup player_mode;
		ButtonGroup game_mode;
		JRadioButton player_mode1,player_mode2;
		JRadioButton game_mode1;
		JRadioButton game_mode2;
		JRadioButton game_mode3;
		JButton boutton_menu_start;
		JButton boutton_menu_quit;
		JButton chose_file;

		JPanel controls;
		JButton open;

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		this.setResizable(false);
		this.setSize(new Dimension(450, 950));
		this.setMaximumSize(new Dimension(450, 950));
		this.setMinimumSize(new Dimension(450, 950));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setBackground(Color.black);
		this.createManagerComponents();
		
		controls=new JPanel();
		controls.setLayout(new BoxLayout(controls,BoxLayout.Y_AXIS));
		controls.setBounds(this.getWidth()+140,10,350,this.getHeight()*2/7);
	    controls.setBackground(new Color(39,33,79,255));
	    controls.setBorder(BorderFactory.createLineBorder(Color.GREEN,6));
	    JLabel cmain =new JLabel("Control List");
	    cmain.setFont(new Font("Rockwell", Font.BOLD, 40)); //Creating an Times New Roman Font Style with size 30
	    cmain.setForeground(Color.green);
	    cmain.setAlignmentX(CENTER_ALIGNMENT);
	    cmain.setHorizontalAlignment(JLabel.CENTER);
	    controls.add(cmain);
	    JLabel cg =new JLabel("Right            A");
	    cg.setFont(new Font("Rockwell", Font.BOLD, 25)); //Creating an Times New Roman Font Style with size 30
	    cg.setForeground(Color.green);
	    cg.setAlignmentX(CENTER_ALIGNMENT);
	    cg.setHorizontalAlignment(JLabel.CENTER);
	    controls.add(cg);
	    JLabel cf =new JLabel("Left             E");
	    cf.setFont(new Font("Rockwell", Font.BOLD, 25)); //Creating an Times New Roman Font Style with size 30
	    cf.setForeground(Color.green);
	    cf.setAlignmentX(CENTER_ALIGNMENT);
	    cf.setHorizontalAlignment(JLabel.CENTER);
	    controls.add(cf);
	    JLabel ce =new JLabel("Hold             Z");
	    ce.setFont(new Font("Rockwell", Font.BOLD, 25)); //Creating an Times New Roman Font Style with size 30
	    ce.setForeground(Color.green);
	    ce.setAlignmentX(CENTER_ALIGNMENT);
	    ce.setHorizontalAlignment(JLabel.CENTER);
	    controls.add(ce);
	    JLabel cd =new JLabel("Soft Drop (press)    S");
	    cd.setFont(new Font("Rockwell", Font.BOLD, 25)); //Creating an Times New Roman Font Style with size 30
	    cd.setForeground(Color.green);
	    cd.setAlignmentX(CENTER_ALIGNMENT);
	    cd.setHorizontalAlignment(JLabel.CENTER);
	    controls.add(cd);
	    JLabel cc =new JLabel("Hard drop                 X");
	    cc.setFont(new Font("Rockwell", Font.BOLD, 25)); //Creating an Times New Roman Font Style with size 30
	    cc.setForeground(Color.green);
	    cc.setAlignmentX(CENTER_ALIGNMENT);
	    cc.setHorizontalAlignment(JLabel.CENTER);
	    controls.add(cc);
	    JLabel cb =new JLabel("Rotate Left                K");
	    cb.setFont(new Font("Rockwell", Font.BOLD, 25)); //Creating an Times New Roman Font Style with size 30
	    cb.setForeground(Color.green);
	    cb.setAlignmentX(CENTER_ALIGNMENT);
	    cb.setHorizontalAlignment(JLabel.CENTER);
	    controls.add(cb);
	    JLabel ca =new JLabel("Rotate right               M");
	    ca.setFont(new Font("Rockwell", Font.BOLD, 25)); //Creating an Times New Roman Font Style with size 30
	    ca.setForeground(Color.green);
	    ca.setAlignmentX(CENTER_ALIGNMENT);
	    ca.setHorizontalAlignment(JLabel.CENTER);
	    controls.add(ca);
	    this.add(controls);
	    
		pause=new JPanel();
		pause.setBackground(new Color(0,0,0,200));
		Border blackline = BorderFactory.createLineBorder(Color.cyan);
		pause.setBorder(blackline);
		pause.setLayout(new BoxLayout(pause,BoxLayout.Y_AXIS));
		
		JLabel text_pause=new JLabel("Jeu en Pause");
		text_pause.setForeground(Color.cyan);
		text_pause.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		pause.add(Box.createRigidArea(new Dimension(0, 35)));
		pause.add(text_pause);
		pause.add(Box.createRigidArea(new Dimension(0, 35)));
		JPanel pause_aux=new JPanel(new FlowLayout());
		pause_aux.add(boutton_pause_resume);
		pause_aux.add(boutton_save_file);
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
	    filling=new JPanel(){
			  @Override
			  protected void paintComponent(Graphics g) {
			    super.paintComponent(g);
			    Image background;
				try {
					String fileName = "resources/images/bck.jpg";
					background = ImageIO.read(new File(fileName));
				    g.drawImage(background, 0, 0, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} };
	    filling.setBounds(0,0,this.getWidth(),this.getHeight()/4);
	    this.add(filling);
	    filling2=new JPanel(){
			  @Override
			  protected void paintComponent(Graphics g) {
			    super.paintComponent(g);
			    Image background;
				try {
					String fileName = "resources/images/bck2.jpg";
					background = ImageIO.read(new File(fileName));
				    g.drawImage(background, 0, 0, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} };
	    filling2.setBounds(0,this.getHeight()*7/10,this.getWidth(),this.getHeight()*3/10);
	    this.add(filling2);
	    menu.setBounds(0,this.getHeight()*2/10,this.getWidth(),this.getHeight()/2);
	    menu.setBackground(new Color(39,33,79,255));
	    
	    BufferedImage myPicture;
	    JLabel tittle;
		try {
			String fileName = "resources/images/tittle.png";
			myPicture = ImageIO.read(new File(fileName));
			tittle =new JLabel(new ImageIcon(myPicture));
		} catch (IOException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			tittle =new JLabel("Tetris (lame edition)");
		}
	    tittle.setHorizontalAlignment(JLabel.CENTER);
	    JLabel game_mode_label =new JLabel("MODE DE JEU:");
	    game_mode_label.setFont(new Font("Rockwell", Font.BOLD, 25)); //Creating an Times New Roman Font Style with size 30
	    game_mode_label.setForeground(Color.green);
	    game_mode_label.setAlignmentX(CENTER_ALIGNMENT);
	    game_mode_label.setHorizontalAlignment(JLabel.CENTER);
	    JLabel player_mode_label =new JLabel("TYPE DE JOUEUR:");
	    player_mode_label.setFont(new Font("Rockwell", Font.BOLD, 25)); //Creating an Times New Roman Font Style with size 30
	    player_mode_label.setForeground(Color.green);
	    player_mode_label.setAlignmentX(CENTER_ALIGNMENT);
	    player_mode_label.setHorizontalAlignment(JLabel.CENTER);
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
	    c.gridx=0;
	    c.gridy=4;
	    c.ipadx=10;
	    c.ipady=10;
	    menu.add(game_mode1,c);
	    c.gridx=1;
	    c.gridy=4;
	    c.ipadx=10;
	    c.ipady=10;
	    menu.add(game_mode2,c);
	    c.gridx=2;
	    c.gridy=4;
	    c.ipadx=10;
	    c.ipady=10;
	    menu.add(game_mode3,c);
	    c.gridx=0;
	    c.gridy=6;
	    c.ipadx=30;
	    c.ipady=30;
	    menu.add(boutton_menu_start,c);
	    c.gridx=1;
	    c.gridy=6;
	    c.ipadx=30;
	    c.ipady=30;
	    menu.add(chose_file,c);
	    c.gridx=3;
	    c.gridy=7;
	    c.ipadx=10;
	    c.ipady=10;
	    menu.add(boutton_menu_quit,c);
	    c.gridx=3;
	    c.gridy=1;
	    c.ipadx=10;
	    c.ipady=10;
	    menu.add(open,c);
	    endgame=new JPanel(new GridLayout(6,1,30,10));
	    this.add(endgame);
	    endgame.setBounds(10,endgame.getParent().getHeight()*2/10,endgame.getParent().getWidth()*6/10-10,endgame.getParent().getHeight()*5/10);
	    JLabel endgame_label1=new JLabel("Partie Terminée");
	    endgame_label1.setHorizontalAlignment(JLabel.CENTER);
	    endgame_label1.setFont(new Font("Rockwell", Font.BOLD, 50)); //Creating an Times New Roman Font Style with size 30
	    endgame_label1.setForeground(Color.cyan);
	    endgame_label1.setAlignmentX(CENTER_ALIGNMENT);
	    endgame_label2=new JLabel("Score :");
	    endgame_label2.setHorizontalAlignment(JLabel.CENTER);
	    endgame_label2.setFont(new Font("Rockwell", Font.BOLD, 25)); //Creating an Times New Roman Font Style with size 30
	    endgame_label2.setForeground(Color.cyan);
	    endgame_label2.setAlignmentX(CENTER_ALIGNMENT);
	    endgame_label2.setHorizontalAlignment(JLabel.CENTER);
	    endgame_label3=new JLabel("Niveau atteint :");
	    endgame_label3.setFont(new Font("Rockwell", Font.BOLD, 25)); //Creating an Times New Roman Font Style with size 30
	    endgame_label3.setForeground(Color.cyan);
	    endgame_label3.setAlignmentX(CENTER_ALIGNMENT);
	    endgame_label3.setHorizontalAlignment(JLabel.CENTER);
	    endgame_label4=new JLabel("Nombre de ligne cassées:");
	    endgame_label4.setHorizontalAlignment(JLabel.CENTER);
	    endgame_label4.setFont(new Font("Rockwell", Font.BOLD, 25)); //Creating an Times New Roman Font Style with size 30
	    endgame_label4.setForeground(Color.cyan);
	    endgame_label4.setAlignmentX(CENTER_ALIGNMENT);
	    endgame.add(endgame_label1);
	    endgame.add(endgame_label2);
	    endgame.add(endgame_label3);
	    endgame.add(endgame_label4);
	    endgame.add(Box.createRigidArea(new Dimension(0, 5)));
	    endgame.add(end_menu);
	    
	    endgame.setBackground(new Color(39,33,79,255));
	    
	    endgame.setOpaque(true);
	    
	    this.endgame.setVisible(false);
	    this.pause.setVisible(false);
	    this.menu.setVisible(false);
	    this.grille.setVisible(false);
	    this.controls.setVisible(false);
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
		controls.setVisible(false);
	}

	@Override
	public void drawGamePlayView() {
		// TODO Auto-generated method stub
		pause.setVisible(false);
		grille.drawGamePlayView();
		menu.setVisible(false);
		endgame.setVisible(false);
		grille.setFocusable(true);
		grille.requestFocus();
		controls.setVisible(false);
	}

	@Override
	public void drawGamePauseView() {
		// TODO Auto-generated method stub
		pause.setVisible(true);
		grille.drawGamePauseView();
		menu.setVisible(false);
		endgame.setVisible(false);
		pause.repaint();
		grille.setFocusable(false);
		controls.setVisible(false);
	}

	@Override
	public void drawEndGameView() {
		// TODO Auto-generated method stub
		grille.update_endgame(endgame_label2,endgame_label3,endgame_label4);
		
		endgame.setVisible(true);
		pause.setVisible(false);
		grille.drawEndGameView();
		menu.setVisible(false);
		grille.setFocusable(false);
		controls.setVisible(false);
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
		boutton_save_file=new JButton("Save & quit");
		
		player_mode=new ButtonGroup();
	    player_mode1= new JRadioButton("IA (not AlphaGo-good)");
	    player_mode1.setFont(new Font("Rockwell", Font.BOLD, 15)); //Creating an Times New Roman Font Style with size 30
	    player_mode1.setForeground(Color.green);
	    player_mode1.setOpaque(false);
	    player_mode2 = new JRadioButton("Human");
	    player_mode2.setFont(new Font("Rockwell", Font.BOLD, 15)); //Creating an Times New Roman Font Style with size 30
	    player_mode2.setForeground(Color.green);
	    player_mode2.setOpaque(false);
        player_mode.add(player_mode1);
        player_mode.add(player_mode2);
        player_mode.setSelected(player_mode2.getModel(), true);
        
        game_mode=new ButtonGroup();
	    game_mode1=new JRadioButton("Marathon");
	    game_mode1.setOpaque(false);
	    game_mode1.setFont(new Font("Rockwell", Font.BOLD, 15)); //Creating an Times New Roman Font Style with size 30
	    game_mode1.setForeground(Color.green);
	    game_mode2=new JRadioButton("Asteroic Field");
	    game_mode2.setOpaque(false);
	    game_mode2.setFont(new Font("Rockwell", Font.BOLD, 15)); //Creating an Times New Roman Font Style with size 30
	    game_mode2.setForeground(Color.green);
	    game_mode3=new JRadioButton("Space Cleaner");
	    game_mode3.setOpaque(false);
	    game_mode3.setFont(new Font("Rockwell", Font.BOLD, 15)); //Creating an Times New Roman Font Style with size 30
	    game_mode3.setForeground(Color.green);
	    game_mode.add(game_mode1);
	    game_mode.add(game_mode3);
	    game_mode.add(game_mode2);
	    
	    game_mode.setSelected(game_mode1.getModel(), true);	
	    
       
	    boutton_menu_start=new JButton("START");
	    boutton_menu_start.setOpaque(false);
	    boutton_menu_quit=new JButton("QUIT");
	    boutton_menu_quit.setOpaque(false);
	    chose_file=new JButton("Load Game");
	    chose_file.setOpaque(false);
	    end_menu=new JButton("Menu");
	    end_menu.setPreferredSize(new Dimension(40, 40));
	    
	   
	    open=new JButton("Controls");
	    ActionListener actionListener = new ActionListener() {
	    	boolean displayed=false;
	         public void actionPerformed(ActionEvent event) {
	        	 displayed=!displayed;
	        	 controls.setVisible(displayed);
	           
	         }
	      };
	      open.addActionListener(actionListener);
	}

	@Override
	public void attachManagerActionListener(ActionListener listener) {
		// TODO Auto-generated method stub
		boutton_pause_resume.addActionListener(listener);
		boutton_pause_quit.addActionListener(listener);
		boutton_save_file.addActionListener(listener);
		end_menu.addActionListener(listener);
		player_mode1.addActionListener(listener);
		player_mode2.addActionListener(listener);
		game_mode1.addActionListener(listener);
		game_mode2.addActionListener(listener);
		game_mode3.addActionListener(listener);
		boutton_menu_start.addActionListener(listener);
		boutton_menu_quit.addActionListener(listener);
		chose_file.addActionListener(listener);
		((VisualGameManager.ActionHandler) listener).setButton(boutton_save_file,chose_file,boutton_pause_resume,boutton_pause_quit, end_menu,player_mode1,player_mode2,game_mode1,game_mode2,game_mode3,boutton_menu_start,boutton_menu_quit);
	}

	@Override
	public void startGameKeyListener(KeyListener listener) {
		// TODO Auto-generated method stub

		grille.setFocusable(true);
		grille.requestFocus();

	}
	
	public void bindGameKeyListener(KeyListener listener) {
		grille.addKeyListener(listener);
		grille.setFocusable(false);
	}
	
	@Override
	public void stopGameKeyListener(KeyListener listener) {
		// TODO Auto-generated method stub

		grille.setFocusable(false);
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
