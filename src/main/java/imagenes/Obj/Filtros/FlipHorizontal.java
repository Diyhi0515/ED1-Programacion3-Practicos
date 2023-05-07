package imagenes.Obj.Filtros;

import imagenes.Obj.ComandoFiltro;
import imagenes.Obj.Imagen;

public class FlipHorizontal extends ComandoFiltro {
    public FlipHorizontal(Imagen imagen){
        this.imagenBase = imagen;
    }

    @Override
    public void ejecutar() {
        int ancho = imagenBase.getAncho();
        int alto = imagenBase.getAlto();
        int[][] pixeles = imagenBase.getPixeles();
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto/2; j++) {
                int auxi = pixeles[i][j];
                pixeles[i][j] = pixeles[i][(alto-1)-j];
                pixeles[i][(alto-1)-j] = auxi;
            }
        }
        imagenBase.cambiosImg();
    }
}
