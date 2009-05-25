package com.myformula.parser;

/**
 * provide all required constants to parse, validate and execute the formula.
 * @author AjayHada
 * 
 */
public interface Constents {

	public static final String OPERATOR_PLUS = "+";
	public static final String OPERATOR_MINUS = "-";
	public static final String OPERATOR_DIVIDE = "/";
	public static final String OPERATOR_MULTIPLY = "*";
	public static final String OPERATOR_MOD = "%";
	public static final String OPERATOR_POWER = "^";
	public static final String OPERATOR_PLUE_PLUS = "++";
	public static final String OPERATOR_MINUS_MINUS = "--";
	public static final String OPERATOR_GREATER_THAN = ">";
	public static final String OPERATOR_GREATER_THAN_EQUAL = ">=";
	public static final String OPERATOR_LESS_THAN = "<";
	public static final String OPERATOR_LESS_THAN_EQUAL = "<=";
	public static final String OPERATOR_EQUALS = "=";
	public static final String OPERATOR_NOT_EQUALS = "!=";
	public static final String OPERATOR_LOGICAL_AND = "&&";
	public static final String OPERATOR_LOGICAL_OR = "||";
	public static final String OPERATOR_LOGICAL_NOT = "!";
	public static final String OPERATOR_BITWISE_AND = "&";
	public static final String OPERATOR_BITWISE_OR = "|";
	public static final String OPERATOR_BITWISE_NOT = "~";
	public static final String OPERATOR_BITWISE_LEFT_SHIFT = "<<";
	public static final String OPERATOR_BITWISE_SIGNED_RIGHT_SHIFT = ">>";
	public static final String OPERATOR_BITWISE_UNSIGNED_RIGHT_SHIFT = ">>>";
	public static final String OPERATOR_TERNARY = "?";
	public static final String OPERATOR_TERNARY_COLON = ":";
	
	public static final String OPENING_BRACE = "(";
	public static final String CLOSING_BRACE = ")";
	
	public static final long OPERAND_VALUE_STRING = 1001;
	public static final long OPERAND_VALUE_INTEGER = 1002;
	public static final long OPERAND_VALUE_DOUBLE = 1003;
	public static final long OPERAND_VALUE_BOOLEAN = 1004;
	public static final long OPERAND_VALUE_DATE = 1005;

	public static final String FUNCTION_ABS = "ABS";
	public static final String FUNCTION_FLOOR = "FLOOR";
	public static final String FUNCTION_CEIL = "CEILING";
	public static final String FUNCTION_ROUND = "ROUND";
	public static final String FUNCTION_ROUNDDECIMAL = "ROUNDDECIMAL";
	public static final String FUNCTION_SUM = "SUM";
	public static final String FUNCTION_AVG = "AVG";

}
