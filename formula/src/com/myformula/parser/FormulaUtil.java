package com.myformula.parser;

import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import com.myformula.parser.objects.Function;

/**
 * Utility Class for common static functions required at multiple places in
 * formula.
 * 
 * @author AjayHada
 * 
 */
public class FormulaUtil {

	public static final int TERNARY_PRECEDENCE = 1;
	public static final int LOGICAL_OR_PRECEDENCE = 2;
	public static final int LOGICAL_AND_PRECEDENCE = 3;
	public static final int RELATIONAL_EQUATE_PRECEDENCE = 4;
	public static final int RELATIONAL_GREATER_LESS_PRECEDENCE = 5;
	public static final int ADD_SUB_PRECEDENCE = 6;
	public static final int DIV_MUL_PRECEDENCE = 7;
	public static final int POWER_PRECEDENCE = 8;
	public static final int FUNCTION_PRECEDENCE = 9;
	public static final int EXPRESSION_PRECEDENCE = 10;
	/**
	 * @param token
	 * @return
	 */
	public static boolean isOperator(String token) {
		return Constents.OPERATOR_PLUS.equals( token ) || Constents.OPERATOR_MINUS.equals( token )
				|| Constents.OPERATOR_MULTIPLY.equals( token ) || Constents.OPERATOR_MOD.equals( token )
				|| Constents.OPERATOR_POWER.equals( token ) || Constents.OPERATOR_DIVIDE.equals( token )
				|| Constents.OPERATOR_EQUALS.equals( token ) || Constents.OPERATOR_GREATER_THAN.equals( token )
				|| Constents.OPERATOR_LESS_THAN.equals( token ) || Constents.OPERATOR_NOT_EQUALS.equals( token )
				|| Constents.OPERATOR_GREATER_THAN_EQUAL.equals( token )
				|| Constents.OPERATOR_LESS_THAN_EQUAL.equals( token ) || Constents.OPERATOR_LOGICAL_AND.equals( token )
				|| Constents.OPERATOR_LOGICAL_OR.equals( token ) || Constents.OPENING_BRACE.equals( token )
				|| Constents.CLOSING_BRACE.equals( token );
	}

	/**
	 * @param token
	 * @return
	 */
	public static boolean isTernary(String token) {
		return Constents.OPERATOR_TERNARY.equals(token);
	}

	/**
	 * Lists only the functions supported in this release. This list needs to be
	 * updated later on for adding more functionality. For more flrxibility this
	 * portion can be detached from calculator
	 * 
	 * @param token -
	 *            function name
	 * @return true if the token is one of the supported functions
	 */
	static boolean isFunction(String token) {
		String tempToken = token.toUpperCase();
		return  tempToken.startsWith(Constents.FUNCTION_ABS) || tempToken.startsWith(Constents.FUNCTION_CEIL)
				|| tempToken.startsWith(Constents.FUNCTION_FLOOR) || tempToken.startsWith(Constents.FUNCTION_ROUND)
				|| tempToken.startsWith(Constents.FUNCTION_ROUNDDECIMAL) || tempToken.startsWith(Constents.FUNCTION_SUM);
	}

	/**
	 * @param functionName
	 * @return
	 */
	static boolean isGroupFunction(String functionName) {
		String tempFunctionName = functionName.toUpperCase();
		return Constents.FUNCTION_SUM.equals(tempFunctionName) || Constents.FUNCTION_AVG.equals(tempFunctionName);
	}

	/**
	 * @param op1
	 * @param op2
	 * @return
	 */
	static boolean isHigherInPrecedence(String op1, String op2) {
		return FormulaUtil.getPrecedence(op1) >= FormulaUtil.getPrecedence(op2); 
		// >= to  handle associativity
	}

