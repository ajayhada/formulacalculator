package com.myformula.exceptions;

/**
 * InvalidExpressionException occured when any exception occured in Expression object 
 * @author AjayHada
 *
 */
public class InvalidExpressionException extends Exception {

	/**
	 * one argument constractor
	 * @param msg
	 */
	public InvalidExpressionException(String msg) {
		super(msg);
	}

	/**
	 * zero argument constractor
	 */
	public InvalidExpressionException() {
		super("InvalidExpressionException throws :");
	}
	/**
	 * default serial id
	 */
	private static final long serialVersionUID = 1L;

}
