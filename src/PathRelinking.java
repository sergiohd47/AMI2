import Comparadores.ComparadorMayorMenor;
import EstructuraDatos.Grafo;
import Solution.Solution;
import javafx.util.Pair;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author Sergio Hernandez Dominguez
 */
public class PathRelinking {
    private static final int NUMERO_SIMULACIONES_SOLUTION=30;
    private static final int NUMERO_SIMULACIONES_EXPERIMENTO=100;

    private ArrayList<ArrayList<Integer>> mejoresSolucionesIniciales;
    private Grafo grafoND;
    public PathRelinking(Solution solution){
        this.mejoresSolucionesIniciales=new ArrayList<>();
        this.grafoND=solution.getGrafo();
    }
    public ArrayList<Pair<HashSet<Integer>,Integer>> combinarSacarMejor(ArrayList<HashSet<Integer>> kMejoresSoluciones){
        ArrayList<Pair<HashSet<Integer>,Integer>> listaSemillasMejores=new ArrayList<>();
        ArrayList<ArrayList<Integer>> listaKMejores=new ArrayList<>();
        for(HashSet<Integer> kMejorAux: kMejoresSoluciones){
            ArrayList<Integer> auxiliar=new ArrayList<>(kMejorAux);
            listaKMejores.add(auxiliar);
        }
        this.mejoresSolucionesIniciales=listaKMejores;
        ArrayList<Pair<ArrayList<Integer>,Integer>> listaSemillasMejoresCamino=new ArrayList<>();
        for(ArrayList<Integer> semilla1: this.mejoresSolucionesIniciales){
            ArrayList<ArrayList<Integer>> semillasGuia=new ArrayList<>(this.mejoresSolucionesIniciales);
            semillasGuia.remove(semilla1);
            for(ArrayList<Integer> semillaGuia: semillasGuia){
                ArrayList<Integer> semilla1Copia=new ArrayList<>(semilla1);
                for(int i=0;i<semillaGuia.size();i++){
                    if(semilla1.get(i)!=semillaGuia.get(i)){
                        if(i==semillasGuia.size()){
                            continue;
                        }
                        semilla1Copia.remove(i);
                        semilla1Copia.add(i,semillaGuia.get(i));
                        listaSemillasMejoresCamino.add(new Pair<>(semilla1Copia,this.simularInfeccion(semilla1Copia)));
                    }
                    semilla1=semilla1Copia;
                }
                Pair<HashSet<Integer>,Integer> semillaMaximaCamino=this.maximoLista(listaSemillasMejoresCamino);
                listaSemillasMejoresCamino.clear();
                listaSemillasMejores.add(semillaMaximaCamino);
            }

        }
        return listaSemillasMejores;
    }



    private Pair<HashSet<Integer>, Integer> maximoLista(ArrayList<Pair<ArrayList<Integer>, Integer>> listaSemillasMejoresCamino) {
        Pair<ArrayList<Integer>,Integer> parMaximo=listaSemillasMejoresCamino.get(0);
        for(Pair<ArrayList<Integer>,Integer> parCamino: listaSemillasMejoresCamino){
            if(parCamino.getValue()>parMaximo.getValue()){
                parMaximo=parCamino;
            }
        }
        HashSet<Integer> parMa=new HashSet<>(parMaximo.getKey());
        Pair<HashSet<Integer>,Integer> parMaximo1=new Pair<>(parMa,parMaximo.getValue());
        return parMaximo1;
    }

    private int simularInfeccion(ArrayList<Integer> conjuntoNodosSemilla){
        HashSet<Integer> conjuntoNodosSemillaAux=new HashSet<>(conjuntoNodosSemilla);
        Solution solution = new Solution(this.grafoND, conjuntoNodosSemillaAux);
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
