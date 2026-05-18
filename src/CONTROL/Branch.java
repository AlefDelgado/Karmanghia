/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROL;

/**
 *
 * @author garcd
 */
public class Branch implements java.io.Serializable
{
    private String nom;
    private String dir;
    private String resp;

    public Branch(String nom, String dir, String resp)
    {
        this.nom = nom;
        this.dir = dir;
        this.resp = resp;
    }

    /**
     * @return the nom
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * @return the dir
     */
    public String getDir()
    {
        return dir;
    }

    /**
     * @return the resp
     */
    public String getResp()
    {
        return resp;
    }

    /**
     * @param resp the resp to set
     */
    public void setResp(String resp)
    {
        this.resp = resp;
    }
    
}
