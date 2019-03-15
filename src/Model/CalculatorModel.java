/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.LinkedList;
import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;


/**
 *
 * @author conrado
 */
public class CalculatorModel {
    
    public static final LinkedList<String> calculationList = new LinkedList<String>() {{
        //Lista de numeros e operações a serem realizdas
    }}; 
    
    public static final LinkedList<String> operations = new LinkedList<String>() {{
        add("+");
        add("-");
        add("x");
        add("÷");
    }};
    
    public static Boolean hasOperations(String operations) {
        if (operations == "+"){
            return true;
        }
        else if(operations == "-"){
            return true;
        }
        else if(operations == "÷"){
            return true;
        }
        else if(operations == "x"){
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
        if (operation == "+"){
            return x+y;
        }
        else if(operation == "-"){
            return x-y;
        }
        else if(operation == "÷"){
            return x/y;
        }
        else if(operation == "x"){
            return x*y;
        }
        return 666.0;
    };
    
    public static String OnClickedResult(LinkedList calculationList) {
        String 
            op = "", auxCalculator = "";
        Double  
            firstNumber = null, secondNumber = null, sum = null;
        Boolean
            fc = true, calculate = false;       
        
        calculationList = FormatCalculationList(calculationList);
        
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
    
    private static LinkedList FormatCalculationList(LinkedList calculationList) {
    	String 
        	auxCalculator = "";
	    LinkedList<Integer> IndexOperationList = new LinkedList<Integer>();
	    
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
	    
	    LinkedList<String> calculationListAux = new LinkedList<String>();
	    calculationListAux = calculationList;
	    
	    Integer currentIndex;
	    Integer lastIndex; //calculationList.size() - 1;
	    while (calculationListAux.contains("x") || calculationListAux.contains("÷")) {
	    	if( calculationListAux.contains("x")) {
	    		calculationListAux = FindMultipliers(calculationList, calculationListAux, IndexOperationList);
	    	} else if( calculationListAux.contains("÷")) {
	    		calculationListAux = FindDivisions(calculationListAux, calculationListAux, IndexOperationList);
	    	}		    
		    currentIndex = 0;
		    lastIndex = calculationListAux.size() -1;
		    
		    while (currentIndex <= lastIndex) {
		        if(hasOperations(calculationList.get(currentIndex).toString())){
		        	IndexOperationList.add(currentIndex);
		        }
		        currentIndex++;
		    }	 
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
	    	if(calculationListAux.get(Integer.parseInt((IndexOperationList.get(currentIndex).toString()))).toString() == "x"){
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
	    ArrayList<Integer> usedIndex = new ArrayList<Integer>(); 
	    
	    calculationListAux = calculationList;
		while (currentIndex <= lastIndex) { 
			if (calculationListAux.get(Integer.parseInt((IndexOperationList.get(currentIndex).toString())))
					.toString() == "÷") {
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
    /*
    private static LinkedList FindSums(LinkedList calculationList, LinkedList calculationListAux, LinkedList IndexOperationList) {   
	    Integer currentIndex = 0;
	    Integer lastIndex = IndexOperationList.size() - 1;
	    
	    while (currentIndex <= lastIndex) {
	    	if(calculationList.get(Integer.parseInt((IndexOperationList.get(currentIndex).toString()))).toString()  == "+"){
	    		if (calculationListAux.isEmpty())
	        		calculationListAux.add(JSType.toString(calculationList.get((JSType.toInteger(IndexOperationList.get(currentIndex))-1)))); // x
	        	calculationListAux.add(JSType.toString(calculationList.get(JSType.toInteger(IndexOperationList.get(currentIndex))))); // operation
	        	calculationListAux.add(JSType.toString(calculationList.get((JSType.toInteger(IndexOperationList.get(currentIndex)) + 1)))); // y
	        }
	        currentIndex++;
	    }
	    return calculationListAux;
	}
    
    private static LinkedList Findsubtractions(LinkedList calculationList, LinkedList calculationListAux, LinkedList IndexOperationList) {   
	    Integer currentIndex = 0;
	    Integer lastIndex = IndexOperationList.size() - 1;
	    
	    while (currentIndex <= lastIndex) {
	    	if(JSType.toString(calculationList.get(JSType.toInteger(IndexOperationList.get(currentIndex)))) == "-"){
	    		if (calculationListAux.isEmpty())
	        		calculationListAux.add(JSType.toString(calculationList.get((JSType.toInteger(IndexOperationList.get(currentIndex))-1)))); // x
	        	calculationListAux.add(JSType.toString(calculationList.get(JSType.toInteger(IndexOperationList.get(currentIndex))))); // operation
	        	calculationListAux.add(JSType.toString(calculationList.get((JSType.toInteger(IndexOperationList.get(currentIndex)) + 1)))); // y
	        }
	        currentIndex++;
	    }
	    return calculationListAux;
	}*/
}
