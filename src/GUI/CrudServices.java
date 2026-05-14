/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author alefdelgado
 */
public class CrudServices extends JPanel
{
    public Window ventana;

    public CrudServices(Window ventana)
    {
        this.ventana = ventana;
        setLayout(new BorderLayout());
        setBackground(Window.mist);
        
        JTabbedPane menu = new JTabbedPane();
        menu.addTab("Nuevo", null);
        menu.addTab("Eliminar", null);
        menu.addTab("Localizar", null);
        menu.addTab("Editar", null);
        
        add(menu, BorderLayout.CENTER);
    }
    
    
}
