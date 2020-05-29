package Constructive;

import Comparadores.ComparadorProbMayorMenor;
import EstructuraDatos.Grafo;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author Sergio Hernandez Dominguez
 */
//ESCOGE VORAZMENTE LOS K ELEMENTOS QUE MAYOR SUMA DE PROBABILIDAD TIENEN
public class SumaProbConstructive implements Constructive {
    private int numeroSemillasEscoger;
    private ComparadorProbMayorMenor comparadorProbMayorMenor;
    public SumaProbConstructive(int numeroK){
        this.numeroSemillasEscoger=numeroK;
        this.comparadorProbMayorMenor=new ComparadorProbMayorMenor();
    }
    @Override
    public HashSet<Integer> construirConjuntoSemillas(Grafo grafo) {
        HashSet<Integer> conjuntoSemillasSol=new HashSet<>();
        ArrayList<Pair<Integer,Double>> listaNodosSuma=new ArrayList<>();
        for(Integer nodo: grafo.nodos()){
            listaNodosSuma.add(new Pair<>(nodo,grafo.sumaProbabilidades(nodo)));
        }
        Collections.sort(listaNodosSuma, this.comparadorProbMayorMenor);
        for(int i=0;i<this.numeroSemillasEscoger;i++){
            conjuntoSemillasSol.add(listaNodosSuma.get(i).getKey());
        }
        return conjuntoSemillasSol;
    }
}
