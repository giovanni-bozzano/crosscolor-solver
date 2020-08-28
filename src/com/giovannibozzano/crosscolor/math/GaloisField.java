package com.giovannibozzano.crosscolor.math;

import com.giovannibozzano.crosscolor.exceptions.ElementNotInFieldException;
import com.giovannibozzano.crosscolor.exceptions.MathArithmeticException;
import com.giovannibozzano.crosscolor.exceptions.MathIllegalArgumentException;

/**
 * Class GaloisField implements methods for computation with finite fields.
 */
public class GaloisField
{
	private final int fieldSize;

	public GaloisField(int fieldSize)
	{
		if (fieldSize <= 0) {
			throw new MathIllegalArgumentException("Field size must be represented by positive number.");
		}
		this.fieldSize = fieldSize;
	}

	public int add(int element1, int element2)
	{
		this.isInField(element1);
		this.isInField(element2);
		return (element1 + element2) % this.fieldSize;
	}

	public int subtract(int element1, int element2)
	{
		this.isInField(element1);
		this.isInField(element2);
		int result = element1 - element2;
		if (result < 0) {
			return result + this.fieldSize;
		}
		return result;
	}

	public int multiply(int element1, int element2)
	{
		this.isInField(element1);
		this.isInField(element2);
		int result = 0;
		for (int i = 0; i < element2; i++) {
			result = this.add(result, element1);
		}
		return result;
	}

	public int divide(int element1, int element2)
	{
		this.isInField(element1);
		this.isInField(element2);
		if (element2 == 0) {
			throw new MathArithmeticException("Division by zero.");
		}
		int temp = 0;
		int iterations = 0;
		while (temp != element1) {
			temp = this.add(temp, element2);
			iterations++;
		}
		return iterations;
	}

	public void isInField(int element)
	{
		if ((element >= this.fieldSize) || (element < 0)) {
			throw new ElementNotInFieldException("Values for this Galois field must be in [0, " + (this.fieldSize - 1) + "].");
		}
	}
}
