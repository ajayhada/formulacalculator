package com.myformula.parser;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

import com.myformula.exceptions.FormulaParseException;
import com.myformula.parser.objects.Expression;
import com.myformula.parser.objects.Function;
import com.myformula.parser.objects.Operand;
import com.myformula.parser.objects.Operator;
import com.myformula.parser.objects.Token;
import com.myformula.parser.objects.functions.AbsFunction;
import com.myformula.parser.objects.functions.CeilFunction;
import com.myformula.parser.objects.functions.FloorFunction;
import com.myformula.parser.objects.functions.RoundDecimalFunction;
import com.myformula.parser.objects.functions.RoundFunction;
import com.myformula.parser.objects.functions.SumFunction;

/**
 * class will parse the formula string and create prefix postfix or infix stacks
 * to evaluate these formulas..
 * 
 * @author AjayHada
 * 
 */
public class FormulaParser implements Constents {

	public static final String FORMULA_DELIMITER = "+-*/()=><!&|?:^% ";
	private Formula formula;

	private Map variableMap;

	/**
	 * @param formula
	 */
	public FormulaParser(Formula formula) {
		this.formula = formula;
	}

	/**
	 * method returns a list(infix) of tokens parsed from formula string.
	 * 
	 * @param str
	 * @param delimiter
	 * @return
	 * @throws Exception
	 */
	private List getInfixTokenList(String str, String delimiter) throws FormulaParseException {
		ArrayList tokens = new ArrayList();
		StringTokenizer tokenizer = new StringTokenizer(str, delimiter, true);
		Stack stack = new Stack();
		while (tokenizer.hasMoreTokens()) {
			String tempStr = tokenizer.nextToken().trim();
			if (!stack.empty() && checkSecondToken((String) stack.peek(), tempStr)) {
				String newStr = (String) stack.pop();
				stack.push(newStr + tempStr);
			} else {
				if (tempStr.length() > 0) {
					stack.push(tempStr);
				}
			}
		}
		Stack reverseStack = new Stack();
		while (!stack.empty()) {
			reverseStack.push(stack.pop());
		}
		while (!reverseStack.empty()) {
			tokens.add(reverseStack.pop());
		}
		return tokens;
	}

	/**
	 * @param oldToken
	 * @param newToken
	 * @return
	 */
	private boolean checkSecondToken(String oldToken, String newToken) {
		if ("(".equals( newToken ) || ")".equals( newToken )) {
			return false;
		}
		if ("=".equals( newToken )
				&& ("=".equals( oldToken ) || "!".equals( oldToken ) || ">".equals( oldToken ) || "<".equals( oldToken ))) {
			return true;
		}
		if (("&".equals( newToken ) && ("&".equals( oldToken ))) || ("|".equals( newToken ) && ("|".equals( oldToken )))) {
			return true;
		}
		return false;
	}

