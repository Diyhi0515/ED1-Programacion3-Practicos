package batalla.obj;

import batalla.red.Protocolo;

import java.awt.*;

public class Batalla {
    private static Batalla intancia;
    private Jugador local;
    private Jugador remoto;
    private Protocolo protocolo;

    public static Batalla getOrCreate(){
        if (intancia == null){
            intancia = new Batalla();
        }
        return intancia;
    }
    public Batalla(){
        local = new Jugador(Color.MAGENTA);
        remoto = new Jugador(Color.CYAN);
        protocolo = null;
    }

    public Jugador getRemoto() {
        return remoto;
    }

    public Jugador getLocal() {
        return local;
    }
    public Protocolo getProtocolo() {
        return protocolo;
    }
    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
    }

    public void enviarPiezasInicial() {
        protocolo.enviarMensaje(Protocolo.PIEZAS,local.getPiezasParaRed());
    }

}
