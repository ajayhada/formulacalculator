package com.myformula.evaluator;

import com.myformula.exceptions.InvalidExpressionException;
import com.myformula.parser.objects.Expression;
import com.myformula.parser.objects.Operand;

/**
 * class is dalegateing the call for evalulating and validating  expression
 * @author AjayHada
 * 
 */
public class Evalualter {

	private AbstractEvaluationEngine engine;
	private boolean validationFlag;
	/**
	 * @param engine
	 */
	public Evalualter(AbstractEvaluationEngine engine) {
		this.engine = engine;
		this.validationFlag = true;
	}

	/**
	 * @param engine
	 */
	public Evalualter(AbstractEvaluationEngine engine, boolean validationFlag) {
		this.engine = engine;
		this.validationFlag = validationFlag;
	}
	
	/**
	 * @param expression
	 * @param evalDefaultFlag
	 * @return
	 */
	public Operand evalulateExpression(Expression expression) {
		if(validationFlag && validate(expression)){
			return expression.evaluate(this.engine);
		}
		return expression.evaluate(this.engine);
	}

	/**
	 * validate the expression, check tha expression is logically correct or not
	 * @param expression
	 * @param evalDefaultFlag
	 * @return
	 */
	public boolean validate(Expression expression) {
		try {
			expression.validateExpression(this.engine);
			return true;
		} catch (InvalidExpressionException e) {
			return false;
		}
	}

}