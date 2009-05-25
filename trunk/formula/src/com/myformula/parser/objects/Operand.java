package com.myformula.parser.objects;


/**
 * Operand is a value Object for formula values.<br>
 * Formula Execution Framework supports so many type of value objects<br>
 * List of supported Operands.
 * NUMBERS - 22 , 22.77 , ANY NUMERIC FORMAT (UNARY MINUS/PLUS NOT ALLOWED)<BR>
 * STRINGS - [ANY STRING BETWEEN SQURE BRAKETS] CASE SENCETIVE <BR>
 * VARIABLES - {DATATYPE#VARIABLE_NAME} CURLY BRESES + # VARIABLE_NAME + CURLY BRESES<BR> 
 * DATE - SHOULD BE IN THIS FORMAT [22.09.2009] <BR>
 * BOOLEAN = TRUE / FALSE (WITHOUT CULY BRESIS) <BR>
 * METHODS - METHOD_NAME[COMA SAPERATED ARGUMENTS] <BR>
 * NULL = REPRESENT AS IT SELF NULL<BR>
 * Expression = (3+7)(in Expression Object)
 * @author AjayHada
 * 
 */
public class Operand extends Token {

	/**
	 * valueType of operands
	 */
	private long valueType;

	/**
	 * String value of Operand
	 */
	private String Value;

	/**
	 * return the type of Operand <br>
	 * List of Supported Operand Values..
	 * 
	 * @return
	 */
	public long getValueType() {
		return this.valueType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myformula.objects.Tokens#getTokenType()
	 */
	public int getTokenType() {
		return TOKEN_TYPE_VALUE;
	}

	/**
	 * sets the type of Operand 
	 * @param valueType
	 */
	public void setValueType(long valueType) {
		this.valueType = valueType;
	}

	/**
	 * return the value of Operand in string.
	 * @return
	 */
	public String getValue() {
		return this.Value;
	}

	/**
	 * sets the value of Operand in string.
	 * @param value
	 */
	public void setValue(String value) {
		this.Value = value;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.Value;
	}

}
