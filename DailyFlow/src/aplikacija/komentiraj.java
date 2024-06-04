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

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;

public class komentiraj extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNazivRecepta;
	public static int ocjena;
	public static ButtonModel button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			komentiraj dialog = new komentiraj();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public komentiraj() {
		setBounds(100, 100, 612, 394);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 33, 302, 161);
		contentPanel.add(scrollPane);
		
		JTextArea textAreaPregled = new JTextArea();
		scrollPane.setViewportView(textAreaPregled);
		
		JLabel lblNazivRecepta = new JLabel("Naziv recepta");
		lblNazivRecepta.setBounds(322, 17, 86, 14);
		contentPanel.add(lblNazivRecepta);
		
		textFieldNazivRecepta = new JTextField();
		textFieldNazivRecepta.setBounds(322, 42, 209, 20);
		contentPanel.add(textFieldNazivRecepta);
		textFieldNazivRecepta.setColumns(10);
		
		JLabel lblOcjena = new JLabel("Ocjena");
		lblOcjena.setBounds(321, 87, 58, 14);
		contentPanel.add(lblOcjena);
		
		JRadioButton rdbtnOcjena1 = new JRadioButton("1");
		rdbtnOcjena1.setBounds(318, 108, 36, 23);
		rdbtnOcjena1.setActionCommand("1");
		contentPanel.add(rdbtnOcjena1);
		
		JRadioButton rdbtnOcjena2 = new JRadioButton("2");
		rdbtnOcjena2.setBounds(356, 108, 36, 23);
		rdbtnOcjena2.setActionCommand("2");
		contentPanel.add(rdbtnOcjena2);
		
		JRadioButton rdbtnOcjena3 = new JRadioButton("3");
		rdbtnOcjena3.setBounds(394, 108, 36, 23);
		rdbtnOcjena3.setActionCommand("3");
		contentPanel.add(rdbtnOcjena3);
		
		JRadioButton rdbtnOcjena4 = new JRadioButton("4");
		rdbtnOcjena4.setBounds(432, 108, 36, 23);
		rdbtnOcjena4.setActionCommand("4");
		contentPanel.add(rdbtnOcjena4);
		
		JRadioButton rdbtnOcjena5 = new JRadioButton("5");
		rdbtnOcjena5.setBounds(470, 108, 36, 23);
		rdbtnOcjena5.setActionCommand("5");
		contentPanel.add(rdbtnOcjena5);
		
		ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnOcjena1);
	    group.add(rdbtnOcjena2);
	    group.add(rdbtnOcjena3);
	    group.add(rdbtnOcjena4);
	    group.add(rdbtnOcjena5);
	   
		JLabel lblSadrzajKomentara = new JLabel("Sadrzaj komentara:");
		lblSadrzajKomentara.setBounds(323, 180, 131, 14);
		contentPanel.add(lblSadrzajKomentara);
		
		JTextArea textAreaKomentar = new JTextArea();
		textAreaKomentar.setBounds(10, 205, 550, 106);
		contentPanel.add(textAreaKomentar);
		
		JLabel lblRecepti = new JLabel("Recepti");
		lblRecepti.setBounds(10, 8, 46, 14);
		contentPanel.add(lblRecepti);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Komentiraj");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String naziv = textFieldNazivRecepta.getText();
						String komentar = textAreaKomentar.getText();
						button =group.getSelection();
						ocjena = Integer.valueOf(button.getActionCommand());
						
						try {
							Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
							Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/lmajetic?" +
																		"user=lmajetic&password=11");
							String sql = "INSERT INTO Komentar(EmailKorisnika, SifraRecepta, SadrzajKomentara, OcjenaKomentara) VALUES(?,?,?,?);";
							PreparedStatement stmt = conn.prepareStatement(sql);
							
							String sqlRecept = "SELECT SifraRecepta FROM Recept WHERE NazivRecepta = ?";
							PreparedStatement stmtRecept = conn.prepareStatement(sqlRecept);
							stmtRecept.setString(1,naziv);
							
							ResultSet rs = stmtRecept.executeQuery();
							
							boolean has_results = rs.next();
							
							
							if (has_results) {
								int SifraRecepta = rs.getInt("SifraRecepta");								
								stmt.setString(1, Global.email);
								stmt.setInt(2, SifraRecepta);
								stmt.setString(3, komentar);
								stmt.setInt(4, ocjena);
								
								stmt.execute();
							}
						}
						catch(Exception ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Odustani");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		dohvatIzBaze(textAreaPregled);
	}
	public void dohvatIzBaze(JTextArea textArea) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/lmajetic?" +
														"user=lmajetic&password=11");

			String sql = "SELECT * FROM Recept;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String text="";
			
			while (rs.next()) {
				text +="\n" + "Sifra recepta: "+ rs.getInt("SifraRecepta")+"\n";
				text += "Nativ recepta: "+ rs.getString("NazivRecepta")+"\n";
				text += "Email korisnika: "+ rs.getString("EmailKorisnika")+"\n";
				text += "Opis: "+ rs.getString("OpisRecepta")+"\n";
				
			}
			textArea.setText(text);
			conn.close();
			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
}
