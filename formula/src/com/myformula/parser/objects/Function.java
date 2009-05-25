package com.myformula.parser.objects;

import java.util.Stack;

/**
 * @author AjayHada
 * 
 */
public abstract class Function extends Token {

	protected String functionName;

	private Stack functionArguments;

	/**
	 * one argument constrector 
	 */
	public Function() {
		this.functionArguments = new Stack();
	}

	/**
	 *return the function type for the concrete object
	 * @return
	 */
	public abstract String getFunctionType();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myformula.objects.Tokens#getTokenType()
	 */
	
	public int getTokenType() {
		return TOKEN_TYPE_FUNCTION;
	}

	/**
	 * returns function argument stack
	 * @return
	 */
	public Stack getFunctionArgumentStack() {
		return this.functionArguments;
	}


	/**
	 * sets function argument stack
	 * @param functionArguments
	 */
	public final void setFunctionArgumentStack(Stack functionArguments) {
		this.functionArguments = functionArguments;
	}


	/**
	 * sets the function name for there concrete objects
	 * @param functionName
	 */
	public abstract void setFunctionName(String functionName);
	
	
	/**
	 * returns the function name for there concrete objects .
	 * @return
	 */
	public String getFunctionName() {
		return this.functionName;
	}

	/**
	 * method which provide implementation to execution the function 
	 * @return
	 */
	public abstract Operand evaluate();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	
	public String toString() {
		return getFunctionName() + "(" + this.functionArguments + ")";
	}

}
