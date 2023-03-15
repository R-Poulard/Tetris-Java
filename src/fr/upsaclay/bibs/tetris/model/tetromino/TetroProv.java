package fr.upsaclay.bibs.tetris.model.tetromino;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;




public class TetroProv implements TetrominoProvider{
	List<Tetromino> listing;
	int point=0;
	public TetroProv(List<Tetromino> tetrominos) {
		listing=tetrominos;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return point<listing.size();
	}
	
	@Override
	public Tetromino next() {
		// TODO Auto-generated method stub
		Tetromino m=listing.get(point);
		point++;
		return m;
	}

	@Override
	public Tetromino showNext(int n) {
		// TODO Auto-generated method stub
		if((n+point)>=listing.size()){
			return null;
		}
		return listing.get(n+point);
	}

}
