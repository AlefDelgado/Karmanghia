package CONTROL;

import java.util.Date;

/**
 *
 * @author alefdelgado
 */
public class Car
{
    String placas;
    String modelo;
    int anio;
    Date fechaIngreso;

    public Car()
    {
    }

    public Car(String placas, String modelo, int anio, Date fechaIngreso)
    {
        this.placas = placas;
        this.modelo = modelo;
        this.anio = anio;
        this.fechaIngreso = fechaIngreso;
    }

    
    
}
