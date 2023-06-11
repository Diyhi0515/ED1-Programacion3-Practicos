package imagenes.Obj.Gui;

import imagenes.Obj.ComandoFiltro;
import imagenes.Obj.Filtros.*;
import imagenes.Obj.Imagen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class ImagenFrame extends JFrame {
    private static Logger logger = LogManager.getRootLogger();
    Imagen modelo;
    public ImagenFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());

        modelo = new Imagen(600,400);

        ImagenPanel panel = new ImagenPanel(modelo);
        modelo.addObserver(panel);

        this.getContentPane().add(panel,BorderLayout.CENTER);

        menu();

        this.pack();
        this.setVisible(true);
    }


    private void menu() {
       JMenuBar bar = new JMenuBar();

        JMenu menu = new JMenu("Archivos");

        JMenuItem item= new JMenuItem("Abrir");
        item.addActionListener(e ->{ //para abrir el archivo
            menuArchivoItemAbrir();
        });
        menu.add(item);

        menu.addSeparator(); //Agrega un nuevo separador al final del menú.

        item = new JMenuItem("Borrar");
        item.addActionListener(e -> {
            logger.info("El usuario borra la imagen");
            modelo.reseteaImagen();
        });
        menu.add(item);

        item = new JMenuItem("Quitar Filtro");
        item.addActionListener(e -> {
            logger.info("El usuario le quitó el filtro a la imagen");
            modelo.reseteaFiltro();
        });
        menu.add(item);

        item = new JMenuItem("Salir");
        item.addActionListener(e -> {
            logger.info("El usuario salió del programa");
            System.exit(0);
        });
        menu.add(item);

        bar.add(menu);

        menu = new JMenu("Imagen");

        item = new JMenuItem("Flip Horizontal");
        item.addActionListener(e -> {
            logger.info("El usuario convirtió la imagen en un espejo Horizontal");
            ComandoFiltro cmdF = new FlipHorizontal(modelo);
            cmdF.ejecutar();
        });
        menu.add(item);

        item = new JMenuItem("Flip Vertical");
        item.addActionListener(e -> {
            logger.info("El usuario convirtió la imagen en un espejo Vertical");
            ComandoFiltro cmdF = new FlipVertical(modelo);
            cmdF.ejecutar();
        });
        menu.add(item);

        item = new JMenuItem("Gris");
        item.addActionListener(e -> {
            logger.info("El usuario convierte la imagen a escala de gris");
            ComandoFiltro cmdF = new Gris(modelo);
            cmdF.ejecutar();
        });
        menu.add(item);

        item = new JMenuItem("Rojo");
        item.addActionListener(e -> {
            logger.info("El usuario convierte la imagen a escala de rojo");
            ComandoFiltro cmdF = new Rojo(modelo);
            cmdF.ejecutar();
        });
        menu.add(item);

        item = new JMenuItem("Verde");
        item.addActionListener(e -> {
            logger.info("El usuario convierte la imagen a escala de verde");
            ComandoFiltro cmdF = new Verde(modelo);
            cmdF.ejecutar();
        });
        menu.add(item);

        item = new JMenuItem("Azul");
        item.addActionListener(e -> {
            logger.info("El usuario convierte la imagen a escala de Azul");
            ComandoFiltro cmdF = new Azul(modelo);
            cmdF.ejecutar();
        });
        menu.add(item);

        item = new JMenuItem("Floyd Steinberg");
        item.addActionListener(e -> {
            logger.info("El usuario convirtió la imagen a escala del filtro Floyd Steinberg");
            ComandoFiltro cmdF = new FloydSteinberg(modelo);
            cmdF.ejecutar();
        });
        menu.add(item);

        item = new JMenuItem("Sal y Pimienta");
        item.addActionListener(e -> {
            logger.info("El usuario convirtió la imagen a escala del filtro Sal y Pimienta");
            ComandoFiltro cmdF = new SalYPimienta(modelo);
            cmdF.ejecutar();
        });
        menu.add(item);

        bar.add(menu);

        this.setJMenuBar(bar);//Establece la barra de menú para este marco


    }
    private void menuArchivoItemAbrir(){
        logger.info("Abrimos el file choose para escoger un archivo");
        JFileChooser ch = new JFileChooser(); //Para escoger el archivo
        int resultado = ch.showOpenDialog(this); //botón aprobar de Abrir archivo
        if(resultado == JFileChooser.APPROVE_OPTION){
            logger.info(ch.getSelectedFile().getAbsolutePath());//Devuelve el archivo seleccionado-
            // Devuelve la cadena de nombre de ruta
            modelo.leer(ch.getSelectedFile());
        }
        this.pack();
    }

    public static void main(String[] args) {
        ImagenFrame obj = new ImagenFrame();
        obj.setVisible(true);
    }
}
