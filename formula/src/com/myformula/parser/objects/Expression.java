package com.myformula.parser.objects;

import com.myformula.evaluator.AbstractEvaluationEngine;
import com.myformula.evaluator.EvaluationEngine;
import com.myformula.exceptions.InvalidExpressionException;

/**
 * class will contain a single expression like (1+1) or sum(2,3).
 * (SUM[1,2,3] - 33), (AJAY + SINGH), (AJAY + 1980) ect...
 * 
 * @author AjayHada
 * 
 */
public class Expression extends Token {

	private String expression;
	private Token operandOne;
	private Token operandTwo;
	private Operator operator;

	/**
	 * @param operandTwo
	 * @param operandOne
	 * @param operator
	 */
	public Expression(Token operandTwo, Token operandOne, Operator operator) {
		this.operandOne = operandOne;
		this.operandTwo = operandTwo;
		this.operator = operator;
		this.expression = "(" + operandOne + operator + operandTwo + ")";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myformula.objects.Tokens#getTokenType()
	 */
	public int getTokenType() {
		return TOKEN_TYPE_EXPRESSION;
	}

	/**
	 * method provides a evaluation process of expression.
	 * @param engine
	 * @param evalDefaultFlag
	 * @return
	 */
	public Operand evaluate(AbstractEvaluationEngine engine) {
		Operand tmpResult = new Operand();
		Operand midTmp1 = new Operand();
		Operand midTmp2 = new Operand();
		boolean operationFlag1 = false;
		boolean operationFlag2 = false;
		if (this.operandOne.getTokenType() == TOKEN_TYPE_EXPRESSION) {
			Expression op1 = (Expression) this.operandOne;
			midTmp1 = op1.evaluate(engine);
		} else if (this.operandOne.getTokenType() == TOKEN_TYPE_FUNCTION) {
			
			midTmp1 = engine.executeFunction((Function) this.operandOne);
			operationFlag1 = true;
		} else {
			midTmp1 = (Operand) this.operandOne;
			operationFlag1 = true;
		}
		if (this.operandTwo.getTokenType() == TOKEN_TYPE_EXPRESSION) {
			Expression op2 = (Expression) this.operandTwo;

			midTmp2 = op2.evaluate(engine);
		} else if (this.operandTwo.getTokenType() == TOKEN_TYPE_FUNCTION) {
			midTmp2 = engine.executeFunction((Function) this.operandTwo);
			operationFlag2 = true;
		} else {
			midTmp2 = (Operand) this.operandTwo;
			operationFlag2 = true;
		}

		if (operationFlag2 && operationFlag1) {
			tmpResult = engine.executeExpression(midTmp1, midTmp2, this.operator);
			return tmpResult;
		}

		Expression tmpExp = new Expression(midTmp2, midTmp1, this.operator);
		System.out.println("----------------"+tmpExp);
		tmpResult = tmpExp.evaluate(engine);
		return tmpResult;
	}

	/**
	 * method provides a Validation process of expression.
	 * 
	 * @param engine
	 * @param evalDefaultFlag
	 * @return
	 * @throws InvalidExpressionException
	 */
	public Operand validateExpression(AbstractEvaluationEngine engine) throws InvalidExpressionException {

		Operand tmpResult = new Operand();
		Operand midTmp1 = new Operand();
		Operand midTmp2 = new Operand();

		boolean operationFlag1 = false;
		boolean operationFlag2 = false;

		if (this.operandOne.getTokenType() == TOKEN_TYPE_EXPRESSION) {
			Expression op1 = (Expression) this.operandOne;
			midTmp1 = op1.validateExpression(engine);

		} else if (this.operandOne.getTokenType() == TOKEN_TYPE_FUNCTION) {
			midTmp1 = engine.validateFunction((Function) this.operandOne);
			operationFlag1 = true;

		} else {
			midTmp1 = (Operand) this.operandOne;
			operationFlag1 = true;

		}
		if (this.operandTwo.getTokenType() == TOKEN_TYPE_EXPRESSION) {
			Expression op2 = (Expression) this.operandTwo;
			midTmp2 = op2.validateExpression(engine);

		} else if (this.operandTwo.getTokenType() == TOKEN_TYPE_FUNCTION) {
			midTmp2 = engine.validateFunction((Function) this.operandTwo);
			operationFlag2 = true;

		} else {
			midTmp2 = (Operand) this.operandTwo;
			operationFlag2 = true;
		}

		if (operationFlag2 && operationFlag1) {
			tmpResult = engine.validateExpression(midTmp1, midTmp2, this.operator);
			return tmpResult;
		}

		Expression tmpExp = new Expression(midTmp2, midTmp1, this.operator);
		tmpResult = tmpExp.validateExpression(engine);
		return tmpResult;
	}

	/**
	 * @return
	 */
	public Token getOperandOne() {
		return this.operandOne;
	}

	/**
	 * @return
	 */
	public Token getOperandTwo() {
		return this.operandTwo;
	}

	/**
	 * @return
	 */
	public Token getOperator() {
		return this.operator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.expression;
	}
}
