package fr.upsaclay.bibs.tetris.view;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import fr.upsaclay.bibs.tetris.model.grid.TetrisGridView;
import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;

public class GamePanelImpl extends JPanel implements GamePanel{
	int nblines;
	int nbcols;
	TetrisGridView grille;
	int loopdelay;
	int score;
	int ligne;
	int level;
	Tetromino held;
	List<Tetromino> next;
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
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
	}

	@Override
	public void updateScoreLines(int lines) {
		// TODO Auto-generated method stub
		this.ligne=lines;
	}

	@Override
	public void updateLevel(int level) {
		// TODO Auto-generated method stub
		this.level=level;
	}

	@Override
	public void updateHeldTetromino(Tetromino tetromino) {
		// TODO Auto-generated method stub
		this.held=tetromino;
		this.update();
	}

	@Override
	public void updateNextTetrominos(List<Tetromino> tetrominos) {
		// TODO Auto-generated method stub
		this.next=tetrominos;
		this.update();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
