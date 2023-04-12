package fr.upsaclay.bibs.tetris.model.tetromino;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;




public class TetroProv implements TetrominoProvider{
	List<Tetromino> listing;
	int point=0;//permet de recuperer le tetromino sans depiler (necessaire pour showNext)
	
	public TetroProv(List<Tetromino> tetrominos) {
		listing=tetrominos;
	}

	@Override
	public boolean hasNext() {//on regarde si l'on est a la fin de la liste
		// TODO Auto-generated method stub
		return point<listing.size();
	}
	
	@Override
	public Tetromino next() {
		// TODO Auto-generated method stub
		Tetromino m=listing.get(point);
		point++;//incrementation => on pasee au tetromino suivant
		return m;
	}

	@Override
	public Tetromino showNext(int n) {
		// TODO Auto-generated method stub
		if((n+point)>=listing.size()){//on regarde si on depasse la fin de la liste
			return null;
		}
		return listing.get(n+point);//sinon on return a partir de point 
	}

}
