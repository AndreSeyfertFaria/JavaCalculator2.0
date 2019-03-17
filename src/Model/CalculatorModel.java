/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author conrado
 */
public class CalculatorModel {
    
    public static LinkedList<String> calculationList = new LinkedList<String>() {{
        //Lista de numeros e operações a serem realizdas
    }}; 
    
    public static final LinkedList<String> operations = new LinkedList<String>() {{
        add("+");
        add("-");
        add("x");
        add("÷");
        add("(");
        add(")");
    }};
    
    public static Boolean hasOperations(String operations) {
        switch (operations) {
            case "+":
                return true;
            case "-":
                return true;
            case "÷":
                return true;
            case "x":
                return true;
        }
        return false;            
    };
    
    
    public static String FormatResult(double result) {
        int auxiliar;
        auxiliar = (int)result;        
        
        if (result == auxiliar){
        return Integer.toString(auxiliar); 
        }
        else{
            return Double.toString(result);   
        }
    };
    
    public static Double Calculate(String operation, Double x, Double y) {
        if (operation.equals("+")){
            return x+y;
        }
        else if(operation.equals("-")){
            return x-y;
        }
        else if(operation.equals("÷")){
            return x/y;
        }
        else if(operation.equals("x")){
            return x*y;
        }
        return 666.0;
    };
    
    public static String OnClickedResult(LinkedList calculationList) {
        String 
            op = "", auxCalculator;
        Double  
            firstNumber = null, secondNumber = null, sum = null;
        Boolean
            fc = true, calculate = false;       
        if (calculationList.contains("(") || calculationList.contains(")")){
            if (validateParenthesis(calculationList)) {
                calculationList = FormatParenthesisCalculation(calculationList);
            } else {
                return "Error in syntax";
            }
        }
        if ((calculationList.contains("x") || calculationList.contains("÷")) && ((calculationList.contains("+") || calculationList.contains("-")))) {
            calculationList = FormatCalculationList(calculationList);
        }
        Integer currentIndex = 0;
        Integer lastIndex = calculationList.size() - 1;
        auxCalculator = calculationList.get(currentIndex).toString();
        
       
        while (currentIndex <= lastIndex) {
            
            if(hasOperations(auxCalculator)){
                op = auxCalculator;
               currentIndex++; 
            } else {
                if (firstNumber == null ){                         
                    firstNumber = Double.parseDouble(auxCalculator);
                } else {
                    secondNumber = Double.parseDouble(auxCalculator);
                    calculate = true;
                }
                currentIndex++;
            }
            if (calculate) {
            	if (fc){
	               sum = Calculate(op,firstNumber,secondNumber);
	               fc = false;
	            } else {
	            	sum = Calculate(op,sum,secondNumber);
	            }
	            calculate = false;	
            }
            
            if(currentIndex <= lastIndex)
                auxCalculator = calculationList.get(currentIndex).toString();
        } 
        calculationList.clear();
        return FormatResult(sum);  
    }
    public static LinkedList FormatParenthesisCalculation(LinkedList calculationList) {
        Integer OpenParenthesisIndex, CloseParenthesisIndex, parenthesisCount, parenthesisIndex;
        String ResultParenthesisCalc;
        LinkedList<String> calculationListAux = new LinkedList();
        while (calculationList.contains("(")){
            OpenParenthesisIndex = -1;
            CloseParenthesisIndex = -1;
            parenthesisCount = 0; 
            parenthesisIndex = 0;
            for (Object calcObj : calculationList) {
                if (calcObj.equals("(")){
                    parenthesisCount++;
                    if (OpenParenthesisIndex.equals(-1))
                        OpenParenthesisIndex = parenthesisIndex;
                } else if (calcObj.equals(")")){
                    parenthesisCount--;
                    if (!OpenParenthesisIndex.equals(-1) && parenthesisCount.equals(0)){
                        CloseParenthesisIndex = parenthesisIndex;
                        break;
                    }
                }
                parenthesisIndex++;
            }
            //subsetting
            for (int i = OpenParenthesisIndex+1; i < CloseParenthesisIndex; i++) {
                calculationListAux.add(calculationList.get(i).toString());
            }
            ResultParenthesisCalc = ParenthesisCalculation(calculationListAux);

            if (OpenParenthesisIndex > 0) {
                if(!hasOperations(calculationList.get(OpenParenthesisIndex -1).toString()) || calculationList.get(OpenParenthesisIndex -1).toString().equals(")")){
                    calculationList.add(OpenParenthesisIndex,"x");
                    OpenParenthesisIndex++;
                    CloseParenthesisIndex++;
                }
            }
            //removing parenthesis calc from my list.
            for (int i = OpenParenthesisIndex; i <= CloseParenthesisIndex; i++) {
                calculationList.remove((int) OpenParenthesisIndex);
            }
            // adding result of parenthesis calc.
            calculationList.add(OpenParenthesisIndex,ResultParenthesisCalc);
            calculationListAux.clear();
        }
        return calculationList;
    }
    
