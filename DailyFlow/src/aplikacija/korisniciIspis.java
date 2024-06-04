package aplikacija;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class korisniciIspis extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JTextArea textAreaPregled;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			korisniciIspis dialog = new korisniciIspis();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public korisniciIspis() {
		setTitle("Korisnici");
		setBounds(100, 100, 552, 365);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 11, 492, 271);
		contentPanel.add(scrollPane);
		{
		textAreaPregled = new JTextArea();
		scrollPane.setViewportView(textAreaPregled);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnZatvori = new JButton("Zatvori");
				btnZatvori.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnZatvori.setActionCommand("OK");
				buttonPane.add(btnZatvori);
				getRootPane().setDefaultButton(btnZatvori);
			}
		}
		dohvatIzBaze(textAreaPregled);
	}
	public void dohvatIzBaze(JTextArea textArea) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/lmajetic?" +
														"user=lmajetic&password=11");
			
			String sql = "SELECT * FROM Korisnik;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String text="";
			while (rs.next()) {
				text += "Email : "+ rs.getString("EmailKorisnika")+"\n";
				text += "Lozinka: "+ rs.getString("Lozinka")+"\n";
				text += "Korisnicko ime: "+ rs.getString("KorisnickoIme")+"\n";
				text += "Preferencije: "+ rs.getString("PreferencijeKorisnika")+"\n\n";
				
			}
		textArea.setText(text);
			
		conn.close();
			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
}
