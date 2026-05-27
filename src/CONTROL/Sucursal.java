
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROL;

import java.io.Serializable;

/**
 *
 * @author alefdelgado
 */
public class Sucursal implements Serializable
{
    String nom;
    String dir;
    String resp;

    public Sucursal()
    {
    }

    public Sucursal(String nom, String dir, String resp)
    {
        this.nom = nom;
        this.dir = dir;
        this.resp = resp;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getDir()
    {
        return dir;
    }

    public void setDir(String dir)
    {
        this.dir = dir;
    }

    public String getResp()
    {
        return resp;
    }

    public void setResp(String resp)
    {
        this.resp = resp;
    }

    @Override
    public String toString()
    {
        return nom;
    }
    
    
}
