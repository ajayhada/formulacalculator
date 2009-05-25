package com.myformula.parser.objects.functions;

import java.util.Iterator;
import java.util.Stack;

import com.myformula.parser.objects.Function;
import com.myformula.parser.objects.Operand;
import com.myformula.parser.Constents;

/**
 * @author AjayHada
 * 
 */
public class SumFunction extends Function {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myformula.objects.Function#getFunctionType()
	 */
	
	public String getFunctionType() {
		return Constents.FUNCTION_SUM;
	}

	/**
	 * @param args
	 */
	public SumFunction(Stack args) {
		setFunctionArgumentStack(args);
		functionName = Constents.FUNCTION_SUM;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myformula.objects.Function#evaluate()
	 */
	
	public Operand evaluate() {
		double sumVal = 0;
		for (Iterator iterator = getFunctionArgumentStack().iterator(); iterator.hasNext();) {
			Operand value = (Operand) iterator.next();
			sumVal = sumVal + Double.valueOf(value.getValue()).doubleValue();
		}
		Operand result = new Operand();
		result.setValueType(Constents.OPERAND_VALUE_DOUBLE);
		result.setValue(String.valueOf(sumVal));
		return result;
	}
	
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
}
