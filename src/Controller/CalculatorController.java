/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CalculatorModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
/**
 *
 * @author conrado
 */
public class CalculatorController {
    String
        expression;
    boolean
        onCompletedOperations = false;
    public static ArrayList<String> historicoContas = new ArrayList<String>();
    
    public static void OnClickedNumber(String btnClicked) { 
        String auxiliar;
        if (!calculationList.isEmpty()){
            auxiliar = calculationList.getLast();
            
            if (CalculatorModel.operations.contains(auxiliar)){
                calculationList.add(btnClicked);
            }
            else{
                Integer index = calculationList.indexOf(calculationList.getLast());
                calculationList.set(index, auxiliar + btnClicked);
            }       
        }
        else
            calculationList.add(btnClicked);
    }
    
    public static void OnClickedOperation(String btnClicked) { 
        String auxiliar, error;
        if (!calculationList.isEmpty()){
            auxiliar = calculationList.getLast();
            
            if ((!CalculatorModel.operations.contains(auxiliar)) || (auxiliar.equals(")"))){
                calculationList.add(btnClicked);
            }
            else{
                error = ("falha");
            }
        }
        else
            calculationList.add(btnClicked);
    }
    
    public static Boolean OnClickedParenthesis(String btnClicked) { 
        String auxiliar, error;
        if (!calculationList.isEmpty()){
            if (btnClicked.equals("(")) {
                auxiliar = calculationList.getLast();

                if (!CalculatorModel.operations.contains(auxiliar) || auxiliar.equals(")")){
                    calculationList.add("x");
                    calculationList.add(btnClicked);
                    return true;
                }
                else{
                    calculationList.add(btnClicked);
                    return true;
                }
            } else {
                if (calculationList.contains("(")) {
                    calculationList.add(btnClicked);                                         
                    return true;
                }
            }
        }
        else if (!btnClicked.equals(")")) {
            calculationList.add(btnClicked);
            return true;
        }
        return false;
    }
    
    private static String TratarResultado(String resultado) {                                       
        String auxiliar;
        auxiliar = resultado;
        
        if (resultado.contains("+")){
            return auxiliar; 
        }
        else{
            return auxiliar;   
        }
    }
    
    public static final LinkedList<String> calculationList = new LinkedList<String>() {{
        //Lista de numeros e operações a serem realizdas
    }};
    
    public static final LinkedList<String> history = new LinkedList<String>() {{
        add("1");
        add("2");
        add("3");
        add("4");
    }};
    
    public static String OnClickedUndo(String btnClicked) {
        String displayText;
        displayText = "";
        
        if(!btnClicked.equals("C")){
            if (!history.isEmpty()) {
                displayText = history.getLast();
                history.remove(history.getLast());
            }
        } else {
            calculationList.clear();
            history.clear();
            historicoContas.clear();
        }        
        return displayText;
    } 
    
    public static String OnClickedResult(String btnClicked) {
        String displayText;
        
        historicoContas.add(Historico(calculationList));
        displayText = CalculatorModel.OnClickedResult(calculationList);
        return displayText;
    }
    
    public static String Historico (LinkedList calculationList) {
        String historico = "";
     
        Iterator<Integer> iterator = calculationList.iterator();
         
        while (iterator.hasNext()) {
             historico = historico + iterator.next();
        }
        return historico;
    }
}
