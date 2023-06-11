package redes.juego;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class Protocolo {
    private static Logger logger = LogManager.getRootLogger();
    public static final int PUERTO = 7658;

    private Socket cliente;
    //private BufferedReader in;
    //private PrintWriter out;
     private ObjectOutputStream out;
    private ObjectOutputStream out2;
     private ObjectInputStream in;
    private ObjectInputStream in2;
    public Protocolo(Socket clt) {
        cliente = clt;
        try {
            /*in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            out = new PrintWriter(cliente.getOutputStream(), true);*/

            out = new ObjectOutputStream(cliente.getOutputStream());
            out2 = new ObjectOutputStream(cliente.getOutputStream());
            in = new ObjectInputStream(cliente.getInputStream());
            in2 = new ObjectInputStream(cliente.getInputStream());
        } catch (Exception e) {
            try {
                cliente.close();
            } catch (IOException ex) {
                logger.error("No pudo cerrar el socket");
            }
            logger.error("Error al crear el protocolo", e);
            return;
        }
    }

    public void procesarRecepcion() {
        try {
            logger.info("Comienza escuchando el socket");

            while (true){
                Pieza pieza = Pieza.getOrCreate();

                int[][] prueba = (int[][]) in.readObject();

//              logger.info("Recibiendo las cordenada");
                pieza.setPosicion(prueba);
                //pieza.cambio();
                Pieza pieza2=Pieza.getOrCreate();
                int [][] prueba2 =(int[][])  in.readObject();
                pieza.setPosicion2(prueba2);
                pieza.cambio();

            }
        }
        catch(Exception e) {
            logger.error("Error al procesar la recepcion, cierra todo", e);
        } finally {
            try {
                cliente.close();
            } catch (Exception e) {
                logger.error("Error al cerrar el socket", e);
            }
        }
    }

    public void start() {
        Pieza pieza= Pieza.getOrCreate();
        Pieza pieza2 = Pieza.getOrCreate();
        pieza.posiociones();
       pieza2.posiociones2();
        pieza.cambio();
       pieza2.cambio();
        try {
            out.writeObject(pieza.getPosicion());
            out.flush();
            out2.writeObject(pieza2.getPosicion2());
            out2.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
