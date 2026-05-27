package CONTROL;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author alefdelgado
 */
public class Auto implements Serializable
{
    String placas;
    String modelo;
    int anio;
    Date fechaIngreso;

    public Auto()
    {
    }

    public Auto(String placas, String modelo, int anio, Date fechaIngreso)
    {
        this.placas = placas;
        this.modelo = modelo;
        this.anio = anio;
        this.fechaIngreso = fechaIngreso;
    }

    
    
}
