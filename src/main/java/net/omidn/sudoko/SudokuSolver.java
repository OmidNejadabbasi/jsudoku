package net.omidn.sudoko;

public class SudokuSolver {
	public static SudokuTable solve(SudokuTable table) {
		
		
		
		return null;
	}
	
	private static class Cell{
		int i;
		int j;
		public Cell(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}
	
	private static Cell getNextEmptyCell(SudokuTable table, int i, int j) {
		Cell nextEmptyCell = null;
		for(int x = i;x<table.getSize();x++) {
			// bound of the y iteration counter
			int bound = j;
			if (x>i) {
				bound = 0;
			}
			for(int y = bound;y<table.getSize();y++) {
				if(table.getCell(x, y) == 0) {
					nextEmptyCell=new Cell(x, y);
					return nextEmptyCell;
				}
			}
		}
		return nextEmptyCell;
	}
}
