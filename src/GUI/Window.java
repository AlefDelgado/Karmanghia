package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.spi.LocaleServiceProvider;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.SeparatorUI;

/**
 *
 * @author alefdelgado
 */
public class Window extends JFrame
{
    // Paleta de colores (De canva, Mauro, no de la IA)
    // Clara:
    
    public static final Color midnigth = Color.decode("#0A1628");
    public static final Color deepNavy = Color.decode("#132238");
    public static final Color ocean = Color.decode("#1A3A5C");
    public static final Color ice = Color.decode("#F0F4FA");
    public static final Color mist = Color.decode("#D4DCE8");
    
    public static final Color cloud = Color.decode("#E8EDF5");
    public static final Color ink = Color.decode("#0A1628");
    public static final Color slate = Color.decode("#5A6A80");
    public static final Color steel = Color.decode("#8A9AB0");
    
    public static final Color gain = Color.decode("#2AAF7F");
    public static final Color loss = Color.decode("#D94F3D");
    public static final Color action = Color.decode("#3A7BD5");
    public static final Color alert = Color.decode("#E8A830");
    
    // Oscura: ()
    
    
    private static JLabel titulo;
    private CardLayout cards = new CardLayout();
    private JPanel container = new JPanel(cards);
    
    public Window(String title)
    {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        container.add(welcome(), "Welcome");
        container.add(new LocateCar(this), "LocateCar");
        container.add(new LocateServices(this), "LocateServices");
        container.add(new Compare(this), "Compare");
        container.add(new FreeVehicle(this), "FreeVehicle");      
        
        JPanel header = createHeader();
        JPanel buttons = botonera();
        JPanel footer = createFooter();
        add(header, BorderLayout.NORTH);
        add(buttons, BorderLayout.WEST);
        add(container, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }
    
    public JPanel createHeader()
    {
        JPanel header = new JPanel();
        header.setBackground(midnigth);
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(0, 80));
        
        titulo = new JLabel("Bienvenido", SwingConstants.CENTER);
        titulo.setForeground(mist);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        
        header.add(titulo, BorderLayout.CENTER);
        
        return header;
    }
    
    public JPanel botonera()
    {
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 50));
        buttons.setBackground(ocean);
        buttons.setPreferredSize(new Dimension(250, 0));
        
        JButton locateCarbtn = new JButton("Vehículos");
        locateCarbtn.setBackground(mist);
        locateCarbtn.setPreferredSize(new Dimension(200, 90));
        locateCarbtn.addActionListener(e -> 
        {
            mostrar("LocateCar");
        });
        
        JButton locateServicebtn = new JButton("Servicios");
        locateServicebtn.setBackground(mist);
        locateServicebtn.setPreferredSize(new Dimension(200, 90));
        locateServicebtn.addActionListener(e -> 
        {
            mostrar("LocateServices");
        });
        
        JButton comparePricesbtn = new JButton("Comparar Precios");
        comparePricesbtn.setBackground(mist);
        comparePricesbtn.setPreferredSize(new Dimension(200, 90));
        comparePricesbtn.addActionListener(e -> 
        {
            mostrar("Compare");
        });
        
        JButton releasebtn = new JButton("Liberar Vehículos (fecha)");
        releasebtn.setBackground(mist);
        releasebtn.setPreferredSize(new Dimension(200, 90));
        releasebtn.addActionListener(e -> 
        {
            mostrar("FreeVehicle");
        });
        
        buttons.add(locateCarbtn, BorderLayout.CENTER);
        buttons.add(locateServicebtn, BorderLayout.CENTER);
        buttons.add(comparePricesbtn, BorderLayout.CENTER);
        buttons.add(releasebtn, BorderLayout.CENTER);
        
        return buttons;
    }
    
    /**
     *
     * @return
     */
    public static JPanel welcome()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 150));
        panel.setBackground(mist);
        panel.setPreferredSize(new Dimension(450, 600));
        
        return panel;
    }
    
    public JPanel createFooter()
    {
        JPanel header = new JPanel();
        header.setLayout(new GridLayout());
        header.setBackground(midnigth);
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(0, 80));
        
        JLabel txt = new JLabel("Alef Delgado - UAEMex 2026", SwingConstants.CENTER);
        txt.setForeground(mist);
        txt.setFont(new Font("Arial", Font.PLAIN, 12));
        
        
        header.add(txt, BorderLayout.CENTER);
        
        return header;
    }
    
    public void mostrar(String name)
    {
        cards.show(container, name);
        switch (name)
        {
            case "LocateCar":
                titulo.setText("Vehículos");
                break;
            case "LocateServices":
                titulo.setText("Servicios");
                break;
            case "Compare":
                titulo.setText("Comparar");
                break;
            case "FreeVehicle":
                titulo.setText("Liberación");
                break;
            default:
                break;
        }
    }
}
