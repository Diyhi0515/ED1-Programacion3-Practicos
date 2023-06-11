package batalla.obj;

import batalla.gui.JugadorPanel;
import batalla.lista.Lista;

import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.Iterator;

public class Jugador {
    private final Color color;
    private Lista<Pieza> piezas;
    private Lista<Integer> pos;
    private PropertyChangeSupport observed;
    private Iterator<Pieza> it;

    private int cont;


    public Jugador(Color color) {
        this.color = color;
        piezas = crearPiezas();
        observed = new PropertyChangeSupport(this);
        notificar();

        pos = new Lista<>();

        it = piezas.iterator();

        cont = 0;
    }

    public Lista<Pieza> getPiezas() {
        return piezas;
    }

    public Lista<Pieza> crearPiezas(){
        Lista<Pieza> resultado = new Lista<>();
        int piezasPuestas = 0;
        boolean completo = false;
        while (!completo) {
            int x = (int) (Math.random() * 285);
            int y = (int) (Math.random() * 385);
            int r = 5 + (int) (Math.random() * 30);

            while (!posiblePiezaOk(x, y, r, resultado)) {
                x = (int) (Math.random() * 285);
                y = (int) (Math.random() * 385);
                r = 5 + (int) (Math.random() * 30);
            }

            resultado.agregar(new Pieza(x, y, r));
            cont++;
            //pos.agregar(cont);
            piezasPuestas++;
            if (piezasPuestas == 7)
                completo = true;
        }
        return resultado;
    }
    private boolean posiblePiezaOk(int x, int y, int r, Lista<Pieza> resultado) {
        for (Pieza p: resultado) {
            if (p == null) {
                return true;
            }
            //Pieza p = resultado.iterator().next();
            if (Math.abs(p.getX() - x) < 15 &&
                    Math.abs(p.getY() - y) < 15)
            {
                return false;
            }
        }
        return true;
    }
    public void addListener(JugadorPanel jugadorPanel) {
        observed.addPropertyChangeListener(jugadorPanel);
    }

    public void dibujar(Graphics g) {
        g.setColor(this.color);
        for (Pieza p: piezas){
            if(p.getEstado() == 0){
                continue;
            }
            p.dibujar(g);
        }
    }

    public void posicionPiezaYEstado(int i, int x, int y, int estado) {
      for(Pieza p : piezas){
            if(i == piezas.tamano()){
                p.setX(x);
                p.setY(y);
                p.setEstado(estado);
            }
      }
    }
    public void notificar() {
        observed.firePropertyChange("JUEGO", true, false);
    }

    public boolean matoPieza(int x, int y) {
        for (Pieza p: piezas){
            if (p.contiene(x, y)){
                p.matar();
                return true;
            }
        }
        return false;
    }
    public String getPiezasParaRed() {
        StringBuilder resultado = new StringBuilder();
        String separador = "";
        for (Pieza p : piezas) {
            resultado.append(separador);
            resultado.append(p.getX())
                    .append(",")
                    .append(p.getY())
                    .append(",")
                    .append(p.getEstado());
            separador = ",";
        }
        return resultado.toString();
    }

}
