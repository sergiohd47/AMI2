import EstructuraDatos.Grafo;
import EstructuraDatos.GrafoND;
import EstructuraDatos.Triple;

import java.util.ArrayList;

/**
 * @author Sergio Hernandez Dominguez
 */
public class Principal {
    private static final int NODOS_SEMILLA_3=3; // EN ESTE CASO SE ESCOGEN TRES NODOS SEMILLAS
    private static final int NODOS_SEMILLA_10=10;
    private static final int NODOS_SEMILLA_20=20;
    private static final int NODOS_SEMILLA_30=30;
    private static final int NODOS_SEMILLA_40=40;
    private static final int NODOS_SEMILLA_50=50;

    private static final int NUMERO_SIMULACIONES_SOLUTION=30;
    private static final int NUMERO_SIMULACIONES_EXPERIMENTO=100;

    //              RUTAS SNAP
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
        //ArrayList<Triple<Integer,Integer,Double>> listaNodos=instancia.leerFicheroYaModificado();
        //Grafo grafoND=instancia.construirGrafo(listaNodos);
        System.out.println("---------------");
        //System.out.println(grafoND.tamañoGrafo());
    }
}
