package erronka;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class bezeroak extends JFrame {
	private JTable table;
	private JButton btnGehitu;
	private JTextField txtIzena;
	private JTextField txtAbizena1;
	private JTextField txtAbizena2;
	private JTextField txtNan;
	private JTextField txtBankuZenbakia;
	private JTextField txtHelbidea;
	private JButton btnKendu;
	private JTextField txtIzena1;
	private JTextField txtNan1;
	private JButton btnAktualizatu;
	private JTextField txtMod1;
	private JTextField txtMod2;
	private JTextField txtMod3;
	private JTextField txtMod4;
	private JTextField txtAbizena0;
	private JTextField txtIzena2;
	private JButton btnBilatu;
	private JTextField txtLetra;

	

	public bezeroak() {
		getContentPane().setLayout(null);
		table = new JTable();
		table.setBounds(157, 13, 760, 509);
		getContentPane().add(table);
		JButton btnTaula = new JButton("Taula");
		btnTaula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioaLocal kon=new konexioaLocal();
				Connection conexion = kon.getConnection();
				String sql = "SELECT * FROM bezeroak";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("izena");
				model.addColumn("abizena1");
				model.addColumn("abizena2");
				model.addColumn("nan");
				model.addColumn("banku_zenbakia");
				model.addColumn("helbidea");
				
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
		btnTaula.setBounds(10, 10, 101, 21);
		getContentPane().add(btnTaula);
		
		
		
		btnGehitu = new JButton("Gehitu");
		btnGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioaLocal kon=new konexioaLocal();
				    Connection conexion = kon.getConnection();
                    String izn;
					String abz1;
					String abz2;
					String nan;
					String bankuNum;
					String helbidea;
				    izn=txtIzena.getText();
				    abz1=txtAbizena1.getText();
				    abz2=txtAbizena2.getText();
				    nan=txtNan.getText();
				    bankuNum=txtBankuZenbakia.getText();
				    helbidea=txtHelbidea.getText();
				    String query ="INSERT INTO erronka.bezeroak"
						+ " Values('"+izn+"', '"+abz1+"', '"+abz2+"', '"+nan+"', '"+bankuNum+"', '"+helbidea+"')";
				    Statement stmt;
					stmt = conexion.createStatement();
					stmt .executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Datuak ondo gehitu dira");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Datuak ezin dira gehitu");
					e1.printStackTrace();
				}
			}

			/**
			 * 
			 */
			private void extracted() {
				String izn,abz1,abz2,nan,bankuNum,helbidea;
			}
		});
		btnGehitu.setBounds(10, 32, 101, 21);
		getContentPane().add(btnGehitu);
		
		btnKendu = new JButton("Kendu");
		btnKendu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioaLocal kon=new konexioaLocal();
				    Connection conexion = kon.getConnection();
				    String izn, nan;
				    izn=txtIzena1.getText();
				    nan=txtNan1.getText();
				    String query ="DELETE FROM erronka.bezeroak WHERE izena= '"+izn+"'&& nan='"+nan+"'";
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
		btnKendu.setBounds(10, 175, 101, 21);
		getContentPane().add(btnKendu);
		
		btnAktualizatu = new JButton("Aktualizatu");
		btnAktualizatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioaLocal kon=new konexioaLocal();
				    Connection conexion = kon.getConnection();
				    String mod1, mod2, mod3, mod4, izn, abz;
				    izn=txtIzena2.getText();
				    abz=txtAbizena0.getText();
				    mod1=txtMod1.getText();
				    mod2=txtMod2.getText();
				    mod3=txtMod3.getText();
				    mod4=txtMod4.getText();
				    String query ="UPDATE langileak SET "+mod1+" ='"+mod2+"', "+mod3+" ='"+mod4+"' WHERE izena= '"+izn+"'&& abizena1='"+abz+"'";
				    Statement st;
					st = conexion.createStatement();
					st .executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Datuak ondo aldatu dira");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Datuak ez dira aldatu");
					e1.printStackTrace();
				}
			}
		});
		btnAktualizatu.setBounds(10, 238, 101, 21);
		getContentPane().add(btnAktualizatu);
		
		btnBilatu = new JButton("Bilatu");
		btnBilatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioaLocal kon=new konexioaLocal();
				Connection conexion = kon.getConnection();
				String letra;
				letra=txtLetra.getText();
				String sql = "SELECT * FROM bezeroak WHERE izena LIKE '"+letra+"%'";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("izena");
				model.addColumn("abizena1");
				model.addColumn("abizena2");
				model.addColumn("nan");
				model.addColumn("banku_zenbakia");
				model.addColumn("helbidea");
				
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
		btnBilatu.setBounds(10, 380, 101, 21);
		getContentPane().add(btnBilatu);
		
		txtIzena = new JTextField();
		txtIzena.setText("izena");
		txtIzena.setColumns(10);
		txtIzena.setBounds(10, 55, 114, 20);
		getContentPane().add(txtIzena);
		
		txtAbizena1 = new JTextField();
		txtAbizena1.setText("Lehenengo abizena");
		txtAbizena1.setColumns(10);
		txtAbizena1.setBounds(10, 75, 114, 20);
		getContentPane().add(txtAbizena1);
		
		txtAbizena2 = new JTextField();
		txtAbizena2.setText("Bigarren abizena");
		txtAbizena2.setColumns(10);
		txtAbizena2.setBounds(10, 95, 114, 20);
		getContentPane().add(txtAbizena2);
		
		txtNan = new JTextField();
		txtNan.setText("nan");
		txtNan.setColumns(10);
		txtNan.setBounds(10, 115, 114, 20);
		getContentPane().add(txtNan);
		
		txtBankuZenbakia = new JTextField();
		txtBankuZenbakia.setText("Banku zenbakia");
		txtBankuZenbakia.setColumns(10);
		txtBankuZenbakia.setBounds(10, 135, 114, 20);
		getContentPane().add(txtBankuZenbakia);
		
		txtHelbidea = new JTextField();
		txtHelbidea.setText("Helbidea");
		txtHelbidea.setColumns(10);
		txtHelbidea.setBounds(10, 155, 114, 20);
		getContentPane().add(txtHelbidea);
		
		txtIzena1 = new JTextField();
		txtIzena1.setText("izena");
		txtIzena1.setColumns(10);
		txtIzena1.setBounds(10, 197, 114, 20);
		getContentPane().add(txtIzena1);
		
		txtNan1 = new JTextField();
		txtNan1.setText("nan");
		txtNan1.setColumns(10);
		txtNan1.setBounds(10, 217, 114, 20);
		getContentPane().add(txtNan1);
		
		txtMod1 = new JTextField();
		txtMod1.setText("Modifikatu nahi dena");
		txtMod1.setColumns(10);
		txtMod1.setBounds(10, 340, 114, 20);
		getContentPane().add(txtMod1);
		
		txtMod2 = new JTextField();
		txtMod2.setText("Modifikazioa");
		txtMod2.setColumns(10);
		txtMod2.setBounds(10, 360, 114, 20);
		getContentPane().add(txtMod2);
		
		txtMod3 = new JTextField();
		txtMod3.setText("Modifikatu nahi dena");
		txtMod3.setColumns(10);
		txtMod3.setBounds(10, 300, 114, 20);
		getContentPane().add(txtMod3);
		
		txtMod4 = new JTextField();
		txtMod4.setText("Modifikazioa");
		txtMod4.setColumns(10);
		txtMod4.setBounds(10, 320, 114, 20);
		getContentPane().add(txtMod4);
		
		txtAbizena0 = new JTextField();
		txtAbizena0.setText("Abizena");
		txtAbizena0.setColumns(10);
		txtAbizena0.setBounds(10, 280, 114, 20);
		getContentPane().add(txtAbizena0);
		
		txtIzena2 = new JTextField();
		txtIzena2.setText("Izena");
		txtIzena2.setColumns(10);
		txtIzena2.setBounds(10, 260, 114, 20);
		getContentPane().add(txtIzena2);
		
		txtLetra = new JTextField();
		txtLetra.setText("Izenaren lehenengo letra");
		txtLetra.setColumns(10);
		txtLetra.setBounds(10, 401, 137, 20);
		getContentPane().add(txtLetra);

	}
}
