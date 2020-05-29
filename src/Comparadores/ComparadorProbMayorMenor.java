package Comparadores;

import javafx.util.Pair;

import java.util.Comparator;

/**
 * @author Sergio Hernandez Dominguez
 */
public class ComparadorProbMayorMenor implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Pair<Integer, Double> par1= (Pair<Integer, Double>) o1;
        Pair<Integer, Double> par2= (Pair<Integer, Double>) o2;
        if(par1.getValue()<par2.getValue()){
            return 1;
        }else if(par1.getValue()>par2.getValue()){
            return -1;
        }else{
            return 0;
        }
    }
}
