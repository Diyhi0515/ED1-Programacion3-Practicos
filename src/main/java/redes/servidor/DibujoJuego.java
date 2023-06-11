package redes.servidor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redes.juego.Pieza;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DibujoJuego extends JPanel implements PropertyChangeListener {
    private static Logger logger = LogManager.getRootLogger();

    //private Pieza p;
    private int x1;
    private  int y1;
    private int[][] pos1;
    private int[][] pos2;
    private boolean b;
    private boolean b2;

    public DibujoJuego(boolean b1){
        Pieza pz = Pieza.getOrCreate();
        pos1 = new int[2][7];
        pos2 = new int[2][7];

        b = b1;

        b2 = false;
        int x1 = 0;
        int y1 = 0;
        pz.addObserver(this);
    }

    public void setB(boolean b) {
        this.b = b;
    }


    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public Dimension getPreferredSize() {
        return new Dimension(300,300);
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        logger.debug("Se dibujan los redondos");
        Pieza pz = Pieza.getOrCreate();
        if (pz != null) {
            if(b) {
                pz.dibujar(g);
            }else {
                pz.dibujar2(g);
            }
        }

        if(b2){
            g.setColor(Color.BLACK);
            g.drawRect(getX1(),getY1(),70,70);
            g.setColor(Color.GREEN);
            g.fillRect(getX1(),getY1(),70,70);
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Pieza p = Pieza.getOrCreate();
        logger.info((p == null ? "NULL" : p.toString()));
        repaint();
    }

}
