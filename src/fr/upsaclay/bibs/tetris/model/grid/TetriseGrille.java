package fr.upsaclay.bibs.tetris.model.grid;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;

public class TetriseGrille implements TetrisGrid{
	int lignes;
	int colonnes;
	TetrisCell[][] grille;
	
	
	
	public TetriseGrille(int nblines, int nbcols) {
		this.lignes=nblines;
		this.colonnes=nbcols;
		grille=new TetrisCell[lignes][colonnes];
		for(int i=0;i<lignes;i++) {
			for(int y=0;y<colonnes;y++) {
				grille[i][y]=TetrisCell.EMPTY;
			}
		}
	}


	@Override
	public int numberOfLines() {
		// TODO Auto-generated method stub
		return lignes;
	}

	@Override
	public int numberOfCols() {
		// TODO Auto-generated method stub
		return colonnes;
	}

	@Override
	public TetrisCell gridCell(int i, int j) {
		// TODO Auto-generated method stub
		if(i<0 || i>=lignes || j<0 || j>=colonnes) {
			return TetrisCell.GREY;
		}
		return grille[i][j];
	}

	@Override
	public void printGrid(PrintStream out) {
		// TODO Auto-generated method stub
		for(int i=0;i<lignes;i++) {
			for(int y=0;y<colonnes;y++) {
				out.print(this.gridCell(i, y)+" ");
			}
			out.print("\n");
		}
	}
	
	@Override
	public boolean hasTetromino() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tetromino getTetromino() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TetrisCoordinates getCoordinates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TetrisCell visibleCell(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasConflicts() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFull(int lineNumber) {
		// TODO Auto-generated method stub
		if(lineNumber<0 || lineNumber>20) {
			return false;
		}
		for(int y=0;y<colonnes;y++) {
			if(grille[lineNumber][y]==TetrisCell.EMPTY) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty(int lineNumber) {
		// TODO Auto-generated method stub
		if(lineNumber<0 || lineNumber>20) {
			return false;
		}
		for(int y=0;y<colonnes;y++) {
			if(grille[lineNumber][y]!=TetrisCell.EMPTY) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<Integer> fullLines() {
		// TODO Auto-generated method stub
		List<Integer> fulls=new ArrayList<Integer>();
		for(int i=0;i<lignes;i++) {
			boolean b=true;
			for(int y=0;y<colonnes;y++) {
				if(grille[i][y]==TetrisCell.EMPTY) {
					b=false;
				}
			}
			if(b) {
				fulls.add(i);
			}
		}
		return fulls;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		for(int i=0;i<lignes;i++) {
			for(int y=0;y<colonnes;y++) {
				if(grille[i][y]!=TetrisCell.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public SynchronizedView getView() {
		// TODO Auto-generated method stub
		throw new ClassCastException("Not castable");
	}

	@Override
	public void initiateCells(TetrisCell[][] cells) {
		if(cells.length!=lignes || cells[0].length!=colonnes) {
			throw new IllegalArgumentException("taille de la cellule non conforme");
		}
		for(int i=0;i<lignes;i++) {
			for(int y=0;y<colonnes;y++) {
				grille[i][y]=cells[i][y];
			}
		}
	}

	@Override
	public void setTetromino(Tetromino tetromino) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCoordinates(TetrisCoordinates coordinates) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAtStartingCoordinates() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryMove(TetrisCoordinates dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryRotateRight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryRotateLeft() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void merge() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hardDrop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Integer> pack() {
		// TODO Auto-generated method stub
		return null;
	}

}
