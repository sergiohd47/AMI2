package EstructuraDatos;

/**
 * @author Sergio Hernandez Dominguez
 */
public class Triple<T,U,V> {
    private final T primerElemento;
    private final U segundoElemento;
    private final V tercerElemento;

    public Triple(T primerElemento, U segundoElemento, V tercerElemento){
        this.primerElemento=primerElemento;
        this.segundoElemento=segundoElemento;
        this.tercerElemento=tercerElemento;
    }

    public T getPrimerElemento() {
        return primerElemento;
    }

    public U getSegundoElemento() {
        return segundoElemento;
    }

    public V getTercerElemento() {
        return tercerElemento;
    }

    @Override
    public String toString() {
        return "Triple{" +
                "primerElemento=" + primerElemento +
                ", segundoElemento=" + segundoElemento +
                ", tercerElemento=" + tercerElemento +
                '}';
    }
}
