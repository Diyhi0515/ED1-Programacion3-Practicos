package batalla.lista;

import java.util.Iterator;

public class Lista<E> implements Iterable<E> {
    private Nodo<E> raiz;
    private int cant;

    public Lista() {
        raiz = null;
        cant = 0;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public void insertar(E o) {
        Nodo<E> nuevo = new Nodo<>(o);
        nuevo.setSiguiente(raiz);
        raiz = nuevo;
        cant++;
    }

    public void agregar(E o) {
        if (raiz == null) {
            insertar(o);
            return;
        }

        Nodo<E> actual = raiz;
        while(actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }

        Nodo<E> nuevo = new Nodo<>(o);
        actual.setSiguiente(nuevo);
        cant++;
    }

    public int tamano() {
        int contador = 0;
        Nodo<E> actual = raiz;
        while(actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }

        return contador;
    }


    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("(").append(tamano()).append(") : ");
        Nodo<E> actual = raiz;
        while(actual != null) {
            resultado.append(actual).append(" --> ");
            actual = actual.getSiguiente();
        }
        return resultado.toString();
    }

    public Iterator<E> iterator() {
        return new ListaIterator<>(raiz);
    }

}
