package erronka;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Panela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Panela(String user) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 538, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBezeroa = new JButton("Bezeroa");
		btnBezeroa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.equals("Jon")||user.equals("Eneko")||user.equals("Maitane")||user.equals("Ander")||user.equals("Mikel")){
							System.out.println("ONDO DA!");
							bezeroak frame = new bezeroak();
							frame.setVisible(true);
						}else System.out.println("Baimena ukatua");
			}
		});
		btnBezeroa.setBounds(0, 0, 256, 101);
		contentPane.add(btnBezeroa);
		
		JButton btnHornitzailea = new JButton("Hornitzailea");
		btnHornitzailea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if(user.equals("Gorka")||user.equals("Ander")||user.equals("Mikel")) {
					Hornitzaileak frame = new Hornitzaileak();
					frame.setVisible(true);
					}else System.out.println("Baimena ukatua");
			}
		});
		btnHornitzailea.setBounds(258, 0, 216, 101);
		contentPane.add(btnHornitzailea);
		
		JButton btnBiltegia = new JButton("Biltegia");
		btnBiltegia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.equals("Mikel")||user.equals("Ander")||user.equals("Naroa")||user.equals("Iker")||user.equals("Julen")||user.equals("Aitor")||user.equals("Ane")||user.equals("Unai")||user.equals("Karla")||user.equals("Markel")||user.equals("Maialen")||user.equals("Igor")||user.equals("Eneko")||user.equals("Maitane")) {
							System.out.println("ONDO DA!");
							biltegia frame = new biltegia();
							frame.setVisible(true);
						}else System.out.println("Baimena ukatua");
			}
			
		});
		btnBiltegia.setBounds(0, 101, 256, 149);
		contentPane.add(btnBiltegia);
		
		JButton btnBerriak = new JButton("Berriak");
		btnBerriak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.equals("Mikel")||user.equals("Ander")||user.equals("Naroa")||user.equals("Iker")||user.equals("Julen")||user.equals("Aitor")||user.equals("Ane")||user.equals("Unai")||user.equals("Karla")||user.equals("Markel")||user.equals("Maialen")||user.equals("Igor")||user.equals("Eneko")||user.equals("Maitane")) {
					System.out.println("ONDO DA!");
					berriak frame = new berriak();
					frame.setVisible(true);
				}else System.out.println("Baimena ukatua");
	        }
			
		});
		btnBerriak.setBounds(256, 101, 218, 149);
		contentPane.add(btnBerriak);
		
		
	}
}
