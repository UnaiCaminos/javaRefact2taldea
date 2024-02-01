package erronka;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class erronka0 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtIzena1;
	private JTextField txtAbz1;
	private JTextField txtAbz2;
	private JTextField txtNan;
	private JTextField txtBankuZenb;
	private JTextField txtLana;
	private JTextField txtKorreoa;
	private JTextField txtName;
	private JTextField textAbizena2;
	private JTextField txtModifikatu1;
	private JTextField txtModifikatu2;
	private JTextField txtModifikatu3;
	private JTextField txtModifikatu4;
	private JTextField textIzena1;
	private JTextField textAbizena1;

	public erronka0() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 778, 758);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnHornitzailea = new JButton("Hornitzaileen taula");
		btnHornitzailea.setBounds(4, 551, 158, 23);
		btnHornitzailea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT * FROM hornitzaile";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("Izena");
				model.addColumn("Kokapena");
				model.addColumn("Telefonoa");
				model.addColumn("korreo_elektronikoa");
				model.addColumn("ID");
				
				table.setModel(model);
				String[] array = new String[5];
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
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnHornitzailea);
		
		table = new JTable();
		table.setBounds(188, 15, 759, 472);
		contentPane.add(table);
		
		JButton btnLangileak = new JButton("Langileak");
		btnLangileak.setBounds(4, 21, 148, 23);
		btnLangileak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT * FROM Langileak";
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
				model.addColumn("9");
				
				table.setModel(model);
				String[] array = new String[9];
				try {
					st=conexion.createStatement();
					rs = st.executeQuery(sql);
					while (rs.next()) {
						array[0]=rs.getString(1);
						array[1]=rs.getString(2);
						array[2]=rs.getString(3);
						array[3]=rs.getString(4);
						array[4]=rs.getString(5);
						array[5]=rs.getString(6);
						array[6]=rs.getString(7);
						array[7]=rs.getString(8);
						array[8]=rs.getString(9);
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnLangileak);
		
		JButton btnBezeroak = new JButton("Bezeroen taula");
		btnBezeroak.setBounds(4, 502, 158, 23);
		btnBezeroak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT DISTINCT * FROM bezeroak";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("Izena");
				model.addColumn("Abizenak");
				model.addColumn("Sexua");
				model.addColumn("korreo_elektronikoa");
				model.addColumn("Telefonoa");
				model.addColumn("helbidea");
				model.addColumn("NAN");
				model.addColumn("PK");
				
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
		contentPane.add(btnBezeroak);
		
		JButton btnNewButton = new JButton("Bezeroak ordainketa");
		btnNewButton.setBounds(4, 527, 158, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT DISTINCT * FROM bezeroak_ordainketak";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("Izena");
				model.addColumn("Data");
				model.addColumn("Ordainketa egoera");
				model.addColumn("Ordainketa metodoa");
				model.addColumn("Ordinketa kantitatea");
				model.addColumn("Eskaeren egoera");
				
				table.setModel(model);
				String[] array = new String[6];
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
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Hornitzaileen ordainketak");
		btnNewButton_1.setBounds(4, 574, 158, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT DISTINCT * FROM hornitzaile_ordainketak";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("Izena");
				model.addColumn("Data");
				model.addColumn("Ordainketa egoera");
				model.addColumn("Ordainketa metodoa");
				model.addColumn("Ordinketa kantitatea");
				model.addColumn("ID");
				model.addColumn("NAN");
				
				table.setModel(model);
				String[] array = new String[7];
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
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnbiltegia = new JButton("Biltegia");
		btnbiltegia.setBounds(4, 598, 158, 23);
		btnbiltegia.addActionListener(new ActionListener() {
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
		contentPane.add(btnbiltegia);
		
		JButton btnGehitu = new JButton("Langileak gehitu");
		btnGehitu.setBounds(4, 45, 148, 23);
		btnGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioa kon=new konexioa();
				    Connection conexion = kon.getConnection();
				    String izena,abz1,abz2,Nan,BankuZenb,funtzioa,korreoa;
				    izena=txtIzena1.getText();
				    abz1=txtAbz1.getText();
				    abz2=txtAbz2.getText();
				    Nan=txtNan.getText();
				    BankuZenb=txtBankuZenb.getText();
				    funtzioa=txtLana.getText();
				    korreoa=txtKorreoa.getText();
				    String query ="INSERT INTO db1.langileak"
						+ " Values('"+izena+"', '"+abz1+"', '"+abz2+"', '"+Nan+"', '"+BankuZenb+"', '"+funtzioa+"', '"+korreoa+"')";
				    Statement stmt;
					stmt = conexion.createStatement();
					stmt .executeUpdate(query);
					System.out.println("Langile bat gehitu duzu");
				} catch (SQLException e1) {
					System.out.println("Error!!!");
					e1.printStackTrace();
				}
				
			}
		});
		contentPane.add(btnGehitu);
		
		JButton btnKendu = new JButton("Langileak kendu");
		btnKendu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioa kon=new konexioa();
				    Connection conexion = kon.getConnection();
				    String langilea, abizena;
				    langilea=txtName.getText();
				    abizena=textAbizena2.getText();
				    String query ="DELETE FROM db1.langileak WHERE izena= '"+langilea+"'&& abizena1= '"+abizena+"'";
				    Statement st;
					st = conexion.createStatement();
					st.executeUpdate(query);
					System.out.println("Langile bat ezabatu duzu");
				} catch (SQLException e1) {
					System.out.println("Error!!!");
					e1.printStackTrace();
				}
			}
		});
		btnKendu.setBounds(4, 240, 158, 23);
		contentPane.add(btnKendu);
		
		JButton btnAktualizatu = new JButton("Taula aktualizatu");
		btnAktualizatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioa kon=new konexioa();
				    Connection conexion = kon.getConnection();
				    String mod1, mod2, mod3, mod4, izn, abz;
				    izn=textIzena1.getText();
				    abz=textAbizena1.getText();
				    mod1=txtModifikatu1.getText();
				    mod2=txtModifikatu2.getText();
				    mod3=txtModifikatu3.getText();
				    mod4=txtModifikatu4.getText();
				    String query ="UPDATE langileak SET "+mod1+" ='"+mod2+"', "+mod3+" ='"+mod4+"' WHERE izena= '"+izn+"'&& abizena1='"+abz+"'";
				    Statement st;
					st = conexion.createStatement();
					st .executeUpdate(query);
					System.out.println("Usuario bat ezabatu duzu");
				} catch (SQLException e1) {
					System.out.println("Error!!!");
					e1.printStackTrace();
				}
			}
		});
		btnAktualizatu.setBounds(4, 328, 158, 23);
		contentPane.add(btnAktualizatu);
		
		txtIzena1 = new JTextField();
		txtIzena1.setBounds(4, 69, 132, 20);
		txtIzena1.setText("Izena");
		contentPane.add(txtIzena1);
		txtIzena1.setColumns(10);
		
		txtName = new JTextField();
		txtName.setText("Izena");
		txtName.setBounds(4, 283, 132, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		textAbizena2 = new JTextField();
		textAbizena2.setText("Abizena");
		textAbizena2.setBounds(4, 304, 132, 20);
		contentPane.add(textAbizena2);
		textAbizena2.setColumns(10);
		
		txtModifikatu1 = new JTextField();
		txtModifikatu1.setText("Modifikatu nahi den elementua");
		txtModifikatu1.setBounds(4, 410, 174, 20);
		contentPane.add(txtModifikatu1);
		txtModifikatu1.setColumns(10);
		
		txtModifikatu2 = new JTextField();
		txtModifikatu2.setText("Modifikazioa");
		txtModifikatu2.setBounds(4, 433, 174, 20);
		contentPane.add(txtModifikatu2);
		txtModifikatu2.setColumns(10);
		
		txtModifikatu3 = new JTextField();
		txtModifikatu3.setText("Modifikatu nahi den elementua");
		txtModifikatu3.setBounds(4, 456, 174, 20);
		contentPane.add(txtModifikatu3);
		txtModifikatu3.setColumns(10);
		
		txtModifikatu4 = new JTextField();
		txtModifikatu4.setText("Modifikazioa");
		txtModifikatu4.setBounds(4, 480, 174, 20);
		contentPane.add(txtModifikatu4);
		txtModifikatu4.setColumns(10);
		
		textIzena1 = new JTextField();
		textIzena1.setText("Izena");
		textIzena1.setToolTipText("");
		textIzena1.setBounds(4, 362, 132, 20);
		contentPane.add(textIzena1);
		textIzena1.setColumns(10);
		
		textAbizena1 = new JTextField();
		textAbizena1.setText("Abizena");
		textAbizena1.setBounds(4, 388, 132, 20);
		contentPane.add(textAbizena1);
		textAbizena1.setColumns(10);
		
		txtAbz2 = new JTextField();
		txtAbz2.setBounds(4, 110, 132, 20);
		txtAbz2.setText("Bigarren abizena");
		contentPane.add(txtAbz2);
		txtAbz2.setColumns(10);
		
		txtNan = new JTextField();
		txtNan.setBounds(4, 132, 132, 20);
		txtNan.setText("NAN");
		contentPane.add(txtNan);
		txtNan.setColumns(10);
		
		txtBankuZenb = new JTextField();
		txtBankuZenb.setBounds(4, 151, 132, 20);
		txtBankuZenb.setText("Banku zenbakia");
		contentPane.add(txtBankuZenb);
		txtBankuZenb.setColumns(10);
		
		txtLana = new JTextField();
		txtLana.setBounds(4, 174, 132, 20);
		txtLana.setText("Lan-postua");
		contentPane.add(txtLana);
		txtLana.setColumns(10);
		
		txtKorreoa = new JTextField();
		txtKorreoa.setBounds(4, 196, 132, 20);
		txtKorreoa.setText("Korreoa");
		contentPane.add(txtKorreoa);
		txtKorreoa.setColumns(10);
		
		txtAbz1 = new JTextField();
		txtAbz1.setBounds(4, 89, 132, 20);
		txtAbz1.setText("Lehen abizena");
		contentPane.add(txtAbz1);
		txtAbz1.setColumns(10);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnKendu, btnHornitzailea, table, btnLangileak, btnBezeroak, btnNewButton, btnNewButton_1, btnbiltegia, btnGehitu, btnAktualizatu, txtIzena1, txtName, textAbizena2, txtModifikatu1, txtModifikatu2, txtModifikatu3, txtModifikatu4, textIzena1, textAbizena1, txtAbz2, txtNan, txtBankuZenb, txtLana, txtKorreoa, txtAbz1}));
	}
}
