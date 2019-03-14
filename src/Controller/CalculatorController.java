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

import jdk.nashorn.internal.runtime.JSType;
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
            
            if (!CalculatorModel.operations.contains(auxiliar)){
                calculationList.add(btnClicked);
            }
            else{
                error = ("falha");
            }
        }
        else
            calculationList.add(btnClicked);
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
        
        if(btnClicked != "C"){
            displayText = history.getLast();
            history.remove(history.getLast());
        }        
        return displayText;
    } 
    
    public static String OnClickedResult(String btnClicked) {
        String displayText;
        
        historicoContas.add(Historico(calculationList));
        System.out.println(historicoContas);
        displayText = CalculatorModel.OnClickedResult(calculationList);
        return displayText;
    }
    
    public static String Historico (LinkedList calculationList) {
        Iterator pos = calculationList.iterator();
        String historico = "";
     
        Iterator<Integer> iterator = calculationList.iterator();
         
        while (iterator.hasNext()) {
             historico = historico + JSType.toString(iterator.next());
        }
        return historico;
    }
}
