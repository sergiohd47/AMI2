import java.util.ArrayList;

/**
 * @author Sergio Hernandez Dominguez
 */
public class PrincipalTrabajoFicheros {
    //CORRER ESTE MAIN SOLO UNA VEZ, YA QUE GENERARA UNOS NUEVOS FICHEROS CON NUEVAS PROBABILIDADES (fichero run: 29/5/2020)
    //              RUTAS SNAP
    //      RUTA SNAP DE PRUEBA
    private static final String RUTA_PRUEBAS="/Users/sergiohernandezdominguez/Desktop/universidad/TFG2/SNAP/formatoTXT/snapPrueba/snapGrafoPrueba.txt";
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
        ArrayList<String> listaRutas=new ArrayList<>();
        /*listaRutas.add(RUTA_COLLEGEMSG_1899);
        listaRutas.add(RUTA_EMAILEU_1005);
        listaRutas.add(RUTA_FACEBOOKCOMB_4039);
        listaRutas.add(RUTA_GNUTELLA5_8846);
        listaRutas.add(RUTA_GNUTELLA6_8717);
        listaRutas.add(RUTA_GNUTELLA8_6301);
        listaRutas.add(RUTA_GNUTELLA8_6301);
        listaRutas.add(RUTA_GNUTELLA9_8114);
        listaRutas.add(RUTA_GRQC_5242);
        listaRutas.add(RUTA_WIKIVOTE_7115);
        listaRutas.add(RUTA_BITCOINOTC_5881);
        listaRutas.add(RUTA_BITCOINALPHA_3783);
        listaRutas.add(RUTA_GOVERMENT_7057);
        listaRutas.add(RUTA_TVSHOW_3892);
        listaRutas.add(RUTA_POLITICIAN_5908);
        */
        listaRutas.add(RUTA_PRUEBAS);

        for(String ruta: listaRutas){
            instancia.modificarFichero(ruta);
            System.out.println("------------------");

        }

    }
}
