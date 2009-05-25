package com.myformula.evaluator;

import com.myformula.exceptions.InvalidExpressionException;
import com.myformula.parser.objects.Function;
import com.myformula.parser.objects.Operand;
import com.myformula.parser.objects.Operator;

public abstract class AbstractEvaluationEngine {
	
	public abstract Operand validateExpression(Operand op1, Operand op2, Operator operator)  throws InvalidExpressionException;
	public abstract Operand executeExpression(Operand op1, Operand op2, Operator operator);
	
	public abstract Operand validateFunction(Function function) throws InvalidExpressionException;
	public abstract Operand executeFunction(Function fun);
	
}
