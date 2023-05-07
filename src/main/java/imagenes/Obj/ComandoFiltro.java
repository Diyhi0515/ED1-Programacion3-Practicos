package imagenes.Obj;

public abstract class ComandoFiltro {
    //todas las clases que hereden de esta ppodran modificar esta clase
    protected Imagen imagenBase;
    public abstract void ejecutar();
}
