package CONTROL;

import ESTRUCTURAS.MultiLista;
import java.io.Serializable;
import poo.Archivos;

/**
 *
 * @author alefdelgado
 */
public class DataBase implements Serializable
{
    public DataBase()
    {
        MultiLista dataBase = new MultiLista();
        
        Archivos.guarda(dataBase, "dataBase.dat");
        
    }
    public DataBase(MultiLista ml)
    {
        Archivos.guarda(ml, "dataBase.dat");
    }
}
