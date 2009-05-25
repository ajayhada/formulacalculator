package com.myformula.parser;

/**
 * 
 * formats of arguments for formula.. <BR>
 * NUMBERS - 22 , 22.77 , ANY NUMERIC FORMAT (UNARY MINUS/PLUS NOT ALLOWED)<BR>
 * STRINGS - [ANY STRING BETWEEN SQURE BRAKETS] CASE SENCETIVE <BR>
 * VARIABLES - {DATATYPE#VARIABLE_NAME} CURLY BRESES + # VARIABLE_NAME + CURLY BRESES<BR> 
 * DATE - SHOULD BE IN THIS FORMAT [22.09.2009] <BR>
 * BOOLEAN = TRUE / FALSE (WITHOUT CULY BRESIS) <BR>
 * METHODS - METHOD_NAME[COMA SAPERATED ARGUMENTS] <BR>
 * NULL = REPRESENT AS IT SELF NULL<BR>
 * 
 * variable value should not be an expression
 * @author AjayHada
 * 
 */
public class Formula {

	private String formulaString;

	/**
	 * sets the formula string.
	 * @param formulaString
	 */
	public Formula(String formulaString) {
		this.formulaString = formulaString;
	}

	/**
	 * returns the formula string.
	 * @return
	 */
	public String getFormulaString() {
		return this.formulaString;
	}

}
