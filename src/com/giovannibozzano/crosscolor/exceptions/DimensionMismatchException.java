package com.giovannibozzano.crosscolor.exceptions;

/**
 * DimensionMismatchException is thrown to indicate that a method has been
 * passed objects with wrong dimensions and computation cannot be performed.
 */
public class DimensionMismatchException extends MathIllegalArgumentException
{
	public DimensionMismatchException(String message)
	{
		super(message);
	}
}
