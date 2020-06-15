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
    private int promedioActual;
    private HashSet<Integer> conjuntoMayorPromedio;
    private HashSet<Integer> conjuntoMayorPromedioPeores;
    private ArrayList<Integer> colaTabu;
    private int mayorPromedioPeores;
    private int criterioParada;

    public TabuImprovement(int longitudTabu, int promedioActual, int criterioParada){
        this.longitudTabu=longitudTabu;
        this.promedioActual=promedioActual;

        this.mayorPromedio=Integer.MIN_VALUE;
        this.mayorPromedioPeores=Integer.MIN_VALUE;

        this.conjuntoMayorPromedio=new HashSet<>();
        this.conjuntoMayorPromedioPeores=new HashSet<>();
        this.colaTabu=new ArrayList<>();
        this.criterioParada=criterioParada;
    }
    @Override
    public void improve(Solution solution) {
        Grafo grafoND=solution.getGrafo();
        HashSet<Integer> conjuntoNodosSemillas=solution.getConjuntoNodoSemilla();
        this.conjuntoMayorPromedio=conjuntoNodosSemillas;
        this.longitudTabu=(int)Math.ceil((float)(this.longitudTabu*conjuntoNodosSemillas.size())/100); //REDONDEA HACIA ARRIBA (0.3->1)
        System.out.println("longitud cola: "+this.longitudTabu);
        boolean terminado=false;
        boolean salirMayorPromedioActual=false;
        int numero=0;
        int numeroIteraccionesNoMejora;
        while(!terminado) {
            numeroIteraccionesNoMejora=0;
            HashSet<Integer> conjuntoNodosEntrada= new HashSet<>(grafoND.nodos());
            conjuntoNodosEntrada.removeAll(this.conjuntoMayorPromedio);
            System.out.println("semilla: "+this.conjuntoMayorPromedio);
            for (Integer nodoSemilla : this.conjuntoMayorPromedio) {
                System.out.println("--------------- IMPROVEMENT GRANDE ---------------");
                for (Integer nodoEntrada : conjuntoNodosEntrada) {
                    System.out.println("------ IMPROVEMENT " + numero + " ------");
                    if (colaTabu.contains(nodoEntrada)) {
                        System.out.println("SALTA POR COLA TABU");
                        continue;
                    }
                    if (this.colaTabu.size() + 1 > this.longitudTabu) {
                        this.colaTabu.remove(0);
                        this.colaTabu.add(nodoEntrada);
                    } else {
                        this.colaTabu.add(nodoEntrada);
                    }
                    HashSet<Integer> conjuntoNuevasSemillas = new HashSet<>(this.conjuntoMayorPromedio);
                    conjuntoNuevasSemillas.remove(nodoSemilla);
                    conjuntoNuevasSemillas.add(nodoEntrada);
                    Solution nuevaSolution = new Solution(grafoND, conjuntoNuevasSemillas);
                    int promedioInfeccion = 0;
                    for (int i = 1; i <= NUMERO_SIMULACIONES_SOLUTION; i++) {
                        HashSet<Integer> conjuntoNuevosInfectados = nuevaSolution.procedimientoCascada();
                        promedioInfeccion = promedioInfeccion + conjuntoNuevosInfectados.size();
                    }
                    promedioInfeccion = promedioInfeccion / NUMERO_SIMULACIONES_SOLUTION;
                    if (promedioInfeccion > this.mayorPromedio) {
                        this.mayorPromedio = promedioInfeccion;
                        if(this.mayorPromedio>this.promedioActual){
                            System.out.println("promedio ("+this.mayorPromedio+") > promedioActual ("+this.promedioActual+")");
                            this.promedioActual=this.mayorPromedio;
                            this.conjuntoMayorPromedio = conjuntoNuevasSemillas;
                            System.out.println("nueva semilla: "+this.conjuntoMayorPromedio);
                            salirMayorPromedioActual=true;
                            break;
                        }
                    } else {
                        if (promedioInfeccion > this.mayorPromedioPeores) {
                            this.mayorPromedioPeores = promedioInfeccion;
                            this.conjuntoMayorPromedioPeores = conjuntoNuevasSemillas;
                        }else{
                            numeroIteraccionesNoMejora++;
                            System.out.println("iteracciones no mejora: "+numeroIteraccionesNoMejora);
                            if(numeroIteraccionesNoMejora==this.criterioParada){
                                terminado=true;
                                break;
                            }
                        }
                    }
                    numero++;
                }
                if(terminado||salirMayorPromedioActual){
                    break;
                }
            }
        }
    }

    @Override
    public int getMayorPromedio() {return this.mayorPromedio;}
    public int getPromedioActual() {return this.promedioActual;}
    public HashSet<Integer> getConjuntoMayorPromedio(){return this.conjuntoMayorPromedio;}
    public HashSet<Integer> getConjuntoMayorPromedioPeores(){return this.conjuntoMayorPromedioPeores;}
    public int getMayorPromedioPeores(){return this.mayorPromedioPeores; }

}