	/**
	 * @param operator
	 * @return
	 */
	public static int getPrecedence(String operator) {
		if (FormulaUtil.isFunction(operator)) {
			return FUNCTION_PRECEDENCE; // return highest precedence
		}
		if (Constents.OPERATOR_PLUS.equals( operator ) || Constents.OPERATOR_MINUS.equals( operator )) {
			return ADD_SUB_PRECEDENCE;

		} else if (Constents.OPERATOR_POWER.equals( operator )) {
			return POWER_PRECEDENCE;

		} else if (Constents.OPERATOR_MULTIPLY.equals( operator ) || Constents.OPERATOR_DIVIDE.equals( operator )
				|| Constents.OPERATOR_MOD.equals( operator )) {
			return DIV_MUL_PRECEDENCE;

		} else if (Constents.OPERATOR_LOGICAL_AND.equals( operator )) {
			return LOGICAL_AND_PRECEDENCE;

		} else if (Constents.OPERATOR_LOGICAL_OR.equals( operator )) {
			return LOGICAL_OR_PRECEDENCE;

		} else if (Constents.OPERATOR_GREATER_THAN.equals( operator ) || Constents.OPERATOR_LESS_THAN.equals( operator )
				|| Constents.OPERATOR_GREATER_THAN_EQUAL.equals( operator )
				|| Constents.OPERATOR_LESS_THAN_EQUAL.equals( operator )) {
			return RELATIONAL_GREATER_LESS_PRECEDENCE;

		} else if (Constents.OPERATOR_EQUALS.equals( operator ) || Constents.OPERATOR_NOT_EQUALS.equals( operator )) {
			return RELATIONAL_EQUATE_PRECEDENCE;

		} else if (Constents.OPERATOR_TERNARY.equals( operator )) {
			return TERNARY_PRECEDENCE;
		}
		return 0;
	}

	/**
	 * @param operator
	 * @return
	 */
	public static boolean isBinary(String operator) {
		return Constents.OPERATOR_DIVIDE.equals( operator ) || Constents.OPERATOR_PLUS.equals( operator )
				|| Constents.OPERATOR_MULTIPLY.equals( operator ) || Constents.OPERATOR_MINUS.equals( operator )
				|| Constents.OPERATOR_POWER.equals( operator ) || Constents.OPERATOR_MOD.equals( operator )
				|| Constents.OPERATOR_GREATER_THAN_EQUAL.equals( operator )
				|| Constents.OPERATOR_GREATER_THAN.equals( operator )
				|| Constents.OPERATOR_LESS_THAN_EQUAL.equals( operator ) || Constents.OPERATOR_LESS_THAN.equals( operator )
				|| Constents.OPERATOR_LOGICAL_AND.equals( operator ) || Constents.OPERATOR_LOGICAL_OR.equals( operator )
				|| Constents.OPERATOR_EQUALS.equals( operator ) || Constents.OPERATOR_NOT_EQUALS.equals( operator );
	}

	/**
	 * @param operator
	 * @return
	 */
	public static boolean isUnary(String operator) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param c
	 * @return
	 */
	static boolean isOpenParenthesis(char c) {
		return ( (c == '(') || (c == '[') || (c == '{') ) ? true : false;
	}

	/**
	 * @param c
	 * @return
	 */
	static boolean isClosedParenthesis(char c) {
		return ( (c == ')') || (c == ']') || (c == '}') ) ? true : false;
	}

	/**
	 * @param open
	 * @param closed
	 * @return
	 */
	static boolean isParenthesesMatch(char open, char closed) {
		if ((open == '(') && (closed == ')')) {
			return true;
		} else if ((open == '[') && (closed == ']')) {
			return true;
		} else {
			return ( (open == '{') && (closed == '}') ) ? true : false;
		}
	}

	/**
	 * @param exp
	 * @return
	 */
	public static boolean isValidParentheses(String exp) {
		Stack s = new Stack();
		int i;
		char current_char;
		Character c;
		char c1;
		boolean ret = true;

		for (i = 0; i < exp.length(); i++) {

			current_char = exp.charAt(i);

			if (FormulaUtil.isOpenParenthesis(current_char)) {
				c = new Character(current_char);
				s.push(c);
			} else if (FormulaUtil.isClosedParenthesis(current_char)) {
				if (s.isEmpty()) {
					ret = false;
					break;
				} else {
					c = (Character) s.pop();
					c1 = c.charValue();
					if (!FormulaUtil.isParenthesesMatch(c1, current_char)) {
						ret = false;
						break;
					}
				}
			}
		}
		if (!s.isEmpty()) {
			ret = false;
		}
		return ret;
	}

	public static boolean isCustomFunction(String token, Set functionSet) {
		if(functionSet.size() <= 0)
			return false;
		for (Iterator iterator = functionSet.iterator(); iterator.hasNext();) {
			Function function = (Function) iterator.next();
			if(token.startsWith(function.getFunctionName())){
				return true;
			}
		}
		return false;
	}

	
}
