package com.giovannibozzano.crosscolor.math;

import java.util.Arrays;

/**
 * Class Vector is objective representation of vector. Data are stored in array
 * and each element of vector is accessed by its numerical index.
 */
public class Vector
{
	private int[] elements;
	private int size;

	/**
	 * Constructs a Vector of specified elements.
	 *
	 * @param elements elements of the vector
	 */
	public Vector(int[] elements)
	{
		this.size = elements.length;
		this.elements = elements;
	}

	/**
	 * Constructs a Vector of specified size. All elements of this vector are
	 * set to zero.
	 *
	 * @param size size of constructed polynomial
	 */
	public Vector(int size)
	{
		this.elements = new int[size];
		this.size = size;
	}

	/**
	 * Set element of vector at specified index to specified value.
	 *
	 * @param index index of element
	 * @param value value to be set
	 */
	public void setElement(int index, int value)
	{
		this.elements[index] = value;
	}

	/**
	 * Returns element of vector at specified index.
	 *
	 * @param index index of element
	 *
	 * @return element at specified index
	 */
	public int getElement(int index)
	{
		return this.elements[index];
	}

	/**
	 * Returns size of a vector.
	 *
	 * @return size of a vector
	 */
	public int getSize()
	{
		return this.size;
	}

	@Override
	public boolean equals(Object vector)
	{
		if (!(vector instanceof Vector)) {
			return false;
		}
		if (((Vector) vector).getSize() != this.size) {
			return false;
		}
		for (int x = 0; x < this.size; x++) {
			if (this.elements[x] != ((Vector) vector).getElement(x)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = 47 * hash + Arrays.hashCode(this.elements);
		return hash;
	}

	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("( ");
		for (int x = 0; x < this.size; x++) {
			if (x == this.size - 1) {
				stringBuilder.append(this.elements[x]);
			} else {
				stringBuilder.append(this.elements[x]);
				stringBuilder.append(", ");
			}
		}
		stringBuilder.append(" )");
		return stringBuilder.toString();
	}
}
