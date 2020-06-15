package Improvement;

import EstructuraDatos.Grafo;
import Solution.Solution;

import java.util.HashSet;

/**
 * @author Sergio Hernandez Dominguez
 */
public class NoTabuImprovement implements Improvement{

    private static final int NUMERO_SIMULACIONES_SOLUTION=30;

    private int mayorPromedio;
    private int mayorPromedioPeores;
    private HashSet<Integer> conjuntoMayorPromedio;
    private HashSet<Integer> conjuntoMayorPromedioPeores;

    public NoTabuImprovement(int promedioActual){
        this.mayorPromedio=promedioActual;
        this.mayorPromedioPeores=Integer.MIN_VALUE;
        this.conjuntoMayorPromedio=new HashSet<>();
        this.conjuntoMayorPromedioPeores=new HashSet<>();
    }
    @Override
    public void improve(Solution solution) {
        Grafo grafoND=solution.getGrafo();
        HashSet<Integer> conjuntoSemillas=solution.getConjuntoNodoSemilla();
        this.conjuntoMayorPromedio=conjuntoSemillas;
        HashSet<Integer> conjuntoNodosEntradas=new HashSet<>(grafoND.nodos());
        conjuntoNodosEntradas.removeAll(conjuntoSemillas);
        int numero=0;
        for(Integer nodoSemilla: conjuntoSemillas){
            System.out.println("--------------- IMPROVEMENT GRANDE ---------------");
            for(Integer nodoEntrada: conjuntoNodosEntradas){
                System.out.println("------ IMPROVEMENT " + numero + " ------");
                HashSet<Integer> conjuntoNuevasSemillas=new HashSet<>(conjuntoSemillas);
                conjuntoNuevasSemillas.remove(nodoSemilla);
                conjuntoNuevasSemillas.add(nodoEntrada);
                Solution nuevaSolution=new Solution(grafoND,conjuntoNuevasSemillas);
                int promedioInfeccion=0;
                for(int i=1;i<=NUMERO_SIMULACIONES_SOLUTION;i++){
                    HashSet<Integer> conjuntoInfectados=nuevaSolution.procedimientoCascada();
                    promedioInfeccion=promedioInfeccion+conjuntoInfectados.size();
                }
                promedioInfeccion=promedioInfeccion/NUMERO_SIMULACIONES_SOLUTION;
                if(promedioInfeccion>this.mayorPromedio){
                    this.mayorPromedio=promedioInfeccion;
                    this.conjuntoMayorPromedio=conjuntoNuevasSemillas;
                }else if(promedioInfeccion>this.mayorPromedioPeores){
                    this.mayorPromedioPeores=promedioInfeccion;
                    this.conjuntoMayorPromedioPeores=conjuntoSemillas;
                }
                numero++;
            }
        }


    }

    @Override
    public int getMayorPromedio() {return this.mayorPromedio;}
    public int getMayorPromedioPeores(){return this.mayorPromedioPeores;}
    public HashSet<Integer> getConjuntoMayorPromedio(){return this.conjuntoMayorPromedio;}
    public HashSet<Integer> getConjuntoMayorPromedioPeores(){return this.conjuntoMayorPromedioPeores;}
}
