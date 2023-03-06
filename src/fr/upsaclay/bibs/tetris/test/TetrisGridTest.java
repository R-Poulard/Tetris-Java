package fr.upsaclay.bibs.tetris.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.upsaclay.bibs.tetris.model.grid.TetrisCell;
import fr.upsaclay.bibs.tetris.model.grid.TetrisCoordinates;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGrid;
import fr.upsaclay.bibs.tetris.model.grid.TetrisGridView;
import fr.upsaclay.bibs.tetris.model.tetromino.Tetromino;
import fr.upsaclay.bibs.tetris.model.tetromino.TetrominoShape;

class TetrisGridTest {
	
	/** Some predefined grids for testing purpose **/
	
	TetrisCell[][] emptyGrid = {
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] smallGrid = {
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.O, 		TetrisCell.O,		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.O,	 	TetrisCell.O,		TetrisCell.L,		TetrisCell.L,		TetrisCell.L},
			{TetrisCell.I, 		TetrisCell.I,		TetrisCell.I,		TetrisCell.I,		TetrisCell.L}
	};
	
	TetrisCell[][] smallGridPacked = {
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.O, 		TetrisCell.O,		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] smallGrid2 = {
			{TetrisCell.L, 		TetrisCell.L,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.I,	 	TetrisCell.L,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.I, 		TetrisCell.L,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.I, 		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.I, 		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.O,		TetrisCell.O},
			{TetrisCell.L,	 	TetrisCell.L,		TetrisCell.L,		TetrisCell.O,		TetrisCell.O},
			{TetrisCell.L, 		TetrisCell.EMPTY,	TetrisCell.T,		TetrisCell.T,		TetrisCell.T},
			{TetrisCell.O, 		TetrisCell.O,		TetrisCell.EMPTY,	TetrisCell.T,		TetrisCell.EMPTY},
			{TetrisCell.O,	 	TetrisCell.O,		TetrisCell.L,		TetrisCell.L,		TetrisCell.L},
			{TetrisCell.I, 		TetrisCell.I,		TetrisCell.I,		TetrisCell.I,		TetrisCell.L}
	};
	
	TetrisCell[][] smallGrid2Packed = {
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.L, 		TetrisCell.L,		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.I,	 	TetrisCell.L,		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.I, 		TetrisCell.L,		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.I, 		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.I, 		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.O,		TetrisCell.O},
			{TetrisCell.L, 		TetrisCell.EMPTY,	TetrisCell.T,		TetrisCell.T,		TetrisCell.T},
			{TetrisCell.O, 		TetrisCell.O,		TetrisCell.EMPTY,	TetrisCell.T,		TetrisCell.EMPTY}
	};
	
	
	TetrisCell[][] smallGridWithIOnTop = {
			{TetrisCell.EMPTY, 	TetrisCell.I,		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.I,		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.I,		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.I,		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.O, 		TetrisCell.O,		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.O,	 	TetrisCell.O,		TetrisCell.L,		TetrisCell.L,		TetrisCell.L},
			{TetrisCell.I, 		TetrisCell.I,		TetrisCell.I,		TetrisCell.I,		TetrisCell.L}
	};
	
	TetrisCell[][] smallGridWithJRight = {
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.J},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.J},
			{TetrisCell.EMPTY,	 TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.J,		TetrisCell.J},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.O, 		TetrisCell.O,		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.O,	 	TetrisCell.O,		TetrisCell.L,		TetrisCell.L,		TetrisCell.L},
			{TetrisCell.I, 		TetrisCell.I,		TetrisCell.I,		TetrisCell.I,		TetrisCell.L}
	};
	
	TetrisCell[][] smallGridWithT = {
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.T,		TetrisCell.T,		TetrisCell.T,		TetrisCell.EMPTY},
			{TetrisCell.O, 		TetrisCell.O,		TetrisCell.T,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.O,	 	TetrisCell.O,		TetrisCell.L,		TetrisCell.L,		TetrisCell.L},
			{TetrisCell.I, 		TetrisCell.I,		TetrisCell.I,		TetrisCell.I,		TetrisCell.L}
	};
	
	TetrisCell[][] smallGridOverlap = {
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.O, 		TetrisCell.T,		TetrisCell.T,		TetrisCell.T,		TetrisCell.EMPTY},
			{TetrisCell.O,	 	TetrisCell.O,		TetrisCell.T,		TetrisCell.L,		TetrisCell.L},
			{TetrisCell.I, 		TetrisCell.I,		TetrisCell.I,		TetrisCell.I,		TetrisCell.L}
	};
	
	TetrisCell[][] smallGridRightRotatedT = {
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.T,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.T,		TetrisCell.T,		TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.T,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.O, 		TetrisCell.O,		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.O,	 	TetrisCell.O,		TetrisCell.L,		TetrisCell.L,		TetrisCell.L},
			{TetrisCell.I, 		TetrisCell.I,		TetrisCell.I,		TetrisCell.I,		TetrisCell.L}
	};
	
	TetrisCell[][] smallGridLeftRotatedT = {
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.T,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.T,		TetrisCell.T,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.T,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.O, 		TetrisCell.O,		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.O,	 	TetrisCell.O,		TetrisCell.L,		TetrisCell.L,		TetrisCell.L},
			{TetrisCell.I, 		TetrisCell.I,		TetrisCell.I,		TetrisCell.I,		TetrisCell.L}
	};
	TetrisCell[][] smallGridRightRotatedI = {
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.I,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.I,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.I,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.I,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.O, 		TetrisCell.O,		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.O,	 	TetrisCell.O,		TetrisCell.L,		TetrisCell.L,		TetrisCell.L},
			{TetrisCell.I, 		TetrisCell.I,		TetrisCell.I,		TetrisCell.I,		TetrisCell.L}
	};
	
	TetrisCell[][] smallGridRightRotatedL = {
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.L, 		TetrisCell.L,		TetrisCell.L,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.L, 		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.O, 		TetrisCell.O,		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.O,	 	TetrisCell.O,		TetrisCell.L,		TetrisCell.L,		TetrisCell.L},
			{TetrisCell.I, 		TetrisCell.I,		TetrisCell.I,		TetrisCell.I,		TetrisCell.L}
	};
	
	TetrisCell[][] mergeTestTop = {
			{TetrisCell.I, 		TetrisCell.I,		TetrisCell.I,		TetrisCell.I,		TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] mergeTestLeft = {
			{TetrisCell.I, 		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.I, 		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.I, 		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.I, 		TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] mergeTestRight = {
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.I},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.I},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.I},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.I}
	};
	
	TetrisCell[][] mergeTestBottom = {
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY, 	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.I, 		TetrisCell.I,		TetrisCell.I,		TetrisCell.I,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickGrid1 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY}
	};
	
	TetrisCell[][] wallKickGrid1WithL = {
			{TetrisCell.EMPTY,	TetrisCell.L,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.L,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.L,		TetrisCell.L,		TetrisCell.GREY}
	};
	
	TetrisCell[][] wallKickGrid2 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY,	TetrisCell.GREY}
	};
	
	TetrisCell[][] wallKickGrid2WithL = {
			{TetrisCell.EMPTY,	TetrisCell.L,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.L,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.L,		TetrisCell.L,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY,	TetrisCell.GREY}
	};
	
	TetrisCell[][] wallKickGrid3 = {
			{TetrisCell.GREY,	TetrisCell.GREY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickGrid3WithL = {
			{TetrisCell.GREY,	TetrisCell.GREY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.L,		TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.L,		TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.L,		TetrisCell.L}
	};
	
	TetrisCell[][] wallKickGrid4 = {
			{TetrisCell.GREY,	TetrisCell.GREY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY}
	};
	
	TetrisCell[][] wallKickGrid4WithL = {
			{TetrisCell.GREY,	TetrisCell.GREY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.L,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.L,		TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.L,		TetrisCell.L,		TetrisCell.GREY}
	};
	
	TetrisCell[][] wallKickNoRotation1 = {
			{TetrisCell.GREY,	TetrisCell.GREY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.GREY,	TetrisCell.GREY}
	};
	
	TetrisCell[][] wallKickGrid5 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickGrid6 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
	};
	
	TetrisCell[][] wallKickGrid7 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickGrid8 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY}
	};
	
	TetrisCell[][] wallKickNoRotation2 = {
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY}
	};
	
	TetrisCell[][] wallKickGrid9 = {
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickGrid10 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.GREY,	TetrisCell.GREY}
	};
	
	TetrisCell[][] wallKickGrid11 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickGrid12 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.GREY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickNoRotation3 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.GREY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY}
	};
	
	TetrisCell[][] wallKickGrid13 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickGrid14 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickGrid15 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickGrid16 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.GREY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.GREY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickNoRotation4 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickIGrid1 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickIGrid2 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.GREY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickIGrid3 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY,	TetrisCell.GREY}
	};
	
	TetrisCell[][] wallKickIGrid4 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickNoIRotation1 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickIGrid5 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickIGrid6 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickIGrid7 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickIGrid8 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickNoIRotation2 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY}
	};
	
	TetrisCell[][] wallKickIGrid9 = {
			{TetrisCell.EMPTY,	TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickIGrid10 = {
			{TetrisCell.EMPTY,	TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.GREY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickIGrid11 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickIGrid12 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickNoIRotation3 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickIGrid13 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickIGrid14 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickIGrid15 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickIGrid16 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickNoIRotation4 = {
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickLeftGrid1 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.GREY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickLeftGrid2 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickLeftGrid3 = {
			{TetrisCell.GREY,	TetrisCell.GREY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickLeftGrid4 = {
			{TetrisCell.GREY,	TetrisCell.GREY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickLeftNoRotation1 = {
			{TetrisCell.GREY,	TetrisCell.GREY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.GREY,	TetrisCell.GREY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickLeftIGrid1 = {
			{TetrisCell.EMPTY,	TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickLeftIGrid2 = {
			{TetrisCell.GREY,	TetrisCell.GREY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickLeftIGrid3 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickLeftIGrid4 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY}
	};
	
	TetrisCell[][] wallKickLeftNoIRotation1 = {
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY},
			{TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.EMPTY,	TetrisCell.GREY}
	};
	
	/**
	 * For testing purpose
	 * Test equality between the cells of a given tetris Grid and a double array of cells
	 * @param g a Tetris gid
	 * @param cells a double array of cells
	 * @return true if same dimension and all cells are equal
	 */
	public static boolean testEqualGrids(TetrisGrid g, TetrisCell[][] cells) {
		if (g.numberOfLines() != cells.length || cells[0].length != g.numberOfCols()) {
			return false;
		}
		for(int i = 0; i < g.numberOfLines(); i++) {
			for(int j = 0; j < g.numberOfCols(); j++) {
				if(g.gridCell(i, j) != cells[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * For testing purpose
	 * Test equality between the visible cells of a given tetris Grid and a double array of cells
	 * @param g a Tetris grid
	 * @param cells a double array of cells
	 * @return true if same dimension and all visible cells are equal
	 */
	public static boolean testEqualVisibles(TetrisGrid g, TetrisCell[][] cells) {
		if (g.numberOfLines() != cells.length || cells[0].length != g.numberOfCols()) {
			return false;
		}
		for(int i = 0; i < g.numberOfLines(); i++) {
			for(int j = 0; j < g.numberOfCols(); j++) {
				if(g.visibleCell(i, j) != cells[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	@Test
	void testGetEmptyGrid() {
		/** this only test that the creation goes without errors **/
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 5);
	}
	
	@Test
	void testGridView() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 5);
		TetrisGridView view = grid.getView();
		/* the tetris view returned by the gird should not be castable into TetrisGrid */
		assertThrows(ClassCastException.class, () -> ((TetrisGrid)view).initiateCells(emptyGrid));
	}
	
	@Test
	void testExcepionEmptyGrid() {
		assertThrows(IllegalArgumentException.class, () -> TetrisGrid.getEmptyGrid(0, 10));
		assertThrows(IllegalArgumentException.class, () -> TetrisGrid.getEmptyGrid(10, 0));
		assertThrows(IllegalArgumentException.class, () -> TetrisGrid.getEmptyGrid(-1, 10));
		assertThrows(IllegalArgumentException.class, () -> TetrisGrid.getEmptyGrid(10, -1));
	}
	
	@Test
	void testNumberOfLines() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 5);
		assertEquals(grid.numberOfLines(), 4);
		grid = TetrisGrid.getEmptyGrid(20, 10);
		assertEquals(grid.numberOfLines(), 20);
	}
	
	@Test
	void testNumberOfCols() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 5);
		assertEquals(grid.numberOfCols(), 5);
		grid = TetrisGrid.getEmptyGrid(20, 10);
		assertEquals(grid.numberOfCols(), 10);
	}
	
	@Test
	void testNormalGridCells() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 5);
		assertTrue(testEqualGrids(grid, emptyGrid));
	}
	
	@Test
	void testOutSideGridCells() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		assertEquals(grid.gridCell(-1, 0), TetrisCell.GREY);
		assertEquals(grid.gridCell(-2, 0), TetrisCell.GREY);
		assertEquals(grid.gridCell(10, 0), TetrisCell.GREY);
		assertEquals(grid.gridCell(11, 0), TetrisCell.GREY);
		assertEquals(grid.gridCell(0, -1), TetrisCell.GREY);
		assertEquals(grid.gridCell(0, -2), TetrisCell.GREY);
		assertEquals(grid.gridCell(0, 5), TetrisCell.GREY);
		assertEquals(grid.gridCell(0, 6), TetrisCell.GREY);
		assertEquals(grid.gridCell(10, 5), TetrisCell.GREY);
	}
	
	@Test
	void testInitiateCells() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		assertTrue(testEqualGrids(grid, smallGrid));
	}
	
	@Test
	void testInitialteCellsCopy() {
		/* test that cell array can be modified independently from the grid array */
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		smallGrid[0][0] = TetrisCell.J;
		/* the previous grid should not have been modified */
		assertEquals(grid.gridCell(0, 0), TetrisCell.EMPTY);
		smallGrid[0][0] = TetrisCell.EMPTY;
	}
	
	@Test
	void testInitiateCellsException() {
		TetrisGrid grid1 = TetrisGrid.getEmptyGrid(9, 5);
		assertThrows(IllegalArgumentException.class, () -> grid1.initiateCells(smallGrid));
		TetrisGrid grid2 = TetrisGrid.getEmptyGrid(10, 6);
		assertThrows(IllegalArgumentException.class, () -> grid2.initiateCells(smallGrid));
	}
	
	
	@Test
	void testPrintGrid() throws UnsupportedEncodingException {
		/* Testing the print on system.out */
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.printGrid(System.out);
		/* testing the result (your string might be slightly different with regards to \n and spaces, 
		 * adapt the test if it is) */
		String expected = 
				"EMPTY EMPTY EMPTY EMPTY EMPTY \n"
				+ "EMPTY EMPTY EMPTY EMPTY EMPTY \n"
				+ "EMPTY EMPTY EMPTY EMPTY EMPTY \n"
				+ "EMPTY EMPTY EMPTY EMPTY EMPTY \n"
				+ "EMPTY EMPTY EMPTY EMPTY EMPTY \n"
				+ "EMPTY EMPTY EMPTY EMPTY EMPTY \n"
				+ "EMPTY EMPTY EMPTY EMPTY EMPTY \n"
				+ "O O EMPTY EMPTY EMPTY \n"
				+ "O O L L L \n"
				+ "I I I I L \n";
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		grid.printGrid(ps);
		String output = os.toString("UTF-8");
		assertEquals(output, expected);
	}
	
	@Test
	void testTetromino() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		/* test that a newly creaded gird does not have a tetromino */
		assertFalse(grid.hasTetromino());
		/* sets tetromino and coordinates and test result */
		Tetromino tet = TetrominoShape.OSHAPE.getTetromino(0);
		TetrisCoordinates coords = new TetrisCoordinates(0,1);
		grid.setTetromino(tet);
		grid.setCoordinates(coords);
		assertTrue(grid.hasTetromino());
		assertEquals(grid.getTetromino(), tet);
		assertEquals(grid.getCoordinates(), coords);
	}
	
	@Test
	void testSetStartingCoordinates() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		Tetromino tet = TetrominoShape.OSHAPE.getTetromino(0);
		grid.setTetromino(tet);
		grid.setAtStartingCoordinates();
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,1));
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setAtStartingCoordinates();
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
	}
	
	@Test
	void testNegativeCoordinates() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,-1));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,-1));
	}
	
	@Test
	void testVisibleCell1() {
		// We place an I shape on top of the small grid
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(testEqualGrids(grid,smallGrid));
		assertTrue(testEqualVisibles(grid,smallGridWithIOnTop));
	}
	
	@Test
	void testVisibleCell2() {
		// We place a J shape on top left of the small grid
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.setTetromino(TetrominoShape.JSHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(0,3));
		assertTrue(testEqualGrids(grid,smallGrid));
		assertTrue(testEqualVisibles(grid,smallGridWithJRight));
	}
	
	@Test
	void testVisibleCell3() {
		// We place a T shape just over the cells of the small grid
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.setTetromino(TetrominoShape.TSHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(5,1));
		assertTrue(testEqualGrids(grid,smallGrid));
		assertTrue(testEqualVisibles(grid,smallGridWithT));
	}
	
	@Test
	void testVisibleCell4() {
		// We place a T shape overlapping the cells of the small grid
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.setTetromino(TetrominoShape.TSHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(6,1));
		assertTrue(testEqualGrids(grid,smallGrid));
		assertTrue(testEqualVisibles(grid,smallGridOverlap));
	}
	
	@Test
	void testVisibleCellNoTet() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		assertTrue(testEqualVisibles(grid,smallGrid));
	}
	
	@Test
	void testVisibleCellException() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		assertThrows(IllegalStateException.class, () -> grid.visibleCell(0, 0));
	}
	
	@Test
	void testHasConflict() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		// non conflict cases
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertFalse(grid.hasConflicts());
		grid.setTetromino(TetrominoShape.JSHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(0,3));
		assertFalse(grid.hasConflicts());
		grid.setTetromino(TetrominoShape.TSHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(5,1));
		assertFalse(grid.hasConflicts());
		// some conflictual cases
		grid.setTetromino(TetrominoShape.TSHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(6,1));
		assertTrue(grid.hasConflicts());
		// Some conflictual cases with the border 
		grid.setTetromino(TetrominoShape.TSHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(0,-1));
		assertTrue(grid.hasConflicts());
		grid.setTetromino(TetrominoShape.TSHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(0,8));
		assertTrue(grid.hasConflicts());
	}
	
	@Test
	void testHasConflictNoTet() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testHasConflictException() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(3));
		assertThrows(IllegalStateException.class, () -> grid.hasConflicts());
	}
	
	@Test
	void testMoving1() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		// Some working moves
		grid.setTetromino(TetrominoShape.TSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,1));
		assertTrue(grid.tryMove(TetrisCoordinates.LEFT));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertTrue(grid.tryMove(TetrisCoordinates.DOWN));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(1,0));
		assertTrue(grid.tryMove(TetrisCoordinates.RIGHT));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(1,1));
		assertTrue(grid.tryMove(TetrisCoordinates.UP));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,1));
		assertTrue(grid.tryMove(new TetrisCoordinates(2,1)));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(2,2));
	}
	
	@Test
	void testMoving2() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		//  Some non working moves
		grid.setTetromino(TetrominoShape.TSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertFalse(grid.tryMove(TetrisCoordinates.LEFT));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertFalse(grid.tryMove(TetrisCoordinates.UP));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		grid.setCoordinates(new TetrisCoordinates(7,2));
		assertFalse(grid.tryMove(TetrisCoordinates.RIGHT));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(7,2));
		assertFalse(grid.tryMove(TetrisCoordinates.DOWN));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(7,2));
	}
	
	@Test
	void testMovingException() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		assertThrows(IllegalStateException.class, () -> grid.tryMove(TetrisCoordinates.DOWN));
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		assertThrows(IllegalStateException.class, () -> grid.tryMove(TetrisCoordinates.DOWN));
	}
	
	@Test
	void testEasyRightRotations1() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.setTetromino(TetrominoShape.TSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,1));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.TSHAPE.getTetromino(1));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,1));
		assertTrue(testEqualVisibles(grid, smallGridRightRotatedT));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.TSHAPE.getTetromino(2));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,1));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.TSHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,1));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.TSHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,1));
	}
	
	@Test
	void testEasyRightRotations2() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(1));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertTrue(testEqualVisibles(grid, smallGridRightRotatedI));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(2));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
	}
	
	@Test
	void testRotationO() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.setTetromino(TetrominoShape.OSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.OSHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
	}
	
	@Test
	void testRightRotationException() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		assertThrows(IllegalStateException.class, () -> grid.tryRotateRight());
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		assertThrows(IllegalStateException.class, () ->  grid.tryRotateRight());
	}
	
	@Test
	void testEasyLeftRotations1() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.setTetromino(TetrominoShape.TSHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(0,1));
		assertTrue(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.TSHAPE.getTetromino(1));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,1));
		assertTrue(testEqualVisibles(grid, smallGridRightRotatedT));
		assertTrue(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.TSHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,1));
		assertTrue(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.TSHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,1));
		assertTrue(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.TSHAPE.getTetromino(2));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,1));
	}
	
	@Test
	void testEasyLeftRotations2() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(1));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertTrue(testEqualVisibles(grid, smallGridRightRotatedI));
		assertTrue(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(2));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
	}
	
	@Test
	void testLefttRotationException() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		assertThrows(IllegalStateException.class, () -> grid.tryRotateLeft());
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		assertThrows(IllegalStateException.class, () ->  grid.tryRotateLeft());
	}
	
	@Test
	void testWallKickRotations1() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(3, 4);
		grid.initiateCells(wallKickGrid1);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,1));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(1));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertFalse(grid.hasConflicts());
		assertTrue(testEqualVisibles(grid, wallKickGrid1WithL));
	}
	
	@Test
	void testWallKickRotations2() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickGrid2);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(1,1));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(1));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertFalse(grid.hasConflicts());
		assertTrue(testEqualVisibles(grid, wallKickGrid2WithL));
	}
	
	@Test
	void testWallKickRotations3() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(5, 3);
		grid.initiateCells(wallKickGrid3);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(1));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(2,0));
		assertFalse(grid.hasConflicts());
		assertTrue(testEqualVisibles(grid, wallKickGrid3WithL));
	}
	
	@Test
	void testWallKickRotations4() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(5, 3);
		grid.initiateCells(wallKickGrid4);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(1));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(2,-1));
		assertFalse(grid.hasConflicts());
		assertTrue(testEqualVisibles(grid, wallKickGrid4WithL));
	}
	
	@Test
	void testWallKickNoLRotation1() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(5, 3);
		grid.initiateCells(wallKickNoRotation1);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertFalse(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickRotations5() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(3, 4);
		grid.initiateCells(wallKickGrid5);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(1));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(2));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,1));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickRotations6() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickGrid6);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(1));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(2));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(1,1));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickRotations7() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickGrid7);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(1));
		grid.setCoordinates(new TetrisCoordinates(1,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(2));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(-1,0));
		assertFalse(grid.hasConflicts());
	}

	@Test
	void testWallKickRotations8() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickGrid8);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(1));
		grid.setCoordinates(new TetrisCoordinates(1,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(2));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(-1,1));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickNoLRotation2() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(3, 4);
		grid.initiateCells(wallKickNoRotation2);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(1));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertFalse(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(1));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickRotations9() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(3, 3);
		grid.initiateCells(wallKickGrid9);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,1));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickRotations10() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 3);
		grid.initiateCells(wallKickGrid10);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(1,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,1));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickRotations11() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 3);
		grid.initiateCells(wallKickGrid11);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(-1,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(1,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickRotations12() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 3);
		grid.initiateCells(wallKickGrid12);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(-1,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(1,1));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickNoLRotation3() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 3);
		grid.initiateCells(wallKickNoRotation3);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(-1,0));
		assertFalse(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(2));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(-1,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickRotations13() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(3, 3);
		grid.initiateCells(wallKickGrid13);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(0,1));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickRotations14() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(3, 3);
		grid.initiateCells(wallKickGrid14);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(0,1));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(1,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickRotations15() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(5, 3);
		grid.initiateCells(wallKickGrid15);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(2,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickRotations16() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(5, 3);
		grid.initiateCells(wallKickGrid16);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(2,1));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickNoLRotation4() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(3, 3);
		grid.initiateCells(wallKickNoRotation4);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertFalse(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickIRotations1() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickIGrid1);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(1));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,-2));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickIRotations2() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickIGrid2);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(1));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,1));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickIRotations3() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickIGrid3);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(-1,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(1));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,-2));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickIRotations4() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickIGrid4);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(2,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(1));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,1));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickNoIRotation1() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickNoIRotation1);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(2,0));
		assertFalse(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(2,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickIRotations5() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickIGrid5);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(1));
		grid.setCoordinates(new TetrisCoordinates(0,1));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(2));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickIRotations6() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickIGrid6);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(1));
		grid.setCoordinates(new TetrisCoordinates(0,-2));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(2));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickIRotations7() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickIGrid7);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(1));
		grid.setCoordinates(new TetrisCoordinates(0,1));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(2));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(-2,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickIRotations8() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickIGrid8);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(1));
		grid.setCoordinates(new TetrisCoordinates(0,-2));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(2));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(1,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickNoIRotation2() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickNoIRotation2);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(1));
		grid.setCoordinates(new TetrisCoordinates(0,-2));
		assertFalse(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(1));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,-2));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickIRotations9() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickIGrid9);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,2));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickIRotations10() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickIGrid10);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,-1));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickIRotations11() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickIGrid11);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(1,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,2));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickIRotations12() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickIGrid12);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(-2,0));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,-1));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickNoIRotation3() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickNoIRotation3);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(-2,0));
		assertFalse(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(2));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(-2,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickIRotations13() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickIGrid13);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(0,-1));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickIRotations14() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickIGrid14);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(0,2));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickIRotations15() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickIGrid15);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(0,-1));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(2,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickIRotations16() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickIGrid16);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(0,2));
		assertTrue(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(-1,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickNoIRotation4() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickNoIRotation4);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(0,2));
		assertFalse(grid.tryRotateRight());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,2));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickLeftRotations1() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(3, 3);
		grid.initiateCells(wallKickLeftGrid1);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,1));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickLeftRotations2() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(3, 3);
		grid.initiateCells(wallKickLeftGrid2);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(1,0));
		assertTrue(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,1));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickLeftRotations3() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(5, 3);
		grid.initiateCells(wallKickLeftGrid3);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(2,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickLeftRotations4() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(5, 3);
		grid.initiateCells(wallKickLeftGrid4);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(2,1));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickLeftNoRotation1() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(5, 3);
		grid.initiateCells(wallKickLeftNoRotation1);
		grid.setTetromino(TetrominoShape.LSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertFalse(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.LSHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickLeftIRotations1() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickLeftIGrid1);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,-1));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickLeftIRotations2() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickLeftIGrid2);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		assertTrue(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,2));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickLeftIRotations3() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickLeftIGrid3);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(2,0));
		assertTrue(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,-1));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickLeftIRotations4() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickLeftIGrid4);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(-1,0));
		assertTrue(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(3));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(0,2));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testWallKickLeftNoIRotation1() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 4);
		grid.initiateCells(wallKickLeftNoIRotation1);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(-1,0));
		assertFalse(grid.tryRotateLeft());
		assertEquals(grid.getTetromino(), TetrominoShape.ISHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(-1,0));
		assertFalse(grid.hasConflicts());
	}
	
	@Test
	void testMerge1() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(0,0));
		grid.merge();
		assertTrue(testEqualGrids(grid, smallGridWithIOnTop));
		assertEquals(grid.getTetromino(), null);
		assertEquals(grid.getCoordinates(), null);
	}
	
	@Test
	void testMerge2() {
		// We place a J shape on top left of the small grid
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.setTetromino(TetrominoShape.JSHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(0,3));
		grid.merge();
		assertTrue(testEqualGrids(grid,smallGridWithJRight));
		assertEquals(grid.getTetromino(), null);
		assertEquals(grid.getCoordinates(), null);
	}
	
	@Test
	void testMerge3() {
		// We place a T shape just over the cells of the small grid
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.setTetromino(TetrominoShape.TSHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(5,1));
		grid.merge();
		assertTrue(testEqualGrids(grid,smallGridWithT));
		assertEquals(grid.getTetromino(), null);
		assertEquals(grid.getCoordinates(), null);
	}
	
	@Test
	void testMerge4() {
		// We place a T shape overlapping the cells of the small grid
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.setTetromino(TetrominoShape.TSHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(6,1));
		grid.merge();
		assertTrue(testEqualGrids(grid,smallGridOverlap));
		assertEquals(grid.getTetromino(), null);
		assertEquals(grid.getCoordinates(), null);
	}
	
	@Test
	void testMergeTop() {
		// We place a T shape overlapping the cells of the small grid
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 5);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(-1,0));
		grid.merge();
		assertTrue(testEqualGrids(grid,mergeTestTop));
		assertEquals(grid.getTetromino(), null);
		assertEquals(grid.getCoordinates(), null);
	}
	
	@Test
	void testMergeLeft() {
		// We place a T shape overlapping the cells of the small grid
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 5);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(0,-1));
		grid.merge();
		assertTrue(testEqualGrids(grid,mergeTestLeft));
		assertEquals(grid.getTetromino(), null);
		assertEquals(grid.getCoordinates(), null);
	}
	
	@Test
	void testMergeRight() {
		// We place a T shape overlapping the cells of the small grid
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 5);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(3));
		grid.setCoordinates(new TetrisCoordinates(0,3));
		grid.merge();
		assertTrue(testEqualGrids(grid,mergeTestRight));
		assertEquals(grid.getTetromino(), null);
		assertEquals(grid.getCoordinates(), null);
	}
	
	@Test
	void testMergeBottom() {
		// We place a T shape overlapping the cells of the small grid
		TetrisGrid grid = TetrisGrid.getEmptyGrid(4, 5);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(2,0));
		grid.merge();
		assertTrue(testEqualGrids(grid,mergeTestBottom));
		assertEquals(grid.getTetromino(), null);
		assertEquals(grid.getCoordinates(), null);
	}
	
	@Test
	void testMergeNoTet() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.merge();
		assertTrue(testEqualGrids(grid,smallGrid));
	}
	
	@Test
	void testMergeException() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		assertThrows(IllegalStateException.class, () -> grid.merge());
	}
	
	@Test
	void testHardDrop1() {
		// Hard drop of a T shape pointing down on small gridd
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.setTetromino(TetrominoShape.TSHAPE.getTetromino(2));
		grid.setCoordinates(new TetrisCoordinates(0,1));
		grid.hardDrop();
		assertTrue(testEqualVisibles(grid,smallGridWithT));
		assertEquals(grid.getTetromino(), TetrominoShape.TSHAPE.getTetromino(2));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(5,1));
	}
	
	@Test
	void testHardDrop2() {
		// Hard drop of a T shape pointing up on small gridd
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		grid.setTetromino(TetrominoShape.TSHAPE.getTetromino(0));
		grid.setCoordinates(new TetrisCoordinates(0,2));
		grid.hardDrop();
		assertEquals(grid.getTetromino(), TetrominoShape.TSHAPE.getTetromino(0));
		assertEquals(grid.getCoordinates(), new TetrisCoordinates(6,2));
	}
 	
	@Test
	void testHardDropException() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		assertThrows(IllegalStateException.class, () -> grid.hardDrop());
		grid.setTetromino(TetrominoShape.ISHAPE.getTetromino(0));
		assertThrows(IllegalStateException.class, () -> grid.hardDrop());
	}
	
	@Test
	void testIsFull1() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		assertTrue(grid.isFull(8));
		assertTrue(grid.isFull(9));
		assertFalse(grid.isFull(7));
		assertFalse(grid.isFull(0));
	}
	
	@Test
	void testIsFull2() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid2);
		assertTrue(grid.isFull(9));
		assertTrue(grid.isFull(8));
		assertFalse(grid.isFull(7));
		assertFalse(grid.isFull(6));
		assertTrue(grid.isFull(5));
		assertFalse(grid.isFull(4));
		assertFalse(grid.isFull(3));
		assertFalse(grid.isFull(2));
		assertFalse(grid.isFull(1));
		assertFalse(grid.isFull(0));
	}
	
	@Test
	void testIsEmpty1() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		assertFalse(grid.isEmpty(9));
		assertFalse(grid.isEmpty(8));
		assertFalse(grid.isEmpty(7));
		for(int i=0; i< 7; i++) {
			assertTrue(grid.isEmpty(i));
		}
	}
	
	@Test
	void testIsEmpty2() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid2);
		for(int i=0; i< 10; i++) {
			assertFalse(grid.isEmpty(i));
		}
	}
	
	@Test
	void testIsEmptyGrid() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		assertTrue(grid.isEmpty());
		grid.initiateCells(smallGrid);
		assertFalse(grid.isEmpty());
	}
	
	@Test
	void testPack1() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		List<Integer> lines = grid.pack();
		assertEquals(grid.numberOfLines(),10);
		assertEquals(lines.size(), 2);
		assertTrue(lines.contains(8));
		assertTrue(lines.contains(9));
		assertTrue(testEqualGrids(grid, smallGridPacked));
	}
	
	@Test
	void testPack2() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid2);
		List<Integer> lines = grid.pack();
		assertEquals(grid.numberOfLines(),10);
		assertEquals(lines.size(), 3);
		assertTrue(lines.contains(8));
		assertTrue(lines.contains(9));
		assertTrue(lines.contains(5));
		assertTrue(testEqualGrids(grid, smallGrid2Packed));
	}
	
	@Test
	void testFullLine1s() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid);
		List<Integer> lines = grid.fullLines();
		assertEquals(lines.size(), 2);
		assertTrue(lines.contains(8));
		assertTrue(lines.contains(9));
		assertTrue(testEqualGrids(grid, smallGrid));
	}
	
	@Test
	void testFullLines2() {
		TetrisGrid grid = TetrisGrid.getEmptyGrid(10, 5);
		grid.initiateCells(smallGrid2);
		List<Integer> lines = grid.fullLines();
		assertEquals(lines.size(), 3);
		assertTrue(lines.contains(8));
		assertTrue(lines.contains(9));
		assertTrue(lines.contains(5));
		assertTrue(testEqualGrids(grid, smallGrid2));
	}
	
}
