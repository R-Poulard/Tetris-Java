package fr.upsaclay.bibs.tetris.model.grid;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;

public class TetriseGrille implements TetrisGrid{
	int lignes;
	int colonnes;
	TetrisCell[][] grille;
	Tetromino tr;
	TetrisCoordinates tr_coo;
	
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
		return tr!=null;
	}

	@Override
	public Tetromino getTetromino() {
		// TODO Auto-generated method stub
		return tr;
	}

	@Override
	public TetrisCoordinates getCoordinates() {
		// TODO Auto-generated method stub
		return tr_coo;
	}

	@Override
	public TetrisCell visibleCell(int i, int j) {
		// TODO Auto-generated method stub
		if(tr_coo==null && tr!=null) {
			throw new IllegalStateException();
		}
		System.out.println(tr_coo);
		System.out.println(i+" "+j);

		if(tr!=null && i>=tr_coo.getLine() && i<(tr_coo.getLine()+tr.getBoxSize()) && j>=tr_coo.getCol() && j<(tr_coo.getCol()+tr.getBoxSize())){
			return (tr.cell(i-tr_coo.getLine(), j-tr_coo.getCol())!=TetrisCell.EMPTY)? tr.cell(i-tr_coo.getLine(), j-tr_coo.getCol()): this.gridCell(i, j);
		}
		return this.gridCell(i, j);
	}

	@Override
	public boolean hasConflicts() {
		// TODO Auto-generated method stub
		if(tr==null) {
			return false;
		}
		if(tr_coo==null) {
			throw new IllegalStateException();
		}
		int l=tr_coo.getLine();
		int c=tr_coo.getCol();
		for(int i=0;i<tr.getBoxSize();i++) {
			for(int y=0;y<tr.getBoxSize();y++) {
				if(tr.cell(i, y)!=TetrisCell.EMPTY && this.gridCell(i+l, y+c)!=TetrisCell.EMPTY) {
						return true;
					}
			}
		}
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
		return new SynchronizedView(this);
	}

	@Override
	public void initiateCells(TetrisCell[][] cells) {
		if(this instanceof  TetrisGridView && !(this instanceof TetriseGrille)) {
			throw new ClassCastException(" cast impossible de tetriscell a vue");
		}
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
		tr=tetromino;
		tr_coo=null;
	}

	@Override
	public void setCoordinates(TetrisCoordinates coordinates) {
		// TODO Auto-generated method stub
		tr_coo=coordinates;
	}

	@Override
	public void setAtStartingCoordinates() {
		// TODO Auto-generated method stub
		if(tr!=null) {
			tr_coo=new TetrisCoordinates(0, (this.numberOfCols()-tr.getBoxSize())/2);
		}
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
