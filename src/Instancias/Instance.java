package Instancias;

import EstructuraDatos.Grafo;
import EstructuraDatos.GrafoND;
import EstructuraDatos.Triple;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sergio Hernandez Dominguez
 */
public class Instance {
    private int numeroNodos;
    private int nodoMaximo;
    private List<Double> listaProbabilidades;
    public Instance(){
        this.numeroNodos=0;
        this.listaProbabilidades=Arrays.asList(0.1,0.01,0.001);

    }

    public ArrayList<Triple<Integer,Integer, Double>> leerFichero(String rutaFichero){
        //SE USA EL SISTEMA DE PATRONES Y DE REGEX PARA DISTINGUIR ENTRE DOS TIPOS DE FICHEROS, LOS .txt y LOS .csv
        String patronTXT=".*.txt.*";
        String patronCSV=".*.csv.*";
        Pattern patTXT=Pattern.compile(patronTXT);
        Pattern patCSV=Pattern.compile(patronCSV);
        Matcher matTXT=patTXT.matcher(rutaFichero);
        Matcher matCSV=patCSV.matcher(rutaFichero);
        HashSet<Integer> nodos=new HashSet<>();
        ArrayList<Triple<Integer,Integer,Double>> listaNodos= new ArrayList<>();
        File archivo=null;
        FileReader fr=null;
        BufferedReader br=null;
        if(matTXT.matches()) {
            try {
                archivo = new File(rutaFichero);
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);
                String linea;
                Random randomPick=new Random();
                while ((linea = br.readLine()) != null) {
                    if (linea.indexOf("#") == 0) { //Ignoran las lineas que empiezan por "#"
                        continue;
                    }
                    double pesoArco=this.listaProbabilidades.get(randomPick.nextInt(this.listaProbabilidades.size()));
                    int nodoOrigen = Integer.parseInt(linea.split("\\s+")[0]);
                    nodos.add(nodoOrigen);
                    int nodoDestino = Integer.parseInt(linea.split("\\s+")[1]);
                    nodos.add(nodoDestino);
                    listaNodos.add(new Triple<>(nodoOrigen, nodoDestino, pesoArco)); //lista de pares de nodoOrigen, nodoDestino y peso de cada arco.
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fr != null) {
                        fr.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }else if(matCSV.matches()){
            try {
                archivo = new File(rutaFichero);
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);
                String linea;
                Random randomPick=new Random();
                while ((linea = br.readLine()) != null) {
                    if (linea.indexOf("#") == 0) { //Ignoran las lineas que empiezan por "#"
                        continue;
                    }
                    //EN LA REGEX DEL FICHERO DE PRUEBA CREADO POR MI, ES NECESARIO PONER "\t".
                    //EN LA REGEX DEL FICHERO DE SNAP "snap2/email-Eu-core.txt", LA REGEX ES UN ESPACIO " ".
                    //REGEX: "\\s+" numero de espacios que sean
                    double pesoArco=this.listaProbabilidades.get(randomPick.nextInt(this.listaProbabilidades.size()));
                    int nodoOrigen = Integer.parseInt(linea.split(",")[0]);
                    nodos.add(nodoOrigen);
                    int nodoDestino = Integer.parseInt(linea.split(",")[1]);
                    nodos.add(nodoDestino);
                    listaNodos.add(new Triple<>(nodoOrigen, nodoDestino,pesoArco)); //lista de pares de nodoOrigen, nodoDestino y peso de cada arco.
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fr != null) {
                        fr.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        this.numeroNodos=nodos.size();
        this.nodoMaximo= Collections.max(nodos);
        return listaNodos;
    }
    public  Grafo construirGrafo(ArrayList<Triple<Integer, Integer, Double>> listaNodos) throws RuntimeException{
        if(listaNodos.isEmpty()){
            throw new RuntimeException("Lista nodos vacia");
        }
        Grafo grafoND=new GrafoND(this.numeroNodos,this.nodoMaximo);
        for(Triple<Integer, Integer, Double> tripleValores: listaNodos){
            if(!grafoND.nodos().contains(tripleValores.getPrimerElemento())){
                grafoND.insertarNodo(tripleValores.getPrimerElemento());
            }
            if(!grafoND.nodos().contains(tripleValores.getSegundoElemento())){
                grafoND.insertarNodo(tripleValores.getSegundoElemento());
            }
            grafoND.insertarArco(tripleValores.getPrimerElemento(), tripleValores.getSegundoElemento(), tripleValores.getTercerElemento());
        }
        return grafoND;
    }
}
