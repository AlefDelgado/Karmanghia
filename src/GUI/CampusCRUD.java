package GUI;

import CONTROL.Ciudad;
import CONTROL.DataBase;
import CONTROL.Marca;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alefdelgado
 */
public class CampusCRUD extends JPanel
{
    Window ventana;
    
    JTable sucursalesJTable;
    static String[] columnas = {"Marca","Ciudad","Sucursal"};
    public static DefaultTableModel ciudadesTableModel = new DefaultTableModel(columnas, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
    
    
    public CampusCRUD(Window ventana)
    {
        this.ventana = ventana;
        setLayout(new BorderLayout());
        JScrollPane tabla = createScrollPanel();
        JPanel campos = createCampos();
        
        add(tabla, BorderLayout.WEST);
        add(campos, BorderLayout.CENTER);
    }
    
    public JScrollPane createScrollPanel()
    {
        
        sucursalesJTable = new JTable(ciudadesTableModel);
        sucursalesJTable.setFont(new Font("Segoe UI", Font.BOLD, 12));
        sucursalesJTable.setBackground(Window.border);
        sucursalesJTable.setForeground(Window.mist);
        sucursalesJTable.setGridColor(Window.surface);
        sucursalesJTable.getTableHeader().setBackground(Window.rootBg);
        sucursalesJTable.getTableHeader().setForeground(Window.mist);
        sucursalesJTable.setFillsViewportHeight(true);
        sucursalesJTable.setRowHeight(50);
        sucursalesJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane panel = new JScrollPane(sucursalesJTable);
        panel.setPreferredSize(new Dimension(800, 600));
        return panel;
    }
    
    public JPanel createCampos()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        panel.setBackground(Window.appBg);
        
        int fila = 0;
        gbc.gridx = 0;
        gbc.gridy = fila;
        JLabel marcalbl = new JLabel("Marca: ");
        marcalbl.setForeground(Window.mist);
        panel.add(marcalbl, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = fila;
        JComboBox<Marca> marcasComboBox = new JComboBox<>(DataBase.marcaComboBoxModel);
        marcasComboBox.setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,int index, boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    setBackground(Window.surface); // azul al hover
                    setForeground(Window.mist);
                    setOpaque(true);
                } else {
                    setBackground(Window.border);
                    setForeground(Window.mist);
                    setOpaque(true);
                }

                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // padding
                return this;
            }
        });
        marcasComboBox.setPreferredSize(new Dimension(160, 20));
        panel.add(marcasComboBox, gbc);
        
        
        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        JLabel ciudadlbl = new JLabel("Ciudad: ");
        ciudadlbl.setForeground(Window.mist);
        panel.add(ciudadlbl, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = fila;
        JComboBox<Ciudad> ciudadesComboBox = new JComboBox<>(DataBase.ciudadComboBoxModel);
        ciudadesComboBox.setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,int index, boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    setBackground(Window.surface); // azul al hover
                    setForeground(Window.mist);
                    setOpaque(true);
                } else {
                    setBackground(Window.border);
                    setForeground(Window.mist);
                    setOpaque(true);
                }

                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // padding
                return this;
            }
        });
        ciudadesComboBox.setPreferredSize(new Dimension(160, 20));
        panel.add(ciudadesComboBox, gbc);
        
        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        JLabel nameLBL = new JLabel("Nombre: ");
        nameLBL.setForeground(Window.mist);
        panel.add(nameLBL, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = fila;
        JTextField nombreField = new JTextField();
        nombreField.setPreferredSize(new Dimension(200, 20));
        panel.add(nombreField, gbc);
        
        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        JLabel dirJLabel = new JLabel("Director: ");
        dirJLabel.setForeground(Window.mist);
        panel.add(dirJLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = fila;
        JTextField directorField = new JTextField();
        directorField.setPreferredSize(new Dimension(200, 20));
        panel.add(directorField, gbc);
        
        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        JLabel respJLabel = new JLabel("Responsable: ");
        respJLabel.setForeground(Window.mist);
        panel.add(respJLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = fila;
        JTextField responsableField = new JTextField();
        responsableField.setPreferredSize(new Dimension(200, 20));
        panel.add(responsableField, gbc);
        
        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton registrarButton = Window.crearBotonExito("Registrar");
        registrarButton.setOpaque(true);
        registrarButton.setBorderPainted(false);
        registrarButton.setFocusPainted(false);
        panel.add(registrarButton, gbc);
        
        fila++;
        gbc.gridy = fila;
        JButton editaButton = Window.crearBotonSecundario("Editar");
        editaButton.setOpaque(true);
        editaButton.setBorderPainted(false);
        editaButton.setFocusPainted(false);
        panel.add(editaButton, gbc);
        
        fila++;
        gbc.gridy = fila;
        JButton eliminaButton = Window.crearBotonPeligro("Eliminar");
        eliminaButton.setOpaque(true);
        eliminaButton.setBorderPainted(false);
        eliminaButton.setFocusPainted(false);
        panel.add(eliminaButton, gbc);
        
        return panel;
    }
}
