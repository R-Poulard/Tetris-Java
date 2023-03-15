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
	TetrisCell[][] I1 =new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
		{TetrisCell.I,		TetrisCell.I,		TetrisCell.I,		TetrisCell.I},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	TetrisCell[][] I2=new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.I,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
		{TetrisCell.EMPTY,		TetrisCell.I,		TetrisCell.EMPTY,		TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.I,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.I,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	//O formes
	TetrisCell[][] O1=new TetrisCell[][] {
		{TetrisCell.O, TetrisCell.O},
		{TetrisCell.O, TetrisCell.O}
	};
	//T formes
	TetrisCell[][] T1=new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.T,		TetrisCell.EMPTY},
		{TetrisCell.T,		TetrisCell.T,		TetrisCell.T},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	TetrisCell[][] T2=new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.T,		TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.T,		TetrisCell.T},
		{TetrisCell.EMPTY,	TetrisCell.T,	TetrisCell.EMPTY}
	};
	TetrisCell[][] T3=new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
		{TetrisCell.T,		TetrisCell.T,		TetrisCell.T},
		{TetrisCell.EMPTY,	TetrisCell.T,	TetrisCell.EMPTY}
	};
	TetrisCell[][] T4=new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.T,	TetrisCell.EMPTY},
		{TetrisCell.T,		TetrisCell.T,	TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.T,	TetrisCell.EMPTY}
	};
	//L shape
	TetrisCell[][] L1=new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.L},
		{TetrisCell.L,		TetrisCell.L,		TetrisCell.L},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	TetrisCell[][] L2=new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.L,	TetrisCell.EMPTY},
		{TetrisCell.EMPTY,		TetrisCell.L,		TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.L,	TetrisCell.L}
	};
	TetrisCell[][] L3=new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
		{TetrisCell.L,		TetrisCell.L,		TetrisCell.L},
		{TetrisCell.L,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	TetrisCell[][] L4=new TetrisCell[][] {
		{TetrisCell.L,	TetrisCell.L,	TetrisCell.EMPTY},
		{TetrisCell.EMPTY,		TetrisCell.L,		TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.L,	TetrisCell.EMPTY}
	};
	//J shape
	TetrisCell[][] J1=new TetrisCell[][] {
		{TetrisCell.J,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
		{TetrisCell.J,		TetrisCell.J,		TetrisCell.J},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	TetrisCell[][] J2=new TetrisCell[][] {
		{TetrisCell.EMPTY,		TetrisCell.J,	TetrisCell.J},
		{TetrisCell.EMPTY,		TetrisCell.J,	TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	    TetrisCell.J,	TetrisCell.EMPTY}
	};
	TetrisCell[][] J3=new TetrisCell[][] {
		{TetrisCell.EMPTY,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
		{TetrisCell.J,		TetrisCell.J,		TetrisCell.J},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.J}
	};
	TetrisCell[][] J4=new TetrisCell[][] {
		{TetrisCell.EMPTY,		TetrisCell.J,	TetrisCell.EMPTY},
		{TetrisCell.EMPTY,		TetrisCell.J,		TetrisCell.EMPTY},
		{TetrisCell.J,	TetrisCell.J,	TetrisCell.EMPTY}
	};
	//Z shape
	TetrisCell[][] Z1=new TetrisCell[][] {
		{TetrisCell.Z,		TetrisCell.Z,		TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.Z,		TetrisCell.Z},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	TetrisCell[][] Z2=new TetrisCell[][] {
		{TetrisCell.EMPTY,		TetrisCell.EMPTY,		TetrisCell.Z},
		{TetrisCell.EMPTY,	TetrisCell.Z,		TetrisCell.Z},
		{TetrisCell.EMPTY,	TetrisCell.Z,	TetrisCell.EMPTY}
	};
	TetrisCell[][] Z3=new TetrisCell[][] {
		{TetrisCell.EMPTY,		TetrisCell.EMPTY,		TetrisCell.EMPTY},
		{TetrisCell.Z,	TetrisCell.Z,		TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.Z,	TetrisCell.Z}
	};
	//S shape
	TetrisCell[][] S1=new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.S,		TetrisCell.S},
		{TetrisCell.S,		TetrisCell.S,		TetrisCell.EMPTY},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	TetrisCell[][] S2=new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.S,		TetrisCell.EMPTY},
		{TetrisCell.EMPTY,		TetrisCell.S,		TetrisCell.S},
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.S}
	};
	TetrisCell[][] S3=new TetrisCell[][] {
		{TetrisCell.EMPTY,	TetrisCell.EMPTY,		TetrisCell.EMPTY},
		{TetrisCell.EMPTY,		TetrisCell.S,		TetrisCell.S},
		{TetrisCell.S,	TetrisCell.S,	TetrisCell.EMPTY}
	};
	//I shape
	mapping.put("ISHAPE0",new TetrominoImpl(I1,0,ISHAPE));
	mapping.put("ISHAPE1",new TetrominoImpl(I1,1,ISHAPE));
	mapping.put("ISHAPE2",new TetrominoImpl(I1,2,ISHAPE));
	mapping.put("ISHAPE3",new TetrominoImpl(I1,3,ISHAPE));
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
	private static class TetrominoImpl implements Tetromino{
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
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<TetrisCoordinates> wallKicksFromLeft() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	}

