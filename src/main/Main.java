
package main;

import model.ProductoRepositorioArreglo;
import view.ProductoView;
import controller.ProductoController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
   //     SwingUtilities.invokeLater(() -> {
            ProductoRepositorioArreglo repo = new ProductoRepositorioArreglo();
            ProductoView view = new ProductoView();
            new ProductoController(repo, view);
            view.setVisible(true);
    //    });
    }
}
