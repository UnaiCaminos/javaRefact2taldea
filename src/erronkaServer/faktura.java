package erronkaServer;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.io.IOException;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;

public class faktura extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnAktualizatu;
	private JTextField txtId;
	private JTextField txtId0;
	private JButton btnPdf;
	private JTextField txtIzena;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					faktura frame = new faktura();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		    }
	
	public faktura() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 671);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnPdf = new JButton("Pdf-a sortu");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;

		        try {
		            
		            String url = "jdbc:mysql://192.168.115.2:3306/erronka2";
		            String usuario = "2taldea";
		            String contraseña = "2taldea@";
		            connection = DriverManager.getConnection(url, usuario, contraseña);
                    String Id;
                    Id=txtId0.getText();
		            String query = "SELECT nan, izena, abizena1, idEskaera, produktukoPrezioa, prezioTotala, konponenteMota, modelo, marka, kopurua"
		            		+ " FROM saskia "
		            		+ " JOIN bezeroak ON saskia.nan_bezeroa = bezeroak.nan "
		            		+ " JOIN produktueskaera ON saskia.id_eskaera = produktueskaera.idEskaera "
		            		+ " JOIN konponenteak ON produktueskaera.idProduktua = konponenteak.id"
		            		+ " WHERE produktueskaera.idEskaera ="+Id;
		            PreparedStatement preparedStatement = connection.prepareStatement(query);
		            ResultSet resultSet = preparedStatement.executeQuery();
		            
		            
		            
		            // Pdf-ak gordetzen diren karpeta zehaztu
		            String dok = "..//javaRefact2taldea//dokumentuak/";
		            String imagePath = "..//javaRefact2taldea//dokumentuak/";
		            File carpeta = new File(dok);

		            // Karpeta espezifikatuan pdf-a sortu
		            String izn;
		            izn = txtIzena.getText();
		            String pdf = izn+".pdf";
		            Document document = new Document();
		            try {
						PdfWriter.getInstance(document, new FileOutputStream(dok + File.separator + pdf));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            document.open();
		           //Logoa gehitu
		            

		            // Titulua gehitu
		            Paragraph title = new Paragraph("Black Market");
		            title.setAlignment(Element.ALIGN_RIGHT); // Titulua eskubialdean jartzeko
		            document.add(title);
		            
		            float x = 18;
		            float y = 725;
		            
		            try {
		              com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance("..//javaRefact2taldea//logoa//logo.png"); //Logoa dagoen ruta
		              logo.scaleToFit(200, 100); // Logoaren tamainua
		              logo.setAbsolutePosition(x, y); // Logoaren posizioa 
		              document.add(logo);
		              document.add(new Paragraph("\n"));
		              document.add(new Paragraph("\n"));
		              document.add(new Paragraph("\n"));
		              document.add(new Paragraph("\n"));
		              document.add(new Paragraph("\n"));
		              document.add(new Paragraph("\n"));
		            } catch (IOException e1) {
		                e1.printStackTrace();//
		            } catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (java.io.IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            
		            double sumatotal = 0;
		            
		            while (resultSet.next()) {
		                String nan = resultSet.getString("nan");
		                String izena = resultSet.getString("izena");
		                String abizena1 = resultSet.getString("abizena1");
		                String produktukoPrezioa = resultSet.getString("produktukoPrezioa");
		                String konponenteMota = resultSet.getString("konponenteMota");
		                String modelo = resultSet.getString("modelo");
		                String marka = resultSet.getString("marka");
		                String kopurua = resultSet.getString("kopurua");
		                
		                double num = Double.parseDouble(produktukoPrezioa);
		                double kop = Double.parseDouble(kopurua);
	                    double suma = 0;
	                    suma = (num*kop) + suma;
	                    sumatotal =suma + sumatotal;
		                
		                document.add(new Paragraph("NAN: " + nan));
		                document.add(new Paragraph("Izena: " + izena));
		                document.add(new Paragraph("Abizena: " + abizena1));
		                document.add(new Paragraph("Produktuaren prezioa: " + produktukoPrezioa+"€"));
		                document.add(new Paragraph("Produktua: " + konponenteMota));
		                document.add(new Paragraph("Modeloa: " + modelo));
		                document.add(new Paragraph("Marka: " + marka));
		                document.add(new Paragraph("Kopurua: " + kopurua));
		                
		                document.add(new Paragraph("\n"));
		            }
		            
		            document.add(new Paragraph("Prezio totala: " + sumatotal+"€"));
		            
		            document.add(new Paragraph("\n"));
	                
		            Paragraph subtitle = new Paragraph("Eskerrik asko erosteagatik/Gracias por la compra/Thank you for buying");
	                subtitle.setAlignment(Element.ALIGN_LEFT);
	                document.add(subtitle);
		            
		            document.close();
		            connection.close();

		            JOptionPane.showMessageDialog(null,"PDF sortu da karpeta honetan: " + dok);
		            
		        } catch (SQLException | DocumentException e1) {
		            e1.printStackTrace();
		        }
			}
		});
		btnPdf.setBounds(0, 26, 112, 23);
		contentPane.add(btnPdf);
		
		JButton btnTaula = new JButton("Taula");
		btnTaula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioaServer kon=new konexioaServer();
				Connection conexion = kon.getConnection();
				String sql = "SELECT * FROM erronka2.saskia";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("id_eskaera");
				model.addColumn("nan_bezeroa");
				model.addColumn("Segimentua_eus");
				model.addColumn("prezioTotala");
				model.addColumn("Segimentua_es");
				model.addColumn("Segimentua_en");
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
		btnTaula.setBounds(0, 131, 112, 23);
		contentPane.add(btnTaula);
		
		table = new JTable();
		table.setBounds(135, 10, 815, 508);
		contentPane.add(table);
		
		btnAktualizatu = new JButton("Aktualizatu");
		btnAktualizatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioaServer kon=new konexioaServer();
				    Connection conexion = kon.getConnection();
				    String id;
				    id=txtId.getText();
				    String query ="UPDATE erronka2.saskia SET segimentua_eus = 'BIDALIA', segimentua_es = 'ENVIADO', segimentua_en = 'SENT' WHERE id_eskaera ="+id;
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
		btnAktualizatu.setBounds(0, 164, 112, 23);
		contentPane.add(btnAktualizatu);
		
		txtId = new JTextField();
		txtId.setText("Eskariaren id");
		txtId.setColumns(10);
		txtId.setBounds(0, 196, 125, 20);
		contentPane.add(txtId);
		
		txtId0 = new JTextField();
		txtId0.setText("Eskariaren id-a");
		txtId0.setColumns(10);
		txtId0.setBounds(0, 59, 125, 20);
		contentPane.add(txtId0);
		
		
		txtIzena = new JTextField();
		txtIzena.setText("Pdf-aren izena");
		txtIzena.setColumns(10);
		txtIzena.setBounds(0, 89, 125, 20);
		contentPane.add(txtIzena);
		

	}
}
