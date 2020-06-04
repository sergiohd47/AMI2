package EstructuraDatos;

import java.util.*;

/**
 * @author Sergio Hernandez Dominguez
 */
public class GrafoND implements Grafo{
    //Esta implementacion de grafos se basa en una lista de adyacencia y en una matriz de adyacencia.
    private HashSet<Integer>[] listaAdyacencia;
    private double[][] matrizAdyacencia;
    private int numeroNodos;

    public GrafoND(int numeroNodos, int nodoMaximo){
        this.numeroNodos=numeroNodos;
        this.listaAdyacencia= new HashSet[nodoMaximo+1]; //INICIALIZA LA LISTA ADYACENCIA AL NUMERO NODOS +1 (desechar la posicion 0)
        System.out.println(nodoMaximo);
        System.out.println(this.listaAdyacencia.length);
        this.matrizAdyacencia= new double[this.listaAdyacencia.length][this.listaAdyacencia.length]; //INICIALIZA LA MATRIZ ADYACENCIA
        for(int i=0;i<this.listaAdyacencia.length;i++){
            this.matrizAdyacencia[0][i]= Double.MIN_VALUE;
            this.matrizAdyacencia[i][0]= Double.MIN_VALUE;
        }
    }


    @Override
    public Collection<Integer> nodos() {
        HashSet<Integer> conjuntoSolucion=new HashSet<>();
        for (HashSet<Integer> listaNodosVecinos : this.listaAdyacencia) {
            if (listaNodosVecinos != null) {
                conjuntoSolucion.addAll(listaNodosVecinos);
            }
        }
        return Collections.unmodifiableCollection(conjuntoSolucion);
    }


    @Override
    public void insertarNodo(int nodo){
        this.listaAdyacencia[nodo]=new HashSet<>();
    }

    @Override
    public void insertarArco(int nodoOrigen, int nodoDestino, double pesoArco) {
        this.listaAdyacencia[nodoOrigen].add(nodoDestino);
        this.listaAdyacencia[nodoDestino].add(nodoOrigen);
        this.matrizAdyacencia[nodoOrigen][nodoDestino]=pesoArco;
        this.matrizAdyacencia[nodoDestino][nodoOrigen]=pesoArco;
    }
    @Override
    public double pesoArco(int nodoOrigen, int nodoDestino){
        return this.matrizAdyacencia[nodoOrigen][nodoDestino];
    }

    @Override
    public HashSet<Integer> nodosVecinos(int nodo) throws RuntimeException{
        return this.listaAdyacencia[nodo];
    }
    @Override
    public int tamañoGrafo(){
        return this.numeroNodos;
    }

    @Override
    public int gradoNodo(int nodo) {
        return this.listaAdyacencia[nodo].size();
    }
    @Override
    public double sumaProbabilidades(int nodo){
        double resultado=0;
        for(Integer nodoVecino: this.nodosVecinos(nodo)){
            resultado=resultado+pesoArco(nodo,nodoVecino);
        }
        return resultado;
    }

    @Override
    public int[] distanciaANodos(int nodo) {
        ArrayList<Integer> cola=new ArrayList<>();
        cola.add(nodo);
        int[] distancias=new int[this.listaAdyacencia.length];
        Arrays.fill(distancias,-1);
        distancias[nodo]=0;
        while(!cola.isEmpty()){
            int nodoSacado=cola.remove(0);
            for(int nodoVecino: this.nodosVecinos(nodoSacado)){
                if(distancias[nodoVecino]==-1){
                    distancias[nodoVecino]=distancias[nodoSacado]+1;
                    cola.add(nodoVecino);
                }
            }
        }
        return distancias;
    }

    @Override
    public float closenessCentrality(Integer nodo) {
        int valorDistancia=0;
        int[] distancias=this.distanciaANodos(nodo);
        for(int distanciaNodo: distancias){
            if(distanciaNodo!=-1) {
                valorDistancia = valorDistancia + distanciaNodo;
            }
        }
        return (float)1/valorDistancia;
    }


    @Override
    public String toString(){
        String resultado="";
        for(Integer nodo: this.nodos()){
            for(Integer nodoVecino: this.nodosVecinos(nodo)) {
                resultado=resultado+"Nodo: " + nodo + " ---- Nodo vecino: " + nodoVecino+"\n";
            }
        }
        return resultado;
    }

}
