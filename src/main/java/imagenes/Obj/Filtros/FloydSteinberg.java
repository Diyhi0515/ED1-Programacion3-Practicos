package imagenes.Obj.Filtros;

import imagenes.Obj.ComandoFiltro;
import imagenes.Obj.Imagen;

import java.awt.*;

public class FloydSteinberg extends ComandoFiltro {
    private int random;
    private int ancho;
    private int alto;
    private int[][] pixeles;
    public FloydSteinberg(Imagen imagen){
        this.imagenBase = imagen;
        random = 0;
        ancho = imagenBase.getAncho();
        alto = imagenBase.getAlto();
        pixeles = imagenBase.getPixeles();
    }

    @Override
    public void ejecutar() {
        /*for (int i = 0; i < ancho-1; i++) {
            for (int j = 0; j < alto-1; j++) {

                int antPixel = pixeles[i][j];
                int nuevPixel =  azul(pixeles[i][j])|verde(pixeles[i][j])|rojo(pixeles[i][j]);
                pixeles[i][j] = nuevPixel;

                int errorQuantificacion = antPixel-nuevPixel;

                pixeles[i + 1][j]= pixeles[i + 1][j] + (7/16 *errorQuantificacion);
                if(i>0) pixeles[i - 1][j + 1] = pixeles[i - 1][j + 1] + (3 / 16 * errorQuantificacion);

                if(j<alto) pixeles[i][j + 1] = pixeles[i][j + 1] + (5 / 16 * errorQuantificacion);

                if(i<ancho && j<alto) pixeles[i + 1][j + 1]= pixeles[i + 1][j + 1] + (1/16 *errorQuantificacion);

            }

        }*/
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                random = (int) (Math.random() * 100 + 1);
                if (random <= 50) {
                    if (random % 4 == 0) {
                        pixeles[i][j] = azul(pixeles[i][j]);
                    }
                    if (random % 3 == 0) {
                        pixeles[i][j] = rojo(pixeles[i][j]);
                    } else if (random % 2 == 0) pixeles[i][j] = verde(pixeles[i][j]);
                }
            }
        }
        imagenBase.cambiosImg();
    }
    public int rojo(int pixel){
        int prom = 0;
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                int r = (pixeles[i][j]) >> 16 & 0x000000FF;
                int g = (pixeles[i][j] >> 8) & 0x000000FF;
                int b = pixeles[i][j] & 0x000000FF;
                prom = (r+g+b)/3;
                //pixeles[i][j] = prom << 16; //(255,0,0)
            }
        }
        return prom << 16 ;
    }
    public int azul(int pixel){
        int prom = 0;
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                int r = (pixeles[i][j]) >> 16 & 0x000000FF;
                int g = (pixeles[i][j] >> 8) & 0x000000FF;
                int b = pixeles[i][j] & 0x000000FF;
                prom = (r+g+b)/3;
                //pixeles[i][j] = prom << 16; //(255,0,0)
            }
        }
        return prom;
        //return pixel & 0x000000FF;
    }
    public int verde(int pixel){
        int prom = 0;
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                int r = (pixeles[i][j]) >> 16 & 0x000000FF;
                int g = (pixeles[i][j] >> 8) & 0x000000FF;
                int b = pixeles[i][j] & 0x000000FF;
                prom = (r+g+b)/3;
                //pixeles[i][j] = prom << 16; //(255,0,0)
            }
        }
        return prom << 8;
        //return (pixel >> 8) & 0x000000FF;
    }


}
