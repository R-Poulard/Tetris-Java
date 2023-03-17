package fr.upsaclay.bibs.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TetroProvRandom implements TetrominoProvider {
	List<Tetromino> listing;
	int point=0;
	
	public TetroProvRandom() {
		
		listing=this.create_bag();
		point=0;
	}
	
	private ArrayList<Tetromino>create_bag() {
		Random r = new Random();
		Tetromino bag[]= {
				TetrominoShape.JSHAPE.getTetromino(r.nextInt((TetrominoShape.JSHAPE.getNumberOfRotations()) + 1)),
				TetrominoShape.ISHAPE.getTetromino(r.nextInt((TetrominoShape.ISHAPE.getNumberOfRotations()) + 1)),
				TetrominoShape.SSHAPE.getTetromino(r.nextInt((TetrominoShape.SSHAPE.getNumberOfRotations()) + 1)),
				TetrominoShape.ZSHAPE.getTetromino(r.nextInt((TetrominoShape.ZSHAPE.getNumberOfRotations()) + 1)),
				TetrominoShape.OSHAPE.getTetromino(r.nextInt((TetrominoShape.OSHAPE.getNumberOfRotations()) + 1)),
				TetrominoShape.LSHAPE.getTetromino(r.nextInt((TetrominoShape.LSHAPE.getNumberOfRotations()) + 1)),
				TetrominoShape.TSHAPE.getTetromino(r.nextInt((TetrominoShape.TSHAPE.getNumberOfRotations()) + 1)),
		};
		for(int i=6;i>=1;i--) {
			int j= r.nextInt(i+1);
			Tetromino t=bag[i];
			bag[i]=bag[j];
			bag[j]=t;
		}
		return new ArrayList<Tetromino>(Arrays.asList(bag));
	}
	@Override
	
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Tetromino next() {
		// TODO Auto-generated method stub
		if(listing.size()==point) {
			listing=create_bag();
			point=0;
		}
		point++;
		return listing.get(point-1);
	}

	@Override
	public Tetromino showNext(int n) {
		// TODO Auto-generated method stub
		while((n+point)>=listing.size()){
			listing.addAll(create_bag());
		}
		return listing.get(n+point);
	}

}
