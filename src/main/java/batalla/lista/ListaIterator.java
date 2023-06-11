package batalla.lista;

import java.util.Iterator;

public class ListaIterator<E> implements Iterator<E> {
    private Nodo<E> actual;
    public ListaIterator(Nodo<E> r){
        actual = r;
    }
    @Override
    public boolean hasNext() {
        return actual != null;
    }

    @Override
    public E next() {
        E obj = actual.getContenido();
        actual = actual.getSiguiente();
        return obj;
    }
}
