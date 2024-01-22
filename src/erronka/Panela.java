package erronka;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interfaz_grafica.bezeroak;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		btnBezeroa.setBounds(0, 0, 283, 101);
		contentPane.add(btnBezeroa);
		
		JButton btnBezeroa_ordainketa = new JButton("Bezeroen ordainketak");
		btnBezeroa_ordainketa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		       if(user.equals("Iker")||user.equals("Eneko")||user.equals("Maitane")||user.equals("Naroa")||user.equals("Ander")||user.equals("Mikel")){
							System.out.println("ONDO DA!");
							Bezeroak_ordainketak frame = new Bezeroak_ordainketak();
							frame.setVisible(true);
						}else System.out.println("Baimena ukatua");
			}
		});
		btnBezeroa_ordainketa.setBounds(0, 101, 189, 149);
		contentPane.add(btnBezeroa_ordainketa);
		
		JButton btnHornitzailea = new JButton("Hornitzailea");
		btnHornitzailea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if(user.equals("Gorka")||user.equals("Ander")||user.equals("Mikel")) {
					Hornitzaileak frame = new Hornitzaileak();
					frame.setVisible(true);
					}else System.out.println("Baimena ukatua");
			}
		});
		btnHornitzailea.setBounds(284, 0, 238, 101);
		contentPane.add(btnHornitzailea);
		
		JButton btnNewButton_3 = new JButton("Hornitzaileak ordainketak");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.equals("Naroa")||user.equals("Ander")||user.equals("Mikel")||user.equals("Gorka")||user.equals("Iker")) {
							System.out.println("ONDO DA!");
							Hornitzaileak_ordainketak frame = new Hornitzaileak_ordainketak();
							frame.setVisible(true);
						}else System.out.println("Baimena ukatua");
			}
		});
		btnNewButton_3.setBounds(190, 101, 220, 149);
		contentPane.add(btnNewButton_3);
		
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
		btnBiltegia.setBounds(411, 101, 127, 149);
		contentPane.add(btnBiltegia);
		
		
	}
}
