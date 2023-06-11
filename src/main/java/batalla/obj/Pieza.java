package batalla.obj;

import java.awt.*;

public class Pieza {
    private int x;
    private int y;
    private int tamano;
    private int estado;
    public Pieza(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.tamano = r;
        this.estado = 3;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public boolean contiene(int x, int y) {
        if (x < (this.x + this.tamano) &&
                x > (this.x) &&
                y < (this.y + this.tamano) &&
                y > (this.y)) {
            return true;
        }
        return false;
    }

    public void matar() {
        /*if(estado != 0){
            estado-=1;
        }*/
        estado-=1;
    }

    public void dibujar(Graphics g) {
        /*if (estado == 0){
            g.setColor(Color.BLACK);
        }*/

        switch (estado){
            case 1:
                g.setColor(Color.BLACK);
            case 2:
                g.setColor(Color.white);
            default:
                g.getColor();
        }
        g.fillRect(x,y,tamano,tamano);

    }
}
enum EstadoPieza { viva, muerta, naciendo, moribunda}
