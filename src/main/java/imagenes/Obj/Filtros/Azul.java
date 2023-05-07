package imagenes.Obj.Filtros;

import imagenes.Obj.ComandoFiltro;
import imagenes.Obj.Imagen;

public class Azul extends ComandoFiltro {
    public Azul(Imagen imagen){
        this.imagenBase = imagen;
    }

    @Override
    public void ejecutar() {
        int ancho = imagenBase.getAncho();
        int alto = imagenBase.getAlto();
        int[][] pixeles = imagenBase.getPixeles();
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                int r = (pixeles[i][j]) >> 16 & 0x000000FF;
                int g = (pixeles[i][j] >> 8) & 0x000000FF;
                int b = pixeles[i][j] & 0x000000FF;
                int prom = (r+g+b)/3;
                pixeles[i][j] = prom; //(0,0,255)
            }
        }
        imagenBase.cambiosImg();
    }
}

