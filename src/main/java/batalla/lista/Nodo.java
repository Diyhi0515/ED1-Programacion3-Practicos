package batalla.lista;

public class Nodo <E>{
    private E contenido;
    private Nodo<E> siguiente;

    public Nodo(E o){
        contenido = o;
        siguiente = null;
    }
    public E getContenido() {
        return contenido;
    }

    public void setContenido(E contenido) {
        this.contenido = contenido;
    }

    public Nodo<E> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<E> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return contenido.toString();
    }
}
