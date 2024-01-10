package interfaz_grafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JTextField;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.showMessageDialog;

import java.io.BufferedReader;
import java.io.InputStreamReader;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;

public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField textField;
	
	public static String BBDD="erronka",contraseña="1WMG2023",ip="jdbc:mysql://localhost:3306/erronka",usuario="root";
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					//frame.setExtendedState(MAXIMIZED_BOTH);
					frame.setVisible(true);
					frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../img//logo.png")));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Inicio() {
		setTitle("Inicio de sesion");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 682); 
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Pasahitza:");
		lblNewLabel_1.setForeground(new Color(128, 255, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(184, 465, 116, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Hasi saioa:");
		lblNewLabel.setForeground(new Color(0, 255, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		//lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel.setBounds(322, 420, 132, 30);
		contentPane.add(lblNewLabel);
		
		//interruptor local/web
		JToggleButton interruptor = new JToggleButton("LOCAL");
		interruptor.setBounds(601, 585, 121, 23);
		contentPane.add(interruptor);
		
		ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent)
            {
                // event is generated in button
                int state = itemEvent.getStateChange();
                // if selected print selected in console
                if (state == ItemEvent.SELECTED) {
                    //System.out.println("Selected");
                	interruptor.setText("SERVER");
                    BBDD = "BlackMarket";
                    contraseña = "Melon@Melon12";
                    ip = "jdbc:mysql://192.168.3.1:3306/BlackMarket";
                    usuario = "langile";
                }
                else {
                    //System.out.println("Deselected");
                	interruptor.setText("LOCAL");
                	BBDD = "erronka";
                    contraseña = "1WMG2023";
                    ip = "jdbc:mysql://localhost:3306/erronka";
                    usuario = "root";
                }
            }};
            interruptor.addItemListener(itemListener);
		
		JButton btnNewButton = new JButton("Sartu");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				   Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (Exception a){
				   a.printStackTrace();
				}
				String nan = textField.getText();
				// Establecemos la conexiÃ³n con la base de datos.
				try {
					Connection conexion = DriverManager.getConnection (ip,usuario,contraseña);
					// Preparamos la consulta
					Statement st = conexion.createStatement();
					ResultSet rs = st.executeQuery ("SELECT * FROM langileak WHERE nan='"+nan+"'");
					Boolean admin = false;
					//ResultSet rs = st.executeQuery ("SELECT * FROM langileak");
					int cont=0;
					while(rs.next()) {
						admin = rs.getBoolean(10);
						cont++;
					}
					if(cont!=0 && admin==true) {
						showMessageDialog(null, "Ongi etorri!(usuario admin)");
						dispose();
						Interfaz oforma = new Interfaz();
						oforma.setVisible(true);
					}else if(cont!=0 && admin==false) {
						showMessageDialog(null, "Ongi etorri!");
						dispose();
						usuario oforma = new usuario();
						oforma.setVisible(true);
					}
					else {
						showMessageDialog(null, "Saiatu berriro...");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					showMessageDialog(null, "BBDD inaccesible");
				};
			}
		});
		btnNewButton.setBounds(341, 502, 86, 31);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Logo");
		lblNewLabel_2.setIcon(new ImageIcon("imagenes/logo.png"));
		lblNewLabel_2.setBounds(65, 82, 657, 329);
		contentPane.add(lblNewLabel_2);
		
		textField = new JPasswordField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setBounds(300, 461, 167, 30);
		contentPane.add(textField);	
	}
}


