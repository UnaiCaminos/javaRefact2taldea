package erronka;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class biltegia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtProduktua;
	private JTextField txtMarka;
	private JTextField txtModeloa;
	private JTextField txtKantitatea;
	private JTextField txtMinimoa;
	private JTextField txtMaximoa;
	private JTextField txtId;
	private JTextField txtPrezioa;
	private JButton btnIdinfo;
	private JTextField textId2;
	private JTextField txtMod1;
	private JTextField txtMod2;
	private JTextField txtMod3;
	private JTextField txtMod4;
	private JTextField txtIzena;
	private JTextField txtAbizena;

	public biltegia() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 749, 642);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(135, 11, 588, 386);
		contentPane.add(table);
		
		JButton btnNewButton = new JButton("Taula");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT * FROM biltegia";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("1");
				model.addColumn("2");
				model.addColumn("3");
				model.addColumn("4");
				model.addColumn("5");
				model.addColumn("6");
				model.addColumn("7");
				model.addColumn("8");
				
				table.setModel(model);
				String[] array = new String[8];
				try {
					st=conexion.createStatement();
					rs = st.executeQuery(sql);
					while (rs.next()) {
						//System.out.println(rs.getString(2));
						array[0]=rs.getString(1);
						array[1]=rs.getString(2);
						array[2]=rs.getString(3);
						array[3]=rs.getString(4);
						array[4]=rs.getString(5);
						array[5]=rs.getString(6);
						array[6]=rs.getString(7);
						array[7]=rs.getString(8);
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 7, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnGehitu = new JButton("Gehitu ");
		btnGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioa kon=new konexioa();
				    Connection conexion = kon.getConnection();
				    String Produktua,modeloa,marka,kantitatea,max,min,Id,prezioa;
				    Produktua=txtProduktua.getText();
				    marka=txtMarka.getText();
				    modeloa=txtModeloa.getText();
				    kantitatea=txtKantitatea.getText();
				    max=txtMaximoa.getText();
				    min=txtMinimoa.getText();
				    Id=txtId.getText();
				    prezioa=txtPrezioa.getText();
				    String query ="INSERT INTO db1.biltegia"
						+ " Values('"+Produktua+"', '"+marka+"', '"+modeloa+"', '"+kantitatea+"', '"+max+"', '"+min+"', '"+Id+"', '"+prezioa+"')";
				    Statement stmt;
				
					stmt = conexion.createStatement();
					stmt .executeUpdate(query);
					System.out.println("Produktu bat gehitu duzu");
				} catch (SQLException e1) {
					System.out.println("Error!!!");
					e1.printStackTrace();
				}
			}
		});
		btnGehitu.setBounds(10, 31, 89, 23);
		contentPane.add(btnGehitu);
	
		btnIdinfo = new JButton("Id_info");
		btnIdinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String Id;
				Id=textId2.getText();
				String sql = "SELECT * FROM biltegia WHERE Id='"+Id+"'";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("1");
				model.addColumn("2");
				model.addColumn("3");
				model.addColumn("4");
				model.addColumn("5");
				model.addColumn("6");
				model.addColumn("7");
				model.addColumn("8");
				
				table.setModel(model);
				String[] array = new String[8];
				try {
					st=conexion.createStatement();
					rs = st.executeQuery(sql);
					while (rs.next()) {
						//System.out.println(rs.getString(2));
						array[0]=rs.getString(1);
						array[1]=rs.getString(2);
						array[2]=rs.getString(3);
						array[3]=rs.getString(4);
						array[4]=rs.getString(5);
						array[5]=rs.getString(6);
						array[6]=rs.getString(7);
						array[7]=rs.getString(8);
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JButton btnAktualizatu = new JButton("Aktualizatu");
		btnAktualizatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioa kon=new konexioa();
				    Connection conexion = kon.getConnection();
				    String mod1, mod2, mod3, mod4, izn, marka;
				    izn=txtIzena.getText();
				    marka=txtAbizena.getText();
				    mod1=txtMod1.getText();
				    mod2=txtMod2.getText();
				    mod3=txtMod3.getText();
				    mod4=txtMod4.getText();
				    String query ="UPDATE biltegia SET "+mod1+" ='"+mod2+"', "+mod3+" ='"+mod4+"' WHERE Produktua_izena= '"+izn+"'&& Marka='"+marka+"'";
				    Statement st;
					st = conexion.createStatement();
					st .executeUpdate(query);
					System.out.println("Produktu baten datuak aldatu dituzu");
				} catch (SQLException e1) {
					System.out.println("Error!!!");
					e1.printStackTrace();
				}
			}
		});
		btnAktualizatu.setBounds(10, 286, 115, 23);
		contentPane.add(btnAktualizatu);
		btnIdinfo.setBounds(10, 240, 89, 23);
		contentPane.add(btnIdinfo);
		
		txtProduktua = new JTextField();
		txtProduktua.setText("Produktua");
		txtProduktua.setBounds(13, 55, 112, 20);
		contentPane.add(txtProduktua);
		txtProduktua.setColumns(10);
		
		txtMarka = new JTextField();
		txtMarka.setText("Marka");
		txtMarka.setBounds(13, 78, 112, 20);
		contentPane.add(txtMarka);
		txtMarka.setColumns(10);
		
		txtModeloa = new JTextField();
		txtModeloa.setText("Modeloa");
		txtModeloa.setBounds(13, 102, 112, 20);
		contentPane.add(txtModeloa);
		txtModeloa.setColumns(10);
		
		txtKantitatea = new JTextField();
		txtKantitatea.setText("Kantitatea");
		txtKantitatea.setBounds(13, 124, 112, 20);
		contentPane.add(txtKantitatea);
		txtKantitatea.setColumns(10);
		
		txtMinimoa = new JTextField();
		txtMinimoa.setText("Kantitate minimoa");
		txtMinimoa.setBounds(13, 149, 112, 20);
		contentPane.add(txtMinimoa);
		txtMinimoa.setColumns(10);
		
		txtMaximoa = new JTextField();
		txtMaximoa.setText("Kantitate maximoa");
		txtMaximoa.setBounds(13, 170, 112, 20);
		contentPane.add(txtMaximoa);
		txtMaximoa.setColumns(10);
		
		txtId = new JTextField();
		txtId.setText("Id");
		txtId.setBounds(13, 194, 112, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtPrezioa = new JTextField();
		txtPrezioa.setText("Prezioa");
		txtPrezioa.setBounds(13, 218, 112, 20);
		contentPane.add(txtPrezioa);
		txtPrezioa.setColumns(10);
		
		textId2 = new JTextField();
		textId2.setText("Id");
		textId2.setBounds(13, 263, 86, 20);
		contentPane.add(textId2);
		textId2.setColumns(10);
		
		txtMod1 = new JTextField();
		txtMod1.setText("Aldatu nahi dena");
		txtMod1.setBounds(10, 352, 115, 20);
		contentPane.add(txtMod1);
		txtMod1.setColumns(10);
		
		txtMod2 = new JTextField();
		txtMod2.setText("Aldaketa");
		txtMod2.setBounds(10, 377, 86, 20);
		contentPane.add(txtMod2);
		txtMod2.setColumns(10);
		
		txtMod3 = new JTextField();
		txtMod3.setText("Aldatu nahi dena");
		txtMod3.setBounds(10, 398, 115, 20);
		contentPane.add(txtMod3);
		txtMod3.setColumns(10);
		
		txtMod4 = new JTextField();
		txtMod4.setText("Aldaketa");
		txtMod4.setBounds(10, 420, 86, 20);
		contentPane.add(txtMod4);
		txtMod4.setColumns(10);
		
		txtIzena = new JTextField();
		txtIzena.setText("Izena");
		txtIzena.setBounds(10, 310, 86, 20);
		contentPane.add(txtIzena);
		txtIzena.setColumns(10);
		
		txtAbizena = new JTextField();
		txtAbizena.setText("Marka");
		txtAbizena.setBounds(10, 332, 86, 20);
		contentPane.add(txtAbizena);
		txtAbizena.setColumns(10);
	}
}
