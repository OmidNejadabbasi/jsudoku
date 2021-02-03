package net.omidn.sudoko;

public class SudokuTable {

	private int[][] table;

	private SudokuTable(int[][] table) {
		if (table.length != table[0].length) {
			throw new IllegalArgumentException("Sudoku table must be a square");
		}
		this.table = table;
	}
	
}