    public static String ParenthesisCalculation(LinkedList calculationList) {
        String 
            op = "", auxCalculator;
        Double  
            firstNumber = null, secondNumber = null, sum = null;
        Boolean
            fc = true, calculate = false;       
        if (calculationList.contains("(") || calculationList.contains(")")){
                calculationList = FormatParenthesisCalculation(calculationList);
        }
        if ((calculationList.contains("x") || calculationList.contains("÷")) && ((calculationList.contains("+") || calculationList.contains("-")))) {
            calculationList = FormatCalculationList(calculationList);
        }
        Integer currentIndex = 0;
        Integer lastIndex = calculationList.size() - 1;
        auxCalculator = calculationList.get(currentIndex).toString();
       
        while (currentIndex <= lastIndex) {
            if(hasOperations(auxCalculator)){
                op = auxCalculator;
               currentIndex++; 
            } else {
                if (firstNumber == null ){                         
                    firstNumber = Double.parseDouble(auxCalculator);
                } else {
                    secondNumber = Double.parseDouble(auxCalculator);
                    calculate = true;
                }
                currentIndex++;
            }
            if (calculate) {
            	if (fc){
	               sum = Calculate(op,firstNumber,secondNumber);
	               fc = false;
	            } else {
	            	sum = Calculate(op,sum,secondNumber);
	            }
	            calculate = false;	
            }
            
            if(currentIndex <= lastIndex)
                auxCalculator = calculationList.get(currentIndex).toString();
        } 
        return FormatResult(sum);
    }
    
    private static LinkedList FormatCalculationList(LinkedList calculationList) {
        String auxCalculator;
        LinkedList<Integer> IndexOperationList = new LinkedList();

        Integer currentIndex = 0;
        Integer lastIndex = calculationList.size() - 1;

        while (currentIndex <= lastIndex) {
            auxCalculator = calculationList.get(currentIndex).toString();
            if(hasOperations(auxCalculator)){
                    IndexOperationList.add(currentIndex);
            }
            currentIndex++;
        }	    
        calculationList = FormatExpression(calculationList,IndexOperationList);
        return calculationList;
    }

    private static LinkedList FormatExpression(LinkedList calculationList,LinkedList IndexOperationList) {
	    
        LinkedList<String> calculationListAux;
        calculationListAux = calculationList;
        Boolean findOperation = false;

        Integer currentIndex = 0;
        Integer currentIndexAux;
        Integer lastIndex;
        while (calculationListAux.contains("x") || calculationListAux.contains("÷")) {
            if( calculationListAux.get(currentIndex).equals("x")) {
                    calculationListAux = FindMultipliers(calculationList, calculationListAux, IndexOperationList);
                    findOperation = true;
            } else if( calculationListAux.get(currentIndex).equals("÷")) {
                    calculationListAux = FindDivisions(calculationListAux, calculationListAux, IndexOperationList);
                    findOperation = true;
            }		    
            if (findOperation) {
                currentIndexAux = 0;
                lastIndex = calculationListAux.size() -1;
                while (currentIndexAux <= lastIndex) {
                    if(hasOperations(calculationList.get(currentIndexAux).toString())){
                            IndexOperationList.add(currentIndexAux);
                    }
                    currentIndexAux++;
                }
                findOperation = false;
                currentIndex = 0;
            }
            currentIndex++;
        }
        return calculationListAux;
    }
    
