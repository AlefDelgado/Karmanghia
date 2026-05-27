package CONTROL;

import ESTRUCTURAS.MultiLista;
import GUI.CitysCRUD;
import java.io.File;
import java.io.Serializable;
import javax.swing.DefaultComboBoxModel;
import poo.Archivos;

public class DataBase implements Serializable
{

    public static Ciudad[] ciudades = new Ciudad[0];
    public static Marca[] marcas = new Marca[0];
    public static Sucursal[] sucursales = new Sucursal[0];
    public static Servicio[] servicios = new Servicio[0];
    public static Auto[] autos = new Auto[0];
    public static MultiLista dataBase = new MultiLista();

    public static DefaultComboBoxModel<Marca> marcaComboBoxModel = new DefaultComboBoxModel<>();
    public static DefaultComboBoxModel<Ciudad> ciudadComboBoxModel = new DefaultComboBoxModel<>();
    public static DefaultComboBoxModel<Sucursal> sucursalComboBoxModel = new DefaultComboBoxModel<>();
    public static DefaultComboBoxModel<Servicio> servicioComboBoxModel = new DefaultComboBoxModel<>();
    public static DefaultComboBoxModel<Auto> autoComboBoxModel = new DefaultComboBoxModel<>();

    public DataBase()
    {

    }

    public static Marca[] insertarMarca(Marca m)
    {

        Marca[] temp = new Marca[marcas.length + 1];
        for (int i = 0; i < marcas.length; i++)
        {
            temp[i] = marcas[i];
        }
        temp[marcas.length] = m;

        marcaComboBoxModel.addElement(m);

        Nodo nueva = new Nodo(m, m.getNom());

        String[] c =
        {
            ""
        };
        dataBase.setR(dataBase.inserta(nueva, c, 0, dataBase.getR()));

        Archivos.guarda(dataBase.getR(), "DataBase.dat");
        dataBase.desp(dataBase.getR(), "");
        return temp;
    }

    public static Marca[] editarMarca(int index, Marca nuevaMarca)
    {

        String etiquetaVieja = marcas[index].getNom();

        marcas[index] = nuevaMarca;
        marcaComboBoxModel.removeElementAt(index);
        marcaComboBoxModel.insertElementAt(nuevaMarca, index);

        String[] c
                =
                {
                    ""
                };
        Nodo[] obj = dataBase.elimina(etiquetaVieja, c, 0, dataBase.getR());
        dataBase.setR(obj[1]);

        Nodo nueva = new Nodo(nuevaMarca, nuevaMarca.getNom());
        dataBase.setR(dataBase.inserta(nueva, c, 0, dataBase.getR()));

        dataBase.desp(dataBase.getR(), "");

        Archivos.guarda(dataBase.getR(), "DataBase.dat");
        return marcas;
    }

    public static Marca[] eliminarMarca(int index)
    {
        String etiquetaEliminar = marcas[index].getNom();

        Marca[] temp = new Marca[marcas.length - 1];
        for (int i = 0, j = 0; i < marcas.length; i++)
        {
            if (i != index)
            {
                temp[j] = marcas[i];
                j++;
            }
        }
        marcaComboBoxModel.removeElementAt(index);

        String c[] =
        {
            ""
        };
        Nodo[] obj = dataBase.elimina(etiquetaEliminar, c, 0, dataBase.getR());

        dataBase.desp(dataBase.getR(), "");

        Archivos.guarda(dataBase.getR(), "DataBase.dat");
        return temp;
    }

    public static Ciudad[] insertarCiudad(String marcaSeleccionada, Ciudad c)
    {
        System.out.println(marcaSeleccionada);
        Ciudad[] temp = new Ciudad[ciudades.length + 1];
        for (int i = 0; i < ciudades.length; i++)
        {
            temp[i] = ciudades[i];
        }
        temp[ciudades.length] = c;

        ciudadComboBoxModel.addElement(c);
        
        Nodo nueva = new Nodo(c, c.getNom());
        String[] cc = 
        {
            marcaSeleccionada, ""
        };
        
        dataBase.setR(dataBase.inserta(nueva, cc, 0, dataBase.getR()));
        Archivos.guarda(dataBase.getR(), "DataBase.dat");
        dataBase.desp(dataBase.getR(), "");
        
        return temp;
    }

    public static Ciudad[] editarCiudad(int index, Ciudad nuevaCiudad, String marcaSeleccionada, String ciudadViejaEtiqueta)
    {
        ciudades[index] = nuevaCiudad;
        ciudadComboBoxModel.removeElementAt(index);
        ciudadComboBoxModel.insertElementAt(nuevaCiudad, index);

        // Eliminar la ciudad vieja de la multilista
        String[] ccEliminar = 
        {
            marcaSeleccionada, ciudadViejaEtiqueta
        };
        Nodo[] obj = dataBase.elimina(ciudadViejaEtiqueta, ccEliminar, 0, dataBase.getR());
        dataBase.setR(obj[1]);

        // Insertar la ciudad nueva en la multilista bajo la misma marca
        Nodo nueva = new Nodo(nuevaCiudad, nuevaCiudad.getNom());
        String[] ccInsertar = 
        {
            marcaSeleccionada, ""
        };
        dataBase.setR(dataBase.inserta(nueva, ccInsertar, 0, dataBase.getR()));

        dataBase.desp(dataBase.getR(), "");
        Archivos.guarda(dataBase.getR(), "DataBase.dat");

        return ciudades;
    }

