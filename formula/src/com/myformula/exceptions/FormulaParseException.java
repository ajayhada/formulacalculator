package com.myformula.exceptions;

/**
 * formula parser Exception occures while parsion formula
 * @author AjayHada
 *
 */
public class FormulaParseException extends Exception {

	public FormulaParseException(String msg) {
		super(msg);
	}

	public FormulaParseException() {
		super("InvalidExpressionException throws :");
	}
	/**
	 * default serial id
	 */
	private static final long serialVersionUID = 1L;

}
