package net.omidn.sudoko;

import java.util.Arrays;

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

	public int getCell(int i, int j) {
		return table[i][j];
	}

	public int getSize() {
		return size;
	}

	@Override
	protected SudokuTable clone() {
		int[][] newTable = new int[size][size];
		for (int i = 0; i < size; i++) {
			newTable[i] = this.table[i].clone();
		}
		SudokuTable newObject = new SudokuTable(newTable);
		return newObject;
	}

	
	/**
	 * Returns a pretty form of this table. If the size of table is 9, then the returned string might look
	 * like some thig like this:<br><br>
	 * 
	 * 0 0 0 | 0 0 0 | 0 0 0 <br>
     * 0 0 0 | 0 0 0 | 0 0 0 <br>
     * 0 0 0 | 0 0 0 | 0 0 0 <br>
     * ------|-------|-------<br>
     * 0 0 0 | 0 0 0 | 0 0 0 <br>
     * 0 0 0 | 0 0 0 | 0 0 0 <br>
     * 0 0 0 | 0 0 0 | 0 0 0 <br>
     * ------|-------|-------<br>
     * 0 0 0 | 0 0 0 | 0 0 0 <br>
     * 0 0 0 | 0 0 0 | 0 0 0 <br>
     * 0 0 0 | 0 0 0 | 0 0 0 <br>
	 * 
	 */
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < size; i++) {
			int sizeSqrt = (int) Math.sqrt(size);
			if(i % sizeSqrt == 0 && i !=0) {
				StringBuilder lineDelim = new StringBuilder();
				for (int k = 0; k < size;k++) {
					if(k / sizeSqrt > 0 && k % sizeSqrt == 1) {
						lineDelim.append("-");
					}
					if (k % sizeSqrt == 0 && k !=0) {
						lineDelim.append("|");
					}
					
					lineDelim.append("--");
				}
				lineDelim.append('\n');
				sb.append(lineDelim);
			}
			for (int j = 0; j < size; j++) {
				if(j % sizeSqrt == 0 && j!=0) {
					sb.append("| ");
				}
				sb.append(getCell(i, j) + " ");
			}
			sb.append('\n');
		}

		return sb.toString();
	}

}
