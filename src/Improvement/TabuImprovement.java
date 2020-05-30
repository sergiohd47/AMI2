package Improvement;

import EstructuraDatos.Grafo;
import Solution.Solution;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Queue;

/**
 * @author Sergio Hernandez Dominguez
 */
public class TabuImprovement implements Improvement {

    private static final int NUMERO_SIMULACIONES_SOLUTION=30;

    private int longitudTabu;
    private int mayorPromedio;
    private HashSet<Integer> conjuntoMayorPromedio;
    private HashSet<Integer> conjuntoMayorPromedioPeores;
    private ArrayList<Integer> colaTabu;
    private ArrayList<Integer> colaTabuPeores;
    private int mayorPromedioPeores;

    public TabuImprovement(int longitudTabu, int promedioActual){
        this.longitudTabu=longitudTabu;
        this.mayorPromedio=promedioActual;
        this.mayorPromedioPeores=Integer.MIN_VALUE;
        this.conjuntoMayorPromedio=new HashSet<>();
        this.conjuntoMayorPromedioPeores=new HashSet<>();
        this.colaTabu=new ArrayList<>();
        this.colaTabuPeores=new ArrayList<>();
    }
    @Override
    public void improve(Solution solution) {
        Grafo grafoND=solution.getGrafo();
        HashSet<Integer> conjuntoNodosSemillas=solution.getConjuntoNodoSemilla();
        this.conjuntoMayorPromedio=conjuntoNodosSemillas;
        HashSet<Integer> conjuntoNodosEntrada= new HashSet<>(grafoND.nodos());
        conjuntoNodosEntrada.removeAll(conjuntoNodosSemillas);
        for(Integer nodoSemilla: conjuntoNodosSemillas){
            for(Integer nodoEntrada: conjuntoNodosEntrada){
                if(colaTabu.contains(nodoEntrada)||colaTabuPeores.contains(nodoEntrada)){
                    continue;
                }
                HashSet<Integer> conjuntoNuevasSemillas=new HashSet<>(conjuntoNodosSemillas);
                conjuntoNuevasSemillas.remove(nodoSemilla);
                conjuntoNuevasSemillas.add(nodoEntrada);
                Solution nuevaSolution=new Solution(grafoND,conjuntoNuevasSemillas);
                int promedioInfeccion=0;
                for(int i=1;i<=NUMERO_SIMULACIONES_SOLUTION;i++) {
                    HashSet<Integer> conjuntoNuevosInfectados = nuevaSolution.procedimientoCascada();
                    promedioInfeccion=promedioInfeccion+conjuntoNuevosInfectados.size();
                }
                promedioInfeccion=promedioInfeccion/NUMERO_SIMULACIONES_SOLUTION;
                if(promedioInfeccion>this.mayorPromedio){
                    this.mayorPromedio=promedioInfeccion;
                    this.conjuntoMayorPromedio=conjuntoNuevasSemillas;
                    if(this.colaTabu.size()+1>this.longitudTabu){
                        this.colaTabu.remove(0);
                        this.colaTabu.add(nodoEntrada);
                    }else{
                        this.colaTabu.add(nodoEntrada);
                    }
                }else{
                    if(promedioInfeccion>this.mayorPromedioPeores){
                        this.mayorPromedioPeores=promedioInfeccion;
                        this.conjuntoMayorPromedioPeores=conjuntoNuevasSemillas;
                        if(this.colaTabuPeores.size()+1>this.longitudTabu){
                            this.colaTabuPeores.remove(0);
                            this.colaTabuPeores.add(nodoEntrada);
                        }else{
                            this.colaTabuPeores.add(nodoEntrada);
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getMayorPromedio() {return this.mayorPromedio;}
    public HashSet<Integer> getConjuntoMayorPromedio(){return this.conjuntoMayorPromedio;}
    public HashSet<Integer> getConjuntoMayorPromedioPeores(){return this.conjuntoMayorPromedioPeores;}
    public ArrayList<Integer> getColaTabu(){return this.colaTabu;}
    public int getMayorPromedioPeores(){return this.mayorPromedioPeores; }
    public ArrayList<Integer> getColaTabuPeores(){return this.colaTabuPeores;}
}
