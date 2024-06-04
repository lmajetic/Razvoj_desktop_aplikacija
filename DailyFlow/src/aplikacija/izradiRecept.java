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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class izradiRecept extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNazivRecepta;
	private JTextField textFieldOznakeRecepta;
	private JTextField textFieldKolicina;

	public static int SifraRecepta;
 

    public static void setSifraR(int sifra){
        izradiRecept.SifraRecepta = sifra;
    }
    
    public static void createTable() {
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/lmajetic?" +
														"user=lmajetic&password=11");
			String sql = "INSERT INTO Recept(NazivRecepta, EmailKorisnika, OznakeRecepta) VALUES (?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			String naziv = "temp";
			String oznake = "";
			
			stmt.setString(1, naziv);
			stmt.setString(2, Global.email);
			stmt.setString(3, oznake);
			stmt.execute();
	
			conn.close();
			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    public static void getSifraRecepta() {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/lmajetic?" +
														"user=lmajetic&password=11");
			
			String naziv = "temp";
			
			String sqlSifra = "SELECT SifraRecepta FROM Recept WHERE EmailKorisnika = ? && NazivRecepta = ?";
			PreparedStatement stmtSifra = conn.prepareStatement(sqlSifra);
			
			stmtSifra.setString(1, Global.email);
			stmtSifra.setString(2, naziv);
			
			ResultSet rs = stmtSifra.executeQuery();
			boolean has_results = rs.next();
			
			if(has_results){
				setSifraR(rs.getInt("SifraRecepta"));
			}
	
			conn.close();
			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
		}
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			izradiRecept dialog = new izradiRecept();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	public izradiRecept() {
		setTitle("Izrada recepta");
		setBounds(100, 100, 602, 442);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNazivRecepta = new JLabel("Naziv recepta:");
		lblNazivRecepta.setBounds(10, 15, 81, 14);
		contentPanel.add(lblNazivRecepta);
		
		textFieldNazivRecepta = new JTextField();
		textFieldNazivRecepta.setBounds(101, 12, 207, 21);
		contentPanel.add(textFieldNazivRecepta);
		textFieldNazivRecepta.setColumns(10);
		
		JLabel lblOpisRecepta = new JLabel("Opis recepta:");
		lblOpisRecepta.setBounds(10, 56, 81, 21);
		contentPanel.add(lblOpisRecepta);
		
		JLabel lblOznake = new JLabel("Oznake:");
		lblOznake.setBounds(10, 181, 67, 21);
		contentPanel.add(lblOznake);
		
		textFieldOznakeRecepta = new JTextField();
		textFieldOznakeRecepta.setBounds(101, 180, 207, 21);
		contentPanel.add(textFieldOznakeRecepta);
		textFieldOznakeRecepta.setColumns(10);
		
		JComboBox comboBoxSastojci = new JComboBox();
		comboBoxSastojci.setModel(new DefaultComboBoxModel(new String[] {"Piletina", "Losos", "Govedina", "Riza", "Tjestenina", "Krompir", "Maslac", "Maslinovo ulje", "Secer", "Jaja", "Mlijeko", "Jogurt", "Mrkva"}));
		comboBoxSastojci.setBounds(101, 230, 156, 22);
		contentPanel.add(comboBoxSastojci);
		
		JLabel lblSastojci = new JLabel("Sastojci:");
		lblSastojci.setBounds(10, 234, 46, 14);
		contentPanel.add(lblSastojci);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(254, 264, 310, 95);
		contentPanel.add(scrollPane);
		
		JTextArea textAreaSastojci = new JTextArea();
		textAreaSastojci.setEditable(false);
		scrollPane.setViewportView(textAreaSastojci);
		
		JLabel lblNewLabel = new JLabel("Kolicina:");
		lblNewLabel.setBounds(10, 270, 67, 14);
		contentPanel.add(lblNewLabel);
		
		textFieldKolicina = new JTextField();
		textFieldKolicina.setBounds(101, 267, 67, 20);
		contentPanel.add(textFieldKolicina);
		textFieldKolicina.setColumns(10);
		
		JTextArea textAreaOpis = new JTextArea();
		textAreaOpis.setBounds(101, 54, 345, 88);
		contentPanel.add(textAreaOpis);
		
		JButton btnDodajSastojak = new JButton("Dodaj sastojak");
		btnDodajSastojak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sastojak = (String) comboBoxSastojci.getSelectedItem();
				String kolicina = textFieldKolicina.getText();
				String text=textAreaSastojci.getText() + "";
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
					Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/lmajetic?" +
																"user=lmajetic&password=11");
					String sql = "INSERT INTO SastojakUReceptu(SifraSastojka, SifraRecepta, EmailKorisnika, KolicinaSastojka) VALUES(?,?,?,?);";
					PreparedStatement stmt = conn.prepareStatement(sql);
					
					String sqlSastojak = "SELECT SifraSastojka FROM Sastojak WHERE NazivSastojka = ?";
					PreparedStatement stmtSastojak = conn.prepareStatement(sqlSastojak);
					stmtSastojak.setString(1,sastojak);
					
					ResultSet rsSastojak = stmtSastojak.executeQuery();
					
					boolean has_results = rsSastojak.next();
					
					
					if (has_results) {
						int SifraSastojka = rsSastojak.getInt("SifraSastojka");
						text +="\n" + "Sastojak: " + sastojak + "    ";
						text += "Kolicina: " + kolicina + "    ";
						text += "Sifra: " + String.valueOf(SifraSastojka);
						textAreaSastojci.setText(text);
						
						stmt.setInt(1, SifraSastojka);
						stmt.setInt(2, SifraRecepta);
						stmt.setString(3, Global.email);
						stmt.setString(4, kolicina);
						
						stmt.execute();
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDodajSastojak.setBounds(306, 230, 123, 23);
		contentPanel.add(btnDodajSastojak);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Izradi recept");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String naziv = textFieldNazivRecepta.getText();
						String opis = textAreaOpis.getText();
						String oznake = textFieldOznakeRecepta.getText();
						
						try {
							Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
							Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/lmajetic?" +
																		"user=lmajetic&password=11");
							String sql = "UPDATE Recept SET NazivRecepta=?, OpisRecepta=?, OznakeRecepta=? WHERE EmailKorisnika=? && NazivRecepta=?";
							PreparedStatement stmt = conn.prepareStatement(sql);
							String temp = "temp";
							
							stmt.setString(1,naziv);
							stmt.setString(2,opis);
							stmt.setString(3,oznake);
							stmt.setString(4,Global.email);
							stmt.setString(5,temp);
							
							stmt.execute();
							conn.close();
							dispose();
							
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
						try {
							Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
							Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/lmajetic?" +
																		"user=lmajetic&password=11");
							
							String temp = "temp";
						    
							
							String sqlSuR = "DELETE FROM SastojakUReceptu WHERE SifraRecepta=?";
							PreparedStatement stmtSuR = conn.prepareStatement(sqlSuR);
							

							stmtSuR.setInt(1, SifraRecepta);
							stmtSuR.executeUpdate();
							
							PreparedStatement st = conn.prepareStatement("DELETE FROM Recept WHERE EmailKorisnika = ? && NazivRecepta = ?;");
						    
						    st.setString(1, Global.email);
						    st.setString(2, temp);
					        st.executeUpdate();
							conn.close();
							dispose();
							
						}
						catch(Exception ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