    public static Ciudad[] eliminarCiudad(int index, String ciudadSeleccionada, String marcaSeleccionada)
    {
        Ciudad[] temp = new Ciudad[ciudades.length - 1];
        for (int i = 0, j = 0; i < ciudades.length; i++)
        {
            if (i != index)
            {
                temp[j] = ciudades[i];
                j++;
            }
        }
        ciudadComboBoxModel.removeElementAt(index);
        
        // Eliminamos la ciudad (segundo nivel) dentro del nodo de la marca
        // El path es: [marcaSeleccionada, ciudadSeleccionada]
        String[] cc = 
        {
            marcaSeleccionada, ciudadSeleccionada
        };
        
        Nodo[] obj = dataBase.elimina(ciudadSeleccionada, cc, 0, dataBase.getR());
        dataBase.setR(obj[1]);
        dataBase.desp(dataBase.getR(), "");
        Archivos.guarda(dataBase.getR(), "DataBase.dat");
        
        return temp;
    }

    public static Sucursal[] insertarSucursales(Sucursal s)
    {
        sucursalComboBoxModel.addElement(new Sucursal("Otra", null, null));
        Sucursal[] temp = new Sucursal[sucursales.length + 1];
        for (int i = 0; i < sucursales.length; i++)
        {
            temp[i] = sucursales[i];
        }
        temp[sucursales.length] = s;

        sucursalComboBoxModel.addElement(s);
        return temp;
    }

    public static Sucursal[] editarSucursal(int index, Sucursal nuevaSucursal)
    {
        sucursales[index] = nuevaSucursal;

        sucursalComboBoxModel.removeElementAt(index);
        sucursalComboBoxModel.insertElementAt(nuevaSucursal, index);
        return sucursales;
    }

    public static Sucursal[] eliminarSucursal(int index)
    {
        Sucursal[] temp = new Sucursal[sucursales.length - 1];
        for (int i = 0, j = 0; i < sucursales.length; i++)
        {
            if (i != index)
            {
                temp[j] = sucursales[i];
                j++;
            }
        }

        sucursalComboBoxModel.removeElementAt(index);
        return temp;
    }

    public static Servicio[] insertarServicios(Servicio se)
    {

        Servicio[] temp = new Servicio[servicios.length + 1];
        for (int i = 0; i < servicios.length; i++)
        {
            temp[i] = servicios[i];
        }
        temp[servicios.length] = se;

        servicioComboBoxModel.addElement(se);
        return temp;
    }

    public static Servicio[] editarServicio(int index, Servicio nuevoServicio)
    {
        servicios[index] = nuevoServicio;
        servicioComboBoxModel.removeElementAt(index);
        servicioComboBoxModel.insertElementAt(nuevoServicio, index);
        return servicios;
    }

    public static Servicio[] eliminarServicio(int index)
    {
        Servicio[] temp = new Servicio[servicios.length - 1];
        for (int i = 0, j = 0; i < servicios.length; i++)
        {
            if (i != index)
            {
                temp[j] = servicios[i];
                j++;
            }
        }
        servicioComboBoxModel.removeElementAt(index);
        return temp;
    }

    public static Auto[] insertarAutos(Auto a)
    {

        Auto[] temp = new Auto[autos.length + 1];
        for (int i = 0; i < autos.length; i++)
        {
            temp[i] = autos[i];
        }
        temp[autos.length] = a;
        autoComboBoxModel.addElement(a);
        return temp;
    }

    public static Auto[] editarAuto(int index, Auto nuevoAuto)
    {
        autos[index] = nuevoAuto;
        autoComboBoxModel.removeElementAt(index);
        autoComboBoxModel.insertElementAt(nuevoAuto, index);
        return autos;
    }

    public static Auto[] eliminarAuto(int index)
    {
        Auto[] temp = new Auto[autos.length - 1];
        for (int i = 0, j = 0; i < autos.length; i++)
        {
            if (i != index)
            {
                temp[j] = autos[i];
                j++;
            }
        }
        autoComboBoxModel.removeElementAt(index);
        return temp;
    }

    public static void persisit()
    {
        if (new File("DataBase.dat").exists())
        {
            
            dataBase.setR((Nodo) Archivos.carga("DataBase.dat"));

            if (dataBase != null)
            {
                if (dataBase.getR() == null)
                {
                    return;
                }

                
                int count = 0;
                Nodo temp = dataBase.getR();

                do
                {
                    count++;
                    temp = temp.getSiguiente();

                } while (temp != dataBase.getR());

                marcas = new Marca[count];

                marcaComboBoxModel.removeAllElements();

                
                int i = 0;
                Nodo marcasActual = dataBase.getR().getSiguiente();

                do
                {
                    marcas[i] = (Marca) marcasActual.getObj();

                    marcaComboBoxModel.addElement(marcas[i]);

                    
                    if (marcasActual.getAbajo() != null)
                    {
                        String nombreMarca = marcas[i].getNom();
                        Nodo ciudadActual = marcasActual.getAbajo().getSiguiente();
                        do
                        {
                            Ciudad c = (Ciudad) ciudadActual.getObj();

                            
                            Ciudad[] tempCiudades = new Ciudad[ciudades.length + 1];
                            for (int k = 0; k < ciudades.length; k++)
                            {
                                tempCiudades[k] = ciudades[k];
                            }
                            tempCiudades[ciudades.length] = c;
                            ciudades = tempCiudades;

                            ciudadComboBoxModel.addElement(c);
                            CitysCRUD.ciudadesTableModel.addRow(new Object[]{nombreMarca, c.getNom()});

                            ciudadActual = ciudadActual.getSiguiente();
                        } while (ciudadActual != marcasActual.getAbajo().getSiguiente());
                    }

                    i++;

                    marcasActual = marcasActual.getSiguiente();
                    
                } while (marcasActual != dataBase.getR().getSiguiente());

            } else
            {
                System.out.println("El archivo existe pero no se pudo cargar.");
            } 
        }
    }
}
    