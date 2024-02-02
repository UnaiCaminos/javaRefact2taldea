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
				if(user.equals("Roberto")||user.equals("Celia")||user.equals("Beñat")||user.equals("Martín")||user.equals("Isabel")||user.equals("Carlos")){
							System.out.println("ONDO DA!");
							bezeroak frame = new bezeroak();
							frame.setVisible(true);
						}else System.out.println("Baimena ukatua");
			}
		});
		btnBezeroa.setBounds(0, 0, 256, 191);
		contentPane.add(btnBezeroa);
		
		new JButton("Hornitzailea").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if(user.equals("Celia")||user.equals("Beñat")||user.equals("Raúl")) {
					Hornitzaileak frame = new Hornitzaileak();
					frame.setVisible(true);
					}else System.out.println("Baimena ukatua");
			}
		});
<<<<<<< HEAD
		new JButton("Hornitzailea").setBounds(258, 0, 216, 101);
		contentPane.add(new JButton("Hornitzailea"));
=======
		btnHornitzailea.setBounds(258, 0, 266, 191);
		contentPane.add(btnHornitzailea);
>>>>>>> b982bab9d706937193e80286850f900c2357fd42
		
		JButton btnBiltegia = new JButton("Biltegia");
		btnBiltegia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.equals("Laura")||user.equals("Beñat")||user.equals("Martín")||user.equals("Miguel")||user.equals("Javier")||user.equals("Sara")||user.equals("Sergio")||user.equals("Daniel")||user.equals("Juan")) {
							System.out.println("ONDO DA!");
							JFrame frame = new konponenteak();
							frame.setVisible(true);
						}else System.out.println("Baimena ukatua");
			}
			
		});
		btnBiltegia.setBounds(0, 191, 524, 214);
		contentPane.add(btnBiltegia);
		
		
	}
}
