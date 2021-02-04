package net.omidn.sudoko;

import java.util.ArrayList;
import java.util.List;

public class SudokuSolver {
	public static SudokuTable solve(SudokuTable table) {
		// initializing the arrayList that holds the possible answers for a cell
		List<List<List<Integer>>> possibleAnswers = new ArrayList<>();
		int tableSize = table.getSize();
		for (int i = 0; i < tableSize; i++) {
			possibleAnswers.add(new ArrayList<List<Integer>>());
			for (int j = 0; j < tableSize; j++) {
				possibleAnswers.get(i).add(new ArrayList<Integer>());
				for (int k = 0; k < tableSize; k++) {
					possibleAnswers.get(i).get(j).add(k+1);
				}
			}
		}
		return null;
	}

	private static class Cell {
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
		for (int x = i; x < table.getSize(); x++) {
			// bound of the y iteration counter
			int bound = j;
			if (x > i) {
				bound = 0;
			}
			for (int y = bound; y < table.getSize(); y++) {
				if (table.getCell(x, y) == 0) {
					nextEmptyCell = new Cell(x, y);
					return nextEmptyCell;
				}
			}
		}
		return nextEmptyCell;
	}
}
