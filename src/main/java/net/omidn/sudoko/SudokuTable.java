package net.omidn.sudoko;

public class SudokuTable {

	private int[][] table;
	private int size;

	private SudokuTable(int[][] table) {
		if (table.length != table[0].length) {
			throw new IllegalArgumentException("Sudoku table must be a square");
		}
		if (Math.sqrt(table.length) - Math.floor(Math.sqrt(table.length)) != 0) {
			throw new IllegalArgumentException("Sudoku table must of size of a perfect square root.");
		}
		this.table = table;
		this.size = table.length;
	}
	
	public static SudokuTable of2DIntArray(int[][] table) {
		return new SudokuTable(table);
	}
	
	
	
	public static SudokuTable allZeroTable(int size) {
		return new SudokuTable(new int[size][size]);
	}
	
	public synchronized void setCell(int i, int j, int value) {
		table[i][j] = value;
	}
	
}
