package aplikacija;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class recepti extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			recepti dialog = new recepti();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public recepti() {
		setTitle("Recepti");
		setBounds(100, 100, 741, 485);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 11, 693, 391);
		contentPanel.add(scrollPane);
		{
		}
		JTextArea textAreaPregled = new JTextArea();
		scrollPane.setViewportView(textAreaPregled);
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
			String sqlCountK = "SELECT COUNT(IDKomentara) AS BrojK FROM Komentar WHERE SifraRecepta = ?;";
			String sql = "SELECT * FROM Recept;";
			String sqlK = "SELECT IDKomentara, EmailKorisnika, SadrzajKomentara, OcjenaKomentara FROM Komentar WHERE SifraRecepta = ?;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			PreparedStatement stmtCountK = conn.prepareStatement(sqlCountK);
			PreparedStatement stmtK = conn.prepareStatement(sqlK);
			String text="";
			
			while (rs.next()) {
				int i = 0;
				text +="\n" + "Sifra recepta: "+ rs.getInt("SifraRecepta")+"\n";
				text += "Nativ recepta: "+ rs.getString("NazivRecepta")+"\n";
				text += "Email korisnika: "+ rs.getString("EmailKorisnika")+"\n";
				text += "Opis: "+ rs.getString("OpisRecepta")+"\n";
				text += "Oznake: "+ rs.getString("OznakeRecepta")+"\n";
				if (rs.getFloat("OcjenaRecepta") == 5.0) {
					text += "Ocjena: "+ rs.getFloat("OcjenaRecepta")+" - odlican recept\n\n";
				}
				else {
					text += "Ocjena: "+ rs.getFloat("OcjenaRecepta")+"\n\n";
				}
				
				String SifraRec = "";
				SifraRec += rs.getInt("SifraRecepta");
				stmtCountK.setString(1,SifraRec);
				stmtK.setString(1,SifraRec);
				
				ResultSet rsCountK = stmtCountK.executeQuery();
				rsCountK.next();
				int brojK = rsCountK.getInt("BrojK");
				
				ResultSet rsK = stmtK.executeQuery();
				
				while (rsK.next()) {
					if (brojK == 0) {
						break;
					}
					else if (i <= brojK) {
						if (i == 0) {
							text += "Komentari: "+"\n";
						}
						text += "ID komentara: "+ rsK.getInt("IDKomentara")+"\n";
						text += "Email korisnika: "+ rsK.getString("Komentar.EmailKorisnika")+"\n";
						text += "Sadrzaj: "+ rsK.getString("SadrzajKomentara")+"\n";
						text += "Ocjena: "+ rsK.getString("OcjenaKomentara")+"\n\n";
						i+=1;
					}
					else {
						break;
					}
					
				}
			}
			textArea.setText(text);
			conn.close();
			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
}
