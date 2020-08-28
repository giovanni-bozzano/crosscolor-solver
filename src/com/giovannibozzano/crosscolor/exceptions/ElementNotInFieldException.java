package com.giovannibozzano.crosscolor.exceptions;

/**
 * ElementNotInFieldException is thrown to indicate that a method has been
 * passed elements out of field.
 */
public class ElementNotInFieldException extends MathIllegalArgumentException
{
	public ElementNotInFieldException(String message)
	{
		super(message);
	}
}
