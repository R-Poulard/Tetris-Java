package fr.upsaclay.bibs.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import fr.upsaclay.bibs.tetris.model.grid.TetrisCell;
import fr.upsaclay.bibs.tetris.model.grid.TetrisCoordinates;

/**
 * The list of different tetromino shapes
 * 
 * Each shape receives its initial tetromino as double array of tetris cells
 * 
 * It should then be able to return any rotation of the initial cells
 * 
 * Suggestion: compute all rotations at the creation of the shape and keeps the tetromino
 * stored inside the shape object
 * 
 * You need to implement the private constructor (as it is an enum) as well as needed
 * methods. You can add methods and fields and all you need.
 * 
 * We provide you with the random method
 * 
 * @author Viviane Pons
 *
 */
public enum TetrominoShape {
	ISHAPE(new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
		{TetrisCell.I,		TetrisCell.I,		TetrisCell.I,		TetrisCell.I},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	}),
	OSHAPE(new TetrisCell[][] {
		{TetrisCell.O, TetrisCell.O},
		{TetrisCell.O, TetrisCell.O}
	}),
	TSHAPE(new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.T,		TetrisCell.EMPTY},
		{TetrisCell.T,		TetrisCell.T,		TetrisCell.T},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	}),
	LSHAPE(new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.L},
		{TetrisCell.L,		TetrisCell.L,		TetrisCell.L},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	}),
	JSHAPE(new TetrisCell[][] {
		{TetrisCell.J,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
		{TetrisCell.J,		TetrisCell.J,		TetrisCell.J},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	}),
	ZSHAPE(new TetrisCell[][] {
		{TetrisCell.Z,		TetrisCell.Z,		TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.Z,		TetrisCell.Z},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	}),
	SSHAPE(new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.S,		TetrisCell.S},
		{TetrisCell.S,		TetrisCell.S,		TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	});
	
	
	private static final Random RANDOM = new Random();
	private final static HashMap<String,Tetromino> mapping=new HashMap<>();
	static {
	//I formes
	TetrisCell[][] I1 =ISHAPE.shape;
	TetrisCell[][] I2 = rotateLeft(I1);
	TetrisCell[][] I3 = rotateLeft(I2);
	TetrisCell[][] I4 = rotateLeft(I3);
	
	//O formes
	TetrisCell[][] O1=OSHAPE.shape;
	
	//T formes
	TetrisCell[][] T1 = TSHAPE.shape;
	TetrisCell[][] T2 = rotateLeft(T1);
	TetrisCell[][] T3 = rotateLeft(T2);
	TetrisCell[][] T4 = rotateLeft(T3);
	
	//L shape
	TetrisCell[][] L1 = LSHAPE.shape;
	TetrisCell[][] L2 = rotateLeft(L1);
	TetrisCell[][] L3 = rotateLeft(L2);
	TetrisCell[][] L4 = rotateLeft(L3);
	
	//J shape
	TetrisCell[][] J1 = JSHAPE.shape;
	TetrisCell[][] J2 = rotateLeft(J1);
	TetrisCell[][] J3 = rotateLeft(J2);
	TetrisCell[][] J4 = rotateLeft(J3);
	
	//Z shape
	TetrisCell[][] Z1 = ZSHAPE.shape;
	TetrisCell[][] Z2 = rotateLeft(Z1);
	TetrisCell[][] Z3 = rotateLeft(Z2);
	
	//S shape
	TetrisCell[][] S1 = SSHAPE.shape;
	TetrisCell[][] S2 = rotateLeft(S1);
	TetrisCell[][] S3 = rotateLeft(S2);

	//I shape
	mapping.put("ISHAPE0",new TetrominoImpl(I1,0,ISHAPE));
	mapping.put("ISHAPE1",new TetrominoImpl(I2,1,ISHAPE));
	mapping.put("ISHAPE2",new TetrominoImpl(I3,2,ISHAPE));
	mapping.put("ISHAPE3",new TetrominoImpl(I4,3,ISHAPE));
	//O shape
	mapping.put("OSHAPE0",new TetrominoImpl(O1,0,OSHAPE));
	//T shape
	mapping.put("TSHAPE0",new TetrominoImpl(T1,0,TSHAPE));
	mapping.put("TSHAPE1",new TetrominoImpl(T2,1,TSHAPE));
	mapping.put("TSHAPE2",new TetrominoImpl(T3,2,TSHAPE));
	mapping.put("TSHAPE3",new TetrominoImpl(T4,3,TSHAPE));
	//L shape
	mapping.put("LSHAPE0",new TetrominoImpl(L1,0,LSHAPE));
	mapping.put("LSHAPE1",new TetrominoImpl(L2,1,LSHAPE));
	mapping.put("LSHAPE2",new TetrominoImpl(L3,2,LSHAPE));
	mapping.put("LSHAPE3",new TetrominoImpl(L4,3,LSHAPE));
	//J shape
	mapping.put("JSHAPE0",new TetrominoImpl(J1,0,JSHAPE));
	mapping.put("JSHAPE1",new TetrominoImpl(J2,1,JSHAPE));
	mapping.put("JSHAPE2",new TetrominoImpl(J3,2,JSHAPE));
	mapping.put("JSHAPE3",new TetrominoImpl(J4,3,JSHAPE));
	//Z shape
	mapping.put("ZSHAPE0",new TetrominoImpl(Z1,0,ZSHAPE));
	mapping.put("ZSHAPE1",new TetrominoImpl(Z2,1,ZSHAPE));
	mapping.put("ZSHAPE2",new TetrominoImpl(Z3,2,ZSHAPE));
	mapping.put("ZSHAPE3",new TetrominoImpl(Z2,3,ZSHAPE));
	//S shape
	mapping.put("SSHAPE0",new TetrominoImpl(S1,0,SSHAPE));
	mapping.put("SSHAPE1",new TetrominoImpl(S2,1,SSHAPE));
	mapping.put("SSHAPE2",new TetrominoImpl(S3,2,SSHAPE));
	mapping.put("SSHAPE3",new TetrominoImpl(S2,3,SSHAPE));
	}
	
	TetrisCell[][] shape;
	
	public static Tetromino randomTetromino() {
		TetrominoShape randomShape = values()[RANDOM.nextInt(values().length)];
		return randomShape.getTetromino(RANDOM.nextInt(randomShape.getNumberOfRotations()));
	}
	
	
	private TetrominoShape(TetrisCell[][] initialShape) {
		shape=initialShape;
	}
	
	
	public TetrisCell getType() {		
		switch(this) {
		case ISHAPE:
			return(TetrisCell.I);
		case JSHAPE:
			return(TetrisCell.J);
		case LSHAPE:
			return(TetrisCell.L);
		case OSHAPE:
			return(TetrisCell.O);
		case SSHAPE:
			return(TetrisCell.S);
		case TSHAPE:
			return(TetrisCell.T);
		case ZSHAPE:
			return(TetrisCell.Z);
		default:
			return null;
		
		}
	}
	
	public int getNumberOfRotations() {
		switch(this) {
		case ISHAPE:
			return(4);
		case JSHAPE:
			return(4);
		case LSHAPE:
			return(4);
		case OSHAPE:
			return(1);
		case SSHAPE:
			return(4);
		case TSHAPE:
			return(4);
		case ZSHAPE:
			return(4);
		default:
			return 0;
		
		}
	}
	
	public int getBoxSize() {
		return shape.length;
	}
	
	public Tetromino getTetromino(int rotationNumber) {
		int rotmax=this.getNumberOfRotations();
		int nb= (((rotationNumber % rotmax) + rotmax) % rotmax);
		String key=this.name()+String.valueOf(nb);	
		return mapping.get(key);
		}

	public static TetrisCell[][] rotateLeft(TetrisCell[][] cells) {
	    int height = cells.length;
	    int width = cells[0].length;
	    TetrisCell[][] rotated = new TetrisCell[width][height];
	    for (int i = 0; i < height; i++) {
	        for (int j = 0; j < width; j++) {
	            rotated[j][height - 1 - i] = cells[i][j];
	        }
	    }
	    return rotated;
	}
	
	private static class TetrominoImpl implements Tetromino{
		
		private final static HashMap<String,List<TetrisCoordinates>> kick_map=new HashMap<>();
		static {
			//generic case
			TetrisCoordinates t0=new TetrisCoordinates(0,0);
			TetrisCoordinates t1=new TetrisCoordinates(0,-1);
			TetrisCoordinates t2=new TetrisCoordinates(0,1);
			TetrisCoordinates t3=new TetrisCoordinates(0,-2);
			TetrisCoordinates t4=new TetrisCoordinates(0,2);
			TetrisCoordinates t5bis=new TetrisCoordinates(1,0);
			TetrisCoordinates t5=new TetrisCoordinates(1,-1);
			TetrisCoordinates t6=new TetrisCoordinates(1,1);
			TetrisCoordinates t7=new TetrisCoordinates(1,-2);
			TetrisCoordinates t8=new TetrisCoordinates(1,2);
			TetrisCoordinates t9bis=new TetrisCoordinates(2,0);
			TetrisCoordinates t9=new TetrisCoordinates(2,-1);
			TetrisCoordinates t10=new TetrisCoordinates(2,1);
			TetrisCoordinates t11=new TetrisCoordinates(2,-2);
			TetrisCoordinates t12=new TetrisCoordinates(2,2);
			TetrisCoordinates t13bis=new TetrisCoordinates(-1,0);
			TetrisCoordinates t13=new TetrisCoordinates(-1,-1);
			TetrisCoordinates t14=new TetrisCoordinates(-1,1);
			TetrisCoordinates t15=new TetrisCoordinates(-1,-2);
			TetrisCoordinates t16=new TetrisCoordinates(-1,2);
			TetrisCoordinates t17bis=new TetrisCoordinates(-2,0);
			TetrisCoordinates t17=new TetrisCoordinates(-2,-1);
			TetrisCoordinates t18=new TetrisCoordinates(-2,1);
			TetrisCoordinates t19=new TetrisCoordinates(-2,-2);
			TetrisCoordinates t20=new TetrisCoordinates(-2,2);
			
			kick_map.put("G01",Arrays.asList(t1,t5,t17bis,t17));
			kick_map.put("G12",Arrays.asList(t1,t13,t9bis,t9));
			kick_map.put("G23",Arrays.asList(t2,t6,t17bis,t18));
			kick_map.put("G30",Arrays.asList(t2,t14,t9bis,t10));
			/*
			kick_map.put("G21",(+1, 0) 	(+1,-1) 	( 0,+2) 	(+1,+2));
			kick_map.put("G32",(-1, 0) 	(-1,+1) 	( 0,-2) 	(-1,-2));
			kick_map.put("G43",(-1, 0) 	(-1,-1) 	( 0,+2) 	(-1,+2));
			kick_map.put("G14",(+1, 0) 	(+1,+1) 	( 0,-2) 	(+1,-2));
			
			*/
			kick_map.put("G10",Arrays.asList(t1,t13,t9bis,t9));
			kick_map.put("G21",Arrays.asList(t1,t5,t17bis,t17));
			kick_map.put("G32",Arrays.asList(t2,t14,t9bis,t10));
			kick_map.put("G03",Arrays.asList(t2,t6,t17bis,t18));
			
			/*
			kick_map.put("I12",(-2, 0) 	(+1, 0) 	(-2,-1) 	(+1,+2));
			kick_map.put("I23",(-1, 0) 	(+2, 0) 	(-1,+2) 	(+2,-1));
			kick_map.put("I34",(+2, 0) 	(-1, 0) 	(+2,+1) 	(-1,-2));
			kick_map.put("I41",(+1, 0) 	(-2, 0) 	(+1,-2) 	(-2,+1));
			*/
			
			kick_map.put("I01",Arrays.asList(t2,t3,t10,t15));
			kick_map.put("I12",Arrays.asList(t3,t2,t7,t18));
			kick_map.put("I23",Arrays.asList(t1,t4,t17,t8));
			kick_map.put("I30",Arrays.asList(t4,t1,t16,t9));
			
			/*
			kick_map.put("I21",(+2, 0) 	(-1, 0) 	(+2,+1) 	(-1,-2));
			kick_map.put("I32",(+1, 0) 	(-2, 0) 	(+1,-2) 	(-2,+1));
			kick_map.put("I43",(-2, 0) 	(+1, 0) 	(-2,-1) 	(+1,+2));
			kick_map.put("I14",(-1, 0) 	(+2, 0) 	(-1,+2) 	(+2,-1));*/
			
			kick_map.put("I10",Arrays.asList(t2,t3,t10,t15));
			kick_map.put("I21",Arrays.asList(t3,t2,t7,t18));
			kick_map.put("I32",Arrays.asList(t1,t4,t17,t8));
			kick_map.put("I03",Arrays.asList(t4,t1,t16,t9));
			/*
			//left kick
			
			kick_map.put("G23",(+1, 0) 	(+1,-1) 	( 0,+2) 	(+1,+2));
			kick_map.put("G34",(+1, 0) 	(+1,+1) 	( 0,-2) 	(+1,-2));
			kick_map.put("G41",(-1, 0) 	(-1,-1) 	( 0,+2) 	(-1,+2));
			//rght kick
			kick_map.put("G21",(+1, 0) 	(+1,-1) 	( 0,+2) 	(+1,+2));
			kick_map.put("G32",(-1, 0) 	(-1,+1) 	( 0,-2) 	(-1,-2));
			kick_map.put("G43",(-1, 0) 	(-1,-1) 	( 0,+2) 	(-1,+2));
			kick_map.put("G14",(+1, 0) 	(+1,+1) 	( 0,-2) 	(+1,-2));
			
			//I case
			
			//left kick
			kick_map.put("I12",(-2, 0) 	(+1, 0) 	(-2,-1) 	(+1,+2));
			kick_map.put("I23",(-1, 0) 	(+2, 0) 	(-1,+2) 	(+2,-1));
			kick_map.put("I34",(+2, 0) 	(-1, 0) 	(+2,+1) 	(-1,-2));
			kick_map.put("I41",(+1, 0) 	(-2, 0) 	(+1,-2) 	(-2,+1));
			//rght kick
			kick_map.put("I21",(+2, 0) 	(-1, 0) 	(+2,+1) 	(-1,-2));
			kick_map.put("I32",(+1, 0) 	(-2, 0) 	(+1,-2) 	(-2,+1));
			kick_map.put("I43",(-2, 0) 	(+1, 0) 	(-2,-1) 	(+1,+2));
			kick_map.put("I14",(-1, 0) 	(+2, 0) 	(-1,+2) 	(+2,-1));*/
		}
		TetrisCell[][] piece;
		int rotation;
		TetrominoShape type;
		
		private TetrominoImpl(TetrisCell[][] piece,int rotation,TetrominoShape type) {
			this.piece=piece;
			this.rotation=rotation;
			this.type=type;
		}
		@Override
		public TetrominoShape getShape() {
			// TODO Auto-generated method stub
			return type;
		}

		@Override
		public int getRotationNumber() {
			// TODO Auto-generated method stub
			return rotation;
		}

		@Override
		public Tetromino rotateRight() {
			// TODO Auto-generated method stub
			return type.getTetromino(rotation+1);
		}

		@Override
		public Tetromino rotateLeft() {
			// TODO Auto-generated method stub
			return type.getTetromino(rotation-1);
		}

		@Override
		public TetrisCell cell(int line, int col) {
			// TODO Auto-generated method stub
			return piece[line][col];
		}

		@Override
		public int getBoxSize() {
			// TODO Auto-generated method stub
			return piece.length;
		}

		@Override
		public List<TetrisCoordinates> wallKicksFromRight() {
			String key="";
			switch(type) {
			case OSHAPE:
				return new ArrayList<TetrisCoordinates>();
			case ISHAPE:
				key="I";
				break;
			default:
				key="G";
			}
			key=key+String.valueOf(rotation)+String.valueOf((((rotation+1 % 4) + 4) % 4));
			return kick_map.get(key);	
		}

		@Override
		public List<TetrisCoordinates> wallKicksFromLeft() {
			// TODO Auto-generated method stub
			String key="";
			switch(type) {
			case OSHAPE:
				return new ArrayList<TetrisCoordinates>();
			case ISHAPE:
				key="I";
				break;
			default:
				key="G";
			}
			key=key+String.valueOf(rotation)+String.valueOf((((rotation-1 % 4) + 4) % 4));
			return kick_map.get(key);	
		}
		
	}
	}

