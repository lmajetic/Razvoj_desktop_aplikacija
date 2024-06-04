package aplikacija;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.sql.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class menu {

	private JFrame frame;
	//Global instance;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu window = new menu();
					window.frame.setVisible(false);
					prijave framep = new prijave();
					framep.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void setJFrameVisible(boolean visible)
	{
	    frame.setVisible(visible);
	}
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 854, 599);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dobrodosli!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 140, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnDrugiKorisnici = new JButton("Lista korisnika");
		btnDrugiKorisnici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				korisniciIspis dlgKi = new korisniciIspis();
				dlgKi.setVisible(true);
			}
		});
		btnDrugiKorisnici.setBounds(10, 65, 140, 25);
		frame.getContentPane().add(btnDrugiKorisnici);
		
		JLabel lblNewLabel_1 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/MapCat.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img));
		lblNewLabel_1.setBounds(160, 25, 668, 524);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnRecepti = new JButton("Recepti");
		btnRecepti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recepti dlgr = new recepti();
				dlgr.setVisible(true);
			}
		});
		btnRecepti.setBounds(10, 136, 140, 25);
		frame.getContentPane().add(btnRecepti);
		
		JButton btnIzradiRecept = new JButton("Izradi Recept");
		btnIzradiRecept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Global.isLoggedIn) {
					izradiRecept dlgIr = new izradiRecept();
					dlgIr.setVisible(true);
					izradiRecept.createTable();
					izradiRecept.getSifraRecepta();
				}
				else {
					JOptionPane.showMessageDialog(null, "Potrebno je ulogirati se kako bi izradili recept!","Greska", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnIzradiRecept.setBounds(10, 191, 140, 25);
		frame.getContentPane().add(btnIzradiRecept);
		
		JButton btnKomentar = new JButton("Komentiraj");
		btnKomentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Global.isLoggedIn) {
					komentiraj dlgk = new komentiraj();
					dlgk.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Potrebno je ulogirati se kako bi objavili komentar!","Greska", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnKomentar.setBounds(10, 230, 140, 25);
		frame.getContentPane().add(btnKomentar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"MapCat.png", "Seal.jpg", "alex.jpg", "bridge.jpg"}));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String image = String.valueOf(comboBox.getSelectedItem());
				Image img = new ImageIcon(this.getClass().getResource("/" + image)).getImage();
				lblNewLabel_1.setIcon(new ImageIcon(img));		
			}
		});
		comboBox.setBounds(10, 276, 140, 22);
		frame.getContentPane().add(comboBox);
		
		if (Global.isLoggedIn == false) {
			JButton btnLogin = new JButton("Login");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login frame = new login();
					frame.setVisible(true);
					setJFrameVisible(false);
					btnLogin.setEnabled(false);
				}
			});
			btnLogin.setBounds(10, 100, 140, 25);
			frame.getContentPane().add(btnLogin);
		}
		
	
	}
}
