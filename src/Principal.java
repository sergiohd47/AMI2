import Constructive.Constructive;
import Constructive.SumaProbConstructive;
import EstructuraDatos.Grafo;
import EstructuraDatos.Triple;
import Improvement.NoTabuImprovement;
import Solution.Solution;
import Improvement.TabuImprovement;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Sergio Hernandez Dominguez
 */
public class Principal {

    private static final int PORCENTAJE_LISTA_TABU_10=10; // PORCENTAJE TABU 10% LONGITUD SEMILLAS
    private static final int PORCENTAJE_LISTA_TABU_20=20; // PORCENTAJE TABU 20% LONGITUD SEMILLAS
    private static final int PORCENTAJE_LISTA_TABU_30=30; // PORCENTAJE TABU 30% LONGITUD SEMILLAS
    private static final int PORCENTAJE_LISTA_TABU_40=40; // PORCENTAJE TABU 40% LONGITUD SEMILLAS
    private static final int PORCENTAJE_LISTA_TABU_50=50; // PORCENTAJE TABU 50% LONGITUD SEMILLAS


    private static final int NODOS_SEMILLA_3=3; // EN ESTE CASO SE ESCOGEN TRES NODOS SEMILLAS
    private static final int NODOS_SEMILLA_10=10;
    private static final int NODOS_SEMILLA_20=20;
    private static final int NODOS_SEMILLA_30=30;
    private static final int NODOS_SEMILLA_40=40;
    private static final int NODOS_SEMILLA_50=50;

    private static final int NUMERO_SIMULACIONES_SOLUTION=30;
    private static final int NUMERO_SIMULACIONES_EXPERIMENTO=100;

    private static final int CRITERIO_PARADA_10=10; //NUMERO DE BUSQUEDAS QUE REALIZA SIN MEJORA
    private static final int CRITERIO_PARADA_15=15;
    private static final int CRITERIO_PARADA_20=20;
    private static final int CRITERIO_PARADA_30=30;
    private static final int CRITERIO_PARADA_35=35;

    //              RUTAS SNAP
    //      RUTA SNAP DE PRUEBA
    private static final String RUTA_PRUEBAS="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapPrueba/snapGrafoPruebaConPeso.txt";
    //      FORMATO .TXT
    private static final String RUTA_COLLEGEMSG_PESO_1899="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapCollegeMsg/CollegeMsgConPeso.txt";
    private static final String RUTA_EMAILEU_PESO_1005="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapEmailEU/email-Eu-coreConPeso.txt";
    private static final String RUTA_FACEBOOKCOMB_PESO_4039="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapFacebComb/facebook_combinedConPeso.txt";
    private static final String RUTA_GNUTELLA5_PESO_8846="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapGNutella5/p2p-Gnutella05ConPeso.txt";
    private static final String RUTA_GNUTELLA6_PESO_8717="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapGNutella6/p2p-Gnutella06ConPeso.txt";
    private static final String RUTA_GNUTELLA8_PESO_6301="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapGNutella8/p2p-Gnutella08ConPeso.txt";
    private static final String RUTA_GNUTELLA9_PESO_8114="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapGNutella9/p2p-Gnutella09ConPeso.txt";
    private static final String RUTA_GRQC_PESO_5242="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapGRQC/CA-GrQcConPeso.txt";
    private static final String RUTA_WIKIVOTE_PESO_7115="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapWikiVote/Wiki-VoteConPeso.txt";
    //      FORMATO .CSV
    private static final String RUTA_BITCOINOTC_PESO_5881="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoCSV/snapBitcoinOTC/soc-sign-bitcoinotcConPeso.csv";
    private static final String RUTA_BITCOINALPHA_PESO_3783="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoCSV/snapBitcoinAlpha/soc-sign-bitcoinalphaConPeso.csv";
    private static final String RUTA_GOVERMENT_PESO_7057="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoCSV/snapGoverment/government_edgesConPeso.csv";
    private static final String RUTA_TVSHOW_PESO_3892="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoCSV/snapTVShow/tvshow_edgesConPeso.csv";
    private static final String RUTA_POLITICIAN_PESO_5908="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoCSV/snapPolitician/politician_edges.csv";

