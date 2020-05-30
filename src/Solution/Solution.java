package Solution;

import EstructuraDatos.Grafo;

import java.util.HashSet;

/**
 * @author Sergio Hernandez Dominguez
 */
public class Solution {
    private Grafo grafo;
    private HashSet<Integer> conjuntoNodoSemilla;
    private int nodosInfectadosPorSemilla;
    public Solution(Grafo grafo, HashSet<Integer> conjuntoNodoSemilla){
        this.grafo=grafo;
        this.conjuntoNodoSemilla=conjuntoNodoSemilla;
        this.nodosInfectadosPorSemilla=0;
    }

    public HashSet<Integer> procedimientoCascada(){
        boolean terminado=false;
        HashSet<Integer> conjuntoNodosActivos=new HashSet<>();
        HashSet<Integer> conjuntoNodosFuturosInfec=new HashSet<>();
        conjuntoNodosActivos.addAll(this.conjuntoNodoSemilla);
        HashSet<Integer> conjuntoNodosActivosUltimos=new HashSet<>();
        HashSet<Integer> conjuntoNodosVisitados=new HashSet<>();
        conjuntoNodosActivosUltimos.addAll(conjuntoNodosActivos);
        int iteraccion=1;
        while(!terminado){
            conjuntoNodosFuturosInfec.clear();
            for(Integer nodo: conjuntoNodosActivosUltimos){
                if(conjuntoNodosVisitados.contains(nodo)){
                    continue;
                }
                conjuntoNodosVisitados.add(nodo);
                for(Integer nodoPosInfect: this.grafo.nodosVecinos(nodo)){
                    double probabilidadSolucion=Math.random();
                    if((!conjuntoNodosActivos.contains(nodoPosInfect)&&(this.grafo.pesoArco(nodo,nodoPosInfect)>probabilidadSolucion))){
                        conjuntoNodosFuturosInfec.add(nodoPosInfect);
                    }
                }
            }
            conjuntoNodosActivosUltimos.addAll(conjuntoNodosFuturosInfec);
            conjuntoNodosActivos.addAll(conjuntoNodosActivosUltimos);
            if(iteraccion==1) {
                this.nodosInfectadosPorSemilla = conjuntoNodosActivos.size();
            }
            terminado=conjuntoNodosFuturosInfec.isEmpty();
            iteraccion++;
        }
        return conjuntoNodosActivos;
    }
    public Grafo getGrafo(){
        return this.grafo;
    }
    public HashSet<Integer> getConjuntoNodoSemilla(){
        return this.conjuntoNodoSemilla;
    }
    public int getNodosInfectadosPorSemilla(){
        return this.nodosInfectadosPorSemilla;
    }
}