	/**
	 * method returns a ( postfix ) List of Operator,Operand and Functions for
	 * further processing.
	 * 
	 * @param inFixTokens
	 * @return
	 * @throws Exception
	 */
	private List convertToRPN(List inFixTokens,Set functionSet) throws FormulaParseException {
		ArrayList rpnTokens = new ArrayList(inFixTokens.size());
		Iterator inFixTokenItr = inFixTokens.iterator();
		Stack operatorStack = new Stack();
//		int orderInExpression = 1;
		while (inFixTokenItr.hasNext()) {
			String token = (String) inFixTokenItr.next();
			token = token.trim();

			if (Constents.OPERATOR_PLUS.equals( token ) || Constents.OPERATOR_MINUS.equals( token )
					|| Constents.OPERATOR_MULTIPLY.equals( token ) || Constents.OPERATOR_DIVIDE.equals( token )
					|| Constents.OPERATOR_EQUALS.equals( token ) || Constents.OPERATOR_GREATER_THAN.equals( token )
					|| Constents.OPERATOR_LESS_THAN.equals( token ) || Constents.OPERATOR_NOT_EQUALS.equals( token )
					|| Constents.OPERATOR_GREATER_THAN_EQUAL.equals( token )
					|| Constents.OPERATOR_LESS_THAN_EQUAL.equals( token ) || Constents.OPERATOR_LOGICAL_AND.equals( token )
					|| Constents.OPERATOR_LOGICAL_OR.equals( token ) || Constents.OPERATOR_TERNARY.equals( token )
					|| Constents.OPERATOR_MOD.equals( token ) || Constents.OPERATOR_POWER.equals( token )
					|| Constents.OPERATOR_TERNARY.equals( token )) {

				while (!operatorStack.empty() && FormulaUtil.isHigherInPrecedence((String) operatorStack.peek(), token)) {
					Operator operator = new Operator((String) operatorStack.pop());
					// rpnTokens.add(operatorStack.pop());
					rpnTokens.add(operator);
				}
				operatorStack.push(token);
				
			} else if (Constents.OPENING_BRACE.equals( token )) {
				operatorStack.push(token);
			} else if (Constents.CLOSING_BRACE.equals( token )) {
				while (!operatorStack.empty()) {
					String op = (String) operatorStack.pop();
					if (Constents.OPENING_BRACE.equals( op )) {
						break; // right parenthesis is already popped off so
						// just break
					} else {
						Operator operator = new Operator(op);
						rpnTokens.add(operator);
					}
				}
			} else {
				if (FormulaUtil.isFunction(token) ) {
					String methodname = token.substring(0, token.indexOf("["));
					String argumentString = token.substring(token.indexOf("[") + 1, token.indexOf("]"));
					Function function = createFunctionObject(methodname, argumentString);
					rpnTokens.add(function);
				} else if (FormulaUtil.isCustomFunction(token, functionSet)) {
					String methodname = token.substring(0, token.indexOf("["));
					String argumentString = token.substring(token.indexOf("[") + 1, token.indexOf("]"));
					Function function = createCustomFunctionObject(methodname, argumentString, functionSet);
					rpnTokens.add(function);
				}else {
					if ((token.length() >= 1) && !(Constents.OPERATOR_TERNARY_COLON.equals( token ))) {
						Operand operand = createValueObject(token);
						rpnTokens.add(operand);
					}
				}
			} // end of all the tokens
			// orderInExpression++;
		}
		while (!operatorStack.empty()) {
			Operator operator = new Operator((String) operatorStack.pop());
			rpnTokens.add(operator);
		}

		return rpnTokens;
	}

