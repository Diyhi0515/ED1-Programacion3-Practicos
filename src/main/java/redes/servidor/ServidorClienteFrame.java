package redes.servidor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redes.juego.Pieza;
import redes.juego.Protocolo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorClienteFrame extends JFrame implements ActionListener{
    private static final Logger logger = LogManager.getRootLogger();
    private GridLayout g;
    private  DibujoJuego panel1 = new DibujoJuego(true);
    private  DibujoJuego panel2 = new DibujoJuego(false);
    public static boolean host = false;
    public boolean b;



    public ServidorClienteFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());

        initMenu();

        g = new GridLayout(1,1);
        setLayout(g);
        setPreferredSize(new Dimension(600,600));


        panel1.setBackground(Color.pink);

        panel2.setBackground(Color.CYAN);

        b = false;

        morir2();

        this.pack();
        this.setVisible(true);
    }

    public void initMenu(){
        JMenuBar bar = new JMenuBar();

        JMenu menu = new JMenu("Archivo");
        JMenuItem item = new JMenuItem("Esperar Conexión");
        item.addActionListener(e -> menuArchivo_EsperarConexion());
        menu.add(item);

        item = new JMenuItem("Conectar");
        item.addActionListener(e -> menuArchivo_Conectar());
        menu.add(item);

        item = new JMenuItem("Salir");
        item.addActionListener(e -> System.exit(0));
        menu.add(item);

        bar.add(menu);

        this.setJMenuBar(bar);
    }

    private void menuArchivo_Conectar() { //Conectar con el servidor
        add(panel1);
        add(panel2);


        String ipServidor = ""; //para q el usuario ingresa el ip
        String regex = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$";
        while (!ipServidor.matches(regex)) {
            ipServidor = JOptionPane.showInputDialog("Ingrese la dirección IP del servidor"); //panel para Ip
            if (ipServidor == null) {
                logger.info("El usuario hizo clic en cancel");
                return;
            }
        }

        final String ip = ipServidor;

        Thread t = new Thread(() -> conectar(ip));
        t.start();
    }

    private void menuArchivo_EsperarConexion() {
       add(panel2);
       add(panel1);

        JOptionPane.showMessageDialog(this,
                "Al cerrar esta ventana comenzara a escuchar", "Mensaje", //panel de espera para conectar
                JOptionPane.INFORMATION_MESSAGE);

        Thread t = new Thread(() -> esperarConexion()); //hilo para la coneccion
        t.start();
    }

    public void esperarConexion() {
        ServerSocket srv = null;
        Socket clt = null;

        try {
            srv = new ServerSocket(Protocolo.PUERTO);
            logger.info("El usuario abrió el puerto y esta escuchando");
            clt = srv.accept();
            host = true;
            iniciar(clt);
        } catch (IOException e) {
            logger.error("No pudo abrir el puerto", e);
        } finally {
            if (clt != null)
                try { clt.close();} catch(Exception e) {
                    logger.error("Error al cerrar el socket", e);
                }
            if (srv != null) {
                try { srv.close();}
                catch(Exception e) {
                    logger.error("Error al cerrar el server socket", e);
                }
            }
        }
    }

    public void conectar(String ipServidor) {
        try  {

            Socket clt = new Socket(ipServidor, Protocolo.PUERTO);
            logger.info("El usuario se conectó al servidor");
            iniciar(clt);
        } catch(IOException e) {
            logger.error("Error al conectar con el servidor", e);
            JOptionPane.showMessageDialog(this, "Error al conectar con el servidor", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void iniciar(Socket clt) {
        Protocolo protocolo = new Protocolo(clt);
        Pieza pz1 = Pieza.getOrCreate();
        pz1.setProtocolo(protocolo);
        if(host) {
            protocolo.start();
        }
        protocolo.procesarRecepcion();
    }

    public void morir2(){
        panel2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Pieza p1 = Pieza.getOrCreate();
                PointerInfo pi = MouseInfo.getPointerInfo();
                int x = e.getX();
                int y = e.getY();
                int [][] array2 = p1.getPosicion2();
                for (int i = 0; i < 7; i++) {
                    if((array2[0][i]>= x && array2[0][i]<=x+60) && (array2[1][i]>= y && array2[1][i]<=y+60)){
                        panel2.setB(true);
                        panel2.setX1(array2[0][i]);
                        panel2.setY1(array2[1][i]);
                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        panel1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Pieza p1 = Pieza.getOrCreate();
                PointerInfo pi = MouseInfo.getPointerInfo();
                int x = e.getX();
                int y = e.getY();
                int [][] array2 = p1.getPosicion2();
                for (int i = 0; i < 7; i++) {
                    if((array2[0][i]>= x && array2[0][i]<=x+60) && (array2[1][i]>= y && array2[1][i]<=y+60)){
                        panel1.setB(true);
                        panel1.setX1(array2[0][i]);
                        panel1.setY1(array2[1][i]);
                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });



    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