    private static LinkedList FindMultipliers(LinkedList calculationList, LinkedList calculationListAux, LinkedList IndexOperationList) {   
        Integer currentIndex = 0;
        Integer lastIndex = IndexOperationList.size() - 1;
        String operation;
        Double x,y,result;
        calculationListAux = calculationList;
        while (currentIndex <= lastIndex) {
            if(calculationListAux.get(Integer.parseInt((IndexOperationList.get(currentIndex).toString()))).toString().equals("x")){
                    x = Double.parseDouble(calculationList.get((Integer.parseInt((IndexOperationList.get(currentIndex).toString()))-1)).toString()); // x
                    operation = calculationList.get(Integer.parseInt((IndexOperationList.get(currentIndex).toString()))).toString(); // operation
                    y =  Double.parseDouble(calculationList.get((Integer.parseInt((IndexOperationList.get(currentIndex).toString()))+1)).toString()); // y

                    result = Calculate(operation, x, y);
                    calculationListAux.remove(calculationListAux.get(Integer.parseInt((IndexOperationList.get(currentIndex).toString()))-1));
                    calculationListAux.remove(calculationListAux.get(Integer.parseInt((IndexOperationList.get(currentIndex).toString()))-1));
                    calculationListAux.remove(calculationListAux.get(Integer.parseInt((IndexOperationList.get(currentIndex).toString()))-1));
                    calculationListAux.add((Integer.parseInt((IndexOperationList.get(currentIndex).toString()))-1),Double.toString(result));
                    return calculationListAux;
            } 
            currentIndex++;
        }
        return calculationList;
    }
    
    private static LinkedList FindDivisions(LinkedList calculationList, LinkedList calculationListAux, LinkedList IndexOperationList) {   
        Integer currentIndex = 0;
        Integer lastIndex = IndexOperationList.size() - 1;
        String operation;
        Double x,y,result;
        ArrayList<Integer> usedIndex = new ArrayList(); 

        calculationListAux = calculationList;
        while (currentIndex <= lastIndex) { 
                if (calculationListAux.get(Integer.parseInt((IndexOperationList.get(currentIndex).toString()))).toString().equals("÷")) {
                        x = Double.parseDouble(calculationList
                                        .get((Integer.parseInt((IndexOperationList.get(currentIndex).toString())) - 1)).toString()); // x
                        operation = calculationList.get(Integer.parseInt((IndexOperationList.get(currentIndex).toString())))
                                        .toString(); // operation
                        y = Double.parseDouble(calculationList
                                        .get((Integer.parseInt((IndexOperationList.get(currentIndex).toString())) + 1)).toString()); // y

                        result = Calculate(operation, x, y);
                        calculationListAux.remove(calculationListAux
                                        .get(Integer.parseInt((IndexOperationList.get(currentIndex).toString())) - 1));
                        calculationListAux.remove(calculationListAux
                                        .get(Integer.parseInt((IndexOperationList.get(currentIndex).toString())) - 1));
                        calculationListAux.remove(calculationListAux
                                        .get(Integer.parseInt((IndexOperationList.get(currentIndex).toString())) - 1));
                        calculationListAux.add((Integer.parseInt((IndexOperationList.get(currentIndex).toString())) - 1),
                                        Double.toString(result));
                        usedIndex.add(currentIndex);
                        return calculationListAux;
                }
                currentIndex++;
        }
        for (Integer index : usedIndex) {
            IndexOperationList.remove(index);
        }
        return calculationList;
    }
    
    public static Boolean validateParenthesis(LinkedList calculationList) { 
        Integer numParenthesis = 0;
        String element;
        Integer currentIndex = 0;
	Integer lastIndex = calculationList.size() - 1;
        
        while (currentIndex <= lastIndex) {
            element = calculationList.get(currentIndex).toString();
            switch (element) {
                case "(":
                    numParenthesis++;
                    break;
                case ")":    
                    numParenthesis--;
                    break;
            }
            currentIndex++;
        }
        return numParenthesis == 0;
    }
}
