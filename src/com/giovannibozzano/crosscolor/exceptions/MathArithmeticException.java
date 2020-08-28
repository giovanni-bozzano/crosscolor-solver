package com.giovannibozzano.crosscolor.exceptions;

/**
 * MathArithmeticException is thrown to indicate that operation has no solution
 * for arguments which have been passed to the method.
 */
public class MathArithmeticException extends MathIllegalArgumentException
{
	public MathArithmeticException(String message)
	{
		super(message);
	}
}