    public static void main(String[] args) {
        Instance instancia=new Instance();
        ArrayList<Triple<Integer,Integer,Double>> listaNodos=instancia.leerFicheroYaModificado(RUTA_FACEBOOKCOMB_PESO_4039);
        Grafo grafoND=instancia.construirGrafo(listaNodos);
        Constructive constructivoSumProb=new SumaProbConstructive(NODOS_SEMILLA_3);
        HashSet<Integer> conjuntoNodosSemilla=constructivoSumProb.construirConjuntoSemillas(grafoND);
        Solution solution=new Solution(grafoND,conjuntoNodosSemilla);
        int promedioInfeccion=0;
        for(int i=1;i<=NUMERO_SIMULACIONES_EXPERIMENTO;i++){
            System.out.println("----------------------------- SIMULACION " + i + " -----------------------------");
            int promedioInfeccionAux=0;
            for(int j=1;j<=NUMERO_SIMULACIONES_SOLUTION;j++) {
                System.out.println("------ SOLUCION " + j + " ------");
                HashSet<Integer> conjuntoInfectados = solution.procedimientoCascada();
                promedioInfeccionAux = promedioInfeccionAux + conjuntoInfectados.size();
            }
            promedioInfeccionAux=promedioInfeccionAux/NUMERO_SIMULACIONES_SOLUTION;
            promedioInfeccion=promedioInfeccion+promedioInfeccionAux;
            System.out.println("PROMEDIO INFECCION: " + promedioInfeccionAux);
            System.out.println("-----------------------------------");
        }
        int promedioActual=promedioInfeccion/NUMERO_SIMULACIONES_EXPERIMENTO;

        long inicioTiempoTabu=System.currentTimeMillis();
        TabuImprovement tabuImprovement=new TabuImprovement(PORCENTAJE_LISTA_TABU_30,promedioActual,CRITERIO_PARADA_20);
        tabuImprovement.improve(solution);
        long finalTiempoTabu=System.currentTimeMillis();
        double tiempoTabu=finalTiempoTabu-inicioTiempoTabu;
        System.out.println("--------------------------------------------------------------------------------");
        long inicioTiempoNoTabu=System.currentTimeMillis();
        //NoTabuImprovement noTabuImprovement=new NoTabuImprovement(promedioActual);
        //noTabuImprovement.improve(solution);
        long finalTiempoNoTabu=System.currentTimeMillis();
        double tiempoNoTabu=finalTiempoNoTabu-inicioTiempoNoTabu;

        System.out.println("PROMEDIO INFECCION MAXIMA: "+promedioActual);
        System.out.println("-----------------------------------");
        System.out.println("--------IMPROVEMENT CON TABU--------");
        System.out.println("PROMEDIO INFECCION MAXIMA TRAS BUSQUEDA: "+tabuImprovement.getMayorPromedio()+" CON EL CONJUNTO SEMILLAS: "+tabuImprovement.getConjuntoMayorPromedio());
        System.out.println("PROMEDIO INFECCION MEJOR TRAS BUSQUEDA: "+tabuImprovement.getMayorPromedioPeores()+" CON EL CONJUNTO SEMILLAS: "+tabuImprovement.getConjuntoMayorPromedioPeores());
        System.out.println("TIEMPO IMPROVEMENT CON TABU: "+tiempoTabu/1000);
        System.out.println("-----------------------------------");
        System.out.println("--------IMPROVEMENT SIN TABU--------");
        //System.out.println("PROMEDIO INFECCION MAXIMA TRAS BUSQUEDA: "+noTabuImprovement.getMayorPromedio()+" CON EL CONJUNTO SEMILLAS: "+noTabuImprovement.getConjuntoMayorPromedio());
        //System.out.println("PROMEDIO INFECCION MEJOR TRAS BUSQUEDA: "+noTabuImprovement.getMayorPromedioPeores()+" CON EL CONJUNTO SEMILLAS: "+noTabuImprovement.getConjuntoMayorPromedioPeores());
        System.out.println("TIEMPO IMPROVEMENT SIN TABU: "+tiempoNoTabu/1000);
    }


}
