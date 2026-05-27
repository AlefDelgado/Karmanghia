package MAIN;

import CONTROL.Ciudad;
import CONTROL.DataBase;
import CONTROL.Marca;
import ESTRUCTURAS.MultiLista;
import GUI.*;
import javax.swing.UIManager;
import poo.Archivos;

/**
 *
 * @author alefdelgado
 */
public class Main
{

    public static void main(String[] args)
    {
        DataBase.persisit();
        
        
        new Window("Alef Delgado").setVisible(true);
        

    }

}
