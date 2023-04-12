package fr.upsaclay.bibs.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TetroProvRandom implements TetrominoProvider {
	List<Tetromino> listing;
	int point=0;//permet d'avoir une shownext sans depiler
	
	public TetroProvRandom() {
		
		listing=this.create_bag();
		point=0;
	}
	
	private ArrayList<Tetromino>create_bag() {
		Random r = new Random();
		Tetromino bag[]= {//on suit le wiki pour creer un faut aleatoire => on mets une piece de chaque dans un sac et on pioche aleatoiremet tant que le sac est pas vide
				TetrominoShape.JSHAPE.getTetromino(r.nextInt((TetrominoShape.JSHAPE.getNumberOfRotations()) + 1)),
				TetrominoShape.ISHAPE.getTetromino(r.nextInt((TetrominoShape.ISHAPE.getNumberOfRotations()) + 1)),
				TetrominoShape.SSHAPE.getTetromino(r.nextInt((TetrominoShape.SSHAPE.getNumberOfRotations()) + 1)),
				TetrominoShape.ZSHAPE.getTetromino(r.nextInt((TetrominoShape.ZSHAPE.getNumberOfRotations()) + 1)),
				TetrominoShape.OSHAPE.getTetromino(r.nextInt((TetrominoShape.OSHAPE.getNumberOfRotations()) + 1)),
				TetrominoShape.LSHAPE.getTetromino(r.nextInt((TetrominoShape.LSHAPE.getNumberOfRotations()) + 1)),
				TetrominoShape.TSHAPE.getTetromino(r.nextInt((TetrominoShape.TSHAPE.getNumberOfRotations()) + 1)),
		};
		for(int i=6;i>=1;i--) {//le sac est melangé pour avoir uen odre aleatoire
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
		return true;//tjrs un next car au pire on repioche un sac
	}

	@Override
	public Tetromino next() {
		// TODO Auto-generated method stub
		if(listing.size()==point) {//si on est a la fin du sac alors on refait un sac
			listing=create_bag();
			point=0;
		}
		point++;
		return listing.get(point-1);
		//ceci revient a prendre le tetromino à point de l'iteration d'avant, cependant on est oblige car il faut incrementer la valeur de point mais toujours recuperer la 1er pîece du sac (sinon on commencerais a 1)
	}
	
	@Override
	public Tetromino showNext(int n) {
		// TODO Auto-generated method stub
		//on va	 agrandir la liste en rajoutant des sac tant que on ne peux pas visualiser le tetromino n
		while((n+point)>=listing.size()){
			listing.addAll(create_bag());//on ajoute a notre liste tout les sac
		}
		return listing.get(n+point);//on return le tetromino qu on veut
	}

}
