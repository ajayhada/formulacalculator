package com.myformula.parser.objects;

/**
 *  <code>Token</code> is a super class of all token strings of formula.
 * @author AjayHada
 * 
 */
public abstract class Token {
	
	/**
	 * Default Token value - 900
	 */
	public static final int TOKEN_TYPE_VALUE = 900;
	
	/**
	 * Operator Token value - 901
	 */
	public static final int TOKEN_TYPE_OPERATOR = 901;
	
	/**
	 * Operand Token value - 902
	 */
	public static final int TOKEN_TYPE_OPERAND = 902;
	
	/**
	 * Expression Token value - 903
	 */
	public static final int TOKEN_TYPE_EXPRESSION = 903;
	
	/**
	 * Function Token value - 904
	 */
	public static final int TOKEN_TYPE_FUNCTION = 904;
	
	
	/**
	 *  method returns the token type value for there concrete objects.
	 * @return
	 */
	public abstract int getTokenType();
	
}
