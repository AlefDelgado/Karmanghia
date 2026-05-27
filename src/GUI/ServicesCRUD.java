/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alefdelgado
 */
public class ServicesCRUD extends JPanel
{
    Window ventana;
    JTable serviciosJTable;
    static String[] columnas = {"Marca","Ciudad","Sucursal","Servicio"};
    public static DefaultTableModel serviciosTableModel = new DefaultTableModel(columnas, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };;

    public ServicesCRUD(Window ventana)
    {
        this.ventana = ventana;
        setLayout(new BorderLayout());
        JScrollPane tabla = createTabla();
        JPanel campos = createCampos();
        
        add(tabla, BorderLayout.WEST);
        add(campos, BorderLayout.CENTER);
    }
    
    public JScrollPane createTabla()
    {
        serviciosJTable = new JTable(serviciosTableModel);
        serviciosJTable.setFont(new Font("Segoe UI", Font.BOLD, 12));
        serviciosJTable.setBackground(Window.border);
        serviciosJTable.setForeground(Window.mist);
        serviciosJTable.setGridColor(Window.surface);
        serviciosJTable.getTableHeader().setBackground(Window.rootBg);
        serviciosJTable.getTableHeader().setForeground(Window.mist);
        serviciosJTable.setFillsViewportHeight(true);
        serviciosJTable.setRowHeight(50);
        serviciosJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane panel = new JScrollPane(serviciosJTable);
        panel.setPreferredSize(new Dimension(800, 600));
        
        return panel;
    }
    
    public JPanel createCampos()
    {
        JPanel panel = new JPanel();
        panel.setBackground(Window.appBg);
        return panel;
    }
}
