package com.user.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.myformula.evaluator.Evalualter;
import com.myformula.evaluator.EvaluationEngine;
import com.myformula.exceptions.FormulaParseException;
import com.myformula.parser.objects.Expression;
import com.myformula.parser.objects.Operand;
import com.myformula.parser.Formula;
import com.myformula.parser.FormulaParser;

/**
 * @author AjayHada
 * 
 */
public class DefaultClass {

	/**
	 *  {n#x} double value
	 *  {b#x} boolean value
	 *  {d#x} date value
	 *  {s#x} String value
	 * TODO: create variable syntax properly
	 * @param args
	 */
	public static void main(String[] args) {
		// Formula formula = new
		// Formula("12+92-31+27-53/64*61/14+51/11+12-16/42+46/93*14+29-23*47-43/54*68/48+55/16/62/63/32+67*79");
		 Formula formula = new Formula("22.22/7.9");
		//Formula formula = new Formula("2.5-SUM[2,7.3]+[aa]");
		 //Formula formula = new Formula("{n#z}*SUM[{n#a},{n#y}]+{n#x}");
		
		Map variableMap = new HashMap();
		variableMap.put("{n#x}", "22.1");
		variableMap.put("{n#y}", "0.99");
		variableMap.put("{n#a}", "100");
		variableMap.put("{n#z}", "5");
		
		FormulaParser formulaParser = new FormulaParser(formula);

		Expression expression = null;
		try {
			expression = formulaParser.parseFormula(variableMap, new HashSet());
			Evalualter formulaEval = new Evalualter(new EvaluationEngine(), false);
			
			Operand result = null;

			if (formulaEval.validate(expression)) {
				result = formulaEval.evalulateExpression(expression);
				System.out.println("Result of expression = " + result.getValue());
			} else {
				System.out .println("Invalidation faild  "
				+ "[intermediate results may be in compatible please check the formula again ]");
			}
		} catch (FormulaParseException e) {
			System.out.println(e.getMessage());
		}

		

	}

}
