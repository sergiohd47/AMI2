package Comparadores;

import javafx.util.Pair;

import java.util.Comparator;
import java.util.HashSet;

/**
 * @author Sergio Hernandez Dominguez
 */
public class ComparadorMayorMenor implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Pair<HashSet<Integer>, Integer> par1= (Pair<HashSet<Integer>, Integer>) o1;
        Pair<HashSet<Integer>, Integer> par2= (Pair<HashSet<Integer>, Integer>) o2;
        if(par1.getValue()<par2.getValue()){
            return 1;
        }else if(par1.getValue()>par2.getValue()){
            return -1;
        }else{
            return 0;
        }
    }
}
