package aplikacija;


import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class registracija extends JDialog {

	//private JFrame frmLogin;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldEmail;
	private JTextField textFieldLozinka;
	private JTextField textFieldKIme;
	private JTextField textFieldLozinkaPonovo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			registracija dialog = new registracija();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public registracija() {
		setTitle("Registracija");
		setBounds(100, 100, 410, 238);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(55, 11, 57, 29);
		contentPanel.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(122, 15, 207, 20);
		contentPanel.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		
		textFieldLozinka = new JTextField();
		textFieldLozinka.setColumns(10);
		textFieldLozinka.setBounds(122, 98, 207, 20);
		contentPanel.add(textFieldLozinka);
		
		JLabel lblLozinka = new JLabel("Zaporka:");
		lblLozinka.setBounds(39, 94, 57, 29);
		contentPanel.add(lblLozinka);
		
		JLabel lblKorisnicko = new JLabel("Korisnicko ime:");
		lblKorisnicko.setBounds(10, 54, 102, 29);
		contentPanel.add(lblKorisnicko);
		
		textFieldKIme = new JTextField();
		textFieldKIme.setColumns(10);
		textFieldKIme.setBounds(122, 58, 207, 20);
		contentPanel.add(textFieldKIme);
		
		JLabel lblLozinkaPonovo = new JLabel("Ponovite lozinku:");
		lblLozinkaPonovo.setBounds(10, 134, 102, 29);
		contentPanel.add(lblLozinkaPonovo);
		
		textFieldLozinkaPonovo = new JTextField();
		textFieldLozinkaPonovo.setColumns(10);
		textFieldLozinkaPonovo.setBounds(122, 138, 207, 20);
		contentPanel.add(textFieldLozinkaPonovo);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					String email = textFieldEmail.getText();
					String lozinka = textFieldLozinka.getText();
					String lozinkaPonovo = textFieldLozinkaPonovo.getText();
					String kime = textFieldKIme.getText();
					
					if (lozinkaPonovo.equals(lozinka)) {
						try{
							Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
							Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/lmajetic?" + "user=lmajetic&password=11");
							
							String sql = "INSERT INTO Korisnik (EmailKorisnika, Lozinka, KorisnickoIme) VALUES(?,?,?);";
							PreparedStatement stmt = conn.prepareStatement(sql);
							stmt.setString(1,email);
							stmt.setString(2,lozinka);
							stmt.setString(3,kime);
							stmt.execute();
							conn.close();
							
							JOptionPane.showMessageDialog(null, "Uspjesno ste kreirali racun!","Uspjeh!", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
						catch(Exception ex){
							JOptionPane.showMessageDialog(null, ex.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
						}
						dispose();
					}
					else JOptionPane.showMessageDialog(null, "Lozinke se ne podudaraju, pokusajte ponovno.","Greska", JOptionPane.ERROR_MESSAGE);
					
				}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);}
		{

			
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
		}
	}
}
