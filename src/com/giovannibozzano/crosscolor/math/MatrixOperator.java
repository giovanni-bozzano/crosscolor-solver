package com.giovannibozzano.crosscolor.math;

import com.giovannibozzano.crosscolor.exceptions.DimensionMismatchException;
import com.giovannibozzano.crosscolor.exceptions.MathArithmeticException;
import com.giovannibozzano.crosscolor.exceptions.MathIllegalArgumentException;

public class MatrixOperator
{
	private final GaloisField galoisField;

	public MatrixOperator(GaloisField galoisField)
	{
		this.galoisField = galoisField;
	}

	public int rank(Matrix matrix)
	{
		this.isValid(matrix);
		int rank = 0;
		Matrix result = this.gauss(matrix);
		for (int x = 0; x < matrix.getRows(); x++) {
			for (int y = 0; y < matrix.getColumns(); y++) {
				if (result.getElement(x, y) != 0) {
					rank++;
					break;
				}
			}
		}
		return rank;
	}

	// Return input matrix in row echelon form
	public Matrix gauss(Matrix matrix)
	{
		this.isValid(matrix);
		// Prepare result
		Matrix result = new Matrix(matrix);
		for (int diagonalPosition = 1; diagonalPosition < Math.min(matrix.getRows(), matrix.getColumns()) + 1; diagonalPosition++) {
			int value;
			// Find column with pivot, swap lines if necessary
			int columnPosition = diagonalPosition - 1;
			while (columnPosition < matrix.getColumns() && result.getElement(diagonalPosition - 1, columnPosition) == 0) {
				result = this.findPivot(result, columnPosition, diagonalPosition - 1);
				if (result.getElement(diagonalPosition - 1, columnPosition) == 0) {
					columnPosition++;
				}
			}
			if (columnPosition == matrix.getColumns()) {
				columnPosition = diagonalPosition - 1;
			}
			for (int rowsUnderDiagonalPosition = diagonalPosition; rowsUnderDiagonalPosition < matrix.getRows(); rowsUnderDiagonalPosition++) {
				try {
					// Set value, it will be used to set column at diagonalPosition to zero
					value = this.galoisField.divide(result.getElement(rowsUnderDiagonalPosition, columnPosition), result.getElement(diagonalPosition - 1, columnPosition));
					// Subtract from line, pivot will be set to zero and other values will be edited
					for (int colsUnderDiagPos = diagonalPosition - 1; colsUnderDiagPos < matrix.getColumns(); colsUnderDiagPos++) {
						result.setElement(rowsUnderDiagonalPosition, colsUnderDiagPos, this.galoisField.subtract(this.galoisField.multiply(result.getElement(diagonalPosition - 1, colsUnderDiagPos), value), result.getElement(rowsUnderDiagonalPosition, colsUnderDiagPos)));
					}
				} catch (MathArithmeticException exception) {
					// Catched division by zero, ok, thrown by columns full of zeroes
				}
			}
		}
		// Result matrix is in row echelon form
		return result;
	}

	// equationMatrix * result = results
	public Vector solveLinearEquationsSystem(Matrix equationMatrix, Vector results)
	{
		this.isValid(equationMatrix);
		if (equationMatrix.getRows() != equationMatrix.getColumns()) {
			throw new MathArithmeticException("Cannot solve linear equations system for non-square matrix.");
		}
		if (equationMatrix.getRows() != results.getSize()) {
			throw new DimensionMismatchException("Cannot solve linear equations system: dimension mismatch.");
		}
		if (rank(equationMatrix) != equationMatrix.getRows()) {
			throw new MathArithmeticException("Cannot solve linear equations system: linearly dependent rows.");
		}
		// Prepare equation matrix
		Matrix completeMatrix = new Matrix(equationMatrix.getRows(), equationMatrix.getColumns() + 1);
		for (int x = 0; x < equationMatrix.getRows(); x++) {
			for (int y = 0; y < equationMatrix.getColumns(); y++) {
				completeMatrix.setElement(y, x, equationMatrix.getElement(x, y));
			}
			completeMatrix.setElement(x, equationMatrix.getColumns(), results.getElement(x));
		}
		completeMatrix = this.gauss(completeMatrix);
		// Set result vector from values prepared in completeMatrix
		Vector result = new Vector(equationMatrix.getColumns());
		for (int x = completeMatrix.getColumns() - 2; x >= 0; x--) {
			for (int y = completeMatrix.getColumns() - 2; y >= x; y--) {
				if (y == x) {
					result.setElement(x, this.galoisField.divide(completeMatrix.getElement(x, completeMatrix.getColumns() - 1), completeMatrix.getElement(x, x)));
				} else {
					completeMatrix.setElement(x, completeMatrix.getColumns() - 1, this.galoisField.subtract(completeMatrix.getElement(x, completeMatrix.getColumns() - 1), this.galoisField.multiply(completeMatrix.getElement(x, y), result.getElement(y))));
				}
			}
		}
		return result;
	}

	// Check if matrix has no zero rows or columns
	public void isValid(Matrix matrix)
	{
		if (matrix.getRows() == 0) {
			throw new MathIllegalArgumentException("Matrix argument is empty, operation cannot be performed.");
		}
		if (matrix.getColumns() == 0) {
			throw new MathIllegalArgumentException("Argument matrix has empty row, operation cannot be performed.");
		}
	}

	// Change line row with line with pivot at position column
	public Matrix findPivot(Matrix matrix, int column, int row)
	{
		for (int x = row; x < matrix.getRows(); x++) {
			for (int y = 0; y < column + 1; y++) {
				if (matrix.getElement(x, y) != 0 && y == column) {
					return this.swapLines(matrix, row, x);
				}
				if (matrix.getElement(x, y) != 0) {
					break;
				}
			}
		}
		return matrix;
	}

	// Return matrix with swapped rows (row1, row2)
	public Matrix swapLines(Matrix matrix, int row1, int row2)
	{
		Matrix result = new Matrix(matrix.getRows(), matrix.getColumns());
		for (int x = 0; x < matrix.getRows(); x++) {
			if (x == row1) {
				for (int y = 0; y < matrix.getColumns(); y++) {
					result.setElement(row1, y, matrix.getElement(row2, y));
				}
			}
			if (x == row2) {
				for (int y = 0; y < matrix.getColumns(); y++) {
					result.setElement(row2, y, matrix.getElement(row1, y));
				}
			}
			if (x != row1 && x != row2) {
				for (int y = 0; y < matrix.getColumns(); y++) {
					result.setElement(x, y, matrix.getElement(x, y));
				}
			}
		}
		return result;
	}
}
