package GUI;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import GUI.Window;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author alefdelgado
 */
public class CrudVehicles extends JPanel
{
    private final Window ventana;

    public CrudVehicles(Window ventana)
    {
        this.ventana = ventana;
        setLayout(new BorderLayout());
        setBackground(Window.mist);
        
        JTabbedPane menu = new JTabbedPane();
        menu.addTab("Nuevo", createPanelAdd());
        menu.addTab("Eliminar", null);
        menu.addTab("Localizar", null);
        menu.addTab("Editar", null);
        
        add(menu, BorderLayout.CENTER);
    }
    
    public JPanel createPanelAdd()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Window.ice);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets =  new Insets(5, 5, 5, 5);
        gbc.fill =  GridBagConstraints.HORIZONTAL;
        
        int fila = 0;
        gbc.gridx = 0; gbc.gridy = fila;
        panel.add(new JLabel("Placas"), gbc);
        gbc.
        
        return panel;
    }
    
    
}
