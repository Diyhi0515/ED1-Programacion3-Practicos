package imagenes.Obj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;

public class Imagen {
    private int ancho;
    private int alto;
    private int[][] pixeles;
    private int[][] auxiPix;
    private PropertyChangeSupport observado;
    private static Logger logger = LogManager.getRootLogger();


    public Imagen(int w, int h){
        ancho = w;
        alto = h;
        pixeles = new int[w][h];
        auxiPix = new int[w][h];

        observado = new PropertyChangeSupport(this);
        logger.info("Se crea imagen vacía de " +w + "x" +h);
    }
    public void dibujar(Graphics g){ //se dibuja la imagen
        BufferedImage  rsm = new  BufferedImage (
                ancho , alto , BufferedImage.TYPE_INT_ARGB );
        Graphics2D g2d = rsm.createGraphics ();

        //Los pixeles se guardan en la gráfica dibujo
        for ( int  i = 0 ; i < ancho ; i ++) {
            for ( int  j = 0 ; j < alto ; j ++) {
                g2d.setColor ( new Color( pixeles [ i ][ j ]));
                g2d.drawLine( i , j , i , j );
            }
        }

        g.drawImage ( rsm , 0 , 0 , null );
    }

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }

    public int[][] getPixeles() {
        return pixeles;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void setPixeles(int[][] pixeles) {
        this.pixeles = pixeles;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void todoUnColor() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
               pixeles[i][j] = 0xFFFFFF;
            }
        }
        observado.firePropertyChange("Imagen", false, true);

    }

    public void bandera() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto/3; j++) {
                pixeles[i][j] = 0xFF0000; //rojo
            }
            for (int j = alto/3; j < 2*alto/3; j++) {
                pixeles[i][j] = 0xF8F32B; //amarillo
            }
            for (int j = 2*alto/3; j < alto; j++) {
                pixeles[i][j] = 0x32CD32; //verde
            }
        }
        observado.firePropertyChange("Imagen", false, true);
    }

    public void leer(File f) {
        BufferedImage bi = null; //para guardar información de la imagen
        try {
            //File f = new File("C:\\Img\\fotoPerrito.jpg"); //saca la imagen
            bi = ImageIO.read(f); //se guarda la info de la imagen
        }catch (IOException e){
            e.printStackTrace();
            logger.error(e);
        }
        ancho = bi.getWidth();
        alto = bi.getHeight();
        pixeles = new int[ancho][alto];
        auxiPix = new int[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                pixeles[i][j] = bi.getRGB(i,j); //almacena los pixeles de la imagen
                auxiPix[i][j] = bi.getRGB(i,j); //igual que el pixeles pero no se tocará
            }
        }
        observado.firePropertyChange("Imagen", false, true);
        //aviso que hay cambio en la imagen
    }



    public void addObserver (PropertyChangeListener listener){
        observado.addPropertyChangeListener(listener);
    }
    public void cambiosImg (){
        observado.firePropertyChange("Imagen", false, true);
    }
    public void reseteaImagen(){
        pixeles = new int[ancho][alto];
        logger.debug("Se borra la imagen");
        cambiosImg();
    }
    public void reseteaFiltro(){
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                pixeles[i][j] = auxiPix[i][j];
                cambiosImg();
            }
        }
        logger.debug("Se resetea el filtro");
    }
}
