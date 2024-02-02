package erronka;

import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;


public class konponenteak extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtmota;
	private JTextField txtImg1;
	private JTextField txtModeloa;
	private JTextField txtDeskripzioa;
	private JTextField txtKantitatea;
	private JTextField txtBerria;
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
	private JTextField txtMarka;
	private JTextField txtBalorazioa;
	private JTextField txtId_1;

	public konponenteak() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 749, 642);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(137, 35, 588, 386);
		contentPane.add(table);
		
		JButton btnNewButton = new JButton("Taula");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioaLocal kon=new konexioaLocal();
				Connection conexion = kon.getConnection();
				String sql = "SELECT * FROM konponenteak";
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
				model.addColumn("10");
				
				table.setModel(model);
				String[] array = new String[10];
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
						array[8]=rs.getString(9);
						array[9]=rs.getString(10);
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					showMessageDialog(null, "Konexio arazoak");
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
				    konexioaLocal kon=new konexioaLocal();
				    Connection conexion = kon.getConnection();
				    String id;
					String mota;
					String modeloa;
					String img;
					String deskribapena;
					String kantitatea;
					String prezioa;
					String berria;
					String marka;
					String balorazioa;
					extracted();
				    mota=txtmota.getText();
				    img=txtImg1.getText();
				    modeloa=txtModeloa.getText();
				    deskribapena=txtDeskripzioa.getText();
				    berria=txtBerria.getText();
				    kantitatea=txtKantitatea.getText();
				    id=txtId.getText();
				    prezioa=txtPrezioa.getText();
				    marka=txtMarka.getText();
				    balorazioa=txtBalorazioa.getText();
				    String query ="INSERT INTO erronka.konponenteak"
						+ " Values("+id+", '"+mota+"', '"+img+"', '"+modeloa+"', '"+deskribapena+"', "+kantitatea+", '"+berria+"', "+prezioa+",'"+marka+"','"+balorazioa+"')";
				    Statement stmt;
				
					stmt = conexion.createStatement();
					stmt .executeUpdate(query);
					showMessageDialog(null, "Produktua ongi gehitu da datu basean");
				} catch (SQLException e1) {
					showMessageDialog(null, "Arazoak datuak taulan gehitzerakoan");
					e1.printStackTrace();
				}
			}

			/**
			 * 
			 */
			private void extracted() {
				String id,mota,modeloa,img,deskribapena,kantitatea,prezioa,berria,marka,balorazioa;
			}
		});
		btnGehitu.setBounds(10, 31, 89, 23);
		contentPane.add(btnGehitu);
	
		btnIdinfo = new JButton("Id_info");
		btnIdinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioaLocal kon=new konexioaLocal();
				Connection conexion = kon.getConnection();
				String Id;
				Id=textId2.getText();
				String sql = "SELECT * FROM konponenteak WHERE Id='"+Id+"'";
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
				model.addColumn("10");
				table.setModel(model);
				String[] array = new String[10];
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
						array[8]=rs.getString(9);
						array[9]=rs.getString(10);
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					showMessageDialog(null, "Id okerra");
					e1.printStackTrace();
				}
			}
		});
		JButton btnAktualizatu = new JButton("Aktualizatu");
		btnAktualizatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioaLocal kon=new konexioaLocal();
				    Connection conexion = kon.getConnection();
				    String mod1, mod2, mod3, mod4, izn, marka;
				    izn=txtIzena.getText();
				    marka=txtAbizena.getText();
				    mod1=txtMod1.getText();
				    mod2=txtMod2.getText();
				    mod3=txtMod3.getText();
				    mod4=txtMod4.getText();
				    String query ="UPDATE konponenteak SET "+mod1+" ='"+mod2+"', "+mod3+" ='"+mod4+"' WHERE konponenteMota= '"+izn+"'&& Marka='"+marka+"'";
				    Statement st;
					st = conexion.createStatement();
					st .executeUpdate(query);
					showMessageDialog(null, "Datuak ondo aldatu dira");
				} catch (SQLException e1) {
					showMessageDialog(null, "Arazoa aktualizatzean.");
					e1.printStackTrace();
				}
			}
		});
		btnAktualizatu.setBounds(10, 328, 115, 23);
		contentPane.add(btnAktualizatu);
		btnIdinfo.setBounds(10, 279, 89, 23);
		contentPane.add(btnIdinfo);
		
		txtmota = new JTextField();
		txtmota.setText("Produktua");
		txtmota.setBounds(13, 76, 112, 20);
		contentPane.add(txtmota);
		txtmota.setColumns(10);
		
		txtImg1 = new JTextField();
		txtImg1.setText("Argazkia(linka)");
		txtImg1.setBounds(13, 100, 112, 20);
		contentPane.add(txtImg1);
		txtImg1.setColumns(10);
		
		txtModeloa = new JTextField();
		txtModeloa.setText("Modeloa");
		txtModeloa.setBounds(13, 122, 112, 20);
		contentPane.add(txtModeloa);
		txtModeloa.setColumns(10);
		
		txtDeskripzioa = new JTextField();
		txtDeskripzioa.setText("Deskripzioa");
		txtDeskripzioa.setBounds(13, 143, 112, 20);
		contentPane.add(txtDeskripzioa);
		txtDeskripzioa.setColumns(10);
		
		txtKantitatea = new JTextField();
		txtKantitatea.setText("Kantitatea");
		txtKantitatea.setBounds(13, 166, 112, 20);
		contentPane.add(txtKantitatea);
		txtKantitatea.setColumns(10);
		
		txtBerria = new JTextField();
		txtBerria.setText("Berria");
		txtBerria.setBounds(13, 188, 112, 20);
		contentPane.add(txtBerria);
		txtBerria.setColumns(10);
		
		txtId = new JTextField();
		txtId.setText("Id");
		txtId.setBounds(13, 55, 112, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtPrezioa = new JTextField();
		txtPrezioa.setText("Prezioa");
		txtPrezioa.setBounds(13, 210, 112, 20);
		contentPane.add(txtPrezioa);
		txtPrezioa.setColumns(10);
		
		textId2 = new JTextField();
		textId2.setText("Id");
		textId2.setBounds(13, 305, 86, 20);
		contentPane.add(textId2);
		textId2.setColumns(10);
		
		txtMod1 = new JTextField();
		txtMod1.setText("Aldatu nahi dena");
		txtMod1.setBounds(13, 401, 115, 20);
		contentPane.add(txtMod1);
		txtMod1.setColumns(10);
		
		txtMod2 = new JTextField();
		txtMod2.setText("Aldaketa");
		txtMod2.setBounds(13, 422, 86, 20);
		contentPane.add(txtMod2);
		txtMod2.setColumns(10);
		
		txtMod3 = new JTextField();
		txtMod3.setText("Aldatu nahi dena");
		txtMod3.setBounds(13, 442, 115, 20);
		contentPane.add(txtMod3);
		txtMod3.setColumns(10);
		
		txtMod4 = new JTextField();
		txtMod4.setText("Aldaketa");
		txtMod4.setBounds(13, 463, 86, 20);
		contentPane.add(txtMod4);
		txtMod4.setColumns(10);
		
		txtIzena = new JTextField();
		txtIzena.setText("Izena");
		txtIzena.setBounds(13, 355, 86, 20);
		contentPane.add(txtIzena);
		txtIzena.setColumns(10);
		
		txtAbizena = new JTextField();
		txtAbizena.setText("Marka");
		txtAbizena.setBounds(13, 377, 86, 20);
		contentPane.add(txtAbizena);
		txtAbizena.setColumns(10);
		
		txtMarka = new JTextField();
		txtMarka.setText("Marka");
		txtMarka.setBounds(13, 235, 112, 19);
		contentPane.add(txtMarka);
		txtMarka.setColumns(10);
		
		txtBalorazioa = new JTextField();
		txtBalorazioa.setText("Balorazioa(1-10)");
		txtBalorazioa.setBounds(13, 258, 114, 19);
		contentPane.add(txtBalorazioa);
		txtBalorazioa.setColumns(10);
		
		JButton btnKendu = new JButton("Kendu");
		btnKendu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioaLocal kon=new konexioaLocal();
				    Connection conexion = kon.getConnection();
				    String id;
				    id=textId2.getText();
				    String query ="DELETE FROM erronka.konponenteak WHERE id= "+id;
				    Statement st;
					st = conexion.createStatement();
					st.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Datuak ondo ezabatu dira");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Datuak ezin dira ezabatu");
					e1.printStackTrace();
				}
			}
		});
		btnKendu.setBounds(10, 488, 115, 23);
		contentPane.add(btnKendu);
		
		txtId_1 = new JTextField();
		txtId_1.setText("id");
		txtId_1.setColumns(10);
		txtId_1.setBounds(13, 514, 86, 20);
		contentPane.add(txtId_1);
	}
}
