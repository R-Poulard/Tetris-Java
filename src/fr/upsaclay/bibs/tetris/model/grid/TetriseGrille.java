package fr.upsaclay.bibs.tetris.model.grid;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
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
		grille=new TetrisCell[lignes][colonnes];//initialise une grille vide
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
		if(tr_coo==null && tr!=null) {//failsafe
			throw new IllegalStateException();
		}
		if(tr!=null && i>=tr_coo.getLine() && i<(tr_coo.getLine()+tr.getBoxSize()) && j>=tr_coo.getCol() && j<(tr_coo.getCol()+tr.getBoxSize())){//regarde si on est dans la zone du tetromino ou pas
			return (tr.cell(i-tr_coo.getLine(), j-tr_coo.getCol())!=TetrisCell.EMPTY)? tr.cell(i-tr_coo.getLine(), j-tr_coo.getCol()): this.gridCell(i, j);//return en fonction
		}
		return this.gridCell(i, j);
	}
	@Override
	public boolean setBlock(int i,int j,TetrisCell type) {//permet d'ajouter une unique cellule a un endroit precis, utilisé par space cleaner
		if(type==TetrisCell.EMPTY || this.visibleCell(i, j)!=TetrisCell.EMPTY) {
			return false;
		}
		else {
			grille[i][j]=type;
			return true;
		}
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
				if(tr.cell(i, y)!=TetrisCell.EMPTY && this.gridCell(i+l, y+c)!=TetrisCell.EMPTY) {//on check pour tout les valeurs dans la box de tetromino si on a deux non empty qui se chevauche
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
					b=false;//empty cell donc on n'ajoute pas la ligne a la liste
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
		//copie cellule par cellule
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
		if(tr_coo==null || tr==null) {
			throw new IllegalStateException();
		}
		int line=tr_coo.getLine();
		int col=tr_coo.getCol();
		for(int i=0;i<tr.getBoxSize();i++) {
			for(int y=0;y<tr.getBoxSize();y++) {				
				if(tr.cell(i, y)!=TetrisCell.EMPTY && this.gridCell(line+i+dir.getLine(), col+y+dir.getCol())!=TetrisCell.EMPTY) {//on prefere regarder de maniere preventive au lieu de set et regarder s il y a des conflits
					return false;
				}
			}
		}
		this.setCoordinates(new TetrisCoordinates(tr_coo.getLine()+dir.getLine(), tr_coo.getCol()+dir.getCol()));
		return true;
	}
	
	private boolean conflict(Tetromino new_tr,TetrisCoordinates newtr_coo) {
		int line=tr_coo.getLine();
		int col=tr_coo.getCol();
		for(int i=0;i<new_tr.getBoxSize();i++) {
			for(int y=0;y<new_tr.getBoxSize();y++) {				
				if(new_tr.cell(i, y)!=TetrisCell.EMPTY && this.gridCell(line+i+newtr_coo.getLine(), col+y+newtr_coo.getCol())!=TetrisCell.EMPTY) {//on check s'il y a des conflit avec la nouvelle position
					return false;
				}
			}
		}
		tr=new_tr;
		this.setCoordinates(new TetrisCoordinates(tr_coo.getLine()+newtr_coo.getLine(), tr_coo.getCol()+newtr_coo.getCol()));
		return true;
	}
	@Override
	public boolean tryRotateRight() {
		if(tr_coo==null || tr==null) {
			throw new IllegalStateException();
		}
		Tetromino tRight=tr.rotateRight();//on test le rotate simple
		if(conflict(tRight,new TetrisCoordinates(0,0))) {//on regarde si y a des conflits
			return true;
		}
		for(TetrisCoordinates i: tRight.wallKicksFromRight()) {//test le wallkick si la move simple n'a pas marché
			if(conflict(tRight,i)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean tryRotateLeft() {
		if(tr_coo==null || tr==null) {
			throw new IllegalStateException();
		}
		Tetromino tLeft=tr.rotateLeft();
		if(conflict(tLeft,new TetrisCoordinates(0,0))) {
			return true;
		}
		for(TetrisCoordinates i: tLeft.wallKicksFromLeft()) {
			if(conflict(tLeft,i)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void merge() {
		// TODO Auto-generated method stub
		if(tr!=null) {
			if(tr_coo==null) {
				throw new IllegalStateException("meging without coordinate");
			}
			int line=tr_coo.getLine();
			int col=tr_coo.getCol();
			for(int i=0;i<tr.getBoxSize();i++) {//pour chaque grille de la box on prend la value entre le grille et la box du tetromino qui n'est pas empty
				for(int y=0;y<tr.getBoxSize();y++) {
					if(tr.cell(i, y)!=TetrisCell.EMPTY) {
						this.grille[line+i][col+y]=tr.cell(i, y);
						}
					}
				}
		tr=null;
		}
		tr_coo=null;
	}

	@Override
	public void hardDrop() {
		// TODO Auto-generated method stub
		while(this.tryMove(TetrisCoordinates.DOWN));
	}

	private boolean emptyLine(int nbLine) {
		if(nbLine<0 || nbLine>=numberOfLines()) {
			return false;
		}
		for(int y=numberOfCols()-1;y>=0;y--) {
			if(this.gridCell(nbLine, y)!=TetrisCell.EMPTY) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public List<Integer> pack() {
		// TODO Auto-generated method stub
		List<Integer> fulls=this.fullLines();	
		if(fulls.isEmpty()) {
			return fulls;
		}
		for(int i=numberOfLines()-1;i>=0;i--) {//on parcours les lignes de bas en haut
			if(fulls.contains(i)) {//si c'est une ligne a elimniter on fills avec des TetrisCell Empty
				Arrays.fill(grille[i],TetrisCell.EMPTY);
				continue;
			}
			else {//on va chercher la ligne non vide la plus basse pour y mettre notreligne non vide
				int compteur=i+1;
				if(!emptyLine(compteur)) {
					continue;
				}
				else {
					while(emptyLine(compteur)) {
						compteur++;
					}
					//on compie celle ligne à la place
					grille[compteur-1]=grille[i];
					TetrisCell[] nw=new TetrisCell[numberOfCols()];
					Arrays.fill(nw,TetrisCell.EMPTY);//remplace la ligne qu on vient d ebouger par une ligne vide
					grille[i]=nw;
				}
			}	
		}
		return fulls;
	}
	//pour le mode cleaner
	public List<Integer> pack2() {
		// TODO Auto-generated method stub
		
		List<Integer> fulls=this.fullLines();	
		if(fulls.isEmpty()) {
			return fulls;
		}
		for(int i=numberOfLines()-1;i>=0;i--) {//meme chose que pack
			if(fulls.contains(i)) {
				Arrays.fill(grille[i],TetrisCell.EMPTY);
				continue;
			}
		}
		for(int i=numberOfLines()-1;i>=0;i--) {//cependant au lieu de copier ligne par ligne on va chercher la ligne vide la plus passe pour chaque cellule de chaque ligne
			for(int j=0;j<numberOfCols();j++) {
				if(grille[i][j]==TetrisCell.EMPTY || grille[i][j]==TetrisCell.ROCK) {
					continue;
				}
				boolean stop=false;
				int deplacement=i;
				while(!stop) {
					if(deplacement+1>=numberOfLines() || grille[deplacement+1][j]!=TetrisCell.EMPTY) {
						
						stop=true;
					}
					else {
						deplacement++;
					}
				}
				
				grille[deplacement][j]=TetrisCell.valueOf(grille[i][j].name());
				grille[i][j]=TetrisCell.EMPTY;
			}
		}
		return fulls;
	}
}
