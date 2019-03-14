/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.LinkedList;
import java.util.Iterator;
import jdk.nashorn.internal.runtime.JSType;
import java.util.ListIterator;
import sun.invoke.util.VerifyType;

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
            fc = true;       
        
        Integer currentIndex = calculationList.indexOf(calculationList.getFirst());
        Integer lastIndex = calculationList.indexOf(calculationList.getLast());
        auxCalculator = JSType.toString(calculationList.get(currentIndex));
       
        while ((currentIndex-1) < lastIndex) {
            
            if(hasOperations(auxCalculator)){
                op = auxCalculator;
               currentIndex++; 
            }
            else{
                if (firstNumber == null ){                         
                    firstNumber = Double.parseDouble(auxCalculator);
                    fc = false;
                }
                else{
                    secondNumber = Double.parseDouble(auxCalculator);
                    fc = true;
                }
                currentIndex++;

            }
            if((currentIndex-1) < lastIndex)
                auxCalculator = JSType.toString(calculationList.get(currentIndex));
            if (fc){
               sum = Calculate(op,firstNumber,secondNumber);
               fc = false;
            }  
        } 
        calculationList.clear();
    
        return FormatResult(sum);
        
    }
}