	/**
	 * @param methodname
	 * @param argumentString
	 * @param functionSet
	 * @return
	 */
	private Function createCustomFunctionObject(String methodname,
			String argumentString, Set functionSet) {
		Stack s = new Stack();
		Function function = null;
		StringTokenizer tokenizer = new StringTokenizer(argumentString, ",", false);
		while (tokenizer.hasMoreTokens()) {
			String tempStr = tokenizer.nextToken().trim();
			Operand val = createValueObject(tempStr);
			s.push(val);
		}
		for (Iterator iterator = functionSet.iterator(); iterator.hasNext();) {
			Function tmpFunction = (Function) iterator.next();
			if(methodname.startsWith(function.getFunctionName())){
				try {
					function = (Function) tmpFunction.getClass().newInstance();
					function.setFunctionArgumentStack(s);
					function.setFunctionName(tmpFunction.getFunctionName());
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return function;
	}

	/**
	 * method constructs and return a new Function object based on parameters.
	 * 
	 * @param methodname
	 * @param argumentString
	 * @return
	 */
	private Function createFunctionObject(String methodname, String argumentString) {
		Stack s = new Stack();
		Function function = null;
		StringTokenizer tokenizer = new StringTokenizer(argumentString, ",", false);
		while (tokenizer.hasMoreTokens()) {
			String tempStr = tokenizer.nextToken().trim();
			Operand val = createValueObject(tempStr);
			s.push(val);
		}
		if (Constents.FUNCTION_ABS.equals( methodname )) {
			function = new AbsFunction(s);
		} else if (Constents.FUNCTION_CEIL.equals( methodname )) {
			function = new CeilFunction(s);
		} else if (Constents.FUNCTION_FLOOR.equals( methodname )) {
			function = new FloorFunction(s);
		} else if (Constents.FUNCTION_ROUND.equals( methodname )) {
			function = new RoundFunction(s);
		} else if (Constents.FUNCTION_ROUNDDECIMAL.equals( methodname )) {
			function = new RoundDecimalFunction(s);
		} else if (Constents.FUNCTION_SUM.equals( methodname )) {
			function = new SumFunction(s);
		}
		return function;
	}

	/**
	 * method constructs and return a new Value object based on parameters.
	 * 
	 * @param token
	 * @return
	 */
	private Operand createValueObject(String token) {
		Operand value = new Operand();
		// TODO: please find the proper syntax for variables so we can parse
		// them properly.
		// set default data type and value
		value.setValueType(findDataType(token));
		value.setValue(token);
		
		//override data type and value
		if (token.charAt(0) == '{' && token.charAt(2) == '#' && token.charAt(token.length() -1) == '}' ) {
			if (token.charAt(1) == 'n') {
				value.setValueType(Constents.OPERAND_VALUE_DOUBLE);
				if (variableMap.containsKey(token)) {
					value.setValue((String) variableMap.get(token));
				} else{
					value.setValue("0");
				}
			} else if (token.charAt(1) == 'd') {
				value.setValueType(Constents.OPERAND_VALUE_DATE);
				if (variableMap.containsKey(token)) {
					value.setValue((String) variableMap.get(token));
				} else{
					value.setValue("28/08/1980");
				}
			} else if (token.charAt(1) == 'b') {
				value.setValueType(Constents.OPERAND_VALUE_BOOLEAN);
				if (variableMap.containsKey(token)) {
					value.setValue((String) variableMap.get(token));
				} else{
					value.setValue("false");
				}
			} else if (token.charAt(1) == 's') {
				value.setValueType(Constents.OPERAND_VALUE_STRING);
				if (variableMap.containsKey(token)) {
					value.setValue((String) variableMap.get(token));
				} else{
					value.setValue("");
				}
			}
			
		}
		return value;
	}

	private long findDataType(String actualValue) {
		try {
			Double.valueOf(actualValue);
			return Constents.OPERAND_VALUE_DOUBLE;
		} catch (NumberFormatException e) {
			try {
				DateFormat.getInstance().parse(actualValue);
				return Constents.OPERAND_VALUE_DATE;
			} catch (Exception e1) {
				if ("true".equals( actualValue.toLowerCase() ) || "false".equals( actualValue.toLowerCase() )) {
					return Constents.OPERAND_VALUE_BOOLEAN;
				} else {
					return Constents.OPERAND_VALUE_STRING;
				}
			}
		}
	}

	/**
	 * 
	 * @param postfixStr
	 * @return
	 * @throws Exception
	 */
	public Expression makeExpressionObject(List postfixStr) throws FormulaParseException {
		Stack stack = new Stack();

		for (int i = 0; i < postfixStr.size(); i++) {

			Token element = (Token) postfixStr.get(i);
			if ((element.getTokenType() == Token.TOKEN_TYPE_OPERATOR)) {
				Token top1 = (Token) stack.pop();

				Token top2 = (Token) stack.pop();

				Operator operator = (Operator) postfixStr.get(i);
				Expression pushValexp = null;
				pushValexp = new Expression(top1, top2, operator);
		
				stack.push(pushValexp);
			} else {
				stack.push(element);
			}
		}
		if(stack.empty()){
			throw new FormulaParseException("Invalid formula: pleaes check the formula.");
		}
		
		Expression retExpression = (Expression) stack.pop();
		
		if(stack.size() > 0){
			throw new FormulaParseException("Invalid formula: pleaes check the formula.");
		}
		return retExpression;
	}

	/**
	 * method returns a parsed Expression Object.
	 * 
	 * @return
	 * @throws Exception
	 */
	public Expression parseFormula(Map variableMap, Set functionSet) throws FormulaParseException {
		this.variableMap = variableMap;
		
		Expression expression;
		try {
			List inFixTokens = getInfixTokenList(this.formula.getFormulaString(), FORMULA_DELIMITER);
			System.out.println(inFixTokens);
			List rpnTokens = convertToRPN(inFixTokens, functionSet);
			System.out.println(rpnTokens);
			expression = makeExpressionObject(rpnTokens);
			System.out.println("Expression= " + expression);
		} catch (RuntimeException e) {
			throw new FormulaParseException(e.getClass().getName() + "  Invalid formula: pleaes check the formula." );
		}
		return expression;

	}

	/**
	 * @return
	 */
	public boolean validateFormula() {

		return true;
	}
}
