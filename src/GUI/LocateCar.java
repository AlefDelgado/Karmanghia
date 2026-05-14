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
import javax.swing.JTextField;

/**
 *
 * @author alefdelgado
 */
public class LocateCar extends JPanel
{
    private final Window ventana;

    public LocateCar(Window ventana)
    {
        this.ventana = ventana;
        setLayout(new GridBagLayout());
        setBackground(Window.mist);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Placa: "), gbc);

        gbc.gridx = 1;
        JTextField placa = new JTextField(20);
        add(placa, gbc);

        // Fila 1
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Modelo: "), gbc);

        gbc.gridx = 1;
        JTextField mod = new JTextField(20);
        add(mod, gbc);
        
    }
    
    
}
