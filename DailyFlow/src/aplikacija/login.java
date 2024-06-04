package aplikacija;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;

public class login extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldEmail;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			login dialog = new login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public login() {
		setTitle("Login");
		setBounds(100, 100, 410, 201);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(22, 26, 57, 29);
		contentPanel.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(89, 30, 207, 20);
		contentPanel.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblLozinka = new JLabel("Zaporka:");
		lblLozinka.setBounds(22, 72, 57, 29);
		contentPanel.add(lblLozinka);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setBounds(89, 76, 207, 20);
		contentPanel.add(passwordField);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener()
			{
				@SuppressWarnings("unlikely-arg-type")
				public void actionPerformed(ActionEvent e) {
					String email = textFieldEmail.getText();
					String lozinkaprava = new String(passwordField.getPassword());
					try{
						Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
						Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/lmajetic?" + "user=lmajetic&password=11");
						
						String sql = "SELECT * FROM Korisnik WHERE EmailKorisnika = ?;";
						PreparedStatement stmt = conn.prepareStatement(sql);
						stmt.setString(1,email);
						
						ResultSet rs = stmt.executeQuery();
						
						boolean has_results = rs.next();

						if(has_results){
							String lozinkaBaza = rs.getString("Lozinka");
							if(lozinkaprava.equals(lozinkaBaza)) {
								JOptionPane.showMessageDialog(null, "Ulogirani ste!","Uspjeh!", JOptionPane.INFORMATION_MESSAGE);
								dispose();
								Global.isLoggedIn = true;
								Global.email = email;
								menu window = new menu();
								window.setJFrameVisible(true);
							} else {
								JOptionPane.showMessageDialog(null, "Unijeli ste krivu lozinku!","Greska!", JOptionPane.ERROR_MESSAGE);
							}
									
						}else {
							JOptionPane.showMessageDialog(null, "Ne postoji takav Email!","Greska!", JOptionPane.ERROR_MESSAGE);
						}
						
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
					}
					
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
