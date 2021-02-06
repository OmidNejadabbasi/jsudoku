package net.omidn.sudoko;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuSolver {
	public static SudokuTable solve(SudokuTable table) {
		
		SudokuTable newTable = table.clone();
		
		// initializing the arrayList that holds the possible answers for a cell
		List<List<List<Integer>>> possibleAnswers = new ArrayList<>();
		int tableSize = table.getSize();
		for (int i = 0; i < tableSize; i++) {
			possibleAnswers.add(new ArrayList<List<Integer>>());
			for (int j = 0; j < tableSize; j++) {
				possibleAnswers.get(i).add(new ArrayList<Integer>());
				for (int k = 0; k < tableSize; k++) {
					possibleAnswers.get(i).get(j).add(k + 1);
				}
			}
		}

		// clearing the possible answers for cell that are already occupied
		for (int i = 0; i < tableSize; i++) {
			for (int j = 0; j < tableSize; j++) {
				if (table.getCell(i, j) != 0) {
					possibleAnswers.get(i).get(j).clear();
				}
			}
		}

		for (int i = 0; i < tableSize; i++) {
			for (int j = 0; j < tableSize; j++) {
				if (table.getCell(i, j) == 0) {
					possibleAnswers.get(i).get(j).removeAll(notValidValues(table, i, j));
				}
			}
		}
		
		recursiveSolve(possibleAnswers, newTable);
		
		return newTable;
	}

	private static boolean recursiveSolve(List<List<List<Integer>>> possibleAnswers, SudokuTable newTable) {
		fillMandatory(possibleAnswers, newTable);
		
		return false;
	}

	/**
	 * Fills any cell in <code>table</code> that only can have one value.
	 * 
	 * @param possibleAnswers The list of possible answers for each cell
	 * @param table The table to fill
	 */
	private static void fillMandatory(List<List<List<Integer>>> possibleAnswers, SudokuTable table) {
		int tableSize = table.getSize();
		for (int i = 0; i < tableSize; i++) {
			for (int j = 0; j < tableSize; j++) {
				if (possibleAnswers.get(i).get(j).size() == 1) {
					table.setCell(i, j, possibleAnswers.get(i).get(j).get(0));
					removeInterferences(possibleAnswers, possibleAnswers.get(i).get(j).get(0));
				}
			}
		}
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

	private static Collection<Integer> notValidValues(SudokuTable table, int i, int j) {

		Set<Integer> notValidSet = new HashSet<>();

		// adding numbers in row i to the set
		for (int x = 0; x < table.getSize(); x++) {
			if (table.getCell(i, x) != 0) {
				notValidSet.add(table.getCell(i, x));
			}
		}

		// adding numbers in column j to the set
		for (int x = 0; x < table.getSize(); x++) {
			if (table.getCell(x, j) != 0) {
				notValidSet.add(table.getCell(i, x));
			}
		}

		// add values from the part this cell is on to the set
		int sqr = (int) Math.sqrt(table.getSize());
		int start_x = (i / sqr) * sqr;
		int start_y = (j / sqr) * sqr;
		for (int x = 0; x < sqr; x++) {
			for (int y = 0; y < sqr; y++) {
				if (table.getCell(start_x + x, start_y + y) != 0) {
					notValidSet.add(table.getCell(start_x + x, start_y + y));
				}
			}
		}

		return notValidSet;

	}

}
