package com.myformula.parser.objects.functions;

import java.util.Stack;

import com.myformula.parser.objects.Function;
import com.myformula.parser.objects.Operand;
import com.myformula.parser.Constents;

/**
 * @author AjayHada
 * 
 */
public class FloorFunction extends Function {

	
	public Operand evaluate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param args
	 */
	public FloorFunction(Stack args) {
		setFunctionArgumentStack(args);
		functionName = Constents.FUNCTION_FLOOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myformula.objects.Function#getFunctionType()
	 */
	
	public String getFunctionType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

}
