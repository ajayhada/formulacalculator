package com.myformula.parser.objects;

import com.myformula.parser.FormulaUtil;

/**
 * Operator token for Expression. denotes all operators supported by Formula Execution framework.
 * <h4> List of  operators </h4><br>
 * *, -, *, \/,^ , &&, ||, =, !=, ==, <=, >=, <, >, ?:(ternary)
 *  
 * @author AjayHada
 * 
 */
public class Operator extends Token {
	
	/**
	 * denotes that operator needs three operands to evaluate. 
	 */
	public static final long OPEREND_REQUIRED_THREE = 3;
	/**
	 * denotes that operator needs two operands to evaluate. 
	 */
	public static final long OPEREND_REQUIRED_TWO = 2;
	/**
	 * denotes that operator needs only one operands to evaluate. 
	 */
	public static final long OPEREND_REQUIRED_ONE = 1;

	private long precedence;

	private String operatorType;

	private long operandRequired;

	/**
	 * one argument constocter, also sets the precedence and operand required attributes 
	 * @param operator
	 */
	public Operator(String operator) {
		this.operatorType = operator;
		this.precedence = FormulaUtil.getPrecedence(operator);

		if (FormulaUtil.isTernary(operator)) {
			this.operandRequired = OPEREND_REQUIRED_THREE;
		} else if (FormulaUtil.isBinary(operator)) {
			this.operandRequired = OPEREND_REQUIRED_TWO;
		} else if (FormulaUtil.isUnary(operator)) {
			this.operandRequired = OPEREND_REQUIRED_ONE;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myformula.objects.Tokens#getTokenType()
	 */
	public int getTokenType() {
		return TOKEN_TYPE_OPERATOR;
	}

	/**
	 * return the execution precedence of operator
	 * @return
	 */
	public long getPrecedence() {
		return this.precedence;
	}

	/**
	 * sets the execution precedence of operator
	 * @param precedence
	 */
	public void setPrecedence(long precedence) {
		this.precedence = precedence;
	}

	/**
	 * return the Operator type in string like. +,-,*,\/.
	 * @return
	 */
	public String getOperatorType() {
		return this.operatorType;
	}

	/**
	 * sets the Operator type in string like. +,-,*,\/.
	 * @param operatorType
	 */
	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

	/**
	 * return the no of operands required to perform operation 
	 * @return
	 */
	public long getOperandRequired() {
		return this.operandRequired;
	}

	/**
	 * sets the no of operands required to perform operation
	 * @param operandRequired
	 */
	public void setOperandRequired(long operandRequired) {
		this.operandRequired = operandRequired;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.operatorType.toString();
	}

}
