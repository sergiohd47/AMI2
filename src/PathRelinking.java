import Comparadores.ComparadorMayorMenor;
import EstructuraDatos.Grafo;
import Solution.Solution;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author Sergio Hernandez Dominguez
 */
public class PathRelinking {
    private static final int NUMERO_SIMULACIONES_SOLUTION=30;
    private static final int NUMERO_SIMULACIONES_EXPERIMENTO=100;

    private ArrayList<HashSet<Integer>> mejoresSolucionesIniciales;
    private Grafo grafoND;
    public PathRelinking(Solution solution){
        this.mejoresSolucionesIniciales=new ArrayList<>();
        this.grafoND=solution.getGrafo();
    }
    public Pair<HashSet<Integer>,Integer> combinarSacarMejor(ArrayList<HashSet<Integer>> kMejoresSoluciones){
        this.mejoresSolucionesIniciales=kMejoresSoluciones;
        ArrayList<Pair<HashSet<Integer>,Integer>> listaSemillasMejores=new ArrayList<>();
        ArrayList<HashSet<Integer>> listaCopiaKMejoresSoluciones=new ArrayList<>(kMejoresSoluciones);
        for(HashSet<Integer> semilla1: this.mejoresSolucionesIniciales){
            listaCopiaKMejoresSoluciones.remove(semilla1);
            for(HashSet<Integer> semillaGuia: listaCopiaKMejoresSoluciones){
                for(Integer nodoSemillaGuia: semillaGuia){
                    HashSet<Integer> semilla1Copia=new HashSet<>(semilla1);
                    if(!semilla1.contains(nodoSemillaGuia)){
                        for(Integer nodoSemillaInicial: semilla1){
                            if(nodoSemillaGuia!=nodoSemillaInicial){
                                semilla1Copia.remove(nodoSemillaInicial);
                                semilla1Copia.add(nodoSemillaGuia);
                                listaSemillasMejores.add(new Pair<>(semilla1Copia,simularInfeccion(semilla1Copia)));
                            }
                        }
                    }
                }
            }
        }
        Collections.sort(listaSemillasMejores,new ComparadorMayorMenor());
        return listaSemillasMejores.get(0);
    }
    private int simularInfeccion(HashSet<Integer> conjuntoNodosSemilla){
        Solution solution = new Solution(this.grafoND, conjuntoNodosSemilla);
        int promedioInfeccion = 0;
        for (int i = 1; i <= NUMERO_SIMULACIONES_EXPERIMENTO; i++) {
            int promedioInfeccionAux = 0;
            for (int j = 1; j <= NUMERO_SIMULACIONES_SOLUTION; j++) {
                HashSet<Integer> conjuntoInfectados = solution.procedimientoCascada();
                promedioInfeccionAux = promedioInfeccionAux + conjuntoInfectados.size();
            }
            promedioInfeccionAux = promedioInfeccionAux / NUMERO_SIMULACIONES_SOLUTION;
            promedioInfeccion = promedioInfeccion + promedioInfeccionAux;
        }
        int promedioActual = promedioInfeccion / NUMERO_SIMULACIONES_EXPERIMENTO;
        return promedioActual;
    }
}
