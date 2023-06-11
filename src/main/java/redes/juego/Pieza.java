package redes.juego;

import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Pieza {
    public static Pieza instance;
    private int x;
    private int y;
    private int x2;
    private int y2;
    private boolean vivo;
    private Random r;
    private int[][] posicion;
    private int[][] posicion2;
    private PropertyChangeSupport observado;


    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private Protocolo protocolo;
    public static Pieza getOrCreate() {
        if (instance == null)
            instance = new Pieza();
        return instance;
    }

    public Pieza(){
        protocolo = null;
        r = new Random();
        x = 0;
        y = 0;
        x2 = 0;
        y2 = 0;
        posicion = new int[2][7];
        posicion2 = new int[2][7];
        vivo = true;

        observado = new PropertyChangeSupport(this);

    }


    public void setPosicion(int[][] posicion) {
        this.posicion = posicion;
    }

    public void setPosicion2(int[][] posicion2) {
        this.posicion2 = posicion2;
    }

    public int getY() {
        y = (int)(Math.random()*300+1)+(int)(Math.random()*10+1);
        return y;
    }

    public int getX() {
        x = (int)(Math.random()*200+1)+(int)(Math.random()*20+1);
        return x;
    }

    public int getX2() {
        x2 = (int)(Math.random()*300+1)+(int)(Math.random()*10+1);
        return x2;
    }

    public int getY2() {
        y2 = (int)(Math.random()*300+1)+(int)(Math.random()*20+1);
        return y2;
    }

    public void posiociones(){
        for (int i = 0; i < posicion[0].length ; i++) {
            posicion[0][i]= getX();
            posicion[1][i]=getY();
        }
    }
    public void posiociones2(){
        for (int i = 0; i < posicion2[0].length ; i++) {
            posicion2[0][i]= getX2();
            posicion2[1][i]=getY2();
        }
    }

    public int[][] getPosicion() {
        return posicion;
    }

    public int[][] getPosicion2() {
        return posicion2;
    }

    public void dibujar(Graphics g){
        for (int i = 0; i < posicion[0].length; i++) {
            g.setColor(Color.BLACK);
            g.drawRect(posicion[0][i],posicion[1][i],70,70);
            g.setColor(Color.GREEN);
            g.fillRect(posicion[0][i],posicion[1][i],70,70);
            cambio();
        }
    }
    public void dibujar2(Graphics g){
        for (int j = 0; j < posicion2[0].length; j++) {
            g.setColor(Color.BLACK);
            g.drawRect(posicion2[0][j],posicion2[1][j],70,70);
            g.setColor(Color.GREEN);
            g.fillRect(posicion2[0][j],posicion2[1][j],70,70);
            cambio();
        }
    }


    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
    }

    public void addObserver(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    public void cambio(){
        observado.firePropertyChange("Pieza", false, true);
    }

}
