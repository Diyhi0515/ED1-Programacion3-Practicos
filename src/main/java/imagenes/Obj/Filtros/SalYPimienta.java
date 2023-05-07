package imagenes.Obj.Filtros;

import imagenes.Obj.ComandoFiltro;
import imagenes.Obj.Imagen;


public class SalYPimienta extends ComandoFiltro {
    private int random;
    public SalYPimienta(Imagen imagen){
        this.imagenBase = imagen;
        random = 0;
    }
    @Override
    public void ejecutar() {
        int ancho = imagenBase.getAncho();
        int alto = imagenBase.getAlto();
        int[][] pixeles = imagenBase.getPixeles();
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                random = (int) (Math.random() * 100 + 1);
               if(random <= 25) {
                   if(random % 2 == 0){
                       pixeles[i][j] = 0xFFFFFF;
                   }else {
                       pixeles[i][j] = 0x0;
                   }
               }
            }
        }
        imagenBase.cambiosImg();
    }


}
