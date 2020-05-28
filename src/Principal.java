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
    private static final String RUTA_COLLEGEMSG_1899="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapCollegeMsg/CollegeMsg.txt";
    private static final String RUTA_EMAILEU_1005="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapEmailEU/email-Eu-core.txt";
    private static final String RUTA_FACEBOOKCOMB_4039="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapFacebComb/facebook_combined.txt";
    private static final String RUTA_GNUTELLA5_8846="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapGNutella5/p2p-Gnutella05.txt";
    private static final String RUTA_GNUTELLA6_8717="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapGNutella6/p2p-Gnutella06.txt";
    private static final String RUTA_GNUTELLA8_6301="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapGNutella8/p2p-Gnutella08.txt";
    private static final String RUTA_GNUTELLA9_8114="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapGNutella9/p2p-Gnutella09.txt";
    private static final String RUTA_GRQC_5242="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapGRQC/CA-GrQc.txt";
    private static final String RUTA_WIKIVOTE_7115="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapWikiVote/Wiki-Vote.txt";
    //      FORMATO .CSV
    private static final String RUTA_BITCOINOTC_5881="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoCSV/snapBitcoinOTC/soc-sign-bitcoinotc.csv";
    private static final String RUTA_BITCOINALPHA_3783="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoCSV/snapBitcoinAlpha/soc-sign-bitcoinalpha.csv";
    private static final String RUTA_GOVERMENT_7057="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoCSV/snapGoverment/government_edges.csv";
    private static final String RUTA_TVSHOW_3892="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoCSV/snapTVShow/tvshow_edges.csv";
    private static final String RUTA_POLITICIAN_5908="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoCSV/snapPolitician/politician_edges.csv";

    public static void main(String[] args) {
        Instance instancia=new Instance();
        String ruta=instancia.modificarFichero(RUTA_TVSHOW_3892);
        ArrayList<Triple<Integer,Integer,Double>> listaNodos=instancia.leerFicheroYaModificado(ruta);
        //Grafo grafoND=instancia.construirGrafo(listaNodos);
        System.out.println("---------------");
        //System.out.println(grafoND.tamañoGrafo());
    }
}
