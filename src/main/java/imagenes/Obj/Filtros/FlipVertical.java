package imagenes.Obj.Filtros;

import imagenes.Obj.ComandoFiltro;
import imagenes.Obj.Imagen;

public class FlipVertical extends ComandoFiltro {
    public FlipVertical(Imagen imagen){
      this.imagenBase = imagen;
    }
    @Override
    public void ejecutar() {
        int ancho = imagenBase.getAncho();
        int[][] pixeles = imagenBase.getPixeles();
        for (int i = 0; i < ancho/2; i++) {
            int[] auxi = pixeles[i];
            pixeles[i] = pixeles[(ancho-1)-i];
            pixeles[(ancho-1)-i] = auxi;
        }
        imagenBase.cambiosImg();
    }
}
