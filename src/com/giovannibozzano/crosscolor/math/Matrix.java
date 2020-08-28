package com.giovannibozzano.crosscolor.math;

import java.util.Arrays;

/**
 * Class Matrix is objective representation of matrix. Data are stored in
 * 2-dimensional array and each element of matrix is accessed by its position in
 * matrix (row, column).
 */
public class Matrix
{
	private final int[][] elements;
	private final int rows;
	private final int columns;

	/**
	 * Constructs a Matrix of specified size and elements.
	 *
	 * @param rows number of rows
	 * @param columns number of columns
	 * @param elements elements of the matrix
	 */
	public Matrix(int rows, int columns, int[][] elements)
	{
		this.rows = rows;
		this.columns = columns;
		this.elements = elements;
	}

	/**
	 * Constructs a Matrix of specified size. All elements of this matrix are
	 * set to zero.
	 *
	 * @param rows number of rows
	 * @param columns number of columns
	 */
	public Matrix(int rows, int columns)
	{
		this.rows = rows;
		this.columns = columns;
		this.elements = new int[rows][columns];
	}

	/**
	 * Constructs a Matrix. Constructed Matrix is identical with Matrix given as
	 * attribute to this constructor.
	 *
	 * @param matrix Matrix to clone
	 */
	public Matrix(Matrix matrix)
	{
		this.rows = matrix.getRows();
		this.columns = matrix.getColumns();
		this.elements = new int[this.rows][this.columns];
		for (int x = 0; x < this.rows; x++) {
			for (int y = 0; y < this.columns; y++) {
				this.elements[x][y] = matrix.getElement(x, y);
			}
		}
	}

	/**
	 * Set element of matrix at specified position to specified value.
	 *
	 * @param x row position of element
	 * @param y column position of element
	 * @param value value to be set
	 */
	public void setElement(int x, int y, int value)
	{
		this.elements[x][y] = value;
	}

	/**
	 * Returns element of matrix at specified position.
	 *
	 * @param x row position of element
	 * @param y column position of element
	 *
	 * @return element at specified position
	 */
	public int getElement(int x, int y)
	{
		return this.elements[x][y];
	}

	/**
	 * Returns number of rows in matrix.
	 *
	 * @return number of rows
	 */
	public int getRows()
	{
		return this.rows;
	}

	/**
	 * Returns number of columns in matrix.
	 *
	 * @return number of columns
	 */
	public int getColumns()
	{
		return this.columns;
	}

	@Override
	public boolean equals(Object matrix)
	{
		if (!(matrix instanceof Matrix)) {
			return false;
		}
		if (((Matrix) matrix).getRows() != this.rows || ((Matrix) matrix).getColumns() != this.columns) {
			return false;
		}
		for (int x = 0; x < this.rows; x++) {
			for (int y = 0; y < this.columns; y++) {
				if (((Matrix) matrix).getElement(x, y) != this.elements[x][y]) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int hashCode()
	{
		int hash = 3;
		hash = 13 * hash + Arrays.deepHashCode(this.elements);
		return hash;
	}

	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (int x = 0; x < this.rows; x++) {
			stringBuilder.append("[ ");
			for (int y = 0; y < this.columns; y++) {
				if (y == this.columns - 1) {
					stringBuilder.append(this.elements[x][y]);
				} else {
					stringBuilder.append(this.elements[x][y]);
					stringBuilder.append(", ");
				}
			}
			stringBuilder.append(" ]\n");
		}
		return stringBuilder.toString();
	}
}
