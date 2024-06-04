package aplikacija;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class prijave extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prijave frame = new prijave();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void vidljivaPrijava(boolean vidljiv) {
		prijave frame = new prijave();
		frame.setVisible(vidljiv);
	}
	public prijave() {
		setTitle("Prijava");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistracija = new JLabel("Nemas racun?");
		lblRegistracija.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistracija.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblRegistracija.setBounds(139, 11, 137, 31);
		contentPane.add(lblRegistracija);
		
		JButton btnRegistracija = new JButton("Registriraj se!");
		btnRegistracija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registracija dlgr = new registracija();
				dlgr.setVisible(true);
			}
		});
		btnRegistracija.setBounds(139, 63, 137, 31);
		contentPane.add(btnRegistracija);
		
		JButton btnLogin = new JButton("Vec imam racun");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login dlgl = new login();
				dlgl.setVisible(true);
				dispose();
			}
		});
		btnLogin.setBounds(139, 149, 137, 31);
		contentPane.add(btnLogin);
		
		JButton btnGostLogin = new JButton("Ulaz kao gost");
		btnGostLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Ulogirani ste kao gost!\nNeke usluge neće biti dostupne dok se ne ulogirate!","Uspjeh!", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				menu window = new menu();
				window.setJFrameVisible(true);
			}
		});
		btnGostLogin.setBounds(139, 191, 137, 31);
		contentPane.add(btnGostLogin);
	}
}
