package com.myformula.evaluator;

import com.myformula.exceptions.InvalidExpressionException;
import com.myformula.parser.objects.Function;
import com.myformula.parser.objects.Operand;
import com.myformula.parser.objects.Operator;
import com.myformula.parser.Constents;

/**
 * this class will provide the implementations of all possible combinations of
 * operation with operand. user can override these methods and can provide his
 * own expression evaluation and validation implementation.
 * 
 * @author AjayHada
 * 
 */
public class EvaluationEngine extends AbstractEvaluationEngine implements Constents {

	/**
	 * method will calculate the value and return that. please override this
	 * method and provide your own implementation. current implementation
	 * supports only PLUS operation on same type of operands (numbers, string).
	 * 
	 * method should return a value of certin type because by default it is
	 * assumed that you are implementing validate method also and throwing
	 * exception in that method for wrong combination types.
	 * 
	 * @param Operand
	 *            1
	 * @param Operand
	 *            2
	 * @return calculated Value
	 */
	public Operand executeExpression(Operand op1, Operand op2, Operator operator) {
		/*
		 * Please
		 */
		Operand result = new Operand();

		if (Constents.OPERATOR_PLUS.equals( operator.getOperatorType() )) {
			if (op1.getValueType() == op2.getValueType() & op2.getValueType() == Constents.OPERAND_VALUE_STRING) {
				result.setValueType(Constents.OPERAND_VALUE_STRING);
				result.setValue(op1.getValue().concat(op2.getValue()));

			} else if (op1.getValueType() == op2.getValueType() & op2.getValueType() == Constents.OPERAND_VALUE_DOUBLE) {
				result.setValueType(Constents.OPERAND_VALUE_DOUBLE);
				result.setValue(String.valueOf(Double.parseDouble(op1.getValue())
								+ Double.parseDouble(op2.getValue())));
			}

		} else if (Constents.OPERATOR_MINUS.equals( operator.getOperatorType() )) {
			result.setValueType(Constents.OPERAND_VALUE_DOUBLE);
			if (op1.getValueType() == op2.getValueType() & op2.getValueType() == Constents.OPERAND_VALUE_DOUBLE) {
				result.setValueType(Constents.OPERAND_VALUE_DOUBLE);
				result.setValue(String.valueOf(Double.parseDouble(op1.getValue())
								- Double.parseDouble(op2.getValue())));

			}
		} else if (Constents.OPERATOR_MULTIPLY.equals( operator.getOperatorType() )) {
			result.setValueType(Constents.OPERAND_VALUE_DOUBLE);
			if (op1.getValueType() == op2.getValueType() & op2.getValueType() == Constents.OPERAND_VALUE_DOUBLE) {
				result.setValueType(Constents.OPERAND_VALUE_DOUBLE);
				result.setValue(String.valueOf(Double.parseDouble(op1.getValue())
								* Double.parseDouble(op2.getValue())));

			}
		} else if (Constents.OPERATOR_DIVIDE.equals( operator.getOperatorType() )) {
			result.setValueType(Constents.OPERAND_VALUE_DOUBLE);
			if (op1.getValueType() == op2.getValueType() & op2.getValueType() == Constents.OPERAND_VALUE_DOUBLE) {
				result.setValueType(Constents.OPERAND_VALUE_DOUBLE);
				result.setValue(String.valueOf(Double.parseDouble(op1.getValue())
								/ Double.parseDouble(op2.getValue())));

			}
		} else if (Constents.OPERATOR_MOD.equals( operator.getOperatorType() )) {
			result.setValueType(Constents.OPERAND_VALUE_DOUBLE);
			// not implemented
		} else if (Constents.OPERATOR_EQUALS.equals( operator.getOperatorType() )) {
			result.setValueType(Constents.OPERAND_VALUE_BOOLEAN);
			// not implemented
		} else if (Constents.OPERATOR_NOT_EQUALS.equals( operator.getOperatorType() )) {
			result.setValueType(Constents.OPERAND_VALUE_BOOLEAN);
			// not implemented
		} else if (Constents.OPERATOR_GREATER_THAN.equals( operator.getOperatorType() )) {
			result.setValueType(Constents.OPERAND_VALUE_BOOLEAN);
			// not implemented
		} else if (Constents.OPERATOR_LESS_THAN.equals( operator.getOperatorType() )) {
			result.setValueType(Constents.OPERAND_VALUE_BOOLEAN);
			// not implemented
		} else {
			result.setValueType(Constents.OPERAND_VALUE_STRING);
			// default is String
		}
		System.out.println(result.getValue() + " = " + op1.getValue() + operator.getOperatorType() + op2.getValue());
		return result;
	}

	/**
	 * all the function classes provides there own evaluate method so we can
	 * leave this or if we want our own implementation then we can override this
	 * method also.
	 * 
	 * @param fun
	 * @return calculated Value
	 */
	public Operand executeFunction(Function fun) {
		return fun.evaluate();
	}

	/**
	 * method will validate the functions and check that required arguments was
	 * give or not.
	 * 
	 * @param function Object
	 * @return calculated Value
	 * @throws InvalidExpressionException
	 */
	public Operand validateFunction(Function function) throws InvalidExpressionException{
		Operand result = new Operand();
		result.setValueType(Constents.OPERAND_VALUE_DOUBLE);
		return result;
	}

	/**
	 * method will perform the validation check for the operation and throws an
	 * exception InvalidExpressionException if not valid.
	 * 
	 * @param operandOne
	 * @param operandTwo
	 * @param operator
	 * @return
	 * @throws InvalidExpressionException
	 */
	public Operand validateExpression(Operand op1, Operand op2, Operator operator) throws InvalidExpressionException {
		Operand result = new Operand();
		if (Constents.OPERATOR_PLUS.equals( operator.getOperatorType() )) {
			if (op1.getValueType() == op2.getValueType() & op2.getValueType() == Constents.OPERAND_VALUE_STRING) {
				result.setValueType(Constents.OPERAND_VALUE_STRING);

			} else if (op1.getValueType() == op2.getValueType() & op2.getValueType() == Constents.OPERAND_VALUE_DOUBLE) {
				result.setValueType(Constents.OPERAND_VALUE_DOUBLE);

			} else {
				throw new InvalidExpressionException();
			}
		} else if (Constents.OPERATOR_MINUS.equals( operator.getOperatorType() )) {
			if (op1.getValueType() == op2.getValueType() & op2.getValueType() == Constents.OPERAND_VALUE_DOUBLE) {
				result.setValueType(Constents.OPERAND_VALUE_DOUBLE);

			} else {
				throw new InvalidExpressionException();
			}
		} else if (Constents.OPERATOR_MULTIPLY.equals( operator.getOperatorType() )) {
			if (op1.getValueType() == op2.getValueType() & op2.getValueType() == Constents.OPERAND_VALUE_DOUBLE) {
				result.setValueType(Constents.OPERAND_VALUE_DOUBLE);

			} else {
				throw new InvalidExpressionException();
			}
		} else if (Constents.OPERATOR_DIVIDE.equals( operator.getOperatorType() )) {
			if (op1.getValueType() == op2.getValueType() & op2.getValueType() == Constents.OPERAND_VALUE_DOUBLE) {
				result.setValueType(Constents.OPERAND_VALUE_DOUBLE);

			} else {
				throw new InvalidExpressionException();
			}
		} else {
			throw new InvalidExpressionException();
		}
		return result;
	}

}
